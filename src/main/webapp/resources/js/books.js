$(document).ready(function() {
	  /**
	   * Edit book action
	   */
	  $("#editbook").submit(function(event) {
		  $("body").addClass("loading");
		  $.ajax({
			  url: $("#editbook").attr( "action"),
			  //data: JSON.stringify(json),
			  data : $('#editbook').serialize(),
			  type: "POST",
			  dataType : "json",
				contentType : 'application/x-www-form-urlencoded',
				mimeType : 'application/json',
		      success: function(response) {
		    	  if (response.status == "SUCCESS") {
		    		  console.log("success " + response.result);
		    		  $('#action_popup').modal("hide");
		    		  location.reload();				// temporary!!!!!!!
		    	  } else {
		    		  for(var key in response.errorsMap) { 
		    			  console.log(response.errorsMap[key]);
		    			  $("#error" + key).text(response.errorsMap[key]);
		    		  }
		    	  }
		    	  $("body").removeClass("loading");
			  },
			  error: function(response) {
				  $("#errorbook").text("Error occured when data sending to server");
				  console.log("error: " + response);
				  $("body").removeClass("loading");
			}
		  });
		  event.preventDefault();	
	  });  
})