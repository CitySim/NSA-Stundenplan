class window.nsa.Views.NavBar extends Backbone.View
	template: nsa.handlebars.navbar

	render: () =>
		@$el.html @template
			user: nsa.Data.user

		return
