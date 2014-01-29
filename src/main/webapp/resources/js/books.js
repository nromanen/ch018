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
		    		  for(var key in response.errorsMap) { 
		    			  console.log(response.errorsMap[key]);
		    			  $("#error" + key).text(response.errorsMap[key]);
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
	  
	  $("#advancedsearch").submit(function(event) {
		  var title = $("#advtitle").val();
		  var authors = $("#advautors").val();
		  var year = $("#advyear").val();
		  var publication = $("#advpublication").val();
		  var available = $("#advavailable").prop('checked');
		  var sortby = $("#advsort").val();
		  if (year == "") {
			  year = 0;
		  }
		  var json = {"title" : title, "authors": authors, "year" : year, "publication": publication, "available": available, "sortby": sortby};
		  $.ajax({
			  url: $("#advancedsearch").attr( "action"),
			  data: JSON.stringify(json),
			  type: "POST",
			  beforeSend: function(xhr) {  
		            xhr.setRequestHeader("Accept", "application/json");  
		            xhr.setRequestHeader("Content-Type", "application/json");  
		      },
		      success: function(response) {
		    	  console.log(response);
		    	  for(var key in response) {
		    		  console.log(key['title']);
		    	  }
		    	  
		    	  //location.reload();
		    	  /*if (response.status == "SUCCESS") {
		    		  console.log("success " + response.result);
		    		  $('#action_popup').modal("hide");
		    		  location.reload();				// temporary!!!!!!!
		    	  } else {
		    		  for(var key in response.errorsMap) { 
		    			  console.log(response.errorsMap[key]);
		    			  $("#error" + key).text(response.errorsMap[key]);
		    		  }
		    	  }*/
			  },
			  error: function(response) {
				  $("#errorbook").text("Error occured when data sending to server");
				  console.log("error: " + response);
			}
		  });
		  console.log(json);
		  event.preventDefault();	
	  })
	  
	  
})