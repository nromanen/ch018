$(document).ready(function() { 
  $max_term = $(".max_term").text();
  if ($max_term == null || $max_term < 1) {
	  $max_term = 90;
  }
  $term = 14;
  if ($max_term < 14) {$term = $max_term}
  
  $( "#slider-range-min" ).slider({
	  range: "min",
	  value: $term,
	  min: 0,
	  max: $max_term,
	  slide: function(event, ui) {
		  $("#days").val(ui.value);
	  }
  });
  $("#days").val($("#slider-range-min").slider("value")); 
}) 