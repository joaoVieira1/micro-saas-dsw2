<%@ page import="com.microsaas.tattoo.model.entity.Agenda" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%
	List<Agenda> horarios = (List<Agenda>) request.getAttribute("horarios");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Horários</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<style>
        body {
            background-color: #f5f5f5;
            font-family: 'Segoe UI', sans-serif;
        }
        .container {
            margin-top: 40px;
        }
        .card {
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
            background-color: #ffffff;
        }
        .card h5 {
            font-weight: 600;
        }
        .badge {
            font-size: 0.9rem;
            padding: 0.5em 0.8em;
        }
        .badge-success {
            background-color: #198754;
        }
        .badge-danger {
            background-color: #dc3545;
        }
        .titulo {
            text-align: center;
            margin-bottom: 30px;
        }
    </style>
<body>
	
	<div class="container">
    <h1 class="titulo">Horários Disponíveis</h1>

    <c:forEach var="horario" items="${horarios}">
        <div class="card p-3">
            <div class="card-body">
                <h5 class="card-title">
                    Data: ${horario.dataHoraFormatada}
                </h5>
                <p class="card-text">
                    Status:
                    <span class="badge 
                        ${horario.status == 'DESOCUPADO' ? 'badge-success' : 'badge-danger'}">
                        ${horario.status}
                    </span>
                </p>
            </div>
        </div>
    </c:forEach>

    <c:if test="${empty horarios}">
        <div class="alert alert-info text-center">Nenhum agendamento encontrado.</div>
    </c:if>
</div>
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

	
</body>
</html>