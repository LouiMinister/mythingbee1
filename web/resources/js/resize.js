function setResponsive() {
	var vRes = {
		    width : window.innerWidth || document.body.clientWidth,
		    height : window.innerWidth || document.body.clientHeight
		  };
	redrawChart();
}

$(window).on('load', function () {
    setResponsive();
});

$(window).on('resize', function () {
    setResponsive();
});
