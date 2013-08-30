class window.nsa.Collections.Replacements extends Backbone.Collection
	model: nsa.Models.Replacement
	url: () =>
		if @fetchData?
			url = nsa.config.api + "/replacement?"
			_.each @fetchData, (v, k) =>
				url += k + "=" + v + "&"
				return
			return url
		else
			return nsa.config.api + "/replacement"
