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
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/relatorios/lucros.js"></script>
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
                                                    Previsão de lucro.<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(15, this);"> [?]</span>
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            <!-- INICIO -->
				<div id="content-cliente" style="overflow: auto;">
                        <table width="100%" border="0">
                          <tr>
                            <td><div align="center"></div></td>
                            <td><div align="right">Selecione o mês:</div></td>
                            <td>
                            	<input class="campo-data" readonly="readonly" name="dt-inicial" type="text" id="dt-inicial" size="20" maxlength="10" />
                            	<img id="bt-data-ini" src="img/calendar.gif" alt="Calendário" class="calendario" />
                            </td>
                            <td colspan="3"><button onClick="buscaRelatorio()"> &nbsp;&nbsp;Enviar&nbsp;&nbsp; </button></td>
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
	                   <table width="100%" border="0" cellpadding="0" cellspacing="0">
	                   	  <tr><td bgcolor="#FFFFFF" colspan="4" height="2"/></tr>
						  <tr><td bgcolor="#CCCCCC" colspan="4" height="5"/></tr>
						  <tr><td bgcolor="#DFE8F6" colspan="4" height="3"/></tr>
			          	  <tr>
						    <td bgcolor="#DFE8F6" width="30%"><div align="left" style="font-weight: bold">Valor total de bol.:</div></td>
						    <td bgcolor="#DFE8F6" width="20%" style="color: blue"><div align="left" id="vl-total-bol">&nbsp;</div></td>
						    <td bgcolor="#DFE8F6" width="30%"><div align="left" style="font-weight: bold">Valor total bol. pagos:</div></td>
						    <td bgcolor="#DFE8F6" width="20%" style="color: blue"><div align="left" id="vl-bol-pagos">&nbsp;</div></td>
						  </tr>
						  <tr>
						    <td bgcolor="#FFFFFF" width=""><div align="left" style="font-weight: bold">Valor bol. pago em mão:</div></td>
						    <td bgcolor="#FFFFFF" style="color: blue"><div align="left" id="vl-bol-pagos-mao">&nbsp;</div></td>
						    <td bgcolor="#FFFFFF"><div align="left" style="font-weight: bold">Valor bol. pagos no banco:</div></td>
						    <td bgcolor="#FFFFFF" style="color: blue"><div align="left" id="vl-bol-pagos-banco">&nbsp;</div></td>
						  </tr>
						  <tr>
						    <td bgcolor="#DFE8F6"><div align="left" style="font-weight: bold">Valor gasto:</div></td>
						    <td bgcolor="#DFE8F6" style="color: blue"><div align="left" id="vl-gasto">&nbsp;</div></td>
						    <td bgcolor="#DFE8F6"><div align="left" style="font-weight: bold">Pago ao banco:</div></td>
						    <td bgcolor="#DFE8F6" style="color: blue"><div align="left" id="vl-pg-banco"></div></td>
						  </tr>
						  <tr>
						    <td bgcolor="#FFFFFF"><div align="left" style="font-weight: bold">&nbsp;</div></td>
						    <td bgcolor="#FFFFFF" style="color: blue">&nbsp;</td>
						    <td bgcolor="#FFFFFF"><div align="left" style="font-weight: bold">Valor lucro:</div></td>
						    <td bgcolor="#FFFFFF" style="color: blue"><div align="left" id="vl-lucro"></div></td>
						  </tr>
						  <tr><td bgcolor="#FFFFFF" colspan="4" height="2"/></tr>
						  <tr><td bgcolor="#CCCCCC" colspan="4" height="5"/></tr>
						  <tr><td bgcolor="#DFE8F6" colspan="4" height="3"/></tr>
			          	  <tr>
						    <td bgcolor="#DFE8F6"><div align="left" style="font-weight: bold">Qtd. bol.:</div></td>
						    <td bgcolor="#DFE8F6" style="color: blue"><div align="left" id="qtd-bol">&nbsp;</div></td>
						    <td bgcolor="#DFE8F6"><div align="left" style="font-weight: bold">&nbsp;</div></td>
						    <td bgcolor="#DFE8F6" style="color: blue"><div align="left">&nbsp;</div></td>
			          	  </tr>
						  <tr>
						    <td bgcolor="#FFFFFF"><div align="left" style="font-weight: bold">Qtd. bol. vencidos:</div></td>
						    <td bgcolor="#FFFFFF" style="color: blue"><div align="left" id="qtd-bol-venc">&nbsp;</div></td>
						    <td bgcolor="#FFFFFF"><div align="left" style="font-weight: bold">Qtd. bol. pagos:</div></td>
						    <td bgcolor="#FFFFFF" style="color: blue"><div align="left" id="qtd-bol-pago">&nbsp;</div></td>
						  </tr>
						  <tr><td bgcolor="#FFFFFF" colspan="4" height="2"/></tr>
						  <tr><td bgcolor="#CCCCCC" colspan="4" height="5"/></tr>
						  <tr><td bgcolor="#DFE8F6" colspan="4" height="3"/></tr>
						  <tr>
						    <td bgcolor="#DFE8F6"><div align="left" style="font-weight: bold">Qtd pagou certo:</div></td>
						    <td bgcolor="#DFE8F6" style="color: blue"><div id="qtd-cliente-pg-certo">&nbsp;</div></td>
						    <td bgcolor="#DFE8F6"><div align="left" style="font-weight: bold">Qtd pagou adiantado:</div></td>
						    <td bgcolor="#DFE8F6" style="color: blue"><div id="qtd-pg-adiantado">&nbsp;</div></td>
						  </tr>
						  <tr>
						    <td bgcolor="#FFFFFF"><div align="left" style="font-weight: bold">Qtd pagou atrasado:</div></td>
						    <td bgcolor="#FFFFFF" style="color: blue"><div id="qtd-pg-atrasado">&nbsp;</div></td>
						    <td bgcolor="#FFFFFF"><div align="left" style="font-weight: bold">&nbsp;</div></td>
						    <td bgcolor="#FFFFFF" style="color: blue"><div align="left">&nbsp;</div></td>
						  </tr>
						  <tr><td bgcolor="#FFFFFF" colspan="4" height="2"/></tr>
						  <tr><td bgcolor="#CCCCCC" colspan="4" height="5"/></tr>
						  <tr><td bgcolor="#DFE8F6" colspan="4" height="3"/></tr>
						</table>
					<br>
                    <div id="div-gastos" style="width:100%">&nbsp;</div>
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
	modalMensagem.escondeModal();
</script>
</html>