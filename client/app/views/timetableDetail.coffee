class window.nsa.Views.TimetableDetail extends Backbone.View
	template: nsa.handlebars.timetableDetail
	className: "view-timetable-detail"

	events:
		"click .app-subcribe": "subcribe"
		"click .app-toggle": "toggleReplacement"
	
	loading: true
	showReplacement: true

	initialize: () =>
		@start = moment().startOf("week").add(1, "day")
		@end = moment().endOf("week").add(1, "day")

		nsa.app.fetchLists ["classes", "days", "lessons", "rooms", "subjects", "teachers"], (err) =>
			return if err?

			@loading = false
			@render()
			return

		replacements = new nsa.Collections.Replacements()
		replacements.fetchData = 
			start: @start.format("MMM DD, YYYY hh:mm:ss A")
			end: @end.format("MMM DD, YYYY hh:mm:ss A")
		replacements.fetchData[@options.fetchData.type] = @options.fetchData.id
		replacements.fetch
			success: () =>
				@replacements = replacements
				return
			error: () =>
				nsa.app.error
					no: 2810
					title: "Stundenplan-Ladefehler"
					message: "Änderungen könnten nicht geladen werden."
				return

		model = new nsa.Models.TimeTable()
		model.fetchData = @options.fetchData
		model.fetch
			success: () =>
				@model = model
				return
			error: () =>
				nsa.app.error
					no: 2809
					title: "Stundenplan-Ladefehler"
					message: "Es ist ein schwerer unerwarteter Stundenplan-Ladefehler aufgetreten"
				return
		return

	toggleReplacement: () =>
		@showReplacement = not @showReplacement
		@render()
		return
		
	render: () =>
		if @loading or not @model? or not @replacements
			@$el.html nsa.handlebars.loading()
			return

		# days prepare
		tempDays = nsa.Data.days.toJSON()
		_.each tempDays, (d) =>
			d.short = d.description.substr(0, 2)
			return

		# lessons prepare
		tempLessons = nsa.Data.lessons.toJSON()
		lessonNo = 1
		_.each tempLessons, (l) =>
			l.no = lessonNo++
			l.format_start = moment(l.timeFrom, "hh:mm a").format("HH:mm")
			l.format_end = moment(l.timeTo, "hh:mm a").format("HH:mm")
			return

		# prepare timetable
		tempTimeTable = []
		_.each tempLessons, (l) =>
			l.days = []
			_.each tempDays, (d) =>
				tempLesson = {}

				# lesson suchen
				_.each @model.get("timetableLessons"), (t) =>
					if d.id is t.day.id and l.id is t.lesson.id
						tempLesson = t
					return

				_.each @replacements.toJSON(), (r) =>
					if d.id is r.day.id and l.id is r.lesson.id
						tempLesson.replacement = r
					return

				l.days.push(tempLesson)
				return
			return

		@$el.html @template
			days: tempDays
			lessons: tempLessons
			timetable: @model.toJSON()
			showReplacement: @showReplacement
			isLoggedIn: nsa.Data.user.isLoggedIn()

		return

	subcribe: () =>
		$.ajax
			url: nsa.config.api + "/newsletter/confirm"
			method: "GET"
			data:
				"id": @model.get("form").id
				"email": @$(".app-email").val()
			success: (data) =>
				try
					@$(".app-email").val("").attr("placeholder", "Vielen Dank!")
				catch e
					nsa.app.error
						no: 2800
						title: "Fehler beim abbonieren"
						message: "Es tratt ein unbekannter Fehler auf.<br>" + e.message

				return

			error: () =>
				nsa.app.error
					no: 2801
					title: "Fehler beim abbonieren"
					message: "Es tratt ein unbekannter Fehler auf."

				return

		return