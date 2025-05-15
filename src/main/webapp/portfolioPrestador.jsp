<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.microsaas.tattoo.model.entity.Prestador"%>
<%@ page import="com.microsaas.tattoo.model.entity.ImagemServico"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
    
<%
List<ImagemServico> imagens = (List<ImagemServico>) session.getAttribute("imagens");
request.setAttribute("imagens", imagens);
%>

<html>
<head>
<title>Portfólio</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background: linear-gradient(135deg, #f3f4f6, #e0e0e0);
	font-family: 'Segoe UI', sans-serif;
	margin: 0;
	padding: 2rem;
	color: #343a40;
}

h2 {
	text-align: center;
	font-size: 2.2rem;
	margin-bottom: 2rem;
	color: #212529;
}

.portfolio {
	max-width: 800px;
	background-color: white;
	margin: auto;
	border-radius: 1rem;
	padding: 2rem;
	box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
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

.sem-imagens {
	text-align: center;
	color: #6c757d;
	font-style: italic;
	margin-top: 2rem;
}
</style>
</head>
<body>
	
	<h2>Seu Portfólio</h2>
	
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