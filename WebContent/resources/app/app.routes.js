(function() {
	angular.module("main").config(configApp);

	function configApp($routeProvider, $httpProvider) {

		$routeProvider
		
		.when('/', {
			templateUrl : 'resources/app/components/home/homeView.html',
			controller : 'home',
			controllerAs : 'controller',
			resolve : {
				entities : function(fileSystemService) {
					var conf = {
							params : {
								path : "/"
							}
						}
					return fileSystemService.getEntities(conf);
				}
			}
		})
		
		.when('/users', {
			templateUrl : 'resources/app/components/users/usersView.html',
			controller : 'users',
			controllerAs : 'controller',
			resolve : {
				users : function(usersService) {
					return usersService.getUsers();
				}
			}
		})
		
		.when('/userProfile', {
			templateUrl: 'resources/app/components/usersProfile/userProfileView.html',
			controller : 'usersProfile',
			controllerAs: 'controller',
			resolve : {
				userProfileType : function(usersService) {
					return usersService.getUserProfileType();
				}
			}
		})
		
		.otherwise('/');

		$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

	}
})();