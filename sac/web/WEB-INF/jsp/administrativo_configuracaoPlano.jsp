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
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/administrativo/confPlano.js"></script>
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
	var reDigits = /^\d+$/;

	function doDigits(event){
		var pStr = o.value;
		if (reDigits.test(pStr)) {
			return true;
		} else {
			return false;
		}
	}
	
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
                                                    Configuração de planos.
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            <!-- INICIO -->
			<div id="content-cliente" style="overflow: auto">
				<br/>
				<fieldset id="fieldset-altera-cliente" style="display: block">
						<div>
                      <select style="width: 350px" name="select-plano" id="select-plano" onChange="carregaPlanoAlteracao()">
                        <option selected value="-1">Selecione um plano</option>
                      </select>&nbsp;&nbsp;
					  <button onclick="carregaPlanoAlteracao()"> &nbsp;&nbsp;&nbsp;Selecionar&nbsp;&nbsp;&nbsp; </button>
					  
					</div>
					</fieldset>
					<br>
				<fieldset id="fieldset-altera-cliente" style="display: block">
					<form action="#" id="form-plano" onsubmit="return false">
					<input type="hidden" id="id-plano" name="id-plano"/>
					<table width="100%" border="0">
					  <tr bgcolor="#DFE8F6">
					    <td width="16%"><div align="right">Nome:</div></td>
					    <td><input name="nome-plano" type="text" id="nome-plano" size="50" maxlength="20" /></td>
					    <td width="16%"><div align="right">Valor: </div></td>
					    <td><input name="valor-plano" type="text" id="valor-plano" onkeyup="return formataDecimal(this, 5);" size="25" maxlength="8"/></td>
					  </tr>
					  <tr>
					    <td><div align="right">Velocidade(Kb):</div></td>
					    <td><input name="velocidade-plano" type="text" onkeyup="formatarNumero(this)" id="velocidade-plano" size="50" maxlength="20" /></td>
					    <td><div align="right"><label for="bloqueia-atraso">Bloqueia cliente</label></div></td>
					    <td><input type="checkbox" id="bloqueia-atraso" name="bloqueia-atraso" style="margin-top: 4px"/></td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td><div align="right">Descri&ccedil;&atilde;o:</div></td>
					    <td><input name="descricao-plano" type="text" id="descricao-plano" size="50" maxlength="100" /></td>
					    <td><div align="right"><label for="plano-ativado"> Ativo</label></div></td>
					    <td><input type="checkbox" id="plano-ativado" name="plano-ativado" style="margin-top: 4px"/></td>
				      </tr>
					  <tr>
					    <td><div align="right">Juros:</div></td>
					    <td>
					    	&nbsp; Diário(%):<input onkeyup="return formataPercentual(this, 5);" name="juroPorMes" type="text" id="juroPorMes" size="5" maxlength="6" />
					    	Multa(%):<input onkeyup="return formataPercentual(this, 5);" name="multa" type="text" id="multa" size="5" maxlength="6" />
					    </td>
					    <td colspan="2">
						    Desconto de<input onkeyup="return formataPercentual(this, 5);" name="desconto" type="text" id="desconto" size="5" maxlength="6"/>
						    % at&eacute; <input name="diasDesconto" type="text" id="diasDesconto" size="5" maxlength="1" /> dias
                        </td>
				      </tr>
					  <tr bgcolor="#DFE8F6">
					    <td>&nbsp;</td>
					    <td colspan="3"><button onclick="gravaPlano()"> &nbsp;&nbsp;Enviar&nbsp;&nbsp; </button></td>
					  </tr>
					</table>
				</form>
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
	UtilsJS.carregaComboPlanoParaAlteracao(fnCarregaComboPlanoAlteraPlanoCallback);
	modalMensagem.escondeModal();
</script>
</html>