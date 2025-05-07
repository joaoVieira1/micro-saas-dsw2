<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.microsaas.tattoo.model.entity.Prestador"%>
<%@ page import="com.microsaas.tattoo.model.entity.ImagemServico"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>

<%
	Prestador prestador = (Prestador) session.getAttribute("prestadorLogado");
	System.out.println("homePrestador" + prestador.getCaminhoFoto());
	List<ImagemServico> imagens = (List<ImagemServico>) session.getAttribute("imagens");
	request.setAttribute("imagens", imagens);
%>


<html>
<head>
<title>Detalhes do Prestador</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

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

    .perfil-container {
        max-width: 800px;
        background-color: white;
        margin: auto;
        border-radius: 1rem;
        padding: 2rem;
        box-shadow: 0 4px 20px rgba(0,0,0,0.1);
    }

    .perfil-container img {
        display: block;
        margin: auto;
        border-radius: 8px;
        max-width: 200px;
        height: auto;
        box-shadow: 0 2px 8px rgba(0,0,0,0.1);
    }

    .perfil-info p {
        font-size: 1.1rem;
        margin: 1rem 0;
    }

    .btn-container {
        display: flex;
        gap: 1rem;
        flex-direction: column;
        align-items: center;
        margin-top: 1.5rem;
        margin-bottom: 2rem;
    }

    .btn-container a {
        width: 100%;
        max-width: 300px;
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
        box-shadow: 0 2px 12px rgba(0,0,0,0.05);
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
    <div class="perfil-container">
        <h2>Área do Tatuador</h2>

        <div>
            <img src="<%=prestador.getFoto()%>" alt="Foto do perfil" />
        </div>

        <div class="perfil-info">
            <p><strong>Nome Fantasia:</strong> <%=prestador.getNomeFantasia()%></p>
            <p><strong>Nome Completo:</strong> <%=prestador.getNomeCompleto()%></p>
        </div>
        
        <form action="login.do?action=logout" method="post" style="display: inline;">
    		<button type="submit" class="btn btn-outline-danger">Logout</button>
		</form>
        

        <div class="btn-container">
            <a href="prestador.do?action=getFormTatuagem" class="btn btn-outline-primary">Cadastrar Tatuagem ao portfólio</a>
            <a href="prestador.do?action=getFormHorario" class="btn btn-outline-success">Cadastrar Horário</a>
        </div>

        <div class="img-galeria">
            <c:forEach var="img" items="${imagens}">
                <div class="img-item">
                    <img src="${img.foto}" alt="Imagem do serviço" />
                    <p><strong>Descrição:</strong> ${img.descricao}</p>
                </div>
            </c:forEach>
        </div>

        <c:if test="${empty imagens}">
            <p class="sem-imagens">Nenhuma imagem cadastrada.</p>
        </c:if>
    </div>
</body>

</html>