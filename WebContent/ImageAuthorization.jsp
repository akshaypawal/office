<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Identification</title>
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<script src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>

		
<link rel="stylesheet" type="text/css" href="file.css" />
<script src="https://code.jquery.com/jquery-1.11.0.js"></script>

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script type="text/javascript" src="js/bootstrap.min.js">


$(document).ready(function()
		{
			 $("#upload").click(function(){
				
				 alert("kkkk");
					
					
				});				
		}); 

</script>

</head>
<body>
<br>
<div>
<img src="img2.jpg" width="350" height="263" alt="error" align="middle" id="dbimg">
</div>

<br>

<div>
<img src="img3.jpg" width="350" height="263" alt="error" align="middle" id="cameraimg">
</div>
<!-- <button type="button" onclick="chooseFile();">choose</button> -->

<form action="uploadservlet" method="post" enctype="multipart/form-data">

<br />
    <div class="col-xs-16 col-md-16 text-center" >
          <input type="file" name="browse" class="btn btn-primary text-center" >
          </div>
         
     <div class="row">
          <div class="col-xs-2 col-md-2 text-center" >
            <input type="submit" class="btn btn-primary" value="upload" id="upload" name="upload">
    		
    
          </div>
     </div> 
          
          
          
          
</form>

</body>
</html>