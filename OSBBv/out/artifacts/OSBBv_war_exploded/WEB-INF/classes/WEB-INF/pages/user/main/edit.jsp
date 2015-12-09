<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 25.11.2015
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
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
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">ОСББ</a>
    </div>
  </div>
</nav>

<div id="section1" class="container-fluid">
  <div class="container">
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/edit/userInfo" method="post">
      <div class="form-group">
        <div class="col-md-4">
          <h3>Введите данные для изменения</h3>
        </div>
      </div>
      <div class="form-group">
        <div class="col-md-4">
          <input type="text" class="form-control" name="name" placeholder="Имя">
        </div>
      </div>
      <div class="form-group">
        <div class="col-md-4">
          <input type="text" class="form-control" name="surname" placeholder="Фамилия">
        </div>
      </div>
      <div class="form-group">
        <div class="col-md-4">
          <input type="text" class="form-control" name="phone" placeholder="Телефон">
        </div>
      </div>
      <div class="form-group">
        <div class="col-md-4">
          <input type="text" class="form-control" name="email" placeholder="Электронная почта">
        </div>
      </div>
      <div class="form-group">
        <div class="col-md-4">
          <input type="text" class="form-control" name="area" placeholder="Площадь">
        </div>
      </div>
      <div class="form-group">
        <div class="col-md-4">
          <input type="text" class="form-control" name="peopleCNT" placeholder="Количество жильцов">
        </div>
      </div>
      <div class="form-group">
        <div class="col-md-4">
          <input type="submit" class="btn btn-primary" value="Подтвердить">
        </div>
      </div>
    </form>
  </div>
</div>

</body>
</html>
