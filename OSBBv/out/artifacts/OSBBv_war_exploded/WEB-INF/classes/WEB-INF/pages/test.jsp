<%--
  Created by IntelliJ IDEA.
  User: m.bratyuk
  Date: 26.11.2015
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form>
    <p>${name}</p>
  <input type="text" name="a" value="1" id="a" />
  <textarea name="b" rows="8" cols="40">2</textarea>
  <input type="button" onclick="add()" class="btn btn-success" formmethod="post" formenctype="multipart/form-data" formaction="/test" value="Регистрация">
</form>

<script>

   function add(){
     var s = $('this').serialize();
     alert(s);add()}
    ;


</script>
</body>
</html>
