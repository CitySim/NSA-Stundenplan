class window.nsa.Views.TimetableDetail extends Backbone.View
	template: nsa.handlebars.timetableDetail
	className: "view-timetable-detail"
	
	initialize: () =>
		nsa.app.fetchLists ["classes", "days", "periods", "rooms", "subjects", "teachers"], (err) =>
			return if err?

			@render()
			return

		return
		
	render: () =>
		@$el.html @template
			user: nsa.Data.user

		return
