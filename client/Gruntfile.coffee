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
				files: [ "style.css" ]
				tasks: [ "copy" ]
			index:
				files: [ "index.html" ]
				tasks: [ "copy" ]

		coffee:
			app:
				options:
					join: true
				files:
					"build/app.js": "app/**/*.coffee"

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
				files:
					"build/libs.js": [ "libs/jquery-2.0.3.js", "libs/underscore.js", "libs/backbone.js", "libs/handlebars.js" ]

		cssmin:
			combine:
				files:
					"build/style.css": "build/style.css"

		copy:
			static:
				expand: true
				src: [ "style.css", "index.html" ]
				dest: "build/"
				
	grunt.loadNpmTasks("grunt-contrib-uglify")
	grunt.loadNpmTasks("grunt-contrib-coffee")
	grunt.loadNpmTasks("grunt-contrib-copy")
	grunt.loadNpmTasks("grunt-contrib-handlebars")
	grunt.loadNpmTasks("grunt-contrib-uglify")
	grunt.loadNpmTasks("grunt-contrib-watch")
	grunt.loadNpmTasks("grunt-contrib-cssmin")

	grunt.registerTask("default", [ "coffee", "copy", "cssmin", "handlebars", "uglify" ])
