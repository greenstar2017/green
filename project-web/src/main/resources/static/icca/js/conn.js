// JavaScript Document

$(document).ready(function(){
  

		
	$(".ccacontlist ul li h4 span").click(function(){
		$(this).parent().parent().parent().hide();
        $('.newtit').show();
    });  
	$(".newtit").click(function(){
		$(this).hide();
		$(".ccacontlist ul li").show();
		})
		
		
	$('.ccanav ul li').click(function(){
		$('.ccanav ul li ul').hide();
		$(this).find('ul').show();
		$('.ccanav ul li a').removeClass('active');
		$(this).find('a').addClass('active');
		$('.ccanav ul li ul li a').removeClass('active');
		})
		
	
		


	//返回顶部
	$(window).scroll(function(){
		var scrolltop=$(this).scrollTop();		
		if(scrolltop>=200){		
			$("#elevator_item").show();
		}else{
			$("#elevator_item").hide();
		}
	});		
	$("#elevator").click(function(){
		$("html,body").animate({scrollTop: 0}, 500);	
	});	
	  

	

		 
			 
		
	
	
	
	
	
});