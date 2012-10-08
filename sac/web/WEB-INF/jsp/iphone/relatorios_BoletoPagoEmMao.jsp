<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.com.meganet.util.Util"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

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
		<script type="text/javascript" src="js/relatorios/boletoPagoEmMao.js"></script>
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
	String dataInicial = (String) request.getSession().getAttribute("dataInicial");
	String dataFinal = (String) request.getSession().getAttribute("dataFinal");
    if(dataInicial == null){
    	dataInicial = "";
    }
    if(dataFinal == null){
    	dataFinal = "";
    }
	%>
	<body>
		<jsp:include page="/iphone/topo.jsp"></jsp:include>
        <div id="login" class="selectable1">
            <div class="toolbar">
                <h1>Rel. bol. pg. mão</h1>
                <a href="#" onclick="fnIndex();" class="back">Voltar</a>
            </div>
            <div id="content-msg" style="visibility: hidden;">&nbsp;</div>
            <form action="#" id="form-altera-cliente" onsubmit="return false">
            <ul class="edit rounded">
            	<li>
            		<table width="100%" border="0" cellpadding="0" cellspacing="0">
            			<tr>
            				<td colspan="2">Data inicial</td>
            			</tr>
            			<tr>
            				<td><input class="campo-data" value="<%=dataInicial %>" readonly="readonly" name="dt-inicial" type="text" id="dt-inicial" maxlength="10" /></td>
            				<td width="20" align="right"><img id="bt-data-ini" src="img/calendar.gif" alt="Calendário" class="calendario" /></td>
            			</tr>
            		</table>
            	</li>
            	<li>
            		<table width="100%" border="0" cellpadding="0" cellspacing="0">
            			<tr>
            				<td colspan="2">Data Final</td>
            			</tr>
            			<tr>
            				<td><input class="campo-data" value="<%=dataFinal %>" readonly="readonly" name="dt-final" type="text" id="dt-final" size="20" maxlength="10" /></td>
            				<td width="20" align="right"><img id="bt-data-fin" src="img/calendar.gif" alt="Calendário" class="calendario" /></td>
            			</tr>
            		</table>
            	</li>
            	<li>&nbsp;</li>
            	<li><button onClick="buscaRelatorio()"> &nbsp;&nbsp;Enviar&nbsp;&nbsp; </button></li>
                <li>
					<table width="100%"><tr><td align="center">
						<display:table id="extratoBoleto" name="sessionScope.boletoPagoEmMao" 
							class="report" export="false" sort="list" requestURI="/relatorioBoletoPagoEmMao.do"
							defaultsort="1" decorator="br.com.meganet.displayTag.WrapperBoletosGeradosVO">
						
							<display:column property="usuario.usuarioNome" title="Cliente" sortable="true" 
								headerClass="sortable"/>
							<display:column property="boletosgeradosDataPagamento" title="PG" sortable="true"
								headerClass="sortable" format="{0,date,dd/MM/yyyy}" class="dtPg"/>
							<display:column property="boletosgeradosValorPago" title="Valor PG" sortable="true"
								headerClass="sortable" class="vlPg"/>
							<display:column property="entregueAdm" title="Entregue" sortable="true"
								headerClass="sortable" class="dtPg"/>
						</display:table>
					</td></tr></table>
                </li>
            </ul>
            </form>
        </div>
	</body>
	<script type="text/javascript">
		iniciaCalendarioIni();
		iniciaCalendarioFin();
		carregaNome();
	</script>
</html>
