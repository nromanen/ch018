$(document).ready(function() { 
/*
$("#refreshLink").click(function(event){
     var href="";
     $.ajax({
           url: href,
           type: "POST",
           success: function(){
               $(".alert").show();
           },
           error: function(){
               $(".alert").show();
           }
    });
    event.preventDefault();
  )};  */
  $('.datetimepicker').datetimepicker();
  console.log("kkkk");
  
    function validateEmail(email) {
        // http://stackoverflow.com/a/46181/11236

        var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }

    function validate() {
        $("#result").text("");
        var email = $("#email").val();
        if (validateEmail(email)) {
            $("#result").text("this email is valid :)");
            $("#result").css("color", "green");
        } else {
            $("#result").text("this email is not valid :(");
            $("#result").css("color", "red");
        }
        return false;
    }
    
  $("#email").change(validate());
})

