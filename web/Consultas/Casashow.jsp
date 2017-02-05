<%@ page import="java.util.ArrayList" %>
<%@ page import="DataBase.DBConnect" %>
<%@ page import="Consultas.Consulta" %><%--
  Created by IntelliJ IDEA.
  User: berg
  Date: 03/02/17
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consulta casa de show</title>

    <%
        String dbOpcao = request.getCookies()[1].getValue();
        DBConnect dbConnect = new DBConnect(dbOpcao);
        Consulta consulta = new Consulta(dbConnect.getConnection(dbOpcao));
    %>

</head>
<body>

p>Entre com o nome da cidade desejada</p>

<form method="get" action="Casashow.jsp">
    <input type="text" name="casashow">
    <input type="submit" name="casashow" value="Confirmar"><br>
</form>
<hr />

<h1>Resultados:</h1>

<p>Quais casas de show de uma cidade possuem restaurante?</p>

<table border="2" CELLSPACING=2 CELLPADDING=6>
    <tr>
        <TH>Casas de Show</TH>
    </tr>

        <%
        String busca = request.getParameter("casashow");
        if (busca != null && !busca.isEmpty()) {
            ArrayList<String> cShow = consulta.csSwComRestaurante(busca);
            for (int i = 0; i < cShow.size(); i++) { %>

                <TR>
                    <TD> <%= cShow.get(i) %></td>
                </TR>
        <% }
        }%>

</body>
</html>
