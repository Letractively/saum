<%@page import="br.com.meganet.util.ConfigUtil"%>
<style type="text/css">
.d1102 {position: relative;overflow: hidden;width: 100%;}
.d1102,.d1102 p,.d1102 a,.d1102 a:link,.d1102 a:visited,.d1102 a:hover{color: #212121;font-size: 11px;}
.d1102 a,.d1102 a:link {color: #286471;text-decoration: underline;}
.d1102 a:visited {color: #666666;}
.d1102 a:hover {color: #09C1E1;text-decoration: none;}
</style>
	<div class="cleared"></div>
   <div class="d1102">
       <div class="art-footer-t"></div>
       <div class="art-footer-l"></div>
       <div class="art-footer-b"></div>
       <div class="art-footer-r"></div>
       <div style="position: relative; padding: 8px">
           <div style="padding: 0; text-align: center; margin: 0 10px 0 10px;">
				<p style="margin: 0; padding: 0; text-align: center;">
					<%=ConfigUtil.getInstance().getProperty("empresa","Meganet") %><br />
					<a href="http://www.gnu.org/licenses/lgpl.html" target="blank">Copyright &copy; 2010 --- Meganet - Efren Junior.(LGPL License)</a>
				</p>
           </div>
   		<div class="cleared"></div>
       </div>
   </div>
<div class="cleared"></div>
<script type="text/javascript">
	function ajustaRodape(){
		var tamJanela = document.body.parentNode.clientHeight;
		var tamScroll = document.body.scrollHeight;
		var corpo = document.getElementById("content-cliente");
		var tamanho = (tamJanela - <%=ConfigUtil.getTamanhoBrowser(pageContext)%>);
		if(tamScroll <= tamJanela){
			<%if(!ConfigUtil.ehIE6(pageContext)){%>
			corpo.style.minHeight = (tamanho) + "px";
			<%}else{%>
			corpo.style.height = (tamanho) + "px";
			<%}%>
		}
	}
	ajustaRodape();
</script>
