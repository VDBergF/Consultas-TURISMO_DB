<%@ page import="DataBase.DBConnect" %><%--
  Created by IntelliJ IDEA.
  User: berg
  Date: 03/02/17
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
    String opcao = request.getParameter("OPCAO");
    DBConnect dbConnect = new DBConnect(opcao);
    String titulo = "Banco de Dados ";
    if (opcao.equals("op1")) {
        titulo += "Oracle";
    } else {
        titulo += "Postgres";
    }
    %>
    <title>Consultas</title>
<body>

<h1><%=titulo%></h1>
<hr />
<p><b>Consultas disponíveis</b></p>

<a href="Consultas/Hotel.jsp"><b>1.</b> Quais hotéis de uma cidade possuem restaurante?</a><br>
<a href="./Consultas/Restaurante.jsp"><b>2.</b> Qual a especialidade de um restaurante?</a><br>
<a href="./Consultas/Cidade.jsp"><b>3.</b> Qual o valor médio de quartos de luxo em todos os hotéis de uma cidade?</a><br>
<a href="./Consultas/Casashow.jsp"><b>4.</b> Quais casas de show possuem restaurante?</a><br>
<a href="./Consultas/Museu.jsp"><b>5.</b> Qual o fundador de um museu?</a><br>
<a href="./index.jsp"><b>6.</b> Qual a data de construção de uma igreja?</a><br>
<a href="./index.jsp"><b>7.</b> Que cidades possuem população menor que X habitantes?</a><br>
<a href="./index.jsp"><b>8.</b> Que cidade oferece o maior número de pontos turisticos?</a><br>

</body>
</html>
