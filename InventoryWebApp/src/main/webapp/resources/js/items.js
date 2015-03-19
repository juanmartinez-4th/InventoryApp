$(function() {
	$('#txt_codigo').attr('readonly', '');
	$('#txt_descripcion').on('keyup', function() {
		var cat = $('#itemCategoryName').val().trim();
		var itemDesc = $('#txt_descripcion').val().trim();
		while(itemDesc.length < 3) {
			itemDesc += 'X';
		}
		var itemCode = (cat != '' ? cat.substring(0, 3).toUpperCase() : 'XXX') + $('#nextItemId').val() + itemDesc.substring(0, 3).toUpperCase();
		$('#txt_codigo').val(itemCode);
	});
	setCategoryMenu();
	$('#btnPrintLabels').on('click', printLabels);
	$('#modal_print_labels').on('shown.bs.modal', function() {
		$('#txt_label_copies').focus();
	});
});

function saveItem(captureNew) {
	if($('#itemCategory').val() == 0) {
		$('#txt_categoria').tooltip('show');
		event.preventDefault();
	}else if($('#txt_descripcion').val().trim() == '') {
		$('#txt_descripcion').tooltip('show');
		event.preventDefault();
	}else if($('#txt_existencia').val().trim() == '' || $('#itemUnit').val() == 0) {
		$('#txt_existencia').tooltip('show');
		event.preventDefault();
	}else {
		$('#redirectNew').val(captureNew);
		$('#itemCaptureForm').submit();
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
						$('#itemCategoryName').val(response[i].name);
						var codeSufix = $('#txt_descripcion').val().trim();
						while(codeSufix.length < 3) {
							codeSufix += 'X';
						}
						var itemCode = response[i].name.substring(0, 3).toUpperCase() + $('#nextItemId').val() + codeSufix.substring(0, 3).toUpperCase();
						$('#txt_codigo').val(itemCode);
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

function setProduction(selectedProduction, name) {
	$('#productionId').val(selectedProduction);
	$('#txt_produccion').val(name);
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

function selectFile(fileIndex) {
	$('#inputFile' + fileIndex).click();
}

function setSelectedFile(fileIndex) {
	var file = $('#inputFile' + fileIndex).val();
    var fileName = file.split('\\');
    $('#selectedFileName' + fileIndex).html(fileName[fileName.length-1]);
    event.preventDefault();
}

function showLabelsModal() {
	if($('#txt_descripcion').val() == '' || $('#itemCategoryName').val() == '') {
		var html = '<div class="alert alert-danger flash" role="alert">' + 
	        '<button type="button" class="close" data-dismiss="alert" aria-label="Cerrar">' + 
	            '<span aria-hidden="true">&times;</span>' + 
	        '</button>' + 
	        '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>' + 
	        '<span class="sr-only">Error: </span> Antes de imprimir etiquetas capture toda la información del artículo' + 
	    '</div>';
		
		$('#divMessages').html(html);
	}else {
		$('#txt_label_code').val($('#txt_codigo').val());
		$('#modal_print_labels').modal({backdrop: 'static'/*, keyboard: false*/})
	}
}

var printLabels = function () {
	var code = $('#txt_label_code').val();
	var copies = $('#txt_label_copies').val();
	
	if(copies == '' || copies == '0' || copies == '00') {
		$('#txt_label_copies').tooltip('show');
		$('#txt_label_copies').focus();
		return false;
	}else {$.ajax({
			type : 'POST',
			url : ctx + '/getItemTag',
			data : 'code=' + code + "&copies=" + copies,
			beforeSend: maskPage(),
			success : function(response) {
				window.open('data:application/pdf;base64, ' + response, '_blank', 'menubar=no,status=no,scrollbars=yes,width=500,height=600');
				$('#modal_print_labels').modal('hide');
			},
			error: function(e) {
				$('#modal_print_labels').modal('hide');
			},
			complete: maskPage()
		});
	}
	
	event.preventDefault();
}
