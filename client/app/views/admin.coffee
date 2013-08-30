class window.nsa.Views.Admin extends Backbone.View
	template: nsa.handlebars.admin

	events:
		"click .app-save": "save"
	
	initialize: () =>
		if nsa.Data.school?
			@model = nsa.Data.school
		else
			@model = new nsa.Models.School()
			@model.fetch
				success: () =>
					nsa.Data.school = @model
					@render()
					return
				error: () =>
					delete nsa.Data.school
					nsa.app.error
						no: 1000
						title: "Fehler"
						message: "Fehler beim Laden der Daten der Schule"
					
					return

		return

	render: () =>
		if not @model?
			@$el.html nsa.handlebars.loading()
			return

		@$el.html @template
			school: @model.toJSON()

	save: () =>
		$.ajax
			url: nsa.config.api + "/school"
			method: "POST"
			processData: false
			data: JSON.stringify
				image: @$("[name=image]").val()
				text: @$("[name=text]").val()
			headers:
				"Content-Type": "application/json"

			success: (data) =>
				alert("ok")
				return

			error: () =>
				nsa.app.error
					no: 4067
					title: "Fehler beim Speichern"
					message: "Es tratt ein schwerer Fehler auf"

				return

		return
