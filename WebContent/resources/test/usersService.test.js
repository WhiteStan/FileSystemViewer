describe(
		'home controller test',
		function() {
			var usersService, $q, $httpBackend;

			beforeEach(angular.mock.module('main'));

			beforeEach(inject(function(_usersService_, _$q_, _$httpBackend_) {
				usersService = _usersService_;
				$q = _$q_;
				$httpBackend = _$httpBackend_;
			}));

			it('should exist', function() {
				expect(usersService).toBeDefined();
			});

			describe(
					'.getUsers()',
					function() {

						var result;

						var RESPONSE_SUCCESS = [ {
							"id" : 1,
							"ssoId" : "admin",
							"password" : "$2a$04$jh6MImnmA6t6YogPRdVCZ.TcCjJCV1JzpbJwJ2WPnkJuTem387tzO",
							"username" : "admin",
							"userProfiles" : [ {
								"id" : 1,
								"type" : "ADMIN"
							} ]
						} ];

						beforeEach(function() {
							result = {};
						});
						
						afterEach(function() {
							$httpBackend.verifyNoOutstandingExpectation();
							$httpBackend.verifyNoOutstandingRequest();
						});

						it('should exist', function() {
							expect(usersService.getUsers).toBeDefined();
						});

						it('should call getUsers once', function() {
							var getUsers = sinon.spy(usersService, 'getUsers');
							usersService.getUsers();
							getUsers.restore();
							sinon.assert.calledOnce(getUsers);

						});
						it(
								'should return users',
								function() {
									var getUsersAPI = "get_users";
									var getUsers = sinon.spy(usersService,
											'getUsers');

									$httpBackend.whenGET(getUsersAPI).respond(
											200, $q.when(RESPONSE_SUCCESS));

									expect(usersService.getUsers).not
											.toHaveBeenCalled();
									expect(result).toEqual({});

									usersService.getUsers().then(
											function(response) {
												result = response;
											});

									$httpBackend.flush();

									sinon.assert.calledOnce(getUsers);
									expect(result[0].id).toEqual(1);
									expect(result[0].ssoId).toEqual('admin');
									expect(result[0].password)
											.toEqual(
													'$2a$04$jh6MImnmA6t6YogPRdVCZ.TcCjJCV1JzpbJwJ2WPnkJuTem387tzO');
									expect(result[0].username).toEqual('admin');
									expect(result[0].userProfiles[0].id)
											.toEqual(1);
									expect(result[0].userProfiles[0].type)
											.toEqual('ADMIN');
								});
					});
			describe('getUserProfileType', function() {

				var result;

				var RESPONSE_SUCCESS = [ "USER", "ADMIN" ];

				beforeEach(function() {
					result = {};
				});

				it('should exist', function() {
					expect(usersService.getUserProfileType).toBeDefined();
				});
				it('should return users', function() {
					var getUserProfileTypeAPI = "get_userProfile_types";
					var getUserProfileType = sinon.spy(usersService,
							'getUserProfileType');

					$httpBackend.whenGET(getUserProfileTypeAPI).respond(200,
							$q.when(RESPONSE_SUCCESS));

					expect(usersService.getUserProfileType).not
							.toHaveBeenCalled();
					expect(result).toEqual({});

					usersService.getUserProfileType().then(function(response) {
						result = response;
					});

					$httpBackend.flush();

					sinon.assert.calledOnce(getUserProfileType);
					expect(result[0]).toEqual('USER');
					expect(result[1]).toEqual('ADMIN');
				});
			})

		});