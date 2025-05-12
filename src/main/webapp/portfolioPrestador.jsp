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
	
	<div class="portfolio">
	
		<div class="img-galeria">
			<c:forEach var="imagem" items="${imagens}">
				<div class="img-item">
					<img src="imagem?tipo=servico&nome=${imagem.foto}" width="200" />
					<p>${imagem.descricao}</p>
				</div>
			</c:forEach>
		</div>

		<c:if test="${empty imagens}">
			<p class="sem-imagens">Nenhuma imagem cadastrada.</p>
		</c:if>
	
	</div>
	
</body>
</html>