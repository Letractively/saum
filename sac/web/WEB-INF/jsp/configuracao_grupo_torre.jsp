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
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/configuracao/grupotorre.js"></script>

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
                                                    Configuração de Grupos de torres.<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(26, this);"> [?]</span>
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            <!-- INICIO -->
							<div id="content-cliente" style="overflow: auto">
								<br/>
								<input type="hidden" name="tipo" id="tipo" value="contato"> 
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
        		                  <tr>
                                 	<td width="120"><div align="right">Servidor:</div></td>
					    			<td>
					    				<select name="combo-alt-serv" style="width: 155px;" id="combo-alt-serv" onchange="buscaDadosServidores();">
                            				<option value="" selected="selected">&nbsp;</option>
                        				</select>
                        				<button onclick="alterar()" disabled="disabled" id="bt-alterar"> &nbsp;&nbsp;Alterar&nbsp;&nbsp; </button>
                        				<button onclick="cancela()" disabled="disabled" id="bt-cancelar"> &nbsp;&nbsp;Cancelar&nbsp;&nbsp; </button>
                        				<button onclick="novo()" id="bt-novo"> &nbsp;&nbsp;Novo servidor&nbsp;&nbsp; </button>
                        			</td>
                          		  </tr>
		                        </table>
		                        <br/>
		                        <input type="hidden" id="id"/>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  			  <tr bgcolor="#DFE8F6">
								    <td width="120"><div align="right">Nome:<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(27, this);"> [?]</span></div></td>
								    <td><input name="nome" type="text" disabled="disabled" id="nome" style="width: 588px" maxlength="30"/></td>
                          		  </tr>
					  			  <tr>
								    <td><div align="right">Descriçao do local:</div></td>
								    <td><input name="local" type="text" disabled="disabled" id="local" style="width: 588px" maxlength="20"/></td>
                          		  </tr>
					  			  <tr bgcolor="#DFE8F6">
								    <td><div align="right">Descrição:</div></td>
								    <td><input name="descricao" type="text" disabled="disabled" id="descricao" style="width: 588px" maxlength="5"/></td>
                          		  </tr>
					  			  <tr>
								    <td><div align="right">Situação:</div></td>
								    <td>
			                            <select name="situacao" disabled="disabled" style="width: 155px;" id="situacao">
								      		<option value="false" selected>Ativado</option>
								      		<option value="true">Desativado</option>
			                            </select>
									</td>
                          		  </tr>
                          		  <tr bgcolor="#DFE8F6">
								    <td><div align="right">Torres do grupo:<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(28, this);"> [?]</span></div></td>
								    <td colspan="4"><br><div id="torres" style="display: none">&nbsp;</div></td>
								  </tr>
								  <tr>
								    <td>&nbsp;</td>
								    <td colspan="4"><button onclick="gravaAlteracao()" disabled="disabled" id="bt-enviar"> &nbsp;&nbsp;Enviar&nbsp;&nbsp; </button></td>
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
	cancela();
	UtilsJS.carregaComboTodosServidores(fnCarregaComboServidorAlteraCallback);
	UtilsJS.carregaComboTorre(fncarregaFormularioTorresCallback);
	modalMensagem.escondeModal();
</script>
</html>