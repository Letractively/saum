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
		<script type="text/javascript" src="js/administrativo/boleto.js"></script>
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
                <h1>Extrato</h1>
                <a href="#" onclick="fnIndex();" class="back">Voltar</a>
            </div>
            <div id="content-msg" style="visibility: hidden;">&nbsp;</div>
            <form action="#" id="form-altera-cliente" onsubmit="return false">
            <ul class="edit rounded">
                <li>
					<table width="100%"><tr><td align="center">
					<display:table id="extratoBoleto" name="sessionScope.extratoBoleto" 
						class="report" sort="list" requestURI="/extrato.do"
						defaultsort="1" decorator="br.com.meganet.displayTag.WrapperBoletosGeradosVO">
						<display:column property="boletosgeradosId" title="Num Bol" 
							sortable="true" class="numeroBoleto" headerClass="sortable"/>
						<display:column property="dataTempVencimento" title="Vencimento" sortable="true"
							headerClass="sortable" class="venc"/>
						<display:column property="dataTempPagamento" title="Data PG." sortable="true"
							headerClass="sortable" class="dtPg"/>
						<display:column property="boletosgeradosValor" title="Valor" sortable="true"
							headerClass="sortable"/>
						<display:column property="boletosgeradosValorPago" title="Valor pago" sortable="true"
							headerClass="sortable" class="dtPg"/>
					</display:table>
					</td></tr></table>
                </li>
            </ul>
            </form>
        </div>
	</body>
		<script type="text/javascript">
		carregaNome();
	</script>
	
</html>
