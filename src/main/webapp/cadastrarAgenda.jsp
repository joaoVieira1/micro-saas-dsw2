<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Horário</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background: linear-gradient(135deg, #f3f4f6, #e0e0e0);
	font-family: 'Segoe UI', sans-serif;
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
}

.form-container {
	background-color: white;
	padding: 2rem;
	border-radius: 1rem;
	box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
	width: 100%;
	max-width: 500px;
}

h2 {
	text-align: center;
	margin-bottom: 2rem;
	color: #343a40;
}

.form-label {
	font-weight: 500;
}

.btn {
	width: 100%;
	margin-top: 1rem;
}
</style>

</head>
<body>
	<div class="form-container">
		<h2>Cadastrar Horário Disponível</h2>

		<form action="prestador.do?action=publicarHorario" method="post">
			<div class="mb-3">
				<label for="data_hora" class="form-label">Data e Hora:</label> <input
					type="datetime-local" class="form-control" id="data_hora"
					name="data_hora" required>
			</div>

			<button type="submit" class="btn btn-dark">Cadastrar</button>
		</form>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>