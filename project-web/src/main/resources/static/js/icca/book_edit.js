app.controller('ICCA_BookEditCtrl', ['$scope', '$http', '$stateParams', '$state', '$filter', 'toaster', 
                                     function($scope, $http, $stateParams, $state, $filter, toaster) {
	
	$scope.lang = 'ch';
	
	CommonRequest.AngularBaseCtrl.call( this, $scope,$http,toaster);
	
	$scope.chgLang = function(lang) {
		$scope.lang = lang;
		$scope.data.desc = encodeURIComponent($("#desc").html()||'');
		setTimeout(function(){
			$("#descEn").html(decodeURIComponent($scope.data.descEn||''));
		}, 500);
		$scope.bingLinkedMenuEvent();
		$('html,body').animate({scrollTop: '0px'}, 800);
	}
	
	$scope.bingLinkedMenuEvent = function(){
		setTimeout(function(){
			angular.element(".linkedMenu").bind('click', function (event) {
				event.stopPropagation();
			});
		}, 1000);
	}
	$(document).ready(function(){
		$scope.bingLinkedMenuEvent();
	});
	
	
	
	$scope.data = {};
	$scope.data.id = $stateParams.id||'';
	
	if(undefined != $scope.data.id && $scope.data.id != '') {
		$scope.postRequest({url: "/console/book/bookInfo.do", data: $.param($scope.data)}, function(resp){
			$scope.data = resp.data;
			delete $scope.data.createTime;
			$("#desc").html(decodeURIComponent($scope.data.desc||''));
		});
	}else {
		$scope.data.pictureList = [];
	}
	
	$scope.cancel = function(){
		$state.go('app.book');
	}
	
	$scope.save = function(){
		var pictureList = $scope.data.pictureList;
		if(pictureList && pictureList.length > 0) {
			$(pictureList).each(function(i,d){
				delete d.createTime;
				for(key in d) {
					$scope.data['pictureList['+i+'].'+key] = d[key];
				}
			});
			delete $scope.data.pictureList;
		}
		$scope.data.descEn = encodeURIComponent($("#descEn").html()||'');
		
		$scope.postRequest({url: "/console/book/saveBook.do", data: $.param($scope.data)}, function(resp){
			if(resp.state == 0) {
				$scope.toaster_success();
				setTimeout(function(){
					$state.go('app.book');
				}, 500);
			}
		}, function(resp){
			$scope.toaster_error();
			$scope.data.pictureList = pictureList;
		})
	}
	
	window.updateFile = function(fileId, obj, type) {
		$("input[id*=myfiles").attr("name","myfiles_temp").attr("id", "myfiles_temp");
		$(obj).attr("name","myfiles").attr("id", "myfiles");
		
		_scope = angular.element($("#bookDivId")).scope();
		jQuery.ajaxFileUpload({
			//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
			url:"/fileupload/imageUpload.do",
			secureuri:false,                       //是否启用安全提交,默认为false
			fileElementId:fileId,           //文件选择框的id属性
			dataType:'json',                       //服务器返回的格式,可以是json或xml等
			fileSize:5120000,
			allowType:'jpg,jpeg,png,JPG,JPEG,PNG',
			success:function(data, status){        //服务器响应成功时的处理函数
				try{
					_scope.$apply(function(){
						if(type == 0) {
							_scope.data.videoUrl = data.fileList[0].showFileUrl;
						}else if(type == 1) {
							_scope.data.showLogo = data.fileList[0].showFileUrl;
							_scope.data.logo = data.fileList[0].fileName;
						}else {
							$(data.fileList).each(function(i,dt){
								_scope.data.pictureList.push({picture:dt.fileName, orderField:dt.orderField, showPicture:dt.showFileUrl});
							});
						}
					});
				}catch(e){
					alert("保存失败");
				}
			},
			error:function(data, status, e){ //服务器响应失败时的处理函数
				alert("保存失败");
			},
			complete:function() {
				$("input[id*=myfiles").each(function(i,obj){
					$(obj).attr("name",$(obj).attr("temp")).attr("id", $(obj).attr("temp"));
				});
			}
		});
	}
}]);
