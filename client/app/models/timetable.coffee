class window.nsa.Models.TimeTable extends Backbone.Model
	url: () =>
		return nsa.config.api + "/timetable/#{@fetchData.type}?id=#{@fetchData.id}"
