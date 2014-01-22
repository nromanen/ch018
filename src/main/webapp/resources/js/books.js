$(document).ready(function() {
	  /**
	   * Edit book action
	   */
	  $("#editbook").submit(function(event) {
		  var id = $("#id").val();
		  var title = $("#title").val();
		  var authors = $("#authors").val();
		  var year = $("#year").val();
		  var publication = $("#publication").val();
		  var pages = $("#pages").val();
		  var description = $("#description").val();
		  var term = $("#term").val();
		  var bookcase = $("#bookcase").val();
		  var shelf = $("#shelf").val();
		  var genre = $("#genre").val();
		  var count = $("#count").val();
		  var available = $("#available").val();
		  var json = { "id" : id, "title" : title, "authors": authors, "year" : year, "publication": publication, 
				  "pages" : pages, "description": description, "term" : term, "bookcase": bookcase,
				  "shelf" : shelf, "genre": genre, "count": count, "available": available};
		  $.ajax({
			  url: $("#editbook").attr( "action"),
			  data: JSON.stringify(json),
			  type: "POST",
			  beforeSend: function(xhr) {  
		            xhr.setRequestHeader("Accept", "application/json");  
		            xhr.setRequestHeader("Content-Type", "application/json");  
		      },
		      success: function(response) {
		    	  if (response.status == "SUCCESS") {
		    		  console.log("success " + response.result);
		    		  $('#action_popup').modal("hide");
		    		  location.reload();				// temporary!!!!!!!
		    	  } else {
		    		  for(i = 0 ; i < response.result.length ; i++){
		    			  console.log("error " + response.result[i].defaultMessage);
		    			  $("#error" + response.result[i].field).text(response.result[i].defaultMessage);
		    		  }
		    	  }
			  },
			  error: function(response) {
				  $("#errorbook").text("Error occured when data sending to server");
				  console.log("error: " + response);
			}
		  });
		  event.preventDefault();	
	});
})