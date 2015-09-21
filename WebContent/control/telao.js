$(document).ready(function(){
	setInterval(atualizaSenha,0300);
});

function atualizaSenha(){
	$.getJSON("../model/ultimaSenha.json", function(data) {

		if ($("#senha1").html() != data[0].senha){
			$('#senha4').html($('#senha3').html());
			$('#senha3').html($('#senha2').html());
			$('#senha2').html($('#senha1').html());
			$('#senha1').html(data[0].senha);
			$('#senha1').fadeOut(300,function(){
				$("#senha1").fadeIn(300);
			});	}
		
	});
}