class window.nsa.Views.TimetableList extends Backbone.View
	template: nsa.handlebars.timetableList
	className: "view-timetable-list"

	events:
		"click .app-tabs a": "showList"

	initialize: () =>
		@selectedList = "classes"
		return

	showList: (event) =>
		$target = $(event.target)
		list = $target.data("list")

		@$(".app-tabs li").removeClass("active")
		$target.closest("li").addClass("active")

		@selectedList = list
		@renderList()

		return

	render: () =>
		@$el.html(@template())

		@renderList()

		return

	renderList: () =>
		if not nsa.Data[@selectedList]?
			@fetchList()
			return

		@collection = nsa.Data[@selectedList]

		html = ""
		switch @selectedList
			when "classes"
				@collection.each (m) =>
					html += """<a href="#timetable/class/#{m.get("id")}" class="list-group-item">
							#{m.get("description")}
						</a>"""
					return
			when "rooms"
				@collection.each (m) =>
					html += """<a href="#timetable/room/#{m.get("id")}" class="list-group-item">
							#{m.get("description")}
						</a>"""
					return
			when "teachers"
				@collection.each (m) =>
					html += """<a href="#timetable/teacher/#{m.get("id")}" class="list-group-item">
							#{m.get("name")}
						</a>"""
					return


		@$(".app-select-list").html(html)

		return

	fetchList: () =>
		switch @selectedList
			when "classes"
				@collection = new nsa.Collections.Classes()
			when "rooms"
				@collection = new nsa.Collections.Rooms()
			when "teachers"
				@collection = new nsa.Collections.Teachers()

		if not @collection?
			nsa.app.error
				no: 1001
				title: "Fehler"
				message: "Unbekannter Listen Typ wurde versucht zu laden (#{@selectedList})"
			return

		@collection.fetch
			success: () =>
				nsa.Data[@selectedList] = @collection
				@renderList()
				return
			error: () =>
				delete nsa.Data[@selectedList]
				nsa.app.error
					no: 1002
					title: "Fehler"
					message: "Fehler beim Laden der Daten"
				
				return

		return