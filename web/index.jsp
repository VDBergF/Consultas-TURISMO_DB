<%--
  Created by IntelliJ IDEA.
  User: berg
  Date: 03/02/17
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Consultas</title>
</head>
<body>

<h1>Bem-Vindo!</h1>
<hr />

<p>Escolha o banco de dados que deseja fazer consultas:</p>

<form method="GET" action="DataBaseConnection.jsp">
  <INPUT TYPE="RADIO" NAME="OPCAO" VALUE="op1" checked> Oracle
  <INPUT TYPE="RADIO" NAME="OPCAO" VALUE="op2"> Postgres
  <p><INPUT TYPE="SUBMIT" NAME="Botão" VALUE="Confirmar"></p>
</form>

</body>
</html>
