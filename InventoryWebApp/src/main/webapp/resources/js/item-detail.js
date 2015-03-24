$(function() {
	updateZoomPlugin(null);
	$('#btnPrintLabels').on('click', printLabels);
	$('#modal_print_labels').on('shown.bs.modal', function() {
		$('#txt_label_copies').focus();
	});
});

function updateZoomPlugin(imageSource) {
	if(imageSource != null) {
		$('#efecto_zoom_01').attr('src', imageSource);
		$("#efecto_zoom_01").data('zoom-image', imageSource);
		$('#efecto_zoom_02').attr('src', imageSource);
		$("#efecto_zoom_02").data('zoom-image', imageSource);
	}
	
	$(".efecto_zoom_screen_big").elevateZoom({
		zoomWindowPosition: 11,
        zoomWindowHeight: 350,
        zoomWindowWidth:350,
        zoomWindowOffetx: -10,
        tint:true, tintColour:'black', tintOpacity:0.5,
        zoomWindowFadeIn: 400, zoomWindowFadeOut: 200, lensFadeIn: 500, lensFadeOut: 500,
        scrollZoom : true,
        borderSize: 10
	});
	
	$(".efecto_zoom_screen_small").elevateZoom({
		zoomType: "inner",
        cursor: "crosshair"
	});
}

function showPicture(imgSource) {
	updateZoomPlugin(imgSource);
	event.preventDefault();
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

// ESCUCHA: Si cambia el tamaño de pantalla para volver a calcular el plugin
$(window).resize(function(){
    $(".zoomContainer").remove();
    updateZoomPlugin(null);
}).resize();//trigger the resize event on page load.
