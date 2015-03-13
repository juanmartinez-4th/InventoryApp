$(function() {
	$('#btnSaveProduction').on('click', saveProduction);
	$('#btnDeleteProduction').on('click', deleteProduction);
	$('#modal_nueva_produccion').on('shown.bs.modal', function() {
		$('#txt_produccion_codigo').focus();
	});
});

var saveProduction = function() {	
	if($('#txt_produccion_codigo').val().trim() == '') {
		$('#txt_produccion_codigo').tooltip('show');
		$('#txt_produccion_codigo').focus();
		return false;
	}else if($('#txt_produccion_nombre').val().trim() == '') {
		$('#txt_produccion_nombre').tooltip('show');
		$('#txt_produccion_nombre').focus();
		return false;
	}else {
		var actionPath;
		
		if($('#productionAction').val() == 'new') {
			actionPath = ctx + '/insertProduction';
		}else {
			actionPath = ctx + '/updateProduction';
		}
		
		$('#production').submit(function() {
			$.ajax({
				type : 'POST',
				url : actionPath,
				data : $('#production').serialize(),
				beforeSend: maskPage(),
				success : function(response) {
					var status = 'success';
					var msg = '';
					
					if(response == null) {
						status = 'error';
					}else if (response.status == 'SUCCESS') {
						msg = escape(response.message);
					}else if (response.status == 'FAIL') {
						msg = escape(response.message);
						status = 'error';
					}else {
						status = 'error';
					}
					
					window.location = ctx + '/adminProductions?' + status + '=1&msg=' + msg;
					$('#modal_nueva_produccion').modal('hide');
				},
				error: function(e) {
					window.location = ctx + '/adminProductions?error=1';
					$('#modal_nueva_produccion').modal('hide');
				},
				complete: maskPage()
			});
			return false;
		});
	}
}

var deleteProduction = function() {
	$.ajax({
		type : 'POST',
		url : ctx + '/deleteProduction',
		data : 'productionId=' + $('#productionToDelete').val(),
		beforeSend: maskPage(),
		success : function(response) {
			var status = 'success';
			var msg = '';
			
			if(response == null) {
				status = 'error';
			}else if (response.status == 'SUCCESS') {
				msg = escape(response.message);
			}else if (response.status == 'FAIL') {
				msg = escape(response.message);
				status = 'error';
			}else {
				status = 'error';
			}
			
			window.location = ctx + '/adminProductions?' + status + '=1&msg=' + msg;
			$('#modal_confirm_delete').modal('hide');
		},
		error: function(e) {
			window.location = ctx + '/adminProductions?error=1';
			$('#modal_confirm_delete').modal('hide');
		},
		complete: maskPage()
	});
	
	return false;
}

function showProductionModal(id, code, name, description) {
	if(id != null && id > 0) {
		$('#productionId').val(id);
		$('#productionAction').val('update');
		$('#catModalTitle').text('Actualizar producción');
		$('#txt_produccion_codigo').val(code);
		$('#txt_produccion_nombre').val(name);
		$('#txt_descripcion').val(description);
		$('#btnSaveProduction').text('Actualizar');
	} else {
		$('#productionAction').val('new');
		$('#catModalTitle').text('Nueva producción');
		$('#txt_produccion_codigo').val('');
		$('#txt_produccion_nombre').val('');
		$('#txt_descripcion').val('');
		$('#btnSaveProduction').text('Guardar');
	}
	$('#modal_nueva_produccion').modal({backdrop: 'static'/*, keyboard: false*/})
}

function confirmDelete(id, name) {
	$('#productionToDelete').val(id);
	$('#productionNameToDelete').text(name);
	$('#modal_confirm_delete').modal({backdrop: 'static'/*, keyboard: false*/})
}
