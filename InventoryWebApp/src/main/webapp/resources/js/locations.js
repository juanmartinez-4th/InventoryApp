$(function() {
	$('#btnSaveLocation').on('click', saveLocation);
	$('#btnDeleteLocation').on('click', deleteLocation);
	$('#modal_nueva_ubicacion').on('shown.bs.modal', function() {
		$('#txt_ubicacion_nombre').focus();
	});
	$("#tableLocations").sortable();
	$("#tableLocations").disableSelection();
});

var saveLocation = function() {
	var name = $('#txt_ubicacion_nombre').val();
	
	if(name.trim() == '') {
		$('#txt_ubicacion_nombre').tooltip('show');
		$('#txt_ubicacion_nombre').focus();
		return false;
	}else {
		var actionPath;
		
		if($('#locationAction').val() == 'new') {
			actionPath = '/insertLocation';
		}else {
			actionPath = '/updateLocation';
		}
		
		$('#location').submit(function() {
			$.ajax({
				type : 'POST',
				url : $('#appContextPath').val() + actionPath,
				data : $('#location').serialize(),
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
					
					window.location = $('#appContextPath').val() + '/adminLocations?' + status + '=1&msg=' + msg;
					$('#modal_nueva_ubicacion').modal('hide');
				},
				error: function(e) {
					window.location = $('#appContextPath').val() + '/adminLocations?error=1';
					$('#modal_nueva_ubicacion').modal('hide');
				}
			});
			return false;
		});
	}
}

var deleteLocation = function() {
	$.ajax({
		type : 'POST',
		url : $('#appContextPath').val() + '/deleteLocation',
		data : 'locationId=' + $('#locationToDelete').val(),
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
			
			window.location = $('#appContextPath').val() + '/adminLocations?' + status + '=1&msg=' + msg;
			$('#modal_confirm_delete').modal('hide');
		},
		error: function(e) {
			window.location = $('#appContextPath').val() + '/adminLocations?error=1';
			$('#modal_confirm_delete').modal('hide');
		}
	});
	
	return false;
}

function showLocationModal(id, name, description) {
	if(id != null && id > 0) {
		$('#locationId').val(id);
		$('#locationAction').val('update');
		$('#locationModalTitle').text('Actualizar ubicación');
		$('#txt_ubicacion_nombre').val(name);
		$('#txt_descripcion').val(description);
		$('#btnSaveLocation').text('Actualizar');
	} else {
		$('#locationAction').val('new');
		$('#locationModalTitle').text('Nueva ubicación');
		$('#txt_ubicacion_nombre').val('');
		$('#txt_descripcion').val('');
		$('#btnSaveLocation').text('Guardar');
	}
	$('#modal_nueva_ubicacion').modal({backdrop: 'static'/*, keyboard: false*/})
}

function confirmDelete(id, name) {
	$('#locationToDelete').val(id);
	$('#locationNameToDelete').text(name);
	$('#modal_confirm_delete').modal({backdrop: 'static'/*, keyboard: false*/})
}
