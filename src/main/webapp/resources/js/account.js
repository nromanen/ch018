function validateEmail(email) {
        // http://stackoverflow.com/a/46181/11236

        var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }
    
    function validate() {
        $("#result").text("");
        var email = $("#email1").val();
        if (validateEmail(email)) {
            $("#result").text("this email is valid");
            $("#result").css("color", "green");
        } else {
            $("#result").text("this email is not valid");
            $("#result").css("color", "red");
        }
        return false;
    }
$(document).ready(function() { 

  $('.datetimepicker').datetimepicker();
  console.log("kkkk");  
  //$("#email1").change(validate());
  
  $("input[id^=editIssueDate]").click(function(){
	  $id = $(this).next().val();
	  $("#oldIssue" + $id).hide();
	  $("#saveNewIssue" + $id).show();
	  $("#cancelIssueEdit" + $id).show();
	  $("#newIssue" + $id).val($("#oldIssue" + $id).text());
	  $("#newIssue" + $id).show();
  
  })
  
  $("input[id^=cancelIssueEdit]").click(function(){
	  $id1 = $(this).next().val();
	  $("#oldIssue" + $id1).show();
	  $("#saveNewIssue" + $id1).hide();
	  $("#cancelIssueEdit" + $id1).hide();
	  $("#newIssue" + $id1).hide();
  })
  
  
  $("input[id=butt1]").click(function(){
	  $("#modal").modal();
	  
  })
  
  $("input[id^=deleteWish]").click(function(){
	  $id = $(this).next().val();
	  console.log($id);
	  $wishID = $("#wishID" + $id).val();
	  //console.log(wishID);
	  var href = $("#hrefrate").val();
	  console.log(href);
	  $.ajax({
		  url: href + "/" + $id,
		  type: "GET",
		  success: function() {
			  console.log("success");
			  $("#modal" + $id).modal();
			  //$("modal" + $id).delay(5000);
			  setTimeout(function () {location.reload();}, 1500);
			  
		  },
		  error: function() {
			  console.log("error");
		  }
	  });
  })
  
  $("input[id^=addToWish]").click(function(){
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
  
  $("input[id^=orderNow]").click(function() {
	  $id= $(this).next().val();
	  console.log($id);
	  var href = $("#hrefOrder").val();
	  href = href + "?book=" + $id// + "&wish=0";
	  var hrefNewOrder = $("#hrefNewOrder").val();
	  hrefNewOrder = hrefNewOrder+ "?book=" +$id//+ "&wish=0";
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
 
  $("#account").validate({
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
  
  $("#order").submit(function(e){
	  var currDate = (new Date).getTime();
	  var orderDate = (new Date($("#orderDate").val())).getTime();
	  console.log(currDate);
	  console.log(orderDate);
	  
	  if(orderDate < currDate) {
		  e.preventDefault();
		  alert($("#dateExpired").text());
	  } 
	 // e.preventDefault();
  })
  
  

})

