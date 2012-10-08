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
		<jsp:include page="/iphone/includes.jsp"></jsp:include>
		<script type="text/javascript" src="js/administrativo/boleto.js"></script>
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
                <h1>Criar boleto</h1>
                <a href="#" onclick="fnIndex();" class="back">Voltar</a>
            </div>
                <div id="content-msg" style="visibility: hidden;">&nbsp;</div>
                <form action="#" id="form-insere-cliente" onsubmit="return false">
                <ul class="edit rounded">
                    <li>
						<input type="radio" name="tp-cria" id="tp-cria" value="usuarioCpf" checked="checked" onclick="limpaNomeCriaBoleto();"/> <label for="tp-cria">CPF/CNPJ:</label>
						<input type="radio" name="tp-cria" id="tp1-cria" value="usuarioNome"  onclick="limpaNomeCriaBoleto();"/> <label for="tp1-cria">Nome</label>
                    </li>
                    <li>Usuário:<input type="text" id="cliente-cria" name="cliente-cria" maxlength="100" onkeydown="return formataCriaBoletoCpfNome(this, event)"/></li>
					<li id="fieldset-resultado-busca" style="display: none;">
			            <div id="nomes-clientes-resultado">&nbsp;</div>
					</li>
                    <li>Valor:<input onfocus="limpaCampo(this);" onkeyup="return formataDecimal(this, 5);" type="text" name="valor-bol" id="valor-bol" /></li>
                    <li><select name="select-mes-cria-boleto" id="select-mes-cria-boleto"></select></li>
                    <li><select name="select-ano-cria-boleto" id="select-ano-cria-boleto"></select></li>
                    <li>&nbsp;</li>
                    <li><button onclick="criaBoletoExtraUsuario()"> OK </button></li>
                </ul>
            </form>
        </div>
	</body>
	<script type="text/javascript">
		carregaComboAno("select-ano-cria-boleto");
		carregaComboMes("select-mes-cria-boleto");
		carregaNome();
	</script>
</html>
