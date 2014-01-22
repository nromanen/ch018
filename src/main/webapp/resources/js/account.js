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
})

