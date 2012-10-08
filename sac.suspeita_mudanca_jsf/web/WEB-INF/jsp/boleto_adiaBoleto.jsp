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
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/administrativo/adiaBoleto.js"></script>
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
                                                    Adiar boletos.<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(40, this);"> [?]</span></span></td>
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            <!-- INICIO -->
		    <div id="content-cliente" style="overflow: auto">
				<!-- 1 -->
				<br/>
				<fieldset id="fieldset-imprime-boleto">
					<legend id="legend-imprime-boleto">
						Adiar boleto por data de vencimentos.
					</legend>
					<table width="100%">
					<tr>
					  <td width="85">&nbsp;Data pesquisa</td>
					  	<td>
					  		<input class="campo-data" readonly="readonly" name="dt-inicial" type="text" id="dt-inicial" style="width: 132px;" maxlength="10" /> 
							<img id="bt-inicial" src="img/calendar.gif" alt="Calendário" class="calendario" />
					  		
					  		&nbsp;&nbsp;&nbsp;&nbsp;até:&nbsp; 
					  		<input class="campo-data" readonly="readonly" name="dt-final" type="text" id="dt-final" style="width: 132px;" maxlength="10" /> 
							<img id="bt-final" src="img/calendar.gif" alt="Calendário" class="calendario" />
					  	</td>
					  </tr>
					  <tr>
					  <td width="85">&nbsp;Nova data </td>
					  	<td>
                            <input class="campo-data" readonly="readonly" name="dt-adiar-venc" type="text" id="dt-adiar-venc" style="width: 132px;" maxlength="10" /> 
							<img id="bt-dt-adiar-venc" src="img/calendar.gif" alt="Calendário" class="calendario" /><br/>
					  	</td>
					  </tr>
					  <tr>
					  <td width="80">&nbsp;</td>
					  	<td>
							<button onclick="adiaBoletoData()"> Gravar </button>
					  	</td>
					  </tr>
					</table>
				</fieldset>
				<br/>
				<!-- 2 -->
				<fieldset id="fieldset-cria-boleto">
					<legend id="legend-cria-boleto">
						Adiar boleto por usuário.
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
							    <td colspan="2"><div align="left" id="cpf">&nbsp;</div></td>
							    <td><div align="left" id="nome">&nbsp;</div></td>
							    <td><div align="left" id="endereco">&nbsp;</div></td>
							  </tr>
							  <tr bgcolor="#E5E5E5">
							    <td>&nbsp;</td>
							    <td width="20%" align="center"><div align="center" style="font-weight: bold">Data venc.</div></td>
							    <td align="center"><div align="center" style="font-weight: bold">Valor</div></td>
							    <td align="center" width="40%"><div align="center" style="font-weight: bold">Nova data</div></td>
							  </tr>
							  <tr height="28" bgcolor="#F4F4FF">
							    <td width="3" align="center"><input checked="checked" onclick="seleciona(1);" type="radio" name="rd-alt" id="rd-1" value="1"/></td>
							    <td><label for="rd-1"><div align="left" style="padding-left: 5px; padding-top: 5px" id="dt-1">&nbsp;</div></label></td>
							    <td><div align="left" style="padding-left: 5px; padding-top: 5px" id="valor-1">&nbsp;</div></td>
							    <td><div id="dv-dt-1">
									<input class="campo-data" readonly="readonly" name="dt-adiar-usr-1" type="text" id="dt-adiar-usr-1" style="width: 132px;" maxlength="10" /> 
									<img id="bt-dt-adiar-usr-1" src="img/calendar.gif" alt="Calendário" class="calendario" /><br/>
								</div></td>
							  </tr>
							  <tr height="28" bgcolor="#F4F4FF">
							    <td align="center"><input onclick="seleciona(2);" type="radio" name="rd-alt" id="rd-2" value="2"/></td>
							    <td><label for="rd-2"><div align="left" style="padding-left: 5px; padding-top: 5px; height: 20px" id="dt-2">&nbsp;</div></label></td>
							    <td><div align="left" style="padding-left: 5px; padding-top: 5px; height: 20px" id="valor-2">&nbsp;</div></td>
							    <td><div id="dv-dt-2" style="display: none;">
									<input class="campo-data" readonly="readonly" name="dt-adiar-usr-2" type="text" id="dt-adiar-usr-2" style="width: 132px;" maxlength="10" /> 
									<img id="bt-dt-adiar-usr-2" src="img/calendar.gif" alt="Calendário" class="calendario" /><br/>
							    </div></td>
							  </tr>
							  <tr height="28" bgcolor="#F4F4FF">
							    <td align="center"><input onclick="seleciona(3);" type="radio" name="rd-alt" id="rd-3" value="3"/></td>
							    <td><label for="rd-3"><div align="left" style="padding-left: 5px; padding-top: 5px" id="dt-3">&nbsp;</div></label></td>
							    <td><div align="left" style="padding-left: 5px; padding-top: 5px" id="valor-3">&nbsp;</div></td>
							    <td><div id="dv-dt-3" style="display: none;">
									<input class="campo-data" readonly="readonly" name="dt-adiar-usr-3" type="text" id="dt-adiar-usr-3" style="width: 132px;" maxlength="10" /> 
									<img id="bt-dt-adiar-usr-3" src="img/calendar.gif" alt="Calendário" class="calendario" /><br/>
							    </div></td>
							  </tr>
							  <tr>
							    <td colspan="3" style="padding-top: 10px;"><button onclick="adiaBoletoUsuario()"> Gravar </button></td>
							  </tr>
							</table>
           					</div>
						</td>
					</tr>
					</table>
				</fieldset>
				<br/>
				<!-- 4 -->
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
	iniciaCalendario();
	modalMensagem.escondeModal();
</script>
</html>