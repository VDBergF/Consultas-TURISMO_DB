<%@ page import="DataBase.DBConnect" %>
<%@ page import="Consultas.Consulta" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: berg
  Date: 03/02/17
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consulta pontos turísticos</title>

    <%
        String dbOpcao = request.getCookies()[1].getValue();
        DBConnect dbConnect = new DBConnect(dbOpcao);
        Consulta consulta = new Consulta(dbConnect.getConnection(dbOpcao));
    %>

</head>
<body>

<h1>Resultados:</h1>

<p>Que cidade oferece o maior número de pontos turisticos?</p>

<table border="2" CELLSPACING=2 CELLPADDING=6>
    <tr>
        <TH>Cidades</TH>
    </tr>

    <%
    ArrayList<String> cidades = consulta.cidadeComMaisRecursos();
    for (int i = 0; i < cidades.size(); i++) { %>

        <TR>
            <TD> <%= cidades.get(i) %></td>
        </TR>
     <%}%>

</table>

</body>
</html>