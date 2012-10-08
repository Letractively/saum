<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.com.meganet.util.ConfigUtil"%>
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
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/administrativo/custo.js"></script>
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
                                                    Manipulação de custos.<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(36, this);"> [?]</span></span></td>
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            <!-- INICIO -->
					    <div id="content-cliente" style="overflow: auto">				
							<!-- 6 -->
							<fieldset id="fieldset-cria-boleto">
								<legend id="legend-cria-boleto">
									Inserção de custo
								</legend>
								<table width="100%">
								<tr>
									<td width="80">&nbsp;Valor</td>
									<td><input onfocus="limpaCampo(this);" onkeyup="return formataDecimal(this, 5);" type="text" name="valor-custo" id="valor-custo" style="width: 160px"/></td>
								</tr>
								<tr bgcolor="#DFE8F6">
									<td>&nbsp;Tipo</td>
									<td>
										<select name="combo-tipo" onchange="selecionaTipo()" style="width: 160px" id="combo-tipo">
	            							<option value="">Tipo</option>
	            						</select>
									</td>
								</tr>
								<tr>
									<td>&nbsp;Motivo</td>
									<td><input type="text" style="width: 450px" disabled="disabled" id="motivo-custo" name="motivo-custo" maxlength="100"/></td>
								</tr>
								<tr bgcolor="#DFE8F6">
									<td>&nbsp;Data</td>
									<td>
										<input class="campo-data" readonly="readonly" name="dt-inicial" type="text" id="dt-inicial"  style="width: 160px" maxlength="10" />
			                        	<img id="bt-data-ini" src="img/calendar.gif" alt="Calendário" class="calendario" />
			                        </td>
			                    </tr>
								<tr bgcolor="#DFE8F6">
									<td>&nbsp;</td>
									<td><button onclick="gravaCusto()"> OK </button></td>
								</tr>
								</table>
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
	iniciaCalendarioIni();
	selecionaTipo();
	AdministracaoJS.carregaComboTipoCusto(carregaComboTipoCustoCallBack);
	modalMensagem.escondeModal();
</script>
</html>