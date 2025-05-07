<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Imagem de Serviço</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
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
        box-shadow: 0 4px 20px rgba(0,0,0,0.1);
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

    .alert {
        margin-bottom: 1rem;
    }
</style>
    
    
</head>
<body>
    <div class="form-container">
        <h2>Cadastrar Imagem de Serviço</h2>

        <%
            String msg = (String) request.getAttribute("mensagem");
            if (msg != null ) {
                boolean success = (Boolean) request.getAttribute("salvo");
        %>
                <div class="alert alert-<%= success ? "success" : "danger" %> alert-dismissible fade show" role="alert">
                    <%= msg %>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
        <%
            }
        %>

        <form action="prestador.do?action=postarTatuagem" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="descricao" class="form-label">Titulo</label>
                <input type="text" class="form-control" name="descricao" id="descricao" required>
            </div>

            <div class="mb-3">
                <label for="fotoTatuagem" class="form-label">Selecionar Imagem</label>
                <input type="file" class="form-control" name="fotoTatuagem" id="fotoTatuagem" accept="image/*" required>
            </div>

            <button type="submit" class="btn btn-dark">Salvar Imagem</button>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
