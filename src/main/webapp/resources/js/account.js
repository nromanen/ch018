$(document).ready(function() { 

  $('.datetimepicker').datetimepicker();
  
  $("input[id^=editIssueDate]").click(function(){
	  $id = $(this).next().val();
	  $("#oldIssue" + $id).hide();
	  $("#saveNewIssue" + $id).show();
	  $("#cancelIssueEdit" + $id).show();
	  $("#newIssue" + $id).show();
	  $("#newIssue" + $id).focus();
  
  })
  
   $("input[id^=saveNewIssue]").click(function(e) {
  	  $id = $(this).prev().val();
	  var currDate = (new Date).getTime();
	  var orderDate = (new Date($("#newIssue" +$id).val())).getTime();
	  console.log(currDate);
	  console.log(orderDate);
	  
	  if(orderDate < currDate) {
		  e.preventDefault();
		  $("#dateExpired").modal();
	  } else {
	  var href = $("#href").val();
	  console.log($("#editIssue" + $id).serialize());
	  $("#fail" + $id).hide("slow");
	  $.ajax({
		  url: href,
		  data: $("#editIssue" +$id).serialize(),
	      type: "POST",
	      dataType: "json",
	      contentType: 'application/x-www-form-urlencoded',
	      mimeType: 'application/json',
	      success: function(data){
	    	 if (data == 1) {
	    		 $("#editissue").modal();
	    		 setTimeout(function () {location.reload();}, 1500);
	         }
	         if (data == 0) {
	        	 $("#fail" + $id).show("slow");
	        	 console.log($("#fail" + $id).text());
	    	 }
	    	 console.log("1111");
	     },
	     error: function() {
	    	 console.log("22222");
	     }
	  })
	  }
	  e.preventDefault();
  }) 
  
  $("#passForm").submit(function(e) {
	  $("#errorpassword").text("");
	  $("#errornewPassword").text("");
	  $("#errorconfirmPassword").text("");
	  console.log("1111");
	  $.ajax({
		  url: $("#passForm").attr("action"),
		  data: $("#passForm").serialize(),
	      type: "POST",
	      dataType: "json",
	      contentType : 'application/x-www-form-urlencoded',
		  mimeType : 'application/json',
		   success: function(response) {
			   if(response.status == "SUCCESS") {
			     console.log("success" + response.result);
			     $("#passModal").modal();
			     
			   } else {
				    if(response.status == "FAIL") {
				    	console.log("FAIL");
				    	for(var key in response.errorsMap) {
				    		  console.log(response.errorsMap[key]);
				    		  $("#error" + key).text(response.errorsMap[key]);
				    	}
				    }
				    if(response.status == "WRONGPASS") {
				    	console.log("you enter wrong password");
				    	$(".alert").show();
				    }
			   }
		   },
		   error: function(response) {
			   
			   console.log("error");
		   }
	  })
	  e.preventDefault();
  })
  
  
  $("input[id^=cancelIssueEdit]").click(function(){
	  $id1 = $(this).next().val();
	  $("#oldIssue" + $id1).show();
	  $("#saveNewIssue" + $id1).hide();
	  $("#cancelIssueEdit" + $id1).hide();
	  $("#newIssue" + $id1).hide();
	  $("#fail" + $id1).hide("slow");
  })
  
  
  $("input[id=butt1]").click(function(){
	  $("#modal").modal();
	  
  })
  
  $("input[id^=deleteWish]").click(function(){
	  $id = $(this).next().val();
	  console.log($id);
	  $wishID = $("#wishID" + $id).val();
	  var href = $("#hrefrate").val();
	  console.log(href);
	  $.ajax({
		  url: href + "/" + $id,
		  type: "GET",
		  success: function() {
			  console.log("success");
			  $("#modal" + $id).modal();
			  setTimeout(function () {location.reload();}, 1500);
			  
		  },
		  error: function() {
			  console.log("error");
		  }
	  });
  })
  
  $("a[id^=addToWish]").click(function(){
	  $id = $(this).next().val();
	  console.log($id);
	  var href = $("#href").val();
	  console.log(href);
	  $.ajax({
		  url: href + "/" +$id,
		  type: "GET",
		  success: function(data) {
			  console.log(data);
			  if (data == 0){
				  console.log("11111");
				  $("#errorAdd" + $id).modal();
			  }
			  if (data == 1) {
			     console.log("22222");
			     $("#successAdd" + $id).modal();
			     setTimeout(function () {location.reload();}, 1500);
			  }
		  },
	      error: function() {
	    	  console.log("22222");
	      }
	  });
  })
  
  $("a[id^=orderNow]").click(function() {
	  $id= $(this).prev().val();
	  console.log($id);
	  var href = $("#hrefOrder").val();
	  href = href + "?book=" + $id
	  var hrefNewOrder = $("#hrefNewOrder").val();
	  hrefNewOrder = hrefNewOrder+ "?book=" +$id
	  console.log(href);
	  console.log(hrefNewOrder);
	  $.ajax({
		  url: href,
	      type: "GET",
	      success: function(data) {
	    	  console.log(data);
	    	  if (data == 0) 
	    		       $("#inUse" + $id).modal();
	    	  if (data == 2) 
	    		  $("#fail" + $id).modal();
	    	  if(data == 1) {
	    		  location.href = hrefNewOrder;
	    	  }
	    	  if (data == 3) {
	    		  $("#inOrder" +$id).modal();
	    	  }
	    	  console.log("11111");
	      },
	      error: function() {
	    	  console.log("22222");
	      }
	  });
  })
  
 	  $("a[id^=showAboutBook]").popover({
		   html: true,
	       placement: "right",
	       content: function() {
	    	                      $id = $(this).next().val();
	    	                      console.log($id);
	    	                      return $("#aboutBook" + $id).html();
	    	        }
	  });
 
  $(".display-item").rateBar({
	  defaultStarColor : '#777777',
      ratedStarColor : '#FFD700',
      onRate : function(rate) {
    	  $r = rate;
          $bookID = $("#bookID").val();
          $buID = $("#buID").val();
    	  console.log(rate);
          var href = $("#hrefrate").val();
          $.ajax({
        	 url: href + "/" +$r+ "/" +$bookID+"/"+$buID,
             type: "GET",
             success: function() {
            	           console.log("success");
            	            alert("Thank you for voting");
            	            location.reload();
                     },
        	 error: function() {
        		        console.log("error");
        		     }  	 
        	 
          });
      }
});
 
  $("#email").validate({
		highlight: function(element, errorClass) {
		$(element).fadeOut(function() {
				$(element).fadeIn();
			});
		}
	}); 
  
  $("#order").validate({
		highlight: function(element, errorClass) {
			$(element).fadeOut(function() {
				$(element).fadeIn();
			});
		}
	});
  
  $("#editIssue").validate({
		highlight: function(element, errorClass) {
			$(element).fadeOut(function() {
				$(element).fadeIn();
			});
		}
	});
  
  $("#order").submit(function(e){
	  var currDate = (new Date).getTime();
	  var orderDate = (new Date($("#orderDate").val())).getTime();
	  console.log(currDate);
	  console.log(orderDate);
	  
	  if(orderDate < currDate) {
		  e.preventDefault();
		  alert($("#dateExpired").text());
	  } 
  })
  
  
  $("#account").submit(function(e) {
	  $("#errorname").text("");
	  $("#errorsurname").text("");
	  $("#errorcellphone").text("");
	  
	  $.ajax({
		  url: $("#account").attr("action"),
		  data: $("#account").serialize(),
	      type: "POST",
	      dataType: "json",
	      contentType : 'application/x-www-form-urlencoded',
		  mimeType : 'application/json',
		   success: function(response) {
			   if(response.status == "SUCCESS") {
			     $("#success").show("slow");
			     setTimeout(function () {
			    	 $("#success").hide("slow");}, 2500);
			     }  
			   if(response.status == "FAIL") {
				   console.log("error");
				   for(var key in response.errorsMap) {
			    		  console.log(response.errorsMap[key]);
			    		  $("#error" + key).text(response.errorsMap[key]);
			    	}
			   }
		   },
		   error: function(response) {
			   console.log("error");
			   $("#errorDiv").show("slow");
		   }
	  })
	e.preventDefault();
  });
  
  $("#email").submit(function(e) {
	  console.log("211");
	  $.ajax({
		  url: $("#email").attr("action"),
	      data: $("#email").serialize(),
	      type: "POST",
	      dataType: "json",
	      contentType : 'application/x-www-form-urlencoded',
		  mimeType : 'application/json',
		  success: function(response){
			 console.log('1111'); 
			 if(response.status == "SUCCESS") {
				 console.log('SUCCESS');
				 $("#emailSuccess").show();
			 }
			 if(response.status == "FAIL") {
				 console.log("FAIL");
				   for(var key in response.errorsMap) {
			    		  console.log(response.errorsMap[key]);
			    		  $("#error" + key).text(response.errorsMap[key]);
			    		  }
			 }
		  },
	      error: function(response){
	    	  console.log("error");
	    	  $("#emailerror").show();
	      }
	  })
	  e.preventDefault();
  });
  
  $("#ord").submit(function(e) {
	  var orderDate = (new Date($("#orderDate").val())).getTime();
	  var maxOrderDate = orderDate + 14;
	  console.log(maxOrderDate);
	  if(orderDate>maxOrderDate){
	   console.log("211");}
	   $.ajax({
			  url: $("#ord").attr("action"),
		      data: $("#ord").serialize(),
		      type: "POST",
		      dataType: "json",
		      contentType : 'application/x-www-form-urlencoded',
			  mimeType : 'application/json',
			  success: function(data) {
			       console.log('sss');
			       e.preventDefault();
			       if(data.status == "FAIL"){
			    	 // var date = new Date(1394143200000);
			    	 // var day = date.getDay();
			    	 // var month = date.getMonth();
			    	  //var year = date.getFullYear();
			    	 // console.log(day +"/"+month+"/"+year);
			    	  console.log(data.result);
			       }
			  },
			  error: function(){
				  console.log("eeee");
				  e.preventDefault();
			  }  
			  })
	  e.preventDefault();  
  })
  
})

