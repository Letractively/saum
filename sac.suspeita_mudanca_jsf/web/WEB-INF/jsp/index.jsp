<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.com.meganet.util.ConfigUtil"%>
<%@page import="br.com.meganet.hbm.vo.Feed"%>
<%@page import="java.util.Iterator"%>
<%@page import="br.com.meganet.util.RssUtil"%>
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
	<jsp:include page="/includes_clientes.jsp"></jsp:include>
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
                        <jsp:include page="/includeBoletoCPF.jsp"></jsp:include>
                    	<!-- menu lateral -->
						<%} %>
                    
                        <div class="art-layout-cell art-content">
                            <jsp:include page="corpo_index.jsp"></jsp:include>
                        </div>
                    </div>
                </div>
                <%if(!fb){ %>
                	<jsp:include page="/rodape.jsp"></jsp:include>
                <%} %>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
	loginCookie2();
	function ativaLeitura(obj){
		var noticias = document.getElementsByName("noticias");
		if($$(obj).style.display == "block"){
			$$(obj).style.display = "none";
		}else{
			for(var i = 0; i < noticias.length; i++){
				noticias[i].style.display = "none";
			}
			$$(obj).style.display = "block";
		}
	}

	function buscaDetalheAviso(tp){
		var parametros = {
		altura:400,
		largura:650,
		endereco:"detalharAviso.do?tp=" +tp};
		
		var modal = new Modal(parametros);
		modal.criaMosca();
		modal.criaDivConteudo();
	}
</script>
</html>