<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Cadastro de Prestadores</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="css/index.css">
</head>
<body>
	
	<div class="container d-flex flex-column align-items-center">
        <h1 class="header-title">InkTime</h1>
        
        <h2>Alcance amantes da tatuagem e administre seu negócio com maestria</h2>
        <h2>CADASTRE-SE JÁ!</h2>
        
        <%
			String msg = (String) request.getAttribute("mensagem");
			if (msg != null ) {
				boolean success = (Boolean) request.getAttribute("salvo");
				
				if (success) {
					out.println("<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">");
				} else {
					out.println("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">");
				}
				out.println(msg);
				out.println("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button></div>");
			}
		%>

        <div class="login-card col-12 col-md-6 col-lg-4">
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
                    <input type="password" class="form-control" id="confirmarSenha" name="confirmarSenha" placeholder="Digite sua senha" required>
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
                	<label for="cidade">Cidade</label>
                	<select name="cidade">
						<option value="Araraquara">Araraquara</option>
						<option value="São Carlos">São Carlos</option>
						<option value="Matão">Matão</option>
					</select>
                </div>
                
                <div class="mb-3">
                    <label for="descricao" class="form-label">Descrição</label>
                    <input type="text" class="form-control" id="descricao" name="descricao" placeholder="Descrição de no máximo 515 caracteres" required>
                </div>
                

                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-dark">Cadastrar-se</button>
                </div>
            </form>
        </div>
    </div>
    
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	
</body>
</html>