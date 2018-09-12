//Inicializa los elementos de la vista Comensales
$(document).ready(function() {

	$('li').removeClass("active");
	$('#menu_registros').addClass("active");
	$('#menu_comensales').addClass("active");

	$("#boton_nuevo_comensal").click(function() {
		valida_formulario();
		$('#panel_mensaje_success').hide();
		form_comensal_habilitado(true);
		mostrar_modal_comensal();

	});

	// Ejecuta el filtrado al pulsar la tecla ENTER
	$('#filtro_comensal_apellido').keypress(function(e) {
		if (e.which == 13) {

			filtrar_comensales();
		}
	});
	
});

// Funcion que inicializa la validacion de los campos del formulario
function valida_formulario() {

	$('#form_comensal').bootstrapValidator({
		feedbackIcons : {

			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			dni : {
				validators : {
					stringLength : {
						min : 8,
						max : 8,
						message : 'Debe ingresar los 8 dígitos'
					},
					notEmpty : {
						message : 'El DNI es requerido'
					},
					remote : {

						type : 'POST',
						url : '/comensales/buscarcomensaldni',
						message : 'El dni ya se encuentra registrado'
					}
				}
			},
			nombres : {
				validators : {
					notEmpty : {
						message : 'El nombre es requerido'
					}
				}
			},
			apellidoPaterno : {
				validators : {
					notEmpty : {
						message : 'El apellido paterno es requerido'
					}
				}
			},
			email : {
				validators : {
					emailAddress : {
						message : 'Escriba un email válido'
					},
					notEmpty : {
						message : 'El email es requerido'
					},
					remote : {

						type : 'POST',
						url : '/comensales/buscarcomensalemail',
						message : 'El email ya se encuentra registrado'
					}
				}
			}
		}
	});

}

function valida_formularioActualizar() {

	$('#form_comensal').bootstrapValidator({
		feedbackIcons : {

			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			dni : {
				validators : {
					stringLength : {
						min : 8,
						max : 8,
						message : 'Debe ingresar los 8 dígitos'
					},
					notEmpty : {
						message : 'El DNI es requerido'
					}
				}
			},
			nombres : {
				validators : {
					notEmpty : {
						message : 'El nombre es requerido'
					}
				}
			},
			apellidoPaterno : {
				validators : {
					notEmpty : {
						message : 'El apellido paterno es requerido'
					}
				}
			},
			email : {
				validators : {
					emailAddress : {
						message : 'Escriba un email válido'
					},
					notEmpty : {
						message : 'El email es requerido'
					}
				}
			}
		}
	});

}

// función para buscar Comesal por Id
function buscar_comensal(comensalId) {

	$.ajax({

		url : "/comensales/buscar/" + comensalId,
		type : 'GET',
		dataType : "json",
		data : {
			comensalId : comensalId,
		},
		success : function(data) {

			$('#idComensal').val(data.idComensal);

			$('#dni').val(data.dni);
			$('#nombres').val(data.nombres);
			$('#apellidoPaterno').val(data.apellidoPaterno);
			$('#apellidoMaterno').val(data.apellidoMaterno);
			$('#sexo').val(data.sexo);
			$('#direccion').val(data.direccion);
			$('#email').val(data.email);
			$('#telefono').val(data.telefono);

			$('#fechaRegistro').val(data.fechaRegistro);
			$('#eliminado').val(data.eliminado);

		}
	});
}

function filtrar_comensales() {

	var apellidoPaterno = $('#filtro_comensal_apellido').val();

	if (apellidoPaterno != null || apellidoPaterno != '') {

		location.href = "/comensales/filtrar/" + apellidoPaterno;
	}

}

function editar_comensal(comensalId) {

	buscar_comensal(comensalId);
	valida_formularioActualizar();
	form_comensal_habilitado(true);
	$('#dni').attr('readonly', 'readonly')
	$('#email').attr('readonly', 'readonly')
	mostrar_modal_comensal();

}

function ver_comensal(comensalId) {
	buscar_comensal(comensalId);
	form_comensal_habilitado(false);
	mostrar_modal_comensal();
}

function eliminar_comensal(idComensal) {

	$('#texto_eliminar_comensal').html('¿Seguro de eliminar al comensal?');
	$('#modal_confirma_eliminar_comensal').modal({
		keyboard : false
	});

	var modalConfirm = function(callback) {

		$('#btn_confirma_eliminar_comensal').on("click", function() {
			callback(true);
			$('#modal_confirma_eliminar_comensal').modal('hide');
		});

		$('#btn_cancela_eliminar_comensal').on("click", function() {
			callback(false);
			$('#modal_confirma_eliminar_comensal').modal('hide');
		});
	}

	modalConfirm(function(confirm) {

		if (confirm) {

			location.href = '/comensales/eliminar/' + idComensal;
		}
	});

}

function form_comensal_habilitado(condicion) {

	var condicion_inversa = "";

	if (condicion == true) {

		condicion_inversa = false;

	} else if (condicion == false) {

		condicion_inversa = true;

	}

	$('#dni').attr('disabled', condicion_inversa);
	$('#nombres').attr('disabled', condicion_inversa);
	$('#apellidoPaterno').attr('disabled', condicion_inversa);
	$('#apellidoMaterno').attr('disabled', condicion_inversa);
	$('#sexo').attr('disabled', condicion_inversa);
	$('#direccion').attr('disabled', condicion_inversa);
	$('#email').attr('disabled', condicion_inversa);
	$('#telefono').attr('disabled', condicion_inversa);

	$('#btn_grabar_comensal').attr('disabled', condicion_inversa);

}

// Función que permite ingresar solo números en un input
function valida_numeros(e) {

	key = window.Event ? e.keyCode : e.which;

	return (key >= 48 && key <= 57);
}

function limpiar_form_comensal() {

	$('#form_comensal').data('bootstrapValidator').resetForm();
	$('#idComensal').val('');
	$('#fechaRegistro').val('');
	$('#eliminado').val('');
	$('#form_comensal').trigger('reset');

	form_comensal_habilitado(true);

	console.log($('#idComensal').val() + '');

}

function ocultar_modal_comensal() {

	$('#modal_registro_comensal').modal('hide');
}

function mostrar_modal_comensal() {

	$('#modal_registro_comensal').modal({
		keyboard : false
	});
}