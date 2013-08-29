class window.nsa.Views.ReplacementList extends Backbone.View
	template: nsa.handlebars.replacementList

	initialize: () =>
		delete nsa.Data.replacements
		nsa.app.fetchList "replacements", (err) =>
			return if err?

			@collection = nsa.Data.replacements
			@render()
			return

		return

	render: () =>
		if not @collection?
			@$el.html nsa.handlebars.loading()
			return

		@$el.html @template
			collection: _.groupBy @collection.toJSON(), (r) =>
				r.format_date = moment(r.date, "MMM DD, YYYY hh:mm:ss A").format("DD.MM.YYYY")
				return r.format_date

		return


