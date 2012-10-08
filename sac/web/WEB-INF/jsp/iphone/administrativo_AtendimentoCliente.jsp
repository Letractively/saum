<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<%@page import="br.com.meganet.util.Util"%>
<%@page import="br.com.meganet.util.ConfigUtil"%>
<html xml:lang="pt-br" xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link rel="icon" type="image/vnd.microsoft.icon" href="favicon.ico" />
		<link rel="shortcut icon" href="favicon.ico" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
		<meta name="format-detection" content="telephone=no"/>
		<link rel="apple-touch-icon" href="favicon.png"/>
		<link rel="apple-touch-icon-precomposed" href="favicon.png" />
		<title><%=ConfigUtil.getInstance().getProperty("empresa","Meganet") %></title>
		<jsp:include page="/iphone/includes.jsp"></jsp:include>
		<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
		<script type="text/javascript" src="js/administrativo/atendimentoCliente.js"></script>
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
	<body onload="initialize('<%=ConfigUtil.getInstance().getProperty("latIni","1") %>','<%=ConfigUtil.getInstance().getProperty("latFim","1") %>', '<%=ConfigUtil.getInstance().getProperty("msgMap","Meganet") %>', <%=ConfigUtil.getInstance().getProperty("zoom_mapa","8") %>);">
		<input type="hidden" id="id-cliente" value="<%=id %>">
		<jsp:include page="/iphone/topo.jsp"></jsp:include>
        <div id="login" class="selectable1">
            <div class="toolbar">
                <h1>Atd. cliente</h1>
                <a href="#" onclick="fnIndex();" class="back">Voltar</a>
            </div>
            <div id="content-msg" style="visibility: hidden;">&nbsp;</div>
            <form action="#" id="form-altera-cliente" onsubmit="return false">
            <ul class="edit rounded">
            	<li>
					<input type="radio" <%if(tp.equalsIgnoreCase("cpf")){ %> checked="checked" <%} %>name="tp" id="tp" value="usuarioCpf" onclick="limpaNome();"/> <label for="tp">CPF/CNPJ:</label>
					<input type="radio" <%if(tp.equalsIgnoreCase("nome")){ %> checked="checked" <%} %>name="tp" id="tp1" value="usuarioNome" onclick="limpaNome();"/> <label for="tp1">Nome</label>
					<input type="radio" <%if(tp.equalsIgnoreCase("id")){ %> checked="checked" <%} %>name="tp" id="tp2" value="usuarioId" onclick="limpaNome();"/> <label for="tp2">Id banco</label>
				</li>
				<li>
					<table width="100%">
						<tr>
							<td><input type="text" value="<%=id %>" id="cliente" name="cliente" maxlength="100" onkeyup="return formataCpfNome(this, event)"/></td>
							<td><button onclick="carregaUsuarios()"> OK </button></td>
						</tr>
					</table>
				</li>
				<li id="fieldset-resultado-busca" style="display: none;">
		            <div id="nomes-clientes-resultado">&nbsp;</div>
				</li>
                <li>
					<input name="id" id="id" type="hidden"/>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
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
					    <td><div align="left" style="font-weight: bold; float: left">Servidor:</div><div style="color: #0000FF" id="torre">&nbsp;</div></td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td><div align="left" style="font-weight: bold; float: left">Telefone:</div><div style="color: #0000FF" id="telefone">&nbsp;</div></td>
					  </tr>
					  <tr>
					    <td><div align="left" style="font-weight: bold; float: left">Celular:</div><div style="color: #0000FF" id="celular">&nbsp;</div></td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td><div align="left" style="font-weight: bold; float: left">Endereco:</div><span style="color: #0000FF" id="endereco">&nbsp;</span> - <span id="complemento">&nbsp;</span></td>
					  </tr>
					  <tr>
					    <td><div align="left" style="font-weight: bold; float: left">Bairro:</div><div style="color: #0000FF" id="bairro">&nbsp;</div></td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td><div align="left" style="font-weight: bold; float: left">Cidade:</div><div style="color: #0000FF" id="cidade">&nbsp;</div></td>
					  </tr>
					  <tr>
					    <td><div align="left" style="font-weight: bold; float: left">Estado:</div><div style="color: #0000FF" id="estado">&nbsp;</div></td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
				    	<td><div align="left" style="font-weight: bold; float: left">E-mail:</div><div class="conteudo" style="color: #0000FF" id="email">&nbsp;</div></td>
					  </tr>
					  <tr>
					    <td><div align="left" style="font-weight: bold; float: left">St cliente:</div><div style="color: #0000FF" id="ativo-bloqueado">&nbsp;</div></td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td><div align="left" style="font-weight: bold; float: left">Dt ativa&ccedil;&atilde;o:</div><div style="color: #0000FF" id="dt-ativacao">&nbsp;</div></td>
					  </tr>
					  <tr>
					    <td><div align="left" style="font-weight: bold; float: left">Dt PG:</div><div style="color: #0000FF" id="dt-pagamento">&nbsp;</div></td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td><div align="left" style="font-weight: bold; float: left">Pg atrasado:</div><div style="color: #0000FF" id="pg-atrasado">&nbsp;</div></td>
					  </tr>
					  <tr>
					    <td><div align="left" style="font-weight: bold; float: left">ID Cliente:</div><div style="color: #0000FF" id="id-cliente-html">&nbsp;</div></td>
					  </tr>
					  <tr bgcolor="#DFE8F6">
					    <td><div id="map_canvas" style="width:100%; height:250px; border: #000000 1px solid"></div></td>
					  </tr>
					  <tr>
					    <td><div align="left" style="font-weight: bold; float: left">End. IP:</div><div style="color: #0000FF" id="end-ip">&nbsp;</div></td>
					  </tr>
					  <tr>
					    <td>
				    		<table width="100%" cellpadding="0" cellspacing="0" border="0">
				    			<tr>
				    				<td><button style="width: 100%;" onclick="verificaMediaPerfomanceAP();" id="bt-perf" disabled="disabled"> Perfomance do AP (Média) </button></td>
				    			</tr>
				    			<tr>
				    				<td><button style="width: 100%;" onclick="verificaAtualPerfomanceAP()" id="bt-perf-a" disabled="disabled"> Perfomance do AP (Atual) </button></td>
				    			</tr>
				    			<%if(ConfigUtil.getInstance().getBooleanProperty("envia_comando_verificar_torre_atual", true)){ %>
				    			<tr>
				    				<td><button style="width: 100%;" onclick="verificaTorre()" id="bt-torre" disabled="disabled"> Verificar Torre (Atual) </button></td>
				    			</tr>
				    			<%} %>
				    			<tr>
				    				<td><button style="width: 100%;" onclick="verificarPlanoCliente()" id="bt-plano" disabled="disabled"> Verificar Plano </button></td>
				    			</tr>
				    			<tr>
				    				<td><button style="width: 100%;" onclick="localizarBoletos()" id="bt-bol" disabled="disabled"> Localizar boletos </button></td>
				    			</tr>
				    			<tr>
				    				<td><button style="width: 100%;" onclick="localizarDemandas()" id="bt-dem" disabled="disabled"> Localizar Demandas </button></td>
				    			</tr>
				    			<tr>
				    				<td><button style="width: 100%;" onclick="verificarComodato()" id="bt-eqp" disabled="disabled"> Verificar comodato </button></td>
				    			</tr>
				    			<tr>
				    				<td><button style="width: 100%;" onclick="verificaDesbloqueio()" id="bt-desbloqueio" disabled="disabled"> Desbloqueio temporário </button></td>
				    			</tr>
				    		</table>
					    </td>
					  </tr>
					</table>
				</li>
				<li id="fieldset-perfomance-ap-media" style="display: none">
					<fieldset>
					<legend id="legend-perfomance-ap-media">Perfomance AP (Média)</legend>
					<span id="content-perfomance-ap-media">
	                  <br/>
					  <table width="100%" border="0">
	                    <tr>
	                      <td><div align="left" style="font-weight: bold; float: left;">Upload: </div><div class="conteudo" id="vel-med-up">&nbsp;</div></td>
	                    </tr>
	                    <tr>
	                      <td><div align="left" style="font-weight: bold; float: left;">Download: </div><div class="conteudo" id="vel-med-down" >&nbsp;</div></td>
	                    </tr>
	                    <tr>
	                      <td><div align="left" style="font-weight: bold;">Tx CCQ: </div><div id="ccq" class="conteudo" style="width: 100%; background: #FFFFFF; border: #000000 1px solid; height: 13px">.</div></td>
	                    </tr>
	                    <tr>
	                      <td><div align="left" style="font-weight: bold;">Força de sinal (Dbi): </div><div class="conteudo" id="sinal" style="width: 100%; background: #FFFFFF; border: #000000 1px solid; height: 13px">&nbsp;</div></td>
	                    </tr>
	                    <tr>
	                      <td><div align="left" style="font-weight: bold; float: left;">Throughput: </div><div class="conteudo" id="throug">&nbsp;</div></td>
	                    </tr>
	                    <tr>
	                      <td><div align="left" style="font-weight: bold; float: left;">Data medição: </div><div class="conteudo" style="width:100%" id="data">&nbsp;</div></td>
	                    </tr>
	                  </table>
					</span>
					</fieldset>
				</li>
				<li id="fieldset-desbloqueio" style="display: none">
					<fieldset>
						<legend id="legend-desbloqueio">Desbloqueio de cliente</legend>
						<span id="content-desbloqueio">
		                  <br/>
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
                		<tr>
                			<td colspan="2"><button style="height: 29px; margin-top: 3px" onclick="desbloqueiaUsr()" id="bt-envia-desb"> Enviar </button></td>
                		</tr>
                	</table>
		                  
		                  </table>
						</span>
					</fieldset>
				</li>
				<li id="fieldset-perfomance-ap" style="display: none">
					<fieldset>
					<legend id="legend-perfomance-ap">Perfomance atual do AP</legend>
					<span id="content-perfomance-ap">
					  <table width="100%" border="0">
	                    <tr>
	                      <td><div align="left" style="font-weight: bold; float: left;">Upload: </div><div class="conteudo" id="vel-med-up-at">&nbsp;</div></td>
	                    </tr>
	                    <tr>
	                      <td><div align="left" style="font-weight: bold; float: left;">Download: </div><div class="conteudo" id="vel-med-down-at" >&nbsp;</div></td>
	                    </tr>
	                    <tr>
	                      <td><div align="left" style="font-weight: bold;">Tx CCQ: </div><div id="ccq-at" style="width: 100%; background: #FFFFFF; border: #000000 1px solid; height: 13px">.</div></td>
	                    </tr>
	                    <tr>
	                      <td><div align="left" style="font-weight: bold;">Força de sinal (Dbi): </div><div id="sinal-at" style="width: 100%; background: #FFFFFF; border: #000000 1px solid; height: 13px">&nbsp;</div></td>
	                    </tr>
	                    <tr>
	                      <td><div align="left" style="font-weight: bold; float: left;">Throughput: </div><div class="conteudo" id="throug-at">&nbsp;</div></td>
	                    </tr>
	                    <tr>
	                      <td><div align="left" style="font-weight: bold; float: left;">Data medição: </div><div class="conteudo" id="data-at">&nbsp;</div></td>
	                    </tr>
	                  </table>
					</span>
					</fieldset>
				</li>
				<li id="fieldset-boletos" style="display: none;">
					<fieldset>
					<legend id="legend-boletos">Lista boletos</legend>
					<span id="content-boletos">
						<div id="div-boletos">&nbsp;</div>
					</span>
					</fieldset>
				</li>
				<li id="fieldset-torre" style="display: none">
					<fieldset>
					<legend id="legend-torre">Perfomance atual da Torre</legend>
					<span id="content-torre">
					  <table width="100%" border="0">
	                    <tr>
	                      <td><div align="left" style="font-weight: bold; float: left;">Nome da torre: </div><div class="conteudo" id="torre-atual">&nbsp;</div></td>
	                    </tr>
	                    <tr>
	                      <td><div align="left" style="font-weight: bold; float: left;">Qtd cliente registrados: </div><div class="conteudo" id="qtd" >&nbsp;</div></td>
	                    </tr>
	                    <tr>
	                      <td><div align="left" style="font-weight: bold; float: left;">Rate de upload: </div><div class="conteudo" id="vel-med-up-torre">&nbsp;</div></td>
	                    </tr>
	                    <tr>
	                      <td><div align="left" style="font-weight: bold; float: left;">Rate de download: </div><div class="conteudo" id="vel-med-down-torre" >&nbsp;</div></td>
	                    </tr>
	                    <tr>
	                      <td><div align="left" style="font-weight: bold;">Tx CCQ: </div><div id="ccq-torre" class="conteudo" style="width: 100%; background: #FFFFFF; border: #000000 1px solid; height: 13px">&nbsp;</div></td>
	                    </tr>
	                    <tr>
	                      <td><div align="left" style="font-weight: bold;">Noise-floor (Dbi): </div><div id="noise-torre" class="conteudo" style="width: 100%; background: #FFFFFF; border: #000000 1px solid; height: 13px">&nbsp;</div></td>
	                    </tr>
	                  </table>
					</span>
					</fieldset>
				</li>
				<li id="fieldset-plano" style="display: none">
					<fieldset>
					<legend id="legend-plano">Verificar plano</legend>
					<span id="content-plano">
					  <table width="100%" border="0">
	                    <tr>
	                      <td><div align="left" style="font-weight: bold; float: left;">Nome: </div><div class="conteudo" id="nome-plano">&nbsp;</div></td>
	                    </tr>
	                    <tr>
	                      <td><div align="left" style="font-weight: bold; float: left;">Velocidade: </div><div class="conteudo" id="velocidade-plano">&nbsp;</div></td>
	                    </tr>
	                    <tr>
	                      <td><div align="left" style="font-weight: bold; float: left;">Pode ser bloqueado: </div><div class="conteudo" id="pode-bloqueio">&nbsp;</div></td>
	                    </tr>
	                    <tr>
	                      <td><div align="left" style="font-weight: bold; float: left;">Valor: </div><div class="conteudo" id="valor-mens">&nbsp;</div></td>
	                    </tr>
	                  </table>
					</span>
					</fieldset>
				</li>
				<li id="fieldset-demandas" style="display: none">
					<fieldset>
					<legend id="legend-demandas">Demandas</legend>
						<span id="content-demandas">
							<div id="div-demandas" style="width: 100%">&nbsp;</div>
						</span>
					</fieldset>
				</li>
				<li id="fieldset-eqp" style="display: none">
					<fieldset>
						<legend id="legend-eqp">Equipamentos em comodato</legend>
						<span id="content-eqp">
							<input type="hidden" id="hEqp"/>
							<div id="eqp" style="display: block">&nbsp;</div>
						</span>
					</fieldset>
					</li>
            </ul>
            </form>
        </div>
		<div id="fieldset-trafego-img-ap"></div>
		<div id="fieldset-solicita-demanda"></div>
		<div id="bt-demanda"></div>
		<div id="bt-contrato"></div>
		<div id="bt-img"></div>
	</body>
	<script type="text/javascript">
		AdministracaoJS.carregaFormularioEqp(fncarregaFormularioEqpCallback);
		iniciaCalendario();
		carregaUsuarios();
		carregaNome();
	</script>
</html>
