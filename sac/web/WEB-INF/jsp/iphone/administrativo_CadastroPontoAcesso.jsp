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
		<script type="text/javascript" src="js/administrativo/cadastroAP.js"></script>
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
                <h1>Cadastro de AP</h1>
                <a href="#" onclick="fnIndex();" class="back">Voltar</a>
            </div>
                <div id="content-msg" style="visibility: hidden;">&nbsp;</div>
                <form action="#" id="form-insere-cliente" onsubmit="return false">
                <ul class="edit rounded">
                    <li>Nome:<input name="usuario" type="text" id="usuario" size="50" maxlength="50" /></li>
                    <li>MAC:<input name="mac" type="text" id="mac" size="25" maxlength="17" onkeydown="return formataMAC(this, event)"/></li>
                    <li>
                   	   	<select name="combo-contrato" id="combo-contrato">
				      		<option value="" selected="selected">Contrato</option>
				    	</select>
                    </li>
                    <li>
						<select name="combo-servidor" id="combo-servidor" onchange="selectTorreEnderecoIP()">
					      <option value="" selected="selected">Servidor</option>
					    </select>
					</li>
                    <li>
                    	<select name="combo-endereco-ip" id="combo-endereco-ip">
					      <option value="" selected="selected">Endereço IP</option>
					    </select>
                    </li>
                    <li>
                    	<select name="combo-plano" id="combo-plano">
					      <option value="" selected="selected">Plano</option>
					    </select>
                    </li>
                    <li>
						<select name="combo-cliente-bloqueado" id="combo-cliente-bloqueado">
							<option value="0" selected>Sem restrição</option>
							<option value="1">Mensagem de advertência</option>
							<option value="2">Bloqueado</option>
							<option value="3">Desativado - Não gera débito</option>
						</select>
                    </li>
                    <li>&nbsp;</li>
                    <li><button onClick="insereUsuario(false)"> &nbsp;&nbsp;Enviar&nbsp;&nbsp; </button></li>
                </ul>
            </form>
        </div>
	</body>
	<script type="text/javascript">
		UtilsJS.carregaComboPlano(fnCarregaComboPlanoCallback);
		UtilsJS.carregaComboServidoresAtivos(fnCarregaComboServidoresCallback);
		ContratoJS.buscaContratos(fnCarregaComboContratosCallBack);
		carregaNome();
	</script>
</html>
