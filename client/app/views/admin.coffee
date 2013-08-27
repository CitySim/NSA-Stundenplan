class window.nsa.Views.Admin extends Backbone.View
	template: nsa.handlebars.admin

	render: () =>
		@$el.html @template
			dummy: 0

		return
