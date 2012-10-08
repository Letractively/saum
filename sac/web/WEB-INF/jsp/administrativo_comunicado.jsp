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
	
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>/js/jscripts/tiny_mce/tiny_mce.js"></script>
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/administrativo/comunicado.js"></script>
	      
	<script type="text/javascript">
		tinyMCE.init({
		
			// General options
			mode : "textareas",
			theme : "advanced",
			plugins : "safari,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template",
	
			// Theme options
			theme_advanced_buttons1 : "bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,formatselect,fontselect,fontsizeselect,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak",
			theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,cleanup,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
			theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,advhr,|,print,|,ltr,rtl,|,fullscreen",
			theme_advanced_toolbar_location : "top",
			theme_advanced_toolbar_align : "left",
			theme_advanced_statusbar_location : "bottom",
			theme_advanced_resizing : false,
	
			// Example content CSS (should be your site CSS)
			content_css : "<%=request.getSession().getAttribute("path") %>/js/editor/css/content.css",
	
			// Drop lists for link/image/media/template dialogs
			template_external_list_url : "<%=request.getSession().getAttribute("path") %>/js/editor/lists/template_list.js",
			external_link_list_url : "<%=request.getSession().getAttribute("path") %>/js/editor/lists/link_list.js",
			external_image_list_url : "<%=request.getSession().getAttribute("path") %>/js/editor/lists/image_list.js",
			media_external_list_url : "<%=request.getSession().getAttribute("path") %>/js/editor/lists/media_list.js",
	
			// Replace values for the template plugin
			template_replace_values : {
				username : "Some User",
				staffid : "991234"
			}
		});
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
                                                    Envio de comunicado.
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            <!-- INICIO -->
						<div id="content-cliente" style="overflow: auto">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td width="155" align="right">Comunicados já enviados:</td>
									<td>
										<select style="width: 200px" name="select-altera-comunicado" id="select-altera-comunicado"></select>
										 &nbsp;&nbsp;&nbsp;<button onclick="carregaComunicado();"> Carregar </button>
										 &nbsp;&nbsp;&nbsp;<button onclick="novoComunicado();"> Novo </button>
									</td>
								</tr>
								<tr>
									<td width="150" align="right">Data de expiração:</td>
									<td>
										<input class="campo-data" readonly="readonly" name="dt-ativacao" type="text" id="dt-ativacao" style="width: 176px" maxlength="10" /> 
						  				<img id="bt-data" src="img/calendar.gif" alt="CalendÃ¡rio" class="calendario" />
									</td>
								</tr>
								<tr>
									<td width="150" align="right">Título:</td>
									<td>
										<input name="titulo" type="text" id="titulo" style="width: 356px" maxlength="40" />
									</td>
								</tr>
								<tr>
									<td width="150" align="right">Tipo de envio:</td>
									<td>
										<select style="width: 358px" name="tp-envio" id="tp-envio" onchange="selecionaTipo(this);">
				                          	<option value="1">Todos os usuários</option>
				                          	<option value="2">Torre</option>
				                          	<option value="3">Usuário(s)</option>
				                          	<option value="4">Data de vencimento</option>
				                          	<option value="5">Cidade</option>
				                          </select>
									</td>
								</tr>
								<tr>
									<td width="150" align="right">&nbsp;</td>
									<td>
										<div id="conteudo-escolha-tipo" style="display: none;">
											<select name="combo-opcoes" style="width: 358px" id="combo-opcoes">
					                            <option value="" selected="selected">&nbsp;</option>
					                        </select>
										</div>
									</td>
								</tr>
							</table>
					<form action="#" id="form-altera-contrato" onsubmit="return false">
					  <table width="100%" border="0">
                        <tr>
                          <td colspan="4" align="center" style="padding: 2px;">
                          	<textarea name="contrato" id="contrato" cols="120" rows="15"></textarea>
                          </td>
                        </tr>
                        <tr bgcolor="#DFE8F6">
                          <td width="16%">&nbsp;</td>
                          <td width="72%">
	                          <button onclick="gravaComunicado()" id="bt-enviar"> &nbsp;&nbsp;Gravar&nbsp;&nbsp; </button></td>
                          <td width="7%">&nbsp;</td>
                          <td width="5%">&nbsp;</td>
                        </tr>
                      </table>
				</form>
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
	AdministracaoJS.carregaAvisos(fnCarregaAvisosCallBack);
</script>
</html>