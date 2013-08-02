class window.nsa.Views.Login extends Backbone.View
	template: nsa.handlebars.login

	render: () =>
		@$el.html @template
			dummy: 0