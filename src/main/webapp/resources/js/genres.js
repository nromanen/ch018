$(document).ready(function() {	  
	$("#newgenrebutton").click(function() {
		$('#action_popup').modal();
	})
	  /**
	   * Edit user action (admin)
	   */
	  $("#newgenre").submit(function(event) {
		  $.ajax({
			  url: $("#newgenre").attr( "action"),
			  data: $("#newgenre").serialize(),
		      type: "POST",
		      dataType: "json",
		      contentType : 'application/x-www-form-urlencoded',
			  mimeType : 'application/json',
		      success: function(response) {
		    	  if(response.status == "SUCCESS") {
		    		  console.log("success " + response.result);
		    		  $('#action_popup').modal("hide");
		    		  location.reload();				// temporary!!!!!!!
		    	  } else {
		    		  for(var key in response.errorsMap) { 
		    			  console.log(response.errorsMap[key]);
		    			  $("#error" + key).text(response.errorsMap[key]);
		    		  }
		    	  }
			  },
			  error: function(response) {
				  $("#errorperson").text("Error occured when data sending to server");
				console.log("error: " + response);
			}
		  });
		  event.preventDefault();	  
	});
})