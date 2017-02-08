<%@ page import="DataBase.DBConnect" %>
<%@ page import="Consultas.Consulta" %><%--
  Created by IntelliJ IDEA.
  User: berg
  Date: 08/02/17
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Média de preço Quartos</title>

    <%
        String dbOpcao = request.getCookies()[1].getValue();
        DBConnect dbConnect = new DBConnect(dbOpcao);
        Consulta consulta = new Consulta(dbConnect.getConnection(dbOpcao));
    %>

</head>
<body>

<p>Entre com o nome da cidade desejada</p>
<form>
    <input type="text" name="quarto_cidade">
    <input type="submit" name="quarto_cidade" value="Confirmar"><br>
</form>
<hr />
<h1>Resultados:</h1>

<p>Qual o valor médio de quartos de luxo em todos os hotéis de uma cidade?</p>

<table border="2" CELLSPACING=2 CELLPADDING=6>
    <tr>
        <TH>Média preço</TH>
    </tr>

    <%
        String busca = request.getParameter("quarto_cidade");
        if (busca != null && !busca.isEmpty()) {
            String mediaPreco = consulta.precoMedioQuartoHoteis(busca);
            if (mediaPreco != null && !mediaPreco.isEmpty()) {%>
                <TR>
                    <TD> <%= mediaPreco %></td>
                </TR>
             <%} else {
                out.println("Não foram encontrados resultados para a busca ou não existem quartos de luxo nesta cidade");
             }
        }%>

</table>

</body>
</html>
