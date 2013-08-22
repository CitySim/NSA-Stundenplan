class window.nsa.Views.TimetableDetail extends Backbone.View
	template: nsa.handlebars.timetableDetail
	className: "view-timetable-detail"
	
	loading: true

	initialize: () =>
		nsa.app.fetchLists ["classes", "days", "periods", "rooms", "subjects", "teachers"], (err) =>
			return if err?

			@loading = false
			@render()
			return

		return
		
	render: () =>
		if @loading
			@$el.html nsa.handlebars.loading()
			return
			
		@$el.html @template
			collection: @collection.toJSON()

		return
