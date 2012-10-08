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
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/administrativo/confBoleto.js"></script>
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
                                                    Configuração de boletos.
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            <!-- INICIO -->
			<div id="content-cliente" style="overflow: auto">
				<br/>
				<form action="#" id="form-plano" onsubmit="return false">
				<fieldset id="fieldset-altera-cliente" style="display: block">
					<legend>Banco</legend>
					<table width="100%" border="0">
					  <tr bgcolor="#DFE8F6">
                        <td width="180"><div align="right">Banco:<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(45, this);"> [?]</span></div></td>
					    <td colspan="3">
							  <select style="width: 350px" name="select-banco" id="select-banco" onchange="verificaBancoEscolhido(this)">
							  	<option value="1">(1) Banco do Brasil</option>
							  	<option value="356">(356) Banco Real</option>
							  	<option value="237">(237) Bradesco</option>
							  	<option value="104">(104) Caixa Econômica Federal</option>
							  	<option value="0">F2B</option>
							  	<option value="399">(399) HSBC</option>
							  	<option value="341">(341) Itaú</option>
							  	<option value="151">(151) Nossa Caixa</option>
							  	<option value="33">(033) Santander</option>
							  	<option value="409">(409) Unibanco</option>
							  </select>
					    </td>
				      </tr>
					</table>
				</fieldset>
				<fieldset id="fieldset-altera-cliente" style="display: block">
					<legend>Dados do boleto</legend>
					<input type="hidden" id="id-plano" name="id-plano"/>
					<table width="100%" border="0">
					  <tr>
                        <td><div align="right">Usuário F2B:<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(46, this);"> [?]</span></div></td>
					    <td colspan="3"><input name="usr" type="text" id="usr" size="60" maxlength="40" /></td>
				      </tr>
					  <tr bgcolor="#DFE8F6">
                        <td><div align="right">Senha na F2B:<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(46, this);"> [?]</span></div></td>
					    <td colspan="3"><input name="sen" type="text" id="sen" size="60" maxlength="40" /></td>
				      </tr>
					  <tr>
                        <td><div align="right">Instru&ccedil;&atilde;o 1:</div></td>
					    <td colspan="3"><input name="instrucao1" type="text" id="instrucao1" size="95" maxlength="150" /></td>
				      </tr>
					  <tr bgcolor="#DFE8F6">
					    <td><div align="right">Instru&ccedil;&atilde;o 2:</div></td>
					    <td colspan="3"><input name="instrucao2" type="text" id="instrucao2" size="95" maxlength="150" /></td>
				      </tr>
					  <tr>
                        <td><div align="right">Instru&ccedil;&atilde;o 3:</div></td>
					    <td colspan="3"><input name="instrucao3" type="text" id="instrucao3" size="95" maxlength="150" /></td>
				      </tr>
					  <tr bgcolor="#DFE8F6">
					    <td><div align="right">Local de pgto:</div></td>
					    <td colspan="3"><input name="localPagamento" type="text" id="localPagamento" size="95" maxlength="150" /></td>
				      </tr>
					  <tr>
					    <td><div align="right">Ag&ecirc;ncia:</div></td>
					    <td><input name="agencia" type="text" id="agencia" size="25" maxlength="6" /></td>
					    <td><div align="right">Conta:</div></td>
					    <td><input name="conta" type="text" id="conta" size="25" maxlength="9" /></td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td><div align="right">Nome titular:</div></td>
					    <td><input name="nome-titular" type="text" id="nome-titular" size="25" maxlength="50" /></td>
					    <td><div align="right">CNPJ:</div></td>
					    <td><input name="cnpj" type="text" onkeyup="return formataCPF(this, event)" id="cnpj" size="25" maxlength="19"/></td>
					  </tr>
					  <tr>
					    <td><div align="right">Carteira:<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(47, this);"> [?]</span></div></td>
					    <td><input name="carteira" type="text" id="carteira" size="25" maxlength="4"/></td>
					    <td><div align="right" title="Somente para HSBC, Santander e Unibanco">Cód. cliente:<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(47, this);"> [?]</span></div></td>
					    <td><input name="codigo_cliente" type="text" id="codigo_cliente" size="25" maxlength="11"/></td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td><div align="right" title="Somente para Caixa Econômica Federal">Cod. fornecido agência:<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(47, this);"> [?]</span></div></td>
					    <td><input name="codigo_fornecido_agencia" onkeydown="return soNumero(this, event)" type="text" id="codigo_fornecido_agencia" size="25" maxlength="8"/></td>
					    <td><div align="right" title="Somente para Caixa Econômica Federal">Cod. fornecido agência DV:<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(47, this);"> [?]</span></div></td>
					    <td><input name="codigo_fornecido_agencia_dv" onkeydown="return soNumero(this, event)" type="text" id="codigo_fornecido_agencia_dv" size="25" maxlength="2"/></td>
				      </tr>
					  <tr>
					    <td><div align="right" title="Somente para Caixa Econômica Federal">Código da Operação:<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(47, this);"> [?]</span></div></td>
					    <td><input name="codigo_0peracao" type="text" onkeydown="return soNumero(this, event)" id="codigo_0peracao" size="25" maxlength="4"/></td>
					    <td><div align="right" title="Somente para o Banco do Brasil">Número convênio:<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(47, this);"> [?]</span></div></td>
					    <td><input name="numeroConvenio" onkeydown="return soNumero(this, event)" type="text" id="numeroConvenio" size="25" maxlength="10"/></td>
				      </tr>
					</table>
				</fieldset>
				<fieldset id="fieldset-altera-cliente" style="display: block">
				<legend>Posições do arquivo retorno<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(48, this);"> [?]</span></legend>
					<table width="100%" border="0">
					  <tr>
					    <td><div align="right">Num. boleto INI:</div></td>
					    <td><input name="num_boleto_ini" onkeydown="return soNumero(this, event)" type="text" id="num_boleto_ini" size="25" maxlength="4"/></td>
					    <td><div align="right">Num. boleto FIM:</div></td>
					    <td><input name="num_boleto_fim" onkeydown="return soNumero(this, event)" type="text" id="num_boleto_fim" size="25" maxlength="4"/></td>
				      </tr>
					  <tr bgcolor="#DFE8F6">
					    <td><div align="right">Valor pago banco INI:</div></td>
					    <td><input name="vlr_pago_banco_ini" onkeydown="return soNumero(this, event)" type="text" id="vlr_pago_banco_ini" size="25" maxlength="4"/></td>
					    <td><div align="right">Valor pago banco FIM:</div></td>
					    <td><input name="vlr_pago_banco_fim" onkeydown="return soNumero(this, event)" type="text" id="vlr_pago_banco_fim" size="25" maxlength="4"/></td>
				      </tr>
					  <tr>
					    <td><div align="right">Valor creditado conta INI:</div></td>
					    <td><input name="valor_cred_conta_ini" onkeydown="return soNumero(this, event)" type="text" id="valor_cred_conta_ini" size="25" maxlength="4"/></td>
					    <td><div align="right">Valor creditado conta FIM:</div></td>
					    <td><input name="valor_cred_conta_fim" onkeydown="return soNumero(this, event)" type="text" id="valor_cred_conta_fim" size="25" maxlength="4"/></td>
				      </tr>
					  <tr bgcolor="#DFE8F6">
					    <td><div align="right">Data pagamento INI:</div></td>
					    <td><input name="dt_pagamento_ini" onkeydown="return soNumero(this, event)" type="text" id="dt_pagamento_ini" size="25" maxlength="4"/></td>
					    <td><div align="right">Data pagamento FIM:</div></td>
					    <td><input name="dt_pagamento_fim" onkeydown="return soNumero(this, event)" type="text" id="dt_pagamento_fim" size="25" maxlength="4"/></td>
				      </tr>
					  <tr>
					    <td><div align="right">Data pagamento máscara:</div></td>
					    <td><input name="dt_pg_mascara" type="text" id="dt_pg_mascara" size="25" maxlength="10"/></td>
					    <td>&nbsp;</td>
					    <td>&nbsp;</td>
				      </tr>
					</table>
				</fieldset>
				<table width="100%" border="0">
					<tr>
					    <td>&nbsp;</td>
					    <td colspan="3"><button onclick="gravaPlano()"> &nbsp;&nbsp;Enviar&nbsp;&nbsp; </button></td>
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
	UtilsJS.buscaDadosInfBoleto(buscaDadosInfBoletoCallBack);
	modalMensagem.escondeModal();
</script>
</html>