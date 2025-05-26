<%@ page import="com.microsaas.tattoo.model.entity.Agenda"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%
List<Agenda> horariosAgendados = (List<Agenda>) request.getAttribute("horariosAgendados");
System.out.print("horariosAgendados[front]: " + horariosAgendados + "\n");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Horários Agendados</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background-color: #f9f9f9;
	font-family: 'Segoe UI', sans-serif;
}

.container {
	margin-top: 40px;
}

.card {
	border-radius: 12px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
	margin-bottom: 20px;
	background-color: #fff;
}

.titulo {
	text-align: center;
	margin-bottom: 30px;
}
</style>
</head>
<body>

	<div class="container">
		<h1 class="titulo">Horários Agendados</h1>

		<c:forEach var="horario" items="${horariosAgendados}">
			<div class="card p-3">
				<div class="card-body">
					<h5 class="card-title">Data: ${horario.dataHoraFormatada}</h5>
				</div>
				<a href="cliente.do?action=desocuparHorario&id=${horario.id} "
					class="btn btn-outline-danger">Desocupar Horário</a>
			</div>
		</c:forEach>


	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
