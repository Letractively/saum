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
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/administrativo/boleto.js"></script>
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
                                                    Boletos.<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(37, this);"> [?]</span></span></td>
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            <!-- INICIO -->
		    <div id="content-cliente" style="overflow: auto">
				<!-- 1 -->
				<br/>
				<fieldset id="fieldset-imprime-boleto">
					<legend id="legend-imprime-boleto">
						Impressão de boleto por data
					</legend>
					<table width="100%">
					<tr>
					  <td width="65">&nbsp;Data </td>
					  	<td>
					  		de:&nbsp; 
					  		<input class="campo-data" readonly="readonly" name="dt-inicial" type="text" id="dt-inicial" size="15" maxlength="10" /> 
							<img id="bt-inicial" src="img/calendar.gif" alt="Calendário" class="calendario" />
					  		
					  		&nbsp;&nbsp;&nbsp;&nbsp;até:&nbsp; 
					  		<input class="campo-data" readonly="readonly" name="dt-final" type="text" id="dt-final" size="15" maxlength="10" /> 
							<img id="bt-final" src="img/calendar.gif" alt="Calendário" class="calendario" />

							<button onclick="abreCriaBoletoData()"> OK </button>
					  	</td>
					  </tr>
					</table>
				</fieldset>
				<br/>
				<!-- 2 -->
				<fieldset id="fieldset-imprime-boleto">
					<legend id="legend-imprime-boleto">
						Impressão de boleto por usuário
					</legend>
					<table width="100%">
					<tr bgcolor="#DFE8F6">
					<td width="65">&nbsp;Usuário</td>
					<td>
						<input type="radio" name="tp" id="tp" value="usuarioCpf" checked="checked" onclick="limpaNome();"/> <label for="tp">CPF/CNPJ:</label>
						<input type="radio" name="tp" id="tp1" value="usuarioNome"  onclick="limpaNome();"/> <label for="tp1">Nome</label>
						&nbsp;&nbsp;<input type="text" id="cliente" name="cliente" maxlength="90" onkeyup="return formataCpfNome(this, event)" style="width: 194px"/>
						<select style="width: 80px" name="select-mes-boleto" id="select-mes-boleto"></select>&nbsp;
						<select style="width: 60px" name="select-ano-boleto" id="select-ano-boleto"></select>&nbsp;&nbsp;
						<button onclick="abreCriaBoletoUsuario()"> OK </button>
					</td>
					</tr>
					</table>
				</fieldset>
				<br/>
				<!-- 4 -->
				<fieldset id="fieldset-cria-boleto">
					<legend id="legend-cria-boleto">
						Criar boleto para usuário<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(57, this);"> [?]</span></span>
					</legend>
					<table width="100%">
					<tr>
					<td>&nbsp;Usuário:</td>
					<td colspan="3">
						<input type="radio" name="tp-cria" id="tp-cria" value="usuarioCpf" checked="checked" onclick="limpaNomeCriaBoleto();"/> <label for="tp-cria">CPF/CNPJ:</label>
						<input type="radio" name="tp-cria" id="tp1-cria" value="usuarioNome"  onclick="limpaNomeCriaBoleto();"/> <label for="tp1-cria">Nome</label>
						&nbsp;&nbsp;<input type="text" id="cliente-cria" name="cliente-cria" maxlength="100" style="width: 280px" onkeydown="return formataCriaBoletoCpfNome(this, event)"/>					</td>
					</tr>
					<tr>
                      <td width="73">
                        <div align="right">Valor:</div></td>
					  <td width="145"><input onfocus="limpaCampo(this);" onkeyup="return formataDecimal(this, 5);" style="width: 120px"  type="text" name="valor-bol" id="valor-bol" />                      </td>
					  <td width="240"><div align="right">Data:</div></td>
					  <td><input class="campo-data" readonly="readonly" name="dt-cria-bol" type="text" id="dt-cria-bol" style="width: 120px" maxlength="10" />
                          <img id="bt-cria-bol" src="img/calendar.gif" alt="Calendário" class="calendario" /></td>
					  </tr>
					<tr>
                      <td>
                        <div align="right">Multa:</div></td>
					  <td><input onkeyup="return formataPercentual(this, 5);" name="multa" type="text" id="multa" size="5" maxlength="6"/></td>
					  <td><div align="right">Juros:</div></td>
					  <td><input onkeyup="return formataPercentual(this, 5);" name="juros" type="text" id="juros" size="5" maxlength="6"/></td>
					  </tr>
					
					<tr>
					<td>
					  <div align="right">Desconto:</div></td>
					<td><input onkeyup="return formataPercentual(this, 5);" name="desconto" type="text" id="desconto" size="5" maxlength="6"/></td>
					<td><div align="right">Limite dias para o desconto:</div></td>
					<td><input name="diasDesconto" type="text" id="diasDesconto" size="5" maxlength="1" /></td>
					</tr>
					
					<tr>
					<td>&nbsp; </td>
					<td colspan="3"><button onclick="criaBoletoExtraUsuario()" style="float: left;"> OK </button></td>
					</tr>
					</table>
				</fieldset>
				<br/>
				<!-- 5 -->
				<fieldset id="fieldset-cria-boleto">
					<legend id="legend-cria-boleto">
						Inserção de crédito ou débito
					</legend>
					<input type="hidden" id="input-temp" name="input-temp"/>
					<table width="100%">
					<tr bgcolor="#DFE8F6">
					<td width="65">Usuário: </td>
					<td>
						<input type="radio" name="tp-credito" id="credito-cpf" value="usuarioCpf" checked="checked" onclick="limpaNomeCredito();"/> <label for="credito-cpf">CPF/CNPJ:</label>
						<input type="radio" name="tp-credito" id="credito-nome" value="usuarioNome" onclick="limpaNomeCredito();"/> <label for="credito-nome">Nome</label>
						&nbsp;&nbsp;<input type="text" id="credito-cliente" name="credito-cliente" maxlength="100" size="50" onkeydown="return formataCreditoCpfNome(this, event)"/>
						&nbsp;&nbsp;<button onclick="buscaUsuarioCredito()"> OK </button>
					</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="hidden" id="id-boleto-1"/>
							<input type="hidden" id="id-boleto-2"/>
							<input type="hidden" id="id-boleto-3"/>
							<div style="display: none" id="conteiner-credito">
							<table width="100%" border="0" cellspacing="2" cellpadding="2">
							  <tr style="padding-bottom: 5px;">
							    <td><div align="left" id="cpf">&nbsp;</div></td>
							    <td colspan="2"><div align="left" id="nome">&nbsp;</div></td>
							    <td><div align="left" id="endereco">&nbsp;</div></td>
							  </tr>
							  <tr bgcolor="#E5E5E5">
							    <td width="17%"><div align="center" style="font-weight: bold">Data pg.</div></td>
							    <td width="20%"><div align="center" style="font-weight: bold">Valor</div></td>
							    <td width="30%"><div align="center" style="font-weight: bold">Crédito/débito</div></td>
							    <td width="30%"><div align="center" style="font-weight: bold">Motivo</div></td>
							  </tr>
							  <tr bgcolor="#F4F4FF">
							    <td><div align="left" style="padding-left: 5px; padding-top: 5px" id="dt-1">&nbsp;</div></td>
							    <td><div align="left" style="padding-left: 5px; padding-top: 5px" id="valor-1">&nbsp;</div></td>
							    <td>&nbsp;&nbsp;<input disabled="disabled" size="10" onkeyup="return formataDecimal(this, 5);" type="text" name="rest-1" id="rest-1" />&nbsp;&nbsp;<input disabled="disabled" name="debito-1" type="checkbox" value="debito-1" id="debito-1"/><label for="debito-1">&nbsp;&nbsp;é crédito</label></td>
							    <td>&nbsp;&nbsp;<input disabled="disabled" type="text" name="motivo-1" id="motivo-1" size="30" maxlength="99"/></td>
							  </tr>
							  <tr bgcolor="#F4F4FF">
							    <td><div align="left" style="padding-left: 5px; padding-top: 5px; height: 20px" id="dt-2">&nbsp;</div></td>
							    <td><div align="left" style="padding-left: 5px; padding-top: 5px; height: 20px" id="valor-2">&nbsp;</div></td>
							    <td>&nbsp;&nbsp;<input disabled="disabled" size="10" onkeyup="return formataDecimal(this, 5);" type="text" name="rest-2" id="rest-2" />&nbsp;&nbsp;<input disabled="disabled" name="debito-2" type="checkbox" value="debito-2" id="debito-2"/><label for="debito-2">&nbsp;&nbsp;é crédito</label></td>
							    <td>&nbsp;&nbsp;<input disabled="disabled" type="text" name="motivo-2" id="motivo-2" size="30" maxlength="99"/></td>
							  </tr>
							  <tr bgcolor="#F4F4FF">
							    <td><div align="left" style="padding-left: 5px; padding-top: 5px" id="dt-3">&nbsp;</div></td>
							    <td><div align="left" style="padding-left: 5px; padding-top: 5px" id="valor-3">&nbsp;</div></td>
							    <td>&nbsp;&nbsp;<input disabled="disabled" size="10" onkeyup="return formataDecimal(this, 5);" type="text" name="rest-3" id="rest-3" />&nbsp;&nbsp;<input disabled="disabled" name="debito-3" type="checkbox" value="debito-3" id="debito-3"/><label for="debito-3">&nbsp;&nbsp;é crédito</label></td>
							    <td>&nbsp;&nbsp;<input disabled="disabled" type="text" name="motivo-3" id="motivo-3" size="30" maxlength="99"/></td>
							  </tr>
							  <tr>
							    <td colspan="4" style="padding-top: 10px;"><button onclick="gravaResticio()"> Gravar crédito/débito </button></td>
							  </tr>
							</table>
           					</div>
						</td>
					</tr>
					</table>
				</fieldset>
				
                <!-- 6 -->
                <fieldset id="fieldset-resultado-busca" style="display: none;">
					<legend id="legend-resultado-busca">
						Resultado da busca
					</legend>
                    <div id="nomes-clientes-resultado"></div>
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
	carregaComboAno("select-ano-boleto");
	iniciaCalendario();
	carregaComboMes("select-mes-boleto");
	modalMensagem.escondeModal();
</script>
</html>