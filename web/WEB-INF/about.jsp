<%--
  Created by IntelliJ IDEA.
  User: Parker
  Date: 2018/3/23
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

    <title>登录</title>

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <style>
        html { position: relative; min-height: 100%; }
        body { margin-bottom: 60px; }
        .footer { position: absolute; bottom: 0; width: 100%; height: 60px; line-height: 60px; background-color: #f5f5f5; }
    </style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <div>
                <div class="my-sm-2 text-center">
                    <h1>关于</h1>
                </div>
                <div class="my-sm-5">
                    <h3>历史更新</h3>
                    <p class="my-sm-3" style="text-align: left">
                        <h5>版本：0.1.1</h5>
                        1.新增可通过排序查看帖子、回帖功能。<br>
                        2.添加设置、撤销管理员功能。<br>
                        3.添加置顶、高亮帖子功能。<br>
                        4.修复用户修改个人信息后无法及时更新session中相关个人信息的问题。<br>
                        5.修改部分AOP切点。<br>
                        6.修改部分权限的验证方式。<br>
                    </p>
                </div>
                <div class="my-sm-5 text-center">
                    <h5>Powered by Parker</h5>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>
<%@include file="foot.jsp"%>
</body>
</html>
