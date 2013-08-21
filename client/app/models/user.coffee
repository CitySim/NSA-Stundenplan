class window.nsa.Models.User extends Backbone.Model
	isLoggedIn: () =>
		return @id?
