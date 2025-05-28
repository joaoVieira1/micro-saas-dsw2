#🖋️ Ink Time
Ink Time é uma plataforma web que conecta tatuadores e clientes, promovendo agilidade no agendamento, visibilidade para os artistas e uma experiência segura para quem deseja se tatuar.

##🚀 Funcionalidades

###👥 Clientes:
Cadastro e login seguro com criptografia.

Exploração de cards de tatuadores filtrados por cidade (Araraquara, São Carlos e Matão).

Visualização do perfil completo do tatuador: foto, descrição, endereço e portfólio.

Solicitação de agendamentos com escolha de horário.

Acompanhamento dos agendamentos confirmados.

###🎨 Tatuadores:
Registro completo do perfil e dados profissionais.

Upload de fotos para portfólio.

Cadastro de horários disponíveis.

Gerenciamento de solicitações de agendamento (aceitar ou recusar).

Histórico completo de agendamentos.

##🛠️ Tecnologias Utilizadas
Java EE — Servlets e JSP

HTML5 & CSS3 — Estrutura e estilo

MySQL — Banco de dados relacional

JDBC — Integração com o banco

Tomcat — Servidor de aplicações

Bootstrap — Responsividade e design moderno

BCrypt — Criptografia de senhas

##⚙️ Como Executar o Projeto

Clone o repositório:
git clone https://github.com/seuusuario/ink-time.git

Configure o banco de dados MySQL:

Crie o banco micro_saas.

Execute os scripts SQL disponíveis em /database.

Configure o context.xml do Tomcat para o DataSource:

<Resource 
    name="jdbc/MicroSaas" 
    auth="Container"
    type="javax.sql.DataSource"
    username="root"
    password=""
    driverClassName="com.mysql.cj.jdbc.Driver"
    url="jdbc:mysql://localhost:3306/micro_saas"
    maxTotal="20"
    maxIdle="10"
    minIdle="2"/>

Compile e execute o projeto no Tomcat.

##📦 Estrutura do Projeto
/src
  ├── model
  │    ├── entity
  │    ├── dao
  │    └── service
  ├── controller
  └── view (JSP)

 /database
  └── scripts.sql

 /webapp
  ├── imagens
  └── styles

##📝 Diferenciais do Ink Time
✅ Foco exclusivo em tatuadores
✅ Agendamento seguro e criptografado
✅ Filtros inteligentes por cidade
✅ Interface limpa e intuitiva
✅ Carrossel dinâmico de portfólio
