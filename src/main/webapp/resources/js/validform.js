$(document).ready(function() {
	console.log("start");
	var field = new Array("inputEmailReg", "inputPasswordReg", "confirmPasswordReg"); //required fields 
	console.log("start");
		
				
		$("#registration").submit(function(e) {
			console.log("submit");
			var error=0; // errors
			$("form #register").find(":input").each(function() {// every input
				for(var i=0;i<field.length;i++){ // if field is required
					if($(this).attr("id")==field[i]){ 
						
						if(!$(this).val()){// if field is empty
							$(this).css('border', 'red 1px solid');// red border
							error=1;// error
														
						}
						else{
							$(this).css('border', 'gray 1px solid');// else gray border
						}
						
					}						
				}				
		   })
		   
		   
		   //check email
		   
			var email = $("#inputEmailReg").val();
		   	if(!isValidEmailAddress(email)){
				error=2;
				$("#inputEmailReg").css('border', 'red 1px solid');// if not valid - red border
			}
		   	else{
				$("#inputEmailReg").css('border', 'gray 1px solid');// else gray border
			}
			
			//password
			var pas1 = $("#inputPasswordReg").val();
			var pas2 = $("#confirmPasswordReg").val();
		   	if(pas1!=pas2){
				error=3;
				$("#inputPasswordReg").css('border', 'red 1px solid');// red border
				$("#confirmPasswordReg").css('border', 'red 1px solid');// red
			}
			
			if(error==0){ // if no errors all good :)
				console.log("sdsdsdsdsdsdsd");
				return true;
			}
			else{
				console.log("sssssssssssssss");
			var err_text = "";
			if(error==1)  err_text="Не все обязательные поля заполнены!";
			if(error==2)  err_text="Введен не корректный e-mail!";
			if(error==3)  err_text="Пароли не совпадают";
			
			$("#messenger").html(err_text);	
			$("#messenger").fadeIn("slow");	
			return false; //
			}


	});

	function isValidEmailAddress(emailAddress) {
		var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
		return pattern.test(emailAddress);
    }
});