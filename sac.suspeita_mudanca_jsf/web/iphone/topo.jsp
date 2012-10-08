<%@page import="br.com.meganet.util.ConfigUtil"%>
<div id="topo-cont" class="first-of-type" style="text-align: right; width: 100%; height: 40px; background: #FFFFFF">
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr valign="middle">
			<td align="left" width="160">
				<img alt="<%=ConfigUtil.getInstance().getProperty("empresa","Meganet") %>" border="0" src="img/logo2.jpg">
			</td>
			<td width="40%">
				<div id="content-msg-logado" style="display: none; text-align: right; width: 100%">
					<span style="font-size: 10px; padding-right: 6px">
						<span id="span-login">&nbsp;</span> - <span style="cursor: pointer; text-decoration: underline; color: blue" onclick="javascript: logoff();"> Sair</span>
					</span>
				</div>
			</td>
		</tr>
	</table>
</div>
