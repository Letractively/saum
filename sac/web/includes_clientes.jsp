<%
Boolean fb = (Boolean) request.getSession().getAttribute("facebook");
if(fb == null){
	fb = false;
}
%>
		<!-- css -->
		<link rel="stylesheet" type="text/css" href="<%=request.getSession().getAttribute("path") %>css/alteraMenu.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getSession().getAttribute("path") %>css/geral.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getSession().getAttribute("path") %>css/modal.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getSession().getAttribute("path") %>css/calendar-system.css">
		<%if(!fb){ %>
		<link rel="stylesheet" href="<%=request.getSession().getAttribute("path") %>css/style.css" type="text/css" media="screen" />
		<!--[if IE 6]><link rel="stylesheet" href="<%=request.getSession().getAttribute("path") %>css/style.ie6.css" type="text/css" media="screen" /><![endif]-->
		<!--[if IE 7]><link rel="stylesheet" href="<%=request.getSession().getAttribute("path") %>css/style.ie7.css" type="text/css" media="screen" /><![endif]-->
		<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/mosca.js"></script>
		<%}else{ %>
		<link rel="stylesheet" href="<%=request.getSession().getAttribute("path") %>css/styleFB.css" type="text/css" media="screen" />
		<!--[if IE 6]><link rel="stylesheet" href="<%=request.getSession().getAttribute("path") %>css/style.ie6.FB.css" type="text/css" media="screen" /><![endif]-->
		<!--[if IE 7]><link rel="stylesheet" href="<%=request.getSession().getAttribute("path") %>css/style.ie7.css" type="text/css" media="screen" /><![endif]-->
		<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/moscaFB.js"></script>
		<%} %>
		
		<!-- css -->
		
		<!-- javascript -->
    	<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/script.js"></script>
		<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/modal.js"></script>
		<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/geral.js"></script>
  		<script type='text/javascript' src='<%=request.getSession().getAttribute("path") %>dwr/engine.js'></script>
  		<script type='text/javascript' src='<%=request.getSession().getAttribute("path") %>dwr/util.js'></script>
		<script type='text/javascript' src='<%=request.getSession().getAttribute("path") %>dwr/interface/UsuariosPortalJS.js'></script>
		<script type='text/javascript' src='<%=request.getSession().getAttribute("path") %>dwr/interface/AdministracaoJS.js'></script>
		<script type='text/javascript' src='<%=request.getSession().getAttribute("path") %>dwr/interface/UtilsJS.js'></script>
		<script type='text/javascript' src='<%=request.getSession().getAttribute("path") %>dwr/interface/ContratoJS.js'></script>
		<script type='text/javascript' src='<%=request.getSession().getAttribute("path") %>js/funcoesMenuInicial/menuIndex.js'></script>
  		<script type='text/javascript' src='<%=request.getSession().getAttribute("path") %>dwr/interface/BoletoJS.js'></script>
		<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/login.js"></script>
		<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/administrativo/boleto.js"></script>
		<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/calendar.js"></script>
		<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/calendar-pt-br.js"></script>
		<script type="text/javascript" src="<%=request.getSession().getAttribute("path") %>js/calendar-setup.js"></script>
