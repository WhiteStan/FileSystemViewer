(function() {
	angular.module("main").controller('auth', auth);

	function auth(authService) {

		var controller = this;
		
		authService.authenticate();
		
		controller.logout = authService.logout;
		
		controller.checkRole = authService.checkRole;
		
		controller.getRoles = authService.getRoles;
	}
})();