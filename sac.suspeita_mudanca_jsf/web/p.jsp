<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<style type="text/css">
<!--
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
}
-->
</style>
</head>
<script language="javascript">
	function sobreInter(obj) {
		var div = document.getElementById("div-cont-ajuda");
		var iframe = document.getElementById("iframe-ajuda");
		div.style.top = (obj.offsetTop + 18) + "px";
		div.style.left = (obj.offsetLeft + 17) + "px";
		div.style.display = "block";
		iframe.src = "/index.do"
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
<body onClick="foraInter()" onkeydown="foraInterKEY(event)">
	<jsp:include page="/include-cabecalho-menu.jsp"></jsp:include>
	<span class="span-ajuda" onclick="foraInterClique();" onmouseover="sobreInter(0, this);"> [?]</span>

	<p>asd ffasdf asdfasd fasd fdfa</p>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	<p>&nbsp;</p>

	<p>&nbsp;</p>
	<p>sdf asdfasdfad</p>
	<p>fasdfas</p>
	<p>df</p>
	<p>asdf</p>
	<p>asdf</p>
	<p>asd</p>
	<p>f</p>
	<p>asdf</p>
	<p>asdf asd</p>
	<p>f</p>
	<p>fd</p>
</body>
</html>
