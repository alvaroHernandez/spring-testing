<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1>Insert Person</h1>
<c:when test="${not empty error}">
    <div id="personInsertionError" class="page-action error">
        {$error}
    </div>
</c:when>
</body>
</html>