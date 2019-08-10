
var layer_toggle = function(obj){
        if (obj.style.display=='none') obj.style.display = 'block';
        else if (obj.style.display=='block') obj.style.display = 'none';
};
var w3_open = function() {
	let el=$('#mySidebar');
  	document.getElementById("mySidebar").style.display = "block";
	el.css('margin-left','85.4px');
};
var w3_close = function() {
	let el=$('#mySidebar');
	el.css('margin-left','0px');
	  document.getElementById("mySidebar").style.display = "none";
	};