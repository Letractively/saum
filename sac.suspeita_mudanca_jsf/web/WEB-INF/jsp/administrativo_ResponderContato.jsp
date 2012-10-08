<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.com.meganet.util.ConfigUtil"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%
Boolean fb = (Boolean) request.getSession().getAttribute("facebook");
if(fb == null){
	fb = false;
}
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link rel="icon" type="image/vnd.microsoft.icon" href="favicon.ico" />
	<link rel="shortcut icon" href="favicon.ico" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title><%=ConfigUtil.getInstance().getProperty("empresa","Meg@net") %> - <%=ConfigUtil.getInstance().getProperty("frase_header_pagina_comum","Mikrotik") %></title>
	<jsp:include page="/includes.jsp"></jsp:include>
	
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/administrativo/responderContato.js"></script>
<%if(!"".equalsIgnoreCase(ConfigUtil.getInstance().getProperty("ID_GOOGLE_ANALYTICS", ""))){ %>
<script type="text/javascript">
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', '<%=ConfigUtil.getInstance().getProperty("ID_GOOGLE_ANALYTICS", "")%>']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
<%} %>

</head>
<body onClick="foraInter()" onkeydown="foraInterKEY(event)">
<div class="box-ajuda" id="div-cont-ajuda" style="position: absolute; width: 180px; display: none; ">
	<div id="iframe-ajuda" style="width: 174px" >&nbsp;</div>  
</div>
<script type="text/javascript">
	modalMensagem.criaMosca();
	modalMensagem.criaDivConteudo();
</script>

<div id="art-page-background-simple-gradient">
        <div id="art-page-background-gradient"></div>
    </div>
    <div id="art-page-background-glare">
        <div id="art-page-background-glare-image"></div>
    </div>
    <div id="art-main">
        <div class="art-sheet">
            <div class="art-sheet-tl"></div>
            <div class="art-sheet-tr"></div>
            <div class="art-sheet-bl"></div>
            <div class="art-sheet-br"></div>
            <div class="art-sheet-tc"></div>
            <div class="art-sheet-bc"></div>
            <div class="art-sheet-cl"></div>
            <div class="art-sheet-cr"></div>
            <div class="art-sheet-cc"></div>
            <div class="art-sheet-body">
            	<jsp:include page="/include-cabecalho-menu.jsp"></jsp:include>
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                    	<!-- menu lateral -->
                        <%if(!fb){ %><jsp:include page="/includeBoletoCPF.jsp"></jsp:include><%} %>
                    	<!-- menu lateral -->
                        <div class="art-layout-cell art-content">
                            <div class="art-post">
                                <div class="art-post-tl"></div>
                                <div class="art-post-tr"></div>
                                <div class="art-post-bl"></div>
                                <div class="art-post-br"></div>
                                <div class="art-post-tc"></div>
                                <div class="art-post-bc"></div>
                                <div class="art-post-cl"></div>
                                <div class="art-post-cr"></div>
                                <div class="art-post-cc"></div>
                                <div class="art-post-body">
                            		<div class="art-post-inner art-article">
                                            <div class="art-postmetadataheader">
                                                <h2 class="art-postheader" style="padding-left: 3px;">
                                                    Responder contato.
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            <!-- INICIO -->
				<div id="content-cliente" style="overflow: auto">
				<span id="content-client">
					<table width="100%"><tr><td align="center">
						<display:table id="demandas-tb" name="sessionScope.contato" 
							class="report" pagesize="10" export="true" sort="list" requestURI="/responderContato.do"
							defaultsort="3" decorator="br.com.meganet.displayTag.WrapperContatoVO">
							<display:setProperty name="export.excel.filename"
								value="contatos-abertos.xls" />
							<display:setProperty name="export.pdf.filename"
								value="contato-abertos.pdf" />
								
							<display:column property="contatoId" title="Num." 
								sortable="true" class="numeroDemanda" headerClass="sortable"/>
							<display:column property="contatoNome" title="Nome" sortable="true"
								headerClass="sortable"/>
							<display:column property="dataEnvio" title="Data" sortable="true"
								headerClass="sortable" class="dtPg"/>
							<display:column property="contatoTipo" title="Tipo" sortable="true"
								headerClass="sortable" class="pago"/>
							<display:column property="fechar" title="Fechar" sortable="false"
								headerClass="sortable" class="numeroDemanda"/>
						</display:table>
					</td></tr></table>
				</span>
				</fieldset>
				<!-- cliente -->
				
				<fieldset id="fieldset-reponde-contato" style="display: none">
				<legend id="legend-solicita-demanda">
				Responder
				</legend>
				<span id="content-solicita-demanda">
					<div>
						<input type="hidden" name="idContato" id="idContato"/>
                    	<table width="100%" border="0" cellspacing="1" cellpadding="0">
                          <tr>
                            <td width="22%"><div align="right" style="font-weight: bold">Mensagem do usuário:</div></td>
                            <td><textarea name="descricao-mensagem" cols="98" rows="5" id="descricao-mensagem"></textarea></td>
                          </tr>
                          <tr>
                            <td width="22%"><div align="right" style="font-weight: bold">Resposta:</div></td>
                            <td><textarea name="descricao-fechamento" cols="98" rows="5" id="descricao-fechamento"></textarea></td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                            <td>
					    		<button onclick="gravaResposta()"> Enviar resposta </button>
                            </td>
                          </tr>
                        </table>
				  	</div>
				</span>
				</fieldset>
				</div>
                                            
                                            <!-- FIM -->
                                            </div>
                                            <div class="cleared"></div>
                            		</div>
                            
                            		<div class="cleared"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%if(!fb){ %><jsp:include page="/rodape.jsp"></jsp:include><%} %>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
	loginCookie();
	modalMensagem.escondeModal();
</script>
</html>