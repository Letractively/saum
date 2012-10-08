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
	<style type="text/css">
		<!--
		table{
			border: 1px solid #000;
		}
		table tr td{
			border: 1px solid #000;
			padding:3px;
		}
		.Titulo {
			font-weight: bold;
			font-size: 36px;
			color: #000;
			text-align: center;
		}
		.subtitulo {
			font-family: "Courier New", Courier, monospace;
		}
		.ordem {
			font-weight: bold;
			font-size: xx-large;
		}
		.subcabecalho2 {
			font-weight: bold;
		}
		.titulo_quadro {
			font-weight: bold;
		}
		.pequeno {
			font-size: 9px;
		}
		-->
	</style>
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
<body>

<table width="740" border="0" align="center" cellpadding="0" cellspacing="0" style="border:0px">
  <tr>
    <td width="29%" style="border:0px"><span class="Titulo"><%=ConfigUtil.getInstance().getProperty("empresa2","Meganet") %></span><br />
    <span class="subtitulo"> <%=ConfigUtil.getInstance().getProperty("frase_header_pagina_comum","Mikrotik") %></span></td>
    <td align="center" valign="middle" class="ordem" style="border:0px; font-size: 30px;">ORDEM DE SERVIÇO</td>
  </tr>
</table>
<br />
<table width="740" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="33%"><span class="subcabecalho2">Número</span>: ${numero}</td>
    <td width="33%"><span class="subcabecalho2">Abertura</span>: ${abertura}</td>
    <td><span class="subcabecalho2">Previsão</span>: ${previsao}</td>
  </tr>
</table>
<br />
<table width="740" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="4" align="center" bgcolor="#DDDDDD"><span class="titulo_quadro">DADOS DO CLIENTE</span></td>
  </tr>
  <tr>
    <td width="10%">Nome</td>
    <td width="40%">${nome}</td>
    <td width="10%">CPF</td>
    <td>${cpf}</td>
  </tr>
  <tr>
    <td>Endereço</td>
    <td colspan="3">${endereco}</td>
  </tr>
  <tr>
    <td>Cidade</td>
    <td>${cidade}</td>
    <td>Estado</td>
    <td>${estado}</td>
  </tr>
  <tr>
    <td>Telefone</td>
    <td>${telefone}</td>
    <td>Celular</td>
    <td>${celular}</td>
  </tr>
  <tr>
    <td>ID</td>
    <td>${idcliente}</td>
    <td>MAC</td>
    <td>${mac}</td>
  </tr>
  <tr>
    <td>Servidor</td>
    <td>${servidor}</td>
    <td>IP</td>
    <td>${ip}</td>
  </tr>
</table>
<br />
<table width="740" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="4" align="center" bgcolor="#DDDDDD"><span class="titulo_quadro">DADOS DA ORDEM DE SERVIÇO</span></td>
  </tr>
  <tr>
    <td width="26%">Abertura</td>
    <td width="24%">${abertura}</td>
    <td width="26%">Previsão</td>
    <td>${previsao}</td>
  </tr>
  <tr>
    <td>Técnico responsável</td>
    <td colspan="3">${responsavel}</td>
  </tr>
  <tr>
    <td>Usuário que abriu a demanda</td>
    <td colspan="3">${abriu}</td>
  </tr>
  <tr>
    <td>Descrição</td>
    <td colspan="3">${descricao_abr}</td>
  </tr>
</table>
<br />
<table width="740" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="4" align="center" bgcolor="#DDDDDD"><span class="titulo_quadro">PREENCHER APÓS O ATENDIMENTO</span></td>
  </tr>
  <tr>
    <td colspan="4">
    	<table border="0" cellspacing="0" cellpadding="0" style="border:0px">
	    	<tr>
        		<td width="31">${defeito}</td>
		        <td width="150" style="border:0px">Defeito técnico</td>
           		<td width="31">${maluso}</td>
		        <td width="150" style="border:0px">Mal uso do eqp.</td>
           		<td width="31">${mudanca}</td>
		        <td width="150" style="border:0px">Mudança de endereço</td>
           		<td width="26">${reproduzir }</td>
		        <td width="150" style="border:0px">Erro não reproduzido</td>
      		</tr>
    	</table>
     </td>
  </tr>
  <tr>
    <td width="21%">Atendente abriu</td>
    <td width="29%">${abriu}</td>
    <td width="21%">Data</td>
    <td width="29%">${encerramento}</td>
  </tr>
  <tr>
    <td>Cliente responsável</td>
    <td colspan="2">&nbsp;</td>
    <td valign="top"><span class="pequeno">Ass</span>.</td>
  </tr>
  <tr>
    <td>Técnico que atendeu</td>
    <td colspan="2">${responsavel}</td>
    <td><span class="pequeno">Ass</span>.</td>
  </tr>
  <tr>
    <td height="85">Serviço realizado</td>
    <td colspan="3">${descricao_enc}</td>
  </tr>
</table>
<br />
<hr style="border: 1px dashed #000000"/>
<table width="740" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="4" align="center" bgcolor="#DDDDDD"><span class="titulo_quadro">COMPROVANTE DO CLIENTE</span></td>
  </tr>
  <tr>
    <td colspan="4">
    	<table border="0" cellspacing="0" cellpadding="0" style="border:0px">
	    	<tr>
        		<td width="31">${defeito}</td>
		        <td width="150" style="border:0px">Defeito técnico</td>
           		<td width="31">${maluso}</td>
		        <td width="150" style="border:0px">Mal uso do eqp.</td>
           		<td width="31">${mudanca}</td>
		        <td width="150" style="border:0px">Mudança de endereço</td>
           		<td width="26">${reproduzir }</td>
		        <td width="150" style="border:0px">Erro não reproduzido</td>
      		</tr>
    	</table>
     </td>
  </tr>
  <tr>
    <td width="21%">Atendente abriu</td>
    <td width="29%">${abriu}</td>
    <td width="21%">Data</td>
    <td width="29%">${encerramento}</td>
  </tr>
  <tr>
    <td>Cliente responsável</td>
    <td colspan="2">&nbsp;</td>
    <td valign="top"><span class="pequeno">Ass</span>.</td>
  </tr>
  <tr>
    <td>Técnico que atendeu</td>
    <td colspan="2">${responsavel}</td>
    <td><span class="pequeno">Ass</span>.</td>
  </tr>
  <tr>
    <td height="85">Serviço realizado</td>
    <td colspan="3">${descricao_enc}</td>
  </tr>
</table>
</body>
</html>
