class window.nsa.Views.Timetable extends Backbone.View
	template: nsa.handlebars.timetable

	render: () =>
		@$el.html @template
			user: nsa.Data.user

		return
