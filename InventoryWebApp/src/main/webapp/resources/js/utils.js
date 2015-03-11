var isPageMasked = false, mask = $('#mask');

var maskPage = function() {
	if(isPageMasked){
		mask.hide();
	} else {
		mask.show();
	}

	isPageMasked = !isPageMasked;
};