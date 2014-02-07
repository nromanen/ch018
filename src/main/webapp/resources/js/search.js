/**
 * search autocomplete
 */
$(document).ready(function() { 
	console.log("haha0");
	$('#isearch').autocomplete({
		serviceUrl: '${pageContext.request.contextPath}/getBooks',
		paramName: "bookName",
		delimiter: ",",
	   transformResult: function(response) {
		   return {
			   //must convert json to javascript object before process
			   suggestions: $.map($.parseJSON(response), function(item) {
				   return { value: item.title, data: item.id };
			   })
		   };
	   }
	});
 });