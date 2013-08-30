class window.nsa.Views.Logout extends Backbone.View
	template: nsa.handlebars.logout

	initialize: () =>
		if $.removeCookie("NSA-Cookie")
			nsa.Data.user.unset("id")
			@message = "Sie wurden erfolgreich ausgeloggt"
		else
			@message = "Sie sind nicht eingeloggt"

		return

	render: () =>
		@$el.html @template
			message: @message
		return
		