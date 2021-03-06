class window.nsa.Views.ReplacementEdit extends Backbone.View
	template: nsa.handlebars.replacementEdit
	className: "view-replacement-edit"

	loading: true

	events:
		"click .app-save": "save"
		"click [name=cancel]": "toggleCancel"

	initialize: () =>
		nsa.app.fetchLists ["classes", "days", "lessons", "rooms", "subjects", "teachers"], (err) =>
			return if err?

			@loading = false
			@render()
			return

		if @options.newReplacement
			@model = new nsa.Models.Replacement _.extend @options.modelData,
				week: moment().format("YYYY-[W]ww")
		else
			nsa.app.fetchList "replacements", (err) =>
				return if err?

				@model = nsa.Data.replacements.get(@options.replacementId)
				if not @model?
						nsa.app.error
						no: 2900
						title: "Fehler beim laden (ID nicht gefunden)"
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

		model = @model.toJSON()

		@$el.html @template
			model: model
			days: nsa.Data.days.toJSON()
			lessons: tempLessons
			teachers: nsa.Data.teachers.toJSON()
			rooms: nsa.Data.rooms.toJSON()
			subjects: nsa.Data.subjects.toJSON()
			classes: nsa.Data.classes.toJSON()
			newReplacement: @options.newReplacement or false
			cancelable: @options.modelData? and not isNaN(@options.modelData.teacher.id) and not isNaN(@options.modelData.room.id) and not isNaN(@options.modelData.subject.id)

		return

	toggleCancel: () =>
		e = @$("[name=cancel]")

		if e.is(":checked")
			@$("[name=teacher]").prop("disabled", true).val(@options.modelData.teacher.id)
			@$("[name=room]").prop("disabled", true).val(@options.modelData.room.id)
			@$("[name=subject]").prop("disabled", true).val(@options.modelData.subject.id)
		else
			@$("[name=teacher]").prop("disabled", false)
			@$("[name=room]").prop("disabled", false)
			@$("[name=subject]").prop("disabled", false)

		return

	save: () =>
		data = {}

		@$("form input, form select, form textarea").each (i, e) =>
			e = $(e)
			name = e.attr("name")
			val = e.val()

			return if not name? or not val?

			if e.is("[type=checkbox]")
				val = if e.is(":checked") then 1 else 0

			data[name] = val
			return

		@$("form input, form select, form textarea, form button").attr("disabled", true)

		@model.fetchData = @options.fetchData

		@model.set(data)
		@model.save {},
			success: () =>
				nsa.app.navigate("replacement/#{@model.id}", { trigger: true })
				return
			error: () =>
				nsa.app.error
					no: 4010
					title: "Fehler"
					message: "Der Server hat ein Problem"

				@$("form input, form select, form textarea, form button").attr("disabled", false)
				return

		return
