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
			headers:
				"Content-Type": "application/x-www-form-urlencoded"

			success: (data) =>
				try
					if data.status is "ok"
						nsa.Data.user.set("id", 1)
						nsa.app.navigate("admin", { trigger: true })
					else
						@$(".app-alert").html(data.error).show()
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