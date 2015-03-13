$(function() {
	$('#breadcrumbCategory').on('load', setSelectedCategory($('#itemCategory').val()));
});

function setSelectedCategory(selectedCategory) {
	if(selectedCategory == null || selectedCategory == 0) {
		$('#breadcrumbCategory').html('<li class="active">Sin clasificación</li>');
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
			complete: maskPage(),
		});
	}
	
	event.preventDefault();
}
