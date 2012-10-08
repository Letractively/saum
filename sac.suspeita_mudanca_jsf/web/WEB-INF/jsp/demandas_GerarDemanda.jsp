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
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/demandas/gerarDemanda.js"></script>
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
                                                    Solicitar demanda.<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(17, this);"> [?]</span>
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            <!-- INICIO -->
				<div id="content-cliente" style="overflow: auto">
					<div>
						<input type="radio" name="tp" id="tp" value="usuarioCpf" checked="checked" onclick="limpaNome();"/> <label for="tp">CPF/CNPJ:</label>
						<input type="radio" name="tp" id="tp1" value="usuarioNome"  onclick="limpaNome();"/> <label for="tp1">Nome</label>
						&nbsp;&nbsp;<input type="text" id="cliente" name="cliente" maxlength="100" size="50" onkeyup="return formataCpfNome(this, event)"/>
						&nbsp;&nbsp;<button onclick="carregaUsuarios()"> OK </button>
				  	</div>
					<fieldset id="fieldset-resultado-busca" style="display: none;">
						<legend id="legend-resultado-busca">
							Resultado da busca
						</legend>
	                    <div id="nomes-clientes-resultado">
	                    </div>
					</fieldset>
					<br>
					<form action="#" id="form-altera-cliente" onsubmit="return false">
					<input name="id" id="id" type="hidden"/>
					<table width="100%" border="0">
					  <tr>
					    <td><div align="right" style="font-weight: bold">MAC:</div></td>
					    <td colspan="3" style="color: blue"><div id="mac"></div></td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td width="150"><div align="right" style="font-weight: bold">Nome:</div></td>
					    <td style="color: blue"><div id="nome">&nbsp;</div></td>
					    <td width="130"><div align="right" style="font-weight: bold">CPF/CNPJ:</div></td>
					    <td style="color: blue"><div id="cpf">&nbsp;</div></td>
					  </tr>
					  <tr>
					    <td><div align="right" style="font-weight: bold">Telefone:</div></td>
					    <td style="color: blue"><div id="telefone">&nbsp;</div></td>
					    <td><div align="right" style="font-weight: bold">Celular:</div></td>
					    <td style="color: blue"><div id="celular">&nbsp;</div></td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td><div align="right" style="font-weight: bold">End./Complemento:</div></td>
					    <td style="color: blue"><span id="endereco">&nbsp;</span> - <span id="complemento">&nbsp;</span></td>
					    <td><div align="right" style="font-weight: bold">Bairro:</div></td>
					    <td style="color: blue"><div id="bairro">&nbsp;</div></td>
					  </tr>
					  <tr>
					    <td><div align="right" style="font-weight: bold">Cidade:</div></td>
					    <td style="color: blue"><div id="cidade">&nbsp;</div></td>
					    <td><div align="right" style="font-weight: bold">Estado:</div></td>
					    <td style="color: blue"><div id="estado">&nbsp;</div></td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td><div align="right" style="font-weight: bold">E-mail</div></td>
					    <td style="color: blue"><div id="email">&nbsp;</div></td>
					    <td></td>
					    <td></td>
					  </tr>
					</table>
				</form>
				</span>
				</fieldset>
				<!-- cliente -->
				
				<fieldset id="fieldset-solicita-demanda" style="display: block">
				<legend id="legend-solicita-demanda">
				Solicitar demanda
				</legend>
				<span id="content-solicita-demanda">
					<div>
                    	<table width="100%" border="0" cellspacing="1" cellpadding="0">
                          <tr>
                            <td width="25%"><div align="right" style="font-weight: bold">Descri&ccedil;&atilde;o:</div></td>
                            <td><textarea name="descricao-demanda" cols="80" rows="5" id="descricao-demanda"></textarea></td>
                          </tr>
                          <tr>
                            <td><div align="right" style="font-weight: bold">Data prevista:<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(18, this);"> [?]</span></div></td>
                            <td>
                            	<input class="campo-data" readonly="readonly" name="data" type="text" id="data" size="20" maxlength="10" /> 
								<img id="bt-data" src="img/calendar.gif" alt="Calendário" class="calendario" />
							</td>
                          </tr>
                          <tr>
                            <td><div align="right" style="font-weight: bold">Hora prevista:</div></td>
                            <td>
	                            <select name="hora" id="hora">
	                            	<option value="" selected>&nbsp;</option>
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
	                          	</select> : <select name="minuto" id="minuto">
	                            	<option value="" selected>&nbsp;</option>
	                            	<option value="00">00</option>
	                            	<option value="10">10</option>
	                            	<option value="20">20</option>
	                            	<option value="30">30</option>
	                            	<option value="40">40</option>
	                            	<option value="50">50</option>
	                          	</select>
							</td>
                          </tr>
                          <tr>
                            <td><div align="right" style="font-weight: bold">Atribuir à:<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(19, this);"> [?]</span></div></td>
                            <td>
	                            <select name="responsavel" id="responsavel"></select>
							</td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                            <td>
                            	<input type="hidden" id="id-demanda" name="id-demanda"/>
					    		<button id="novo" onclick="novo()" disabled="disabled"> Nova demanda </button>&nbsp;&nbsp;&nbsp;&nbsp;
					    		<button id="grava" onclick="gravaDemanda()"> Enviar demanda </button>&nbsp;&nbsp;&nbsp;&nbsp;
					    		<button id="imprime" onclick="imprimeDemanda()" disabled="disabled"> Imprimir demanda </button>
                            </td>
                          </tr>
                        </table>

				  	</div>
				  	</span>
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
	AdministracaoJS.buscaUsuariosAdministrativos(buscaUsuariosAdministrativosCallBack);
</script>
</html>