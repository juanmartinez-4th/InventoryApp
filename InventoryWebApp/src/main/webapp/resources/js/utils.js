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