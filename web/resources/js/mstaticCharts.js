google.charts.load("current", {packages:['corechart']});
//google.charts.setOnLoadCallback(drawChart);
var map;
var markers = [];
var alldata = [];
var prev1year= [];
var prev2year= [];
var area=25;
var polygon;
var ranking = function(infos){
	$('<tr class="ranked">'+'<td class="rank">'+infos.rank+'</td>'+
		'<td class="district">'+infos.stats.district+'</td>'+
		'<td class="occurCnt">'+infos.stats.occurCnt+'</td>'+'</tr>',{}).appendTo('#rankContent > tbody');
};
var selection = function(event){
	alldata = null;
	alldata = [];
	$('.ranked').remove();
	var crimeId = event.srcElement.id;
	if(crimeId==null) crimeId="ALL";
	$.ajax({
		url:"/api/stats/stats",
		type:'POST',
		data:{crimeId: crimeId},
		success:function(data){
			//alert("성공");
			//console.log(data.length);
			for(let x=0;x<data.length;x++){
				alldata=alldata.concat(data[x]);
			}
			//지도에 폴리곤으로 표시할 영역데이터 배열입니다
			prev2year=data[1];
			prev1year=data[2];
			drawChart(alldata);
			for(let i=0; i<area;i++)
			{
				ranking(alldata[i]);
			}
			},
		error:function(jqXHR,textStatus,errorThrown){
			alert("에러"+textStatus+":",errorThrown);
			//self.close();
		}
		,});
	};

$(document).ready(function(){
});