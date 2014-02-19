$(document).ready(function() {	  
	$("#comment_it").submit(function(e) {
		 $.ajax({
			  url: $("#comment_it").attr( "action"),
			  data: $("#comment_it").serialize(),
		      type: "POST",
		      dataType: "json",
		      contentType : 'application/x-www-form-urlencoded',
			  mimeType : 'application/json',
		      success: function(response) {
		    	  if(response.status == "SUCCESS") {
		    		  console.log("success " + response.result);
		    		  //$comments = $("#comments").html();
		    		  //$("#comments").html('<div class="comment"> <h6>' + response.result.person.email + ':</h6><c:out value="' + response.result.comment + '" escapeXml="true" /> </div>' + comments)
		    	  } else {
		    		  for(var key in response.errorsMap) { 
		    			  console.log(response.errorsMap[key]);
		    			  $("#error" + key).text(response.errorsMap[key]);
		    		  }
		    	  }
			  },
			  error: function(response) {
				 // $("#errorperson").text("Error occured when data sending to server");
				console.log("error: " + response);
			}
		  });
		  e.preventDefault();	  
	})
	
})