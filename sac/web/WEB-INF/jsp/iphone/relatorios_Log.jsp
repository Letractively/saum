<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.com.meganet.util.Util"%>
<%@page import="java.util.List"%>
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
		<script type="text/javascript" src="js/relatorios/log.js"></script>
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
  	String tipo = (String) request.getSession().getAttribute("tipo-rel");
  	String dtInicial = (String) request.getSession().getAttribute("dtInicial");
  	String dtFinal = (String) request.getSession().getAttribute("dtFinal");
  	
  	if(tipo == null){
  		tipo = "";
  	}
  	if(dtInicial == null){
  		dtInicial = "";
  	}
  	if(dtFinal == null){
  		dtFinal = "";
  	}
%>
	<body>
		<input value="<%=tipo %>" id="sessao-combo" type="hidden"/>
		<jsp:include page="/iphone/topo.jsp"></jsp:include>
        <div id="login" class="selectable1">
            <div class="toolbar">
                <h1>Log servidor</h1>
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
            				<td><input class="campo-data" readonly="readonly" name="dt-inicial" value="<%=dtInicial %>" type="text" id="dt-inicial" size="20" maxlength="10" /></td>
            				<td width="25" align="right"><img id="bt-data-ini" src="img/calendar.gif" alt="Calendário" class="calendario" /></td></td>
            			</tr>
            		</table>
            	</li>
            	<li>
            		<table width="100%" border="0" cellpadding="0" cellspacing="0">
            			<tr>
            				<td colspan="2">Data Final</td>
            			</tr>
            			<tr>
            				<td><input class="campo-data" readonly="readonly" name="dt-final" value="<%=dtFinal %>" type="text" id="dt-final" size="20" maxlength="10" /></td>
            				<td width="25" align="right"><img id="bt-data-fin" src="img/calendar.gif" alt="Calendário" class="calendario" /></td></td>
            			</tr>
            		</table>
            	</li>
            	<li>
            		<select name="combo-tipo" id="combo-tipo">
            			<option value="">Tipo</option>
            		</select>
            	</li>
            	<li>&nbsp;</li>
            	<li><button onClick="buscaLog()"> &nbsp;&nbsp;Enviar&nbsp;&nbsp; </button></li>
                <li>
					<%
					List log = (List) request.getSession().getAttribute("logmeganet");
					if(log != null && log.size() > 0) {%>
					<table width="100%"><tr><td align="center">
						<display:table id="extratoBoleto" name="sessionScope.logmeganet" 
							class="report" export="false" sort="list" requestURI="/relatorioLog.do"
							defaultsort="2">
							<display:column property="logData" title="Data" 
								sortable="true" class="dtPg2" format="{0,date,dd/MM/yyyy}" headerClass="sortable"/>
							<display:column property="logAcao" title="dtPg" 
								sortable="true" headerClass="sortable" class="acao"/>
							<display:column property="logDescricao" class="data" title="Descrição" 
								sortable="true" headerClass="sortable"/>
						</display:table>
					</td></tr></table>
					<%} %>
                </li>
            </ul>
            </form>
        </div>
	</body>
	<script type="text/javascript">
		iniciaCalendarioIni();
		iniciaCalendarioFin();
		carregaComboTipoLog();
		carregaNome();
	</script>
</html>
