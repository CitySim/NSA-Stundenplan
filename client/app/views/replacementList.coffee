class window.nsa.Views.ReplacementList extends Backbone.View
	template: nsa.handlebars.replacementList
	className: "view-replacement-list"

	initialize: () =>
		@week = moment().format("YYYY-[W]ww")

		replacements = new nsa.Collections.Replacements()
		replacements.fetchData =
			week: @week

		replacements.fetch
			success: () =>
				@collection = replacements
				@render()
				return
			error: () =>
				nsa.app.error
					no: 2810
					title: "Vertretungen-Ladefehler"
					message: "Änderungen konnten nicht geladen werden."
				return

		return

	render: () =>
		if not @collection?
			@$el.html nsa.handlebars.loading()
			return

		@$el.html @template
			week: moment(@week, "YYYY-[W]ww").format("ww, YYYY")
			collection: _.groupBy @collection.toJSON(), (r) =>
				if r.lesson?
					r.format_start = moment(r.lesson.timeFrom, "hh:mm a").format("HH:mm")
					r.format_end = moment(r.lesson.timeTo, "hh:mm a").format("HH:mm")

				if not r.teacher? and not r.room? and not r.subject?
					r.dropped = true
					
				return r.day.description

		return


