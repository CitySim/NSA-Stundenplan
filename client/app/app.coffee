window.nsa = _.extend {}, window.nsa,
	Views: {}
	Models: {}

class window.nsa.App extends Backbone.Router
	routes:
		""						: "home"

	home: () =>
		homeView = new nsa.Views.Home
			el: $(".app-output")
		homeView.render()

		return

$ () ->
	window.nsa.app = new window.nsa.App()
	Backbone.history.start({ pushState: true })
