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
		<script type="text/javascript" src="js/relatorios/lucros.js"></script>
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
        <div id="login" class="selectable1">
            <div class="toolbar">
                <h1>Lucros</h1>
                <a href="#" onclick="fnIndex();" class="back">Voltar</a>
            </div>
            <div id="content-msg" style="visibility: hidden;">&nbsp;</div>
            <form action="#" id="form-altera-cliente" onsubmit="return false">
            <ul class="edit rounded">
				<li>
            		<table width="100%" border="0" cellpadding="0" cellspacing="0">
            			<tr>
            				<td colspan="2">Selecione o mês</td>
            			</tr>
            			<tr>
            				<td><input class="campo-data" readonly="readonly" name="dt-inicial" type="text" id="dt-inicial" size="20" maxlength="10" /></td>
            				<td width="20" align="right"><img id="bt-data-ini" src="img/calendar.gif" alt="Calendário" class="calendario" /></td>
            			</tr>
            		</table>
				</li>
            	<li>&nbsp;</li>
            	<li><button onClick="buscaRelatorio()"> &nbsp;&nbsp;Enviar&nbsp;&nbsp; </button></li>
            	<li>
					<input name="id" id="id" type="hidden"/>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr bgcolor="#DFE8F6">
					    <td>
					    	<div align="left" style="font-weight: bold; float: left;">Vlr total de bol.:</div>
					    	<div style="color: #0000FF" id="vl-total-bol"></div>
					    </td>
					  </tr>
					  <tr>
					    <td>
					    	<div align="left" style="font-weight: bold; float: left">Vlr total bol. pagos:</div>
					    	<div style="color: #0000FF" id="vl-bol-pagos">&nbsp;</div>
					    </td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td>
					    	<div align="left" style="font-weight: bold; float: left;">Vlr bol. pago em mão:</div>
					    	<div style="color: #0000FF" id="vl-bol-pagos-mao"></div>
					    </td>
					  </tr>
					  <tr>
					    <td>
					    	<div align="left" style="font-weight: bold; float: left">Vlr bol. pago no banco:</div>
					    	<div style="color: #0000FF" id="vl-bol-pagos-banco">&nbsp;</div>
					    </td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td>
					    	<div align="left" style="font-weight: bold; float: left;">Vlr gasto:</div>
					    	<div style="color: #0000FF" id="vl-gasto"></div>
					    </td>
					  </tr>
					  <tr>
					    <td>
					    	<div align="left" style="font-weight: bold; float: left">Pago ao banco:</div>
					    	<div style="color: #0000FF" id="vl-pg-banco">&nbsp;</div>
					    </td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td>
					    	<div align="left" style="font-weight: bold; float: left">Valor lucro:</div>
					    	<div style="color: #0000FF" id="vl-lucro">&nbsp;</div>
					    </td>
					  </tr>
					  <tr>
					    <td>
					    	<div align="left" style="font-weight: bold; float: left;">Qtd. bol.:</div>
					    	<div style="color: #0000FF" id="qtd-bol"></div>
					    </td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td>
					    	<div align="left" style="font-weight: bold; float: left">Qtd. bol. vencidos:</div>
					    	<div style="color: #0000FF" id="qtd-bol-venc">&nbsp;</div>
					    </td>
					  </tr>
					  <tr>
					    <td>
					    	<div align="left" style="font-weight: bold; float: left;">Qtd. bol. pagos:</div>
					    	<div style="color: #0000FF" id="qtd-bol-pago"></div>
					    </td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td>
					    	<div align="left" style="font-weight: bold; float: left">Qtd pagou certo:</div>
					    	<div style="color: #0000FF" id="qtd-cliente-pg-certo">&nbsp;</div>
					    </td>
					  </tr>
					  <tr>
					    <td>
					    	<div align="left" style="font-weight: bold; float: left;">Qtd pagou adiantado:</div>
					    	<div style="color: #0000FF" id="qtd-pg-adiantado"></div>
					    </td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td>
					    	<div align="left" style="font-weight: bold; float: left">Qtd pagou atrasado:</div>
					    	<div style="color: #0000FF" id="qtd-pg-atrasado">&nbsp;</div>
					    </td>
					  </tr>
					</table>
            	</li>
            	<li><div id="div-gastos" style="width:100%">&nbsp;</div></li>
            </ul>
			</form>
        </div>
	</body>
	<script type="text/javascript">
		iniciaCalendarioIni();
		carregaNome();
	</script>
</html>
