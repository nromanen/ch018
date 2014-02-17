$(document).ready(function() {
	$(".show_image").tooltip({
	  	html:true,
	  	placement: "bottom",
	  	animation: true,
	  	title: function() {
	  		$img = $(this).parent().prev().text();
	  		$("#testimg").attr("src", "http://localhost:8080/library" + $img);
	  		return $('#popover_show_img').html();
	      }
	});
})