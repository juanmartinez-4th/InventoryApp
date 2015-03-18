var isPageMasked = false, mask = $('#mask');

var logoutSubmit = function() {
    document.getElementById("logoutForm").submit();
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
	$(".flash").fadeTo(500, 0).slideUp(500, function(){
		$(this).remove();
    });
}, 5000);

$(".numeric_field").on('keydown', function(e) {
	// Allow: backspace, delete, tab, escape, enter and .
    if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
         // Allow: Ctrl+A
        (e.keyCode == 65 && e.ctrlKey === true) || 
         // Allow: home, end, left, right, down, up
        (e.keyCode >= 35 && e.keyCode <= 40)) {
             // let it happen, don't do anything
             return;
    }
    // Ensure that it is a number and stop the keypress
    if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
        e.preventDefault();
    }
});
