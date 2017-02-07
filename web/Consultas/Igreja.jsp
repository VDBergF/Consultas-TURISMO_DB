<%@ page import="java.util.ArrayList" %>
<%@ page import="DataBase.DBConnect" %>
<%@ page import="Consultas.Consulta" %>
<%@ page import="Modelos.Igreja" %><%--
  Created by IntelliJ IDEA.
  User: berg
  Date: 03/02/17
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consulta igreja</title>

    <%
        String dbOpcao = request.getCookies()[1].getValue();
        DBConnect dbConnect = new DBConnect(dbOpcao);
        Consulta consulta = new Consulta(dbConnect.getConnection(dbOpcao));
    %>

</head>
<body>

<p>Entre com o nome da igreja desejada</p>
<form>
    <input type="text" name="igreja">
    <input type="submit" name="igreja" value="Confirmar"><br>
</form>
<hr />
<h1>Resultados:</h1>

<p>Qual a data de construção de uma igreja?</p>

<table border="2" CELLSPACING=2 CELLPADDING=6>
    <tr>
        <TH>Código</TH>
        <TH>Nome</TH>
    </tr>

        <%
        String busca = request.getParameter("igreja");
        if (busca != null && !busca.isEmpty()) {
            ArrayList<Igreja> igrejas = consulta.dataConstIgreja(busca, true);
            for(Igreja igreja: igrejas){
                String link = "./infoigreja.jsp?id_igreja=" + igreja.getCodigo();
                %>
                <TR>
                    <TD><a href="<%=link%>"><%= igreja.getCodigo() %></a></td>
                    <TD><%= igreja.getNome() %>
                    </td>
                </TR>
        <% }
    }%>

</body>
</html>
