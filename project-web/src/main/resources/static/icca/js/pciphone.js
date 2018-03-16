// JavaScript Document

//动态追加css和js
function loadjscssfile(filename, filetype) {
 
    if (filetype == "js") {
        var fileref = document.createElement('script');
        fileref.setAttribute("type", "text/javascript");
        fileref.setAttribute("src", filename);
    } else if (filetype == "css") {
 
        var fileref = document.createElement('link');
        fileref.setAttribute("rel", "stylesheet");
        fileref.setAttribute("type", "text/css");
        fileref.setAttribute("href", filename);
    }
    if (typeof fileref != "undefined") {
        document.getElementsByTagName("head")[0].appendChild(fileref);
    }
 
}

//loadjscssfile('http://www.sjcx.org/css/base.css', 'css');
//loadjscssfile('http://www.sjcx.org/js/html5.js', 'js');

//判断浏览器选择css

if(/AppleWebKit.*Mobile/i.test(navigator.userAgent) || (/MIDP|SymbianOS|NOKIA|SAMSUNG|LG|NEC|TCL|Alcatel|BIRD|DBTEL|Dopod|PHILIPS|HAIER|LENOVO|MOT-|Nokia|SonyEricsson|SIE-|Amoi|ZTE/.test(navigator.userAgent))){ 

if(window.location.href.indexOf("?mobile")<0){ 

try{ 

if(/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)){ 

// 判断访问环境是 Android|webOS|iPhone|iPod|BlackBerry 则加载以下样式 

setActiveStyleSheet("style_mobile_a.css"); 

} 

else if(/iPad/i.test(navigator.userAgent)){ 

// 判断访问环境是 iPad 则加载以下样式 

setActiveStyleSheet("style_mobile_iPad.css"); 

} 

else{ 

// 判断访问环境是 其他移动设备 则加载以下样式 

setActiveStyleSheet("style_mobile_other.css"); 

} 

} 

catch(e){} 

} 

} 

else{ 

// 如果以上都不是，则加载以下样式 

loadjscssfile('/icca/css/pc.css', 'css');

} 


 



// 判断完毕后加载样式 

//function setActiveStyleSheet(filename){document.write("＜link href="+filename+" rel=stylesheet＞");} 



//加载页面


