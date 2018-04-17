(function() {
    angular.module("main").controller('users', users);

    function users(users, usersService, $location, $route) {
        var controller = this;
        controller.users = users;

        controller.getUsers = usersService.getUsers();

        controller.getUserProfileType = usersService.getUserProfileType();

        controller.deleteUser = function(username) {
            var conf = {
                params: {
                    username: username
                }
            }

            usersService.deleteUser(conf).then(function(response) {
                $route.reload();
            });
        }

        controller.openProfile = function(user) {

            $location.path("userProfile");

            var isEdit = true;
            
            
            if (user == undefined) {
            	
                isEdit = false;
                user = {};
            }

            usersService.isEdit(isEdit);

            usersService.selectUser(user);

        }
    }
})();