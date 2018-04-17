describe("main", function() {
	var controller, users, usersService;

	// beforeEach(angular.mock.module('ui.router'));
	beforeEach(angular.mock.module('main'));

	beforeEach(inject(function($controller, _usersService_) {
		controller = $controller;

		usersService = _usersService_;
		users = controller('users', {
			users : {
				usersService : usersService
			}
		});
	}));

	describe('usersController', function() {

		it('should be defined', function() {
			expect(users).toBeDefined();
		});

		it('usersService should be defined', function() {
			expect(usersService).toBeDefined();
		})
	});
});