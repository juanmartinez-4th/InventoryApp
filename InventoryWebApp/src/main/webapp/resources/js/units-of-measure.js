$(function() {
	$('#btnSaveUnit').on('click', saveUnit);
	$('#btnDeleteUnit').on('click', deleteUnit);
	$('#modal_nueva_unidadMedida').on('shown.bs.modal', function() {
		$('#txt_unidadMedida_nombre').focus();
	});
	$("[data-toggle=tooltip").tooltip();
	$('#unitsTable').dataTable({
		"columnDefs": [{ "targets": [2, 3], "orderable": false , "searchable": false}],
		"language": {
            "url": ctx + "/resources/localisation/dataTables.spanish.lang"
        }
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
			actionPath = ctx + '/insertUnitOfMeasure';
		}else {
			actionPath = ctx + '/updateUnitOfMeasure';
		}
		
		$('#unitOfMeasure').submit(function() {
			$.ajax({
				type : 'POST',
				url : actionPath,
				data : $('#unitOfMeasure').serialize(),
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
					
					window.location = ctx + '/adminUnitsOfMeasure?' + status + '=1&msg=' + msg;
					$('#modal_nueva_unidadMedida').modal('hide');
				},
				error: function(e) {
					window.location = ctx + '/adminUnitsOfMeasure?error=1';
					$('#modal_nueva_unidadMedida').modal('hide');
				},
				complete: maskPage()
			});
			return false;
		});
	}
}

var deleteUnit = function() {
	$.ajax({
		type : 'POST',
		url : ctx + '/deleteUnitOfMeasure',
		data : 'unitId=' + $('#unitToDelete').val(),
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
			
			window.location = ctx + '/adminUnitsOfMeasure?' + status + '=1&msg=' + msg;
			$('#modal_confirm_delete').modal('hide');
		},
		error: function(e) {
			window.location = ctx + '/adminUnitsOfMeasure?error=1';
			$('#modal_confirm_delete').modal('hide');
		},
		complete: maskPage()
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
