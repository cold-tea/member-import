angular.module('bqTestModule', [])
    .controller('FetchController', ['$scope', '$http',
        function ($scope, $http) {
	    	$scope.memberData = [];
	    	fetchData();
            function fetchData() {
                $http({method: 'GET', url: 'http://localhost:8989/test/api/load-data'}).then(function (response) {
                	console.log(response.data);
                	$scope.memberData = response.data;
                    $scope.memberArray = Array.prototype.concat.apply([], Object.keys($scope.memberData).map((key) => $scope.memberData[key]));
                    console.log($scope.memberArray);
                }, function (reason) {
                    console.log('error ' + reason)
                });
            }

        }])