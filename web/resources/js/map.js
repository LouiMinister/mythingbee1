var map;
var mapTypeControl = new kakao.maps.MapTypeControl();
var zoomControl = new kakao.maps.ZoomControl();
var cctvFlag = 0;
var bellFlag = 0;
var guardFlag = 0;
var policeFlag = 0;
var shopFlag = 0 ;
var securityLampFlag = 0;

function cctvToggle() {
	var img = document.getElementsByName("cctv");
	var tmp = img[0].src;
	img[0].src = img[1].src;
	img[1].src = tmp;

//   cctvFlag == 0 ? showMarkers("cctv") : hideMarkers("cctv");

	if(cctvFlag == 0){
		cctvFlag = 1;
		searchNewPlaces();
	} else {
		cctvFlag = 0;
		hideMarkers("cctv");
	}
}

function bellToggle() {
	var img = document.getElementsByName("bell");
	var tmp = img[0].src;
	img[0].src = img[1].src;
	img[1].src = tmp;
	if(bellFlag == 0){
		bellFlag = 1;
		searchNewPlaces();
	} else {
		bellFlag = 0;
		hideMarkers("bell");
	}
}

function guardToggle() {
	var img = document.getElementsByName("guard");
	var tmp = img[0].src;
	img[0].src = img[1].src;
	img[1].src = tmp;
//   guardFlag == 0? showMarkers("guard") : hideMarkers("guard");
//   guardFlag= 1- guardFlag;
	if(guardFlag == 0){
		guardFlag = 1;
		searchNewPlaces();
	} else {
		guardFlag = 0;
		hideMarkers("gurad");
	}
}

function policeToggle() {
	var img = document.getElementsByName("police");
	var tmp = img[0].src;
	img[0].src = img[1].src;
	img[1].src = tmp;
//   policeFlag == 0 ? showMarkers("police") : hideMarkers("police");
//   policeFlag = 1-policeFlag;
	if(policeFlag == 0){
		policeFlag = 1;
		searchNewPlaces();
	} else {
		policeFlag = 0;
		hideMarkers("police");
	}
}

function shopToggle() {
	var img = document.getElementsByName("shop");
	var tmp = img[0].src;
	img[0].src = img[1].src;
	img[1].src = tmp;
	if(shopFlag == 0){
		shopFlag = 1;
		searchNewPlaces();
	} else {
		shopFlag = 0;
		hideMarkers("shop");
	}
}

function securityLampToggle() {
	var img = document.getElementsByName("security_lamp");
	var tmp = img[0].src;
	img[0].src = img[1].src;
	img[1].src = tmp;
	if(securityLampFlag == 0){
		securityLampFlag = 1;
		searchNewPlaces();
	} else {
		securityLampFlag = 0;
		hideMarkers("securityLamp");
	}
}



$('#search_menu').click(function(){
	$('#left_bar').addClass("open");
});

$(function () {

	function closeMenu() {
		$('#left_bar').animate({
			'left': '-480px',
			'z-index': '0'
		}, 150, 'linear', function () {
			$("#btn").text("검색");
		});
	}

	function openMenu() {
		$('#left_bar').animate({
			'left': '100px',
			'z-index': '99999'
		}, 150, 'linear', function () {
			$("#search_menu").text("검색");
		});
	}


	$("#search_menu").on("click", function () {
		return (this.tog ^= 1) ? openMenu() : closeMenu();
	});
});

var imageSize = new kakao.maps.Size(30, 40.5), // 마커이미지의 크기입니다
	cctvImageSize = new kakao.maps.Size(40,42.5),
	imageOption = {offset: new kakao.maps.Point(34, 69)}
	cctvImageOption = {offset: new kakao.maps.Point(30,65)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

var policeImageSrc = '/resources/image/map/police_color.png'; // 마커이미지의 주소입니다
var cctvImageSrc = '/resources/image/map/cctv_color.png'; // 마커이미지의 주소입니다
var shopImageSrc = '/resources/image/map/shop_color.png'; // 마커이미지의 주소입니다
var bellImageSrc = '/resources/image/map/bell_color.png'; // 마커이미지의 주소입니다
var securityLampImageSrc = '/resources/image/map/security_lamp_color.png'; // 마커이미지의 주소입니다
var guardImageSrc = '/resources/image/map/guard_color.png'; // 마커이미지의 주소입니다






//마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
var policeMarkerImage = new kakao.maps.MarkerImage(policeImageSrc, imageSize/*, imageOption*/);
var shopMarkerImage = new kakao.maps.MarkerImage(shopImageSrc, imageSize/*, imageOption*/);
var securityLampMarkerImage = new kakao.maps.MarkerImage(securityLampImageSrc, imageSize/*, imageOption*/);
var bellMarkerImage = new kakao.maps.MarkerImage(bellImageSrc, imageSize/*, imageOption*/);
var cctvMarkerImage = new kakao.maps.MarkerImage(cctvImageSrc, cctvImageSize/*, cctvImageOption*/);
var guardMarkerImage = new kakao.maps.MarkerImage(guardImageSrc, imageSize/*, imageOption*/);
//markerPosition = new kakao.maps.LatLng(37.498004414546934, 127.02770621963765); // 마커가 표시될 위치입니다

var policeIwContent = '<div style="padding:5px;"> 경찰서! <br>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
var shopIwContent = '<div style="padding:5px;"> 편의점! <br>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
var securityLampIwContent = '<div style="padding:5px;"> 보안등! <br>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
var bellIwContent = '<div style="padding:5px;"> 비상벨! <br>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
var cctvIwContent = '<div style="padding:5px;"> cctv! <br>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
var guardIwContent = '<div style="padding:5px;"> 지킴이집! <br>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

// 포지션 마커 마커 전부 담는 배열 생성
var policePositions = [];
var policeMarkers = [];
var policeAll = [];

var shopPositions = [];
var shopMarkers = [];
var shopAll = [];

var securityLampPositions = [];
var securityLampMarkers = [];
var securityLampAll = [];

var bellPositions = [ ];
var bellMarkers = [];
var bellAll = [];

var cctvPositions = [];
var cctvMarkers = [];
var cctvAll = [];

var guardPositions = [];
var guardMarkers = [];
var guardAll = [];

var iwContent;
var detailList;

//배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
function setMarkers(markers, map) {
	if(detailList){
		detailList.close();
	}
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(map);
	}
}


//"마커 감추기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에서 삭제하는 함수입니다
function hideMarkers(type) {
	// 얘네들이 하나도 안 켜져있으면 맥스 레벨 조정
	if( (bellFlag | cctvFlag | securityLampFlag | shopFlag) != 1){
		map.setMaxLevel(12);
		// 지킴이집이랑 경찰서도 꺼져 있으면 z index 기본값 0으로 세팅
		if( (guardFlag | policeFlag) == 0){
			zIndex = 1;
		}
	}
	switch(type){
		case "cctv": setMarkers(cctvAll,null); cctvAll= []; cctvMarkers = []; break;
		case "police": setMarkers(policeAll,null); policeAll= []; policeMarkers = [];break;
		case "shop": setMarkers(shopAll,null); shopAll= []; shopMarkers = [];break;
		case "securityLamp":setMarkers(securityLampAll,null); securityLampAll= []; securityLampMarkers = []; break;
		case "guard": setMarkers(guardAll,null); guardvAll= []; guardMarkers = [];break;
		case "bell": setMarkers(bellAll,null); bellAll= []; bellMarkers = [];break;
	}
}

var zIndex = 1;
function makeOverListener(map, marker, infowindow) {
	return function() {
		// infowindow.open(map, marker);
		marker.setZIndex(zIndex++);
	};
}

// 인포윈도우를 닫는 클로저를 만드는 함수입니다
function makeOutListener(infowindow) {
	return function() {
		infowindow.close();
	};
}

function makeClickListener(marker){

	return function() {

		$.ajax('api/map/detail',{
			type: 'GET',
			// contentType:"application/json; charset=UTF-8",
			data: {
				code : marker.getTitle()
			}
		}).then(function(data,status){
			if(status == 'success')
			{
				var adminName = data.adminName;
				var roadAddr = data.roadAddr;
				var landAddr = data.landAddr;
				var adminTel = data.adminTel;

				if(adminTel == null) { adminTel =""; }

				var iwContent = '<div style="width:270px;">'
					+'<div class="card border-dark mb-3" style="max-width: 20rem; margin-bottom:0 z-index:10">'
					+'<div class="card-header">상세 정보</div>'
					+'<div class="card-body">'
					+'<h4 class="card-title"></h4>'
					+'<p class="card-text">관리: '+adminName+'<br>'+adminTel+'<br></p><p style="font-size:13px;">'+roadAddr+'<br>'+landAddr+'</p></div>';
				// 인포윈도우를 생성합니다
				var infowindow = new kakao.maps.InfoWindow({
					content : iwContent
				});
				if(detailList){
					detailList.close();
				}
				detailList = infowindow;
				infowindow.open(map,marker);
			}
		})
	}
}

function searchNewPlaces() {
	// 시설물 하나도 켜진거 없으면 리턴
	if( (cctvFlag | bellFlag | securityLampFlag | shopFlag | policeFlag | guardFlag) == 0){
		return ;
	}
	// 지도 영역 조정하고 가져오기 . 해당 시설물 중 하나라도 켜져 있으면 크기 제한
	if( (cctvFlag | bellFlag | securityLampFlag | shopFlag) == 1 ){
		map.setMaxLevel(2);
	}
	var center = map.getCenter();
	var level = map.getLevel();
	var bounds = map.getBounds();
	// 영역의 남서쪽 좌표를 얻어옵니다
	var swLatLng = bounds.getSouthWest();

	// 영역의 북동쪽 좌표를 얻어옵니다
	var neLatLng = bounds.getNorthEast();

	var json = JSON.stringify(bounds);
	var facility = [ cctvFlag, bellFlag, policeFlag, shopFlag, guardFlag, securityLampFlag];
	var facilName = ["cctv","bell","police","convenience"/*shop*/,"guard","light"];
	var fn = JSON.stringify(facilName);
	var fa = JSON.stringify(facility);
	$.ajax('api/map/search',{
		type: 'GET',
		dataType:"json",
		data: {
			bounds:json,
			facilFlag : fa,
			facilName : fn
		}
	}).then(function(data,status){
		if(status == 'success')
		{

			for (var i = 0; i < data.length; i++){
				var temp = data[i].data;
				// if(temp.length == 1) {continue;}
				var positions=[];
				if(temp == null ) { continue;}
				for(var j=0; j<temp.length; j++){
					var posi = {
						number : temp[j].code,
//                     title : temp[j].title,
						latlng : new kakao.maps.LatLng(temp[j].lat, temp[j].lng)
					};
					positions.push(posi);
				}
				switch(data[i].name){
					case "cctv":
						cctvPositions = [];
						cctvPositions = positions;
						createCctvMarkers();
						setMarkers(cctvMarkers,map);
						break;
					case "bell":
						bellPositions = [];
						bellPositions = positions;
						createBellMarkers();
						setMarkers(bellMarkers,map);
						break;
					case "guard": guardPositions = [];
						guardPositions = positions;
						createGuardMarkers();
						break;
					case "convenience" : shopPositions = [];
						shopPositions = positions;
						createShopMarkers();
						setMarkers(shopMarkers,map);
						break;
					case "light" : cctvPositions = [];
						securityLampPositions = (positions);
						createSecurityLampMarkers();
						setMarkers(securityLampMarkers,map);
						break;
					case "police": policePositions = [];
						policePositions = (positions);
						createPoliceMarkers();
						setMarkers(policeMarkers,map);
						break;
				}
			}
		}
	})
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

$(function () {

	function closeSecurity() {
		$('#security').animate({
			'bottom': '-102px'
		}, 150, 'linear', function () {
		});
	}

	function openSecurity() {
		$('#security').animate({
			'bottom': '0'
		}, 150, 'linear', function () {
		});
	}
	$("#security_menu").on("click", function () {
		return (this.tog ^= 1) ? openSecurity() : closeSecurity();
	});
});

//마커를 담을 배열입니다
var searchMarkers = [];

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places();

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({zIndex:1});
var keyword;
// 키워드 검색을 요청하는 함수입니다
function searchPlaces() {
	if(detailList){
		detailList.close();
	}

	keyword = document.getElementById('keyword').value;
	if (!keyword.replace(/^\s+|\s+$/g, '')) {
		alert('키워드를 입력해주세요!');
		return false;
	}

	// 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
	switch(keyword){
		case "대형마트","마트","수퍼" : ps.categorySearch('MT1', placesSearchCB, {useMapBounds:true}); break;
		case "편의점" : ps.categorySearch('CS2', placesSearchCB, {useMapBounds:true}); break;
		case "어린이집", "유치원": ps.categorySearch('PS3', placesSearchCB, {useMapBounds:true}); break;
		case "학교" : ps.categorySearch('SC4', placesSearchCB, {useMapBounds:true}); break;
		case "학원" : ps.categorySearch('AC5', placesSearchCB, {useMapBounds:true}); break;
		case "주차장" : ps.categorySearch('PK6', placesSearchCB, {useMapBounds:true}); break;
		case "주유소","충전소": ps.categorySearch('OL7', placesSearchCB, {useMapBounds:true}); break;
		case "지하철역","지하철": ps.categorySearch('SW8', placesSearchCB, {useMapBounds:true}); break;
		case "문화시설": ps.categorySearch('CT1', placesSearchCB, {useMapBounds:true}); break;
		case "중개업소": ps.categorySearch('AG2', placesSearchCB, {useMapBounds:true}); break;
		case "공공기관": ps.categorySearch('PO3', placesSearchCB, {useMapBounds:true}); break;
		case "관광명소": ps.categorySearch('AT4', placesSearchCB, {useMapBounds:true}); break;
		case "숙박": ps.categorySearch('AD5', placesSearchCB, {useMapBounds:true}); break;
		case "음식점": ps.categorySearch('FD6', placesSearchCB, {useMapBounds:true}); break;
		case "카페": ps.categorySearch('CE7', placesSearchCB, {useMapBounds:true}); break;
		case "병원": ps.categorySearch('HP8', placesSearchCB, {useMapBounds:true}); break;
		case "약국": ps.categorySearch('PM9', placesSearchCB, {useMapBounds:true}); break;
		case "은행" : ps.categorySearch('BK9', placesSearchCB, {useMapBounds:true}); break;
		default :  ps.keywordSearch(keyword, placesSearchCB);  break;
	}
}

function hideAllFacility() {

	if(cctvFlag==1){ cctvToggle();}
	if(bellFlag==1){bellToggle();}
	if(shopFlag==1){shopToggle();}
	if(policeFlag==1){policeToggle();}
	if(securityLampFlag==1){securityLampToggle();}
	if(guardFlag==1){guardToggle();}
}

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
	hideAllFacility();
	if (status === kakao.maps.services.Status.OK) {

		var searchResult = document.getElementById('address_result');

		// 정상적으로 검색이 완료됐으면
		// 검색 목록과 마커를 표출합니다
		displayPlaces(data);

		// 페이지 번호를 표출합니다
		displayPagination(pagination);

	} else if (status === kakao.maps.services.Status.ZERO_RESULT) {

		alert('검색 결과가 존재하지 않습니다.');


	} else if (status === kakao.maps.services.Status.ERROR) {

		alert('검색 결과 중 오류가 발생했습니다.');


	}
}
var listEl; // 검색 결과 목록
// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {

	listEl = document.getElementById('placesList'),
		menuEl = document.getElementById('menu_wrap'),
		fragment = document.createDocumentFragment(),
		bounds = new kakao.maps.LatLngBounds(),
		listStr = '';

	// 검색 결과 목록에 추가된 항목들을 제거합니다
	removeAllChildNods(listEl);

	// 지도에 표시되고 있는 마커를 제거합니다
	removeMarker();

	for ( var i=0; i<places.length; i++ ) {

		// 마커를 생성하고 지도에 표시합니다
		var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
			marker = addMarker(placePosition, i),
			itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

		// 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
		// LatLngBounds 객체에 좌표를 추가합니다
		bounds.extend(placePosition);

		// 마커와 검색결과 항목에 mouseover 했을때
		// 해당 장소에 인포윈도우에 장소명을 표시합니다
		// mouseout 했을 때는 인포윈도우를 닫습니다
		(function(marker, title) {
			kakao.maps.event.addListener(marker, 'mouseover', function() {
				displayInfowindow(marker, title);
			});

			kakao.maps.event.addListener(marker, 'mouseout', function() {
				infowindow.close();
			});

			/*  itemEl.onmouseover =  function () {
                  displayInfowindow(marker, title);
              };

              itemEl.onmouseout =  function () {
                  infowindow.close();
              };*/
		})(marker, places[i].place_name);

		fragment.appendChild(itemEl);
	}

	// 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
	listEl.appendChild(fragment);
	// menuEl.scrollTop = 0;

	// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
	map.setBounds(bounds);
	// 검색 결과 지도 레벨 서울시로 조정
	// map.setLevel(8);
	// map.setCenter(new kakao.maps.LatLng(37.54273333812522, 126.91993213895803));
}

// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {

	var el = document.createElement('li'),
		itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
			'<div class="info">' +
			'   <h5>' + places.place_name + '</h5>';

	if (places.road_address_name) {
		itemStr += '    <span>' + places.road_address_name + '</span>' +
			'   <span class="jibun gray">' +  places.address_name  + '</span>';
	} else {
		itemStr += '    <span>' +  places.address_name  + '</span>';
	}

	itemStr += '  <span class="tel">' + places.phone  + '</span>' +
		'</div>';

	el.innerHTML = itemStr;
	el.className = 'item';

	return el;
}

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, title) {
	var imageSrc = 'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
		imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
		imgOptions =  {
			spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
			spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
			offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
		},
		markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
		marker = new kakao.maps.Marker({
			position: position, // 마커의 위치
			image: markerImage
		});

	marker.setMap(map); // 지도 위에 마커를 표출합니다
	searchMarkers.push(marker);  // 배열에 생성된 마커를 추가합니다

	return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
	for ( var i = 0; i < searchMarkers.length; i++ ) {
		searchMarkers[i].setMap(null);
	}
	searchMarkers = [];
}
var paginationEl;
// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {
	paginationEl = document.getElementById('pagination')
	var fragment = document.createDocumentFragment(),
		i;

	// 기존에 추가된 페이지번호를 삭제합니다
	while (paginationEl.hasChildNodes()) {
		paginationEl.removeChild (paginationEl.lastChild);
	}

	for (i=1; i<=pagination.last; i++) {
		var el = document.createElement('a');
		el.href = "#";
		el.innerHTML = i;

		if (i===pagination.current) {
			el.className = 'on';
		} else {
			el.onclick = (function(i) {
				return function() {
					pagination.gotoPage(i);
				}
			})(i);
		}

		fragment.appendChild(el);
	}
	paginationEl.appendChild(fragment);
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title) {
	var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

	infowindow.setContent(content);
	infowindow.open(map, marker);
}

// 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {
	while (el.hasChildNodes()) {
		el.removeChild (el.lastChild);
	}
}


//마커이미지의 주소와, 크기, 옵션으로 마커 이미지를 생성하여 리턴하는 함수입니다
function createMarkerImage(src, size, options) {
	var markerImage = new kakao.maps.MarkerImage(src, size, options);
	return markerImage;
}

// 좌표와 마커이미지를 받아 마커를 생성하여 리턴하는 함수입니다
function createMarker(position, image, number) {
	var marker = new kakao.maps.Marker({
		map:map,
		position: position,
		image: image,
		title: number
	});
	return marker;
}

//비상벨 마커를 생성하고 비상벨 마커 배열에 추가하는 함수입니다
function createBellMarkers() {
	bellMarkers= [];
	for (var i = 0; i < bellPositions.length; i++) {
		var marker = createMarker(bellPositions[i].latlng, bellMarkerImage, bellPositions[i].number);
		// 생성된 마커를 cctv 마커 배열에 추가합니다
		bellMarkers.push(marker);
		bellAll.push(marker);
		//인포윈도우를 생성합니다
//       var infowindow = new kakao.maps.InfoWindow({
//          content : bellPositions[i].iwContent
//       });
		// 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
		// 이벤트 리스너로는 클로저를 만들어 등록합니다
		// for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
       kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
//        kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
		kakao.maps.event.addListener(marker, 'click', makeClickListener(marker));
	}
}

// 비상벨 마커들의 지도 표시 여부를 설정하는 함수입니다
function setBellMarkers(map) {
	for (var i = 0; i < bellMarkers.length; i++) {
		bellMarkers[i].setMap(map);
	}
}

// 지구대 마커를 생성하고 지구대 마커 배열에 추가하는 함수입니다
function createPoliceMarkers() {
	policeMarkers= [];
	for (var i = 0; i < policePositions.length; i++) {
		var marker = createMarker(policePositions[i].latlng, policeMarkerImage, policePositions[i].number);
		// 생성된 마커를 cctv 마커 배열에 추가합니다
		policeMarkers.push(marker);
		policeAll.push(marker);
		//인포윈도우를 생성합니다
		var infowindow = new kakao.maps.InfoWindow({
			content : policePositions[i].iwContent
		});
		// 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
		// 이벤트 리스너로는 클로저를 만들어 등록합니다
		// for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
       kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
//        kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
		kakao.maps.event.addListener(marker, 'click', makeClickListener(marker));
	}
}

// 지구대 마커들의 지도 표시 여부를 설정하는 함수입니다
function setPoliceMarkers(map) {
	for (var i = 0; i < policeMarkers.length; i++) {
		policeMarkers[i].setMap(map);
	}
}

// 편의점 마커를 생성하고 편의점 마커 배열에 추가하는 함수입니다
function createShopMarkers() {
	shopMarkers= [];
	for (var i = 0; i < shopPositions.length; i++) {
		var marker = createMarker(shopPositions[i].latlng, shopMarkerImage, shopPositions[i].number);
		// 생성된 마커를 cctv 마커 배열에 추가합니다
		shopMarkers.push(marker);
		shopAll.push(marker);
		//인포윈도우를 생성합니다
		var infowindow = new kakao.maps.InfoWindow({
			content : shopPositions[i].iwContent
		});
		// 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
		// 이벤트 리스너로는 클로저를 만들어 등록합니다
		// for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
       kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
//        kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
		kakao.maps.event.addListener(marker, 'click', makeClickListener(marker));
	}
}

// 편의점 마커들의 지도 표시 여부를 설정하는 함수입니다
function setShopMarkers(map) {
	for (var i = 0; i < shopMarkers.length; i++) {
		shopMarkers[i].setMap(map);
	}
}

//지킴이집 마커를 생성하고 지킴이집 마커 배열에 추가하는 함수입니다
function createGuardMarkers() {
	guardMarkers= [];
	for (var i = 0; i < guardPositions.length; i++) {
		var marker = createMarker(guardPositions[i].latlng, guardMarkerImage, guardPositions[i].number);
		// 생성된 마커를 cctv 마커 배열에 추가합니다
		guardMarkers.push(marker);
		guardAll.push(marker);
		//인포윈도우를 생성합니다
		var infowindow = new kakao.maps.InfoWindow({
			content : guardPositions[i].iwContent
		});
		// 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
		// 이벤트 리스너로는 클로저를 만들어 등록합니다
		// for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
       kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
//        kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
		kakao.maps.event.addListener(marker, 'click', makeClickListener(marker));
	}
}

// 지킴이집 마커들의 지도 표시 여부를 설정하는 함수입니다
function setGuardMarkers(map) {
	for (var i = 0; i < guardMarkers.length; i++) {
		guardMarkers[i].setMap(map);
	}
}

// 보안등 마커를 생성하고 보안등 마커 배열에 추가하는 함수입니다
function createSecurityLampMarkers() {
	securityLampMarkers= [];
	for (var i = 0; i < securityLampPositions.length; i++) {
		var marker = createMarker(securityLampPositions[i].latlng, securityLampMarkerImage, securityLampPositions[i].number);
		// 생성된 마커를 cctv 마커 배열에 추가합니다
		securityLampMarkers.push(marker);
		securityLampAll.push(marker);
		//인포윈도우를 생성합니다
		var infowindow = new kakao.maps.InfoWindow({
			content : securityLampPositions[i].iwContent
		});
		// 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
		// 이벤트 리스너로는 클로저를 만들어 등록합니다
		// for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
       kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
//        kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
		kakao.maps.event.addListener(marker, 'click', makeClickListener(marker));
	}
}

// 보안등 마커들의 지도 표시 여부를 설정하는 함수입니다
function setSecurityLampMarkers(map) {
	for (var i = 0; i < securityLampMarkers.length; i++) {
		securityLampMarkers[i].setMap(map);
	}
}

//cctv 마커를 생성하고 cctv 마커 배열에 추가하는 함수입니다
function createCctvMarkers() {
	cctvMarkers= [];
	for (var i = 0; i < cctvPositions.length; i++) {
		var marker = createMarker(cctvPositions[i].latlng, cctvMarkerImage, cctvPositions[i].number);
		// 생성된 마커를 cctv 마커 배열에 추가합니다
		cctvMarkers.push(marker);
		cctvAll.push(marker);
		//인포윈도우를 생성합니다
		var infowindow = new kakao.maps.InfoWindow({
			content : cctvPositions[i].iwContent
		});
		// 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
		// 이벤트 리스너로는 클로저를 만들어 등록합니다
		// for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
      kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
//       kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
		kakao.maps.event.addListener(marker, 'click', makeClickListener(marker));
	}
}

// cctv 마커들의 지도 표시 여부를 설정하는 함수입니다
function setCctvMarkers(map) {
	for (var i = 0; i < cctvMarkers.length; i++) {
		cctvMarkers[i].setMap(map);
	}
}

//카테고리를 클릭했을 때 type에 따라 카테고리의 스타일과 지도에 표시되는 마커를 변경합니다
//function changeMarker(type){
//
//    var bellMenu = document.getElementById('bell');
//    var policeMenu = document.getElementById('police');
//    var guardMenu = document.getElementById('guard');
//    var cctvMenu = document.getElementById('cctv');
//    var shopMenu = document.getElementById('shop');
//    var securityLampMenu = document.getElementById('security_lamp');
//
//    // 비상벨 카테고리가 클릭됐을 때
//    if (type === 'bell') {
//
//       if(bellFlag == 1){
//          bellMenu.className='';
//          setBellMarkers(null);
//       }
//       else {
//          // 비상벨 카테고리를 선택된 스타일로 변경하고
//          bellMenu.className = 'menu_selected';
//
//          setBellMarkers(map);
//       }
//
//        // 편의점과 보안등 카테고리는 선택되지 않은 스타일로 바꿉니다
//        //storeMenu.className = '';
//        //carparkMenu.className = '';
//
//        // 비상벨 마커들만 지도에 표시하도록 설정합니다
//        setBellMarkers(map);
////        setStoreMarkers(null);
////        setCarparkMarkers(null);
//
//    } else if (type === 'shop') { // 편의점 카테고리가 클릭됐을 때
//
//       if(shopFlag ==1 ){
//          shopMenu.className='';
//          setShopMarkers(null);
//       }
//       else {
//          // 편의점 카테고리를 선택된 스타일로 변경하고
//          shopMenu.className = 'menu_selected';
//          // 편의점 마커들을 지도에 표시하도록 설정합니다
//          setShopMarkers(map);
//       }
//        //coffeeMenu.className = '';
//       // carparkMenu.className = '';
//
//        //setCoffeeMarkers(null);
//        //setCarparkMarkers(null);
//
//    } else if (type === 'securityLamp') { // 보안등 카테고리가 클릭됐을 때
//
//       if(securityLampFlag ==1){
//          securityLampMenu.className='';
//          setSecurityLampMarkers(null);
//       }
//       else {
//          // 보안등 카테고리를 선택된 스타일로 변경하고
//          securityLampMenu.className = 'menu_selected';
//          // 보안등 마커들을 지도에 표시하도록 설정합니다
//          setSecurityLampMarkers(map);
//       }
////        coffeeMenu.className = '';
////        storeMenu.className = '';
//
////        setCoffeeMarkers(null);
////        setStoreMarkers(null);
//    }    else if (type === 'police') { // 지구대 카테고리가 클릭됐을 때
//
//       if(policeFlag ==1){
//          policeMenu.className='';
//          setPoliceMarkers(null);
//       }
//       else {
//          // 지구대 카테고리를 선택된 스타일로 변경하고
//          policeMenu.className = 'menu_selected';
//          // 지구대 마커들을 지도에 표시하도록 설정합니다
//          setPoliceMarkers(map);
//       }
//    }
//    else if (type === 'guard') { // 지킴이집 카테고리가 클릭됐을 때
//
//       if(guardFlag ==1){
//          guardMenu.className='';
//          setGuardMarkers(null);
//       }
//       else {
//          // 지킴이집 카테고리를 선택된 스타일로 변경하고
//          guardMenu.className = 'menu_selected';
//          // 지킴이집 마커들을 지도에 표시하도록 설정합니다
//          setGuardMarkers(map);
//       }
//    }
//    else {
//       if(cctvFlag ==1){
//          cctvMenu.className='';
//          setCctvMarkers(null);
//       }
//       else {
//          // cctv 카테고리를 선택된 스타일로 변경하고
//          cctvMenu.className = 'menu_selected';
//          // cctv 마커들을 지도에 표시하도록 설정합니다
//          setCctvMarkers(map);
//       }
//    }
//}

function setZoomable(zoomable) {
	// 마우스 휠로 지도 확대,축소 가능여부를 설정합니다
	map.setZoomable(zoomable);
}

function getRoad() {
	$.ajax('api/map/road',{
		type: 'GET'
	}).then(function(data,status){
		if(status == 'success')
		{
			console.log(data);
			for (var i = 0; i < data.length; i++){
				var temp = data[i];
				// if(temp.length == 1) {continue;}
				var positions=[];
				if(temp == null ) { continue;}
				var latlng = new kakao.maps.LatLng(temp.lat, temp.lon);
				// 지도에 표시
				var marker = new kakao.maps.Marker({
					position: latlng,
					title: temp.roadIndex
				});
				marker.setMap(map);
			}
		}
	})
}

function getRoadLine() {
	$.ajax('api/map/road',{
		type: 'GET'
	}).then(function(data,status){
		if(status == 'success')
		{
			for (var i = 0; i < data.length; i++){
				var temp = data[i];
				// if(temp.length == 1) {continue;}
				var positions=[];
				if(temp == null ) { continue;}

				var linePath = [
					new kakao.maps.LatLng(temp.startLat, temp.startLon),
					new kakao.maps.LatLng(temp.endLat, temp.endLon)
				];

				var polyline = new kakao.maps.Polyline({
					path : linePath,
					strokeWeight : 4,
					storkeColor : '#FFAE00',
					strokeOpacity : 0.8,
					storkeStyle : 'solid'
				});

				polyline.setMap(map);
			}
		}
	})
}

function getNode() {
	$.ajax('api/map/node',{
		type: 'GET'
	}).then(function(data,status){
		if(status == 'success')
		{
			console.log(data);
			for (var i = 0; i < data.length; i++){
				var temp = data[i];
				// if(temp.length == 1) {continue;}
				var positions=[];
				if(temp == null ) { continue;}
				var latlng = new kakao.maps.LatLng(temp.lat-0.00279045488643, temp.lon+0.0021230328);
				// 지도에 표시
				var marker = new kakao.maps.Marker({
					position: latlng,
					title: temp.nodeId
				});
				marker.setMap(map);
			}
		}
	})
}

function getEdge() {
	$.ajax('api/map/edge',{
		type: 'GET'
	}).then(function(data,status){
		if(status == 'success')
		{
			console.log(data);
			for (var i = 0; i < data.length; i++){
				var temp = data[i];
				var positions=[];
				if(temp == null ) { continue;}
				var linePath = [
					new kakao.maps.LatLng(temp.startLat-0.00279045488643, temp.startLon+0.0021230328),
					new kakao.maps.LatLng(temp.endLat-0.00279045488643, temp.endLon+0.0021230328)
				];

				var polyline = new kakao.maps.Polyline({
					path : linePath,
					strokeWeight : 2,
					storkeColor : '#FFAE00',
					strokeOpacity : 0.8,
					storkeStyle : 'solid'
				});

				polyline.setMap(map);
			}
		}
	})
}

$(document).ready(function(){
	var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center : new kakao.maps.LatLng(37.48396611813521, 126.95557093273275), //지도의 중심좌표.
		level : 2
		//지도의 레벨(확대, 축소 정도)
	};
	map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

	map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

	map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

	kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
		// keyword.value().set
		// // 기존에 추가된 페이지번호를 삭제합니다
		// while (paginationEl.hasChildNodes()) {
		// 	paginationEl.removeChild (paginationEl.lastChild);
		// }
		// removeAllChildNods(listEl);
		if(detailList){
			detailList.close();
		}

		var latlng = mouseEvent.latLng;
		console.log(latlng);
	});
	//////////////////////////////////////////////
	// getRoad();	// 모든 도로에 마커 표시
	getNode();	// 모든 교차로 (속도 변화 구간) 에 마커 표시
	// getRoadLine();
	getEdge();


	// 지도에 idle 이벤트를 등록합니다 지도 이동할때 발생하는 이벤트
	kakao.maps.event.addListener(map, 'dragend', searchNewPlaces);
	kakao.maps.event.addListener(map, 'zoom_changed', searchNewPlaces);

	// 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
//   contentNode.className = 'placeinfo_wrap';

	// 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
	// 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
//   addEventHandle(contentNode, 'mousedown', kakao.maps.event.preventMap);
//   addEventHandle(contentNode, 'touchstart', kakao.maps.event.preventMap);

	// 커스텀 오버레이 컨텐츠를 설정합니다
//   placeOverlay.setContent(contentNode);

	$('#search_menu').click(function(){
		if($('#searchForm').hasClass("open")){
			$('#searchForm').css("display","none");
			$('#searchForm').removeClass("open");
		}
		else {
			$('#searchForm').css("display","block");
			$('#searchForm').addClass("open");
		}
	});

	$('#security_menu').click(function(){
		if($('#searchForm').hasClass("open")){
			$('#searchForm').css("display","none");
			$('#searchForm').removeClass("open");
		}
	});
});
