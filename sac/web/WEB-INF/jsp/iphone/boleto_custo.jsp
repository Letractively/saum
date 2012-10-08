<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.com.meganet.util.Util"%>
<%@page import="br.com.meganet.util.ConfigUtil"%>
<html xmlns="http://www.w3.org/1999/xhtml">
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
		<script type="text/javascript" src="js/administrativo/custo.js"></script>
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
                <h1>Custos</h1>
                <a href="#" onclick="fnIndex();" class="back">Voltar</a>
            </div>
            <div id="content-msg" style="visibility: hidden;">&nbsp;</div>
	        <form action="#" id="form-altera-cliente" onsubmit="return false">
	        	<input name="id" id="id" type="hidden"/>
	            <ul class="rounded">
	                <li>Valor: <input pattern="[0-9]*" onfocus="limpaCampo(this);" onkeyup="return formataDecimal(this, 5);" type="text" name="valor-custo" id="valor-custo"/></li>
	                <li>
						<select name="combo-tipo" onchange="selecionaTipo()" id="combo-tipo">
  							<option value="">Tipo</option>
  						</select>
					</li>
	                <li>Motivo: <input type="text" disabled="disabled" id="motivo-custo" name="motivo-custo" maxlength="100"/></li>
	                <li>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
	            			<tr>
	            				<td colspan="2">Data:</td>
	            			</tr>
	            			<tr>
	            				<td><input class="campo-data" readonly="readonly" name="dt-inicial" type="text" id="dt-inicial" maxlength="10" /></td>
	            				<td width="20" align="right"><img id="bt-data-ini" src="img/calendar.gif" alt="Calendário" class="calendario" /></td>
	            			</tr>
	            		</table>
			        </li>
	                <li>&nbsp;</li>
	                <li><button onclick="gravaCusto()"> OK </button></li>
	            </ul>
	        </form>
		</div>
	</body>
	<script type="text/javascript">
		iniciaCalendarioIni();
		selecionaTipo();
		AdministracaoJS.carregaComboTipoCusto(carregaComboTipoCustoCallBack);
	</script>
</html>
