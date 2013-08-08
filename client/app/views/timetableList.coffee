class window.nsa.Views.TimetableList extends Backbone.View
	template: nsa.handlebars.timetableList
	className: "view-timetable-list"

	events:
		"click .app-tabs a": "showList"

	initialize: () =>

	showList: (event) =>
		$target = $(event.target)
		list = $target.data("list")

		@$(".app-tabs li").removeClass("active")
		$target.closest("li").addClass("active")

		switch list
			when "classes"
				@collection = @collection
			when "rooms"
				@collection = @collection
			when "teachers"
				@collection = @collection

	render: () =>
		@$el.html @template
			user: nsa.Data.user

		return
