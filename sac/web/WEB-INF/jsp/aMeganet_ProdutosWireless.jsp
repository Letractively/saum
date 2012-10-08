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
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/meganet/produtosWireless.js"></script>

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
                                                    Produtos.
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            <!-- INICIO -->
			<div id="content-cliente" style="overflow: auto">
              <fieldset id="fieldset-link">
	        		<legend id="legend-altera-cliente">
					Conheça alguns produtos utilizados pela <%=ConfigUtil.getInstance().getProperty("empresa","Meganet") %>
					</legend>
					<span id="content-topo">
		               	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabela-links">
		                     <tr class="tabela-links">
								<td width="25%" id="ap" onMouseOver="tbOver(this)" onMouseOut="tbOut(this)" onClick="abreAccessPoint();">Access Point</td>
								<td width="25%" id="ad" onMouseOver="tbOver(this)" onMouseOut="tbOut(this)" onClick="abreAntena();">Antena Direcional</td>
								<td width="25%" id="cr" onMouseOver="tbOver(this)" onMouseOut="tbOut(this)" onClick="abreCabo();">Cabo RGC 213</td>
								<td id="pg" onMouseOver="tbOver(this)" onMouseOut="tbOut(this)" onClick="abrePig();">Pig-tail</td>
							  </tr>
						</table>
	              </span>
			  </fieldset>
			  
			  <!-- Access point -->
			  <fieldset id="fieldset-ap" style="display:none">
					<legend id="legend-ap">
						<img id="img-ap" src="img/img_menos.gif" alt="" class="show-hide" onclick="escondeFieldSet('ap')" />
						Access Point
					</legend>
					<span id="content-ap">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="31%" align="center" valign="top"><img src="img/zinwell.png" alt="AP" width="250" height="250"></td>
                            <td width="33%" align="left" valign="top"><STRONG>Caracter&iacute;sticas</STRONG> <BR>
                              <BR>
                              - 802.11b (250 mW); <BR>
                              - 802.11g (100   mW); <BR>
                              - 32 MB de Mem&oacute;ria RAM; <BR>
                              - Sistema CSMA/CA com ACK; <BR>
                              - Wizard   (Configura&ccedil;&atilde;o passo-a-passo); <BR>
                              - Trabalha como Router, Bridge, Client, AP,   AP+WDS e WDS; <BR>
                              - Spanning Tree; <BR>
                              - Sincronismo de hor&aacute;rio via Servidor   NTP; <BR>
                              - Ferramenta para Site Survey.;<BR>
                              - System Log.<BR>
                              <BR>
                              <strong>Hardware</strong> <BR>
                              <BR>
                              - Chipset Realtek RTL 8186<BR>
                              - 1 Porta WAN; <BR>
                              - 1 Porta LAN; <BR>
                              - 1   Interface Wireless. <BR>
                              <BR>
                              <strong>Seguran&ccedil;a</strong> <BR>
                              <BR>
                              - Encripta&ccedil;&atilde;o WEP, WPA   (TKIP) e WPA2 (AES); <BR>
                              - 802.1x; <BR>
                              - Suporta Servidor RADIUS; <BR>
                              - Senha   pr&eacute;-configurada; <BR>
                              - Controle por MAC Address de at&eacute; 50 Endere&ccedil;os; <BR>
                            - Block   Relay (Bloqueia para que os clientes n&atilde;o se enxerguem). </td>
                            <td width="36%" align="left" valign="top"><strong>TCP/IP</strong> <BR>
                              <BR>
                              - Configura&ccedil;&atilde;o de LAN e   WAN; <BR>
                              - NAT; <BR>
                              - 802.1d Spanning tree; <BR>
                              - Clone MAC Address; <BR>
                              - DHCP   Server; <BR>
                              - WAN (IP via DHCP, Estatico, PPPoE e PPTP); <BR>
                              - Roteamento RIP. <BR>
                              <BR>
                              <strong>Firewall</strong> <BR>
                              <BR>
                              - Port Filtering; <BR>
                              - IP Filtering; <BR>
                              - MAC   Filtering; <BR>
                              - Port Filtering; <BR>
                              - DMZ (Redirecionamento para Servidor); <BR>
                              - VPN (IPSEC com NAT Transversal). <BR>
                              <BR>
                              <strong>Gerenciamento</strong> <BR>
                              <BR>
                              -   Status; <BR>
                              - Estat&iacute;sticas; <BR>
                              - QoS; <BR>
                              - Controle de Banda por Interface; <BR>
                              - DDNS (DNS Din&acirc;mico); <BR>
                              - Logs. <BR>
                            - SNMP</td>
                          </tr>
                        </table>

			  </span>
			  </fieldset>


			  <!-- antena -->
			  <fieldset id="fieldset-antena" style="display:none;">
					<legend id="legend-antena">
						<img id="img-antena" src="img/img_menos.gif" alt="" class="show-hide" onclick="escondeFieldSet('antena')" />
						Antena Direcional
					</legend>
					<span id="content-antena">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="18%" align="center" valign="middle" bgcolor="#FFFFFF"><img src="img/antena.png" alt="AP" width="100" height="100"></td>
                            <td width="82%" valign="top" style="padding-left:10px"><ul>
                              <li>Frequ&ecirc;ncia de opera&ccedil;&atilde;o: 2400 MHz ~ 2500MHz</li>
                              <li>Ganho: 25 dBi</li>
                              <li>HPBW (Vertical/Horizontal) : 14&ordm;/10&ordm;</li>
                              <li>Polariza&ccedil;&atilde;o: Linear, Vertical</li>
                              <li>M&eacute;dia "Front to back": &gt; 30 dB</li>
                              <li>Pot&ecirc;ncia: M&aacute;ximo 100 W (cw)</li>
                              <li>Imped&acirc;ncia: 50 Ohms</li>
                              <li>Cabo de conex&atilde;o: RG-58/u, 30 cm</li>
                              <li>Conector: Tipo N, F&ecirc;mea</li>
                              <li>Resist&ecirc;ncia ao vento: 216 km/hr</li>
                              <li>Temperatura: -40 &deg;C ~ 80 &deg;C</li>
                              <li>Prote&ccedil;&atilde;o contra raios: Terra</li>
                              <li>Tipo de antena: Direcional, grid</li>
                              <li>Tipo de montagem: "Pole end", diametro m&aacute;ximo 50 mm</li>
                              <li>Dimens&otilde;es: 10 cm X 6 cm X 5 cm</li>
                            </ul></td>
                          </tr>
                        </table>

			  </span>
			  </fieldset>
              
			  <!-- cabo RGC -->
			  <fieldset id="fieldset-cabo" style="display:none;">
					<legend id="legend-cabo">
						<img id="img-cabo" src="img/img_menos.gif" alt="" class="show-hide" onclick="escondeFieldSet('cabo')" />
						Cabo RGC
					</legend>
					<span id="content-antena">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="18%" align="center" valign="middle" bgcolor="#FFFFFF"><img src="img/cabo_rgc213.jpg" alt="AP" width="250" height="100"></td>
                            <td width="82%" valign="top" style="padding-left:10px"><DIV align="left">- Di&acirc;metro fio de cobre: 2,60 mm<BR>
                              - Di&acirc;metro externo: 10,3   mm<BR>
                              - Imped&acirc;ncia: 50 &Omega;<BR>
                            - Atenua&ccedil;&atilde;o dB/100m (800 MHz): 15 dB </DIV></td>
                          </tr>
                        </table>

			  </span>
			  </fieldset>

			  <!-- pig-tail -->
			  <fieldset id="fieldset-pig" style="display:none;">
					<legend id="legend-pig">
						<img id="img-pig" src="img/img_menos.gif" alt="" class="show-hide" onclick="escondeFieldSet('pig')" />
						Cabo RGC
					</legend>
					<span id="content-pig">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="18%" align="center" valign="middle" bgcolor="#FFFFFF"><img src="img/pigtail.png" alt="AP" width="100" height="100"></td>
                            <td width="82%" valign="top" style="padding-left:10px"><p>Pig-tail (Rabo de porco)</p>
                            <p>Serve como redutor de conex&atilde;o, o cabo RGC 213 al&eacute;m de ser grosso &eacute; muito r&iacute;gido, necessitando de um adaptador male&aacute;vel e que na outra ponta possa ser conectada ao Access Point.</p></td>
                          </tr>
                        </table>

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
	modalMensagem.escondeModal();
</script>
</html>