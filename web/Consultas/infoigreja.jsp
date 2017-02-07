<%@ page import="DataBase.DBConnect" %>
<%@ page import="Consultas.Consulta" %>
<%@ page import="Modelos.Igreja" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: berg
  Date: 06/02/17
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Data construcao igreja</title>
    <%
        String dbOpcao = request.getCookies()[1].getValue();
        DBConnect dbConnect = new DBConnect(dbOpcao);
        Consulta consulta = new Consulta(dbConnect.getConnection(dbOpcao));
    %>

</head>
<body>

<h1>Resultados:</h1>

<p>Qual a data de construção de uma igreja?</p>

<table border="2" CELLSPACING=2 CELLPADDING=6>
    <tr>
        <TH>Data construção</TH>
    </tr>

        <%
        String busca = request.getParameter("id_igreja");
        if (busca != null && !busca.isEmpty()) {
            ArrayList<Igreja> igrejas = consulta.dataConstIgreja(busca, false);
            for(Igreja igreja: igrejas) {%>
    <TR>
        <TD><%= igreja.getDataConstrucao() %>
        </td>
    </TR>
        <% }

        }%>

</body>
</html>
