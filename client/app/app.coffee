window.nsa = _.extend {}, window.nsa,
	Views: {}
	Models: {}
	Collections: {}
	Data: {}
	config:
		api: "/api"
	version: "0.0.2"

class window.nsa.App extends Backbone.Router
	routes:
		""						: "home"
		"timetable"				: "timetable"
		"timetable/class/:id"	: "timetableDetail"
		"replacement"			: "replacement"
		"replacement/:id"		: "replacementDetails"
		"login"					: "login"
		"about"					: "about"
		"*error"				: "errorNotFound"

	initialize: () =>
		return

	home: () =>
		@lastView?.remove()

		home = new nsa.Views.Home()
		home.render()
		home.$el.appendTo(".app-output")

		@lastView = home
		return

	timetable: () =>
		@lastView?.remove()

		timetableList = new nsa.Views.TimetableList()
		timetableList.render()
		timetableList.$el.appendTo(".app-output")

		@lastView = timetableList
		return

	timetableDetail: () =>
		@lastView?.remove()

		timetableDetail = new nsa.Views.TimetableDetail()
		timetableDetail.render()
		timetableDetail.$el.appendTo(".app-output")

		@lastView = timetableDetail
		return

	replacement: () =>
		@lastView?.remove()

		replacementList = new nsa.Views.ReplacementList()
		replacementList.render()
		replacementList.$el.appendTo(".app-output")

		@lastView = replacementList
		return

	replacementDetails: () =>
		@lastView?.remove()

		replacementDetail = new nsa.Views.ReplacementDetail()
		replacementDetail.render()
		replacementDetail.$el.appendTo(".app-output")

		@lastView = replacementDetail
		return

	login: () =>
		@lastView?.remove()

		loginView = new nsa.Views.Login()
		loginView.render()
		loginView.$el.appendTo(".app-output")

		@lastView = loginView
		return

	about: () =>
		@lastView?.remove()

		about = new nsa.Views.About()
		about.render()
		about.$el.appendTo(".app-output")

		@lastView = about
		return

	errorNotFound: () =>
		@error
			no: 404
			title: "Not Found"
			message: "Diese URL stimmt nicht (glauben wir), tut uns leid."

		return

	error: (error) =>
		@lastView?.remove()

		error = new nsa.Views.Error
			error: error
		error.render()
		error.$el.appendTo(".app-output")

		@lastView = error
		return

$ () ->
	navbarView = new nsa.Views.NavBar
		el: $(".app-navbar")
	navbarView.render()

	window.nsa.app = new window.nsa.App()
	Backbone.history.start()
	#Backbone.history.start({ pushState: true })

	return
