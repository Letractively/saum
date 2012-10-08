<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<%
Boolean fb = (Boolean) request.getSession().getAttribute("facebook");
if(fb == null){
	fb = false;
}
%>

<%@page import="br.com.meganet.util.ConfigUtil"%><html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link rel="icon" type="image/vnd.microsoft.icon" href="favicon.ico" />
		<link rel="shortcut icon" href="favicon.ico" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><%=ConfigUtil.getInstance().getProperty("frase_header_pagina_comum","Mikrotik") %></title>
		<jsp:include page="/includes.jsp"></jsp:include>
		
		<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/relatorios/localizacaoCliente.js"></script>
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
	<body onClick="foraInter()" onkeydown="foraInterKEY(event)" bgcolor="ffffff" marginwidth="0" marginheight="0">
		<div class="box-ajuda" id="div-cont-ajuda" style="position: absolute; width: 180px; display: none; ">
	<div id="iframe-ajuda" style="width: 174px" >&nbsp;</div>  
</div>
		<div id="painelGeral" onclick="fixaLocal()"></div>
		<table width="1080" border="0" cellspacing="0" cellpadding="0">
		  <tr>
		    <td height="40" colspan="2">
		    	<select name="combo-cliente" id="combo-cliente" style="width: 320px">
              		<option value="" selected>Cadastrar usuario</option>
            	</select>
		      	<button onclick="gravarPosicaoCliente();"> Enviar </button>
                <button onclick="apaga()" disabled="disabled" id="bt-apaga"> Apagar usuário </button>            
                <select name="combo-cliente2" id="combo-cliente2" style="width: 320px; margin-left:220px" onchange="localizarMapa();">
                  <option value="" selected>Localizar no mapa</option>
                </select></td>
		  </tr>
		  <tr>
		    <td width="540" align="left" valign="top">
		        <font color="red">Nome:</font> <span id="nome">&nbsp;</span><br>
		        <font color="red">Endere&ccedil;o:</font> <span id="end">&nbsp;</span><br>
		        <font color="red">Bairro:</font> <span id="bairro">&nbsp;</span>
		        <font color="red">Cidade:</font> <span id="cidade">&nbsp;</span>
	            <font color="red">CEP:</font> <span id="cep">&nbsp;</span><br>
		        <font color="red">Telefone:</font> <span id="telefone">&nbsp;</span>
		        <font color="red">Celular:</font> <span id="celular">&nbsp;</span><br>
		        <font color="red">CPF/CNPJ:</font> <span id="cpf">&nbsp;</span><br>
		    </td>
		    <td><img src="img/mapa/004/2x004005.jpg" alt="" width="270" height="100" /><img src="img/mapa/004/2x004006.jpg" alt="" width="270" height="100" /></td>
		  </tr>
		  <tr>
		    <td colspan="2" align="left" valign="top"><img src="img/mapa/005/2x005003.jpg" alt="" width="270" height="270" /><img src="img/mapa/005/2x005004.jpg" alt="" width="270" height="270" /><img src="img/mapa/005/2x005005.jpg" alt="" width="270" height="270" /><img src="img/mapa/005/2x005006.jpg" alt="" width="270" height="270" /></td>
	      </tr>
		  <tr>
		    <td colspan="2"><img src="img/mapa/006/2x006003.jpg" alt="" width="270" height="270" /><img src="img/mapa/006/2x006004.jpg" alt="" width="270" height="270" /><img src="img/mapa/006/2x006005.jpg" alt="" width="270" height="270" /><img src="img/mapa/006/2x006006.jpg" alt="" width="270" height="270" /></td>
		  </tr>
		  <tr>
		    <td colspan="2"><img src="img/mapa/007/2x007003.jpg" alt="" width="270" height="270" /><img src="img/mapa/007/2x007004.jpg" alt="" width="270" height="270" /><img src="img/mapa/007/2x007005.jpg" alt="" width="270" height="270" /><img src="img/mapa/007/2x007006.jpg" alt="" width="270" height="270" /></td>
		  </tr>
		</table>
	<div class="bottom2"><%=ConfigUtil.getInstance().getProperty("empresa","Meganet") %><span class="span-bottom2">- Todos direitos reservados (2008)</span></div>
	</body>
	<script type="text/javascript">
		AdministracaoJS.buscaClientesSemLocalizacao(buscaClientesSemLocalizacaoCallBack);
		AdministracaoJS.buscaClientesComboLocalizacao(buscaClientesComboLocalizacaoCallBack);
		AdministracaoJS.buscaClientesComLocalizacao(buscaClientesComLocalizacaoCallBack);
	</script>
</html>
