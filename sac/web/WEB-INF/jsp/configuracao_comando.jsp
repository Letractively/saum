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
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/configuracao/comando.js"></script>
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
                                                    Configuração de comandos.<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(31, this);"> [?]</span></span></td>
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            <!-- INICIO -->
							<div id="content-cliente" style="overflow: auto">
								<br/>
								<input type="hidden" name="tipo" id="tipo" value="contato"> 
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
        		                <tr>
                                 	<td width="100"><div align="right">Grupo torre:<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(32, this);"> [?]</span></span></td></div></td>
					    			<td>
					    				<select name="combo-alt-servidores" style="width: 155px;" id="combo-alt-servidores" onchange="buscaTorresVinculadasAoServidor();">
                            				<option value="" selected="selected">&nbsp;</option>
                        				</select>
                        			</td>
                        		</tr>
                        		<tr>
                                 	<td width="80"><div align="right">Tipo:<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(33, this);"> [?]</span></span></td></div></td>
					    			<td>
					    				<select name="combo-tipo" style="width: 155px;" id="combo-tipo" onchange="buscaTorresVinculadasAoServidor();">
                            				<option value="1" selected="selected">Adiciona</option>
                            				<option value="2" selected="selected">Altera</option>
                            				<option value="3" selected="selected">Deleta</option>
                        				</select>
                        			</td>
                          		</tr>
		                        </table>
		                        <br/>
		                        <input type="hidden" id="id"/>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  			  <tr bgcolor="#DFE8F6">
								    <td>Comando 1</td>
								    <td>Torres vinculadas</td>
                          		  </tr>
					  			  <tr bgcolor="#DFE8F6">
								    <td><input name="cmd1" type="text" id="cmd1" style="width: 550px" maxlength="350"/></td>
								    <td>
								    	<select name="combo-torre-1" style="width: 155px;" id="combo-torre-1">
                        				</select>
								    </td>
                          		  </tr>
					  			  <tr bgcolor="#DFE8F6">
								    <td colspan="3">&nbsp;</td>
                          		  </tr>
        		                  <tr>
								    <td>Comando 2</td>
								    <td>Torres vinculadas</td>
                          		  </tr>
        		                  <tr>
								    <td><input name="cmd2" type="text" id="cmd2" style="width: 550px" maxlength="350"/></td>
   								    <td>
								    	<select name="combo-torre-2" style="width: 155px;" id="combo-torre-2">
                        				</select>
								    </td>
                          		  </tr>
					  			  <tr>
								    <td colspan="3">&nbsp;</td>
                          		  </tr>
					  			  <tr bgcolor="#DFE8F6">
								    <td>Comando 3</td>
								    <td>Torres vinculadas</td>
                          		  </tr>
					  			  <tr bgcolor="#DFE8F6">
								    <td><input name="cmd3" type="text" id="cmd3" style="width: 550px" maxlength="350"/></td>
								    <td>
								    	<select name="combo-torre-3" style="width: 155px;" id="combo-torre-3">
                        				</select>
								    </td>
                          		  </tr>
					  			  <tr bgcolor="#DFE8F6">
								    <td colspan="3">&nbsp;</td>
                          		  </tr>
        		                  <tr>
								    <td>Comando 4</td>
								    <td>Torres vinculadas</td>
                          		  </tr>
        		                  <tr>
								    <td><input name="cmd4" type="text" id="cmd4" style="width: 550px" maxlength="350"/></td>
								    <td>
								    	<select name="combo-torre-4" style="width: 155px;" id="combo-torre-4">
                        				</select>
								    </td>
                          		  </tr>
					  			  <tr>
								    <td colspan="3">&nbsp;</td>
                          		  </tr>
					  			  <tr bgcolor="#DFE8F6">
								    <td>Comando 5</td>
								    <td>Torres vinculadas</td>
                          		  </tr>
					  			  <tr bgcolor="#DFE8F6">
								    <td><input name="cmd5" type="text" id="cmd5" style="width: 550px" maxlength="350"/></td>
								    <td>
								    	<select name="combo-torre-5" style="width: 155px;" id="combo-torre-5">
                        				</select>
								    </td>
                          		  </tr>
					  			  <tr bgcolor="#DFE8F6">
								    <td colspan="3">&nbsp;</td>
                          		  </tr>
        		                  <tr>
								    <td>Comando 6</td>
								    <td>Torres vinculadas</td>
                          		  </tr>
        		                  <tr>
								    <td><input name="cmd6" type="text" id="cmd6" style="width: 550px" maxlength="350"/></td>
								    <td>
								    	<select name="combo-torre-6" style="width: 155px;" id="combo-torre-6">
                        				</select>
								    </td>
                          		  </tr>
								  <tr>
								    <td>&nbsp;</td>
								    <td><button onclick="gravaAlteracao()" id="bt-enviar">&nbsp;&nbsp;Enviar&nbsp;&nbsp;</button></td>
								  </tr>
		                        </table>
		                        <br/>
								<table class="report" width="99%" border="0" cellspacing="0" cellpadding="0">
									<thead>
									  <tr>
									    <th width="7%">Símbolo</th>
									    <th width="43%">Tradução</th>
									    <th width="7%">Símbolo</th>
									    <th width="43%">Tradução</th>
									  </tr>
								  </thead>
								  <tr>
								    <td>{1}</td>
								    <td>Usado onde pode ser setado os valores &quot;yes&quot; ou &quot;no&quot; (disabled={1})</td>
								    <td>{2}</td>
								    <td>Mac do usuario</td>
								  </tr>
								  <tr>
								    <td>{3}</td>
								    <td>ID do usuário no sistema (comment=&quot;{3}&quot;)</td>
								    <td>{4}</td>
								    <td>ID do usuário no sistema (name={4})</td>
								  </tr>
								  <tr>
								    <td>{5}</td>
								    <td>Endereço IP do cliente (address={5})</td>
								    <td>{6}</td>
								    <td>Endereço IP do cliente (target-addresses={6})</td>
								  </tr>
								  <tr>
								    <td>{7}</td>
								    <td>Nome da torre e interface (interface={7})</td>
								    <td>{8}</td>
								    <td>Nome da torre e/ou interface (server={8})</td>
								  </tr>
								  <tr>
								    <td>{9}</td>
								    <td>Utilizado na WLAN (authentication={9})</td>
								    <td>{10}</td>
								    <td>Utilizado na WLAN (forwarding={10}). Sempre será &quot;no&quot;</td>
								  </tr>
								  <tr>
								    <td>{11}</td>
								    <td>Usado no hotspot (type={11}). Assume &quot;bypassed&quot;, &quot;regular&quot;, &quot;blocked&quot;. de acordo com os 4 estágios do cliente - &quot;Sem restrição&quot;, &quot;Mensagem de advertência&quot; e &quot;Bloqueado&quot;</td>
								    <td>{12}</td>
								    <td>Valor do burst-limit no queue (burst-limit={12}). É calculado de acordo com a velocidade do plano.</td>
								  </tr>
								  <tr>
								    <td>{13}</td>
								    <td>Valor do threshold (burst-threshold={13}). É calculado de acordo com a velocidade do plano.</td>
								    <td>{14}</td>
								    <td>Valor do burst-time (burst-time={14}).  É calculado de acordo com a velocidade do plano.</td>
								  </tr>
								  <tr>
								    <td>{15}</td>
								    <td>Utilizado para os parametros de &quot;disabled&quot; (disabled={15}). Assume o valor de &quot;yes&quot; apenas para clientes &quot;desativados&quot;</td>
								    <td>{16}</td>
								    <td>Limite do queue (limit-at={16}).   É calculado de acordo com a velocidade do plano.</td>
								  </tr>
								  <tr>
								    <td>{17}</td>
								    <td>Limite máximo do queue (max-limit={17}).   É calculado de acordo com a velocidade do plano.</td>
								    <td>{21}</td>
								    <td>Senha do cliente a ser cadastrado com o login. Caso seu cliente necessite de login PPPoE ou login no HotSpot</td>
								  </tr>
								  <tr>
								    <td>{22}</td>
								    <td>Move o queue para a posição 3. se tiver adicionando o queue é necessário movê-lo para cima do queue do hotspot.<br />
								    Ex.: &quot;/queue simple move [find name={4}] {22}&quot;</td>
								    <td>{23}</td>
								    <td>Usado no tempo de controle do queue (time={8})</td>
								  </tr>
								</table>
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
	UtilsJS.carregaComboTodosServidores(fnCarregaComboServidorAlteraCallback);
	modalMensagem.escondeModal();
</script>
</html>