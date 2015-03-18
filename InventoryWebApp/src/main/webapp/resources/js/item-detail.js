function showPicture(imgSource) {
	$('#efecto_zoom_01').attr('src', imgSource);
	$("#efecto_zoom_01").data('zoom-image', imgSource).elevateZoom({
		zoomWindowPosition: 11,
        zoomWindowHeight: 350,
        zoomWindowWidth:350,
        zoomWindowOffetx: -10,
        tint:true, tintColour:'black', tintOpacity:0.5,
        zoomWindowFadeIn: 400, zoomWindowFadeOut: 200, lensFadeIn: 500, lensFadeOut: 500,
        scrollZoom : true,
        borderSize: 10
	});
	$('#efecto_zoom_02').attr('src', imgSource);
	$("#efecto_zoom_02").data('zoom-image', imgSource).elevateZoom({
		zoomType: "inner",
        cursor: "crosshair"
	});
	event.preventDefault();
}
