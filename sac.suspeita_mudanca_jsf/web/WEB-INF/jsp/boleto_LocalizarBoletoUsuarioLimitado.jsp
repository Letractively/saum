<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.com.meganet.util.ConfigUtil"%>
<%@page import="br.com.meganet.util.Util"%>
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
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/administrativo/localizarBoleto.js"></script>
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
<%
String id = Util.acoesBusca("LocalizarBoletoView", request);
if(id == null){
	id = "";
}
%>

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
                                                    Localizar boleto.<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(34, this);"> [?]</span></span></td>
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            <!-- INICIO -->
												<div id="content-cliente" style="overflow: auto">
													<div>
														<form action="#" id="dd" onsubmit="return false">
															<label for="cliente">Número do boleto/comprovante</label>
															&nbsp;&nbsp;<input type="text" id="cliente" value="<%=id %>" name="cliente" maxlength="32" size="37" onkeydown="return soNumero(this, event)"/>
															&nbsp;&nbsp;<button onclick="carregaUsuario()" style="margin-right: 15px"> OK </button>
														</form>
												  	</div>
													<form action="#" id="form-altera-cliente" onsubmit="return false">
													<input name="id" id="id" type="hidden"/>
													<input name="tipo" id="tipo" type="hidden"/>
													<input type="hidden" id="input-temp" name="input-temp"/>
													<br/><br/>
													<table width="100%" border="0">
													  <tr bgcolor="#DFE8F6">
													    <td><div align="right">MAC:</div></td>
													    <td colspan="3"><input name="mac-alt" readonly="readonly" type="text" id="mac-alt" size="25" maxlength="17"/></td>
													  </tr>
													  <tr>
													    <td width="15%"><div align="right">Nome:</div></td>
													    <td><input name="usuario-alt" readonly="readonly" type="text" readonly="readonly" id="usuario-alt" size="50" maxlength="50" /></td>
													    <td width="18%"><div align="right">CPF/CNPJ:</div></td>
													    <td><input name="cpf-alt" readonly="readonly" type="text" id="cpf-alt" size="25" maxlength="14" readonly="readonly"/></td>
													  </tr>
													  <tr bgcolor="#DFE8F6">
													    <td><div align="right">Telefone:</div></td>
													    <td><input name="telefone-alt" readonly="readonly" type="text" id="telefone-alt" size="25" maxlength="13"/></td>
													    <td><div align="right">Celular:</div></td>
													    <td><input name="telefone2-alt" readonly="readonly" type="text" id="telefone2-alt" size="25" maxlength="13"/></td>
													  </tr>
													  <tr>
													    <td><div align="right">Endereco:</div></td>
													    <td><input name="endereco" readonly="readonly" type="text" id="endereco" size="21" maxlength="35"/><input name="complemento" readonly="readonly" type="text" id="complemento" size="23"/></td>
													    <td><div align="right">Bairro:</div></td>
													    <td><input name="bairro" readonly="readonly" type="text" id="bairro" size="25" maxlength="30" /></td>
													  </tr>
													  <tr bgcolor="#DFE8F6">
													    <td><div align="right">Cidade:</div></td>
													    <td><input name="cidade" type="text" readonly="readonly" id="cidade" size="25" maxlength="13"/>&nbsp;&nbsp;CEP: <input name="cep-alt" type="text" id="cep-alt" size="9" maxlength="9" /></td>
													    <td><div align="right">Estado:</div></td>
													    <td><input name="estado" type="text" readonly="readonly" id="estado" size="25" maxlength="24"/></td>
													  </tr>
													  <tr>
													    <td><div align="right">E-mail</div></td>
													    <td><input name="email-alt" readonly="readonly" type="text" id="email-alt" size="50" maxlength="50" /></td>
													    <td>&nbsp;</td>
													    <td>&nbsp;</td>
													  </tr>
													  <tr>
													    <td><div align="right">Plano:</div></td>
													    <td><input type="text" readonly="readonly" name="combo-alt-plano" size="25" id="combo-alt-plano"/></td>
													    <td><div align="right">Situação cliente:</div></td>
													    <td><input type="text" readonly="readonly" name="combo-cliente-bloqueado" size="25" id="combo-cliente-bloqueado"/></td>
												      </tr>
													  <tr bgcolor="#DFE8F6">
													    <td><div align="right">Data ativa&ccedil;&atilde;o:</div></td>
													    <td><input class="campo-data" readonly="readonly" name="dt-ativacao" type="text" id="dt-ativacao" size="25" maxlength="10" /></td>
													    <td><div align="right">Data Pagamento :</div></td>
													    <td><input type="text" readonly="readonly" name="combo-alt-data-pagamento" size="25" id="combo-alt-data-pagamento"/></td>
													  </tr>
													  <tr>
													    <td colspan="4" height="5"><img src="dd" width="1" height="1"/></td>
													  </tr>
													  <tr bgcolor="#DFE8F6">
													    <td colspan="4" align="center">
													    	Boleto
													    </td>
													  </tr>
													  <tr>
													    <td colspan="4" height="5"><img src="dd" width="1" height="1"/></td>
													  </tr>
													  <tr bgcolor="#DFE8F6">
													    <td><div align="right">Valor:</div></td>
													    <td><input type="text" readonly="readonly" name="bol-valor" size="25" id="bol-valor"/></td>
													    <td><div align="right">Valor Pago:</div></td>
													    <td><input type="text" readonly="readonly" name="bol-valor-pago" size="25" id="bol-valor-pago"/></td>
												      </tr>
													  <tr>
													    <td><div align="right">Data pagamento:</div></td>
													    <td><input class="campo-data" readonly="readonly" name="bol-dt-pagamento" type="text" id="bol-dt-pagamento" size="25" maxlength="10" /></td>
													    <td><div align="right">Data Vencimento:</div></td>
													    <td><input type="text" readonly="readonly" name="bol-dt-vencimento" size="25" id="bol-dt-vencimento"/></td>
													  </tr>
													  <tr bgcolor="#DFE8F6">
													    <td><div align="right">Pagou em mão:</div></td>
													    <td><input type="text" readonly="readonly" name="bol-pago-mao" size="25" id="bol-pago-mao"/></td>
													    <td><div align="right">Valor pago em mão:</div></td>
													    <td><input type="text" readonly="readonly" name="bol-valor-pago-mao" size="25" id="bol-valor-pago-mao" onkeyup="return formataDecimal(this, 5);"/></td>
												      </tr>
													</table>
												</form>
												</div>
												<input type="hidden" name="combo-alt-endereco-ip" id="combo-alt-endereco-ip"/>
												<input type="hidden" name="combo-alt-torre" id="combo-alt-torre"/>
												<input type="hidden" name="bt-inf-pag" id="bt-inf-pag"/>
												<input type="hidden" name="chk-hab-pag" id="chk-hab-pag"/>
												<input type="hidden" name="id-bol" id="id-bol"/>
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
	carregaUsuario();
	habilitaPagamento($("chk-hab-pag"));
	modalMensagem.escondeModal();
</script>
</html>