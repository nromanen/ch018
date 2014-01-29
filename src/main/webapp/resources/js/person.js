$(document).ready(function() {
	  /**
	   * Edit user action
	   */
	  $("#addedituser").submit(function(event) {
		  console.log("begin");
		  var id = $("#id").val();
		  var uname = $("#uname").val();
		  var surname = $("#surname").val();
		  var email = $("#email").val();
		  var cellphone = $("#cellphone").val();
		  var multibookAllowed = $("#multibookAllowed").val();
		  var untimelyReturns = $("#untimelyReturns").val();
		  var timelyReturns = $("#timelyReturns").val();
		  var failedOrders = $("#failedOrders").val();
		  
		  var confirm = $("#confirm").prop('checked');
		  var json = { "id" : id, "name" : uname, "surname": surname, 
				  "email" : email, "cellphone": cellphone, 
				  "confirm": confirm, "timelyReturns": timelyReturns,
				  "untimelyReturns": untimelyReturns, "multibookAllowed" : multibookAllowed, 
				  "failedOrders" : failedOrders
				  };
		  $.ajax({
			  url: $("#addedituser").attr( "action"),
			  data: JSON.stringify(json),
			  type: "POST",
			  beforeSend: function(xhr) {  
		            xhr.setRequestHeader("Accept", "application/json");  
		            xhr.setRequestHeader("Content-Type", "application/json");  
		      },
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
	  
	  /**
	   * Edit user action (admin)
	   */
	  $("#addedituseradm").submit(function(event) {
		  console.log("begin");
		  var id = $("#id").val();
		  var uname = $("#uname").val();
		  var surname = $("#surname").val();
		  var email = $("#email").val();
		  var cellphone = $("#cellphone").val();
		  var multibookAllowed = $("#multibookAllowed").val();
		  var untimelyReturns = $("#untimelyReturns").val();
		  var timelyReturns = $("#timelyReturns").val();
		  var failedOrders = $("#failedOrders").val();
		  var role = $("#role").val();
		  console.log(role);
		  
		  var confirm = $("#confirm").prop('checked');
		  var json = { "id" : id, "name" : uname, "surname": surname, 
				  "email" : email, "cellphone": cellphone, 
				  "confirm": confirm, "timelyReturns": timelyReturns,
				  "untimelyReturns": untimelyReturns, "multibookAllowed" : multibookAllowed, 
				  "failedOrders" : failedOrders, "role" : role
				  };
		  $.ajax({
			  url: $("#addedituseradm").attr( "action"),
			  data: JSON.stringify(json),
			  type: "POST",
			  beforeSend: function(xhr) {  
		            xhr.setRequestHeader("Accept", "application/json");  
		            xhr.setRequestHeader("Content-Type", "application/json");  
		      },
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