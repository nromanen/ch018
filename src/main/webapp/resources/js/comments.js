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
		    		  console.log("success " + response);
		    		  location.reload();
		    	
			  },
			  error: function(response) {
				 location.reload();
				console.log("error: " + response);
			}
		  });
		  e.preventDefault();	  
	})
	
})