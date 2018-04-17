(function() {
	angular.module("main").controller('home', home);

	function home(entities, fileSystemService, authService, $scope, $rootScope) {
		var controller = this;
		controller.entities = entities;

		controller.checkRole = authService.checkRole;

		controller.openFolder = function(path, entities) {

			var requestPath;

			if (path != '') {

				requestPath = entities.name == "/" ? entities.name + path
						: entities.name + "/" + path;

			} else {
				requestPath = entities.name;

			}

			var conf = {
				params : {
					path : requestPath
				}
			}

			fileSystemService.openFolder(conf).then(function(response) {
				entities.subEntities = response.subEntities;
				entities.name = response.name;
			});
		}

		controller.openPreviousFolder = function(entities) {

			var requestPath = entities.name.lastIndexOf("/") == 0 ? "/"
					: entities.name
							.substring(0, entities.name.lastIndexOf("/"));

			var conf = {
				params : {
					path : requestPath
				}
			}

			fileSystemService.openPreviousFolder(conf).then(function(response) {
				entities.subEntities = response.subEntities;
				entities.name = response.name;
			})
		}

		controller.createFolder = function(folderName, entities) {

			if (folderName != undefined) {

				var requestPath = entities.name == "/" ? entities.name
						+ folderName : entities.name + "/" + folderName;

				fileSystemService.createFolder(requestPath).then(

				function(response) {
					controller.openFolder('', entities);
				});
			} else {
				$rootScope.status_code = "Creation error";
				$rootScope.error_message = "Invalid name";
				$('#action-response').modal();
			}
		}

		controller.createFile = function(fileName, entities) {

			if (fileName != undefined) {

				var requestPath = entities.name == "/" ? entities.name
						+ fileName : entities.name + "/" + fileName;

				fileSystemService.createFile(requestPath).then(
						function(response) {

							controller.openFolder('', entities);
						});
			} else {

				$rootScope.status_code = "Creation error";
				$rootScope.error_message = "Invalid name";
				$('#action-response').modal();
			}
		}

		controller.deleteEntity = function(entityName, entities) {

			var requestPath = entities.name == "/" ? entities.name + entityName
					: entities.name + "/" + entityName;

			var conf = {
				params : {
					path : requestPath
				}
			}

			fileSystemService.deleteEntity(conf).then(function(response) {

				controller.openFolder('', entities);
			})
		}

		controller.showDropdownMenu = function(event) {

			$(event.target).parent().toggleClass("open");
		}

		controller.hideDropdownMenu = function(event) {

			if (event.which === 13) {

				$('.dropdown').find("input").val('');
				$('.dropdown .dropdown-toggle').parent().removeClass("open")
			}
		}

	}

})();