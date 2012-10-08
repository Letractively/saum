var posX = 0;
var posY = 0;
var posFixoX = 0;
var posFixoY = 0;
var foiClicado = false;
var idObjSelecionado = 0;
var usuarioLocalizado = 0;
var MouseCoord = function(event) { 
	event = event || window.event;   

	var e = {
		_x: (event.pageX || event.clientX + (document.documentElement || document.body).scrollLeft),    
		_y: (event.pageY || event.clientY + (document.documentElement || document.body).scrollTop) 
	};   
	
	 return {X: e._x, Y: e._y};
 };

window.onload = function(){  
	document.onmousemove = function(event){    
		MouseCoord(event).toString("console");     
		posX = MouseCoord(event).X;
		posY = MouseCoord(event).Y;
	};
};

function fixaLocal(){
	var localX = posX - 3;
	var localY = posY - 3;
	posFixoX = localX;
	posFixoY = localY;
	if((localX > 515 || localY > 555) && !foiClicado){
		foiClicado = true;
		var oDiv = document.createElement("div");
		oDiv.id = localX + "-" + localY;
		oDiv.style.position = "absolute";
		oDiv.style.left = localX + "px";
		oDiv.style.top = localY + "px";
		oDiv.style.width = "6px";
		oDiv.style.height = "6px";
		oDiv.style.zIndex = "33";
		oDiv.style.display = "block";
		oDiv.style.background = "#009900";
		oDiv.onclick = function(){some(this)};
		oDiv.onmouseover = function(){clienteMouseOverTemp(this)};
		oDiv.onmouseout = function(){clienteMouseOutTemp(this)};
		document.body.appendChild(oDiv);
	}
}

function clienteMouseOverTemp(obj){
	obj.style.width = "12px";
	obj.style.height = "12px";
}

function clienteMouseOutTemp(obj){
	obj.style.width = "6px";
	obj.style.height = "6px";
}

function clienteMouseOver(obj){
	obj.style.width = "12px";
	obj.style.height = "12px";
}

function clienteMouseOut(obj){
	obj.style.width = "6px";
	obj.style.height = "6px";
}

function some(obj){
	document.body.removeChild(obj);
	foiClicado = false;
}

function apaga(){
	var resp = confirm("Apaga esse usuario do mapa?");
	if(resp){
		AdministracaoJS.deletaMapa(idObjSelecionado, apagaCallBack);
	}
}

function apagaCallBack(ret){
	window.location = "localizacaoCliente.do";
}

function buscaClientesComLocalizacaoCallBack(mapas){

	for(var i = 0; i < mapas.length; i++){
		var oDiv = document.createElement("div");
		oDiv.id = mapas[i].mapaId;
		oDiv.style.position = "absolute";
		oDiv.style.left = mapas[i].mapaLeft + "px";
		oDiv.style.top = mapas[i].mapaTop + "px";
		oDiv.style.width = "6px";
		oDiv.style.height = "6px";
		oDiv.style.zIndex = "33";
		oDiv.style.display = "block";
		var statusCliente = mapas[i].usuario.usuarioBloqueado;
		if(statusCliente == 0){
			oDiv.style.background = "#0000FF";
		}else if(statusCliente == 1){
			oDiv.style.background = "#00FF00";
		}else if(statusCliente == 2){
			oDiv.style.background = "#FF00FF";
		}else if(statusCliente == 3){
			oDiv.style.background = "#FF0000";
		}
		oDiv.onclick = function(){buscaInfCliente(this);};
		oDiv.onmouseover = function(){clienteMouseOver(this);};
		oDiv.onmouseout = function(){clienteMouseOut(this);};
		document.body.appendChild(oDiv);
	}
}

function buscaInfCliente(obj){
	idObjSelecionado = obj.id;
	AdministracaoJS.buscaInfCliente(obj.id, buscaInfClienteCallBack);
}

function buscaInfClienteCallBack(usuario){
	$$("nome").innerHTML = usuario.usuarioNome;
	$$("end").innerHTML = usuario.usuarioEndereco;
	$$("cep").innerHTML = usuario.usuarioCep;
	$$("cidade").innerHTML = usuario.usuarioCidade;
	$$("telefone").innerHTML = usuario.usuarioTelefone1;
	$$("celular").innerHTML = usuario.usuarioTelefone2;
	$$("bairro").innerHTML = usuario.usuarioBairro;
	$$("cpf").innerHTML = usuario.usuarioCpf;
	$$("bt-apaga").disabled = false;
}

function buscaClientesComboLocalizacaoCallBack(clientes){
	var oComboCliente = $$("combo-cliente2");
	oComboCliente.options[0] = new Option("Localizar no mapa", "");
	DWRUtil.addOptions("combo-cliente2", clientes,"usuarioId", "usuarioNome");
	oComboCliente.options[0].selected = true;
}

function buscaClientesSemLocalizacaoCallBack(clientes){
	var oComboCliente = $$("combo-cliente");
	oComboCliente.options[0] = new Option("Clientes não inseridos", "");
	DWRUtil.addOptions("combo-cliente", clientes,"usuarioId", "usuarioNome");
	oComboCliente.options[0].selected = true;
}

function gravarPosicaoCliente(){
	
	var oComboUsuario = $$("combo-cliente");
	var comboUsuario = oComboUsuario.options[oComboUsuario.selectedIndex].value;
	var oUsuario = {usuarioId:comboUsuario};
	var mapa = {
			usuario:oUsuario,
			mapaTop:posFixoY,
			mapaLeft:posFixoX
	};
	AdministracaoJS.gravaMapa(mapa, gravarPosicaoClienteCallBack);
	
}

function localizarMapa(){
	var oDiv = $$(usuarioLocalizado);
	if(oDiv != null){
		oDiv.style.width = "6px";
		oDiv.style.height = "6px";
		oDiv.style.background = "#FF0000";
	}
	var oComboUsuario = $$("combo-cliente2");
	var comboUsuario = oComboUsuario.options[oComboUsuario.selectedIndex].value;
	AdministracaoJS.localizarMapa(comboUsuario, localizarMapaCallBack);
}

function localizarMapaCallBack(ret){
	usuarioLocalizado = ret;
	var oDiv = $$(ret);
	oDiv.style.width = "8px";
	oDiv.style.height = "8px";
	oDiv.style.background = "#FFFF00";
}
function gravarPosicaoClienteCallBack(ret){
	window.location = "localizacaoCliente.do";
}