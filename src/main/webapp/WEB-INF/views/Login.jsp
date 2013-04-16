<!DOCTYPE html>
<html>
<head>

<title>
Login
</title>


<meta charset="utf-8" />
<title>jQuery UI Tabs - Default functionality</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />
<!-- Combo-handled YUI CSS files: --> 
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.9.0/build/reset-fonts-grids/reset-fonts-grids.css&2.9.0/build/resize/assets/skins/sam/resize.css&2.9.0/build/layout/assets/skins/sam/layout.css"> 
<!-- Combo-handled YUI JS files: --> 
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.9.0/build/yahoo-dom-event/yahoo-dom-event.js&2.9.0/build/animation/animation-min.js&2.9.0/build/dragdrop/dragdrop-min.js&2.9.0/build/element/element-min.js&2.9.0/build/resize/resize-min.js&2.9.0/build/layout/layout-min.js"></script> 
<link rel="stylesheet" type="text/css"href="http://yui.yahooapis.com/2.9.0/build/reset-fonts-grids/reset-fonts-grids.css">


<!-- CSS -->
<link href="Register.css" rel="stylesheet">
<link href="form.css" rel="stylesheet">

<script src="wufoo.js"></script>
  <script src="jquery.js"></script>
  <script src="layout.js"></script>

<script type="text/javascript" src="jquery.validate.js"></script>




<style>
#top
{

('visibility','hidden')

}


</style>

 
  <script> 
  $(document).ready(function(){
  $("#Login").validate();
 
 });
  
  $("#top").css("visibility", "hidden");
  $("#top").hide();
  
  $('div').remove('.top');
  
  $(function() {
	  $("#top").hide();
	});
  
  
  
  </script>




</head>

<body id="public" class="yui-skin-sam">
<div id ="center">
<div id="container" class="ltr">

<h1 id="logo">
<a ></a>
</h1>

<form class="Login" id="Login" method="Post" action="Login" name ="Login">

<header id="header" class="info">
<h2>Login</h2>
<div></div>
</header>

<ul>


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
</div>
<div id ="top" >
<label>welcome hokhlkjgffme</label>
</div>
<div id='left'  collapse= 'true'>

</div>



</body>
</html>