//alterado
var map;
var latMarcador = new google.maps.LatLng(-15.671046574581688, -47.648005185180665);
var latlng;
var clickedLocation;
var directionsService = new google.maps.DirectionsService();
var directionDisplay = new google.maps.DirectionsRenderer;
var markersArray = [];

function fnCarregaComboServidorAlteraCallback(servs){
	var oComboBox = $$('combo-alt-servidores');
	for (var i = 0; i < servs.length ; i++){
		oComboBox.options[i + 1] = new Option(servs[i].servidorNome, servs[i].servidorId);
	}
}
function initialize(latIni, latFim, msg, tam, z) {
	latlng = new google.maps.LatLng(latIni, latFim);
	var myOptions = {
		zoom : z,
		navigationControl: true,
		mapTypeControl: false,
		scaleControl: true,
		center : latlng,
		mapTypeId : google.maps.MapTypeId.SATELLITE
	};
	map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
	directionDisplay.setMap(map);
	
	var marker1 = new google.maps.Marker( {
		position : latMarcador,
		map : map,
		title : msg
	});

	var tamJan = document.body.parentNode.clientHeight;
	var t = (tamJan - tam);
	document.getElementById("map_canvas").style.height = ((t - 28) + "px");
}
function deletaMarcadores(){
	if (markersArray) {
		for (i in markersArray) {
			markersArray[i].setMap(null);
		}
		markersArray.length = 0;
	}
}
function calcRoute() {
  var request = {
    origin:latMarcador, 
    destination:clickedLocation,
    travelMode: google.maps.DirectionsTravelMode.DRIVING
  };
  directionsService.route(request, function(result, status) {
    if (status == google.maps.DirectionsStatus.OK) {
    	directionDisplay.setDirections(result);
    }
  });
}
function colocaMsgNoMarcador(usr, marker){
	var html = "<b>Nome:</b> " + usr.usuarioNome + "<br/>";
	html += "<b>Endereço:</b> " + usr.usuarioEndereco + " - " + usr.usuarioComplementoEndereco + ", " + usr.usuarioBairro+ ", " + usr.usuarioCidade + "<br/>";
	html += "<b>CPF/CNPJ:</b> " + usr.usuarioCpf + "<br/>";
	html += "<b>Id:</b> " + usr.usuarioId + "<br/>";
	html += "<b>Torre:</b> " + usr.servidor.servidorNome + "<br/>";
	var infowindow = new google.maps.InfoWindow(
			{ content: html,
				size: new google.maps.Size(50,50)
			});
	google.maps.event.addListener(marker, 'click', function() {
		infowindow.open(map,marker);
	});
	google.maps.event.addListener(marker, 'rightclick', function() {
		infowindow.close();
	});
}
function buscaClientesComboLocalizacaoCallBack(clientes){
	var oComboCliente = $$("combo-cliente2");
	oComboCliente.options[0] = new Option("Localizar um cliente no mapa", "");
	DWRUtil.addOptions("combo-cliente2", clientes,"usuarioId", "usuarioNome");
	oComboCliente.options[0].selected = true;
}
function localizarMapa(){
	var oComboUsuario = $$("combo-cliente2");
	var comboUsuario = oComboUsuario.options[oComboUsuario.selectedIndex].value;
	AdministracaoJS.localizarMapaGoogle(comboUsuario, localizarMapaCallBack);
}
function localizarMapaCallBack(ret){
	$$("calcula").disabled = false;
	map.setCenter(latlng);
	map.setZoom(15);
	deletaMarcadores();
	clickedLocation = new google.maps.LatLng(ret.mapaTop, ret.mapaLeft);
	var marker = new google.maps.Marker({
		position : clickedLocation,
		map : map,
		title : ret.usuario.usuarioNome
	});
	colocaMsgNoMarcador(ret.usuario, marker);
	markersArray.push(marker);
}


function buscarClientesPorTorre(){
	var oComboServ = $$("combo-alt-servidores");
	var comboServ = oComboServ.options[oComboServ.selectedIndex].value;
	AdministracaoJS.buscarMapaPeloServidor(comboServ, buscarClientesPorTorreCallBack);
	
}
function buscarClientesPorTorreCallBack(ret){
	$$("calcula").disabled = true;
	map.setCenter(latlng);
	map.setZoom(15);
	deletaMarcadores();
	for(var i = 0; i < ret.length; i++){
		var loc = new google.maps.LatLng(ret[i].mapaTop, ret[i].mapaLeft);
		var m = new google.maps.Marker({
			position : loc,
			map : map,
			title : ret[i].usuario.usuarioNome
		});
		colocaMsgNoMarcador(ret[i].usuario, m);
		markersArray.push(m);
	}
}