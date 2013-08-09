class window.nsa.Views.Error extends Backbone.View
	template: nsa.handlebars.error

	video: [
		"//www.youtube.com/embed/iqT0iFZifgw?rel=0&autoplay=1&loop=1" # FUKKIRETA 10 hours 
		"//www.youtube.com/embed/jnLGWph0VZ0?rel=0&autoplay=1&loop=1" # Courage to tell a lie 10 hours 
		"//www.youtube.com/embed/jI-kpVh6e1U?rel=0&autoplay=1&loop=1" # Nyan Cat 10 hours HD 720p 
		"//www.youtube.com/embed/oXYZttxunPw?rel=0&autoplay=1&loop=1" # WTF-Papagei
		"//www.youtube.com/embed/eh7lp9umG2I?rel=0&autoplay=1&loop=1" # What's going on? 10 hours
		"//www.youtube.com/embed/EVwlMVYqMu4?rel=0&autoplay=1&loop=1" # Two dogs dining
		"//www.youtube.com/embed/kxopViU98Xo?rel=0&autoplay=1&loop=1" # Epic sax guy 10 hours
		"//www.youtube.com/embed/sCNrK-n68CM?rel=0&autoplay=1&loop=1" # Trololo 10 hours
	]

	render: () =>
		@$el.html @template
			error: @options.error
			vidsource: @video[Math.floor(Math.random() * @video.length)]

		return
