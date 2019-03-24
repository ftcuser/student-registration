module.exports = function(grunt) {
	grunt.initConfig({
		karma : {
			unit: {
				configFile: 'karma-unit.conf.js',
				singleRun : true
				
			}
		}
	});
	grunt.loadNpmTasks('grunt-karma');
	grunt.registerTask('default', ['karma:unit']);
};