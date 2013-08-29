class window.nsa.Views.ReplacementEdit extends Backbone.View
	template: nsa.handlebars.replacementEdit

	loading: true

	events:
		"click .app-save": "save"

	initialize: () =>
		nsa.app.fetchLists ["classes", "lessons", "rooms", "subjects", "teachers"], (err) =>
			return if err?

			@loading = false
			@render()
			return

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

		@$el.html @template
			model: @model.toJSON()
			lessons: tempLessons
			teachers: nsa.Data.teachers.toJSON()
			rooms: nsa.Data.rooms.toJSON()
			subjects: nsa.Data.subjects.toJSON()
			classes: nsa.Data.classes.toJSON()

		return

	save: () =>
		data = {}

		@$("form input, form select, form textarea").each (i, e) =>
			e = $(e)
			name = e.attr("name")
			val = e.val()

			return if not name? or not val?

			data[name] = val
			return

		console.log data
		return
