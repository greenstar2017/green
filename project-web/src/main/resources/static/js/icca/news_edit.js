app.controller('ICCA_NewsEditCtrl', ['$scope', '$http', '$stateParams', '$state', '$filter', 'toaster', 
                                     function($scope, $http, $stateParams, $state, $filter, toaster) {
	
	$scope.lang = 'ch';
	
	CommonRequest.AngularBaseCtrl.call( this, $scope,$http,toaster);
	
	$scope.chgLang = function(lang) {
		if(undefined == $scope.data.publish || '' == $scope.data.publish) {
			$scope.toaster_warn('请选择发布日期');
			return false;
		}
		$scope.lang = lang;
		$scope.data.desc = encodeURIComponent($("#desc").html()||'');
		setTimeout(function(){
			$("#descEn").html(decodeURIComponent($scope.data.descEn||''));
		}, 500);
		$('html,body').animate({scrollTop: '0px'}, 800);
	}
	
	$(document).ready(function(){
		setTimeout(function(){
			angular.element(".linkedMenu").bind('click', function (event) {
				event.stopPropagation();
			});
		}, 1000);
	});
	
	
	$scope.data = {};
	$scope.data.id = $stateParams.id||'';
	
	if(undefined != $scope.data.id && $scope.data.id != '') {
		
		$scope.postRequest({url: "/console/news/newsInfo.do", data: $.param($scope.data)}, function(resp){
			$scope.data = resp.data;
			delete $scope.data.createTime;
			$("#desc").html(decodeURIComponent($scope.data.desc||''));
		});
	}
	
	$scope.cancel = function(){
		$state.go('app.news');
	}
	
	$scope.save = function(){
		var _scope = angular.element($("#newsDivId")).scope();
		_scope.data.descEn = encodeURIComponent($("#descEn").html()||'');
		if(undefined == _scope.data.publish || '' == _scope.data.publish) {
			$scope.toaster_error('请选择发布日期');
			return false;
		}
		_scope.data.publish = new Date(_scope.data.publish);
		jQuery.ajaxFileUpload({
			//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
			url:"/console/news/saveNews.do",
			secureuri:false,                       //是否启用安全提交,默认为false
			fileElementId:"fileData",           //文件选择框的id属性
			dataType:'JSON',                       //服务器返回的格式,可以是json或xml等
			data:_scope.data,
			fileSize:5120000,
			allowType:'jpg,jpeg,png,JPG,JPEG,PNG',
			success:function(data, status){        //服务器响应成功时的处理函数
				try{
					var reg = /<pre.+?>(.+)<\/pre>/g;  
					var result = data.match(reg);  
					data = RegExp.$1;
					data = JSON.parse(data);
					$scope.toaster_success();
					if(data.state == 0) {
						$state.go('app.news');
					}
				}catch(e){
					$scope.toaster_error();
				}
			},
			error:function(data, status, e){ //服务器响应失败时的处理函数
				$scope.toaster_error();
			},
			complete:function() {
			}
		});
		
	}
}]);
