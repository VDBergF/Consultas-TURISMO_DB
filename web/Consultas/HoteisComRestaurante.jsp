<%@ page import="DataBase.DBConnect" %>
<%@ page import="Consultas.Consulta" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: berg
  Date: 03/02/17
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consulta cidade</title>

    <%
        String dbOpcao = request.getCookies()[1].getValue();
        DBConnect dbConnect = new DBConnect(dbOpcao);
        Consulta consulta = new Consulta(dbConnect.getConnection(dbOpcao));
    %>

</head>
<body>

<p>Entre com o nome da cidade desejada</p>

<form method="get" action="HoteisComRestaurante.jsp">
    <input type="text" name="cidade">
    <input type="submit" name="cidade" value="Confirmar"><br>
</form>
<hr />
<h1>Resultados:</h1>

<p>Quais hotéis de uma cidade possuem restaurante?</p>

<table border="2" CELLSPACING=2 CELLPADDING=6>
    <tr>
        <TH>Hoteis</TH>
    </tr>

    <%
        String busca = request.getParameter("cidade");
        if (busca != null && !busca.isEmpty()) {
            ArrayList<String> hoteis = consulta.hoteisComRestaurante(busca);
            for (int i = 0; i < hoteis.size(); i++) { %>

                <TR>
                    <TD> <%= hoteis.get(i) %></td>
                </TR>
        <% }
        }%>

</table>

</body>
</html>
