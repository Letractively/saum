<?xml version="1.0" encoding="UTF-8"?>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<%@page import="br.com.meganet.util.ConfigUtil"%>
<html xmlns="http://www.w3.org/1999/xhtml" manifest="/m?manifest=1">
	<head>
		<link rel="icon" type="image/vnd.microsoft.icon" href="favicon.ico" />
		<link rel="shortcut icon" href="favicon.ico" />
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no"/>
		<meta name="format-detection" content="address=no" />
		
		<link rel="apple-touch-icon" href="favicon.png"/>
		<link rel="apple-touch-icon-precomposed" href="favicon.png" />
		<title><%=ConfigUtil.getInstance().getProperty("empresa","Meganet") %></title>
		<jsp:include page="/iphone/includes.jsp"></jsp:include>
		
		<script type="text/javascript" language="javascript">
	        var jQT = new $.jQTouch({
	            icon: 'jqtouch.png',
	            addGlossToIcon: false,
	            startupScreen: 'jqt_startup.png',
	            statusBar: 'black',
	            preloadImages: [
	                '../img/tempo_carregando.gif'
	                ]
	        });
	        $(function(){
	            $('body').bind('turn', function(e, data){
	                $('#orient').html('Orientation: ' + data.orientation);
	            });
	        });

		</script>
	</head>

	<body>
		<jsp:include page="/iphone/topo.jsp"></jsp:include>
		<div id="ui" class="selectable1">
			<div class="toolbar">
				<h1>Home</h1>
			</div>
			<div id="menu-conteiner">
				<ul class="rounded">
					<li class="arrow"><a onclick="iniciaEsq_Dir('ui', 'login')" href="#">Login</a></li>
					<li class="arrow"><a onclick="iniciaEsq_Dir('ui', 'avisos')" href="#">Avisos</a></li>
					<li class="arrow"><a onclick="fnContato()" href="#">Contato</a></li>
				</ul>
			</div>
		</div>
		
		<div id="avisos" class="selectable2">
			<div class="toolbar">
				<h1>Avisos</h1>
				<a class="back" onclick="iniciaDir_Esq('ui', 'avisos')" href="#">Home</a>
			</div>
			<div id="content-aviso" class="painelScrol">&nbsp;</div>
		</div>
		
        <div id="login" class="selectable2">
            <div class="toolbar">
                <h1>Login</h1>
                <a href="#" onclick="iniciaDir_Esq('ui', 'login')" class="back">Back</a>
            </div>
            <form onsubmit="return false">
                <div id="content-msg" style="visibility: hidden;">&nbsp;</div>
                <ul class="edit rounded">
                    <li><input type="email" value="" name="cmp-usuario" placeholder="E-mail" id="cmp-usuario" /></li>
                    <li><input type="password" value="" name="cmp-senha" placeholder="Senha" id="cmp-senha" /></li>
                    <li>Lembrar login<span class="toggle"><input type="checkbox" id="cb-permanecer"/></span></li>
                    <li>&nbsp;</li>
                    <li>
						<button onclick="javascript: login();">Enviar</button>
						<button onclick="javascript: esqueci();">Esqueci a senha</button>
					</li>
                </ul>
            </form>
        </div>
        
	</body>
	<script type="text/javascript">
		loginCookie();
	</script>
</html>
