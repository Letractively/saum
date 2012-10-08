<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.com.meganet.util.ConfigUtil"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link rel="icon" type="image/vnd.microsoft.icon" href="favicon.ico" />
	<link rel="shortcut icon" href="favicon.ico" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title><%=ConfigUtil.getInstance().getProperty("empresa","Meg@net") %> - <%=ConfigUtil.getInstance().getProperty("frase_header_pagina_comum","Mikrotik") %></title>
	<jsp:include page="/includes.jsp"></jsp:include>
		<script type="text/javascript">
			function trocaSenha(){
				var sen = $("sen").value;
				var csen = $("csen").value;
				var param = $("param").value;
				if(csen == sen && sen.length >= 6){
					$("btsenhd").disabled = "disabled";
					UsuariosPortalJS.trocaSenha(sen, param, trocaSenhaCallBack);
				}
			}
			function trocaSenhaCallBack(ret){
				if(ret == 0){
					$("content-login").style.display = "none";
					$("content-msg-troca").innerHTML = "Senha alterada.<br><a href=\"index.do\">Ir para a página inicial</a>";
				}else if(ret == 1){
					$("content-msg-troca").innerHTML = "Erro ao encontrar usuário.";
				}else if(ret == 2){
					$("content-msg-troca").innerHTML = "Erro desconhecido.";
				}
			}
		
		</script>
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
                        <jsp:include page="/includeBoletoCPF.jsp"></jsp:include>
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
                                                    Troca de senha.<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(2, this);"> [?]</span>
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            <!-- INICIO -->
					    <div id="content-cliente" style="overflow: auto">
							<table width="100%" align="center">
								<tr>
									<td align="center">
										<fieldset id="fieldset-login" style="text-align: left; height: 95px; width: 230px">
											<span id="content-login" style="display: block;">
												<input type="hidden" id="param" value="${id}">
												<label for="cmp-usuario" style="margin-right:24px;">Senha: </label><input type="password" id="sen" maxlength="12" style="width: 150px" /><BR>
												<label for="cmp-usuario" style="margin-right:10px;">Confirme: </label><input type="password" id="csen" maxlength="12" style="width: 150px" /><BR>
												<button style="margin-left: 68px; width: 60px" onclick="javascript: trocaSenha();" id="btsenhd">Enviar</button>&nbsp;&nbsp;
												<br>
											</span>
											<div style="font-size: 10px; background-color: #FFB3B8; width: 100%; text-align: center; margin-top: 5px" id="content-msg-troca">Escolha a sua senha, minímo 6 caracteres.</div>
										</fieldset>
									</td>
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
                <jsp:include page="/rodape.jsp"></jsp:include>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
modalMensagem.escondeModal();
</script>
</html>