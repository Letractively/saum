
<%@tag import="java.util.Set"%><%@tag import="java.util.Map"%>
<%@ tag import="java.util.Iterator"%>
<%@ tag import="java.util.List"%>
<%@ tag pageEncoding="UTF-8" body-content="tagdependent"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="lista" required="false" type="java.lang.String" %>
<%@ attribute name="mapa" required="false" type="java.lang.String" %>
<%@ attribute name="labelOption" required="false" type="java.lang.String" %>
<%@ attribute name="valueOption" required="false" type="java.lang.String" %>
<%@ attribute name="nome" required="false" %>
<%@ attribute name="onchange" required="false" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="width" required="false" %>
		<style type="text/css">
		
			#tselectbox {
			    width:0px;
			}
			
			#tselectbox span {
				margin-top:2px;
			}
			
			#tselectbox select {
				margin:0px 0px 1px 0px;/*-2*/
			    *margin:-2px !important;
				/*margin:0px 0px -2px 0px;1*/
				float:left;
			    border-width:0;/*add*/
				font-family:Tahoma;
				font-size:12px;
				background-color:#f3f9f7;
				height:16px;
				 *height:20px !important;
			}
			
			#tselectbox td {
			    background-repeat:repeat-x;
			}
		
		</style>
         	<table id="tselectbox" cellpadding=0 cellspacing=0 border=0>
                <tr>
                    <td>
                        <img src="<%=request.getContextPath()%>/imagens/select_campo_esquerda.gif">
                    </td>
                    <td background="<%=request.getContextPath()%>/imagens/select_campo_centro.gif">
                        <span>
                            <select width="${width}" name="${nome}" onchange="${onchange}" id="${id}">
                        <%
                        try{
                        	if(!"".equalsIgnoreCase(lista) && lista != null){
	                        	List ll = (List) request.getAttribute(lista);
	                        	for (Iterator iterator = ll.iterator(); iterator.hasNext();) {
									Object object = (Object) iterator.next();
									Class prodClass = Class.forName(object.getClass().getName());
									
									String labelC = labelOption.substring(0,1).toUpperCase();
									String valueC = valueOption.substring(0,1).toUpperCase();
									
									labelC = labelC + labelOption.substring(1, labelOption.length());
									valueC = valueC + valueOption.substring(1, valueOption.length());
									
									String label = object.getClass().getMethod("get" + labelC).invoke(object).toString();
									String value = object.getClass().getMethod("get" + valueC).invoke(object).toString();
									
				        			out.print("<option value=\"" + value + "\">"+ label +"</option>");
	                      		}
                      		}else if(!"".equalsIgnoreCase(mapa) && mapa != null){
                      			Map ll = (Map) request.getAttribute(mapa);
                      			Set set = ll.keySet();
                      			for(Object key : set){
				        			out.print("<option value=\"" + key + "\">"+ ll.get(key) +"</option>");
	                      		}
                      		
                      		}
	                    } catch (Exception e) {
	                    	e.printStackTrace();
						}
                      %>
                            </select>
                        </span>
                    </td>
                    <td>
                        <img src="<%=request.getContextPath()%>/imagens/select_campo_direita.gif">
                    </td>
                </tr>
            </table>			