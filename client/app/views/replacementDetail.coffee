class window.nsa.Views.ReplacementDetail extends Backbone.View
	template: nsa.handlebars.replacementDetail

	initialize: () =>
		nsa.app.fetchList "replacements", (err) =>
			return if err?

			@model = nsa.Data.replacements.get(@options.replacementId)
			if not @model?
				nsa.app.error
					no: 2704
					title: "Fehler beim laden"
					message: "Fehler beim laden der Vertretung"
				return
			
			@render()
			return

		return

	render: () =>
		if not @model?
			@$el.html nsa.handlebars.loading()
			return

		@$el.html @template
			model: @model.toJSON()

		return
