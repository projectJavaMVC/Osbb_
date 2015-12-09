<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

  <title>OSBB Главная страница. Ведение учета вашего ОСББ</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <style>
    body {
      position: relative;
    }
    #section1 {padding-top:50px;height:100%;color: #fff; background-color: #1E88E5;}
  </style>


</head>
<body data-spy="scroll" data-target=".navbar" data-offset="50">
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar">ывфы</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">ОСББ</a>
    </div>
  </div>
</nav>

<div id="section1" class="container-fluid">
  <div class="container">



      <div class="row">
        <div class="control-group" id="fields">
          <div class="controls">
            <form role="form"     enctype="multipart/form-data" class="form-horizontal" action="/test2" method="post">
              <c:forEach items="${services}" var="service">

              <div class="entry input-group col-xs-3">
                  <input class="form-control" co  name="service_ID_${service.id}"  type="text" placeholder="${service.name}"/>

                    	<span class="input-group-btn">
                            <button class="btn btn-success btn-remove btn-danger" type="button">
                              <span class="glyphicon glyphicon-minus"></span>
                            </button>
                        </span>
              </div>
              </c:forEach>
              <div class="form-group">
                <div class="col-md-4">
                  <input type="submit"    class="btn btn-success"  value="Регистрация">
                 </div>
              </div>
            </form>
            <div class="form-group">
              <div class="col-md-4">
                <input type="button"  id="click"  class="btn btn-success"  value="test">
              </div>
            </div>
          </div>
        </div>
      </div>




  </div>
</div>

<script>
  var services = [];
  $('#clicfucckkk').click(function(){

    var str = $("form").serialize();
    $("#results").text(str);
    $.post("/test2/",str);
  });

  $('#click').click(function(){

    console.log(services)
  });


  $(function()
  {
    $(document).on('click', '.btn-remove', function(e)
    {
      $(this).parents('.entry:first').remove();

      e.preventDefault();
      return false;
    });
  });




</script>
</body>
</html>
