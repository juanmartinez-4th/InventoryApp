$(function() {
	$('#btnSaveCategory').on('click', saveCategory);
	$('#btnDeleteCategory').on('click', deleteCategory);
	$('#modal_nueva_categoria').on('shown.bs.modal', function() {
		$('#txt_categoria_nombre').focus();
	});
});

var saveCategory = function() {
	var name = $('#txt_categoria_nombre').val();
	
	if(name.trim() == '') {
		$('#txt_categoria_nombre').tooltip('show');
		$('#txt_categoria_nombre').focus();
		return false;
	}else {
		var actionPath;
		
		if($('#categoryAction').val() == 'new') {
			actionPath = '/insertCategory';
		}else {
			actionPath = '/updateCategory';
		}
		
		$('#category').submit(function() {
			$.ajax({
				type : 'POST',
				url : $('#appContextPath').val() + actionPath,
				data : $('#category').serialize(),
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
					
					window.location = $('#appContextPath').val() + '/adminCategories?' + status + '=1&msg=' + msg;
					$('#modal_nueva_categoria').modal('hide');
				},
				error: function(e) {
					window.location = $('#appContextPath').val() + '/adminCategories?error=1';
					$('#modal_nueva_categoria').modal('hide');
				}
			});
			return false;
		});
	}
}

function setParentCategory(selectedCategory) {
	if(selectedCategory == 0) {
		$('#breadcrumbParentcat').html('');
		$('#parentCategory').val(selectedCategory);
	}else {
		$.ajax({
			type : 'POST',
			url : $('#appContextPath').val() + '/getCategoryHierarchy',
			data : 'categoryId=' + selectedCategory,
			success : function(response) {
				var parents = response.split(',');
				var html = '';
				
				for (var i = 0; i < parents.length; i++) {
					if(parents[i] != '' && (i == (parents.length - 1))) {
						html += '<li class="active">' + parents[i] + '</li>';
					}else if(parents[i] != '' && i != (parents.length - 1)) {
						html += '<li>' + parents[i] + '</li>';
					}
				}
				
				$('#breadcrumbParentcat').html(html);
				$('#parentCategory').val(selectedCategory);
			},
			error: function(e) {
				$('#breadcrumbParentcat').html('');
				$('#parentCategory').val(selectedCategory);
			}
		});
	}
	
	event.preventDefault();
}

var deleteCategory = function() {
	$.ajax({
		type : 'POST',
		url : $('#appContextPath').val() + '/deleteCategory',
		data : 'categoryId=' + $('#categoryToDelete').val(),
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
			
			window.location = $('#appContextPath').val() + '/adminCategories?' + status + '=1&msg=' + msg;
			$('#modal_confirm_delete').modal('hide');
		},
		error: function(e) {
			window.location = $('#appContextPath').val() + '/adminCategories?error=1';
			$('#modal_confirm_delete').modal('hide');
		}
	});
	
	return false;
}

function showCategoryModal(id, name, description, parentCategory) {
	if(id != null && id > 0) {
		$('#categoryId').val(id);
		$('#categoryAction').val('update');
		$('#catModalTitle').text('Actualizar categoría');
		$('#txt_categoria_nombre').val(name);
		$('#txt_descripcion').val(description);
		setParentCategory(parentCategory);
		$('#btnSaveCategory').text('Actualizar');
	} else {
		$('#categoryAction').val('new');
		$('#catModalTitle').text('Nueva categoría');
		$('#txt_categoria_nombre').val('');
		$('#txt_descripcion').val('');
		setParentCategory(0);
		$('#btnSaveCategory').text('Guardar');
	}
	$('#modal_nueva_categoria').modal({backdrop: 'static'/*, keyboard: false*/})
}

function confirmDelete(id, name) {
	$('#categoryToDelete').val(id);
	$('#categoryNameToDelete').text(name);
	$('#modal_confirm_delete').modal({backdrop: 'static'/*, keyboard: false*/})
}
