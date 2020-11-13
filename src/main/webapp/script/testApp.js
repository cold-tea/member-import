angular.module('bqTestModule', [])
    .controller('FetchController', ['$scope', '$http',
        function ($scope, $http) {
	    	fetchData();
            function fetchData() {
                $http({method: 'GET', url: 'http://localhost:8080/test/api/load-data'}).then(function (response) {
                	$scope.memberData = response.data;
                    $scope.memberArray = Array.prototype.concat.apply([], Object.keys($scope.memberData).map((key) => $scope.memberData[key]));
                }, function (reason) {
                    console.log('error ' + reason)
                });
            }

        }])