$(document).ready(function() {
	  /**
	   * Edit book action
	   */
	$(".reset_img").click(function(event) {
		$("#file").val("").replaceWith($("#file").clone());
	})
	
	$("#editbook").validate();
	$("#file").bind('change', function() {
		if (this.files[0].size > 1000000) {
			$("#file").val("").replaceWith($("#file").clone());
		}
	});
	
	  $("#editbook").submit(function(event) {
		  var formData = new FormData($("#editbook")[0]);
		  console.log(formData);
		  $("body").addClass("loading");
		  $.ajax({
			  url: $("#editbook").attr( "action"),
			  data : formData,
			  type: "POST",
			  dataType : "json",
			  contentType : false,
			  mimeType : 'multipart/form-data',
			  cache: false,
			  processData:false,
		      success: function(response) {
		    	  if (response.status == "SUCCESS") {
		    		  console.log("success " + response.result);
		    		  $('#action_popup').modal("hide");
		    		  //location.reload();				// temporary!!!!!!!
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
	  
	  $(".delete_all_orders").click(function(event) {
		  var id = $(".book_id_remove").text();
		  var href = $(event.target).attr("href")+id+"/?all="+1;
		  $.ajax({
			  url: href,
	          type: "DELETE",
	          success: function(data) {
	        	  if (data == 0) {
	        		  console.log("error delete");
	        		  $(".book_id_remove").text(id);
	        		  $(".alert").hide();
	        	  }
	        	  else {
	        		  console.log("success");
	        		  $(".table" + id).remove();
	        		  $(".alert").hide();
	        	  }
	        	  $('#popup').modal("hide");
	          },
	          error: function(data) {
	        	  console.log("error");
	        	}
		  });
		  event.preventDefault();
	  });
})