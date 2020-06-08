$(function() {
	$("#respuesta")
			.keypress(
					function(e) {
						var tval = $("#respuesta").val(), tlength = tval.length, set = 400, remain = parseInt(set
								- tlength);
						$('#charNum').text(remain);
						if (remain <= 0 && e.which !== 0 && e.charCode !== 0) {
							$("#respuesta").val(
									(tval).substring(0, tlength - 1));
							return false;
						}
					});

	$("#valorarPositivo").click(function(e) {
		e.preventDefault();
		valorar("positiva");
	});
	$("#valorarNegativo").click(function(e) {
		e.preventDefault();
		valorar("negativa")
	});
	var valorado = false;
	function valorar(valoracion) {
		let idPublicacion = $("#idPublicacion").val();
		var datos = {
			"idPublicacion" : idPublicacion,
			"valoracion" : valoracion
		};
		$.ajax({
			type : "post",
			url : "/publicaciones/valorar",
			dataType : "json",
			contentType : "application/json",
			data : JSON.stringify(datos),
			success : function(response) {
			}
		});
		if (!valorado) {
			if (valoracion == "positiva") {
				let numValoraciones = parseInt($("#valoracionPositiva").text());
				numValoraciones++;
				$("#valoracionPositiva").text(numValoraciones);
			} else {
				let numValoraciones = parseInt($("#valoracionNegativa").text());
				numValoraciones++;
				$("#valoracionNegativa").text(numValoraciones);
			}
			valorado = true;
		}

	}
});