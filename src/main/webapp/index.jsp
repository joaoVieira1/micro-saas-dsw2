<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>InkTime - Página Inicial</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #f3f4f6, #e0e0e0);
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }

        .container {
            max-width: 400px;
            margin: auto;
            padding: 2rem;
        }

        .header-title {
            text-align: center;
            font-family: 'Segoe UI', sans-serif;
            font-size: 2.5rem;
            margin-bottom: 2rem;
            color: #343a40;
        }

        .login-card {
            background-color: white;
            border-radius: 1rem;
            padding: 2rem;
            box-shadow: 0 4px 20px rgba(0,0,0,0.1);
            animation: fadeIn 0.4s ease-in-out;
        }

        .btn {
            padding: 0.75rem;
            font-weight: 500;
        }

        .form-label {
            font-weight: 500;
        }

        footer {
            text-align: center;
            padding: 1rem;
            margin-top: auto;
            background-color: #f8f9fa;
            font-size: 0.9rem;
            color: #6c757d;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-10px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .alert {
            margin-top: 1rem;
        }
    </style>
</head>
<body>

    <div class="container text-center">
        <h1 class="header-title">InkTime</h1>

        <%
            String msg = (String) request.getAttribute("mensagem");
            if (msg != null) {
        %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <%= msg %>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        <%
            }
        %>

        <div class="login-card mt-3">
            <form action="login.do?action=login" method="post">
                <div class="mb-3 text-start">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Digite seu email" required>
                </div>

                <div class="mb-4 text-start">
                    <label for="senha" class="form-label">Senha</label>
                    <input type="password" class="form-control" id="senha" name="senha" placeholder="Digite sua senha" required>
                </div>

                <div class="d-grid gap-2 mb-2">
                    <button type="submit" class="btn btn-dark">Entrar</button>
                </div>
                <div class="d-grid gap-2">
                    <a href="login.do?action=getCadastroCliente" class="btn btn-outline-primary">Cadastre-se como cliente</a>
                    <a href="login.do?action=getCadastroPrestador" class="btn btn-outline-success">Cadastre-se como tatuador</a>
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
