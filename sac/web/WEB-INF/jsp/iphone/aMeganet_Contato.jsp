<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.com.meganet.util.Util"%>

<%@page import="br.com.meganet.util.ConfigUtil"%><html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link rel="icon" type="image/vnd.microsoft.icon" href="favicon.ico" />
		<link rel="shortcut icon" href="favicon.ico" />
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
		<meta name="format-detection" content="telephone=no"/>
		<link rel="apple-touch-icon" href="favicon.png"/>
		<link rel="apple-touch-icon-precomposed" href="favicon.png" />
		<title><%=ConfigUtil.getInstance().getProperty("empresa","Meganet") %></title>
		<script type='text/javascript' src='dwr/interface/ContatoJS.js'></script>
		<jsp:include page="/iphone/includes.jsp"></jsp:include>
		<script type="text/javascript" src="js/meganet/contato.js"></script>
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
		<div id="login" class="selectable1">
            <div class="toolbar">
                <h1>Contato</h1>
                <a href="#" onclick="fnIndex();" class="back">Voltar</a>
            </div>
			<form action="#" id="form-cliente" onsubmit="return false">
			<input type="hidden" name="tipo" id="tipo" value="contato"> 
            <ul class="edit rounded">
                <li>Nome:<input name="usuario" type="text" id="usuario" size="50" maxlength="50" /></li>
                <li>E-mail:<input name="email" type="email" id="email" size="50" maxlength="50" /></li>
                <li>Endereço:<input name="cr" type="text" id="cr" maxlength="80" /></li>
                <li>Telefone:<input pattern="[0-9]*" name="telefone" type="text" id="telefone" size="25" maxlength="13"/></li>
                <li>Mensagem:<textarea name="mensagem-texto" cols="100" rows="8" wrap="virtual" id="mensagem-texto"></textarea></li>
                <li>&nbsp;</li>
                <li><button onClick="enviaMensagem()"> Enviar </button></li>
			</ul>
            </form>
        </div>
	</body>
	<script type="text/javascript">
		carregaNome();
	</script>

</html>
