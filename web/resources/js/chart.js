google.charts.load("current", {packages:['corechart']});
google.charts.setOnLoadCallback(drawChart);

var drawChart = function(infos) {
	   console.log(infos);
	   var resolution = {
	          width : window.innerWidth || document.body.clientWidth,
	          height : window.innerWidth || document.body.clientHeight
	        };
	   
	  var data = google.visualization.arrayToDataTable([
	    ["서울특별시", "범죄 발생 수", { role: "style" } ],
	    [""+infos[0].stats.district+"", infos[0].stats.occurCnt, "DA6A75"],
	    [""+infos[1].stats.district+"", infos[1].stats.occurCnt, "983834"],
	    [""+infos[2].stats.district+"",infos[2].stats.occurCnt, "F0A38D"],
	    [""+infos[3].stats.district+"", infos[3].stats.occurCnt, "color: #E9E39A"],
	    [""+infos[4].stats.district+"", infos[4].stats.occurCnt, "color: #E9E39A"],
	    [""+infos[5].stats.district+"", infos[5].stats.occurCnt, "color: #E9E39A"],
	    [""+infos[6].stats.district+"", infos[6].stats.occurCnt, "color: #E9E39A"],
	    [""+infos[7].stats.district+"", infos[7].stats.occurCnt, "color: #E9E39A"],
	    [""+infos[8].stats.district+"", infos[8].stats.occurCnt, "color: #E9E39A"],
	    [""+infos[9].stats.district+"", infos[9].stats.occurCnt, "color: #E9E39A"]
	  ]);
	//카테고리, 지역 이름, 발생 년도, 발생수, 검거수, 인구
	  var view = new google.visualization.DataView(data);
	  view.setColumns([0, 1,
	                   { calc: "stringify",
	                     sourceColumn: 1,
	                     type: "string",
	                     role: "annotation" },
	                   2]);

	  var options = {
	    title: "서울시 전체 범죄율 TOP10",
	    width: '30%',
	    height: '30%',
	    bar: {groupWidth: "75%"},
	    legend: { position: "none" },
	  };
	  var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
	  chart.draw(view, options);
	};
var redrawChart = function(){
	chart.draw(view, options);
};
