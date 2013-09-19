<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table>
    <thead><td>ASIN</td><td>Title</td><td>Price</td></thead>
    <c:forEach var="prod" items="${stuff.fItems}">
    <tr>
        <td>
            ${prod.itemId}
        </td>
        <td>
            ${prod.itemAttr.itemTitle}
        </td>
        <td>
            <input type="hidden" class="prices" value="${prod.offerSummary.lowestNewPrice.amount}" /> 
            <span></span>
        </td>
    </tr>
</c:forEach>
</table>