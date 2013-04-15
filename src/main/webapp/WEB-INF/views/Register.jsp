<!DOCTYPE html>
<html>
<head>

<title>
Registration Form
</title>




<!-- CSS -->
<link href="Register.css" rel="stylesheet">
<link href="form.css" rel="stylesheet">

<script src="wufoo.js"></script>
  <script src="jquery.js"></script>

<script type="text/javascript" src="jquery.validate.js"></script>






 
  <script> 
  $(document).ready(function(){
  $("#RegistrationForm").validate();
  
  if (RegistrationForm.Cpassword.value != RegistrationForm.password.value){
	  alert("Confirm password doesn't match");
	  return false;
  }
  return true;

 });
  
  function validatePass(password, Cpassword) {
	    if (password.value != Cpassword.value || password.value == '' || Cpassword.value == '') {
	    	Cpassword.setCustomValidity('Password incorrect');
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
  var Username=document.forms["RegistrationForm"]["Username"].value;
  var Lname=document.forms["RegistrationForm"]["Lname"].value;
  var password=document.forms["RegistrationForm"]["password"].value;
  var Cpassword=document.forms["RegistrationForm"]["Cpassword"].value;
  var Address=document.forms["RegistrationForm"]["Address"].value;
  var City=document.forms["RegistrationForm"]["City"].value;
  var Country=document.forms["RegistrationForm"]["Country"].value;
  var email=document.forms["RegistrationForm"]["Email"].value;
  var atpos=email.indexOf("@");
  var dotpos=email.lastIndexOf(".");
  var Phone=document.forms["RegistrationForm"]["Phone"].value;
  var PhoneI=document.forms["RegistrationForm"]["PhoneI"].value;
  var PhoneII=document.forms["RegistrationForm"]["PhoneII"].value;
  
  
  

//Let me warn you:
//There are many many people with names less than 7 characters

if (Username==null || Username=="" || Username.length<7) {
 alert("Please provide Your full name");
 return false;
} // if returned so else is not required



if (password == null || password==""){
  // length = 0 or password is null here so:  
  // if(password.length<8)  is unnecessary 
  if(password == ""){  
         alert("Password must contain at least 8 charecters");
  } else
         alert("Please insert your password");
  return false;
}  // if returned so else is not required

if (Cpassword != Cassword){
  alert("Confirm password doesn't match");
  return false;
}  // if returned so else is not required

if (email==null || email==""){
  alert("Email must be filled out.");
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

<form class="RegistrationForm" id="RegistrationForm" method="Post" action="Register" name ="Register" onsubmit="return validate(this);" >


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
<input id="Username" name="Username" type="text"  size="8" tabindex="1" class="required && field text ln" minlength="2"/>
<label for="Username">First</label>
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
<input id="Address" name="Address" type="text" class="required && field text ln"  minlength="4" size="14" tabindex="2" />
<label for="Address">Address</label>
</span>
<span>
<input id="City" name="City" type="text" class="required && field text ln"  minlength="4" minlength="2" size="14" tabindex="2" />
<label for="City">City</label>
</span>

<span class="country">
<select id="Country" name="Country" class="field select addr" tabindex="8" >
<option value="" selected="selected"></option>

<option value="Egypt" >Egypt</option>


</select>
<label for="">Country</label>
</span>
</li>





<li id="foli9" class="notranslate      ">
<label class="desc" id="title9" for="Email">
Email
</label>
<div>
<input id="Email" name="Email" type="email"  class="required email && field text ln" spellcheck="false" class="field text medium" value="" maxlength="255" tabindex="9" /> 
</div>
</li>
<li id="foli10" class="phone notranslate      ">
<label class="desc" id="title10" for="Phone">
Mobile Number
</label>
<span>
<input id="Phone" name="Phone" type="tel" class="required number && field text ln"" minlength="4" size="4"  maxlength="4"  tabindex="10" />
<label for="Phone">####</label>
</span>
<span class="symbol">-</span>
<span>
<input id="PhoneI" name="PhoneI" type="tel" class="required number && field text ln"" minlength="3" size="3" maxlength="3" tabindex="11" />
<label for="PhoneI">###</label>
</span>
<span class="symbol">-</span>
<span>
 <input id="PhoneII" name="PhoneII" type="tel" class="required number && field text ln"" minlength="3" size="4" maxlength="4" tabindex="12" />
<label for="PhoneII">####</label>
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