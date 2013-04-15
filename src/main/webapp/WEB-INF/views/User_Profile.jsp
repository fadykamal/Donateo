<!doctype html>
 
<html >
<head>
  <meta charset="utf-8" />
  <title>${message}</title>
  <link href="Register.css" rel="stylesheet">
<link href="form.css" rel="stylesheet">
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css" />
 <!--  <link href='tabbedContent.css' rel='stylesheet' type='text/css' /> -->

  <%
  // This is a scriptlet.  Notice that the "date"
  // variable we declare here is available in the
  // embedded expression later on.

    java.util.Date date = new java.util.Date();
  java.util.Hashtable<String, Integer> All = new  java.util.Hashtable<String, Integer>();
  All.put("ok", 0);
  All.put("mai", 1);
  All.put("samar", 2);
  java.util.Enumeration keys =All.keys();
  
  Object result = "";
  while (keys.hasMoreElements()) {
      Object key = keys.nextElement();
    //  Object valueOfKey =All.get(key);
    //  if (value.equals(valueOfKey)) {
          result = key;
      //}
  }
%>
<c:if test="${not empty objects}">
    <table>
        <c:forEach var="o" items="${hashtable}">
            <tr>
                <td>${o}</td>
              
               
            </tr>
        </c:forEach>
    </table>
</c:if>
  
  <script>
  
  $(function() {
	    var tabs = $( "#tabs" ).tabs();
	    tabs.find( ".ui-tabs-nav" ).sortable({
	      axis: "x",
	      stop: function() {
	        tabs.tabs( "refresh" );
	      }
	    });
	  });
  
  
  $(function() {
	    $( "#tabs" ).tabs({
	      event: "mouseover"
	    });
	  });
  
  
  </script>
  
  <style>
  
  #Photo
  {
  
   margin-top: 28px; 
    margin-left: 0px; 
  }
  
   #tabs{
    margin-top: -204px; 
    width :  695px;
    height:560px;
   margin-left: 200px;
  }
   #header {  
 padding-top:20px;  
                       }  
                       
    #main {  
       background:#c8c0be url(images/background_light_slice.jpg) repeat-x; 

          }  
    #main .container {  
        background-image:url(images/background_light.jpg);  
        background-repeat:no-repeat;  
        min-height:620px;  
         min-width:100px: 
       
                     }


div.ex
{
width:170px;
height: 100px;

padding:10px;
border:5px solid gray;
margin:0px;
margin-top:-350px;
}                  
                       
  </style>
  
  
  
</head>
<body id="public">

<div id="container" class="ltr">

<h1 id="logo">
<a ></a>
</h1>



<header id="header" class="info">
<h2>${message}</h2>
<div></div>
</header>
  <div id="main">  
        <div class="container">  
         
<div id="header"> </div>  
              

 <div id="ProfilePage">
    <div id="LeftCol">
        <div id="Photo">
        <img class="photo" src="images/unknown2.png" style="width:200px;height:200px" id="profile-photo">
        </div>
        
    </div>

 

    <!-- Needed because other elements inside ProfilePage have floats -->
    <div style="clear:both"></div>
</div>
<div id="tabs">
  <ul>
    <li><a href="#tabs-1">Current project</a></li>
    <li><a href="#tabs-2">following</a></li>
    <li><a href="#tabs-3">finished</a></li>
  </ul>
  <div id="tabs-1">
<p>  </p>
  </div>
  <div id="tabs-2">
<p>   <%    while (keys.hasMoreElements()) {
    Object key = keys.nextElement();
    Object valueOfKey =All.get(key);
  //  if (value.equals(valueOfKey)) {
        result = key;
        
    //}
} %>

</p>
  </div>
  <div id="tabs-3">
<p> ${hashtable}</p>
  </div>
</div>

<div class="ex">
<li>
<span>
<label> Country:</label>
</span>
</li>
</br>

<li>
<span>
<label> City:</label>
</span>
</li>

</div>

 </div>
              </div>
 </div>
</body>
</html>