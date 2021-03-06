<%@ page import="DataBase.DBConnect" %>
<%@ page import="Consultas.Consulta" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: berg
  Date: 04/02/17
  Time: 20:40
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

<p>Entre com o número da população desejada</p>

<form method="get" action="Cidade.jsp">
    <input type="text" name="cidade2">
    <input type="submit" name="cidade2" value="Confirmar"><br>
</form>
<hr />
<h1>Resultados:</h1>

<p>Que cidades possuem população menor que X habitantes?</p>

<table border="2" CELLSPACING=2 CELLPADDING=6>
    <tr>
        <TH>Cidades</TH>
    </tr>

    <%
        String busca = request.getParameter("cidade2");
        if (busca != null && !busca.isEmpty()) {
            ArrayList<String> cidades = consulta.cidadePopu(busca);
            for (int i = 0; i < cidades.size(); i++) { %>

    <TR>
        <TD> <%= cidades.get(i) %></td>
    </TR>
    <% }
    }%>

</table>

</body>
</html>
