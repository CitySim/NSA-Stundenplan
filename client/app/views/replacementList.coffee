class window.nsa.Views.ReplacementList extends Backbone.View
	template: nsa.handlebars.replacementList

	render: () =>
		@$el.html @template
			user: nsa.Data.user

		return
