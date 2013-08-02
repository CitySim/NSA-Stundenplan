class window.nsa.Views.Home extends Backbone.View
	template: nsa.handebars.home

	render: () =>
		@$el.html @template
			dummy: 0