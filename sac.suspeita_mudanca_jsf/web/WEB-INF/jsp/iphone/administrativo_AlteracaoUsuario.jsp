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
		<script type="text/javascript" src="js/administrativo/alteraUsuario.js"></script>
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
                <h1>Altera usr.</h1>
                <a href="#" onclick="fnIndex();" class="back">Voltar</a>
            </div>
			<form action="#" id="form-altera-cliente" onsubmit="return false">
			<input name="id" id="id" type="hidden"/>
			<input name="latitude" id="latitude" type="hidden"/>
			<input name="longitude" id="longitude" type="hidden"/>
            <ul class="edit rounded">
            	<li>
					<input type="radio" name="tp" id="tp" value="usuarioCpf" checked="checked" onclick="limpaNome();"/> <label for="tp">CPF/CNPJ</label>
					<input type="radio" name="tp" id="tp1" value="usuarioNome" onclick="limpaNome();"/> <label for="tp1">Nome</label>
				</li>
				<li>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td><input type="text" id="cliente" name="cliente" maxlength="100" onkeyup="return formataCpfNome(this, event)"/></td>
							<td><button onclick="carregaUsuariosParaAlteraccao()"> OK </button></td>
						</tr>
					</table>
				</li>
				<li id="fieldset-resultado-busca" style="display: none;">
		            <div id="nomes-clientes-resultado">&nbsp;</div>
				</li>
                <li>MAC:<input name="mac-alt" type="text" id="mac-alt" maxlength="17" onkeydown="return formataMAC(this, event)"/></li>
                <li>Usuário:<input name="usuario-alt" type="text" readonly="readonly" id="usuario-alt" maxlength="50" /></li>
                <li>CPF/CNPJ:<input pattern="[0-9]*"  name="cpf-alt" type="text" id="cpf-alt" size="25" maxlength="14" readonly="readonly"/></li>
                <li>Telefone:<input onblur="verificaTamanho(this);" pattern="[0-9]*" name="telefone-alt" type="text" id="telefone-alt" size="25" maxlength="13" onkeydown="return formataTelefone(this, event)"/></li>
                <li>Celular:<input onblur="verificaTamanho(this);" pattern="[0-9]*" name="telefone2-alt" type="text" id="telefone2-alt" size="25" maxlength="13" onkeydown="return formataTelefone(this, event)" /></li>
                <li>CEP:<input pattern="[0-9]*" style="width: 90%" name="cep-alt" type="text" id="cep-alt" maxlength="9" onkeydown="return formataCEP(this, event)" /> <img src="img/check.png" onclick="buscaCEP();" style="cursor: pointer;"/></li>
                <li>Endereço:<input name="endereco" type="text" id="endereco" size="25" maxlength="35"/></li>
                <li>Complemento:<input name="complemento" type="text" id="complemento" size="25" maxlength="100"/></li>
                <li>Bairro:<input name="bairro" type="text" id="bairro" size="25" maxlength="30" /></li>
                <li>Cidade:<input name="cidade" type="text" id="cidade" size="25" maxlength="13"/></li>
                <li>E-mail:<input name="email-alt" type="email" id="email-alt" size="50" maxlength="50" /></li>
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
                <li id="div-data-bloqueio" style="display: none;">
                	<table width="100%" cellpadding="0" cellspacing="0" border="0">
                		<tr>
                			<td colspan="2">Data limite:</td>
                		</tr>
                		<tr>
                			<td><input class="campo-data" readonly="readonly" name="dt-limite-bloqueio" type="text" id="dt-limite-bloqueio" maxlength="10" /></td>
                			<td style="padding: 3px;"><img id="bt-dt-limite-bloqueio" src="img/calendar.gif" alt="Calendário" class="calendario" /><br/></td>
                		</tr>
                		<tr>
                			<td colspan="2"><textarea id="motivo" style="width: 100%" maxlength="150" onclick="this.value = ''">Motivo do desbloqueio</textarea></td>
                		</tr>
                	</table>
                </li>
                <li>Data ativa&ccedil;&atilde;o:<input class="campo-data" readonly="readonly" name="dt-ativacao" type="text" id="dt-ativacao" size="20" maxlength="10" /></li>
                <li>
                	<select name="combo-alt-data-pagamento" id="combo-alt-data-pagamento">
						<option value="" selected>Data pagamento</option>
                     	<option value="8">08 de cada mês</option>
                     	<option value="20">20 de cada mês</option>
                     	<option value="28">28 de cada mês</option>
                   	</select>
                </li>
                <li><div align="right" onclick="expandeRecolhe();"><b id="msg-menu-cont">+ Menus</b></div></li>
                <li><div id="menus" style="display: none">&nbsp;</div></li>
                <li><div id="map_canvas" style="width:100%; height:250px; border: #000000 1px solid"></div></li>
                
                <li>Equipamentos em comodato:</li>
                <li><div id="eqp" style="display: block">&nbsp;</div>&nbsp;</li>
                
                <li>Usuario Adm.:<span class="toggle"><input type="checkbox" id="usuario-administrativo" name="usuario-administrativo" style="margin-top: 2px"/></span></li>
                <li>&nbsp;</li>
                <li>Paga em mão:<span class="toggle"><input type="checkbox" id="usuario-paga-mao" name="usuario-paga-mao" style="margin-top: 2px"/></span></li>
                <li>&nbsp;</li>
                <li>E-mails monetários:<span class="toggle"><input type="checkbox" id="usuario-email-monetario" name="usuario-email-monetario" style="margin-top: 2px"/></span></li>
                <li>&nbsp;</li>
                <li>Altera próprio perfil:<span class="toggle"><input type="checkbox" id="altera_proprio_perfil" name="altera_proprio_perfil" style="margin-top: 2px"/></span></li>
                <li>&nbsp;</li>
                <li><button onclick="alteraUsuario()" disabled="disabled" id="bt-enviar"> &nbsp;&nbsp;Enviar&nbsp;&nbsp; </button></li>
                <li><button onclick="excluirUsuario()" disabled="disabled" id="bt-excluir"> &nbsp;&nbsp;Excluir&nbsp;&nbsp; </button></li>
			</ul>
            </form>
        </div>
	</body>
	<script type="text/javascript">
		AdministracaoJS.carregaFormularioMenu(fncarregaFormularioMenuCallback);
		AdministracaoJS.carregaFormularioEqp(fncarregaFormularioEqpCallback);
		UtilsJS.carregaComboPlano(fnCarregaComboPlanoAlteraUsrCallback);
		UtilsJS.carregaComboServidoresAtivos(fnCarregaComboServidorAlteraUsrCallback);
		carregaComboDatasPagamento();
		carregaComboEstado("select-estado");
		ContratoJS.buscaContratos(fnCarregaComboContratosCallBack);
		iniciaCalendario();
		carregaNome();
	</script>
</html>
