<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.microsaas.tattoo.model.entity.Prestador" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>

<%
    List<Prestador> prestadores = (List<Prestador>) request.getAttribute("prestadores");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ink Time - Tatuadores</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
        }

        h1 {
            text-align: center;
            margin-bottom: 40px;
            color: #333;
        }

        .filtro-form {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 10px;
            margin-bottom: 40px;
        }

        .card {
            margin-bottom: 30px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s ease-in-out;
        }

        .card:hover {
            transform: scale(1.01);
        }

        .card-img-top {
            height: 200px;
            object-fit: cover;
        }

        .card-title {
            font-weight: bold;
            font-size: 1.25rem;
        }

        .card-text {
            margin-bottom: 8px;
        }
        .btn-container{
        	display: flex;
        	align-items: center;
        	justify-content: center;
        }
    </style>
</head>
<body>

    <h1>Ink Time - Conheça os Tatuadores</h1>

    <form method="get" action="cliente.do" class="filtro-form">
    	<input type="hidden" name="action" value="filtrarTatuadores">
    	Filtrar por cidade:
    <select name="cidade">
        <option value="">-- Todas --</option>
        <option value="Araraquara" ${cidadeSelecionada == 'Araraquara' ? 'selected' : ''}>Araraquara</option>
        <option value="São Carlos" ${cidadeSelecionada == 'São Carlos' ? 'selected' : ''}>São Carlos</option>
        <option value="Matão" ${cidadeSelecionada == 'Matão' ? 'selected' : ''}>Matão</option>
    </select>

    <input type="submit" value="Filtrar" class="btn btn-dark">
	</form>

    <div class="container">
        <div class="row">
            <c:forEach var="prestador" items="${prestadores}">
                <div class="col-md-4">
                    <div class="card">
                        <img src="https://via.placeholder.com/400x200.png?text=Foto+do+Tatuador" class="card-img-top" alt="Foto do Tatuador">
                        <div class="card-body">
                            <h5 class="card-title">${prestador.nomeFantasia}</h5>
                            <p class="card-text"><strong>Nome completo:</strong> ${prestador.nomeCompleto}</p>
                            <p class="card-text"><strong>Cidade:</strong> ${prestador.cidadePrestador}</p>
                            <p class="card-text"><strong>Sobre:</strong> ${prestador.descricao}</p>
                            <a href="verPrestador.do?id=${prestador.id}" class="btn btn-dark">Ver mais</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
	
		<div class="btn-container">
			<form action="login.do?action=logout" method="post">
				<button type="submit" class="btn btn-outline-danger">Logout</button>
			</form>
		</div>
	
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
