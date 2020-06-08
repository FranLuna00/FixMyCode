$(function () {
	$("body").on("click", ".self-destroy", function (e) {
		$(this).parents(".a-borrar").remove();
		let id = $(this).parents(".a-borrar").find("input").val();
		let nomrbe = $(this).parents(".a-borrar").find("span").text();
		$("#selectorEtiqueta").append("<option value='"+id+"'>"+nomrbe+"</option>");
    });
	$("#agregarEtiqueta").click(
		function (e) {
			e.preventDefault();
			if ($("#etiquetasvacias")) {
				$("#etiquetasvacias").remove();
			}
			if ($('#selectorEtiqueta').has('option').length > 0) {
				let opcion = $("#selectorEtiqueta").children(
					"option:selected");
				var input = "<input type='hidden' name='etiquetas' value='"
					+ opcion.val() + "'>";
				var texto = "<span class=''>"
					+ opcion.text() + "</span>";
				$("#etiquetas").append("<div class='a-borrar list-group-item'>" + input + texto +"<button type='button' class='btn btn-danger self-destroy float-right'> <i\
				class='fas fa-minus-circle'></i> </button></div>" );
				$("#selectorEtiqueta").find('option:selected').remove();
			}
		});
});