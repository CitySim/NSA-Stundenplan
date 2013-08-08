class window.nsa.Views.Home extends Backbone.View
	template: nsa.handlebars.home

	initialize: () =>
		if not nsa.Data.school?
			@model = nsa.Data.school = new nsa.Models.School()
			@model.fetch
				success: @render
				error: () =>
					delete nsa.Data.school
					nsa.app.error
						no: 1000
						title: "Fehler"
						message: "Fehler beim Laden der Daten der Schule"
					
					return

		return

	render: () =>
		@$el.html nsa.handlebars.loading()

		###
		@$el.html @template
			school: @model.toJSON
		###

		return
