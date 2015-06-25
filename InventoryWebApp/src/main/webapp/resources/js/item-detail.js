$(function() {
	updateZoomPlugin(null);
	$('#btnPrintLabels').on('click', printLabels);
	$('#modal_print_labels').on('shown.bs.modal', function() {
		$('#txt_label_copies').focus();
	});
	$('[data-toggle=tooltip').tooltip();
	setCategory($('#itemCategory').val());
	$('#itemCode').attr('readonly', '');
	$('#existencia_value').attr('readonly', '');
	$('#existencia_unit').attr('readonly', '');
	$('#itemDescription').attr('readonly', '');
	$('#itemDetail').attr('readonly', '');
	$('#txt_seccion').attr('readonly', '');
	$('#txt_pasillo').attr('readonly', '');
	$('#txt_anaquel').attr('readonly', '');
	$('#txt_casilla').attr('readonly', '');
	$('#txt_proyecto').attr('readonly', '');
	$('#txt_costo').attr('readonly', '');
	$('#txt_precio_venta').attr('readonly', '');
	$('#txt_precio_renta').attr('readonly', '');
});

function updateZoomPlugin(imageSource) {	
	$('.efecto_zoom_screen_big').elevateZoom({
		gallery:'gallery1',
		galleryActiveClass: 'active',
		zoomWindowPosition: 11,
        zoomWindowHeight: 350,
        zoomWindowWidth:350,
        zoomWindowOffetx: -10,
        tint:true, tintColour:'black', tintOpacity:0.5,
        zoomWindowFadeIn: 400, zoomWindowFadeOut: 200, lensFadeIn: 500, lensFadeOut: 500,
        scrollZoom : true,
        borderSize: 10
	});
	
	$('.efecto_zoom_screen_small').elevateZoom({
		gallery:'gallery1',
		galleryActiveClass: 'active',
		zoomType: 'inner',
        cursor: 'crosshair'
	});
	
	$('.efecto_zoom_screen_big').bind('click', function(e) {
		var ez = $('.efecto_zoom_screen_big').data('elevateZoom');
		$.fancybox(ez.getGalleryList()); 
		return false; 
	});
	
	$('.efecto_zoom_screen_small').bind('click', function(e) {
		var ez = $('.efecto_zoom_screen_big').data('elevateZoom');
		$.fancybox(ez.getGalleryList()); 
		return false; 
	});
}

function showLabelsModal() {
	$('#txt_label_code').val($('#itemCode').val());
	$('#modal_print_labels').modal({backdrop: 'static'/*, keyboard: false*/});
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
			data : 'code=' + code + '&copies=' + copies,
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

function setCategory(selectedCategory) {
	$.ajax({
		type : 'POST',
		url : ctx + '/getCategoryHierarchy',
		data : 'categoryId=' + selectedCategory,
		beforeSend: maskPage(),
		success : function(response) {
			var html = '';
			
			for (var i = 0; i < response.length; i++) {
				if(i == (response.length - 1)) {
					html += '<li class="active resalta_negro">' + response[i].name + '</li>';
				}else {
					html += '<li class="resalta_negro">' + response[i].name + '</li>';
				}
			}
			
			$('#breadcumbCategory').html(html);
		},
		error: function(e) {
			$('#breadcumbCategory').html('');
		},
		complete: maskPage()
	});
}

// ESCUCHA: Si cambia el tamaño de pantalla para volver a calcular el plugin
$(window).resize(function(){
    $('.zoomContainer').remove();
    updateZoomPlugin(null);
}).resize();//trigger the resize event on page load.
