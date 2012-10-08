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
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/administrativo/cadastroAP.js"></script>
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
                                                    Cadastro de ponto de acesso.<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(54, this);"> [?]</span>
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            <!-- INICIO -->
				<div id="content-cliente" style="overflow: auto">
						<form action="#" id="form-insere-cliente" onsubmit="return false">
					<table width="100%" border="0">
					  <tr>
					    <td width="13%"><div align="right">Nome:</div></td>
					    <td width="37%"><input name="usuario" type="text" id="usuario" size="50" maxlength="50" /></td>
					    <td width="22%"><div style="visibility: hidden" align="right">Necessita login no hotSpot:</div></td>
				      <td width="28%">
				    	<input style="visibility: hidden;" type="checkbox" name="hotspot" id="hotspot" value="1"/>
				      </td>
					  </tr>
					  <tr>
					  	<td>&nbsp;</td>
					    <td width="13%" colspan="3">
					    	<div id="div-observacao" style="color: #FF0000">&nbsp;</div>
					    </td>
					  </tr>
					  <!--tr>
					    <td><div align="right">Login:</div></td>
					    <td><input name="login" type="text" id="login" size="25" maxlength="15" /></td>
					    <td><div align="right">Senha:</div></td>
					    <td><input name="senha" type="text" id="senha" size="25" maxlength="8" /></td>
					  </tr-->
					  <tr bgcolor="#DFE8F6">
					    <td><div align="right">MAC:</div></td>
					    <td><input name="mac" type="text" id="mac" style="width: 220px" maxlength="17" onblur="verificarMAC();" onkeydown="return formataMAC(this, event)"/></td>
					    <td><div align="right">Contrato:</div></td>
					    <td>
					    	<select name="combo-contrato" id="combo-contrato" style="width: 220px">
					      		<option value="" selected="selected">&nbsp;</option>
					    	</select>
					    </td>
					  </tr>
					  <tr>
					    <td><div align="right">Servidor:</div></td>
					    <td><select name="combo-servidor" id="combo-servidor" style="width: 220px" onchange="selectTorreEnderecoIP()">
					      <option value="" selected="selected">&nbsp;</option>
					    </select></td>
					    <td><div align="right">Endereço IP:<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(55, this);"> [?]</span></div></td>
					    <td><select name="combo-endereco-ip" id="combo-endereco-ip" style="width: 220px">
					      <option value="" selected="selected">&nbsp;</option>
					    </select></td>
					  </tr>
					  <tr>
					    <td bgcolor="#DFE8F6"><div align="right">Plano:</div></td>
					    <td bgcolor="#DFE8F6"><select name="combo-plano" id="combo-plano" style="width: 220px">
					      <option value="" selected="selected">&nbsp;</option>
					    </select></td>
					    <td bgcolor="#DFE8F6">
					    	<div align="right">Situação cliente:</div>
					    </td>
					    <td bgcolor="#DFE8F6">
                            <select name="combo-cliente-bloqueado" style="width: 220px" id="combo-cliente-bloqueado">
					      		<option value="0" selected>Sem restrição</option>
					      		<option value="1">Mensagem de advertência</option>
					      		<option value="2">Bloqueado</option>
					      		<option value="3">Desativado - Não gera débito</option>
                            </select>
                        </td>
					  </tr>
					  <tr>
					    <td>&nbsp;</td>
					    <td colspan="3"><button onClick="insereUsuario(false)"> &nbsp;&nbsp;Enviar&nbsp;&nbsp; </button></td>
					  </tr>
					</table>
				</form>
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
	UtilsJS.carregaComboPlano(fnCarregaComboPlanoCallback);
	UtilsJS.carregaComboServidoresAtivos(fnCarregaComboServidoresCallback);
	ContratoJS.buscaContratos(fnCarregaComboContratosCallBack);
	modalMensagem.escondeModal();
</script>
</html>