window.nsa = _.extend {}, window.nsa,
	Views: {}
	Models: {}
	Collections: {}
	Data: {}
	config:
		api: "/api"
	version: "0.5.0"

class window.nsa.App extends Backbone.Router
	routes:
		""							: "home"
		"timetable"					: "timetable"
		"timetable/:type/:id"		: "timetableDetail"
		"replacement"				: "replacement"
		"replacement/new/:lesson/:form/:day": "replacementNew"
		"replacement/:id"			: "replacementDetails"
		"replacement/:id/edit"		: "replacementEdit"
		"login"						: "login"
		"logout"					: "logout"
		"login/changepw"			: "changepw"
		"about"						: "about"
		"admin"						: "admin"
		"*error"					: "errorNotFound"

	initialize: () =>
		return

	home: () =>
		@showView(new nsa.Views.Home())
		return

	timetable: () =>
		@showView(new nsa.Views.TimetableList())
		return

	timetableDetail: (type, id) =>
		if ["room", "teacher", "class"].indexOf(type) is -1
			@error
				no: 2405
				title: "Unbekannter Typ"
				message: "Unbekannter Typ von Stundenplan (#{type})"
			return

		@showView new nsa.Views.TimetableDetail
			fetchData:
				type: type
				id: id

		return

	replacement: () =>
		@showView(new nsa.Views.ReplacementList())
		return

	replacementDetails: (id) =>
		@showView new nsa.Views.ReplacementDetail
			replacementId: id

		return

	replacementEdit: (id) =>
		if not nsa.Data.user.isLoggedIn()
			@navigate("login", { trigger: true })
			return
			
		@showView new nsa.Views.ReplacementEdit
			replacementId: id

		return

	replacementNew: (lesson, form, day) =>
		if not nsa.Data.user.isLoggedIn()
			@navigate("login", { trigger: true })
			return
			
		@showView new nsa.Views.ReplacementEdit
			newReplacement: true
			fetchData:
				lesson: lesson
				form: form
				day: day

		return

	login: () =>
		if nsa.Data.user.isLoggedIn()
			@navigate("admin", { trigger: true })
			return

		@showView(new nsa.Views.Login())
		return

	logout: () =>
		if not nsa.Data.user.isLoggedIn()
			@navigate("home", { trigger: true })
			return

		@showView(new nsa.Views.Logout())
		return

	changepw: () =>
		@showView(new nsa.Views.ChangePassword())
		return

	about: () =>
		@showView(new nsa.Views.About())
		return

	admin: () =>
		if not nsa.Data.user.isLoggedIn()
			@navigate("login", { trigger: true })
			return

		@showView(new nsa.Views.Admin())
		return

	errorNotFound: () =>
		@error
			no: 404
			title: "Not Found"
			message: "Diese URL stimmt nicht (glauben wir), tut uns leid."

		return

	error: (error) =>
		@showView new nsa.Views.Error
			error: error
		return

	showView: (view) =>
		@lastView?.remove()

		if window.matchMedia? and not window.matchMedia("(min-width: 768px)").matches
			# close nav
			$(".nsa-navbar-collapse").collapse("hide")
			console.log "close"


		view.render()
		view.$el.appendTo(".app-output")
		
		@lastView = view
		return

	fetchLists: (lists, callback) =>
		if lists.length is 0
			callback(null)
			return

		@fetchList lists[0], (err) =>
			if err?
				callback(err)
				return

			@fetchLists(lists.splice(1), callback)
			return
		return

	fetchList: (list, callback) =>
		switch list
			when "classes"		then collection = new nsa.Collections.Classes()
			when "days"			then collection = new nsa.Collections.Days()
			when "lessons"		then collection = new nsa.Collections.Lessons()
			when "rooms"		then collection = new nsa.Collections.Rooms()
			when "subjects"		then collection = new nsa.Collections.Subjects()
			when "teachers"		then collection = new nsa.Collections.Teachers()
			when "replacements"	then collection = new nsa.Collections.Replacements()

		if not collection?
			nsa.app.error
				no: 1001
				title: "Fehler"
				message: "Unbekannter Listen Typ wurde versucht zu laden (#{list})"
			return

		collection.fetch
			success: () =>
				nsa.Data[list] = collection
				callback(null, collection)
				return
			error: () =>
				delete nsa.Data[list]
				callback("error")

				nsa.app.error
					no: 1002
					title: "Fehler"
					message: "Fehler beim Laden der Daten (#{list})"
				
				return

		return

$ () ->
	nsa.Data.user = new nsa.Models.User()
	
	cookieVal = $.cookie("NSA-Cookie")
	if cookieVal?
		nsa.Data.user.set("id", cookieVal)

	navbarView = new nsa.Views.NavBar
		el: $(".app-navbar")
	navbarView.render()

	#$(".nsa-navbar-collapse").collapse()

	window.nsa.app = new window.nsa.App()
	Backbone.history.start()
	#Backbone.history.start({ pushState: true })

	Handlebars.registerHelper "ifcond", (v1, operator, v2, options) ->
		switch operator
			when "=="	then (if v1 is v2	then options.fn(this) else options.inverse(this))
			when "!="	then (if v1 isnt v2	then options.fn(this) else options.inverse(this))
			when "<"	then (if v1 < v2	then options.fn(this) else options.inverse(this))
			when "<="	then (if v1 <= v2	then options.fn(this) else options.inverse(this))
			when ">"	then (if v1 > v2	then options.fn(this) else options.inverse(this))
			when ">="	then (if v1 >= v2	then options.fn(this) else options.inverse(this))
			when "and"	then (if v1 and v2	then options.fn(this) else options.inverse(this))
			when "or"	then (if v1 or v2	then options.fn(this) else options.inverse(this))
			else option.inverse()
		
	return
