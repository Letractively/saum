<%@ tag pageEncoding="UTF-8" %>
<%@ tag body-content="scriptless" %>
<%-- use "body-content=empty" para tags sem corpo --%>
<%@ attribute name="size" type="java.lang.Integer" %>
<%@ attribute name="color" %>
<b><font color="${color}" size="+${size}">
<jsp:doBody />
</font></b>