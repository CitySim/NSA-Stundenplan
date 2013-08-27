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
		{ t: "v", s: "//www.youtube.com/embed/_K8G4eAXC88?rel=0&autoplay=1&loop=1" } # cuban spider
		{ t: "v", s: "//www.youtube.com/embed/MtN1YnoL46Q?rel=0&autoplay=1&loop=1" } # duck song
		{ t: "p", s: "//i.imgur.com/bBKKy.gif" }
		{ t: "p", s: "//i.imgur.com/fdabF.gif" }
		{ t: "p", s: "//i.imgur.com/yU7bA.gif" }
		{ t: "p", s: "//i.imgur.com/8NXsd.gif" }
		{ t: "p", s: "//i.imgur.com/1NCGi.gif" }
		{ t: "p", s: "//i.imgur.com/baZaV.gif" }
		{ t: "p", s: "//i.imgur.com/rFTLKwl.gif" }
		{ t: "p", s: "//i.imgur.com/LqGSNc1.gif" }
		{ t: "p", s: "//i.imgur.com/m00qVbx.jpg" }
		{ t: "p", s: "//i.imgur.com/3fA05yq.gif" }
		{ t: "p", s: "//i.imgur.com/gHn7f2m.gif" }
		{ t: "p", s: "//i.imgur.com/kJZQAkO.gif" }
		{ t: "p", s: "//i.imgur.com/3LYGxiv.gif" }
		{ t: "p", s: "//i.imgur.com/cPWodt2.gif" }
		{ t: "p", s: "//i.minus.com/ibkfHHcBzBdxms.gif" }
		{ t: "p", s: "http://24.media.tumblr.com/defb8d97d2b73ba8f0e8ba595d39ac72/tumblr_mr1kvyyWoF1ruv1gno1_500.gif" }
		{ t: "p", s: "http://25.media.tumblr.com/f7a49d541824b1a8bf9ba6577655fb69/tumblr_mhphvgezmJ1rqazauo1_r1_250.gif" }
		{ t: "p", s: "//imgs.xkcd.com/comics/the_mother_of_all_suspicious_files.png" }
		{ t: "p", s: "//d24w6bsrhbeh9d.cloudfront.net/photo/aLKQ98W_460sa.gif" }
		{ t: "p", s: "//lh6.googleusercontent.com/-fKy3VlWjRh0/UDkGjrSCiqI/AAAAAAAEjkM/1TT-GtPpVmI/w800-h800/photo.jpg" }
		{ t: "p", s: "http://anongallery.org/img/4/1/i-have-no-idea-what-im-doing-dog.jpg" }
		{ t: "p", s: "http://6.asset.soup.io/asset/5093/5414_978b.gif" }
		{ t: "p", s: "http://6.asset.soup.io/asset/5079/4262_7d25_500.gif" }
		{ t: "p", s: "http://f.asset.soup.io/asset/4908/3839_27b2_704.gif" }
		{ t: "p", s: "http://6.asset.soup.io/asset/4907/7510_8096.gif" }
		{ t: "p", s: "http://0.asset.soup.io/asset/4901/5744_bfa8.gif" }
		{ t: "p", s: "http://theoldreader.com/kittens/600/400" } #                __             
		{ t: "p", s: "http://theoldreader.com/kittens/600/400" } #               /\ \__          
		{ t: "p", s: "http://theoldreader.com/kittens/600/400" } #   ___     __  \ \ ,_\   ____  
		{ t: "p", s: "http://theoldreader.com/kittens/600/400" } #  /'___\ /'__`\ \ \ \/  /',__\ 
		{ t: "p", s: "http://theoldreader.com/kittens/600/400" } # /\ \__//\ \L\.\_\ \ \_/\__, `\
		{ t: "p", s: "http://theoldreader.com/kittens/600/400" } # \ \____\ \__/.\_\\ \__\/\____/
		{ t: "p", s: "http://theoldreader.com/kittens/600/400" } #  \/____/\/__/\/_/ \/__/\/___/ 
		{ t: "p", s: "http://theoldreader.com/kittens/600/400" } # 
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
