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
		<script type="text/javascript" src="js/administrativo/localizarBoleto.js"></script>
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
	<%
	String id = Util.acoesBusca("LocalizarBoletoView", request);
	if(id == null){
		id = "";
	}
	%>
	
	<body>
		<jsp:include page="/iphone/topo.jsp"></jsp:include>
        <div id="login" class="selectable1">
            <div class="toolbar">
                <h1>Localiza boleto</h1>
                <a href="#" onclick="fnIndex();" class="back">Voltar</a>
            </div>
            <div id="content-msg" style="visibility: hidden;">&nbsp;</div>
            	<form action="#" id="dd" onsubmit="return false">
		            <ul class="edit rounded">
		                <li>Numero do boleto:<input pattern="[0-9]*" type="text" id="cliente" value="<%=id %>" name="cliente" maxlength="10" onkeydown="return soNumero(this, event)"/></li>
		                <li><button onclick="carregaUsuario()" style="margin-right: 15px"> OK </button></li>
		            </ul>
		        </form>
		        <form action="#" id="form-altera-cliente" onsubmit="return false">
		        	<input name="id" id="id" type="hidden"/>
		        	<input name="tipo" id="tipo" type="hidden"/>
		            <ul class="rounded">
		                <li>MAC: <input name="mac-alt" readonly="readonly" type="text" id="mac-alt" maxlength="17"/></li>
		                <li>Nome:<input name="usuario-alt" readonly="readonly" type="text" readonly="readonly" id="usuario-alt" maxlength="50" /></li>
		                <li>CPF/CNPJ:<input name="cpf-alt" readonly="readonly" type="text" id="cpf-alt" maxlength="14" readonly="readonly"/></li>
		                <li>Telefone:<input name="telefone-alt" readonly="readonly" type="text" id="telefone-alt" maxlength="13"/></li>
		                <li>Celular:<input name="telefone2-alt" readonly="readonly" type="text" id="telefone2-alt" maxlength="13"/></li>
		                <li>Endereço:<input name="endereco" readonly="readonly" type="text" id="endereco" maxlength="35"/></li>
		                <li>Complemento:<input name="complemento" readonly="readonly" type="text" id="complemento"/></li>
		                <li>Bairro:<input name="bairro" readonly="readonly" type="text" id="bairro" maxlength="30" /></li>
		                <li>Cidade:<input name="cidade" type="text" readonly="readonly" id="cidade" maxlength="13"/>&nbsp;&nbsp;CEP: <input name="cep-alt" type="text" id="cep-alt" maxlength="9" /></li>
		                <li>Estado:<input name="estado" type="text" readonly="readonly" id="estado" maxlength="24"/></li>
		                <li>E-mail:<input name="email-alt" readonly="readonly" type="text" id="email-alt" maxlength="50" /></li>
		                <li>Plano:<input type="text" readonly="readonly" name="combo-alt-plano" id="combo-alt-plano"/></li>
		                <li>Situação cliente:<input type="text" readonly="readonly" name="combo-cliente-bloqueado" id="combo-cliente-bloqueado"/></li>
		                <li>Data ativação:<input class="campo-data" readonly="readonly" name="dt-ativacao" type="text" id="dt-ativacao" maxlength="10" /></li>
		                <li>Data pagamento:<input type="text" readonly="readonly" name="combo-alt-data-pagamento" id="combo-alt-data-pagamento"/></li>
		                <li><div style="text-align: center; width: 100%">Boleto</div></li>
		                <li>Valor:<input type="text" readonly="readonly" name="bol-valor" id="bol-valor"/></li>
		                <li>Valor pago:<input type="text" readonly="readonly" name="bol-valor-pago" id="bol-valor-pago"/></li>
		                <li>Data pagamento:<input class="campo-data" readonly="readonly" name="bol-dt-pagamento" type="text" id="bol-dt-pagamento" maxlength="10" /></li>
		                <li>Data vencimento:<input type="text" readonly="readonly" name="bol-dt-vencimento" id="bol-dt-vencimento"/></li>
		                <li>Pagou em mão:<input type="text" readonly="readonly" name="bol-pago-mao" id="bol-pago-mao"/></li>
		                <li>Valor pago em mão:<input pattern="[0-9]*" type="text" readonly="readonly" name="bol-valor-pago-mao" id="bol-valor-pago-mao" onkeyup="return formataDecimal(this, 5);"/></li>
		            </ul>
		        </form>
        </div>
		<input type="hidden" name="combo-alt-endereco-ip" id="combo-alt-endereco-ip"/>
		<input type="hidden" name="combo-alt-torre" id="combo-alt-torre"/>
		<input type="hidden" name="bt-inf-pag" id="bt-inf-pag"/>
		<input type="hidden" name="chk-hab-pag" id="chk-hab-pag"/>
		<input type="hidden" name="id-bol" id="id-bol"/>
	</body>
	<script type="text/javascript">
		carregaUsuario();
		habilitaPagamento($("chk-hab-pag"));
		carregaNome();
	</script>
</html>
