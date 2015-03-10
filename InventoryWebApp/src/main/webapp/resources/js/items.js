$(function() {
	$('#btnSaveItem').on('click', saveItem);
	$('#btnSaveNewItem').on('click', saveItem);
});

var saveItem = function() {
	if($('#itemCategory').val() == 0) {
		$('#txt_categoria').tooltip('show');
		return false;
	}else {
		$('#itemCaptureForm').submit(function() {
			$.ajax({
				type : 'POST',
				url : $('#appContextPath').val() + '/insertItem',
				data : $('#itemCaptureForm').serialize(),
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
					
					window.location = $('#appContextPath').val() + '/listItems?' + status + '=1&msg=' + msg;
				},
				error: function(e) {
					window.location = $('#appContextPath').val() + '/listItems?error=1';
				}
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
				
				$('#breadcrumbCategory').html(html);
				$('#itemCategory').val(selectedCategory);
			},
			error: function(e) {
				$('#breadcrumbCategory').html('');
				$('#itemCategory').val(selectedCategory);
			}
		});
	}
	
	event.preventDefault();
}

function setUnitOfMeasure(selectedUnit, name) {
	$('#itemUnit').val(selectedUnit);
	$('#btnUnitOfM').text(name)
	event.preventDefault();
}
