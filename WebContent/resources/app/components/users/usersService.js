(function() {
    angular.module("main").factory("usersService", usersService);

    function usersService($http, $rootScope, $route) {

        var selectedUser;
        var isEdit;

        function getUsers() {

            return $http.get("users").then(

                function(response) {
                    return response.data;
                },

                function(response) {
                    $rootScope.status_code = response.status;
                    $rootScope.error_message = response.statusText;
                    $('#action-response').modal();
                })
        }


        function getUserProfileType() {

            return $http.get("roles").then(

                function(response) {
                    return response.data;
                },

                function(response) {
                    $rootScope.status_code = response.status;
                    $rootScope.error_message = response.statusText;
                    $('#action-response').modal();
                })
        }


        function selectUser(user) {
            selectedUser = user;
        }


        function isEdit(action) {
            isEdit = action;
        }


        function getIsEdit() {
            return isEdit;
        }


        function getSelectedUser() {
            return selectedUser;
        }


        function deleteUser(conf) {

            return $http.delete("user", conf).then(

                function(response) {
                    return response;
                },

                function(response) {
                    $rootScope.status_code = response.status;
                    $rootScope.error_message = response.statusText;
                    $('#action-response').modal();
                });

        }


        function updateUser(data) {

            return $http.put("user", data).then(

                function(response) {
                    return response;
                },
                function(response) {
                    $rootScope.status_code = response.status;
                    $rootScope.error_message = response.statusText;
                    $('#action-response').modal();
                });
        }


        function saveUser(data) {

            return $http.post("user", data).then(

                function(response) {
                    return response
                },

                function(response) {
                    $rootScope.status_code = response.status;
                    $rootScope.error_message = response.statusText;
                    $('#action-response').modal();
                });
        }

        return {
            getUsers: getUsers,
            deleteUser: deleteUser,
            getUserProfileType: getUserProfileType,
            updateUser: updateUser,
            selectUser: selectUser,
            getSelectedUser: getSelectedUser,
            isEdit: isEdit,
            getIsEdit: getIsEdit,
            saveUser: saveUser
        }
    }
})();