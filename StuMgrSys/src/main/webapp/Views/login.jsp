<%--
  Created by IntelliJ IDEA.
  User: jingcg
  Date: 17/12/5
  Time: 下午7:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>管理系统入口</title>

    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/myCss.css">
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <style>
        div.loginStyle{
            max-width: 420px;
            margin: auto;
            padding-top: 6%;
        }
    </style>

</head>
<body>
<div class="loginStyle">
    <div class="row">
        <div class="col-md-12">
            <div class = "text-center m-b-md">
                <h3>登录入口</h3>
                <small>请输入以下信息</small>
            </div>
            <div class = "hpanel">
                <div class="panel-body">
                    <form:form action="/loginCheck" method="post" role="form" commandName="login">
                        <div class="form-group">
                            <label class="control-label">账号</label>
                            <input type="text" class="form-control" id="userAccount" name="userAccount" placeholder="输入账号:"/>
                            <p style="color:red">${errUserAccount}</p>
                        </div>
                        <div class="form-group">
                            <label class="control-label">密码</label>
                            <input type="password" class="form-control" id="password" name="password" placeholder="输入密码:"/>
                            <p style="color:red">${errPWD}</p>
                        </div>
                        <button type="submit" class="btn btn-success btn-block">登录</button>
                    </form:form>
                    <h3>${loginFeedback}</h3>
                </div>

            </div>
        </div>
    </div>

</div>

</body>
</html>
