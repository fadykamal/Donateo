<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>

<head>
<title>New Project</title>



<!--  <meta charset="utf-8" />
  <title>jQuery UI Datepicker - Select a Date Range</title>
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css" />
  
  
Combo-handled YUI CSS files: 
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.9.0/build/reset-fonts-grids/reset-fonts-grids.css&2.9.0/build/resize/assets/skins/sam/resize.css&2.9.0/build/layout/assets/skins/sam/layout.css"> 
Combo-handled YUI JS files: 
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.9.0/build/yahoo-dom-event/yahoo-dom-event.js&2.9.0/build/animation/animation-min.js&2.9.0/build/dragdrop/dragdrop-min.js&2.9.0/build/element/element-min.js&2.9.0/build/resize/resize-min.js&2.9.0/build/layout/layout-min.js"></script> 
<link rel="stylesheet" type="text/css"href="http://yui.yahooapis.com/2.9.0/build/reset-fonts-grids/reset-fonts-grids.css">
  
  <script src="layout.js"></script>
  <script src="jquery.js"></script>
<script type="text/javascript" src="jquery.validate.js"></script>

  
  <script>
  
  
  

  $(document).ready(function(){
  $("#newProject").validate({  errorClass:"errors",  
		   rules: {  
			     FirstName: {required: true}  
			   }
			 });  
			}); 

  $(function() {
	  $("#category").click();
    $( "#from" ).datepicker({
      defaultDate: "+1w",
      changeMonth: true,
      numberOfMonths: 3,
      onClose: function( selectedDate ) {
        $( "#to" ).datepicker( "option", "minDate", selectedDate );
      }
    });
    $( "#to" ).datepicker({
      defaultDate: "+1w",
      changeMonth: true,
      numberOfMonths: 3,
      onClose: function( selectedDate ) {
        $( "#from" ).datepicker( "option", "maxDate", selectedDate );
      }
    });
  });
  </script>
  
  <script type="application/javascript">
  
  function date(){
	
	  document.getElementById("fromto").style.visibility = "visible";
	  document.getElementById("date").style.visibility = "hidden";

  }
  function obj(){
	  document.getElementById("objectdetails").style.visibility = "visible";
	  
  }
  function funded(){
	  document.getElementById("funded").style.visibility = "visible";
	  
  }
  
  function viewDiv()
  {
	  if( typeof viewDiv.counter == 'undefined' ) { 
	  	viewDiv.counter = 2;
	  }
	  else {
		  if (viewDiv.counter==6)
		  {viewDiv.counter=2;
		  alert("You can add only 5 objects");}
		 
	  }
  document.getElementById("obj"+viewDiv.counter.toString()).style.visibility = "visible";
  viewDiv.counter++;
  }
  
  
  </script>
 -->  
 
  
  <style>
 
div.categ {margin:-0.5cm 4cm 0cm 4cm}
div.textarea{margin:-0.5cm 4cm 0cm 4.5cm}
div.type{margin:-0.5cm 4cm 0cm 3cm}
div.check{margin:-0.5cm 4cm 0cm 3cm}
div.create{margin:-0.5cm 8cm 0cm 15cm}


  #inside
  { margin-left:0px;
  width:800px;
  text-align:left;
    
  }
  
   .errors {color:#900;
  padding-left: .5em;
   vertical-align: top};  
   
    </style>

</head>

<body > 

<div id="top">
<h5>Add New Project</h5>

</div>
<div id="left" style="visibility:hidden">
</div>
<div id="right" style="visibility:hidden">
</div>
<div id="bottom" style="visibility:hidden">
</div>

<div id="center">
<div id="inside" class ='ltr'>

<form class="newProject" id="newProject" method="Post" action="newProject" name ="newProject">



<h3>Project Title: <input type="text" name="name" id= "name" class="required"><br> </h3>

</br>

<h3>Project Description:<div class="textarea"><textarea rows="5" cols="30" name="description" class="required"></textarea></div></h3>
</br> 
<h3>Project Location: <input type="text" name="location" id= "location" class="required"><br> </h3>
 </br> 
 

<h3>Project Type: <div class="required && type"><input type="radio" name="type" value="charity"  >Charitable project<br>
									<input type="radio" name="type" value="campaign" onclick= "date();" >Campaign
                      </div>
</h3> 
</br> 
</br> 

<div class ="required && check" >
						<input type="checkbox" name="isDonation" value="isDonation" id="isDonation" onclick="funded();" >Financial Donation<br>
						<input type="checkbox" name="isObject" value="isObject"  id ="isObject"onclick ="obj();" >Object Donation<br>
                    	<input type="checkbox" name="isVolunteer" value="isVolunteer" id="isVolunteer" >Volunteer

</div>
</br> 

<h3>Project Category: <div class="required && categ" ><input type="checkbox" name="health" value="health" id="health"  >Health<br>
										  <input type="checkbox" name="education" value="education" id="education" >Educatoin<br>
					                      <input type="checkbox" name="economy" value="economy" id="economy" >Economy<br>
					                      <input type="checkbox" name="orphans" value="orphans" id="orphans">Orphans<br>
					                      <input type="checkbox" name="development" value="development" id="development" >Development<br>
					                      <input type="checkbox" name="slums" value="slums" id ="Slums" ">Slums
                      </div>
</h3> 
</br> 

<h3>Project Severity: <input type="radio" name="severity" value="1" >1
<input type="radio" name="severity" value="2" >2
</h3>
</br> 



<div id="date">
<h3>Needed Before: <input type="date" name="deadline" id="deadline" class="required"></h3>
</div>

<div id ="fromto" style="visibility: hidden">
<label for="from">From</label>
<input type="text" id="from" name="from" class="required" />
<label for="to">to</label>
<input type="text" id="to" name="to" class="required"/>
  </div>
  </br>
  
<div id="funded" style="visibility:hidden">
<h3>Required Fund: <input type="text" name="fund" id= "fund" class="required && number"> L.E. <br> </h3>
</div>
</br> 


<div id ="objectdetails" style="visibility: hidden">
<div id="obj1">
<h3>Object Needed: <input type="text" name="object_name1" id= "object_name1" class="required"> Amount Needed:<input type="text" name="object_amount1" id= "object_amount1" class="required"></h3>
</div>
</br>
<div id="obj2" style="visibility: hidden">
<h3>Object Needed: <input type="text" name="object_name2" id= "object_name2" class="required"> Amount Needed:<input type="text" name="object_amount2" id= "object_amount2" class="required"></h3>
</div>
</br>
<div id="obj3" style="visibility: hidden">
<h3>Object Needed: <input type="text" name="object_name3" id= "object_name3" class="required"> Amount Needed:<input type="text" name="object_amount3" id= "object_amount3" class="required"></h3>
</div>
</br>
<div id="obj4" style="visibility: hidden">
<h3>Object Needed: <input type="text" name="object_name4" id= "object_name4" class="required"> Amount Needed:<input type="text" name="object_amount4" id= "object_amount4" class="required"></h3>
</div>
</br>
<div id="obj5" style="visibility: hidden">
<h3>Object Needed: <input type="text" name="object_name5" id= "object_name5" class="required"> Amount Needed:<input type="text" name="object_amount5" id= "object_amount5" class="required"></h3>
</div>
</br>
<input type="button" value="Add another object" onclick="viewDiv();"> 
</div>



<div class = "create"> 
<button  type="submit" style="height: 50px; width: 125px" name ="submit"  >Create Project</button>
</div>

</form>
</div>
</div>
</body>
</html>