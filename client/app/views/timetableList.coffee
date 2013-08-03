class window.nsa.Views.TimetableList extends Backbone.View
	template: nsa.handlebars.timetableList
	className: "view-timetable-list"

	render: () =>
		@$el.html @template
			user: nsa.Data.user

		return
