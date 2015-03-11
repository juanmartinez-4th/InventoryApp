$(function() {
	$('#btnSaveItem').on('click', saveItem);
	$('#btnSaveNewItem').on('click', saveItem);
	setCategoryMenu();
});

var saveItem = function() {
	if($('#itemCategory').val() == 0) {
		$('#txt_categoria').tooltip('show');
		return false;
	}else {
		$('#itemCaptureForm').submit(function() {
			$.ajax({
				type : 'POST',
				url : ctx + '/insertItem',
				data : $('#itemCaptureForm').serialize(),
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
					
					window.location = ctx + '/listItems?' + status + '=1&msg=' + msg;
				},
				error: function(e) {
					window.location = ctx + '/listItems?error=1';
				},
				complete: maskPage()
			});
			return false;
		});
	}
}

function setCategory(selectedCategory) {
	if(selectedCategory == 0) {
		$('#breadcrumbCategory').html('<li class="active">Seleccione una categoría</li>');
		$('#itemCategory').val(selectedCategory);
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
				
				$('#breadcrumbCategory').html(html);
				$('#itemCategory').val(selectedCategory);
			},
			error: function(e) {
				$('#breadcrumbCategory').html('');
				$('#itemCategory').val(selectedCategory);
			},
			complete: maskPage()
		});
	}
	
	event.preventDefault();
}

function setUnitOfMeasure(selectedUnit, name) {
	$('#itemUnit').val(selectedUnit);
	$('#btnUnitOfM').text(name)
	event.preventDefault();
}

function buildSubmenu(category) {
	var html = '<li onclick="javascript:setCategory(' + category.id + ')" class="dropdown-submenu">' + 
			'<a href="#" class="dropdown-toggle">' + category.name + '</a>' +
			'<ul class="dropdown-menu">';
	var descendants = category.descendantCategories;
	
	for (var i = 0; i < descendants.length; i++) {
		if(descendants[i].descendantCategories.length > 0) {
			html += buildSubmenu(descendants[i]);
		}else {
			html += '<li onclick="javascript:setCategory(' + descendants[i].id + ')"><a href="#">' + descendants[i].name + '</a></li>';
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
					html += '<li onclick="javascript:setCategory(' + response[i].id + ')"><a href="#">' + response[i].name + '</a></li>';
				}
			}
			
			$('#categoriesMenu').html(html);
		},
		error: function(e) {
			$('#categoriesMenu').html('');
		},
		complete: maskPage(),
	});
	
	event.preventDefault();
}
