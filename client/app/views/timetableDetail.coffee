class window.nsa.Views.TimetableDetail extends Backbone.View
	template: nsa.handlebars.timetableDetail
	className: "view-timetable-detail"

	render: () =>
		@$el.html @template
			user: nsa.Data.user

		return
