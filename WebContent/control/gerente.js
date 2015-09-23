$(document).ready(function(){
	setInterval(atualizaSenha,1300);
	$("#chamarProxima").click(function(){
		chamarProxima();							
	});
	$("#reiniciarContagem").click(function(){
		reiniciarContagem();												
	});
});

function atualizaSenha() {
	$.ajax({
		url : "../control/senha.jsp",
		data:{
			"acao" : "atualizar",
		},
		type : "POST"
	}).done(function(data) {
		if ($("#senha1").html() != data) {
			$('#senha4').html($('#senha3').html());
			$('#senha3').html($('#senha2').html());
			$('#senha2').html($('#senha1').html());
			$('#senha1').html(data);
			$('#senha1').fadeOut(300, function() {
				$("#senha1").fadeIn(300);
			});
		}

	});

}

function chamarProxima(){
	$.ajax({
		url : "../control/senha.jsp",
		data:{
			"acao" : "chamar",
		},
		type : "POST"
	}).done(function(data) {
		alert('Senha chamada');
	});
}
function reiniciarContagem(){
	$.ajax({
		url : "../control/senha.jsp",
		data:{
			"acao" : "reiniciar",
		},
		type : "POST"
	}).done(function(data) {
		alert('Contagem reiniciada');
	});
}