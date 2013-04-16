<!DOCTYPE html>
<html>
<head>

<title>
Registration Form
</title>




<!-- CSS -->
<link href="Register.css" rel="stylesheet">
<link href="form.css" rel="stylesheet">


  <script src="jquery.js"></script>
   <script src="layout.js"></script>

<script type="text/javascript" src="jquery.validate.js"></script>






 
  <script> 
  $(document).ready(function(){
  $("#Register").validate();
  
  if (Register.Cpassword.value != Register.password.value){
	  alert("Confirm password doesn't match");
	  return false;
  }
  return true;

 });
  
  function validatePass(password, Cpassword) {
	    if (password.value != Cpassword.value || password.value == '' || Cpassword.value == '') {
	    	Cpassword.setCustomValidity('password incorrect');
	    } else {
	    	Cpassword.setCustomValidity('');
	    }
	}
  function validate(form) {
	  var e = form.elements;

	  /* Your validation code. */

	  if(e['password'].value != e['Cpassword'].value) {
	    alert('Your passwords do not match. Please type more carefully.');
	    return false;
	  }
	  return true;
	}

  /*function valide()
  {
  var name=document.forms["Register"]["name"].value;
  var Lname=document.forms["Register"]["Lname"].value;
  var password=document.forms["Register"]["password"].value;
  var Cpassword=document.forms["Register"]["Cpassword"].value;
  var address=document.forms["Register"]["address"].value;
  var City=document.forms["Register"]["City"].value;
  var Country=document.forms["Register"]["Country"].value;
  var email=document.forms["Register"]["email"].value;
  var atpos=email.indexOf("@");
  var dotpos=email.lastIndexOf(".");
  var phone=document.forms["Register"]["phone"].value;
  var phoneI=document.forms["Register"]["phoneI"].value;
  var phoneII=document.forms["Register"]["phoneII"].value;
  
  
  

//Let me warn you:
//There are many many people with names less than 7 characters

if (name==null || name=="" || name.length<7) {
 alert("Please provide Your full name");
 return false;
} // if returned so else is not required



if (password == null || password==""){
  // length = 0 or password is null here so:  
  // if(password.length<8)  is unnecessary 
  if(password == ""){  
         alert("password must contain at least 8 charecters");
  } else
         alert("Please insert your password");
  return false;
}  // if returned so else is not required

if (Cpassword != Cassword){
  alert("Confirm password doesn't match");
  return false;
}  // if returned so else is not required

if (email==null || email==""){
  alert("email must be filled out.");
  return false;
} // if returned so else is not required

if (atpos <1 || (dotpos <atpos+2) || (dotpos + 2 >= email.length)){
  alert("Not a valid e-mail address");
  return false;
} // if returned no else needed.

//and now this function should return:
return(true); 
}

*/
  
  
  
  </script>


<h>RegistrationForm</h>

</head>

<body id="public">
<div id="container" class="ltr">

<h1 id="logo">
<a ></a>
</h1>

<form class="Register" id="Register" method="Post" action="Register" name ="Register" onsubmit="return validate(this);" >


<header id="header" class="info">
<h2>Register</h2>
<div></div>
</header>

<ul>

<li>
<label class="desc" id="title1" for="Field1">
Name
</label>
<span>
<input id="name" name="name" type="text"  size="8" tabindex="1" class="required && field text ln" minlength="2"/>
<label for="name">Username</label>
</span>

</li>
<li>
<label class="desc" id="title1" for="Field1">
Password
</label>
<span>
<input id="password" name="password" type="password" class="required && field text ln"  minlength="4" size="14" tabindex="2" />
<label for="password">Password</label>
</span>
<span>
<input id="Cpassword" name="Cpassword" type="password" class="required && field text ln"  minlength="4" minlength="2" size="14" tabindex="2" onfocus="validatePass(document.getElementById('password'), this);"
																																			oninput="validatePass(document.getElementById('password'), this);" />
<label for="Cpassword">Confirm Password</label>
</span>
</li>

<li>
<label class="desc" id="title1" for="Field1">
Address
</label>
<span>
<input id="address" name="address" type="text" class="required && field text ln"  minlength="4" size="14" tabindex="2" />
<label for="address">Address</label>
</span>

</li>





<li id="foli9" class="notranslate      ">
<label class="desc" id="title9" for="email">
Email
</label>
<div>
<input id="email" name="email" type="email"  class="required email && field text ln" spellcheck="false" class="field text medium" value="" maxlength="255" tabindex="9" /> 
</div>
</li>
<li id="foli10" class="phone notranslate      ">
<label class="desc" id="title10" for="phone">
Mobile Number
</label>
<span>
<input id="phone" name="phone" type="tel" class="required number && field text ln"" minlength="4" size="4"  maxlength="4"  tabindex="10" />
<label for="phone">####</label>
</span>
<span class="symbol">-</span>
<span>
<input id="phoneI" name="phoneI" type="tel" class="required number && field text ln"" minlength="3" size="3" maxlength="3" tabindex="11" />
<label for="phoneI">###</label>
</span>
<span class="symbol">-</span>
<span>
 <input id="phoneII" name="phoneII" type="tel" class="required number && field text ln"" minlength="3" size="4" maxlength="4" tabindex="12" />
<label for="phoneII">####</label>
</span>
</li>
<li id="foli11" class="notranslate  threeColumns     ">

</li>
 <li class="buttons ">
<div>

 <input id="submit" name="submit" class="btTxt submit"  type="submit" value="submit"/>
    </div>
</li>

</ul>
</form> 

</div>


</body>
</html>