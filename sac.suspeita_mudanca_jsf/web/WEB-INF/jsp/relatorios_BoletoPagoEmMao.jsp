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
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/relatorios/boletoPagoEmMao.js"></script>
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
   if(dataInicial == null){
   	dataInicial = "";
   }
   if(dataFinal == null){
   	dataFinal = "";
   }
%>

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
                                                    Boletos pagos em mão.<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(7, this);"> [?]</span>
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            <!-- INICIO -->
                                            
												<div id="content-cliente" style="overflow: auto">
								                        <table width="100%" border="0">
								                          <tr>
								                            <td><div align="center"></div></td>
								                            <td><div align="right">Data inicial:</div></td>
								                            <td>
								                            	<input class="campo-data" value="<%=dataInicial %>" readonly="readonly" name="dt-inicial" type="text" id="dt-inicial" size="20" maxlength="10" />
								                            	<img id="bt-data-ini" src="img/calendar.gif" alt="Calendário" class="calendario" />
								                            </td>
								                            <td><div align="right">Data final:</div></td>
								                            <td>
								                            	<input class="campo-data" value="<%=dataFinal %>" readonly="readonly" name="dt-final" type="text" id="dt-final" size="20" maxlength="10" />
								                            	<img id="bt-data-fin" src="img/calendar.gif" alt="Calendário" class="calendario" />
								                            </td>
								                          </tr>
								                          <tr>
								                            <td><div align="center"></div></td>
								                            <td>&nbsp;</td>
								                            <td colspan="5"><button onClick="buscaRelatorio()"> &nbsp;&nbsp;Enviar&nbsp;&nbsp; </button></td>
								                          </tr>
								                        </table>
									                     <fieldset id="fieldset-resultado-busca" style="display: none;">
															<legend id="legend-resultado-busca">
																Resultado da busca
															</legend>
										                    <div id="nomes-clientes-resultado">
										                    </div>
														</fieldset>
								                        <br>
								                        	<table width="100%"><tr><td align="center">
																<display:table id="extratoBoleto" name="sessionScope.boletoPagoEmMao" 
																	class="report" pagesize="30" export="true" sort="list" requestURI="/relatorioBoletoPagoEmMao.do"
																	defaultsort="1" decorator="br.com.meganet.displayTag.WrapperBoletosGeradosVO">
																	<display:setProperty name="export.excel.filename"
																		value="boletoPagoMao.xls" />
																	<display:setProperty name="export.pdf.filename"
																		value="boletoPagoMao.pdf" />
								
																	<display:column property="usuario.usuarioNome" title="Cliente" sortable="true" 
																		headerClass="sortable"/>
																	<display:column property="usuario.usuarioTelefone1" title="Telefone" sortable="true"
																		headerClass="sortable" class="telefonecss"/>
																	<display:column property="boletosgeradosDataPagamento" title="Pagamento" sortable="true"
																		headerClass="sortable" format="{0,date,dd/MM/yyyy}" class="dtPg2"/>
																	<display:column property="boletosgeradosDataVencimento" title="Vencimento" sortable="true"
																		headerClass="sortable" format="{0,date,dd/MM/yyyy}" class="dtPg2"/>
																	<display:column property="boletosgeradosValorPago" title="Valor Pago" sortable="true"
																		headerClass="sortable" class="dtPg2"/>
																	<display:column property="entregueAdm" title="Entregue Adm" sortable="true"
																		headerClass="sortable" class="dtPg2"/>
																</display:table>
															</td></tr></table>
														<br>
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
	modalMensagem.escondeModal();
</script>
</html>