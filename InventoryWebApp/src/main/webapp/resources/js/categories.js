$(function() {
	$('#btnSaveCategory').on('click', saveCategory);
	$('#btnDeleteCategory').on('click', deleteCategory);
	$('#breadcrumbCategory').on('load', 
			setSelectedCategory($('#breadcrumbCategory'), $('#parentCategory').val()));
	$('#modal_nueva_categoria').on('shown.bs.modal', function() {
		$('#txt_categoria_nombre').focus();
	});
});

function setSelectedCategory(breadcumb, selectedCategory) {
	if(selectedCategory == null || selectedCategory == 0) {
		if(breadcumb.is($('#breadcrumbCategory'))) {
			breadcumb.html('<li class="active">Categorías principales</li>');
		}else {
			breadcumb.html('');
		}
		$('#parentCategory').val(selectedCategory);
		$('#btnAddNewCategory').text('Nueva Categoría');
	}else {
		$('#btnAddNewCategory').text('Nueva Subcategoría');
		$.ajax({
			type : 'POST',
			url : ctx + '/getCategoryHierarchy',
			data : 'categoryId=' + selectedCategory,
			beforeSend: maskPage(),
			success : function(response) {
				var html = '';
				
				if(breadcumb.is($('#breadcrumbCategory'))) {
					html = '<li><a href="' + ctx + '/adminCategories">Todas</a></li>';
				}
				
				for (var i = 0; i < response.length; i++) {
					if(i == (response.length - 1)) {
						html += '<li class="active">' + response[i].name + '</li>';
					}else {
						if(breadcumb.is($('#breadcrumbCategory'))) {
							html += '<li><a href="' + 
										ctx + '/adminCategories?parentCategory=' + response[i].id + '">' + 
										response[i].name + '</a></li>';
						}else {
							html += '<li>' + response[i].name + '</li>';
						}
					}
				}
				
				breadcumb.html(html);
				$('#parentCategory').val(selectedCategory);
			},
			error: function(e) {
				breadcumb.html('');
				$('#parentCategory').val(selectedCategory);
			},
			complete: maskPage(),
		});
	}
	
	if(event != null) {
		event.preventDefault();
	}
}

var saveCategory = function() {
	var name = $('#txt_categoria_nombre').val();
	
	if(name.trim() == '') {
		$('#txt_categoria_nombre').tooltip('show');
		$('#txt_categoria_nombre').focus();
		return false;
	}else {
		var actionPath;
		
		if($('#categoryAction').val() == 'new') {
			actionPath = ctx + '/insertCategory';
		}else {
			actionPath = ctx + '/updateCategory';
		}
		
		$('#category').submit(function() {
			$.ajax({
				type : 'POST',
				url : actionPath,
				data : $('#category').serialize(),
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
					
					if($('#parentCategory').val() == 0) {
						window.location = ctx + '/adminCategories?' + status + '=1&msg=' + msg;
					}else {
						window.location = ctx + '/adminCategories?parentCategory=' + $('#parentCategory').val() + '&' + status + '=1&msg=' + msg;
					}
					
					$('#modal_nueva_categoria').modal('hide');
				},
				error: function(e) {
					if($('#parentCategory').val() == 0) {
						window.location = ctx + '/adminCategories?error=1';
					}else {
						window.location = ctx + '/adminCategories?parentCategory=' + $('#parentCategory').val() + '&error=1';
					}
					$('#modal_nueva_categoria').modal('hide');
				},
				complete: maskPage()
			});
			return false;
		});
	}
}

var deleteCategory = function() {
	$.ajax({
		type : 'POST',
		url : ctx + '/deleteCategory',
		data : 'categoryId=' + $('#categoryToDelete').val(),
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
			
			window.location = ctx + '/adminCategories?' + status + '=1&msg=' + msg;
			$('#modal_confirm_delete').modal('hide');
		},
		error: function(e) {
			window.location = ctx + '/adminCategories?error=1';
			$('#modal_confirm_delete').modal('hide');
		},
		complete: maskPage()
	});
	
	return false;
}

function showCategoryModal(id, name, description) {
	if(id != null && id > 0) {
		$('#categoryId').val(id);
		$('#categoryAction').val('update');
		if($('#parentCategory').val() == 0) {
			$('#catModalTitle').text('Actualizar categoría');
			$('#divParentCategory').hide();
		}else {
			$('#catModalTitle').text('Actualizar subcategoría');
			$('#divParentCategory').show();
		}
		$('#txt_categoria_nombre').val(name);
		$('#txt_descripcion').val(description);
		$('#btnSaveCategory').text('Actualizar');
	} else {
		$('#categoryAction').val('new');
		if($('#parentCategory').val() == 0) {
			$('#catModalTitle').text('Nueva categoría');
			$('#divParentCategory').hide();
		}else {
			$('#catModalTitle').text('Nueva subcategoría');
			$('#divParentCategory').show();
		}
		$('#txt_categoria_nombre').val('');
		$('#txt_descripcion').val('');
		$('#btnSaveCategory').text('Guardar');
	}
	$('#formParentCategory').val($('#parentCategory').val());
	setSelectedCategory($('#breadcrumbParentcat'), $('#parentCategory').val());
	$('#modal_nueva_categoria').modal({backdrop: 'static'/*, keyboard: false*/})
}

function confirmDelete(id, name) {
	$('#categoryToDelete').val(id);
	$('#categoryNameToDelete').text(name);
	$('#modal_confirm_delete').modal({backdrop: 'static'/*, keyboard: false*/})
}
