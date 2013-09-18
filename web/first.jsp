<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Otsi Amazonist</h1>
        <jsp:include page="amaz.jsp" />
        <jsp:include page="./WEB-INF/jspf/products.jsp" />
        <jsp:include page="./WEB-INF/jspf/pages.jsp" />
        
    </body>
</html>
