class window.nsa.Collections.Rooms extends Backbone.Collection
	model: nsa.Models.Room
	url: nsa.config.api + "/room"
