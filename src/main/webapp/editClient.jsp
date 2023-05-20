<%@ page import="com.example.labservlet.models.entitys.Address" %>
<%@ page import="com.example.labservlet.models.entitys.Client" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 18.05.2023
  Time: 0:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UpdateClient</title>
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

</head>
<body>
<h1>Обновление клиента</h1>
<form action="UpdateServlet" method="post">
        <input type="hidden" name="clientId" value="${client.clientId}">

        <label for="clientName">Имя клиента:</label>
        <input type="text" id="clientName" name="clientName" value="${client.clientName}" required>

        <label for="clientType">Тип клиента:</label>
        <select name="clientType" id="clientType" required>
                <c:forEach items="${clientTypes}" var="clientType">
                        <option value="${clientType}">${clientType.getDisplayName()}</option>
                </c:forEach>
        </select>

        <label for="clientAdded">Дата добавления:</label>
        <input type="date" id="clientAdded" name="clientAdded" value="${client.added}" required>

        <h2>Адреса:</h2>

        <c:forEach items="${client.addresses}" var="address" varStatus="status">
                <div id="address${status.index}">
                        <label for="addressIp${status.index}">Сетевой адрес:</label>
                        <input type="text" id="addressIp${status.index}" name="addressIp[]" value="${address.ip}" required>

                        <label for="addressMac${status.index}">Физический адрес:</label>
                        <input type="text" id="addressMac${status.index}" name="addressMac[]" value="${address.mac}" required>

                        <label for="addressModel${status.index}">Модель устройства:</label>
                        <input type="text" id="addressModel${status.index}" name="addressModel[]" value="${address.model}" required>

                        <label for="addressLocation${status.index}">Адрес места нахождения:</label>
                        <input type="text" id="addressLocation${status.index}" name="addressLocation[]" value="${address.address}" required>

                        <button type="button" onclick="deleteAddress(${status.index})">Удалить адрес</button>
                </div>
                <hr>
        </c:forEach>

        <div id="newAddressContainer"></div>
        <br><br>
        <button type="button" onclick="addNewAddress()">Добавить новый адрес</button>
        <br><br>
        <input type="submit" value="Обновить клиента">
</form>
<script>
        function deleteAddress(index) {
                var addressContainer = document.getElementById("address" + index);
                addressContainer.parentNode.removeChild(addressContainer);
        }

        function addNewAddress() {
                var newAddressContainer = document.getElementById("newAddressContainer");

                var newIndex = document.getElementsByClassName("address-container").length;

                var newAddressDiv = document.createElement("div");
                newAddressDiv.className = "address-container";
                newAddressDiv.id = "address" + newIndex;

                newAddressDiv.innerHTML = `
                    <label for="addressIp${newIndex}">Сетевой адрес:</label>
                    <input type="text" id="addressIp${newIndex}" name="addressIp[]" required>

                    <label for="addressMac${newIndex}">Физический адрес:</label>
                    <input type="text" id="addressMac${newIndex}" name="addressMac[]" required>

                    <label for="addressModel${newIndex}">Модель устройства:</label>
                    <input type="text" id="addressModel${newIndex}" name="addressModel[]" required>

                    <label for="addressLocation${newIndex}">Адрес места нахождения:</label>
                    <input type="text" id="addressLocation${newIndex}" name="addressLocation[]" required>

                    <button type="button" onclick="deleteAddress(${newIndex})">Удалить адрес</button>
                `;

                newAddressContainer.appendChild(newAddressDiv);
        }
</script>

</body>


</html>
