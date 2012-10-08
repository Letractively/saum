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
                                                    Informações.
                                                </h2>
                                            </div>
                                            <div class="art-postcontent">
                                            	<div id="content-cliente" style="overflow: auto">
	                                                <!-- article-content -->
				                                    
	                                                <div class="cleared"></div>
		                                               <%
		                                        		int cont2 = 0;
		                                        		boolean ex2 = false;
		                                        		Feed f2 = null;
		                                               	if(ConfigUtil.getInstance().getBooleanProperty("utiliza_leitor_RSS", false) && !fb){
			                                        		f2 = RssUtil.getInstance().getFeed2();
			                                        		if(f2.getNoticias() != null && f2.getNoticias().size() > 0){
			                                        			ex2 = true;
			                                        			cont2 = f2.getNoticias().size();
			                                        		}
		                                               	}
		                                                %>
	                                                	<table border="0" cellpadding="1" cellspacing="1" width="100%">
															<tr>
																<td width="50%" valign="top">
			                                                      	<div class="overview-table-inner">
						                                                <div id="content-aviso" class="painelScrol" style="text-align: left; margin-top: 10px;"></div>
			                                                       	</div>
																</td>
																<td width="50%" valign="top">
			                                                    	<div class="overview-table-inner">
						                                                <div id="content-aviso2" class="painelScrol" style="text-align: left; margin-top: 10px;"></div>
			                                                		</div>
																</td>
															</tr>
															<tr>
																<td valign="top">
			                                                      <div class="overview-table-inner">
			                                                    <%if(!fb && !ConfigUtil.getInstance().getProperty("facebook_id_aplicativo", "0").equalsIgnoreCase("0")){%>
																	<iframe 
																	src="http://www.facebook.com/plugins/likebox.php
																		?href=http://www.facebook.com/apps/application.php
																			?id=<%=ConfigUtil.getInstance().getProperty("facebook_id_aplicativo", "0") %>
																			&width=312
																			&colorscheme=light
																			&show_faces=false&border_color
																			&stream=true
																			&header=true
																			&height=337"
																	scrolling="no" frameborder="0"
																	style="border: none; overflow: hidden; width: 312px; height: 337px;"
																	allowTransparency="true"></iframe>
																<%}else{ %>
			                                                    	&nbsp;
			                                                    <%} %>
			                                                       </div>
																</td>
																<td valign="top">
			                                                    <div class="overview-table-inner">
			                                                      <%if(ex2){ %>
			                                                      		<a href="<%=f2.getImagemLink() %>" target="_blank" title="<%=f2.getNoticiaTitulo() %>">
			                                   						  		<img src="<%=f2.getImagem() %>" alt="<%=f2.getNoticiaTitulo() %>" class="image" />
			                                   						  </a>
			                                   						  <%
			                                   						  	int c2 = 0;
			                                   						  	for (Iterator<Feed> iterator = f2.getNoticias().iterator(); iterator.hasNext();) {
			                                   			            	Feed s = (Feed) iterator.next();
			                                   			            	if(c2 < 7){
			                                   			            	%>
			                                   			            		<%="<p class=\"ul-noticia\">" + s.getNoticiaTitulo() + " - " + s.getNoticiaData() + " - <span style=\"text-decoration: none; color:#FF0000; cursor:pointer\" onclick=\"ativaLeitura('noticia2"+c2+"')\"> Mais...</span></p>" %>
			                                   			            		<div name="noticias" id="noticia2<%=c2 %>" style="display: none; font-size: 12px; color: #444444; padding-left: 18px; margin-bottom: 8px"><%=s.getNoticiaConteudo() + "<a style=\"text-decoration: none; color:#FF0000; cursor:pointer\" target=\"_blank\" href="+s.getNoticiaLink()+"> Leia mais...</a>"%></div>
			                                   			            	<%
			                                   			            	c2++;
			                                   			            	} %>
			                                   			              <%} %>
			                                                       <%}%>
			                                                		</div>
																</td>
															</tr>
														</table>
	                                                
                                                </div>
                                            </div>
                                        <div class="cleared"></div>
                            		</div>
                            		<div class="cleared"></div>
                                </div>
                            </div>