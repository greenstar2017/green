'use strict';

/* Controllers */

angular.module('app')
  .controller('AppCtrl', ['$scope', '$translate', '$localStorage', '$window', '$http',
    function(              $scope,   $translate,   $localStorage,   $window , $http) {
      // add 'ie' classes to html
      var isIE = !!navigator.userAgent.match(/MSIE/i);
      isIE && angular.element($window.document.body).addClass('ie');
      isSmartDevice( $window ) && angular.element($window.document.body).addClass('smart');

      function isSmartDevice( $window )
      {
          // Adapted from http://www.detectmobilebrowsers.com
          var ua = $window['navigator']['userAgent'] || $window['navigator']['vendor'] || $window['opera'];
          // Checks for iOs, Android, Blackberry, Opera Mini, and Windows mobile devices
          return (/iPhone|iPod|iPad|Silk|Android|BlackBerry|Opera Mini|IEMobile/).test(ua);
      }
      
      $scope.login = function(){
  		$http({
  			method: 'POST' ,
  			url: '/userLogin' ,
  			data: $.param($scope.user), // pass in data as strings
  			headers: { 'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8; ajax' }
  		}).success( function (resp) {
  			if(resp.state == 0) {
  				if(resp.data.userAccount.type == 1) {
  					window.location.href = "/console#/app/lenderMng"
  				}else {
  					window.location.href = "/console#/app/loanBillMng"
  				}
  			}else {
  				$scope.errorMsg = resp.message;
  			}
  		}).error(function() {
  		});
  		
  	}

  }]);