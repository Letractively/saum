<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">

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
			function trocaSenha(){
				var sen = $$("sen").value;
				var csen = $$("csen").value;
				var param = $$("param").value;
				if(csen == sen && sen.length >= 6){
					$$("btsenhd").disabled = "disabled";
					UsuariosPortalJS.trocaSenha(sen, param, trocaSenhaCallBack);
				}
			}
			function trocaSenhaCallBack(ret){
				if(ret == 0){
					$$("content-msg").innerHTML = "Senha alterada.<br><a href=\"index.do">Ir para a página inicial</a>";
				}else if(ret == 1){
					$$("content-msg").innerHTML = "Erro ao encontrar usuário.";
				}else if(ret == 2){
					$$("content-msg").innerHTML = "Erro desconhecido.";
				}
				$$("content-msg").style.visibility = "visible";
			}
		</script>
	</head>
	<body>
		<jsp:include page="/iphone/topo.jsp"></jsp:include>
		
        <div id="login" class="selectable1">
            <div class="toolbar">
                <h1>Troca de senha</h1>
                <a href="#" onclick="fnIndex();" class="back">Home</a>
            </div>
            <form onsubmit="return false">
            	<input type="hidden" id="param" value="${id}">
                <div id="content-msg" style="visibility: hidden;">&nbsp;</div>
                <ul class="edit rounded">
                    <li>
                    	<input type="password" id="sen" maxlength="12" placeholder="Nova senha"/>
                    </li>
                    <li>
                    	<input type="password" id="csen" maxlength="12" placeholder="Confirme a senha"/>
                    </li>
                    <li>&nbsp;</li>
                    <li>
                    	<button onclick="javascript: trocaSenha();" id="btsenhd">Enviar</button>
					</li>
                </ul>
            </form>
        </div>
	</body>
</html>
