$(function() {
	$('#breadcrumbCategory').on('load', setSelectedCategory($('#itemCategory').val()));
	$('#categoriesMenu').on('load', setCategoryMenu());
});

function setSelectedCategory(selectedCategory) {
	if(selectedCategory == null || selectedCategory == 0) {
		$('#breadcrumbCategory').html('<li class="active">Seleccione una categoría para filtrar resultados</li>');
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
						html += '<li><a href="' + 
									ctx + '/getItemsBycategory?categoryId=' + response[i].id + '">' + 
									response[i].name + '</a></li>';
					}
				}
				
				$('#breadcrumbCategory').html(html);
				$('#itemCategory').val(selectedCategory);
			},
			error: function(e) {
				$('#breadcrumbCategory').html('');
				$('#itemCategory').val(selectedCategory);
			},
			complete: maskPage(),
		});
	}
	
	event.preventDefault();
}

function buildSubmenu(category) {
	var html = '<li class="dropdown-submenu">' + 
			'<a href="' + ctx + '/getItemsBycategory?categoryId=' + category.id + 
			'" class="dropdown-toggle">' + category.name + '</a>' +
			'<ul class="dropdown-menu">';
	var descendants = category.descendantCategories;
	
	for (var i = 0; i < descendants.length; i++) {
		if(descendants[i].descendantCategories.length > 0) {
			html += buildSubmenu(descendants[i]);
		}else {
			html += '<li><a href="' + ctx + '/getItemsBycategory?categoryId=' + descendants[i].id + '">' + descendants[i].name + '</a></li>';
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
					html += '<li><a href="' + ctx + '/getItemsBycategory?categoryId=' + response[i].id + '">' + response[i].name + '</a></li>';
				}
			}
			
			$('#categoriesMenu').html(html);
		},
		error: function(e) {
			$('#categoriesMenu').html('<li><a href="' + ctx + '/listItems">Todo</a></li>');
		},
		complete: maskPage(),
	});
	
	event.preventDefault();
}
