<!DOCTYPE html>
<html>
<head>

<title>
Login
</title>




<!-- CSS -->
<link href="Register.css" rel="stylesheet">
<link href="form.css" rel="stylesheet">

<script src="wufoo.js"></script>
  <script src="jquery.js"></script>

<script type="text/javascript" src="jquery.validate.js"></script>






 
  <script> 
  $(document).ready(function(){
  $("#commentForm").validate();
 
 });



  
  
  
  </script>




</head>

<body id="public">
<div id="container" class="ltr">

<h1 id="logo">
<a ></a>
</h1>

<form class="commentForm" id="commentForm" method="Post" action="Login" name ="Login">

<header id="header" class="info">
<h2>Login</h2>
<div></div>
</header>

<ul>

<li>
<label class="desc" id="title1" for="Field1">
Name
</label>
<span>
<input id="Username" name="Username" type="text"  size="8" tabindex="1" class=" field text ln" minlength="2"/>
<label for="Username">Username</label>
</span>
</li>

<li>
<label class="desc" id="title2" for="Field2">
OR
</label>

</span>
</li>
<li>
<label class="desc" id="title1" for="Field1">
Email
</label>
<span>
<input id="email" name="email" type="email" class=" field text ln"  tabindex="2" />
<label for="email">Email</label>
</span>
</li>



<li>
<label class="desc" id="title1" for="Field1">
Password
</label>
<span>
<input id="password" name="password" type="password" class="required && field text ln"  tabindex="2" />
<label for="password">Password</label>
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