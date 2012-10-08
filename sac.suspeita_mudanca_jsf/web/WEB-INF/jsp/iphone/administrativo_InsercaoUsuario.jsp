<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.com.meganet.util.Util"%>

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
		<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
		<script type="text/javascript" src="js/administrativo/insereUsuario.js"></script>
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

	<body onload="initialize('<%=ConfigUtil.getInstance().getProperty("latIni","1") %>','<%=ConfigUtil.getInstance().getProperty("latFim","1") %>', '<%=ConfigUtil.getInstance().getProperty("msgMap","Meganet") %>', <%=ConfigUtil.getInstance().getProperty("zoom_mapa","8") %>);">
		<jsp:include page="/iphone/topo.jsp"></jsp:include>
		<div id="login" class="selectable1">
            <div class="toolbar">
                <h1>Insere usr.</h1>
                <a href="#" onclick="fnIndex();" class="back">Voltar</a>
            </div>
			<form action="#" id="form-cliente" onsubmit="return false">
			<input name="id" id="id" type="hidden"/>
			<input name="latitude" id="latitude" type="hidden"/>
			<input name="longitude" id="longitude" type="hidden"/>
            <ul class="edit rounded">
				<li id="fieldset-resultado-busca" style="display: none;">
		            <div id="nomes-clientes-resultado">&nbsp;</div>
				</li>
                <li><select name="combo-mac" id="combo-mac" onchange="selecionaMac();"></select></li>
                <li>Usuário:<input name="usuario" type="text" readonly="readonly" id="usuario" maxlength="50" /></li>
                <li>CPF/CNPJ:<input name="cpf" pattern="[0-9]*" type="text" id="cpf" maxlength="18" onkeyup="return formataCPF(this, event)" onblur="return formataCPF(this, event)"/></li>
                <li>Telefone:<input onblur="verificaTamanho(this);" name="telefone" pattern="[0-9]*" type="text" id="telefone" maxlength="13" onkeydown="return formataTelefone(this, event)"/></li>
                <li>Celular:<input onblur="verificaTamanho(this);" name="telefone2" pattern="[0-9]*" type="text" id="telefone2" maxlength="13" onkeydown="return formataTelefone(this, event)" /></li>
                <li>CEP:<br><input style="width:95%" name="cep" pattern="[0-9]*" type="text" id="cep" maxlength="9" onkeydown="return formataCEP(this, event)"/><img src="img/check.png" onclick="buscaCEP();" style="cursor: pointer;"/></li>
                <li>Endereço:<input name="endereco" type="text" id="endereco" maxlength="35"/></li>
                <li>Complemento:<input name="complemento" type="text" id="complemento" maxlength="100"/></li>
                <li>Bairro:<input name="bairro" type="text" id="bairro" maxlength="30" /></li>
                <li>Cidade:<input name="cidade" type="text" id="cidade" maxlength="13"/></li>
                <li>E-mail:<input name="email" type="email" id="email" maxlength="50" /></li>
                <li><select name="select-estado" id="select-estado"></select></li>
                <li>
                	<select name="combo-contrato" id="combo-contrato">
   						<option value="" selected>Contrato</option>
					</select>
				</li>
                <li>
                	<select name="combo-alt-servidor" id="combo-alt-servidor" onchange="selectTorreEnderecoIP();">
                        <option value="" selected="selected">Servidor</option>
                    </select>
                </li>
                <li>
                	<select name="combo-alt-endereco-ip" id="combo-alt-endereco-ip">
					  <option value="" selected="selected">Endereço IP</option>
					</select>
                </li>
                <li>
                	<select name="combo-alt-plano" id="combo-alt-plano">
					  <option value="" selected="selected">Plano</option>
					</select>
                </li>
                <li>
                 	<select name="combo-cliente-bloqueado" id="combo-cliente-bloqueado" onchange="verificaDataLimiteClientePagamentoAtrasado()">
			      		<option value="0" selected>Sem restrição</option>
			      		<option value="1">Mensagem de advertência</option>
			      		<option value="2">Bloqueado</option>
			      		<option value="3">Desativado - Não gera débito</option>
                	</select>
                </li>
                <li>
               		<table width="100%" border="0" cellpadding="0" cellspacing="0">
            			<tr>
            				<td colspan="2">Data ativa&ccedil;&atilde;o:</td>
            			</tr>
            			<tr>
            				<td><input class="campo-data" readonly="readonly" name="dt-ativacao" type="text" id="dt-ativacao" size="20" maxlength="10" /></td>
            				<td width="20" align="right"><img id="bt-data" src="img/calendar.gif" alt="Calendário" class="calendario" /></td>
            			</tr>
            		</table>
				</li>
                <li>
                	<select name="combo-data-pagamento" id="combo-data-pagamento">
						<option value="" selected>Data pagamento</option>
                     	<option value="8">08 de cada mês</option>
                     	<option value="20">20 de cada mês</option>
                     	<option value="28">28 de cada mês</option>
                   	</select>
                </li>
                <li><div id="map_canvas" style="width:100%; height:250px; border: #000000 1px solid"></div></li>
                
                <li><div onclick="expandeRecolhe();" style="text-align: left;"><b id="msg-menu-cont">+ Menus</div></li>
                <li><div id="menus" style="display: none">&nbsp;</div>&nbsp;</li>
                
                <li>Equipamentos em comodato:</li>
                <li><div id="eqp" style="display: block">&nbsp;</div>&nbsp;</li>
                
                <li>Usuario Adm.:<span class="toggle"><input type="checkbox" id="usuario-administrativo" name="usuario-administrativo" style="margin-top: 2px"/></span></li>
                <li>&nbsp;</li>
                <li>&nbsp;</li>
                <li>Altera próprio perfil:<span class="toggle"><input type="checkbox" id="altera_proprio_perfil" name="altera_proprio_perfil" style="margin-top: 2px"/></span></li>
                <li><button onclick="insereUsuario()" id="bt-enviar"> &nbsp;&nbsp;Enviar&nbsp;&nbsp; </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="excluirUsuario()" id="bt-excluir"> &nbsp;&nbsp;Excluir&nbsp;&nbsp; </button></li>
			</ul>
            </form>
        </div>
	</body>
	<script type="text/javascript">
		UtilsJS.carregaComboMAC(fncarregaComboMACCallback);
		AdministracaoJS.carregaFormularioMenu(fncarregaFormularioMenuCallback);
		AdministracaoJS.carregaFormularioEqp(fncarregaFormularioEqpCallback);
		carregaComboDatasPagamento();
		carregaComboEstado("select-estado");
		iniciaCalendario();
		carregaNome();
	</script>
</html>
