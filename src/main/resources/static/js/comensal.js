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
					notEmpty:{
						message: 'DNI requerido'
					},
					stringLength:{
						min: 8,
						max: 8,
						message: 'Ingrese los 8 dígitos '
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
			apellido_paterno:{
				validators:{
					notEmpty:{
						message: 'El apellido paterno es requerido'
					}					
				}
			},
			email:{
				validators:{
					emailAddress:{
						message: 'formato no válido'
					},
					notEmpty:{						
						message: 'El email es requerido'
					}
				}
			},
			apellido_materno:{
				validators:{
					notEmpty:{
						message: 'El apellido materno es requerido'
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
	
	$('#form_comensal').trigger('reset');
		
}

function ocultar_modal_comensal(){

	$('#modal_registro_comensal').modal('hide');	
}

function mostrar_modal_comensal(){

	$('#modal_registro_comensal').modal({keyboard:false});	
}