class window.nsa.Views.Home extends Backbone.View
	template: nsa.handlebars.home

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
			model: @model.toJSON()

		return
