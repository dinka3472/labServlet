<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Добавление нового клиента</title>
    <script>
        var addressRowCount = 1;

        function addAddressRow() {
            var addressesContainer = document.getElementById("addresses");

            var newAddressRow = document.createElement("div");
            newAddressRow.classList.add("address");

            newAddressRow.innerHTML = `
<label for="addressIp${addressRowCount}">Сетевой адрес:</label>
<input type="text" id="addressIp${addressRowCount}" name="addressIp[]" required>


<label for="addressMac${addressRowCount}">Физический адрес:</label>
<input type="text" id="addressMac${addressRowCount}" name="addressMac[]" required>


<label for="addressModel${addressRowCount}">Модель устройства:</label>
<input type="text" id="addressModel${addressRowCount}" name="addressModel[]" required>


<label for="addressLocation${addressRowCount}">Адрес места нахождения:</label>
<input type="text" id="addressLocation${addressRowCount}" name="addressLocation[]" required>

<hr>
`;

            addressesContainer.appendChild(newAddressRow);
            addressRowCount++;
        }
    </script>
    <style>
        .address label {
            display: block;
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
<h1>Добавление нового клиента</h1>
<form action="CreateServlet" method="post">
    <div>
        <label for="clientName">Наименование клиента:</label>
        <input type="text" id="clientName" name="clientName" required>

        <label for="clientType">Тип клиента:</label>
        <select name="clientType" id="clientType" required>
            <c:forEach items="${clientTypes}" var="clientType">
                <option value="${clientType}">${clientType.getDisplayName()}</option>
            </c:forEach>
        </select>

        <label for="clientAdded">Дата добавления:</label>
        <input type="date" id="clientAdded" name="clientAdded" required>
    </div>

    <h2>Адреса:</h2>

    <div id="addresses">
        <div class="address">
            <label for="addressIp0">Сетевой адрес:</label>
            <input type="text" id="addressIp0" name="addressIp[]" required>

            <label for="addressMac0">Физический адрес:</label>
            <input type="text" id="addressMac0" name="addressMac[]" required>

            <label for="addressModel0">Модель устройства:</label>
            <input type="text" id="addressModel0" name="addressModel[]" required>

            <label for="addressLocation0">Адрес места нахождения:</label>
            <input type="text" id="addressLocation0" name="addressLocation[]" required>

            <hr>
        </div>
    </div>

    <button type="button" onclick="addAddressRow()">Добавить адрес</button>

    <input type="submit" value="Добавить клиента">
</form>
</body>
</html>