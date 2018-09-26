$(document).ready(function() {

	$('li').removeClass("active");
	$('#menu_registros').addClass("active");
	$('#menu_productos').addClass("active");

	$('#precioCompra').numeric('.');
	$('#precioVenta').numeric('.');

	$('#checkAlertaStock').bootstrapToggle({
		on : 'Si',
		off : 'No',
		onstyle : 'success',
		offstyle : 'default'
	});

	initializerToggleAll(1);

	valida_formulario();

	$("#boton_nuevo_producto").click(function() {
		limpiar_form_producto()
		ocultar_mensajes();
		mostrar_modal_producto();

	});

	$("#btn_cancelar_producto").click(function() {
		limpiar_form_producto();
		ocultar_modal_producto();

	});

	$('#checkAlertaStock').change(function() {
		if ($(this).prop('checked')) {

			$('#alertaStock').val(1);
		} else {

			$('#alertaStock').val(0);
		}
		console.log('Valor a enviar : ' + $('#alertaStock').val());
	});

	//
	$("#btn_cancelar_vista_producto").click(function() {

		$('#modal_vista_producto').modal('hide');
	});
	//
	// // Ejecuta el filtrado al pulsar la tecla ENTER
	$('#filtro_producto_descripcion').keypress(function(e) {

		if (e.which == 13) {

			filtrar_productos();
		}
	});

});

function initializerToggleAll(opcion) {

	$.ajax({

		url : "/productos/listar/" + opcion,
		type : 'GET',
		dataType : "json",
		data : {
			opcion : opcion,
		},
		success : function(data) {

			for ( var i in data) {
				console.log(data[i].descripcion);

				$('#checkAlertaStock_ext_' + data[i].idProducto)
						.bootstrapToggle({
							on : 'Si',
							off : 'No',
							onstyle : 'success',
							offstyle : 'default'
						});

			}

		}
	});

}

// Funcion que inicializa la validacion de los campos del formulario
function valida_formulario() {

	$('#form_producto').bootstrapValidator({
		feedbackIcons : {

			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			descripcion : {
				validators : {
					notEmpty : {
						message : 'La descripción es requerida'
					}
				}
			},
			presentacion : {
				validators : {
					notEmpty : {
						message : 'La presentación es requerida'
					}
				}
			},
			precioVenta : {
				validators : {
					notEmpty : {
						message : 'Campo Obligatorio'
					}
				}
			},
			stockMinimo : {
				validators : {
					notEmpty : {
						message : 'Campo Obligatorio'
					},
					greaterThan : {
						value : 0,
						message : 'Debe ser mayor a 0'
					}
				}
			},
			stockActual : {
				validators : {
					notEmpty : {
						message : 'Campo Obligatorio'
					}
				}
			}
		}
	});

}

function buscar_producto(productoId, opcion) {

	$.ajax({

		url : "/productos/buscar/" + productoId,
		type : 'GET',
		dataType : "json",
		data : {
			productoId : productoId,
		},
		success : function(data) {

			console.log(data);

			if (opcion === 1) {

				$('#idProducto').val(data.idProducto);
				$('#eliminado').val(data.eliminado);

				$('#descripcion').val(data.descripcion);
				$('#tipoProducto').val(data.tipoProducto.idTipoProducto);
				$('#presentacion').val(data.presentacion);
				$('#precioCompra').val(data.precioCompra);
				$('#precioVenta').val(data.precioVenta);
				$('#stockMinimo').val(data.stockMinimo);
				$('#stockActual').val(data.stockActual);

				if (data.alerta === 1) {

					$('#checkAlertaStock').bootstrapToggle('on');

				} else if (data.alerta === 0) {

					$('#checkAlertaStock').bootstrapToggle('off');
				}

				$('#fechaRegistro').val(data.fechaRegistro);

			} else if (opcion === 2) {

				$('#descripcion_ver').text(data.descripcion);
				$('#tipoProducto_ver').text(data.tipoProducto.idTipoProducto);
				$('#presentacion_ver').text(data.presentacion);
				$('#precioCompra_ver').text('S/. ' + data.precioCompra);
				$('#precioVenta_ver').text('S/. ' + data.precioVenta);
				$('#stockMinimo_ver').text(data.stockMinimo);
				$('#stockActual_ver').text(data.stockActual);

				if (data.alerta === 1) {

					$('#alerta_ver').text('Si');

				} else if (data.alerta === 0) {

					$('#alerta_ver').text('No');
				}

				$('#fechaRegistro_ver').text(data.fechaRegistro);
			}
		}
	});
}

function filtrar_productos() {

	var descripcion = $('#filtro_producto_descripcion').val();

	if (descripcion != null || descripcion != '') {

		location.href = "/productos/filtrar/" + descripcion;
	}

}

function editar_producto(productoId) {
	console.log(productoId);
	ocultar_mensajes();
	buscar_producto(productoId, 1);
	mostrar_modal_producto();

}

function ver_producto(productoId) {
	ocultar_mensajes();
	buscar_producto(productoId, 2);
	$('#modal_vista_producto').modal();
}

function eliminar_producto(idProducto) {
	ocultar_mensajes();
	$('#texto_eliminar_producto').html('¿Seguro de eliminar el producto?');
	$('#modal_confirma_eliminar_producto').modal({
		keyboard : false
	});

	var modalConfirm = function(callback) {

		$('#btn_confirma_eliminar_producto').on("click", function() {
			callback(true);
			$('#modal_confirma_eliminar_producto').modal('hide');
		});

		$('#btn_cancela_eliminar_producto').on("click", function() {
			callback(false);
			$('#modal_confirma_eliminar_producto').modal('hide');
		});
	}

	modalConfirm(function(confirm) {

		if (confirm) {

			location.href = '/productos/eliminar/' + idProducto;
		}
	});
}

function limpiar_form_producto() {

	$('#form_producto').data('bootstrapValidator').resetForm();
	$('#idProducto').val('');
	$('#fechaRegistro').val('');
	$('#eliminado').val('');
	$('#form_producto').trigger('reset');
	$('#checkAlertaStock').bootstrapToggle('off');

	console.log($('#idProducto').val() + '');

}

function ocultar_modal_producto() {

	$('#modal_registro_producto').modal('hide');
}

function mostrar_modal_producto() {

	$('#modal_registro_producto').modal({
		keyboard : false
	});
}

function ocultar_mensajes() {

	$('#panel_mensaje_success_producto').hide();
	$('#panel_mensaje_error_producto').hide();
	$('#panel_mensaje_warning_producto').hide();
	$('#panel_mensaje_info_producto').hide();
}
