<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.com.meganet.util.ConfigUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="br.com.meganet.hbm.vo.LogMeganet"%>
<%@page import="java.text.SimpleDateFormat"%>
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
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/administrativo/arquivo_retorno.js"></script>

	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>upload/yahoo.js"></script>
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>upload/plupload.js"></script>
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>upload/plupload.gears.js"></script>
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>upload/plupload.silverlight.js"></script>
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>upload/plupload.flash.js"></script>
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>upload/plupload.browserplus.js"></script>
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>upload/plupload.html4.js"></script>
	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>upload/plupload.html5.js"></script>
	<style type="text/css">
		.msg{
			background:#F3F3F3; 
			border: 1px solid #BABABA; 
			border-radius: 3px 3px 3px 3px; 
			color: #42454A; 
			display: inline-block; 
			font: 12px sans-serif;
			width: 100%;
			color:#000000; 
			text-align: left; 
			margin-right:5px; 
			margin-top: 5px; 
			margin-bottom: 5px; 
			padding: 2px;
		}
		.msg-erro{
			background:#F3F3F3; 
			border: 1px solid #BABABA; 
			border-radius: 3px 3px 3px 3px; 
			color: #42454A; 
			display: inline-block; 
			font: 12px sans-serif;
			width: 100%;
			color:#000000; 
			text-align: left; 
			margin-top: 5px;
			margin-right:5px; 
			margin-bottom: 5px; 
			padding: 2px;
		}
	</style>
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
                                                    Transferir arquivo de retorno.
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            <!-- INICIO -->
			<div id="content-cliente" style="overflow: auto">
				<fieldset id="fieldset-cria-boleto">
					<legend id="legend-cria-boleto">
						Transferir arquivo retorno
					</legend>
					<div style="font-size: 10px; display:none; background-color: #FF0000; width: 100%;color:#FFFFFF; font-weight:bolder ;text-align: center; margin-top: 5px; margin-bottom: 5px; padding: 2px;" id="content-msg-erro">
					
					</div>
					<%if(request.getSession().getAttribute("arquivoNegado") != null){ %>
						<div style="font-size: 10px; background-color: #FF0000; width: 100%;color:#FFFFFF; font-weight:bolder ;text-align: center; margin-top: 5px; margin-bottom: 5px; padding: 2px;" id="content-msg-erro-arq">
							<%=(String) request.getSession().getAttribute("arquivoNegado") %>
						</div>
					<%} 
					request.getSession().removeAttribute("arquivoNegado");
					%>
					
					
<form id="submit-form" method="post" action="genericMulti.do">

	<div>
		<div id="filelist"></div>
		<br />
		<br />
		<br />
		<br />
		<br />
		<img src="img/bt_add.png" style="width: 123px; height: 30px" id="pickfiles"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<img src="img/bt_upload.jpg" id="uploadfiles"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
    
</form>
<script type="text/javascript">
// Custom example logic
function $(id) {
	return document.getElementById(id);	
}


var uploader = new plupload.Uploader({
	runtimes : 'gears,html5,flash,silverlight,browserplus',
	browse_button : 'pickfiles',
	max_file_size : '1mb',
	unique_names : true,
	url : 'genericMulti.do',
	resize : {width : 320, height : 240, quality : 90},
	flash_swf_url : 'upload/plupload.flash.swf',
	silverlight_xap_url : 'upload/plupload.silverlight.xap'
});

uploader.bind('Init', function(up, params) {
});

uploader.bind('Erro', function(up, err) {
	$('filelist').innerHTML += "<div class=\"msg-erro\"><div>Erro: " + err.code +
		", Mensagem: " + err.message +
		(err.file ? ", File: " + err.file.name : "") +
		"</div></div>";

	up.refresh(); // Reposition Flash/Silverlight
});

uploader.bind('FilesAdded', function(up, files) {
	for(var i in files){
		eval("existeLogParaEsseArquivo(\""+files[i].name+"\", \""+files[i].id+"\")");
		$('filelist').innerHTML += '<div id="' + files[i].id + '">' + files[i].name + ' (' + plupload.formatSize(files[i].size) + ')<b></b></div>';
	}
});

uploader.bind('UploadFile', function(up, file) {
	$('submit-form').innerHTML += '<input type="hidden" name="file-' + file.id + '" value="' + file.name + '" />';
});

uploader.bind('UploadProgress', function(up, file) {
	$(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
});

$('uploadfiles').onclick = function() {
	uploader.start();
	return false;
};

uploader.init();
</script>
					
					
					<br>
                   	<table width="100%"><tr><td align="center">
						<table id="extratoBoleto" class="report">
							<thead>
								<tr>
									<th class="sortable">Data da ocorrência</th>
									<th class="sortable">Ação</th>
									<th class="sortable">Descrição</th>
								</tr>
							</thead>
							<tbody>
	                    <%
	                    List log = (List) request.getSession().getAttribute("ultimosLog");
	                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	                  	for (Iterator iterator = log.iterator(); iterator.hasNext();) {
	               			LogMeganet logMeganet = (LogMeganet) iterator.next();
	                   	%>
								<tr>
									<td class="data"><%=sdf.format(logMeganet.getLogData())%></td>
									<td><%=logMeganet.getLogAcao() %></td>
									<td><%=logMeganet.getLogDescricao() %></td>
								</tr>
						<%} %>
							</tbody>
						</table>
					</td>
				</tr>
			</table>
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