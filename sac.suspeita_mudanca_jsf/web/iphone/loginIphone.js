//alterado
function login() {
	var usr = $$("cmp-usuario").value;
	var senha = $$("cmp-senha").value;
	var permanecer = $$("cb-permanecer");
	var usuario = {
			usuarioEmail:usr,
			usuarioSenha:senha,
			permanecer: permanecer.checked
			};
	UsuariosPortalJS.login(usuario, callBackLogin);
}
function callBackLogin(login){
	if(login != ""){
		iniciaOperacoesLogin(login);
		$$("cmp-usuario").value = "";
		$$("cmp-senha").value = "";
	}else{
		finalizaOperacoesLogin();
	}
}
function esqueci() {
	var usuario = $$("cmp-usuario").value;
	if(usuario == ""){
		$$("content-msg").innerHTML = "Digite seu e-mail.";
	}else{
		var usuario = {usuarioEmail:usuario};
		UsuariosPortalJS.esqueci(usuario, callBackEsqueci);
	}
}
function callBackEsqueci(ret){
	if(ret == 1){
		$$("content-msg").innerHTML = "<strong>E-mail com a nova senha enviado!</strong>";
	}else if(ret == 0){
		$$("content-msg").innerHTML = "Usuário não encontrado!";
	}else{
		$$("content-msg").innerHTML = "Ocorreu uma execeção, tente mais tarde!";
	}
	$$("content-msg").style.visibility = "visible";
}
function loginCookie(){
	UsuariosPortalJS.loginCookie(callBackLoginCookie);
}
function callBackLoginCookie(login){
	if(login != ""){
		iniciaOperacoesLoginCookie(login);
	}else{
		finalizaOperacoesLoginCookie();
	}
}
function logoff(){
	UsuariosPortalJS.logoff(callBackLogoff);
}
function callBackLogoff(sucesso){
	finalizaOperacoesLoginCookie();
	fnIndex();
}
function finalizaOperacoesLogin(){
	$$("content-msg").style.visibility = "visible";
	$$("content-msg").innerHTML = "Login ou senha inválidos.";
}
function finalizaOperacoesLoginCookie(){
	$$("content-msg").style.visibility = "hidden";
}
function iniciaOperacoesLogin(login){
	$$("content-msg-logado").style.display = "block";
	$$("content-msg").style.visibility = "visible";
	var l = login.split(" ");
	$$("span-login").innerHTML = l[0];
	iniciaDir_Esq('ui', 'login');
	UsuariosPortalJS.carregaMenu(1,callBackCarregaMenu);
}
function iniciaOperacoesLoginCookie(login){
	$$("content-msg-logado").style.display = "block";
	$$("content-msg").style.visibility = "visible";
	var l = login.split(" ");
	$$("span-login").innerHTML = l[0];
	UsuariosPortalJS.carregaMenu(1,callBackCarregaMenu);
}
function carregaNome(){
	UsuariosPortalJS.carregaNome(carregaNomeCallBack);
}
function carregaNomeCallBack(login){
	if(login != ""){
		var l = login.split(" ");
		$$("content-msg-logado").style.display = "block";
		$$("span-login").innerHTML = l[0];
	}
}
function callBackCarregaMenu(menu){
    var html = "<ul class=\"rounded\">";
    for(var i = 0; i < menu.length; i++){
        if(menu[i].possuiItem){
            html += "<li class=\"arrow\" onclick=\"iniciaEsq_Dir('ui', '"+menu[i].id+"')\"><a href=\"#\">"+ menu[i].id +"</a></li>";
        }
    }
    html += "</ul>";
    document.getElementById("menu-conteiner").innerHTML = html;
    document.getElementById("menu-conteiner").style.display = "block";
    
    var html2 = "";
	for(var j = 0; j < menu.length; j++){
		if(menu[j].possuiItem){
			var temSub = false;
			if(menu[j].itemdata.length > 0){
				temSub = true;
			}
			if(temSub){
			    html2 += "<div id=\""+menu[j].id+"\" class=\"selectable2\">";
			    html2 += "<div class=\"toolbar\">";
			    html2 += "<h1>"+menu[j].id+"</h1>";
			    html2 += "<a class=\"back\" onclick=\"iniciaDir_Esq('ui', '"+menu[j].id+"')\" href=\"#\">Home</a>";
			    html2 += "</div>";
			    html2 += "<div id=\"menu-conteiner\">";
			    html2 += "<ul class=\"rounded\">";
			    
			    for(var k = 0; k < menu[j].itemdata.length; k++){
			    	if(menu[j].itemdata[k].menuAtivado == 1){
			    		html2 += "<li class=\"arrow\" onclick=\""+menu[j].itemdata[k].url+"();\"><a href=\"#\">"+ menu[j].itemdata[k].text +"</a></li>";
			    	}
			    }
			}
		    html2 += "</ul></div></div>";
		}
	}
	document.getElementsByTagName("body")[0].innerHTML = document.getElementsByTagName("body")[0].innerHTML + html2;;
	verificaHistorico();
}