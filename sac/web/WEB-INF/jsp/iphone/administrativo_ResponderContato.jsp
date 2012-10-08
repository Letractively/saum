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
		<script type="text/javascript" src="js/administrativo/responderContato.js"></script>
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
                <h1>Responder contato</h1>
                <a href="#" onclick="fnIndex();" class="back">Voltar</a>
            </div>
            <div id="content-msg" style="visibility: hidden;">&nbsp;</div>
            <form action="#" id="form-altera-cliente" onsubmit="return false">
            <ul class="edit rounded">
                <li>
					<table width="100%"><tr><td align="center">
						<display:table id="demandas-tb" name="sessionScope.contato" 
							class="report" pagesize="10" export="true" sort="list" requestURI="/responderContato.do"
							defaultsort="2" decorator="br.com.meganet.displayTag.WrapperContatoVO">
							<display:column property="contatoId" title="Num." 
								sortable="true" class="pg" headerClass="sortable"/>
							<display:column property="contatoNome" title="Nome" sortable="true"
								headerClass="sortable"/>
							<display:column property="dataEnvio" title="Data envio" sortable="true"
								headerClass="sortable" class="cliente"/>
							<display:column property="fechar" title="Fechar" sortable="false"
								headerClass="sortable" class="numeroDemanda"/>
						</display:table>
					</td></tr></table>
                </li>
                <span id="fieldset-reponde-contato" style="display: none;">
                	<input type="hidden" name="idContato" id="idContato"/>
                	<li><div style="width: 100%; text-align: center;">Responder</div></li>
                	<li>Msg. usr.:<textarea name="descricao-mensagem" rows="5" id="descricao-mensagem"></textarea></li>
                	<li>Resposta:<textarea name="descricao-fechamento" rows="5" id="descricao-fechamento"></textarea></li>
                	<li>&nbsp;</li>
                	<li><button onclick="gravaResposta()"> Enviar resposta </button></li>
                </span>
            </ul>
            </form>
        </div>
	</body>
	<script type="text/javascript">
		carregaNome();
	</script>
	
</html>
