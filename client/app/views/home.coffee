class window.nsa.Views.Home extends Backbone.View
	template: nsa.handlebars.home

	render: () =>
		@$el.html @template
			dummy: 0