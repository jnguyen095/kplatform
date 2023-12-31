<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
<head>
    <title>Home page</title>
</head>
<body>

    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Welcome ${name}</h1>
            <c:forEach items="${products}" var="product">
                <p>${product.title}</p>
            </c:forEach>
        </div>
    </div>

</body>
</html>