module.exports = (grunt) ->
	grunt.initConfig
		pkg: grunt.file.readJSON("package.json")
		watch:
			coffee:
				files: [ "app/*/*.coffee", "app/app.coffee" ]
				tasks: [ "coffee" ]
			handlebars:
				files: [ "templates/*.hbs" ]
				tasks: [ "handlebars" ]
			css:
				files: [ "css/*.css" ]
				tasks: [ "cssmin" ]
			static:
				files:[ "index.html", "libs/*.js" ]
				tasks: [ "copy" ]

		coffee:
			app:
				options:
					join: true
					sourceMap: true
				files:
					"build/app.js": [ "app/app.coffee", "app/models/*.coffee", "app/collections/*.coffee", "app/views/*.coffee" ]

		handlebars:
			templates:
				options:
					namespace: "nsa.handlebars"
					processName: (filePath) ->
						return filePath.replace(/^templates\//, "").replace(/\//g, ".").replace(/\.hbs$/, "")
				files:
					"build/templates.js": "templates/*.hbs"

		uglify:
			libs:
				options:
					sourceMap: "build/libs.js.map"
					sourceMappingURL: "libs.js.map"
				files:
					"build/libs.js": [ "libs/moment.js", "libs/jquery-2.0.3.js", "libs/bootstrap.js", "libs/underscore.js", "libs/backbone.js", "libs/handlebars.js" ]

		cssmin:
			combine:
				files:
					"build/style.css": "css/*.css"

		copy:
			static:
				expand: true
				src: [ "index.html", "libs/*.js", "font/*.*" ]
				dest: "build/"
				
	grunt.loadNpmTasks("grunt-contrib-uglify")
	grunt.loadNpmTasks("grunt-contrib-coffee")
	grunt.loadNpmTasks("grunt-contrib-copy")
	grunt.loadNpmTasks("grunt-contrib-handlebars")
	grunt.loadNpmTasks("grunt-contrib-uglify")
	grunt.loadNpmTasks("grunt-contrib-watch")
	grunt.loadNpmTasks("grunt-contrib-cssmin")

	grunt.registerTask("default", [ "coffee", "copy", "cssmin", "handlebars", "uglify" ])
