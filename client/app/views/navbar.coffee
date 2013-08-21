class window.nsa.Views.NavBar extends Backbone.View
	template: nsa.handlebars.navbar

	initialize: () =>
		@model = nsa.Data.user
		@listenTo(@model, "change", @render)
		return

	render: () =>
		@$el.html @template
			user: @model.toJSON()
			isLoggedIn: @model.isLoggedIn()

		return
