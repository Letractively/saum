<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">


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
		<script type="text/javascript" src="js/administrativo/alteraUsuarioLimitado.js"></script>
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
                <h1>Altera usr.</h1>
                <a href="#" onclick="fnIndex();" class="back">Voltar</a>
            </div>
			<form action="#" id="form-altera-cliente" onsubmit="return false">
			<input name="id" id="id" type="hidden"/>
            <ul class="edit rounded">
                <li>Nome:<input name="usuario-alt" type="text" readonly="readonly" id="usuario-alt" maxlength="50" /></li>
                <li>CPF/CNPJ:<input name="cpf-alt" readonly="readonly" type="text" id="cpf-alt" size="25" maxlength="14" readonly="readonly"/></li>
                <li>E-mail:<input name="email-alt" type="text" id="email-alt" size="50" maxlength="50" /></li>
                <li>Telefone:<input onblur="verificaTamanho(this);" name="telefone-alt" type="text" pattern="[0-9]*" id="telefone-alt" size="25" maxlength="13" onkeydown="return formataTelefone(this, event)"/></li>
                <li>Celular:<input onblur="verificaTamanho(this);" name="telefone2-alt" type="text" pattern="[0-9]*" id="telefone2-alt" size="25" maxlength="13" onkeydown="return formataTelefone(this, event)" /></li>
                <li>Endereço:<input name="endereco" readonly="readonly" type="text" id="endereco" size="25" maxlength="35"/></li>
                <li>Complemento:<input name="complemento" readonly="readonly" type="text" id="complemento" size="25" maxlength="100"/></li>
                <li>Bairro:<input name="bairro" readonly="readonly" type="text" id="bairro" size="25" maxlength="30" /></li>
                <li>Cidade:<input name="cidade" type="text" readonly="readonly" id="cidade" size="25" maxlength="13"/></li>
                <li>CEP:<input name="cep-alt" readonly="readonly" pattern="[0-9]*" type="text" id="cep-alt" size="9" maxlength="9" onkeydown="return formataCEP(this, event)" /></li>
                <li>Estado:<input name="estado" type="text" readonly="readonly" id="estado" size="25" maxlength="24"/></li>
                <li>Contrato:<span id="spanContrato">&nbsp;</span></li>
                <li>Boleto impresso:<span class="toggle"><input type="checkbox" id="recebe-boleto"/></span></li>
                <li>
                	<select name="combo-alt-data-pagamento" disabled="disabled" id="combo-alt-data-pagamento">
						<option value="" selected>Data pagamento</option>
                     	<option value="8">08 de cada mês</option>
                     	<option value="20">20 de cada mês</option>
                     	<option value="28">28 de cada mês</option>
                   	</select>
                </li>
                <li>&nbsp;</li>
                <li><button onclick="alteraUsuario()" id="bt-enviar"> &nbsp;&nbsp;Enviar&nbsp;&nbsp; </button></li>
			</ul>
            </form>
        </div>
	</body>
	<script type="text/javascript">
		carregaComboDatasPagamento();
		carregaUsuarioParaAlteraccao();
		carregaNome();
	</script>
</html>
