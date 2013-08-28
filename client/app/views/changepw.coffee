class window.nsa.Views.ChangePassword extends Backbone.View
	template: nsa.handlebars.changepassword

	events:
		"click .btn.app-login": "sendPasswordChange"

	render: () =>
		@$el.html(@template())

		return

	sendPasswordChange: () =>
		$.ajax
			url: nsa.config.api + "/login/changepw?user=#{@$(".app-username").val()}"
			method: "GET"

			success: (data) =>
				try
					#debugger
					if data.status is "ok"
						nsa.Data.user.set("id", $.cookie("NSA-Cookie"))
						nsa.app.navigate("admin", { trigger: true })
					else
						@$(".app-alert").html(data).show()
				catch e
					nsa.app.error
						no: 2551
						title: "Fehler beim zurücksetzen das Passworts"
						message: "Es tratt ein schwerer Fehler beim auf, versuchen die später erneut.<br>" + e.message

				return

			error: () =>
				nsa.app.error
					no: 2550
					title: "Fehler beim zurücksetzen das Passworts"
					message: "Es tratt ein schwerer Fehler beim auf, versuchen die später erneut."

				return

		return