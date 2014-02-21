$(document).ready(function() { 
  
	$("a[id^=issueuserorder]").click(function() {
		var tr = "#" + $(this).parent().parent().attr("id");
		var id = $(tr + " td:first-child").text();

		var confirmed = $(tr + " .tdconfirmed").prop('checked');

		$("#aname").text(id);
		$("#booknamemessage").text($(tr + " .tdtitle").text() + " " + $(tr + " .tdauthors").text());
		$("#usernamemessage").text($("#username").text());
		$("#bookplacemessage").text($(tr + " .tdbookcase").text() + "/" + $(tr + " .tdshelf").text());	
		$("#confimation").prop('checked', confirmed);	
		if ($("#confimation").prop('checked') == false) {
		$("#confirmuser").show();
		}
		$("#days").val($(tr + " .tddays").text());
		$term = $("#days").val();
		
		$( "#slider-range-min" ).slider({
			  range: "min",
			  value: $term,
			  min: 0,
			  max: $term,
			  slide: function(event, ui) {
				  $("#days").val(ui.value);
			  }
		  });
		 $("#days").val($("#slider-range-min").slider("value")); 
		$('#action_popup').modal();
	})
		
	$("a[id^=issueorder]").click(function() {
		var tr = "#" + $(this).parent().parent().attr("id");
		var id = $(tr + " td:first-child").text();
		var pid= $(".pid" + id).text(); //get person id from table
		var confirmed = $(tr + " .tdconfirmed").prop('checked');
		$("#aname").text(id);
		$("#booknamemessage").text($("#bookname").text());
		$("#usernamemessage").text($(tr + " .tdname").text() + " " + $(tr + " .tdsurname").text());
		$("#bookplacemessage").text($(tr + " .tdbookcase").text() + "/" + $(tr + " .tdshelf").text());	
		$("#confimation").prop('checked', confirmed);	
		if ($("#confimation").prop('checked') == false) {
		$("#confirmuser").show();
		}
		$("#days").val($(tr + " .tddays").text());
		
		$term = $("#days").val();
		
		$( "#slider-range-min" ).slider({
			  range: "min",
			  value: $term,
			  min: 0,
			  max: $term,
			  slide: function(event, ui) {
				  $("#days").val(ui.value);
			  }
		  });
		 $("#days").val($("#slider-range-min").slider("value")); 
		$('#action_popup').modal();
	})
  
  
  
  
  
 
  
  
  
  $("#issueLink").click(function(event) {
	  if ($("#confimation").prop('checked') == false) {
		  return false;
	  }
	  var id = $("#aname").text();
	  var days = $("#days").val();
	  var href = $(event.target).attr("href")+id+"/"+days;
      $.ajax({
    	  url: href,
    	  type: "GET",
    	  success: function(data) {
    		  console.log("success");
    		  $(".table" + id).remove();
    		  $('#action_popup').modal("hide");
    	  },
    	  error: function(data) {
    		  console.log("error");
		  }
      });
      event.preventDefault();
  });
}) 