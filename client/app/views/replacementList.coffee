class window.nsa.Views.ReplacementList extends Backbone.View
	template: nsa.handlebars.replacementList
	className: "view-replacement-list"

	initialize: () =>
		if @options.week?
			@week = @options.week
		else
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

		momentWeek = moment(@week, "YYYY").add("weeks", parseInt(@week.split("W")[1]) - 1)

		@$el.html @template
			week: momentWeek.format("ww, YYYY")
			navigation:
				prev: momentWeek.add("weeks", -1).format("YYYY-[W]ww")
				next: momentWeek.add("weeks", 2).format("YYYY-[W]ww")
			collection: _.groupBy @collection.toJSON(), (r) =>
				if r.lesson?
					r.format_start = moment(r.lesson.timeFrom, "hh:mm a").format("HH:mm")
					r.format_end = moment(r.lesson.timeTo, "hh:mm a").format("HH:mm")
					
				return r.day.description

		return


