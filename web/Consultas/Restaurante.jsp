<%@ page import="DataBase.DBConnect" %>
<%@ page import="Consultas.Consulta" %><%--
  Created by IntelliJ IDEA.
  User: berg
  Date: 03/02/17
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consulta Restaurante</title>

    <%
        String dbOpcao = request.getCookies()[1].getValue();
        DBConnect dbConnect = new DBConnect(dbOpcao);
        Consulta consulta = new Consulta(dbConnect.getConnection(dbOpcao));
    %>

</head>
<body>

<p>Entre com o nome do restaurante desejado</p>

<form method="get" action="Restaurante.jsp">
    <input type="text" name="restaurante">
    <input type="submit" name="restaurante" value="Confirmar"><br>
</form>
<hr />

<h1>Resultados:</h1>

<p>Qual a especialidade de um restaurante?</p>

<table border="2" CELLSPACING=2 CELLPADDING=6>
    <tr>
        <TH>Especialidade</TH>
    </tr>

    <%
        String busca = request.getParameter("restaurante");
        if (busca != null && !busca.isEmpty()) {
            String especialidade = consulta.espRestaurante(busca);%>
            <%if (especialidade != null && !especialidade.isEmpty()) {%>
                <TR>
                    <TD> <%= especialidade %></td>
                </TR>
            <%} else {
                out.println("Não foram encontrado resultados para a pesquisa...");
            }
        }%>

</table>

</body>
</html>
