class window.nsa.Views.Error extends Backbone.View
	template: nsa.handlebars.error

	render: () =>
		@$el.html @template
			error: @options.error

		return
