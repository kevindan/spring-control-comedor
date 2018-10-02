function mostrar_modal_actualizar_stock_producto(idProducto) {
	ocultar_mensajes();
	cargar_stock_producto(idProducto);
	$('#modal_actualizar_stock_producto').modal({
		keyboard : false
	});
}

function actualizar_alerta(idProducto) {

	if ($('#checkAlertaStock_ext_' + idProducto).prop('checked')) {

		$('#boton_stock_' + idProducto).show();
		grabar_estado_alerta(idProducto, 1);

	} else {

		$('#boton_stock_' + idProducto).hide();
		grabar_estado_alerta(idProducto, 0);
	}

}

function grabar_estado_alerta(productoId, alerta) {

	$.ajax({

		url : "/productos/actualizaalerta/" + productoId + "/" + alerta + "",
		type : 'GET',
		dataType : "json",
		data : {
			idProducto : productoId,
			alerta : alerta
		},
		success : function(result) {

			if (result === 1) {

				console.log('Ok');

			} else if (result === 0) {

				console.log('Error');
			}
		}
	});
}

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

function cargar_stock_producto(idProducto) {

	$.ajax({

		url : "/productos/buscar/" + idProducto,
		type : 'GET',
		dataType : "json",
		data : {
			idProducto : idProducto,
		},
		success : function(data) {

			$('#stockActual_actualizar').val(data.stockActual);
			$('#idProducto').val(data.idProducto);

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

function actualizar_stock_producto() {

	var idProducto = $('#idProducto').val();
	var stock_actual_actualizar = $('#stockActual_actualizar').val();

	location.href = '/productos/actualizarstock/' + idProducto + '/'
			+ stock_actual_actualizar;
}

function limpiar_form_producto() {

	$('#form_producto').data('bootstrapValidator').resetForm();
	$('#form_producto').trigger('reset');
	$('#checkAlertaStock').bootstrapToggle('off');

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

function inicializa_elementos_formulario(condicion) {

	$('#precioCompra').val(0.00);
	$('#stockMinimo').val(1);

	if (condicion == 'mostrar') {

		$('#col_precioCompra').show();
		$('#col_stockMinimo').show();

	} else if (condicion == 'ocultar') {

		$('#col_precioCompra').hide();
		$('#col_stockMinimo').hide();
	}
}

$("#boton_nuevo_producto").click(function() {
	limpiar_form_producto()
	ocultar_mensajes();
	mostrar_modal_producto();

});

$("#btn_cancelar_producto").click(function() {
	limpiar_form_producto();
	ocultar_modal_producto();

});

$("#btn_cancelar_stock_producto").click(function() {
	$('#idProducto').val('');
	$('#stockActual_actualizar').val('');
	$('#modal_actualizar_stock_producto').modal('hide');

});

$("#btn_cancelar_vista_producto").click(function() {

	$('#modal_vista_producto').modal('hide');
});

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

	$('#imagen').fileinput({
		language : 'es',
		showUpload : false,
		showCancel : false,
		//dropZoneEnabled: false,
		allowedFileExtensions : [ 'jpg', 'png' ]
	});

	inicializa_elementos_formulario('ocultar')

	valida_formulario();

	$('#checkAlertaStock').change(function() {
		if ($(this).prop('checked')) {

			inicializa_elementos_formulario('mostrar');
			$('#alertaStock').val(1);

		} else {

			inicializa_elementos_formulario('ocultar');
			$('#alertaStock').val(0);
		}
	});

	// // Ejecuta el filtrado al pulsar la tecla ENTER
	$('#filtro_producto_descripcion').keypress(function(e) {

		if (e.which == 13) {

			filtrar_productos();
		}
	});
});