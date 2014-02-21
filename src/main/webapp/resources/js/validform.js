$(document).ready(function() {
	
	$("#formlogin").validate({
		submitHandler: function(form) {
			form.submit();
		}
	});
	
	$("#registration").validate({
		submitHandler: function(form) {
			form.submit();
		}
	});
	
	$("#loginpopup").validate({
		submitHandler: function(form) {
			form.submit();
		}
	});

});
		

		
	function isValidEmailAddress(emailAddress) {
		// TODO: check if '@', non-whitespace symbols before and after it is sufficient - there could be cyryllic domains, for example
		var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
		return pattern.test(emailAddress);
    }
	
	function isValidPhoneNumber(phoneNumber) {
		var pattern = new RegExp(/\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/);
		return pattern.test(phoneNumber);
	}