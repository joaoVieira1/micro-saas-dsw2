<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>InkTime - Cadastro de Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
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
    </style>
</head>
<body>

<div class="container d-flex flex-column align-items-center">
    <h1 class="header-title">InkTime</h1>
    <p class="subheading text-center">Fique por dentro da comunidade Ink da sua região</p>
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
        <form action="login.do?action=cadastroCliente" method="post">
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
                <label for="nome" class="form-label">Nome completo</label>
                <input type="text" class="form-control" id="nome" name="nome" placeholder="Digite seu nome completo" required>
            </div>

            <div class="mb-3">
                <label for="cpf" class="form-label">CPF</label>
                <input type="text" class="form-control" id="cpf" name="cpf" placeholder="XXX.XXX.XXX-XX" required>
            </div>

            <div class="mb-3">
                <label for="endereco" class="form-label">Endereço</label>
                <input type="text" class="form-control" id="endereco" name="endereco" placeholder="Digite seu endereço" required>
            </div>

            <div class="mb-3">
                <label for="contato" class="form-label">Contato</label>
                <input type="text" class="form-control" id="contato" name="contato" placeholder="(XX) XXXXX-XXXX" required>
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
