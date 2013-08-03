class window.nsa.Views.ReplacementDetail extends Backbone.View
	template: nsa.handlebars.replacementDetail

	render: () =>
		@$el.html @template
			user: nsa.Data.user

		return
