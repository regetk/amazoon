<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table>
    <thead><td>ASIN</td><td>Title</td><td>Price</td></thead>
<%-- TODO 13 kaupa --%>
<c:forEach var="prod" items="${stuff.fItems}">
<tr>
    <td>
        ${prod.itemId}
    </td>
    <td>
        ${prod.itemAttr.itemTitle}
    </td>
    <td>
        ${prod.offerSummary.lowestNewPrice.amount}
    </td>

    
</tr>
</c:forEach>
</table>