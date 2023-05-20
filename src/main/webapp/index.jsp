<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Список клиентов</title>
</head>
<body>
<h1>Список клиентов</h1>

<form action="FilterServlet" method="GET">
    <label for="clientType">Тип клиента:</label>
    <select name="clientType" id="clientType" >
        <option value="">Тип клиента</option>
        <c:forEach items="${clientTypes}" var="clientType">
            <option value="${clientType}" ${selectedClientType == clientType ? 'selected' : ''}>${clientType.getDisplayName()}</option>
        </c:forEach>
    </select>
    <label for="searchText">Поиск:</label>
    <input type="text" name="searchText" id="searchText" placeholder="Введите текст для поиска" value="${empty searchText ? '' : searchText}">

    <button type="submit">Применить фильтр</button>
</form>

<table border="1">
    <tr>
        <th>Имя клиента</th>
        <th>Тип клиента</th>
        <th>Added Date</th>
        <th>IP Адрес</th>
        <th>MAC Адрес</th>
        <th>Модель</th>
        <th>Физический адрес</th>
        <th>Действие</th>
    </tr>

    <c:forEach var="client" items="${clients}">
        <c:set var="rowSpan" value="${fn:length(client.addresses)}" />
        <c:forEach var="address" items="${client.addresses}" varStatus="loop">
            <tr>
                <c:if test="${loop.index == 0}">
                    <td rowspan="${rowSpan}">${client.clientName}</td>
                    <td rowspan="${rowSpan}">${client.type.getDisplayName()}</td>
                    <td rowspan="${rowSpan}">${client.added}</td>
                </c:if>
                <td>${address.ip}</td>
                <td>${address.mac}</td>
                <td>${address.model}</td>
                <td>${address.address}</td>
                <c:if test="${loop.index == 0}">
                    <td rowspan="${rowSpan}">
                        <form action="UpdateServlet" method="GET">
                            <input type="hidden" name="clientId" value="${client.clientId}">
                            <button type="submit">Обновить клиента</button>
                        </form>
                        <form action="DeleteServlet" method="POST">
                            <input type="hidden" name="clientId" value="${client.clientId}">
                            <button type="submit">Удалить клиента</button>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </c:forEach>
</table>
<br><br>
<form action="CreateServlet" method="get">
    <button type="submit">Добавить нового клиента</button>
</form>
</body>
</html>
