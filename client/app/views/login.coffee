class window.nsa.Views.Login extends Backbone.View
	template: nsa.handlebars.login

	events:
		"click .btn.app-login": "sendLogin"

	render: () =>
		@$el.html(@template())

		return

	sendLogin: () =>
		$.ajax
			url: nsa.config.api + "/login"
			method: "POST"
			data:
				user: @$(".app-username").val()
				password: @$(".app-password").val()
			success: (data) =>
				try
					data = JSON.parse(data)

					if not data.status
						@$("app-alert").html(data.error).show()
					else
						nsa.app.navigate("admin")
				catch e
					nsa.app.error
						no: 2543
						title: "Fehler bei Login"
						message: "Es tratt ein schwerer Fehler beim Login auf<br>" + e.message

				return

			error: () =>
				nsa.app.error
					no: 2542
					title: "Fehler bei Login"
					message: "Es tratt ein schwerer Fehler beim Login auf"

				return

		return