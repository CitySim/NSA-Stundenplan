class window.nsa.Views.Logout extends Backbone.View
	template: nsa.handlebars.logout

	initialize: () =>
		@message = "sie werden augeloggt..."

		$.ajax
			url: nsa.config.api + "/login/logout?cookie=#{$.cookie("NSA-Cookie")}"
			method: "GET"
			success: (data) =>
				if data.toString() is "false"
					nsa.app.error
						no: 3206
						title: "Fehler beim ausloggen"
						message: "Ein Eintrag konnte nicht gelöscht werden, der Sever meldet einen Fehler."
					return

				setTimeout () =>
					if $.removeCookie("NSA-Cookie")
						nsa.Data.user.unset("id")
						@message = "Sie wurden erfolgreich ausgeloggt"
					else
						@message = "Sie sind nicht eingeloggt"
					@render()

					return
				, 300
				return

			error: () =>
				nsa.app.error
					no: 3201
					title: "Fehler beim Löschen"
					message: "Es tratt ein schwerer Fehler auf"

				return
		return

	render: () =>
		@$el.html @template
			message: @message
		return
