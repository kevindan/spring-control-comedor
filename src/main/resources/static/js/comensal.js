//Inicializa los elementos de la vista Comensales
$(document).ready(function(){
	
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