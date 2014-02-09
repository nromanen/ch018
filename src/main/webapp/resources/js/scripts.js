function fill_form(box, id) {
	
	var cl = ".title" + id; 
	$("#id").css("display", "none");
	  $("#id").val($(".id"+id).text());
	  $("#title").val($(".title" + id).text());
	  $("#authors").val($(".authors" + id).text());
	  $("#year").val($(".year" + id).text());
	  $("#publication").val($(".publication" + id).text());
	  $("#pages").val($(".pages" + id).text());
	  $("#description").val($(".description" + id).text());
	  $("#term").val($(".term" + id).text());
	  $("#bookcase").val($(".bookcase" + id).text());
	  $("#shelf").val($(".shelf" + id).text());
	  $("#genre").val($(".genre" + id).text());
	  $("#description").val($(".desc"+id).text());
	  $("#term").val($(".term"+id).text());
	  $count = $(".count"+id).text().split('/');
	  $("#count").val($count[1]);
	  $("#available").val($count[0]);
	  $("#image").val($(".iimg" + id).text());
	  $("#rating").val($(".rating" + id).text());
	  $("#numberOfEvaluations").val($(".evals" + id).text());
	  
	  $("#uname").val($(".uname" + id).text());
	  $("#surname").val($(".surname" + id).text());
	  $("#email").val($(".email" + id).text());
	  $("#cellphone").val($(".cellphone" + id).text());
	  $("#multibookAllowed").val($(".multibookAllowed" + id).text());
	  $("#untimelyReturns").val($(".untimelyReturns" + id).text());
	  $("#timelyReturns").val($(".timelyReturns" + id).text());
	  $("#failedOrders").val($(".failedOrders" + id).text());
	  $("#confirm").prop('checked', $(".confirm" + id).prop('checked'));
	  $("#rating").text($(".rating" + id).text());
	  $("label[id^=error]").text("");
	  $("#errorperson").text("");
	}

function reset_form() {
	$("#id").css("display", "none");
	  $("#id").val(0);
	  $("#title").val("");
	  $("#authors").val("");
	  $("#year").val(0);
	  $("#publication").val("");
	  $("#pages").val(0);
	  $("#description").val("");
	  $("#term").val(14);
	  $("#bookcase").val(0);
	  $("#shelf").val(0);
	  $("#genre").val(1);
	  $("#description").val("");
	  $("#count").val(0);
	  $("#available").val(0);
	  $("#image").val("");
	  $("#rating").val(0);
	  $("#numberOfEvaluations").val(0);
	  
	  $("#uname").val("");
	  $("#surname").val("");
	  $("#email").val("");
	  $("#cellphone").val("");
	  $("#multibookAllowed").val(0);
	  $("#untimelyReturns").val(0);
	  $("#timelyReturns").val(0);
	  $("#failedOrders").val(0);
	  $("#confirm").prop('checked', false);
	  $("#rating").text("");
	  $("label[id^=error]").text("");
	  $("#errorperson").text("");
}

$(document).ready(function() { 
	$( ".hidden-nb" ).hide();
	$("#formlogin").validate({
		highlight: function(element, errorClass) {
			$(element).fadeOut(function() {
				$(element).fadeIn();
			});
		}
	});
	
	$("#registration").validate({
		highlight: function(element, errorClass) {
			$(element).fadeOut(function() {
				$(element).fadeIn();
			});
		}
	});
	
	$("#loginpopup").validate({
		highlight: function(element, errorClass) {
			$(element).fadeOut(function() {
				$(element).fadeIn();
			});
		}
	});
	
	$(".my-nav-header").click(function() {
		if ( $( ".hidden-nb" ).is( ":hidden" ) ) {
			$( ".hidden-nb" ).show( "slow" );
		} else {
			$( ".hidden-nb" ).slideUp();
		}
	});
	
	/**
	 * Highlight nav
	 */
	var url = window.location.href;
	// Will only work if string in href matches with location
	$('li a[href="'+ url +'"]').parent().addClass('active');
	// Will also work for relative and absolute hrefs
	$('li a').filter(function() {
	    return this.href == url;
	}).parent().addClass('active');
	

    $("#signtoggle").popover({
    	html:true,
    	placement: "bottom",
    	content: function() {
    	      return $('#popover_content_wrapper').html();
        }
    });
    
    $("#advtoggle").popover({
    	html:true,
    	placement: "bottom",
    	content: function() {
    	      return $('#popover_advanced_search').html();
        },
        title: function() {
			return $('#adv_search_title').text();
		}
    });

    $("#cellphone").mask("(999) 999-9999");
	
	//$("table").tablesorter();
	$(".alert .close").click(function() {
		$(".alert").hide();
	});
	
	$("[rel=tooltip]").tooltip({
		html : true, 
        content: function() {
          return $('#tooltip_content_wrapper').html();
        }
	});
	/**
	 * New book/user click
	 */
	$("#newbookbutton, #newuserbutton").click(function() {
		reset_form();
		$('#action_popup').modal();
		$('.myModalLabelNew').show();
		$(".myModalLabelEdit").hide();
	})
	
	/**
	 * Edit book/user click
	 */
	$("a[id^=editbook], a[id^=edituser]").click(function() {
		var tr = "#" + $(this).parent().parent().attr("id");
		var id = $(tr + " td:first-child").text();
		fill_form("#action_popup", id)
		$('#action_popup').modal();
		$(".myModalLabelEdit").show();
		$('.myModalLabelNew').hide();
	})
	
	/**
	 * Delete book/user click
	 */
	$("a[id^=deletebiu], a[id^=deleteorder], a[id^=deletebook], a[id^=deleteuser]").click(function() {
		var tr = "#" + $(this).parent().parent().attr("id");
		var id = $(tr + " td:first-child").text();
		$("#name").text(id);
		console.log(id);
		$('#popup').modal();
	})
	
	/**
	 * Return/issue click for books
	 */  
	$("a[id^=returnbook], a[id^=issueorder]").click(function() {
		var tr = "#" + $(this).parent().parent().attr("id");
		var id = $(tr + " td:first-child").text();
		var pid= $(".pid" + id).text(); //get person id from table
		var confirmed = $(tr + " .tdconfirmed").prop('checked'); 	
		$("#aname").text(id);
		$("#booknamemessage").text($("#bookname").text());
		$("#usernamemessage").text($(tr + " .tdname").text() + " " + $(tr + " .tdsurname").text());
		$("#bookplacemessage").text($(tr + " .tdbookcase").text() + "/" + $(tr + " .tdshelf").text());	
		$("#confimation").prop('checked', confirmed);		
		if ($("#confimation").prop('checked') == false) {
			$("#confirmuser").show();
		}
		$("#days").val($(tr + " .tddays").text());
		$('#action_popup').modal();
	})
	
	/**
	 * Return click for user
	 */  
	$("a[id^=returnuserbook]").click(function() {
		var tr = "#" + $(this).parent().parent().attr("id");
		var id = $(tr + " td:first-child").text();
		var pid= $(".pid" + id).text(); //get book id from table
		$("#aname").text(id);
		$("#booknamemessage").text($(tr + " .tdtitle").text() + " " + $(tr + " .tdauthors").text());
		$("#usernamemessage").text($("#username").text());
		$("#bookplacemessage").text($(tr + " .tdbookcase").text() + "/" + $(tr + " .tdshelf").text());
		$('#action_popup').modal();
	})
	
	$("a[id^=issueuserorder]").click(function() {
		var tr = "#" + $(this).parent().parent().attr("id");
		var id = $(tr + " td:first-child").text();
		
		var confirmed = $(tr + " .tdconfirmed").prop('checked'); 
		
		$("#aname").text(id);
		$("#booknamemessage").text($(tr + " .tdtitle").text() + " " + $(tr + " .tdauthors").text());
		$("#usernamemessage").text($("#username").text());
		$("#bookplacemessage").text($(tr + " .tdbookcase").text() + "/" + $(tr + " .tdshelf").text());	
		$("#confimation").prop('checked', confirmed);		
		if ($("#confimation").prop('checked') == false) {
			$("#confirmuser").show();
		}
		$("#days").val($(tr + " .tddays").text());
		$('#action_popup').modal();
	})

 
  
  /**
   * Delete book/user action
   */
  $("#deleteLink").click(function(event) {
	  var id = $("#name").text();
	  var href = $(event.target).attr("href")+id;
	  $.ajax({
		  url: href,
          type: "DELETE",
          success: function(data) {
        	  if (data == 0) {
        		  console.log("error delete");
        		  $(".alert").show();
        	  }
        	  else {
        		  console.log("success");
        		  $(".table" + id).remove();
        	  }
        	  $('#popup').modal("hide");
          },
          error: function(data) {
        	  console.log("error");
        	}
	  });
	  event.preventDefault();
  });
	  
  
  /**
   * Return book click
   */
	
  $("#actionLink").click(function(event) {
	  $('#progress').show();
	  var id = $("#aname").text();
	  var href = $(event.target).attr("href")+id;
      $.ajax({
    	  url: href,
    	  type: "GET",
    	  success: function(data) {
    		  console.log("success");
    		  $(".table" + id).remove();
    		  $('#action_popup').modal("hide");
    		  $('#progress').css("display", "none");
    	  },
    	  error: function(data) {
    		  console.log("error");
		  }
      });
      event.preventDefault();
  });
  
  $("#issueLink").click(function(event) {
	  if ($("#confimation").prop('checked') == false) {
		  return false;
	  }
	  var id = $("#aname").text();
	  var days = $("#days").val();
	  var href = $(event.target).attr("href")+id+"/"+days;
      $.ajax({
    	  url: href,
    	  type: "GET",
    	  success: function(data) {
    		  console.log("success");
    		  $(".table" + id).remove();
    		  $('#action_popup').modal("hide");
    	  },
    	  error: function(data) {
    		  console.log("error");
		  }
      });
      event.preventDefault();
  });  
  
	
});