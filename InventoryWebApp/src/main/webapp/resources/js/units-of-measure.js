$(function() {
	$('#btnSaveUnit').on('click', saveUnit);
	$('#btnDeleteUnit').on('click', deleteUnit);
	$('#modal_nueva_unidadMedida').on('shown.bs.modal', function() {
		$('#txt_unidadMedida_nombre').focus();
	});
});

var saveUnit = function() {
	var name = $('#txt_unidadMedida_nombre').val();
	
	if(name.trim() == '') {
		$('#txt_unidadMedida_nombre').tooltip('show');
		$('#txt_unidadMedida_nombre').focus();
		return false;
	}else {
		var actionPath;
		
		if($('#unitAction').val() == 'new') {
			actionPath = '/insertUnitOfMeasure';
		}else {
			actionPath = '/updateUnitOfMeasure';
		}
		
		$('#unitOfMeasure').submit(function() {
			$.ajax({
				type : 'POST',
				url : $('#appContextPath').val() + actionPath,
				data : $('#unitOfMeasure').serialize(),
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
					
					window.location = $('#appContextPath').val() + '/adminUnitsOfMeasure?' + status + '=1&msg=' + msg;
					$('#modal_nueva_unidadMedida').modal('hide');
				},
				error: function(e) {
					window.location = $('#appContextPath').val() + '/adminUnitsOfMeasure?error=1';
					$('#modal_nueva_unidadMedida').modal('hide');
				}
			});
			return false;
		});
	}
}

var deleteUnit = function() {
	$.ajax({
		type : 'POST',
		url : $('#appContextPath').val() + '/deleteUnitOfMeasure',
		data : 'unitId=' + $('#unitToDelete').val(),
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
			
			window.location = $('#appContextPath').val() + '/adminUnitsOfMeasure?' + status + '=1&msg=' + msg;
			$('#modal_confirm_delete').modal('hide');
		},
		error: function(e) {
			window.location = $('#appContextPath').val() + '/adminUnitsOfMeasure?error=1';
			$('#modal_confirm_delete').modal('hide');
		}
	});
	
	return false;
}

function showUnitModal(id, name, description) {
	if(id != null && id > 0) {
		$('#unitId').val(id);
		$('#unitAction').val('update');
		$('#catModalTitle').text('Actualizar unidad de medida');
		$('#txt_unidadMedida_nombre').val(name);
		$('#txt_descripcion').val(description);
		$('#btnSaveUnit').text('Actualizar');
	} else {
		$('#unitAction').val('new');
		$('#catModalTitle').text('Nueva unidad de medida');
		$('#txt_unidadMedida_nombre').val('');
		$('#txt_descripcion').val('');
		$('#btnSaveUnit').text('Guardar');
	}
	$('#modal_nueva_unidadMedida').modal({backdrop: 'static'/*, keyboard: false*/})
}

function confirmDelete(id, name) {
	$('#unitToDelete').val(id);
	$('#unitNameToDelete').text(name);
	$('#modal_confirm_delete').modal({backdrop: 'static'/*, keyboard: false*/})
}
