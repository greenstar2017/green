// JavaScript Document
jQuery(document).ready(function(){
	//$(".boxo").find(".thumb-image li a.current").click();
	click_thumb("current");	
})
jQuery(document).on("click",".boxo .prev",function(){
	//$(this).closest(".boxo").find(".thumb-image li a.current").parent().prev().children("a").click();
	click_thumb("prev");
})
jQuery(document).on("click",".boxo .next",function(){
	//$(this).closest(".boxo").find(".thumb-image li a.current").parent().next().children("a").click();
	click_thumb("next");
})
jQuery(document).on("click",".thumb-image li a",function(){
	$(".boxo .thumb-image li a").removeClass("current");
	$(this).addClass("current");
	click_thumb("current");													 
})
function click_thumb(action){
	var action;
	if(action=="prev"){
		_this=$(".boxo").find(".thumb-image li a.current").parent().prev().children("a");	
	}else if(action=="next"){
		_this=$(".boxo").find(".thumb-image li a.current").parent().next().children("a");
	}else{
		_this=$(".boxo").find(".thumb-image li a.current");
	}
	$(".boxo .loading").css({"z-index":"6"});
	if(_this.length>0){
		$(".thumb-image li a").removeClass("current");
		_this.addClass("current");
	}
	var url=_this.attr("data-url");
	var low=_this.attr("data-low");
	var num=_this.attr("data-num");
	var urla=_this.parent().next().children("a").attr("data-url");
	var lowa=_this.parent().next().children("a").attr("data-low");
	//var numa=_this.parent().next().children("a").attr("data-num");
	var urlb=_this.parent().prev().children("a").attr("data-url");
	var lowb=_this.parent().prev().children("a").attr("data-low");
	//var numb=_this.parent().prev().children("a").attr("data-num");
	if(action=="prev"){
		if($(".boxo .full-image-prev").length>0){
			$(".boxo .full-image-next").remove();
			$(".boxo .full-image-prev").next().addClass("full-image-next full-image-preload");
			$(".boxo .full-image-prev").next().hide();
			$(".boxo .full-image-prev").removeClass("full-image-prev full-image-preload");
			if(urlb!=null){
				$(".boxo .next").after("<div class='full-image full-image-preload full-image-prev thide'><img src='"+urlb+"' lowsrc='"+lowb+"' /></div>");
			}
		}else{
		
		}
	}else if(action=="next"){
		if($(".boxo .full-image-next").length>0){
			$(".boxo .full-image-prev").remove();
			$(".boxo .full-image-next").prev().addClass("full-image-prev full-image-preload");
			$(".boxo .full-image-next").prev().hide();
			$(".boxo .full-image-next").removeClass("full-image-next full-image-preload");
			if(urla!=null){
				$(".boxo .thumb-image").before("<div class='full-image full-image-preload full-image-next thide'><img src='"+urla+"' lowsrc='"+lowa+"' /></div>");
			}
		}else{
		
		}
	}else{
		$(".boxo .full-image").remove();
		$(".boxo .thumb-image").before("<div class='full-image thide'><img src='"+url+"' lowsrc='"+low+"' /></div>");
		if(urla!=null){
			$(".boxo .thumb-image").before("<div class='full-image full-image-preload full-image-next thide'><img src='"+urla+"' lowsrc='"+lowa+"' /></div>");
		}
		if(urlb!=null){
			$(".boxo .next").after("<div class='full-image full-image-preload full-image-prev thide'><img src='"+urlb+"' lowsrc='"+lowb+"' /></div>");
		}
		
	}
	var w_width=$(window).width();
	var w_height=$(window).height()-24;
	var b_width=$(".boxo .thumb-image ul li a.current").attr("data-width");
	var b_height=$(".boxo .thumb-image ul li a.current").attr("data-height");
	var myratio=b_width/b_height;
	//alert(parseInt(myratio));
	if(w_width/w_height<myratio){
		$(".boxo .full-image img").css({"height":w_height+"px"});
		$(".boxo .full-image img").css({"width":w_height*myratio+"px"});
	}else{
		$(".boxo .full-image img").css({"width":w_width+"px"});
		$(".boxo .full-image img").css({"height":(w_width/myratio)+"px"});
	}
	$(".boxo .ad-info span").html(num);
	//$(".boxo .full-image img").attr("src",url);
	$(".boxo .full-image:not(.full-image-preload)").fadeIn(500,function(){
		$(".boxo .loading").css({"z-index":"2"});
	});
	
}
/*jQuery(document).on("click",".thumb-image li a",function(){
	$(".boxo .loading").css({"z-index":"6"});
	$(".thumb-image li a").removeClass("current");
	$(this).addClass("current");
	$(".boxo .full-image:not(.full-image-a)").remove();
	var url=$(this).attr("data-url");
	var low=$(this).attr("data-low");
	var num=$(this).attr("data-num");
	var urla=$(this).parent().next().children("a").attr("data-url");
	var lowa=$(this).parent().next().children("a").attr("data-low");
	var numa=$(this).parent().next().children("a").attr("data-num");
	if($(".boxo .full-image-a").length>0){
		$(".boxo .full-image-a").removeClass("full-image-a");
		$(".boxo .thumb-image").before("<div class='full-image full-image-a thide'><img src='"+urla+"' lowsrc='"+lowa+"' /></div>");
	}else{
		$(".boxo .thumb-image").before("<div class='full-image thide'><img src='"+url+"' lowsrc='"+low+"' /></div><div class='full-image full-image-a thide'><img src='"+urla+"' lowsrc='"+lowa+"' /></div>");
	}
	var w_width=$(window).width();
	var w_height=$(window).height()-24;
	var b_width=$(".boxo .thumb-image ul li a.current").attr("data-width");
	var b_height=$(".boxo .thumb-image ul li a.current").attr("data-height");
	var myratio=b_width/b_height;
	//alert(parseInt(myratio));
	if(w_width/w_height<myratio){
		$(".boxo .full-image img").css({"height":w_height+"px"});
		$(".boxo .full-image img").css({"width":w_height*myratio+"px"});
	}else{
		$(".boxo .full-image img").css({"width":w_width+"px"});
		$(".boxo .full-image img").css({"height":(w_width/myratio)+"px"});
	}
	$(".boxo .ad-info span").html(num);
	//$(".boxo .full-image img").attr("src",url);
	$(".boxo .full-image:not(.full-image-a)").fadeIn(500,function(){
		$(".boxo .loading").css({"z-index":"2"});
	});
	/*$(".boxo .full-image:not(.full-image-a) img").load(function(){
		$(".boxo .loading").css({"z-index":"2"});
	})*/
/*})*/