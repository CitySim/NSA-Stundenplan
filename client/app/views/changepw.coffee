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
					@$(".app-alert").html("Ihr neues Passwort wurde ihnen zugesendet").show()
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