/**
 * search autocomplete
 */
$(document).ready(function() { 
	$('#isearch').autocomplete({
		minLength: 2,
        delay: 500,
		source: $(".navbar-search").attr("action")+'/getBooks',
	});
	
	/**
	 * Advanced search autocomplete
	 */
	$('#advtitle').autocomplete({
		minLength: 2,
        delay: 500,
		source: $("#advancedsearch").attr("action")+'/getTitles',
	});
	
	$('#advautors').autocomplete({
		minLength: 2,
        delay: 500,
		source: $("#advancedsearch").attr("action")+'/getAuthors',
	});
	
	$('#advpublication').autocomplete({
		minLength: 2,
        delay: 500,
		source: $("#advancedsearch").attr("action")+'/getPublics',
	});

 });