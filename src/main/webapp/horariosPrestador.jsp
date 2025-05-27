<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.microsaas.tattoo.model.entity.Agenda"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>

<%
List<Agenda> agendamentos = (List<Agenda>) request.getAttribute("agendamentos");
System.out.print("Agendamentos[front]: " + agendamentos + "\n");
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>Horários do Prestador</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
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
</head>
<body>

	<div class="container">
		<h1 class="titulo">Horários Cadastrados</h1>

		<c:forEach var="agendamento" items="${agendamentos}">
			<div class="card p-3">
				<div class="card-body">
					<h5 class="card-title">Data: ${agendamento.dataHoraFormatada}
					</h5>
					<p class="card-text">
						Status: <span
							class="badge 
                        ${agendamento.status == 'DESOCUPADO' ? 'badge-success' : 'badge-danger'}">
							${agendamento.status} </span>
					</p>
					<c:choose>
						<c:when test="${agendamento.clienteId != 0}">
							<c:if test="${!agendamento.aceito}">
								<form
									action="prestador.do?action=confirmarAgenda&id=${agendamento.id}"
									method="post" class="d-inline">
									<input type="hidden" name="id" value="${agendamento.id}" />
									<button type="submit" class="btn btn-success btn-sm">Confirmar</button>
								</form>
							</c:if>

							<c:if test="${agendamento.aceito}">
								<form
									action="prestador.do?action=desconfirmarAgenda&id=${agendamento.id}"
									method="post" class="d-inline">
									<input type="hidden" name="id" value="${agendamento.id}" />
									<button type="submit" class="btn btn-warning btn-sm">Desconfirmar</button>
								</form>
							</c:if>
						</c:when>
						<c:otherwise>
							<p class="text-muted">Horário vago</p>
						</c:otherwise>
					</c:choose>


				</div>
			</div>
		</c:forEach>

		<c:if test="${empty agendamentos}">
			<div class="alert alert-info text-center">Nenhum agendamento
				encontrado.</div>
		</c:if>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
