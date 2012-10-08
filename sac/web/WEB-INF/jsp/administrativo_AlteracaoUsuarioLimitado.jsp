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
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/administrativo/alteraUsuarioLimitado.js"></script>
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
                                                    Alteração de dados.<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(41, this);"> [?]</span>
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            <!-- INICIO -->
				<div id="content-cliente" style="overflow: auto">
					<form action="#" id="form-altera-cliente" onsubmit="return false">
					<input name="id" id="id" type="hidden"/>
					<table width="100%" border="0">
					  
					  <tr>
					    <td width="15%"><div align="right">Nome:</div></td>
					    <td><input name="usuario-alt" type="text" id="usuario-alt" readonly="readonly" size="50" maxlength="50" /></td>
					    <td width="15%"><div align="right">CPF/CNPJ:</div></td>
					    <td><input name="cpf-alt" readonly="readonly" type="text" id="cpf-alt" size="25" maxlength="14"/></td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td><div align="right">Telefone:</div></td>
					    <td><input name="telefone-alt" onblur="verificaTamanho(this);" type="text" id="telefone-alt" size="25" maxlength="13" onkeydown="return formataTelefone(this, event)"/></td>
					    <td><div align="right">Celular:</div></td>
					    <td><input name="telefone2-alt" onblur="verificaTamanho(this);" type="text" id="telefone2-alt" size="25" maxlength="13" onkeydown="return formataTelefone(this, event)" /></td>
					  </tr>
					  <tr>
					    <td><div align="right">End./Complemento:</div></td>
					    <td><input name="endereco" readonly="readonly" type="text" id="endereco" size="22" maxlength="35"/>&nbsp;<input name="complemento" readonly="readonly" type="text" id="complemento" size="25" maxlength="100"/></td>
					    <td><div align="right">Bairro:</div></td>
					    <td><input name="bairro" readonly="readonly" type="text" id="bairro" size="25" maxlength="30" /></td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td><div align="right">Cidade:</div></td>
					    <td>
					    	<input name="cidade" readonly="readonly" type="text" id="cidade" size="25" maxlength="13"/>
					    	&nbsp;&nbsp;CEP: <input name="cep-alt" readonly="readonly" type="text" id="cep-alt" size="15" maxlength="9" onkeydown="return formataCEP(this, event)" />					    </td>
					    <td><div align="right">Estado:</div></td>
					    <td><input name="estado" readonly="readonly" disabled="disabled" type="text" id="estado" size="25" maxlength="24"/></td>
					  </tr>
					  <tr>
					    <td><div align="right">E-mail</div></td>
					    <td><input name="email-alt" type="text" id="email-alt" size="50" maxlength="50" /></td>
					    <td><div align="right">Contrato:</div></td>
					    <td><span id="spanContrato">&nbsp;</span></td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td><div align="right">Data Pagamento :</div></td>
					    <td><select name="combo-alt-data-pagamento" id="combo-alt-data-pagamento">
                            <option value="" selected>&nbsp;</option>
                            <option value="8">08 de cada m&ecirc;s</option>
                            <option value="20">20 de cada m&ecirc;s</option>
                            <option value="28">28 de cada m&ecirc;s</option>
                          </select>
                        </td>
					    <td><div align="right">Boleto impresso:<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(42, this);"> [?]</span> </div></td>
					    <td><input type="checkbox" id="recebe-boleto"/></td>
					  </tr>
					  <tr>
					    <td>&nbsp;</td>
					    <td colspan="4"><button onclick="alteraUsuario()" id="bt-enviar"> &nbsp;&nbsp;Enviar&nbsp;&nbsp; </button></td>
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
	carregaUsuarioParaAlteraccao();
	carregaComboDatasPagamento();
	modalMensagem.escondeModal();
</script>
</html>