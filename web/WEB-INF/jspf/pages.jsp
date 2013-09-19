<%-- 
    Document   : pages
    Created on : Sep 18, 2013, 11:43:35 AM
    Author     : reget.kalamees
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul >
<c:forEach var="page" begin="1" end="${cPages}" step="1">
    <li style="display: inline;"><a class="pageLink" href="?page=${page}&fWord=${saadetis.fWord}&sCurr=${saadetis.cUnit}">${page}</a></li>
</c:forEach>
</ul>