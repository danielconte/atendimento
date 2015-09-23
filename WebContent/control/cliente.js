$(document).ready(function() {
	setInterval(atualizaSenha, 1300);
	$("#atendNormal").click(function() {
		gerarSenha("N");
	});
	$("#atendPreferencial").click(function() {
		gerarSenha("P");
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

function gerarSenha(tipo) {
	switch (tipo) {
	case "N":
		$.ajax({
			url : "../control/senha.jsp",
			data:{
				"acao" : "senhaNormal",
			},
			type : "POST"
		}).done(function(data) {
			alert('Sua senha é a ' + data);
		});
	case "P":
		$.ajax({
			url : "../control/senha.jsp",
			data:{
				"acao" : "senhaPreferencial",
			},
			type : "POST"
		}).done(function(data) {
			alert('Sua senha é a ' + data);
		});
		break;
	}
}
