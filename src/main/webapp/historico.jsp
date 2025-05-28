<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.microsaas.tattoo.model.entity.HistoricoAgendamento"%>
<%@ page import="java.util.List"%>

<%
List<HistoricoAgendamento> listaHistorico = (List<HistoricoAgendamento>) request.getAttribute("listaHistorico");
%>


<html>
<head>
    <title>Histórico de Agendamentos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container py-5">
    <h2 class="mb-4 text-center">Histórico de Agendamentos</h2>

    <table class="table table-bordered table-striped bg-white shadow-sm">
        <thead class="table-dark">
            <tr>
                <th>Data da Alteração</th>
                <th>Data do Agendamento</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${listaHistorico}">
                <tr>
                    <td>${item.dataAlteracaoFormatada}</td>
                    <td>${item.dataAgendamentoFormatada}</td>
                    <td>
                        <span class="badge
                            <c:choose>
                                <c:when test="${item.status == 'ACEITO'}">bg-success</c:when>
                                <c:when test="${item.status == 'CANCELADO'}">bg-danger</c:when>
                                <c:when test="${item.status == 'SOLICITADO'}">bg-warning text-dark</c:when>
                                <c:otherwise>bg-secondary</c:otherwise>
                            </c:choose>">
                            ${item.status}
                        </span>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
