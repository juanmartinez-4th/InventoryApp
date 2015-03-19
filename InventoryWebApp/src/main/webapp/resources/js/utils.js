var isPageMasked = false, mask = $('#mask');

var logout = function() {
    $(".logout_form").submit();
}

var maskPage = function() {
	if(isPageMasked){
		mask.hide();
	} else {
		mask.show();
	}

	isPageMasked = !isPageMasked;
};

window.setTimeout(function() {
	$(".flash").fadeTo(500, 0).slideUp(500, function() {
		$(this).remove();
    });
}, 5000);

$(".numeric_field").on('keypress', function(e) {
	var s = String.fromCharCode(e.charCode);
    return e.charCode === 0 || /\d/.test(s);
});

$(".decimal_field").on('keypress', function(e) {
	var s = String.fromCharCode(e.charCode);
    var containsDecimalPoint = /\./.test($(".decimal_field").val());
    return e.charCode === 0 || /\d/.test(s) || 
        /\./.test(s) && !containsDecimalPoint;
});

//PROBLEMA: Fixed FOOTER tapa contenido del MAIN content
//FIX: LEER la altura del footer y pasársela al MARGIN-BOTTOM del contenido en MAIN
$(document).ready (function(){
	if($("footer") != null) {
	    var newHeight = $("footer").css( "height" );
	    $("main").css("margin-bottom", newHeight);
	}
});

$(window).resize (function(){
	if($("footer") != null) {
	    var newHeight = $("footer").css( "height" );
	    $("main").css("margin-bottom", newHeight);
	}
});
