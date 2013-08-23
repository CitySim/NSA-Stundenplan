class window.nsa.Views.TimetableDetail extends Backbone.View
	template: nsa.handlebars.timetableDetail
	className: "view-timetable-detail"
	
	loading: true

	initialize: () =>
		nsa.app.fetchLists ["classes", "days", "lessons", "rooms", "subjects", "teachers"], (err) =>
			return if err?

			@loading = false
			@render()
			return

		model = new nsa.Models.TimeTable()
		model.fetchData = @options.fetchData
		model.fetch
			success: () =>
				@model = model
				return
		return
		
	render: () =>
		if @loading or not @model?
			@$el.html nsa.handlebars.loading()
			return
		
		tempLessons = nsa.Data.lessons.toJSON()
		lessonNo = 1
		_.each tempLessons, (l) =>
			l.no = lessonNo++
			l.format_start = moment(l.timeFrom, "hh:mm a").format("HH:mm")
			l.format_end = moment(l.timeTo, "hh:mm a").format("HH:mm")
			return

		tempDays = nsa.Data.days.toJSON()
		_.each tempDays, (d) =>
			d.short = d.description.substr(0, 2)
			return

		@$el.html @template
			days: tempDays
			lessons: tempLessons
			timetable: @model.toJSON()

		return
