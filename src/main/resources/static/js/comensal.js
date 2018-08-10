//Inicializa los elementos de la vista Comensales
$(document).ready(function(){
	
	valida_formulario();
	
	$('li').removeClass("active");
	$('#menu_registros').addClass("active");
	$('#menu_comensales').addClass("active");

	$("#boton_nuevo_comensal").click(function(){
		
		mostrar_modal_comensal();	
	});
	
	$('#btn_cancelar_comensal').click(function(){
		
		limpiar_form_comensal();
		ocultar_modal_comensal();
				
	});	
	    
});

//Funcion que inicializa la validacion de los campos del formulario

function valida_formulario(){
	
	$('#form_comensal').bootstrapValidator({
		feedbackIcons:{
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating:'glyphicon glyphicon-refresh'
		},		
		fields:{
			dni:{
				validators:{
					stringLength:{
						min: 8,
						max: 8,
						message: 'Debe ingresar los 8 dígitos'
					},
					notEmpty:{
						message: 'El DNI es requerido'
					}
				}
			},
			nombres:{
				validators:{
					notEmpty:{
						message: 'El nombre es requerido'
					}
				}
			},
			apellidoPaterno:{
				validators:{
					notEmpty:{
						message: 'El apellido paterno es requerido'						
					}
				}
			},
			email:{
				validators:{
					emailAddress:{
						message: 'Escriba un email válido'
					},
					notEmpty:{
						message: 'El email es requerido'
					}
				}
			}
		}	
	});
	
}
//Función que permite ingresar solo números en un input
function valida_numeros(e){
		
	key = window.Event ? e.keyCode : e.which;
				
	return (key >= 48 && key <= 57);
}

function limpiar_form_comensal(){
	
	$('#form_comensal').data('bootstrapValidator').resetForm();
	$('#form_comensal').trigger('reset');
		
}

function ocultar_modal_comensal(){

	$('#modal_registro_comensal').modal('hide');	
}

function mostrar_modal_comensal(){

	$('#modal_registro_comensal').modal({keyboard:false});	
}