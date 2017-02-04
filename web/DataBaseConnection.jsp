<%@ page import="DataBase.DBConnect" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.sun.org.apache.bcel.internal.generic.SWITCH" %>
<%@ page import="Consultas.Consulta" %><%--
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
    String db = request.getParameter("OPCAO");
    DBConnect dbConnect = new DBConnect(db);
    Consulta consulta = new Consulta(dbConnect.getConnection(db));
    consulta.hoteisComRestaurante();

    String titulo = "Banco de Dados ";
    if (db.equals("op1")) titulo += "Oracle";
    else titulo += "Postgres";
    %>

    <title>Consultas</title>
<body>

<h1><%=titulo%></h1>
<hr />
<p><b>Consultas disponíveis</b></p>

<%--<a href="Consultas/Hotel.jsp"><b>1.</b> Quais hotéis de uma cidade possuem restaurante?</a><br>--%>
<%--<a href="./Consultas/Restaurante.jsp"><b>2.</b> Qual a especialidade de um restaurante?</a><br>--%>
<%--<a href="./Consultas/Cidade.jsp"><b>3.</b> Qual o valor médio de quartos de luxo em todos os hotéis de uma cidade?</a><br>--%>
<%--<a href="./Consultas/Casashow.jsp"><b>4.</b> Quais casas de show possuem restaurante?</a><br>--%>
<%--<a href="./Consultas/Museu.jsp"><b>5.</b> Qual o fundador de um museu?</a><br>--%>
<%--<a href="./Consultas/Igreja.jsp"><b>6.</b> Qual a data de construção de uma igreja?</a><br>--%>
<%--<a href="./Consultas/Cidade.jsp"><b>7.</b> Que cidades possuem população menor que X habitantes?</a><br>--%>
<%--<a href="./Consultas/Cidade.jsp"><b>8.</b> Que cidade oferece o maior número de pontos turisticos?</a><br>--%>


<form method="GET" action="./Consultas/Cidade.jsp">

    <select name="perguntas">
        <option value="0">-------------- Selecione a pergunta desejada para consulta --------------</option>
        <option value="1">1. Quais hotéis de uma cidade possuem restaurante?</option>
        <option value="2">2. Qual a especialidade de um restaurante?</option>
        <option value="3">3. Qual o valor médio de quartos de luxo em todos os hotéis de uma cidade?</option>
        <option value="3">4. Quais casas de show possuem restaurante?</option>
        <option value="3">5. Qual o fundador de um museu?</option>
        <option value="3">6. Qual a data de construção de uma igreja?</option>
        <option value="3">7. Que cidades possuem população menor que X habitantes?</option>
        <option value="3">8. Que cidade oferece o maior número de pontos turisticos?</option>
    </select>

    <p><INPUT TYPE="SUBMIT" NAME="Botão" VALUE="Confirmar"></p>
</form>

</body>
</html>
