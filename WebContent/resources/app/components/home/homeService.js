(function() {
    angular.module("main").factory("fileSystemService", fileSystemService);

    function fileSystemService($http, $rootScope) {

        function getEntities(conf) {

            return $http.get("filesystem", conf).then(function(response) {
                return response.data;
            })

        }

        function openFolder(conf) {

            return $http.get("filesystem", conf).then(

                function(response) {
                    return response.data

                },

                function(response) {
                    $rootScope.status_code = response.status;
                    $rootScope.error_message = response.statusText;
                    $('#action-response').modal();
                })
        }

        function openPreviousFolder(conf) {

            return $http.get("filesystem", conf).then(

                function(response) {
                    return response.data;
                },

                function(response) {
                    $rootScope.status_code = response.status;
                    $rootScope.error_message = response.statusText;
                    $('#action-response').modal();
                })

        }

        function createFolder(data) {

            return $http.post(
                "filesystem", data).then(

                function(response) {
                    return response;
                },

                function(response) {
                    $rootScope.status_code = response.status;
                    $rootScope.error_message = response.statusText;
                    $('#action-response').modal();
                })
        }

        function createFile(data) {

            return $http.post(
                "file", data).then(

                function(response) {
                    return response;
                },

                function(response) {
                    $rootScope.status_code = response.status;
                    $rootScope.error_message = response.statusText;
                    $('#action-response').modal();
                })
        }

        function deleteEntity(conf) {

            return $http.delete(
                "filesystem", conf).then(

                function(response) {
                    return response;
                },

                function(response) {
                    $rootScope.status_code = response.status;
                    $rootScope.error_message = response.statusText;
                    $('#action-response').modal();
                })
        }


        return {
            getEntities: getEntities,
            openFolder: openFolder,
            openPreviousFolder: openPreviousFolder,
            createFolder: createFolder,
            createFile: createFile,
            deleteEntity: deleteEntity,
        };
    }
})();