window.nsa = _.extend {}, window.nsa,
	Views: {}
	Models: {}
	Data: {}

class window.nsa.App extends Backbone.Router
	routes:
		""						: "home"
		"timetable"				: "timetable"
		"login"					: "login"

	initialize: () =>
		
		return

	home: () =>
		@lastView?.remove()

		homeView = new nsa.Views.Home()
		homeView.render()
		homeView.$el.appendTo(".app-output")

		@lastView = homeView
		return

	timetable: () =>
		@lastView?.remove()

		timetableView = new nsa.Views.Timetable()
		timetableView.render()
		timetableView.$el.appendTo(".app-output")

		@lastView = timetableView
		return

	login: () =>
		@lastView?.remove()

		loginView = new nsa.Views.Login()
		loginView.render()
		loginView.$el.appendTo(".app-output")

		@lastView = loginView
		return

$ () ->
	navbarView = new nsa.Views.NavBar
		el: $(".app-navbar")
	navbarView.render()

	window.nsa.app = new window.nsa.App()
	Backbone.history.start()
	#Backbone.history.start({ pushState: true })

	return
