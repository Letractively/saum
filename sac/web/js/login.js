//alterado
function login() {
	var usuario = $$("cmp-usuario").value;
	var senha = $$("cmp-senha").value;
	var permanecer = $$("cb-permanecer");
	
	var usuario = {
			usuarioEmail:usuario,
			usuarioSenha:senha,
			permanecer: permanecer.checked
			};
	UsuariosPortalJS.login(usuario, callBackLogin);
}
function callBackLogin(login){
	if(login != ""){
		if(	$$("content-aviso") != null && $$("content-aviso2") != null){
			UsuariosPortalJS.carregaAvisos(carregaAvisosCallBack);
		}
		iniciaOperacoesLogin(login);
		$$("cmp-usuario").value = "";
		$$("cmp-senha").value = "";
		$$("content-menu").style.display = "none";
	}else{
		finalizaOperacoesLogin();
	}
}
function esqueci() {
	var usuario = $$("cmp-usuario").value;
	if(usuario == ""){
		if($$("content-msg-login") != null){
			$$("content-msg-login").innerHTML = "Digite seu e-mail.";
		}
	}else{
		modalMensagem.criaMosca();
		modalMensagem.criaDivConteudo();
		var usuario = {usuarioEmail:usuario};
		UsuariosPortalJS.esqueci(usuario, callBackEsqueci);
	}
}
function callBackEsqueci(ret){
	if(ret == 1){
		$$("content-msg-login").innerHTML = "<strong>E-mail com a nova senha enviado!</strong>";
	}else if(ret == 0){
		$$("content-msg-login").innerHTML = "Usuário não encontrado!";
	}else{
		$$("content-msg-login").innerHTML = "Ocorreu uma execeção, tente mais tarde!";
	}
	modalMensagem.escondeModal();
}
function loginCookie(){
	UsuariosPortalJS.loginCookie(callBackLoginCookie);
}
function loginCookie2(){
	UsuariosPortalJS.loginCookie(callBackLoginCookie2);
}
function callBackLoginCookie(login){
	if(login != ""){
		iniciaOperacoesLogin(login);
	}else{
		finalizaOperacoesLoginCookie();
	}
}
function callBackLoginCookie2(login){
	if(	$$("content-aviso") != null && $$("content-aviso2") != null){
		UsuariosPortalJS.carregaAvisos(carregaAvisosCallBack);
	}
	if(login != ""){
		iniciaOperacoesLogin(login);
	}else{
		finalizaOperacoesLoginCookie();
	}
}
function logoff(){
	UsuariosPortalJS.logoff(callBackLogoff);
}
function callBackLogoff(sucesso){
	fnIndex();
}
function finalizaOperacoesLogin(){
	$$("span-login").innerHTML = "Login";
	$$("content-msg-login").innerHTML = "Login ou senha inválidos.";
}
function finalizaOperacoesLoginCookie(){
	UsuariosPortalJS.carregaMenu(2,callBackCarregaMenu);
	if($$("span-login") != null){
		$$("span-login").innerHTML = "Login";
	}
	if($$("content-msg-login") != null){
		$$("content-msg-login").innerHTML = "Usuário não autenticado.";
	}
}
function iniciaOperacoesLogin(login){
	if($$("content-msg-login") != null){
		$$("content-msg-login").style.display = "none";
	}
	if($$("link-logoff") != null){
		$$("link-logoff").style.display = "block";
	}
	if($$("content-menu") != null){
		$$("content-menu").style.display = "none";
	}
	if($$("span-login") != null){
		var nome = login.split(" ");
		$$("span-login").innerHTML = nome[0] + "&nbsp;-&nbsp;";
	}
	UsuariosPortalJS.carregaMenu(0,callBackCarregaMenu);
}
function carregaAvisosCallBack(avisos){
	var texto = "";
	var texto2 = "";
	
	for(var i = 0; i < avisos.length; i++){
		if(avisos[i].avisosAviso != null){
			texto += "<p class=\"ul-noticia\">" + avisos[i].avisosTitulo;
			texto +=  " - <span style=\"text-decoration: none; color:#FF0000; cursor:pointer\" onclick=\"ativaLeitura('noticia3"+ i +"')\"> Mais...</span></p>";
   			texto += "<div name=\"noticias\" id=\"noticia3"+ i +"\" style=\"display: none; font-size: 12px; color: #444444; padding-left: 18px; margin-bottom: 8px\">"+ avisos[i].avisosAviso +"</div>";
   			texto +=  "</p>";
   		}else{
   			texto2 += "<p class=\"ul-noticia\">" + avisos[i].avisosTitulo;
   			texto2 +=  "</p>";
   		}
	}
	$$("content-aviso").innerHTML = texto;
	if(texto2 != null && $$("content-aviso2") != null){
		$$("content-aviso2").innerHTML = texto2;
	}
}

function mouseNoMenu(){
	if($$("chartdiv") != null){
		$$("chartdiv").style.display = "none";
	}
}

function mouseForaDoMenu(){
	if($$("chartdiv") != null){
		$$("chartdiv").style.display = "block";
	}
}

function callBackCarregaMenu(menu){
	var html = "<ul class=\"art-menu\">";
	html += "<li><a href=\"#\" onclick=\"fnIndex();\"><span class=\"l\"></span><span class=\"r\"></span><span class=\"t\">Início</span></a></li>";
	for(var i = 0; i < menu.length; i++){
		if(menu[i].possuiItem){
			html += "<li><a href=\"#\" onmouseout=\"mouseForaDoMenu();\" onmousemove=\"mouseNoMenu();foraInter();\" ><span class=\"l\"></span><span class=\"r\"></span><span class=\"t\">"+ menu[i].id +"</span></a>";
			var temSub = false;
			if(menu[i].itemdata.length > 0){
				temSub = true;
			}
			if(temSub){
				html += "<ul>";
				for(var k = 0; k < menu[i].itemdata.length; k++){
					html += "<li><a href=\"#\" onmouseout=\"mouseForaDoMenu();\" onmousemove=\"mouseNoMenu();foraInter();\" onclick=\""+ menu[i].itemdata[k].url +"\">"+ menu[i].itemdata[k].text +"</a></li>";
				}
			}
			html += "</ul>";
		}
		html += "</li>";
	}
	html += "</ul>";
	document.getElementById("menu-conteiner").innerHTML = html;
	document.getElementById("menu-conteiner").style.display = "block";
}
function vertical() {
	   var navItems = document.getElementById("nav").getElementsByTagName("li");
	   for (var i=0; i< navItems.length; i++) {
	      if(navItems[i].className == "submenu") {
	         navItems[i].onmouseover=function() {this.getElementsByTagName('ul')[0].style.display="block";this.style.backgroundColor = "#f9f9f9";};
	         navItems[i].onmouseout=function() {this.getElementsByTagName('ul')[0].style.display="none";this.style.backgroundColor = "#FFFFFF";};
	      }
	   }
	}