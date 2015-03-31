$(function() {
	$('#breadcrumbCategory').on('load', setSelectedCategory($('#itemCategory').val()));
	$('#categoriesMenu').on('load', setCategoryMenu());
	$('#categories-menu').smartmenus({
		subMenusSubOffsetX: 1,
		subMenusSubOffsetY: -8
	});
	$('#itemsTable').dataTable({
		"columnDefs": [{ "targets": [3, 4], "orderable": false , "searchable": false}],
		"language": {
            "url": ctx + "/resources/localisation/dataTables.spanish.lang"
        }
	});
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
						var itemUrl = ctx + '/getItemsBycategory?categoryId=' + response[i].id;
						itemUrl += $('#showGridView').length ? '&showGrid=true' : '';
						
						html += '<li><a href="' + itemUrl + '">' + response[i].name + '</a></li>';
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
	var itemUrl = ctx + '/getItemsBycategory?categoryId=' + category.id;
	itemUrl += $('#showGridView').length ? '&showGrid=true' : '';
	
	var html = '<li><a href="' + itemUrl + '">' + category.name + '</a><ul>';
	var descendants = category.descendantCategories;
	
	for (var i = 0; i < descendants.length; i++) {
		if(descendants[i].descendantCategories.length > 0) {
			html += buildSubmenu(descendants[i]);
		}else {
			itemUrl = ctx + '/getItemsBycategory?categoryId=' + descendants[i].id;
			itemUrl += $('#showGridView').length ? '&showGrid=true' : '';
			
			html += '<li><a href="' + itemUrl + '">' + descendants[i].name + '</a></li>';
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
					var itemUrl = ctx + '/getItemsBycategory?categoryId=' + response[i].id;
					itemUrl += $('#showGridView').length ? '&showGrid=true' : '';
					
					html += '<li><a href="' + itemUrl + '">' + response[i].name + '</a></li>';
				}
			}
			
			$('#categoriesMenu').html(html);
			$('#categories-menu').smartmenus('refresh');
		},
		error: function(e) {
			var itemUrl = ctx + '/listItems';
			itemUrl += $('#showGridView').length ? '&showGrid=true' : '';
			$('#categoriesMenu').html('<li><a href="' + itemUrl + '">Todo</a></li>');
		},
		complete: maskPage(),
	});
	
	event.preventDefault();
}
