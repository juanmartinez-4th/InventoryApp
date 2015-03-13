$(function() {
	$('#btnSaveCategory').on('click', saveCategory);
	$('#btnDeleteCategory').on('click', deleteCategory);
	$('#modal_nueva_categoria').on('shown.bs.modal', function() {
		setCategoryMenu();
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
					
					window.location = ctx + '/adminCategories?' + status + '=1&msg=' + msg;
					$('#modal_nueva_categoria').modal('hide');
				},
				error: function(e) {
					window.location = ctx + '/adminCategories?error=1';
					$('#modal_nueva_categoria').modal('hide');
				},
				complete: maskPage()
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
			url : ctx + '/getCategoryHierarchy',
			data : 'categoryId=' + selectedCategory,
			beforeSend: maskPage(),
			success : function(response) {
				var html = '';
				
				for (var i = 0; i < response.length; i++) {
					if(i == (response.length - 1)) {
						html += '<li class="active">' + response[i].name + '</li>';
					}else {
						html += '<li>' + response[i].name + '</li>';
					}
				}
				
				$('#breadcrumbParentcat').html(html);
				$('#parentCategory').val(selectedCategory);
			},
			error: function(e) {
				$('#breadcrumbParentcat').html('');
				$('#parentCategory').val(selectedCategory);
			},
			complete: maskPage()
		});
	}
	
	event.preventDefault();
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

function buildSubmenu(category) {
	var html = '<li onclick="javascript:setParentCategory(' + category.id + ')" class="dropdown-submenu">' + 
			'<a href="#" class="dropdown-toggle">' + category.name + '</a>' +
			'<ul class="dropdown-menu">';
	var descendants = category.descendantCategories;
	
	for (var i = 0; i < descendants.length; i++) {
		if(descendants[i].descendantCategories.length > 0) {
			html += buildSubmenu(descendants[i]);
		}else {
			html += '<li onclick="javascript:setParentCategory(' + descendants[i].id + ')"><a href="#">' + descendants[i].name + '</a></li>';
		}
	}
	
	html += '</ul></li>';
	
	return html;
}

function setCategoryMenu() {
	$.ajax({
		type : 'POST',
		url : ctx + '/getCategoryTree',
		beforeSend: maskPage(),
		success : function(response) {
			var html = $('#categoriesMenu').html();
			
			for (var i = 0; i < response.length; i++) {
				if(response[i].descendantCategories.length > 0) {
					html += buildSubmenu(response[i]);
				}else {
					html += '<li onclick="javascript:setParentCategory(' + response[i].id + ')"><a href="#">' + response[i].name + '</a></li>';
				}
			}
			
			$('#categoriesMenu').html(html);
		},
		error: function(e) {
			$('#categoriesMenu').html('<li onclick="javascript:setParentCategory(0)"><a href="#">Ninguna</a></li>');
		},
		complete: maskPage(),
	});
	
	return false;
}
