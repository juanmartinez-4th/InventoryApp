$(function() {
	$('#txt_profile').attr('readonly', true);
	$('#btnSaveUser').on('click', saveUser);
	$('#btnDeleteUser').on('click', deleteUser);
	$('#modal_nuevo_usuario').on('shown.bs.modal', function() {
		$('#txt_nombre_usuario').focus();
	});
	$("[data-toggle=tooltip").tooltip();
	$('#usersTable').dataTable({
		"columnDefs": [{ "targets": [3, 4], "orderable": false , "searchable": false}],
		"language": {
            "url": ctx + "/resources/localisation/dataTables.spanish.lang"
        }
	});
});

var saveUser = function() {
	if($('#txt_nombre_usuario').val() == '') {
		$('#txt_nombre_usuario').tooltip('show');
		$('#txt_nombre_usuario').focus();
		return false;
	}else if($('#txt_password').val() == '') {
		$('#txt_password').tooltip('show');
		$('#txt_password').focus();
		return false;
	}else if($('#txt_profile').val() == '') {
		$('#txt_profile').tooltip('show');
		$('#txt_profile').focus();
		return false;
	}else {
		var actionPath;
		
		if($('#userAction').val() == 'new') {
			actionPath = ctx + '/saveUser';
		}else {
			actionPath = ctx + '/updateUser';
		}
		
		$('#userForm').submit(function() {
			$.ajax({
				type : 'POST',
				url : actionPath,
				data : $('#userForm').serialize(),
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
					
					window.location = ctx + '/adminUsers?' + status + '=1&msg=' + msg;
					$('#modal_nuevo_usuario').modal('hide');
				},
				error: function(e) {
					window.location = ctx + '/adminUsers?error=1';
					$('#modal_nuevo_usuario').modal('hide');
				},
				complete: maskPage()
			});
			return false;
		});
	}
}

var deleteUser = function() {
	$.ajax({
		type : 'POST',
		url : ctx + '/deleteUser',
		data : 'username=' + $('#userToDelete').text(),
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
			
			window.location = ctx + '/adminUsers?' + status + '=1&msg=' + msg;
			$('#modal_confirm_delete').modal('hide');
		},
		error: function(e) {
			window.location = ctx + '/adminUsers?error=1';
			$('#modal_confirm_delete').modal('hide');
		},
		complete: maskPage()
	});
	
	return false;
}

function showUserModal(username, password, role, enabled) {
	if(username != '') {
		$('#userAction').val('update');
		$('#catModalTitle').text('Modificar usuario');
		$('#txt_nombre_usuario').val(username);
		$('#txt_nombre_usuario').attr('readonly', true);
		$('#txt_password').val(password);
		$('#txt_profile').val(role);
		$('#userEnabled').val(enabled);
		if(enabled == 'true') {
			$('#txt_enabled').val('Habilitado');
		}else {
			$('#txt_enabled').val('Deshabilitado');
		}
		$('#btnSaveUser').text('Actualizar');
	} else {
		$('#userAction').val('new');
		$('#catModalTitle').text('Agregar usuario');
		$('#txt_nombre_usuario').val('');
		$('#txt_nombre_usuario').attr('readonly', false);
		$('#txt_profile').val('');
		$('#txt_password').val('');
		$('#userEnabled').val('true');
		$('#txt_enabled').val('Habilitado');
		$('#btnSaveUser').text('Guardar');
	}
	$('#modal_nuevo_usuario').modal({backdrop: 'static'/*, keyboard: false*/})
}

function confirmDelete(username) {
	$('#userToDelete').text(username);
	$('#modal_confirm_delete').modal({backdrop: 'static'/*, keyboard: false*/})
}

function setEnabledUser(enabled) {
	$('#userEnabled').val(enabled);
	if(enabled == 'true') {
		$('#txt_enabled').val('Habilitado');
	}else {
		$('#txt_enabled').val('Deshabilitado');
	}
}

function setSelectedAuthority(selectedAuthority) {
	$('#txt_profile').val(selectedAuthority);
}
