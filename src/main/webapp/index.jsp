<%@ page import="com.tictactoe.Sign" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/main.css">
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <script src="<c:url value='/static/jquery-3.6.0.min.js'/>"></script>
    <title>Tic-Tac-Toe</title>
</head>
<body>
<h1>Tic-Tac-Toe</h1>

<table id="game-table">
    <c:forEach var="i" begin="0" end="8">
        <c:if test="${i % 3 == 0}"><tr></c:if>
        <td
                <c:choose>
                    <c:when test="${data.get(i) == 'EMPTY' && empty winner && !draw}">
                        onclick="window.location='/logic?click=${i}'"
                    </c:when>
                    <c:otherwise>
                        class="disabled"
                    </c:otherwise>
                </c:choose>
        >
                ${data.get(i).getSign()}
        </td>
        <c:if test="${i % 3 == 2}"></tr></c:if>
    </c:forEach>
</table>

<hr>

<c:set var="CROSSES" value="<%=Sign.CROSS%>"/>
<c:set var="NOUGHTS" value="<%=Sign.NOUGHT%>"/>

<c:if test="${winner == CROSSES}">
    <h1>CROSSES WIN!</h1>
    <button onclick="restart()">Start again</button>
</c:if>
<c:if test="${winner == NOUGHTS}">
    <h1>NOUGHTS WIN!</h1>
    <button onclick="restart()">Start again</button>
</c:if>
<c:if test="${draw}">
    <h1>IT'S A DRAW</h1>
    <button onclick="restart()">Start again</button>
</c:if>

<h2>Выбери уровень сложности</h2>
<form action="settings" method="post">
    <select name="difficulty">
        <option value="1" <c:if test="${ai.difficulty == 1}">selected</c:if>>Лёгкий</option>
        <option value="2" <c:if test="${ai.difficulty == 2}">selected</c:if>>Средний</option>
        <option value="3" <c:if test="${ai.difficulty == 3}">selected</c:if>>Сложный</option>
    </select>
    <button type="submit">Сохранить</button>
</form>

<br>

<div class="current-difficulty">
    Текущая сложность: <c:choose>
    <c:when test="${ai.difficulty == '1'}">
        Лёгкая
    </c:when>
    <c:when test="${ai.difficulty == '2'}">
        Средняя
    </c:when>
    <c:when test="${ai.difficulty == '3'}">
        Сложная
    </c:when>
    </c:choose>
</div>

<script>
    function restart() {
        $.ajax({
            url: '/restart',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            async: false,
            success: function () {
                location.reload();
            }
        });
    }
</script>

</body>
</html>
