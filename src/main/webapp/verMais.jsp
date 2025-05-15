<%@ page import="com.microsaas.tattoo.model.entity.ImagemServico"%>
<%@ page import="com.microsaas.tattoo.model.entity.Agenda"%>
<%@ page import="com.microsaas.tattoo.model.entity.Prestador"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>

<%
List<ImagemServico> imagens = (List<ImagemServico>) request.getAttribute("imagens");
List<Agenda> horarios = (List<Agenda>) request.getAttribute("horarios");
Prestador prestador = (Prestador) request.getAttribute("prestador");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=prestador.getNomeFantasia()%> - Perfil</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background-color: #f8f9fa;
}

.title-portfolio{
	display: flex;
	justify-content: center;
	align-items: center;
}

h2{
font-family: 'Segoe UI', sans-serif;
            font-size: 2.5rem;}

.perfil-card {
	max-width: 1000px;
	margin: 40px auto;
	padding: 30px;
	background-color: #fff;
	border-radius: 16px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.perfil-foto {
	width: 180px;
	height: 180px;
	border-radius: 50%;
	object-fit: cover;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.dados-texto p {
	margin: 5px 0;
}

.portfolio-title {
	margin-top: 50px;
	font-weight: bold;
	font-size: 24px;
	color: #333;
	text-align: center;
}

.carousel-item img {
	object-fit: cover;
	height: 400px;
}

.img-galeria {
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
	gap: 2rem;
}

.img-item {
	background-color: #fff;
	padding: 1rem;
	border-radius: 0.75rem;
	box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
	text-align: center;
}

.img-item img {
	max-width: 100%;
	border-radius: 8px;
}

.img-item p {
	margin-top: 0.5rem;
	font-size: 0.95rem;
}
</style>
</head>
<body>

	<div class="perfil-card">
		<div class="row align-items-center">
			<div class="col-md-4 text-center">
				<img src="imagens.do?nomeFoto=<%=prestador.getFoto()%>"
					alt="Foto do Tatuador" class="perfil-foto">
			</div>
			<div class="col-md-8 dados-texto">
				<h2><%=prestador.getNomeFantasia()%></h2>
				<p>
					<strong>Nome completo:</strong>
					<%=prestador.getNomeCompleto()%></p>
				<p>
					<strong>Cidade:</strong>
					<%=prestador.getCidadePrestador()%></p>
				<p>
					<strong>Endereço:</strong>
					<%=prestador.getEndereco()%></p>
				<p>
					<strong>Sobre:</strong>
					<%=prestador.getDescricao() %></p>
				<a href="cliente.do?action=verHorarios&id=${prestador.id}" class="btn btn-outline-dark mt-2">Agende seu horário</a>
			</div>
		</div>
	</div>
	
	<div class="title-portfolio">
		<h2>PORTFÓLIO</h2>
	</div>
	
	
	<div id="carouselPortfolio" class="carousel slide" style="max-width: 600px; margin: auto;">
	    <div class="carousel-inner">
	
	        <c:forEach var="imagem" items="${imagens}" varStatus="status">
	            <div class="carousel-item ${status.first ? 'active' : ''}">
	                <div class="d-flex flex-column align-items-center p-3">
	                    <img src="imagensPort.do?nomeFoto=${imagem.foto}" class="d-block w-100" style="max-height: 400px; object-fit: contain;" />
	                    <p class="mt-2 text-center">${imagem.descricao}</p>
	                </div>
	            </div>
	        </c:forEach>
	
	    </div>
	
	    <!-- Botões de navegação -->
	    <button class="carousel-control-prev" type="button" data-bs-target="#carouselPortfolio" data-bs-slide="prev">
	        <span class="carousel-control-prev-icon bg-dark rounded-circle p-2" aria-hidden="true"></span>
	        <span class="visually-hidden">Anterior</span>
	    </button>
	    <button class="carousel-control-next" type="button" data-bs-target="#carouselPortfolio" data-bs-slide="next">
	        <span class="carousel-control-next-icon bg-dark rounded-circle p-2" aria-hidden="true"></span>
	        <span class="visually-hidden">Próximo</span>
	    </button>
	</div>
	
	

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
