<%@ page import = "control.Controle" %>
<%
String acao = "";

acao = request.getParameter("acao");

String texto = "";
Controle controle = new Controle();

switch (acao){
case "reiniciar":
	controle.reiniciarContagem();
	break;
case "senhaNormal":
	texto = controle.gerarSenha("N");
	break;
case "senhaPreferencial":
	texto = controle.gerarSenha("P");
	break;
case "chamar":
	controle.chamarSenha();
	break;
case "atualizar" :
	System.out.println(texto);
	texto = controle.ultimaChamada();
	break;
}

out.println(texto);

%>