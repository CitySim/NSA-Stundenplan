class window.nsa.Views.About extends Backbone.View
	template: nsa.handlebars.about

	initialize: () =>

		$.ajax
			url: "api/version"
			dataType: "json"
			success: (data) =>
				@$(".app-server-version").html("v. #{data.version}")
				return
			error: () =>
				@$(".app-server-version").html("Fehler")
				return

		return

	render: () =>
		@$el.html @template
			version: nsa.version

		return
