<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.com.meganet.util.ConfigUtil"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><%=ConfigUtil.getInstance().getProperty("frase_header_pagina_comum","Mikrotik") %></title>
		<jsp:include page="/includes.jsp"></jsp:include>
		<script type="text/javascript" src="js/administrativo/menu.js"></script>
		<script type="text/javascript" src="js/popUp/administraMenu.js"></script>
		<%
			String coluna = (String) request.getParameter("coluna");
		%>
	</head>

	<body bgcolor="#FFFFFF">
	<input type="hidden" id="hidden-coluna-escolhida-para-modificacao"> 
	<div style="text-align: center">
		<script language="JavaScript" type="text/javascript">
			buscaDadosMenus('<%=coluna%>');
		</script>
		<table class="report" id="tabela-altera-menu" style="width: 430px">
		    <col class="coluna-1">
    		<col class="coluna-2">
			<thead>
				<tr>
					<th>Protocolo</th>
					<th>Operador</th>
					<th>Tipo</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		</div>
		<div id="content-altera-menu" style="display: none;">
			<hr>
			<input type="hidden" id="hidden-id-menu">
			<table>
				<tr>
					<td width="125"><label for="cmp-texto" style="margin-right:10px;">Nome Apresentado: </label></td>
					<td><input type="text" id="cmp-texto" maxlength="20" style="width: 120px" /></td>
				</tr>
				<tr>
					<td><label for="cmp-funcao" style="margin-right:52px;">Função JS: </label></td>
					<td><input type="text" id="cmp-funcao" disabled="disabled" maxlength="20" style="width: 120px" /></td>
				</tr>
				<tr>
					<td><label for="cmp-checked" style="margin-right:70px;">Checado: </label></td>
					<td><input type="checkbox" id="cmp-checked" /></td>
				</tr>
				<tr>
					<td><label for="cmp-ativado" style="margin-right:79px;">Ativado: </label></td>
					<td><input type="checkbox" id="cmp-ativado" /></td>
				</tr>
				<tr>
					<td><label for="cmp-ativado" style="margin-right:79px;">Mostra Iphone: </label></td>
					<td><input type="checkbox" disabled="disabled" id="cmp-iphone" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<select name="select-altera-menu" id="select-altera-menu" onChange="alteraMenu()">
							<option selected value="0"><%=ConfigUtil.getInstance().getProperty("empresa2","Meganet") %></option>
							<option value="1">Pagamentos</option>
							<option value="2">Cadastro</option>
							<option value="3">Dúvidas</option>
							<option value="4">Clientes</option>
							<option value="5">Boletos</option>
							<option value="6">Adminsitrativo</option>
							<option value="7">Relatórios</option>
							<option value="8">Demandas</option>
							<option value="9">Painel de controle</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><button onclick="alteraMenu()">OK</button></td>
				</tr>
			</table>
		</div>
	</body>
</html>