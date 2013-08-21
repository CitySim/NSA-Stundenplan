window.nsa = _.extend {}, window.nsa,
	Views: {}
	Models: {}
	Collections: {}
	Data: {}
	config:
		api: "/api"
	version: "0.1.1"

class window.nsa.App extends Backbone.Router
	routes:
		""						: "home"
		"timetable"				: "timetable"
		"timetable/:type/:id"	: "timetableDetail"
		"replacement"			: "replacement"
		"replacement/:id"		: "replacementDetails"
		"login"					: "login"
		"about"					: "about"
		"*error"				: "errorNotFound"

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

		@showView(new nsa.Views.TimetableDetail())
		return

	replacement: () =>
		@showView(new nsa.Views.ReplacementList())
		return

	replacementDetails: () =>
		@showView(new nsa.Views.ReplacementDetail())
		return

	login: () =>
		@showView(new nsa.Views.Login())
		return

	about: () =>
		@showView(new nsa.Views.About())
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
			when "classes"	then collection = new nsa.Collections.Classes()
			when "days"		then collection = new nsa.Collections.Days()
			when "periods"	then collection = new nsa.Collections.Periods()
			when "rooms"	then collection = new nsa.Collections.Rooms()
			when "subjects"	then collection = new nsa.Collections.Subjects()
			when "teachers"	then collection = new nsa.Collections.Teachers()

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
	navbarView = new nsa.Views.NavBar
		el: $(".app-navbar")
	navbarView.render()

	window.nsa.app = new window.nsa.App()
	Backbone.history.start()
	#Backbone.history.start({ pushState: true })

	return
