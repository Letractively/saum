<%@page import="br.com.meganet.util.Util"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>

<form action="http://www.caldascountryshow.com.br/minhachance/computar3.php" method="post" name="form_votar" id="form_votar" onsubmit="return validaForm()">
<table width="500" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><p><strong>Informe um email válido:</strong><p></td>
    <td><input name="email_votante" id="email_votante" style="width:200px; border:1px solid #333; float:left;" type="email" value="<%=Util.getEmail() %>" /><br class="clear" />
    <div id="retorno"></div>
    </td>

  </tr>
  <tr>
    <td colspan="2">
    <script type="text/javascript" src="http://www.google.com/recaptcha/api/challenge?k=6LdKosgSAAAAAOxjQtSlrPk4t2X6fsykHM9j1htu"></script>

	<noscript>
  		<iframe src="http://www.google.com/recaptcha/api/noscript?k=6LdKosgSAAAAAOxjQtSlrPk4t2X6fsykHM9j1htu" height="300" width="500" frameborder="0"></iframe><br/>
  		<textarea name="recaptcha_challenge_field" rows="3" cols="40"></textarea>
  		<input type="hidden" name="recaptcha_response_field" value="manual_challenge"/>
	</noscript>    </td>
    </tr>
  <tr>
  	
  	

    <td><input name="ip_maquina" type="hidden" value="<%=Util.getProximoIP() %>" />
    <input name="id_candidato" type="hidden" value="97" />
    <input name="id_sessao" type="hidden" value="<%=Util.getProximaSessao() %>" />
    </td>
    <td><p style="margin-top:30px;"><input name="bt_votar" type="image" src="imagens/bt_votar.png" /></p></td>
  </tr>
</table>
</form>

</body>
</html>
