function checkValidEmailAddress(emailAddress) {
    var pattern = new RegExp(/^(("[\w-+\s]+")|([\w-+]+(?:\.[\w-+]+)*)|("[\w-+\s]+")([\w-+]+(?:\.[\w-+]+)*))(@((?:[\w-+]+\.)*\w[\w-+]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][\d]\.|1[\d]{2}\.|[\d]{1,2}\.))((25[0-5]|2[0-4][\d]|1[\d]{2}|[\d]{1,2})\.){2}(25[0-5]|2[0-4][\d]|1[\d]{2}|[\d]{1,2})\]?$)/i);
    
    return pattern.test(emailAddress);
};

$(document).ready(function() {
	// hide the send button on default
	$("#sendbtn").css("opacity", 0);
	
	$(".txtbar, .txtbox").live("focus", function(){
		var thelabel = $(this).prev();
		var infobox = $(this).next();
		var rowbox = $(this).parent();
		var currid = $(this).attr('id');
		var pxlchange = '-45px';
		
		if(currid == "subject") { pxlchange = '-90px'; }
		if(currid == "message") { pxlchange = '-60px'; }
		
		rowbox.addClass('colors');
  		
		thelabel.animate({
			left: pxlchange
			}, 350, 'linear', function(){
			// animation complete
		});
		
		infobox.animate({
			opacity: 1.0
		}, 350, 'linear', function(){
			// animation complete
		});
		
		$(this).live("keyup", function(){
			var theval = $(this).val();
			var limitval = 3;
			var replacehtml = "";
			var nameinfohtml = "Enter your full name, at least 3 letters required.";
			var emailinfohtml = "Enter a valid e-mail address. We promise not to share this with <strong>anyone</strong>!";
			var subjectinfohtml = "Enter an e-mail subject if you'd like.";
			var messageinfohtml = "Lay into that keyboard(at least 10 chars) and let us know what's on your mind.";
			
			if(currid == "name") {
				replacehtml = nameinfohtml;
			} else if(currid == "email") {
				replacehtml = emailinfohtml;
			} else if(currid == "subject") {
				replacehtml = subjectinfohtml;
				limitval = 1;
			} else if(currid == "message") {
				replacehtml = messageinfohtml;
				limitval = 10;
			}
			
			// checking against e-mail regex
			if(currid == "email"){
				if(checkValidEmailAddress(theval)) {
					infobox.html("Looks good!");
					infobox.addClass('good');
				}
				else if(!checkValidEmailAddress(theval)) {
					infobox.html(replacehtml);
					infobox.removeClass('good');
				}
			} 
			else {
				// we use this logic to check for name+message fields
				if(theval.length >= limitval) {
					infobox.html("Looks good!");
					infobox.addClass('good');
				} else if(theval.length < limitval) {
					infobox.html(replacehtml);
					infobox.removeClass('good');
				}
			}
			
			
			// now we check if we can display the send button
			// much easier to just look for 'good class on the req fields
			if( $('#name').next().hasClass('good') 
			 && $('#email').next().hasClass('good')
			 && $('#message').next().hasClass('good') ) {
				// if all 3 boxes are good then we display our send button!
				// jquery validation complete
				$('#sendbtn').animate({
					opacity: 1.0
				}, 200, 'linear', function(){
					// display submitbtn animation complete
				});
			}
		});
	});
	
	$(".txtbar, .txtbox").live("blur", function(){
		var thelabel = $(this).prev();
		var infobox = $(this).next();
		var rowbox = $(this).parent();
		var currid = $(this).attr('id');
		
		rowbox.removeClass('colors');
		
		infobox.animate({
			opacity: 0
		}, 400, 'linear', function(){
			// animation complete
		});
	});
});
