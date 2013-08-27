class window.nsa.Views.ReplacementList extends Backbone.View
	template: nsa.handlebars.replacementList

	initialize: () =>
		collection = new nsa.Collections.Replacements()
		collection.fetch
			success: () =>
				@collection = collection
				@render()
				return
			error: () =>
				delete @collection
				nsa.app.error
					no: 2401
					title: "Fehler"
					message: "Fehler beim Laden der Vertretungen"
				
				return

		return

	render: () =>
		if not @collection?
			@$el.html nsa.handlebars.loading()
			return

		@$el.html @template
			collection: _.groupBy @collection.toJSON(), (r) =>
				r.format_date = moment(r.date, "ddd DD, YYYY hh:mm:ss A").format("DD.MM.YYYY")
				return r.format_date

		return


