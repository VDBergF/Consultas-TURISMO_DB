<%@ page import="DataBase.DBConnect" %>
<%@ page import="Consultas.Consulta" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: berg
  Date: 03/02/17
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consulta fundador</title>

    <%
        String dbOpcao = request.getCookies()[1].getValue();
        DBConnect dbConnect = new DBConnect(dbOpcao);
        Consulta consulta = new Consulta(dbConnect.getConnection(dbOpcao));
    %>

</head>
<body>

<p>Entre com o nome do museu desejado</p>
<form>
    <input type="text" name="museu">
    <input type="submit" name="museu" value="Confirmar"><br>
</form>
<hr />
<h1>Resultados:</h1>

<p>Qual o fundador de um Museu?</p>

<table border="2" CELLSPACING=2 CELLPADDING=6>
    <tr>
        <TH>Nome</TH>
    </tr>

    <%
        String busca = request.getParameter("museu");
        if (busca != null && !busca.isEmpty()) {
            ArrayList<String> nomes = consulta.buscaFundadorMuseu(busca);
             if (nomes != null && !nomes.isEmpty()) {
                for (String museu: nomes) {%>
                    <TR>
                        <TD> <%= museu %></td>
                    </TR>
                <%}
            } else {
                out.println("Não foram encontrado resultados para a pesquisa...");
             }
         }%>

</table>

</body>
</html>
