<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.com.meganet.util.ConfigUtil"%>
	<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
	<%@page import="java.util.List"%>
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
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/relatorios/rendimentos.js"></script>
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

<%
String dataInicial = (String) request.getSession().getAttribute("dataInicial");
String dataFinal = (String) request.getSession().getAttribute("dataFinal");
Integer comboEstado = (Integer) request.getSession().getAttribute("comboEstado");
Integer comboPesquisa = (Integer) request.getSession().getAttribute("comboPesquisa");
   if(dataInicial == null){
   	dataInicial = "";
   }
   if(dataFinal == null){
   	dataFinal = "";
   }
   if(comboEstado == null){
   	comboEstado = 0;
   }
   if(comboPesquisa == null){
   	comboPesquisa = 0;
   }
%>
<input value="<%=comboEstado %>" id="s-comboEstado" type="hidden"/>
<input value="<%=comboPesquisa %>" id="s-comboPesquisa" type="hidden"/>

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
                                                    Relatório de Boletos.
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            <!-- INICIO -->
				<div id="content-cliente" style="overflow: auto;">
                        <table width="100%" border="0">
                          <tr>
                            <td><div align="right">Data inicial:</div></td>
                            <td>
                            	<input value="<%=dataInicial %>" class="campo-data" readonly="readonly" name="dt-inicial" type="text" id="dt-inicial" size="15" maxlength="10" />
                            	<img id="bt-data-ini" src="img/calendar.gif" alt="Calendário" class="calendario" />
                            </td>
                            <td><div align="right">Data final:</div></td>
                            <td>
                            	<input value="<%=dataFinal %>" class="campo-data" readonly="readonly" name="dt-final" type="text" id="dt-final" size="15" maxlength="10" />
                            	<img id="bt-data-fin" src="img/calendar.gif" alt="Calendário" class="calendario" />
                            </td>
                            <td><div align="right">Sit. boleto:</div></td>
                            <td><select name="combo-estado" id="combo-estado">
                                <option value="0" selected="selected">Todos</option>
                                <option value="1">Pagos</option>
                                <option value="2">Abertos</option>
                            </select></td>
                          </tr>
                          <tr bgcolor="#DFE8F6">
                            <td width="15%"><div align="right">Campo de pesq.:</div></td>
                            <td width="25%"><select name="combo-campo" id="combo-campo">
                              <option value="0" selected="selected">Data vencimento</option>
                              <option value="1">Data pagamento</option>
                            </select></td>
                            <td width="15%">&nbsp;</td>
                            <td width="20%">&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>
                          
                          <tr>
                            <td>&nbsp;</td>
                            <td colspan="4"><button onClick="buscaRendimento()"> &nbsp;&nbsp;Enviar&nbsp;&nbsp; </button></td>
                          </tr>
                        </table>
                        <br>
                            <%
                        	List bol = (List) request.getSession().getAttribute("boletos");
                        	if(bol != null && bol.size() > 0) {%>
                        
                        	<table width="100%"><tr><td align="center">
								<display:table id="extratoBoleto" name="sessionScope.boletos" 
									class="report" pagesize="30" export="true" sort="list" requestURI="/rendimentos.do"
									defaultsort="5" decorator="br.com.meganet.displayTag.WrapperBoletosGeradosVO">
									<display:setProperty name="export.excel.filename"
										value="rendimentos.xls" />
									<display:setProperty name="export.pdf.filename"
										value="rendimentos.pdf" />
										
									<display:column property="usuario.usuarioNome" title="Cliente" 
										sortable="true" class="clientecss" headerClass="sortable"/>
									<display:column property="usuario.usuarioTelefone1" title="Telefone" 
										sortable="true" class="telefonecss" headerClass="sortable"/>
									<display:column property="usuario.usuarioTelefone2" title="Celular" 
										sortable="true" class="telefonecss" headerClass="sortable"/>
									<display:column property="usuario.planosPacotes.planospacotesNome" title="Plano" 
										sortable="true" class="dtPg2" headerClass="sortable"/>
									<display:column property="dataTempVencimento" title="Venc." sortable="true"
										headerClass="sortable" format="{0,date,dd/MM/yyyy}" class="venc"/>
									<display:column property="dataTempPagamento" title="Dt Pg." sortable="true"
										headerClass="sortable" format="{0,date,dd/MM/yyyy}" class="dtPg2"/>
									<display:column property="boletosgeradosValor" title="Valor" sortable="true"
										headerClass="sortable" class="pago"/>
									<display:column property="boletosgeradosValorPago" title="Valor pago" sortable="true"
										headerClass="sortable" class="pago"/>
								</display:table>
								</td></tr></table>
								<%
								}
	                        	List bolPg = (List) request.getSession().getAttribute("boletosPagos");
                        		if(bolPg != null && bolPg.size() > 0) {
                        		%>
                        
                        	<table width="100%"><tr><td align="center">
								<display:table id="extratoBoleto" name="sessionScope.boletosPagos" 
									class="report" pagesize="30" export="true" sort="list" requestURI="/rendimentos.do"
									defaultsort="5">
									<display:setProperty name="export.excel.filename"
										value="boletosPagos.xls" />
									<display:setProperty name="export.pdf.filename"
										value="boletosPagos.pdf" />
										
									<display:column property="usuario.usuarioNome" title="Cliente" 
										sortable="true" headerClass="sortable"/>
									<display:column property="usuario.planosPacotes.planospacotesNome" title="Plano" 
										sortable="true" class="planocss" headerClass="sortable"/>
									<display:column property="dataTempVencimento" title="Venc." sortable="true"
										headerClass="sortable" class="venc"/>
									<display:column property="dataTempPagamento" title="Dt Pg." sortable="true"
										headerClass="sortable" class="dtPg2"/>
									<display:column property="boletosgeradosValor" title="Valor" sortable="true"
										headerClass="sortable" class="dtPg2"/>
									<display:column property="boletosgeradosValorPago" title="Valor pago" sortable="true"
										headerClass="sortable" class="dtPg2"/>
									<display:column property="valorDeveriaSerPago" title="Valor correto" sortable="true"
										headerClass="sortable" class="dtPg2"/>
									<display:column property="valorRecebidoPeloBoletoNaConta" title="Valor na conta" sortable="true"
										headerClass="sortable" class="dtPg2"/>
								</display:table>
								</td></tr></table>
								<%} %>
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
	iniciaCalendarioFin();
	iniciaSessao();
	modalMensagem.escondeModal();
</script>
</html>