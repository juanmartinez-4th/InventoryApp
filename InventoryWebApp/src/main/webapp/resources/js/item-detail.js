$(function() {
	updateZoomPlugin(null);
});

function updateZoomPlugin(imageSource) {
	if(imageSource != null) {
		$('#efecto_zoom_01').attr('src', imageSource);
		$("#efecto_zoom_01").data('zoom-image', imageSource);
		$('#efecto_zoom_02').attr('src', imageSource);
		$("#efecto_zoom_02").data('zoom-image', imageSource);
	}
	
	$("#efecto_zoom_01").elevateZoom({
		zoomWindowPosition: 11,
        zoomWindowHeight: 350,
        zoomWindowWidth:350,
        zoomWindowOffetx: -10,
        tint:true, tintColour:'black', tintOpacity:0.5,
        zoomWindowFadeIn: 400, zoomWindowFadeOut: 200, lensFadeIn: 500, lensFadeOut: 500,
        scrollZoom : true,
        borderSize: 10
	});
	
	$("#efecto_zoom_02").elevateZoom({
		zoomType: "inner",
        cursor: "crosshair"
	});
}

function showPicture(imgSource) {
	updateZoomPlugin(imgSource);
	event.preventDefault();
}

// ESCUCHA: Si cambia el tamaño de pantalla para volver a calcular el plugin
$(window).resize(function(){
    $(".zoomContainer").remove();
    updateZoomPlugin(null);
}).resize();//trigger the resize event on page load.
