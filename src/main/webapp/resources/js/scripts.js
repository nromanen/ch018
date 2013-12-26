/* Открываем модальное окно: */
function open_popup(box) { 
  $("#background").show() 
  $(box).centered_popup(); 
  $(box).delay(100).show(1); 
} 

function open_andfill(box, id) {
	var cl = ".title" + id;
	  $("#background").show(); 
	  $(box).centered_popup(); 
	  $(box).delay(100).show(1); 
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
	}
 
/* Закрываем модальное окно: */
function close_popup(box) { 
  $(box).hide(); 
  $("#background").delay(100).hide(1); 
} 

function delete_order(id) { 
	var text = "Remove order: ";
	var tr = "#" + $(this).parent().parent().attr("id");
	 var id = $(tr + " td:first-child").text();
	  var name = $(tr + " td:nth-child(2)").text() + " " + $("#" + tr + " td:nth-child(3)").text();
	  $("#background").show(); 
	  $("#popup").centered_popup(); 
	  $("#popup").delay(100).show(1); 
	  $("#name").text(name);
	} 
 
$(document).ready(function() { 
	$("table").tablesorter();
	
  $.fn.centered_popup = function() { 
    this.css('position', 'absolute'); 
    this.css('top', ($(window).height() - this.height()) / 2 + $(window).scrollTop() + 'px'); 
    this.css('left', ($(window).width() - this.width()) / 2 + $(window).scrollLeft() + 'px'); 
  } 
  

  
  $("#newbookbutton").click(function() {
	  
	  $("#background").show() 
	  $("#popup").centered_popup(); 
	  $("#popup").delay(100).show(1);
  })
  
  $("a[id^=cancel], input[id^=cancel]").click(function() {
	  $("#popup").hide(); 
	  $("#action_popup").hide(); 
	  $("#background").delay(100).hide(1); 
  })
  
  /**
   * Books scripts
   */
  $("a[id^=editbook]").click(function() {
	  var tr = "#" + $(this).parent().parent().attr("id");
	  var id = $(tr + " td:first-child").text();
	  //var name = $(tr + " td:nth-child(2)").text() + " " + $("#" + tr + " td:nth-child(3)").text(); 
	  open_andfill("#action_popup", id)
  })
  
  $("a[id^=deletebook]").click(function() {
	  var tr = "#" + $(this).parent().parent().attr("id");
	  var id = $(tr + " td:first-child").text();
	  //var name = $(tr + " td:nth-child(2)").text() + " " + $("#" + tr + " td:nth-child(3)").text(); 
	  $("#name").text(id);
	  $("#idtoremove").text(id);
	  console.log(id);
	  open_popup("#popup");
  })
  
  
  /**
   * Orders scripts
   */
  
  $("a[id^=deleteorder]").click(function() {
	  var tr = "#" + $(this).parent().parent().attr("id");
	  var id = $(tr + " td:first-child").text();
	  //var name = $(tr + " td:nth-child(2)").text() + " " + $("#" + tr + " td:nth-child(3)").text(); 
	  $("#name").text(id);
	  $("#idtoremove").text(id);
	  console.log(id);
	  open_popup("#popup");
  })
  
  /**
   * Return click
   */
  $("a[id^=issueorder]").click(function() {
	  var tr = "#" + $(this).parent().parent().attr("id");
	  var id = $(tr + " td:first-child").text();
	  //var name = $(tr + " td:nth-child(2)").text() + " " + $("#" + tr + " td:nth-child(3)").text(); 
	  $("#name").text(id);
	  console.log(id);
	  open_popup("#action_popup");
  })
  
  /**
   * Books in use scripts
   */
  
    $("a[id^=deletebiu]").click(function() {
	  var tr = "#" + $(this).parent().parent().attr("id");
	  var id = $(tr + " td:first-child").text();
	  //var name = $(tr + " td:nth-child(2)").text() + " " + $("#" + tr + " td:nth-child(3)").text(); 
	  $("#name").text(id);
	  console.log(id);
	  open_popup("#popup");
  })
  
  $("a[id^=returnbook]").click(function() {
	  var tr = "#" + $(this).parent().parent().attr("id");
	  var id = $(tr + " td:first-child").text();
	  //var name = $(tr + " td:nth-child(2)").text() + " " + $("#" + tr + " td:nth-child(3)").text(); 
	  $("#name").text(id);
	  console.log(id);
	  open_popup("#action_popup");
  })

 
  
  //Delete 
  $("#deleteLink").click(function(event) {
	  var id = $("#name").text();
	  var href = $(event.target).attr("href")+id;
          $.ajax({
        	  
                  url: href,
                    type: "DELETE",
                    
                    beforeSend: function(xhr) {
                            xhr.setRequestHeader("Accept", "application/json");
                            xhr.setRequestHeader("Content-Type", "application/json");
                    },
                    
                    success: function(data) {
                    	
                    	console.log("sdsdsdsdsdsdsd");
					},
					
					error: function(data) {
                    	
                    	console.log("sdsdsdsdsdsdsd");
					}
          });

          event.preventDefault();
  });
  
  //Action
  $("#actionLink").click(function(event) {
	  var id = $("#name").text();
	  var href = $(event.target).attr("href")+id;
          $.ajax({
        	  
                  url: href,
                    type: "GET",
                    
                    beforeSend: function(xhr) {
                            xhr.setRequestHeader("Accept", "application/json");
                            xhr.setRequestHeader("Content-Type", "application/json");
                    },
                    
                    success: function(data) {
                    	
                    	console.log("sdsdsdsdsdsdsd");
					}
          });

          event.preventDefault();
  });
  

  
  
 
});