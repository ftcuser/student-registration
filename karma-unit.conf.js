module.exports = function(config){
	config.set({
		frameworks: ['jasmine'],
		browsers: ['PhantomJS'],
		files:[
			   'https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.1/angular.js',
			   'https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.1/angular-route.js',
			   'https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.1/angular-mocks.js',
		       'src/main/webapp/scripts/app-test.js',
		       'src/main/webapp/scripts/services/echo-service.js', 
		       'src/main/webapp/scripts/services/userwebservice.js',		  
		       'src/main/webapp/spec/unit/*.js'
		       ]
	});
};