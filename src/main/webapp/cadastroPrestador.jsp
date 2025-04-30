<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>InkTime - Cadastro de Prestadores</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <style>
        body {
            background-color: #f9f9f9;
        }

        .header-title {
            font-size: 3rem;
            font-weight: bold;
            color: #343a40;
            margin-top: 40px;
        }

        .subheading {
            font-size: 1.25rem;
            color: #6c757d;
            margin-bottom: 10px;
        }

        .login-card {
            background-color: white;
            padding: 30px;
            margin-top: 30px;
            border-radius: 15px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
        }

        footer {
            margin-top: 60px;
            padding: 20px;
            text-align: center;
            color: #6c757d;
        }

        select.form-select {
            padding: 0.5rem;
        }
    </style>
</head>
<body>

<div class="container d-flex flex-column align-items-center">
    <h1 class="header-title">InkTime</h1>
    <p class="subheading text-center">Alcance amantes da tatuagem e administre seu negócio com maestria</p>
    <p class="subheading text-center fw-bold">Cadastre-se já!</p>

    <%
        String msg = (String) request.getAttribute("mensagem");
        if (msg != null ) {
            boolean success = (Boolean) request.getAttribute("salvo");
            
            if (success) {
                out.println("<div class='alert alert-success alert-dismissible fade show mt-3' role='alert'>");
            } else {
                out.println("<div class='alert alert-danger alert-dismissible fade show mt-3' role='alert'>");
            }
            out.println(msg);
            out.println("<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button></div>");
        }
    %>

    <div class="login-card col-12 col-md-8 col-lg-6">
        <form action="login.do?action=cadastroPrestador" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Digite seu email" required>
            </div>

            <div class="mb-3">
                <label for="senha" class="form-label">Senha</label>
                <input type="password" class="form-control" id="senha" name="senha" placeholder="Digite sua senha" required>
            </div>

            <div class="mb-3">
                <label for="confirmarSenha" class="form-label">Confirme sua senha</label>
                <input type="password" class="form-control" id="confirmarSenha" name="confirmarSenha" placeholder="Confirme sua senha" required>
            </div>

            <div class="mb-3">
                <label for="nome_fantasia" class="form-label">Apelido</label>
                <input type="text" class="form-control" id="nome_fantasia" name="nome_fantasia" placeholder="Digite seu apelido" required>
            </div>

            <div class="mb-3">
                <label for="nome_completo" class="form-label">Nome completo</label>
                <input type="text" class="form-control" id="nome_completo" name="nome_completo" placeholder="Digite seu nome completo" required>
            </div>

            <div class="mb-3">
                <label for="foto" class="form-label">Foto de perfil</label>
                <input type="file" class="form-control" id="foto" name="foto" accept="image/png, image/jpeg">
            </div>

            <div class="mb-3">
                <label for="endereco" class="form-label">Endereço</label>
                <input type="text" class="form-control" id="endereco" name="endereco" placeholder="Digite seu endereço" required>
            </div>

            <div class="mb-3">
                <label for="cidade" class="form-label">Cidade</label>
                <select class="form-select" id="cidade" name="cidade" required>
                    <option value="" disabled selected>Selecione sua cidade</option>
                    <option value="Araraquara">Araraquara</option>
                    <option value="São Carlos">São Carlos</option>
                    <option value="Matão">Matão</option>
                </select>
            </div>

            <div class="mb-3">
                <label for="descricao" class="form-label">Descrição</label>
                <textarea class="form-control" id="descricao" name="descricao" rows="3" maxlength="515" placeholder="Descrição de no máximo 515 caracteres" required></textarea>
            </div>

            <div class="d-grid gap-2 mt-4">
                <button type="submit" class="btn btn-dark">Cadastrar-se</button>
            </div>
        </form>
    </div>
</div>

<footer>
    &copy; 2025 InkTime - Todos os direitos reservados
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
