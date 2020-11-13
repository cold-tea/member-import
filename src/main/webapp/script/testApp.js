angular.module('bqTestModule', [])
    .controller('FetchController', ['$scope', '$http',
        function ($scope, $http) {
	    	$scope.memberData = [];
	    	fetchData();
            function fetchData() {
                $http({method: 'GET', url: 'http://localhost:8989/test/api/load-data'}).then(function (response) {
                	console.log(response.data);
                	$scope.memberData = response.data;
            
                }, function (reason) {
                    console.log('error ' + reason)
                });
            }

        }])