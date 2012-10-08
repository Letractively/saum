<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="br.com.meganet.util.ConfigUtil"%><html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link rel="icon" type="image/vnd.microsoft.icon" href="favicon.ico" />
		<link rel="shortcut icon" href="favicon.ico" />
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
		<meta name="format-detection" content="telephone=no"/>
		<link rel="apple-touch-icon" href="favicon.png"/>
		<link rel="apple-touch-icon-precomposed" href="favicon.png" />
		<title><%=ConfigUtil.getInstance().getProperty("empresa","Meganet") %></title>
		<jsp:include page="/iphone/includes.jsp"></jsp:include>
		<script type="text/javascript" src="js/demandas/gerarDemanda.js"></script>
		<script type="text/javascript" language="javascript">
	        var jQT = new $.jQTouch({
	            icon: 'jqtouch.png',
	            addGlossToIcon: false,
	            startupScreen: 'jqt_startup.png',
	            statusBar: 'black',
	            preloadImages: [
	                '../img/tempo_carregando.gif'
	                ]
	        });
	        $(function(){
	            $('body').bind('turn', function(e, data){
	                $('#orient').html('Orientation: ' + data.orientation);
	            });
	        });
		</script>
	</head>

	<body>
		<jsp:include page="/iphone/topo.jsp"></jsp:include>
        <div id="login" class="selectable1">
            <div class="toolbar">
                <h1>Demanda</h1>
                <a href="#" onclick="fnIndex();" class="back">Voltar</a>
            </div>
			<form action="#" id="form-altera-cliente" onsubmit="return false">
			<input name="id" id="id" type="hidden"/>
            <ul class="edit rounded">
            	<li>
					<input type="radio" name="tp" id="tp" value="usuarioCpf" checked="checked" onclick="limpaNome();"/> <label for="tp">CPF/CNPJ:</label>
					<input type="radio" name="tp" id="tp1" value="usuarioNome"  onclick="limpaNome();"/> <label for="tp1">Nome</label>
				</li>
				<li>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td><input type="text" id="cliente" name="cliente" maxlength="100" size="50" onkeyup="return formataCpfNome(this, event)"/></td>
							<td><button onclick="carregaUsuarios()"> OK </button></td>
						</tr>
					</table>
				</li>
				<li id="fieldset-resultado-busca" style="display: none;">
		            <div id="nomes-clientes-resultado">&nbsp;</div>
				</li>
                <li>
					<form action="#" id="form-altera-cliente" onsubmit="return false">
						<input name="id" id="id" type="hidden"/>
						<table width="100%" border="0">
						  <tr bgcolor="#DFE8F6">
						    <td><div align="left" style="font-weight: bold; float: left;">MAC:</div><div style="color: #0000FF" id="mac"></div></td>
						  </tr>
						  <tr>
						    <td><div align="left" style="font-weight: bold; float: left">Nome:</div><div class="conteudo" id="nome">&nbsp;</div></td>
						  </tr>
						  <tr bgcolor="#DFE8F6">
						    <td><div align="left" style="font-weight: bold; float: left">CPF/CNPJ:</div><div style="color: #0000FF" id="cpf">&nbsp;</div></td>
						  </tr>
						  <tr>
						    <td><div align="left" style="font-weight: bold; float: left">Telefone:</div><div style="color: #0000FF" id="telefone">&nbsp;</div></td>
						  </tr>
						  <tr bgcolor="#DFE8F6">
						    <td><div align="left" style="font-weight: bold; float: left">Celular:</div><div style="color: #0000FF" id="celular">&nbsp;</div></td>
						  </tr>
						  <tr>
						    <td><div align="left" style="font-weight: bold; float: left">Endereco:</div><span id="endereco">&nbsp;</span> - <span id="complemento">&nbsp;</span></td>
						  </tr>
						  <tr bgcolor="#DFE8F6">
						    <td><div align="left" style="font-weight: bold; float: left">Bairro:</div><div style="color: #0000FF" id="bairro">&nbsp;</div></td>
						  </tr>
						  <tr>
						    <td><div align="left" style="font-weight: bold; float: left">Cidade:</div><div style="color: #0000FF" id="cidade">&nbsp;</div></td>
						  </tr>
						  <tr bgcolor="#DFE8F6">
						    <td><div align="left" style="font-weight: bold; float: left">Estado:</div><div style="color: #0000FF" id="estado">&nbsp;</div></td>
						  </tr>
						  <tr>
					    	<td><div align="left" style="font-weight: bold; float: left">E-mail:</div><div style="color: #0000FF" id="email">&nbsp;</div></td>
						  </tr>
						</table>
					</form>
				</li>
                <li>Descrição:<textarea name="descricao-demanda" cols="80" rows="5" id="descricao-demanda"></textarea></li>
                <li>Data prevista:
                	<TABLE width="100%">
                		<TR>
                			<Td><input class="campo-data" readonly="readonly" name="data" type="text" id="data" maxlength="10" /></Td>
                			<Td width="10" style="padding-left: 2px;"><img id="bt-data" src="img/calendar.gif" alt="Calendário" class="calendario" /></Td>
                		</TR>
                	</TABLE>
                </li>
                <li>
                	<select name="hora" id="hora">
                     	<option value="" selected>Hora prevista</option>
                     	<option value="8">8</option>
                     	<option value="9">9</option>
                     	<option value="10">10</option>
                     	<option value="11">11</option>
                     	<option value="12">12</option>
                     	<option value="13">13</option>
                     	<option value="14">14</option>
                     	<option value="15">15</option>
                     	<option value="16">16</option>
                     	<option value="17">17</option>
                     	<option value="18">18</option>
                     	<option value="19">19</option>
                     	<option value="20">20</option>
                     	<option value="21">21</option>
                     	<option value="22">22</option>
                   	</select>
                </li>
                <li>
                	<select name="minuto" id="minuto">
                    	<option value="" selected>Minuto</option>
                    	<option value="00">00</option>
                    	<option value="10">10</option>
                    	<option value="20">20</option>
                    	<option value="30">30</option>
                    	<option value="40">40</option>
                    	<option value="50">50</option>
                  	</select>
                </li>
                <li>Atribuir à:<select name="responsavel" id="responsavel"></select></li>
                <li>&nbsp;</li>
                <li><button onclick="gravaDemanda()"> Enviar demanda</button></li>
			</ul>
            </form>
        </div>
	</body>
	<script type="text/javascript">
		iniciaCalendario();
		carregaNome();
		AdministracaoJS.buscaUsuariosAdministrativos(buscaUsuariosAdministrativosCallBack);
	</script>
</html>
