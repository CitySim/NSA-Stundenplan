class window.nsa.Collections.Teachers extends Backbone.Collection
	model: nsa.Models.Teacher
	url: nsa.config.api + "/teacher"