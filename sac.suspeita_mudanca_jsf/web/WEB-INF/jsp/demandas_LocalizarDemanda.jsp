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
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/demandas/localizarDemanda.js"></script>
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
                                                    Localizar demanda.<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(16, this);"> [?]</span>
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            <!-- INICIO -->
				<div id="content-cliente" style="overflow: auto">
					<br>
				<fieldset>
				<legend>Número da demanda</legend>
					<div>
						<%
							String id = (String) request.getParameter("id");
							if(id == null){
								id = "";
							}
						%>
						<input type="text" value="<%=id%>" id="id" name="id" maxlength="10" size="50"/>
						&nbsp;&nbsp;<button onclick="carregaDemanda()"> OK </button>
				  	</div>
					<br>
				</fieldset>
				<br>
				<fieldset>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#DFE8F6">
				    <td width="10%"><div align="right" style="font-weight: bold">Nome</div></td>
				    <td width="40%"><div id="nome">&nbsp;</div></td>
				    <td width="10%"><div align="right" style="font-weight: bold">CPF</div></td>
				    <td><div id="cpf">&nbsp;</div></td>
				  </tr>
				  <tr>
				    <td><div align="right" style="font-weight: bold">Endereço</div></td>
				    <td colspan="3"><div id="endereco">&nbsp;</div></td>
				  </tr>
				  <tr bgcolor="#DFE8F6">
				    <td><div align="right" style="font-weight: bold">Cidade</div></td>
				    <td><div id="cidade">&nbsp;</div></td>
				    <td><div align="right" style="font-weight: bold">Estado</div></td>
				    <td><div id="estado">&nbsp;</div></td>
				  </tr>
				  <tr>
				    <td><div align="right" style="font-weight: bold">Telefone</div></td>
				    <td><div id="telefone">&nbsp;</div></td>
				    <td><div align="right" style="font-weight: bold">Celular</div></td>
				    <td><div id="celular">&nbsp;</div></td>
				  </tr>
				  <tr bgcolor="#DFE8F6">
				    <td><div align="right" style="font-weight: bold">ID cliente</div></td>
				    <td><div id="idcliente">&nbsp;</div></td>
				    <td><div align="right" style="font-weight: bold">MAC</div></td>
				    <td><div id="mac">&nbsp;</div></td>
				  </tr>
				  <tr>
				    <td><div align="right" style="font-weight: bold">Servidor</div></td>
				    <td><div id="servidor">&nbsp;</div></td>
				    <td><div align="right" style="font-weight: bold">IP</div></td>
				    <td><div id="ip">&nbsp;</div></td>
				  </tr>
				</table>
				<br />
				<hr style="border: 1px dashed #000000"/>
				<br />
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#DFE8F6">
				    <td width="20%"><div align="right" style="font-weight: bold">Abertura</div></td>
				    <td width="30%"><div id="abertura">&nbsp;</div></td>
				    <td width="20%"><div align="right" style="font-weight: bold">Previsão</div></td>
				    <td><div id="previsao">&nbsp;</div></td>
				  </tr>
				  <tr>
				    <td><div align="right" style="font-weight: bold">Técnico responsável</div></td>
				    <td><div id="responsavel">&nbsp;</div></td>
				    <td><div align="right" style="font-weight: bold">Encerramento</div></td>
				    <td><div id="encerramento">&nbsp;</div></td>
				  </tr>
				  <tr bgcolor="#DFE8F6">
				    <td><div align="right" style="font-weight: bold">Abriu a demanda</div></td>
				    <td colspan="3"><div id="abriu">&nbsp;</div></td>
				  </tr>
				  <tr>
				    <td><div align="right" style="font-weight: bold">Desc. Abertura</div></td>
				    <td colspan="3"><div id="descricao-abr">&nbsp;</div></td>
				  </tr>
				  <tr bgcolor="#DFE8F6">
				    <td><div align="right" style="font-weight: bold">Desc. encerramento</div></td>
				    <td colspan="3"><div id="descricao-enc">&nbsp;</div></td>
				  </tr>
				</table>		
				<br>
				</fieldset>
				<!-- cliente -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
				  		<tr>
                            <td>&nbsp;</td>
                            <td>
					    		<button id="imprime" onclick="imprimeDemanda()" disabled="disabled"> Imprimir demanda </button>
                            </td>
                          </tr>
                        </table>
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
	carregaDemanda();
</script>
</html>