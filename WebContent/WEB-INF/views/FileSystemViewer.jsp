<!doctype html>
<html>
<head>
<title>File System Viewer</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body ng-app="main" ng-cloak class="ng-cloak">
	<div ng-controller="auth as auth" class="container">
		<ul class="nav nav-pills" role="tablist">
			<li><a href="#/">home</a></li>
			<li ng-if="auth.checkRole('ROLE_ADMIN')"><a href="#/users">users</a></li>
			<li ng-if="auth.checkRole('ROLE_ANONYMOUS')"><a href=""
				ng-click="auth.logout()">login</a></li>
			<li ng-if="!auth.checkRole('ROLE_ANONYMOUS')"><a href=""
				ng-click="auth.logout()">logout</a></li>
		</ul>
	</div>
	<div ng-view class="container"></div>
	<div class="modal fade" id="action-response" role="dialog">
		<div class="modal-dialog">

			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Status code - {{status_code}}</h4>
				</div>
				<div class="modal-body">
					<p>{{error_message}}</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
	<script
		src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.js"></script>
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script
		src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	<script src="resources/app/app.module.js"></script>
	<script src="resources/app/app.routes.js"></script>
	<script src="resources/app/components/auth/authController.js"></script>
	<script src="resources/app/components/auth/authService.js"></script>
	<script src="resources/app/components/home/homeController.js"></script>
	<script src="resources/app/components/home/homeService.js"></script>
	<script src="resources/app/components/users/usersController.js"></script>
	<script src="resources/app/components/users/usersService.js"></script>
	<script
		src="resources/app/components/usersProfile/usersProfileController.js"></script>
</body>
</html>