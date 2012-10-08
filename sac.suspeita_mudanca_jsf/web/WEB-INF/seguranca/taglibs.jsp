<%@ page language="java" session="true" %>
<%//@page import="br.com.omni.gap.pojo.Usuario"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display" %>
<%
//Usuario usr = (Usuario) session.getAttribute("USUARIO");
//if(usr == null){
%>
<c:redirect url="/index.do"/>
<%//}%>