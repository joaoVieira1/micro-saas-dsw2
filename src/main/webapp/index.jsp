<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>InkTime - Página Inicial</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/index.css">
</head>
<body>

    <div class="container d-flex flex-column align-items-center">
        <h1 class="header-title">InkTime</h1>
        
        <%
			String msg = (String) request.getAttribute("mensagem");
			if (msg != null ) {
				out.println("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">");
				out.println(msg);
				out.println("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button></div>");
			}
		%>

        <div class="login-card col-12 col-md-6 col-lg-4">
            <form action="login.do?action=login" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Digite seu email" required>
                </div>

                <div class="mb-3">
                    <label for="senha" class="form-label">Senha</label>
                    <input type="password" class="form-control" id="senha" name="senha" placeholder="Digite sua senha" required>
                </div>

                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-dark">Entrar</button>
                    <a href="login.do?action=getCadastroCliente" class="btn btn-outline-secondary">Cadastre-se como cliente</a>
                    <a href="login.do?action=getCadastroPrestador" class="btn btn-outline-secondary">Cadastre-se como tatuador</a>
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
