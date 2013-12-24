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
	  $("#id").text($(".id"+id).text());
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
	}
 
/* Закрываем модальное окно: */
function close_popup(box) { 
  $(box).hide(); 
  $("#background").delay(100).hide(1); 
} 
 
$(document).ready(function() { 
  /* Позиционируем блочный элемент окна по центру страницы: */
  $.fn.centered_popup = function() { 
    this.css('position', 'absolute'); 
    this.css('top', ($(window).height() - this.height()) / 2 + $(window).scrollTop() + 'px'); 
    this.css('left', ($(window).width() - this.width()) / 2 + $(window).scrollLeft() + 'px'); 
  } 
  
  $("#deleteorder").click(function() {
	  var tr = $(this).parent().parent().prop("tagName");;
	  var id = $(tr + " td:first-child").text();
	  var name = $(tr + " td:nth-child(2)").text() + " " + $(tr + " td:nth-child(3)").text();
	  $("#background").show(); 
	  $("#popup").centered_popup(); 
	  $("#popup").delay(100).show(1); 
	  $("#name").text(name);
  })
 
 
});