$(function() {
	$('#btnSaveProject').on('click', saveProject);
	$('#btnDeleteProject').on('click', deleteProject);
	$('#modal_nuevo_proyecto').on('shown.bs.modal', function() {
		$('#txt_proyecto_nombre').focus();
	});
});

var saveProject = function() {
	var name = $('#txt_proyecto_nombre').val();
	
	if(name.trim() == '') {
		$('#txt_proyecto_nombre').tooltip('show');
		$('#txt_proyecto_nombre').focus();
		return false;
	}else {
		var actionPath;
		
		if($('#projectAction').val() == 'new') {
			actionPath = '/insertProject';
		}else {
			actionPath = '/updateProject';
		}
		
		$('#project').submit(function() {
			$.ajax({
				type : 'POST',
				url : $('#appContextPath').val() + actionPath,
				data : $('#project').serialize(),
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
					
					window.location = $('#appContextPath').val() + '/adminProjects?' + status + '=1&msg=' + msg;
					$('#modal_nuevo_proyecto').modal('hide');
				},
				error: function(e) {
					window.location = $('#appContextPath').val() + '/adminProjects?error=1';
					$('#modal_nuevo_proyecto').modal('hide');
				}
			});
			return false;
		});
	}
}

var deleteProject = function() {
	$.ajax({
		type : 'POST',
		url : $('#appContextPath').val() + '/deleteProject',
		data : 'projectId=' + $('#projectToDelete').val(),
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
			
			window.location = $('#appContextPath').val() + '/adminProjects?' + status + '=1&msg=' + msg;
			$('#modal_confirm_delete').modal('hide');
		},
		error: function(e) {
			window.location = $('#appContextPath').val() + '/adminProjects?error=1';
			$('#modal_confirm_delete').modal('hide');
		}
	});
	
	return false;
}

function showProjectModal(id, name, description) {
	if(id != null && id > 0) {
		$('#projectId').val(id);
		$('#projectAction').val('update');
		$('#catModalTitle').text('Actualizar proyecto');
		$('#txt_proyecto_nombre').val(name);
		$('#txt_descripcion').val(description);
		$('#btnSaveProject').text('Actualizar');
	} else {
		$('#projectAction').val('new');
		$('#catModalTitle').text('Nuevo proyecto');
		$('#txt_proyecto_nombre').val('');
		$('#txt_descripcion').val('');
		$('#btnSaveProject').text('Guardar');
	}
	$('#modal_nuevo_proyecto').modal({backdrop: 'static'/*, keyboard: false*/})
}

function confirmDelete(id, name) {
	$('#projectToDelete').val(id);
	$('#projectNameToDelete').text(name);
	$('#modal_confirm_delete').modal({backdrop: 'static'/*, keyboard: false*/})
}
