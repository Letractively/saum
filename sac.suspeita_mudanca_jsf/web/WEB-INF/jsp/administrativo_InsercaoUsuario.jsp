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
	<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/administrativo/insereUsuario.js"></script>
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
<body onClick="foraInter()" onkeydown="foraInterKEY(event)" onload="initialize('<%=ConfigUtil.getInstance().getProperty("latIni","1") %>','<%=ConfigUtil.getInstance().getProperty("latFim","1") %>', '<%=ConfigUtil.getInstance().getProperty("msgMap","Meganet") %>', <%=ConfigUtil.getInstance().getProperty("zoom_mapa","8") %>);">
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
                                                    Inserção de usuário.<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(43, this);"> [?]</span>
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            <!-- INICIO -->
													<br>
													<form action="#" id="form-cliente" onsubmit="return false">
													<input type="hidden" id="id" name="id"/>
													<input name="latitude" id="latitude" type="hidden"/>
													<input name="longitude" id="longitude" type="hidden"/>
													<table width="100%" border="0">
													  <tr bgcolor="#DFE8F6">
													    <td title="Usado apenas para cliente"><div align="right"><font color="red">*</font>MAC:</div></td>
													    <td><select name="combo-mac" id="combo-mac" onchange="selecionaMac();"></select></td>
								                        <td>&nbsp;</td>
								                        <td>&nbsp;</td>
													  </tr>
													  <tr>
													    <td width="18%"><div align="right">Nome:</div></td>
													    <td><input name="usuario" type="text" id="usuario" size="50" maxlength="50" /></td>
													    <td><div align="right">CPF/CNPJ:</div></td>
													    <td><input name="cpf" type="text" id="cpf" size="25" maxlength="18" onkeyup="return formataCPF(this, event)" onblur="return formataCPF(this, event)"/></td>
													  </tr>
													  <!--tr>
													    <td><div align="right">Login:</div></td>
													    <td><input name="login" type="text" id="login" size="25" maxlength="15" /></td>
													    <td><div align="right">Senha:</div></td>
													    <td><input name="senha" type="text" id="senha" size="25" maxlength="8" /></td>
													  </tr-->
													  <tr bgcolor="#DFE8F6">
													    <td><div align="right">Telefone:</div></td>
													    <td><input name="telefone" type="text" id="telefone" size="25" onblur="verificaTamanho(this);" maxlength="13" onkeydown="return formataTelefone(this, event)"/></td>
													    <td><div align="right">Celular:</div></td>
													    <td><input name="telefone2" type="text" id="telefone2" size="25" maxlength="13" onblur="verificaTamanho(this);" onkeydown="return formataTelefone(this, event)" /></td>
													  </tr>
													  <tr>
													    <td><div align="right">End./Complemento:</div></td>
													    <td><input name="endereco" type="text" id="endereco" size="25" maxlength="35"/>&nbsp;<input name="complemento" type="text" id="complemento" size="25" maxlength="100"/></td>
													    <td><div align="right">Bairro:</div></td>
													    <td><input name="bairro" type="text" id="bairro" size="25" maxlength="30" /></td>
													  </tr>
													  <tr bgcolor="#DFE8F6">
													    <td><div align="right">Cidade:</div></td>
													    <td>
													    	<input name="cidade" type="text" id="cidade" size="25" maxlength="13"/>
													    	&nbsp;&nbsp;CEP: <input name="cep" type="text" id="cep" size="9" maxlength="9" onkeydown="return formataCEP(this, event)" />
													    	<img src="img/check.png" onclick="buscaCEP();" style="cursor: pointer;"/><span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(44, this);"> [?]</span>
													    </td>
													    <td><div align="right">Estado:</div></td>
													    <td>
															<select style="width: 155px" name="select-estado" id="select-estado"></select>
														</td>
													  </tr>
													  <tr>
													    <td><div align="right">E-mail</div></td>
													    <td><input name="email" type="text" id="email" size="50" maxlength="50" /></td>
													    <td title="Usado apenas para cliente">
													    	<div align="right"><font color="red">*</font>Contrato:</div>
													    </td>
													    <td>
													    	<select name="combo-contrato" style="width: 155px;" id="combo-contrato">
													      		<option value="" selected="selected">&nbsp;</option>
													    	</select>
													    </td>
													  </tr>
													  <tr bgcolor="#DFE8F6">
								                        <td title="Usado apenas para cliente" ><div align="right"><font color="red">*</font>Servidor:</div></td>
													    <td><select name="combo-alt-servidor" style="width: 155px;" id="combo-alt-servidor" onchange="selectTorreEnderecoIP()">
								                            <option value="" selected="selected">&nbsp;</option>
								                        </select></td>
													    <td title="Usado apenas para cliente"><div align="right"><font color="red">*</font>Endereço IP:</div></td>
													    <td><select name="combo-alt-endereco-ip" style="width: 155px;" id="combo-alt-endereco-ip">
													      <option value="" selected="selected">&nbsp;</option>
													    </select></td>
													  </tr>
													  <tr>
													    <td title="Usado apenas para cliente"><div align="right"><font color="red">*</font>Plano:</div></td>
													    <td><select name="combo-alt-plano" style="width: 155px;" id="combo-alt-plano">
													      <option value="" selected="selected">&nbsp;</option>
													    </select></td>
													    <td><div align="right">Situação cliente:</div></td>
													    <td title="Combo de bloqueio utilizado apenas para cliente">
								                            <select name="combo-cliente-bloqueado" style="width: 155px;" id="combo-cliente-bloqueado">
													      		<option value="0" selected>Sem restrição</option>
													      		<option value="1">Mensagem de advertência</option>
													      		<option value="2">Bloqueado</option>
													      		<option value="3">Desativado - Não gera débito</option>
								                            </select>
								                        </td>
												      </tr>
													  <tr bgcolor="#DFE8F6">
													    <td title="Usado apenas para cliente"><div align="right"><font color="red">*</font>Data ativa&ccedil;&atilde;o:</div></td>
													    <td>
								                        	<input class="campo-data" readonly="readonly" name="dt-ativacao" type="text" id="dt-ativacao" size="25" maxlength="10" /> 
															<img id="bt-data" src="img/calendar.gif" alt="Calendário" class="calendario" />
														</td>
													    <td><div align="right">Data Pagamento:</div></td>
													    <td>
													    	<select name="combo-data-pagamento" style="width: 155px;" id="combo-data-pagamento">
								                            	<option value="" selected>&nbsp;</option>
								                            	<option value="8">08 de cada mês</option>
								                            	<option value="20">20 de cada mês</option>
								                            	<option value="28">28 de cada mês</option>
								                          	</select>
													    </td>
													  </tr>
													  <tr>
													    <td><div align="right" onclick="expandeRecolhe();" style="cursor: pointer;"><b id="msg-menu-cont">+ Menus</b></div></td>
													    <td colspan="4"><div id="menus" style="display: none">&nbsp;</div></td>
													  </tr>
													  <tr bgcolor="#DFE8F6">
													    <td colspan="4"><div id="eqp" style="display: block">&nbsp;</div></td>
													  </tr>
													  <tr>
													    <td colspan="4"><div id="map_canvas" style="width:703px; height:250px; border: #000000 1px solid"></div></td>
													  </tr>
													  <tr bgcolor="#DFE8F6">
													    <td><div align="right">Usuario Adm.:</div></td>
													    <td colspan="4">
															<input type="checkbox" id="usuario-administrativo" name="usuario-administrativo" style="margin-top: 2px"/>
													    </td>
													  </tr>
													  <tr>
													    <td><div align="right">Altera próprio perfil:<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(5, this);"> [?]</span></div></td>
													    <td colspan="4">
															<input type="checkbox" id="altera_proprio_perfil" name="altera_proprio_perfil" style="margin-top: 2px"/>
													    </td>
													  </tr>
													  <tr>
													    <td>&nbsp;</td>
													    <td colspan="4">
													    	<button onclick="insereUsuario()"> &nbsp;&nbsp;Enviar&nbsp;&nbsp; </button>
													    	<button onclick="excluirUsuario()"> &nbsp;&nbsp;Excluir&nbsp;&nbsp; </button>
													    </td>
													  </tr>
													</table>
												</form>                                            
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
	UtilsJS.carregaComboMAC(fncarregaComboMACCallback);
	carregaComboDatasPagamento();
	carregaComboEstado("select-estado");
	AdministracaoJS.carregaFormularioMenu(fncarregaFormularioMenuCallback);
	AdministracaoJS.carregaFormularioEqp(fncarregaFormularioEqpCallback);
</script>
</html>