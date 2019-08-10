google.charts.load("current", {packages:['corechart']});
//google.charts.setOnLoadCallback(drawChart);
var map;
var markers = [];
var alldata = [];
var prev1year= [];
var prev2year= [];
var polygon;
//$(".w3-sidebar w3-bar-item w3-button").click(function())
//var selection = $(".w3-sidebar w3-bar-item w3-button").text();

var ranking = function(infos){
	//console.log(infos);
	$('<tr class="ranked">'+'<td class="rank">'+infos.rank+'</td>'+
			'<td class="district">'+infos.stats.district+'</td>'+
			'<td class="occurCnt">'+infos.stats.occurCnt+'</td>'+'</tr>',{}).appendTo('#rankContent > tbody');
};
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
  mapOption = { 
      center: new kakao.maps.LatLng(37.578126, 126.9546207), // 지도의 중심좌표
      level: 9 // 지도의 확대 레벨
  };


var map = new kakao.maps.Map(mapContainer, mapOption),
customOverlay2 = new kakao.maps.CustomOverlay({}),
infowindow = new kakao.maps.CustomOverlay({
	   clickable : true
	   //removable : true
	   });
map.setMinLevel(9);
map.setMaxLevel(9);
var setMarkers = function(map){
	for (var i = 0; i < positions.length; i ++) {
	    // 마커 이미지의 이미지 크기 입니다
	    var imageSize = new kakao.maps.Size(50, 35); 
	    //console.log(positions[i].img);
	    var imageSrc = positions[i].img;
	    // 마커 이미지를 생성합니다    
	    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
	    
	    // 마커를 생성합니다
	     markers[i] = new kakao.maps.Marker({
	        map: map, // 마커를 표시할 지도
	        position: positions[i].latlng, // 마커를 표시할 위치
	        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
	        image : markerImage // 마커 이미지 
	    });
	}
	for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }
};
var selection = function(event){
	alldata = null;
	alldata = [];
	$('.ranked').remove();
	var crimeId = event.srcElement.id;
	console.log(crimeId);
	if(crimeId==null) crimeId="ALL";
	$.ajax({
			url:"/api/stats/stats",
				type:'POST',
				data:{crimeId: crimeId},
			success:function(data){
				//alert("성공");
				console.log(data.length);
			for(let x=0;x<data.length;x++){
				alldata=alldata.concat(data[x]);
			}
			//지도에 폴리곤으로 표시할 영역데이터 배열입니다
			prev2year=data[1];
			prev1year=data[2];
			console.log(prev2year);
			console.log(prev1year);
			drawChart(alldata);

			for (let i = 0, len = areas.length; i < len; i++) {
				let prev2;
				let prev1;
				for(j=0;j<25;j++){
					if(prev2year[j].stats.district==areas[i].name){
						prev2=prev2year[j];
					}
				}
				for(j=0;j<25;j++){
					if(prev1year[j].stats.district==areas[i].name){
						prev1=prev1year[j];
					}
				}
				for(j=0;j<25;j++){
					if(areas[i].name==alldata[j].stats.district){
						displayArea(areas[i],alldata[j],prev2,prev1);
					}
				}
				ranking(alldata[i]);
			}

			
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("에러"+textStatus+":",errorThrown);
			//self.close();

		}

	});
};

//다각형을 생상하고 이벤트를 등록하는 함수입니다
var displayArea = function(area,d,d15,d16) {
	//console.log("thisd is d16");
	//console.log(d16);
	if(d.rank<=5){
		 polygon = new kakao.maps.Polygon({
	     map: map, // 다각형을 표시할 지도 객체
	     path: area.path,
	     strokeWeight: 0.7,
	     strokeColor: 'BLACK',
	     strokeOpacity: 0.8,
	     fillColor: '#F34D4D',
	     fillOpacity: 0.7 
	 });
	}else if(d.rank<=10){
		 polygon = new kakao.maps.Polygon({
		     map: map, // 다각형을 표시할 지도 객체
		     path: area.path,
		     strokeWeight: 0.7,
		     strokeColor: 'BLACK',
		     strokeOpacity: 0.8,
		     fillColor: '#F9964D',
		     fillOpacity: 0.7 
		 });
	}else if(d.rank<=15){
		 polygon = new kakao.maps.Polygon({
		     map: map, // 다각형을 표시할 지도 객체
		     path: area.path,
		     strokeWeight: 0.7,
		     strokeColor: 'BLACK',
		     strokeOpacity: 0.8,
		     fillColor: '#FFE07C',
		     fillOpacity: 0.7 
		 });
	}else if(d.rank<=20){
		 polygon = new kakao.maps.Polygon({
		map: map, // 다각형을 표시할 지도 객체
	     path: area.path,
	     strokeWeight: 0.7,
	     strokeColor: 'BLACK',
	     strokeOpacity: 0.8,
	     fillColor: '#B3DE85',
	     fillOpacity: 0.7 
		 });
	}else{
		 polygon = new kakao.maps.Polygon({
		     map: map, // 다각형을 표시할 지도 객체
		     path: area.path,
		     strokeWeight: 0.7,
		     strokeColor: '#004c80',
		     strokeOpacity: 0.8,
		     fillColor: ' #8AAC66',
		     fillOpacity: 0.7 
		 });
    }
    kakao.maps.event.addListener(polygon, 'mouseover', function(mouseEvent) {
			  // polygon.setOptions({fillColor: '#09f'});
			   customOverlay2.setContent('<div class="area">' + area.name + '</div>');
			   customOverlay2.setPosition(mouseEvent.latLng); 
			   customOverlay2.setMap(map);
			});


		// 다각형에 mousemove 이벤트를 등록하고 이벤트가 발생하면 커스텀 오버레이의 위치를 변경합니다 
		kakao.maps.event.addListener(polygon, 'mousemove', function(mouseEvent) {
		   
		   customOverlay2.setPosition(mouseEvent.latLng); 
		});

		// 다각형에 mouseout 이벤트를 등록하고 이벤트가 발생하면 폴리곤의 채움색을 원래색으로 변경합니다
		// 커스텀 오버레이를 지도에서 제거합니다 
		kakao.maps.event.addListener(polygon, 'mouseout', function() {
		  // polygon.setOptions({fillColor: '#fff'});
		   customOverlay2.setMap(null);
		});
		/*  kakao.maps.event.addListener(map, 'idle', function() {
				console.log(map.getLevel());
			}); */

		// 다각형에 click 이벤트를 등록하고 이벤트가 발생하면 다각형의 이름과 면적을 인포윈도우에 표시합니다 
		kakao.maps.event.addListener(polygon, 'click', function(mouseEvent) {
			console.log(d);
			console.log(mouseEvent.latLng.getLat());
			console.log(mouseEvent.latLng.getLng());
			 closeOverlay();
			 var content = '<div class=wrap>' +
				'<div class="info">' +
			'<div class="title">'+
		'<h2 class="area-name">' + area.name +'</h2>'+
		'<div class="close" onclick="closeOverlay()" title="닫기"></div>' +                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
		'<div>' +
		'<div class="body">' +
		'<div class="desc">' +
		'<table class="table-hover">' +
		'<thead>' +
		'<tr>' +
		 '<th>종류</th>' +
		 '<th>2015</th>' +
		 '<th>2016</th>' +
		'</tr>' +
		'</thead>' +
		'<tbody>' +
		'<tr>' +
		 '<td>만명당 범죄율 순위</td>' +
		 '<td>'+d15.rank+'</td>' +
		 '<td>'+d16.rank+'</td>' +
		'</tr>' +
		'<tr>' +
		 '<td>범죄 발생 수</td>' +
		 '<td>'+d15.stats.occurCnt+'</td>' +
		 '<td>'+d16.stats.occurCnt+'</td>' +
		'</tr>' +
		'<tr>' +
		 '<td>검거 수</td>' +
		 '<td>'+d15.stats.arrestCnt+'</td>' +
		 '<td>'+d16.stats.arrestCnt+'</td>' +
		'</tr>' +
		'<tr>' +
		 '<td>범죄율(인구대비)</td>' +
		 '<td>'+d15.rate+'</td>' +
		 '<td>'+d16.rate+'</td>' +
		'</tr>' +
		'</tbody>' +
		'</table>' +
		'</div>'+
		'</div>'+
		'</div>'+
		'</div>';
		   infowindow.setContent(content); 
		   infowindow.setPosition(mouseEvent.latLng); 
		   infowindow.setMap(map);
		   
		});
};

$(document).ready(function(){
	//selection(event);
//	$(".w3-bar-header").click(w3_open){}
	setMarkers(map);
	
});

function closeOverlay() {//오버레이 삭제
	infowindow.setMap(null);

}
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


