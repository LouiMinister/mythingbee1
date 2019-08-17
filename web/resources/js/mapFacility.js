var imageSize = new kakao.maps.Size(30, 40.5), // 마커이미지의 크기입니다
imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
  
var policeImageSrc = 'image/map/police_color.png'; // 마커이미지의 주소입니다    
var cctvImageSrc = 'image/map/cctv_color.png'; // 마커이미지의 주소입니다    
var shopImageSrc = 'image/map/shop_color.png'; // 마커이미지의 주소입니다    
var bellImageSrc = 'image/map/bell_color.png'; // 마커이미지의 주소입니다    
var security_lampImageSrc = 'image/map/security_lamp_color.png'; // 마커이미지의 주소입니다    
var guardImageSrc = 'image/map/guard_color.png'; // 마커이미지의 주소입니다    
  

//마커를 표시할 위치와 title 객체 배열입니다 
var positions = [
    {
    	content : '<div>경찰서!<div>',
        latlng: new kakao.maps.LatLng(33.450705, 126.570677)
    },
    {
    	content : '<div>경찰서!<div>',
        latlng: new kakao.maps.LatLng(33.450936, 126.569477)
    },
    {
    	content : '<div>경찰서!<div>',
        latlng: new kakao.maps.LatLng(33.450879, 126.569940)
    },
    {
    	content : '<div>경찰서!<div>',
        latlng: new kakao.maps.LatLng(33.451393, 126.570738)
    }
]; 

//마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
var markerImage = new kakao.maps.MarkerImage(policeImageSrc, imageSize, imageOption);
//markerPosition = new kakao.maps.LatLng(37.498004414546934, 127.02770621963765); // 마커가 표시될 위치입니다

var iwContent = '<div style="padding:5px;"> 경찰서! <br>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

var markers = [];

//배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
function setMarkers(map) {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }            
}

//"마커 보이기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에 표시하는 함수입니다
function showMarkers() {
 setMarkers(map);
}

//"마커 감추기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에서 삭제하는 함수입니다
function hideMarkers() {
 setMarkers(null);    
}

function makeOverListener(map, marker, infowindow) {
    return function() {
        infowindow.open(map, marker);
    };
}

// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
function makeOutListener(infowindow) {
    return function() {
        infowindow.close();
    };
}

function createPolice(){
	for(var i=0; i<positions.length; i++){
		//마커를 생성합니다
		var marker = new kakao.maps.Marker({
			map:map,
			position: positions[i].latlng,
			image: markerImage // 마커이미지 설정 
		});
		
		//인포윈도우를 생성합니다
		var infowindow = new kakao.maps.InfoWindow({
			content : iwContent 
		});
		
		markers.push(marker);
		// 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
	    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
	    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
	    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
	    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
	}
}


//지도에 마커를 표시하는 함수입니다
function displayMarker(place) {
    
    // 마커를 생성하고 지도에 표시합니다
    var marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x) 
    });

    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function() {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
        infowindow.open(map, marker);
    });
}