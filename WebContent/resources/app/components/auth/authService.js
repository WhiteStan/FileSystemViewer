(function() {
    angular.module("main").factory("authService", authService);

    function authService($http, $rootScope, $window) {

        var roles;

        function authenticate(credentials) {

            $http.get('userRoles').then(function(response) {

                $rootScope.authenticated = true;
                roles = response.data;

            }, function() {
                $rootScope.authenticated = false;
            });

        }

        function logout() {
            $http.post('logout', {}).then(function() {

                $rootScope.authenticated = false;
                $window.location.href = 'login'

            });
        }

        function checkRole(role) {

            var result = false;

            if (roles != undefined) {

                roles.forEach(function(value) {

                    if (role == value.authority)
                        result = true;

                })

            }

            return result;
        }

        function getRoles() {

            return roles;

        }

        return {
            authenticate: authenticate,
            logout: logout,
            checkRole: checkRole,
            getRoles: getRoles
        }
    }
})();