class window.nsa.Models.Replacement extends Backbone.Model
	url: () =>
		if @isNew()
			return nsa.config.api + "/replacement?lesson=#{@fetchData.lesson}&form=#{@fetchData.form}&day=#{@fetchData.day}"
		else
			return nsa.config.api + "/replacement"
