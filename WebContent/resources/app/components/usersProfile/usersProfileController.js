(function() {
    angular.module("main").controller('usersProfile', UsersProfile);

    function UsersProfile(userProfileType, usersService, $location) {
        var controller = this;

        controller.userProfileType = userProfileType;

        controller.getSelectedUser = usersService.getSelectedUser;

        function UserProfile(type) {
            this.type = type;
            this.id = (type == 'ADMIN' ? 1 : 2);
        }

        controller.updateUser = function(user) {
            var obj = [];

            if (user != undefined) {

                user.userProfiles.forEach(function(value) {
                    obj.push(new UserProfile(value.type));
                });

                user.userProfiles = obj;
            }

            if (usersService.getIsEdit()) {
                usersService.updateUser(user).then(function() {
                    $location.path("/users");
                });
            } else {
                usersService.saveUser(user).then(function() {
                    $location.path("/users");
                });
            }

        }
    }
})();