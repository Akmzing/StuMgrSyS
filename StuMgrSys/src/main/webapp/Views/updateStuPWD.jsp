<%--
  Created by IntelliJ IDEA.
  User: jingcg
  Date: 17/12/9
  Time: 下午5:29
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
    <title>管理系统</title>

    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/myCss.css">
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>

</head>
<body>
<div class="main-body">
    <hr/>
    <div class="containerNavigation">
        <div class="row">
            <ul class="pc-nav">
                <li class="style"><a href="/stuMain">主页</a></li>
                <li class="style">
                    <div class="dropdown">
                        <p style="color: black;">选课管理</p>
                        <div class="dropdown-content">
                            <a href="/selectedStuCourse">我的课程</a>
                            <br>
                            <a href="/selectStuCourse">选修课程</a>
                        </div>
                    </div>
                </li>
                <li class="style">
                    <div class="dropdown">
                        <p style="color: black;">个人管理</p>
                        <div class="dropdown-content">
                            <a href="/stuInf">个人信息</a>
                            <br>
                            <a href="/updateStuPWD">修改密码</a>
                        </div>
                    </div>
                </li>
                <li class="style">
                    <a style="color: black;" href="/loginOut">注销</a>
                </li>
            </ul>
        </div>
    </div>
    <hr/>

    <h1>修改密码</h1>
    <hr/>

    <form:form action="/updateStuPWDPost" method="get" role="form">
        <div class="form-group">
            <label for="opassword">原密码:</label>
            <input type="text" class="form-control" id="opassword" name="opassword" placeholder="输入密码:"/>
            <p style="color:red">${errOpassword}</p>
        </div>
        <div class="form-group">
            <label for="password1">密码:</label>
            <input type="text" class="form-control" id="password1" name="password1" placeholder="输入密码:"/>
            <p style="color:red">${errPassword1}</p>
        </div>
        <div class="form-group">
            <label for="password2">密码:</label>
            <input type="text" class="form-control" id="password2" name="password2" placeholder="输入密码:"/>
            <p style="color:red">${errPassword2}</p>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success">修改</button>
        </div>
        <h3>${updateFeedBack}</h3>
    </form:form>
</div>


<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="/js/bootstrap.min.js"></script>
</body>
</html>
