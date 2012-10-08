<%
	Boolean fb = (Boolean) request.getSession().getAttribute("facebook");
	if (fb == null) {
		fb = false;
	}
	if (!fb) {
%>
<div class="art-header">
	<div class="art-header-jpeg"></div>
</div>
<%
	}
%>
<style type="text/css">
<!--
.span-ajuda {
	color: #FF777A; 
	height: 15px; 
	width: 50px; 
	font-size: 12px; 
	margin-bottom: 2px; 
	cursor: help;
	font-weight: bold;
}
.box-ajuda {
	-webkit-border-radius: 0px 5px 5px 5px;
	-moz-border-radius: 0px 5px 5px 5px;
	-o-border-radius: 0px 5px 5px 5px;
	-khtml-border-radius: 0px 5px 5px 5px;
	border-radius: 0px 5px 5px 5px;
	-webkit-box-shadow: rgba(0, 0, 0, 0.398438) 0 1px 3px;
	-moz-box-shadow: rgba(0, 0, 0, 0.398438) 0 1px 3px;
	box-shadow: rgba(0, 0, 0, 0.398438) 0 1px 3px;
	background: white;
	padding: 3px 3px 3px 3px;
	border-color: #e5e5e5 #dbdbdb #d2d2d2;
	border: 1px solid;
	z-index: 100001;
}
-->
</style>
<div class="art-nav">
	<div class="l"></div>
	<div class="r"></div>
	<div id="menu-conteiner" style="display: block; z-index: 99999">&nbsp;</div>
</div>
<script language="javascript">
	function sobreInter(op, obj) {
		AdministracaoJS.buscaString(op, sobreInterCallBack);
		var div = document.getElementById("div-cont-ajuda");
		var iframe = document.getElementById("iframe-ajuda");
		div.style.top = (findPosY(obj) + 17) + "px";
		div.style.left = (findPosX(obj) + 17) + "px";
	}
	function sobreInterCallBack(ret) {
		var div = document.getElementById("div-cont-ajuda");
		var iframe = document.getElementById("iframe-ajuda");
		div.style.display = "block";
		iframe.innerHTML = ret;
	}
	function findPosX(obj) {
		var curleft = 0;
		if (obj.offsetParent)
			while (1) {
				curleft += obj.offsetLeft;
				if (!obj.offsetParent)
					break;
				obj = obj.offsetParent;
			}
		else if (obj.x)
			curleft += obj.x;
		return curleft;
	}

	function findPosY(obj) {
		var curtop = 0;
		if (obj.offsetParent)
			while (1) {
				curtop += obj.offsetTop;
				if (!obj.offsetParent)
					break;
				obj = obj.offsetParent;
			}
		else if (obj.y)
			curtop += obj.y;
		return curtop;
	}
	function foraInterClique() {
		if (document.getElementById("div-cont-ajuda").style.display == "block") {
			document.getElementById("div-cont-ajuda").style.display = "none"
		}
	}
	function foraInterKEY(e) {
		car = (navigator.appName == "Netscape") ? e.which : e.keyCode;
		if (car == 27) {
			foraInter();
		}
	}
	function foraInter() {
		var div = document.getElementById("div-cont-ajuda");
		div.style.display = "none";
	}
</script>

