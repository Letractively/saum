<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.com.meganet.util.ConfigUtil"%>
<%@page import="br.com.meganet.util.Util"%>
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
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/administrativo/atendimentoCliente.js"></script>
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
<%
String id = Util.acoesBusca("buscaUsuario", request);
String tp = "";
if(id != ""){
	String[] valor = id.split("-@-");
	tp = valor[0];
	id = valor[1];
	if(!tp.equalsIgnoreCase("cpf") && !tp.equalsIgnoreCase("nome") && !tp.equalsIgnoreCase("id")){
		id = "";
	}
}
%>
<input type="hidden" id="id-cliente" value="<%=id %>">

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
                   		<%if(!fb){ %>
                    	<!-- menu lateral -->
                        <%if(!fb){ %><jsp:include page="/includeBoletoCPF.jsp"></jsp:include><%} %>
                    	<!-- menu lateral -->
						<%} %>
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
                                                    Atendimento de cliente.<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(53, this);"> [?]</span>
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            <!-- INICIO -->
				<div id="content-cliente" style="overflow: auto">
					<div>
						<input type="radio" <%if(tp.equalsIgnoreCase("cpf")){ %> checked="checked" <%} %>name="tp" id="tp" value="usuarioCpf" onclick="limpaNome();"/> <label for="tp">CPF/CNPJ:</label>
						<input type="radio" <%if(tp.equalsIgnoreCase("nome")){ %> checked="checked" <%} %>name="tp" id="tp1" value="usuarioNome" onclick="limpaNome();"/> <label for="tp1">Nome</label>
						<input type="radio" <%if(tp.equalsIgnoreCase("id")){ %> checked="checked" <%} %>name="tp" id="tp2" value="usuarioId" onclick="limpaNome();"/> <label for="tp2">Id banco</label>
						&nbsp;&nbsp;<input type="text" value="<%=id %>" id="cliente" name="cliente" maxlength="100" size="50" onkeyup="return formataCpfNome(this, event)"/>
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
					<div>
					<form action="#" id="form-altera-cliente" onsubmit="return false">
					<input name="id" id="id" type="hidden"/>
					<table width="100%" border="0">
					  <tr>
					    <td><div align="right" style="font-weight: bold">MAC:</div></td>
					    <td colspan="3" style="color: blue"><div id="mac"></div></td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td width="20%"><div align="right" style="font-weight: bold">Nome:</div></td>
					    <td width="30%" style="color: blue" colspan="3"><div id="nome">&nbsp;</div></td>
					  </tr>
					  <tr>
					    <td width="20%"><div align="right" style="font-weight: bold">CPF/CNPJ:</div></td>
					    <td width="30%" style="color: blue"><div id="cpf">&nbsp;</div></td>
					    <td><div align="right" style="font-weight: bold">Servidor:</div></td>
					    <td style="color: blue"><div id="torre">&nbsp;</div></td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td><div align="right" style="font-weight: bold">Telefone:</div></td>
					    <td style="color: blue"><div id="telefone">&nbsp;</div></td>
					    <td><div align="right" style="font-weight: bold">Celular:</div></td>
					    <td style="color: blue"><div id="celular">&nbsp;</div></td>
					  </tr>
					  <tr>
					    <td><div align="right" style="font-weight: bold">Endereco:</div></td>
					    <td style="color: blue"><span id="endereco">&nbsp;</span> - <span id="complemento">&nbsp;</span></td>
					    <td><div align="right" style="font-weight: bold">Bairro:</div></td>
					    <td style="color: blue"><div id="bairro">&nbsp;</div></td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td><div align="right" style="font-weight: bold">Cidade:</div></td>
					    <td style="color: blue"><div id="cidade">&nbsp;</div></td>
					    <td><div align="right" style="font-weight: bold">Estado:</div></td>
					    <td style="color: blue"><div id="estado">&nbsp;</div></td>
					  </tr>
					  <tr>
					    <td><div align="right" style="font-weight: bold">E-mail</div></td>
					    <td style="color: blue"><div id="email">&nbsp;</div></td>
					    <td><div align="right" style="font-weight: bold">Situação cliente:</div></td>
					    <td style="color: blue"><div id="ativo-bloqueado">&nbsp;</div></td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td><div align="right" style="font-weight: bold">Data ativa&ccedil;&atilde;o:</div></td>
					    <td style="color: blue"><div id="dt-ativacao">&nbsp;</div></td>
					    <td><div align="right" style="font-weight: bold">Data Pagamento :</div></td>
					    <td style="color: blue"><div id="dt-pagamento">&nbsp;</div></td>
					  </tr>
					  <tr>
					    <td><div align="right" style="font-weight: bold">Pagamento atrasado:</div></td>
					    <td style="color: blue"><div id="pg-atrasado">&nbsp;</div></td>
					    <td><div align="right" style="font-weight: bold">ID Cliente</div></td>
					    <td style="color: blue"><div id="id-cliente-html">&nbsp;</div></td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td><div align="right" style="font-weight: bold">Endere&ccedil;o IP:</div></td>
					    <td style="color: blue"><div id="end-ip">&nbsp;</div></td>
					    <td>&nbsp;</td>
					    <td style="color: blue">&nbsp;</td>
					  </tr>
					  <tr>
					    <td colspan="4"><div id="map_canvas" style="width:703px; height:250px; border: #000000 1px solid"></div></td>
					  </tr>
					  <tr>
					    <td colspan="4">
					    	<div>
					    		<br/>
					    		<table width="100%" cellpadding="0" cellspacing="0" border="0">
					    			<tr>
					    				<td><button style="width: 170px; height: 29px;" onclick="verificaMediaPerfomanceAP();" id="bt-perf" disabled="disabled"> Perfomance do AP (Média) </button></td>
					    				<td><button style="width: 170px; height: 29px" onclick="verificaAtualPerfomanceAP()" id="bt-perf-a" disabled="disabled"> Perfomance do AP (Atual) </button></td>
					    				<td><button style="width: 170px; height: 29px; margin-top: 3px" onclick="localizarBoletos()" id="bt-bol" disabled="disabled"> Localizar boletos </button></td>
					    				<td><button style="width: 170px; height: 29px; margin-top: 3px" onclick="verificarPlanoCliente()" id="bt-plano" disabled="disabled"> Verificar plano </button></td>
					    			</tr>
					    			<tr>
					    				<td><button style="width: 170px; height: 29px; margin-top: 3px" onclick="visualizarContrato()" id="bt-contrato" disabled="disabled"> Visualizar contrato </button></td>
					    				<td><button style="width: 170px; height: 29px; margin-top: 3px" onclick="solicitarDemanda()" id="bt-demanda" disabled="disabled"> Solicitar demanda </button></td>
					    				<td><button style="width: 170px; height: 29px; margin-top: 3px" onclick="localizarDemandas()" id="bt-dem" disabled="disabled"> Localizar demandas </button></td>
					    				<td><button style="width: 170px; height: 29px; margin-top: 3px" onclick="verificarComodato()" id="bt-eqp" disabled="disabled"> Verificar comodato </button></td>
					    			</tr>
					    			<tr>
					    				<td><button style="width: 170px; height: 29px" onclick="verificaDesbloqueio()" id="bt-desbloqueio" disabled="disabled"> Desbloqueio temporário </button></td>
					    				<%if(ConfigUtil.getInstance().getBooleanProperty("envia_comando_verificar_torre_atual", true)){ %>
					    				<td><button style="width: 170px; height: 29px" onclick="verificaTorre()" id="bt-torre" disabled="disabled"> Verificar torre (Atual) </button></td>
					    				<%}else{ %>
					    				<td>&nbsp;</td>
					    				<%} %>
					    				<td>&nbsp;</td>
					    				<td>&nbsp;</td>
					    			</tr>
					    			<tr>
					    				<td>&nbsp;</td>
					    				<td>&nbsp;</td>
					    				<td>&nbsp;</td>
					    				<td>&nbsp;</td>
					    			</tr>
					    		</table>
					    	</div>
					    </td>
					  </tr>
					</table>
				</form>
					</div>
				</span>
				</fieldset>
				<!-- cliente -->
				
               <fieldset id="fieldset-desbloqueio" style="display: none">
				<legend id="legend-desbloqueio">Desbloqueio de cliente</legend>
				<span id="content-desbloqueio">
				<div>
                  <br/>
				  <table width="100%" border="0">
                    <tr bgcolor="#DFE8F6">
                      <td width="200"><div align="right" style="font-weight: bold">Data limite: </div></td>
                      <td>
                      	<input class="campo-data" readonly="readonly" name="dt-limite-bloqueio" type="text" id="dt-limite-bloqueio" style="width: 132px;" maxlength="10" /> 
						<img id="bt-dt-limite-bloqueio" src="img/calendar.gif" alt="Calendário" class="calendario" />
					  </td>
                    </tr>
                    <tr>
                      <td><div align="right" style="font-weight: bold">Motivo do desbloqueio: </div></td>
                      <td>
                      	<textarea id="motivo" style="width: 155px" maxlength="150" onclick="this.value = ''"></textarea>
                      </td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td>
                      	<button style="height: 29px; margin-top: 3px" onclick="desbloqueiaUsr()" id="bt-envia-desb"> Enviar </button>
                      </td>
                    </tr>
                  </table>
				</div>
				</span>
				</fieldset>
				
               <fieldset id="fieldset-perfomance-ap-media" style="display: none">
				<legend id="legend-perfomance-ap-media">Perfomance AP (Média dos 3 meses anteriores)</legend>
				<span id="content-perfomance-ap-media">
				<div>
                  <br/>
				  <table width="100%" border="0">
                    <tr bgcolor="#DFE8F6">
                      <td width="200"><div align="right" style="font-weight: bold">Rate m&eacute;dio de upload: </div></td>
                      <td><div id="vel-med-up">&nbsp;</div></td>
                      <td width="200"><div align="right" style="font-weight: bold">Rate m&eacute;dio de download: </div></td>
                      <td><div id="vel-med-down" >&nbsp;</div></td>
                    </tr>
                    <tr>
                      <td><div align="right" style="font-weight: bold">Tx CCQ: </div></td>
                      <td><div id="ccq" style="width: 100%; background: #FFFFFF; border: #000000 1px solid; height: 13px">&nbsp;</div></td>
                      <td><div align="right" style="font-weight: bold">Força de sinal (Dbi): </div></td>
                      <td><div id="sinal" style="width: 100%; background: #FFFFFF; border: #000000 1px solid; height: 13px">&nbsp;</div></td>
                    </tr>
                    <tr bgcolor="#DFE8F6">
                      <td><div align="right" style="font-weight: bold">Throughput: </div></td>
                      <td><div id="throug">&nbsp;</div></td>
                      <td><div align="right" style="font-weight: bold">Data da última medição: </div></td>
                      <td><div id="data">&nbsp;</div></td>
                    </tr>
                  </table>
				</div>
				</span>
				</fieldset>
				
               <fieldset id="fieldset-perfomance-ap" style="display: none">
				<legend id="legend-perfomance-ap">Perfomance atual do AP</legend>
				<span id="content-perfomance-ap">
				<div>
				  <table width="100%" border="0">
                    <tr bgcolor="#DFE8F6">
                      <td width="200"><div align="right" style="font-weight: bold">Rate m&eacute;dio de upload: </div></td>
                      <td><div id="vel-med-up-at">&nbsp;</div></td>
                      <td width="200"><div align="right" style="font-weight: bold">Rate m&eacute;dio de download: </div></td>
                      <td><div id="vel-med-down-at" >&nbsp;</div></td>
                    </tr>
                    <tr>
                      <td><div align="right" style="font-weight: bold">Tx CCQ: </div></td>
                      <td><div id="ccq-at" style="width: 100%; background: #FFFFFF; border: #000000 1px solid; height: 13px">&nbsp;</div></td>
                      <td><div align="right" style="font-weight: bold">Força de sinal (Dbi): </div></td>
                      <td><div id="sinal-at" style="width: 100%; background: #FFFFFF; border: #000000 1px solid; height: 13px">&nbsp;</div></td>
                    </tr>
                    <tr bgcolor="#DFE8F6">
                      <td><div align="right" style="font-weight: bold">Throughput: </div></td>
                      <td><div id="throug-at">&nbsp;</div></td>
                      <td><div align="right" style="font-weight: bold">Data medição: </div></td>
                      <td><div id="data-at">&nbsp;</div></td>
                    </tr>
                  </table>
				</div>
				</span>
				</fieldset>
				
               <fieldset id="fieldset-trafego-img-ap" style="display: none">
				<legend id="legend-trafego-img-ap">Tráfego médio do cliente</legend>
				<span id="content-trafego-img-ap">
					<div id="div-trafego-img" style="width: 100%">&nbsp;</div>
				</span>
				</fieldset>
				
               <fieldset id="fieldset-boletos" style="display: none;">
				<legend id="legend-boletos">Lista boletos</legend>
				<span id="content-boletos">
					<div id="div-boletos" style="width: 100% height: 150px; overflow: auto">&nbsp;</div>
				</span>
				</fieldset>
				
               <fieldset id="fieldset-torre" style="display: none">
				<legend id="legend-torre">Perfomance atual da Torre que o cliente está acessando</legend>
				<span id="content-torre">
				<div>
				  <table width="100%" border="0">
                    <tr bgcolor="#DFE8F6">
                      <td width="200"><div align="right" style="font-weight: bold">Nome da torre: </div></td>
                      <td><div id="torre-atual">&nbsp;</div></td>
                      <td width="200"><div align="right" style="font-weight: bold">Quantidade de cliente registrados: </div></td>
                      <td><div id="qtd" >&nbsp;</div></td>
                    </tr>
                    <tr>
                      <td width="200"><div align="right" style="font-weight: bold">Rate m&eacute;dio de upload: </div></td>
                      <td><div id="vel-med-up-torre">&nbsp;</div></td>
                      <td width="200"><div align="right" style="font-weight: bold">Rate m&eacute;dio de download: </div></td>
                      <td><div id="vel-med-down-torre" >&m;</div></td>
                    </tr>
                    <tr bgcolor="#DFE8F6">
                      <td><div align="right" style="font-weight: bold">Tx CCQ: </div></td>
                      <td><div id="ccq-torre" style="width: 100%; background: #FFFFFF; border: #000000 1px solid; height: 13px">&nbsp;</div></td>
                      <td><div align="right" style="font-weight: bold">Noise-floor (Dbi): </div></td>
                      <td><div id="noise-torre" style="width: 100%; background: #FFFFFF; border: #000000 1px solid; height: 13px">&nbsp;</div></td>
                    </tr>
                  </table>
				</div>
				</span>
				</fieldset>
				
               <fieldset id="fieldset-plano" style="display: none">
				<legend id="legend-plano">Verificar plano</legend>
				<span id="content-plano">
				<div>
				  <table width="100%" border="0">
                    <tr bgcolor="#DFE8F6">
                      <td width="200"><div align="right" style="font-weight: bold">Nome: </div></td>
                      <td><div id="nome-plano">&nbsp;</div></td>
                      <td width="200"><div align="right" style="font-weight: bold">Velocidade: </div></td>
                      <td><div id="velocidade-plano">&nbsp;</div></td>
                    </tr>
                    <tr>
                      <td><div align="right" style="font-weight: bold">Pode ser bloqueado: </div></td>
                      <td><div id="pode-bloqueio">&nbsp;</div></td>
                      <td><div align="right" style="font-weight: bold">Valor: </div></td>
                      <td><div id="valor-mens">&nbsp;</div></td>
                    </tr>
                  </table>
				</div>
				</span>
				</fieldset>
				
				<fieldset id="fieldset-solicita-demanda" style="display: none">
				<legend id="legend-solicita-demanda">Solicitar demanda</legend>
				<span id="content-solicita-demanda">
					<div>
                    	<table width="100%" border="0" cellspacing="1" cellpadding="0">
                          <tr>
                            <td width="18%"><div align="right" style="font-weight: bold">Descri&ccedil;&atilde;o:</div></td>
                            <td width="82%"><textarea name="descricao-demanda" cols="80" rows="5" id="descricao-demanda"></textarea></td>
                          </tr>
                          <tr>
                            <td><div align="right" style="font-weight: bold">Atribuir à:</div></td>
                            <td>
	                            <select name="responsavel" id="responsavel"></select>
							</td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                            <td>
					    		<button id="bt-enviar-demanda" onclick="gravaDemanda()"> Enviar demanda</button>
                            </td>
                          </tr>
                        </table>

				  	</div>
				  </span>
				 </fieldset>
				 
				<fieldset id="fieldset-eqp" style="display: none">
				<legend id="legend-eqp">Equipamentos em comodato</legend>
					<span id="content-eqp">
						<input type="hidden" id="hEqp"/>
						<div id="eqp" style="display: block">&nbsp;</div>
					</span>
				</fieldset>
				 
				<fieldset id="fieldset-demandas" style="display: none;">
				<legend id="legend-demandas">Lista de demandas</legend>
				<span id="content-demandas">
					<div id="div-demandas" style="width: 100% height: 150px; overflow: auto">&nbsp;</div>
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
                <%if(!fb){ %>
                <%if(!fb){ %><jsp:include page="/rodape.jsp"></jsp:include><%} %>
                <%} %>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
	loginCookie();
	iniciaCalendario();
	AdministracaoJS.carregaFormularioEqp(fncarregaFormularioEqpCallback);
	modalMensagem.escondeModal();
</script>
</html>