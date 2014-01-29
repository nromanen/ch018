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
                     },
        	 error: function() {
        		        console.log("error");
        		     }  	 
        	 
          });
      }
});
 
})

