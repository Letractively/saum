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
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/configuracao/enderecoIP.js"></script>
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
                                                    Endereço IP.<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(29, this);"> [?]</span>
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            <!-- INICIO -->
							<div id="content-cliente" style="overflow: auto">
								<br/>
								<input type="hidden" name="tipo" id="tipo" value="contato"> 
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
        		                  <tr>
        		                  	<td width="15">&nbsp;</td>
                                 	<td width="60">Servidor:</td>
					    			<td>
					    				<select name="combo-alt-servidor" style="width: 155px;" id="combo-alt-servidor" onchange="buscaIP();">
                            				<option value="" selected="selected">&nbsp;</option>
                        				</select>
                        				<button style="margin-top: 3px" onclick="buscaIP()" id="bt-ok"> OK </button>
                        			</td>
                          		  </tr>
		                        </table>
		                        <br/>
		                        <input type="hidden" id="id"/>
		                        
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								    <tbody>
								        <tr height="28">
								            <td width="8">&nbsp;</td>
								            <td width="8" rowspan="14">&nbsp;</td>
								            <td width="355" valign="top" align="left" colspan="2"><span class="bold"><b>Insira o IP e máscara de sub-rede</b></span></td>
								            <td width="8" valign="top" align="left" rowspan="14">&nbsp;</td>
								            <td valign="top" align="left" colspan="2" ><span class="bold"><b>Resultado</b></span></td>
								        </tr>
								        <tr height="1">
								            <td width="8">&nbsp;</td>
								            <td width="149" valign="bottom" align="left"><span class="normal">IP de exemplo:<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(30, this);"> [?]</span></span></td>
								            <td width="204" valign="bottom" align="left"><input type="text" name="ipinput" id="ipinput" style="width: 148px;" maxlength="15" tabindex="1" /></td>
								            <td width="175" valign="bottom" align="left"><span class="normal">Primeiro host</span></td>
								            <td valign="bottom" align="left"><input type="text" value="0.0.0.0" name="starthost" readonly="readonly" id="starthost" size="15" maxlength="15" /></td>
								        </tr>
								        <tr height="1">
								            <td width="8">&nbsp;</td>
								            <td width="149" valign="bottom" align="left"><span class="normal">Máscara sub-rede</span></td>
								            <td width="204" valign="bottom" align="left">
									             <select name="cidr_netmask" id="cidr_netmask" style="width: 150px;">
													<option value='255.255.255.0'>255.255.255.0 - /24</option>
													<option value='255.255.255.128'>255.255.255.128 - /25</option>
													<option value='255.255.255.192'>255.255.255.192 - /26</option>
													<option value='255.255.255.224'>255.255.255.224 - /27</option>
													<option value='255.255.255.240'>255.255.255.240 - /28</option>
													<option value='255.255.255.248'>255.255.255.248 - /29</option>
													<option value='255.255.255.252'>255.255.255.252 - /30</option>
												</select>
								            </td>
								            <td width="175" valign="bottom" align="left"><span class="normal">Último host</span></td>
								            <td valign="bottom" align="left">
								            	<input type="text" value="0.0.0.0" name="endhost" readonly="readonly" id="endhost" size="15" maxlength="15" />
								            </td>
								        </tr>
								        <tr height="3">
								            <td width="8">&nbsp;</td>
								            <td width="149" valign="top" align="center">&nbsp;</td>
								            <td width="204" valign="top" align="center">&nbsp;</td>
								            <td width="175" valign="bottom" align="left"><span class="normal">Nº max. de hosts</span></td>
								            <td valign="bottom" align="left"><input type="text" value="0" name="numofhosts" id="numofhosts" size="15" maxlength="15" /></td>
								        </tr>
								        <tr height="2">
								            <td width="8" colspan="2" rowspan="2">&nbsp;</td>
								        </tr>
								        <tr height="3">
								            <td width="149" valign="top" rowspan="2">
								            	<button style="margin-top: 3px" onclick="calculate()" id="submitButtonName"> Calcular </button>
								            </td>
								            <td width="204" valign="top" align="left" rowspan="2">&nbsp;</td>
								        </tr>
								        <tr height="25">
								            <td width="8" colspan="2">&nbsp;</td>
								        </tr>
								        <tr height="3">
								            <td width="8">&nbsp;</td>
								            <td width="149" valign="top" align="center" rowspan="2">&nbsp;</td>
								            <td width="204" valign="top" align="center" rowspan="2">&nbsp;</td>
								            <td width="175" valign="bottom" align="left" rowspan="2"><span class="normal">Endereço de rede(Base)</span></td>
								            <td width="176" valign="bottom" align="left" rowspan="2">
								            	<input type="text" value="0.0.0.0" name="subnetaddress" readonly="readonly" id="subnetaddress" size="15" maxlength="15" />
								           	</td>
								        </tr>
								        <tr height="10">
								            <td width="8" colspan="2">&nbsp;</td>
								        </tr>
								        <tr height="17">
								            <td width="8" rowspan="5">&nbsp;</td>
								            <td width="355" valign="middle" align="left" colspan="2" rowspan="5"><span class="bold">Informações.</span>
								            <p><textarea name="reportbox" id="reportbox" style="width: 90%; height: 80px;" readonly="readonly"> Para determinar o range de IP, insira o endereço e máscara de sub-rede nos campos acima e presione &quot;Calcular&quot;.</textarea></p>
								            </td>
								        </tr>
								
								        <tr height="3">
								            <td width="175" valign="bottom" align="left"><span class="normal">Endereço de broadcast</span></td>
								            <td width="176" valign="bottom" align="left"><input type="text" value="0.0.0.0" name="broadcastaddress" readonly="readonly" id="broadcastaddress" size="15" maxlength="15" /></td>
								        </tr>
								        <tr height="3">
								            <td width="175" valign="bottom" align="left"><span class="normal">Classe de rede</span></td>
								            <td width="113" valign="bottom" align="left" colspan="1"><input type="text" value="-" name="networkclass" readonly="readonly" id="networkclass" size="2" maxlength="1" /></td>
								        </tr>
								
								        <tr height="2">
								            <td width="175" valign="bottom" align="left"><span class="normal">Tamanho do endereço de rede</span></td>
								            <td width="113" valign="bottom" align="left" colspan="1"><input type="text" value="0" name="subsizebits" readonly="readonly" id="subsizebits" size="2" maxlength="2" />   Bits</td>
								        </tr>
								        <tr height="4">
								            <td width="175" valign="bottom" align="left"><span class="normal">Tamanho do endereço do host</span></td>
								            <td width="113" valign="bottom" align="left" colspan="1"><input type="text" value="0" name="hostsizebits" readonly="readonly" id="hostsizebits" size="2" maxlength="2" />   Bits</td>
								        </tr>
								    </tbody>
								</table>
								<br/>
					            <div id="div-ips">&nbsp;</div>
						        <br/><br/>
						        <table width="100%" border="0" cellspacing="0" cellpadding="0">
						        	<tr>
						        		<td width="15">&nbsp;</td>
						        		<td><button style="margin-top: 3px" onclick="enviar()" id="bt-envia"> Salvar </button></td>
						        	</tr>
						        </table>
		                        
							</div>
			  			  </td>
                                            
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
	UtilsJS.carregaComboServidoresAtivos(fnCarregaComboServidorAlteraUsrCallback);
	modalMensagem.escondeModal();
</script>
</html>