<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.com.meganet.util.Util"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

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
		<script type="text/javascript" src="js/demandas/finalizarDemanda.js"></script>
		<jsp:include page="/iphone/includes.jsp"></jsp:include>
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
                <h1>Finalizar Demanda</h1>
                <a href="#" onclick="fnIndex();" class="back">Voltar</a>
            </div>
            <div id="content-msg" style="visibility: hidden;">&nbsp;</div>
            <form action="#" id="form-altera-cliente" onsubmit="return false">
            <ul class="edit rounded">
                <li>
					<table width="100%"><tr><td align="center">
						<display:table id="demandas-tb" name="sessionScope.demandas" 
							class="report" pagesize="10" export="true" sort="list" requestURI="/finalizarDemanda.do"
							defaultsort="2" decorator="br.com.meganet.displayTag.WrapperDemandasVO">
							<display:column property="demandasId" title="N. Dem." 
								sortable="true" class="numeroDemanda" headerClass="sortable"/>
							<display:column property="dataAbertura" title="Abertura" sortable="true"
								headerClass="sortable" class="aberturaDemanda"/>
							<display:column property="cliente" title="Cliente" sortable="true"
								headerClass="sortable" class="cliente"/>
							<display:column property="demandasDescricao" title="Descrição" sortable="true"
								headerClass="sortable" class="descricao"/>
							<display:column property="fechar" title="Fechar" sortable="false"
								headerClass="sortable" class="numeroDemanda"/>
						</display:table>
					</td></tr></table>
                </li>
                <span id="fieldset-solicita-demanda" style="display: none;">
                	<input type="hidden" name="idDemanda" id="idDemanda"/>
                	<li><div style="width: 100%; text-align: center;">Finalizar</div></li>
                	<li>Descrição:<textarea name="descricao-demanda" rows="5" id="descricao-demanda" style="width:99%"></textarea></li>
                	<li>Origem do problema:<select name="combo-tp-erro" id="combo-tp-erro" style="width:99%"></select></li>
	                <li>
	                	<select name="hora" id="hora" style="width:99%">
	                     	<option value="" selected>Hora atendimento</option>
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
	                	<select name="minuto" id="minuto" style="width:99%">
	                    	<option value="" selected>Minuto</option>
	                    	<option value="00">00</option>
	                    	<option value="10">10</option>
	                    	<option value="20">20</option>
	                    	<option value="30">30</option>
	                    	<option value="40">40</option>
	                    	<option value="50">50</option>
	                  	</select>
	                </li>
                	<li>&nbsp;</li>
                	<li>Data atendimento:
	                	<TABLE width="100%">
	                		<TR>
	                			<Td><input class="campo-data" readonly="readonly" name="data" type="text" id="data" maxlength="10" /></Td>
	                			<Td width="10" style="padding-left: 2px;"><img id="bt-data" src="img/calendar.gif" alt="Calendário" class="calendario" /></Td>
	                		</TR>
	                	</TABLE>
	                </li>
                	<li>&nbsp;</li>
                	
                	<li><button onclick="gravaDemanda()">Encerrar demanda</button></li>
                </span>
            </ul>
            </form>
        </div>
	</body>
	<script type="text/javascript">
		carregaNome();
		iniciaCalendario();
	</script>
	
</html>
