class window.nsa.Views.ReplacementDetail extends Backbone.View
	template: nsa.handlebars.replacementDetail
	className: "view-replacement-detail"

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

		model = @model.toJSON()
		model.format_start = moment(model.lesson.timeFrom, "hh:mm a").format("HH:mm")
		model.format_end = moment(model.lesson.timeTo, "hh:mm a").format("HH:mm")

		if not model.teacher? and not model.room? and not model.subject?
			model.dropped = true

		@$el.html @template
			model: model
			isLoggedIn: nsa.Data.user.isLoggedIn()

		return
