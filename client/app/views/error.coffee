class window.nsa.Views.Error extends Backbone.View
	template: nsa.handlebars.error

	render: () =>
		@$el.html @template
			error: @options.error
			vidsource: [
				"//www.youtube.com/embed/iqT0iFZifgw?rel=0&autoplay=1"
				"//www.youtube.com/embed/jnLGWph0VZ0?rel=0&autoplay=1"
				"//www.youtube.com/embed/jI-kpVh6e1U?rel=0&autoplay=1"
				][Math.floor(Math.random() * 3)]

		return
