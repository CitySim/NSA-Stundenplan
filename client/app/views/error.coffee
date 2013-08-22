class window.nsa.Views.Error extends Backbone.View
	template: nsa.handlebars.error

	errorImageList: [
		{ t: "v", s: "//www.youtube.com/embed/iqT0iFZifgw?rel=0&autoplay=1&loop=1" } # FUKKIRETA 10 hours 
		{ t: "v", s: "//www.youtube.com/embed/jnLGWph0VZ0?rel=0&autoplay=1&loop=1" } # Courage to tell a lie 10 hours 
		{ t: "v", s: "//www.youtube.com/embed/jI-kpVh6e1U?rel=0&autoplay=1&loop=1" } # Nyan Cat 10 hours HD 720p 
		{ t: "v", s: "//www.youtube.com/embed/oXYZttxunPw?rel=0&autoplay=1&loop=1" } # WTF-Papagei
		{ t: "v", s: "//www.youtube.com/embed/eh7lp9umG2I?rel=0&autoplay=1&loop=1" } # What's going on? 10 hours
		{ t: "v", s: "//www.youtube.com/embed/kxopViU98Xo?rel=0&autoplay=1&loop=1" } # Epic sax guy 10 hours
		{ t: "v", s: "//www.youtube.com/embed/sCNrK-n68CM?rel=0&autoplay=1&loop=1" } # Trololo 10 hours
		{ t: "v", s: "//www.youtube.com/embed/5r-_RV8q6aI?rel=0&autoplay=1&loop=1" } # pink fluffy unicorn
		{ t: "p", s: "//imgs.xkcd.com/comics/increased_risk.png" } # xkcd 1252
		{ t: "p", s: "//thecodinglove.com/post/58165354217/when-i-try-to-understand-a-foreign-code" }
		{ t: "p", s: "http://24.media.tumblr.com/defb8d97d2b73ba8f0e8ba595d39ac72/tumblr_mr1kvyyWoF1ruv1gno1_500.gif" }
		{ t: "p", s: "//i.imgur.com/bBKKy.gif" }
		{ t: "p", s: "//imgs.xkcd.com/comics/the_mother_of_all_suspicious_files.png" }
	]

	render: () =>
		errorImage = @errorImageList[Math.floor(Math.random() * @errorImageList.length)]

		switch errorImage.t
			when "v"
				errorImage = """<iframe class="errorvid" width="100%" height="315" src="#{errorImage.s}" frameborder="0" allowfullscreen></iframe>"""
			when "p"
				errorImage = """<img class="errorvid" width="100%" src="#{errorImage.s}">"""

		@$el.html @template
			error: @options.error
			errorImage: errorImage

		return
