<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Parker
  Date: 2018/3/23
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE>
<html>
<head>
    <%@include file="/WEB-INF/web/head.jsp"%>
    <title>请先登录</title>
</head>
<body>
<jsp:include page="/WEB-INF/web/header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <div class="my-sm-5">
                <h1 class="text-center">请先登录</h1>
            </div>
            <div class="my-sm-5">
                <h5 class="text-center">
                    <a href="/user/login.action">跳转至登录页面</a>
                </h5>
            </div>
            <div class="my-sm-5">
                <h5 class="text-center">
                    <a href="${sessionScope.get("referURL")}">返回之前页面</a>
                </h5>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/web/footer.jsp"></jsp:include>
<%@include file="/WEB-INF/web/foot.jsp"%>
</body>
</html>
