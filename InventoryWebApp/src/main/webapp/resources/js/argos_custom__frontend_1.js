// PROBLEMA: Fixed FOOTER tapa contenido del MAIN content
// FIX: LEER la altura del footer y pasársela al MARGIN-BOTTOM del contenido en MAIN
$(document).ready (function(){
    var newHeight = $("footer").css( "height" );
    $("main").css("margin-bottom", newHeight);
});

$(window).resize (function(){
    var newHeight = $("footer").css( "height" );
    $("main").css("margin-bottom", newHeight);
});


// ACTIVA: Efecto ZOOM con clases  .efecto_zoom_screen_big  y  .efecto_zoom_screen_small

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

// ESCUCHA: Si cambia el tamaño de pantalla para volver a calcular el plugin
$(window).resize(function(){
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
})
    .resize();//trigger the resize event on page load.
