<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
<h1>Insert Person</h1>
<c:if test="${not empty error}">
    <div id="personInsertionError" class="page-action error">
        ${error}
    </div>
</c:if>
<form:form method="POST" action="/person" modelAttribute="person">
    <table>
        <tr>
            <td><form:label path="firstName">Name</form:label></td>
            <td><form:input path="firstName"  id="firstName"/></td>
        </tr>
        <tr>
            <td><form:label path="document">Document Number</form:label></td>
            <td><form:input path="document" id="document"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit" id="submitButton"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>