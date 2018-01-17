<%--
  Created by IntelliJ IDEA.
  User: jingcg
  Date: 17/12/9
  Time: 下午2:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <div>
        <h3>课程列表</h3>

        <!-- 如果用户列表为空 -->
        <c:if test="${empty courseList}">
            <table class="table table-bordered table-striped">
                <tr>
                    <th>课程号</th>
                    <th>课程名</th>
                    <th>学分</th>
                </tr>
            </table>
        </c:if>

        <!-- 如果用户列表非空 -->
        <c:if test="${!empty courseList}">
            <table class="table table-bordered table-striped">
                <tr>
                    <th>课程号</th>
                    <th>课程名</th>
                    <th>学分</th>
                </tr>

                <c:forEach items="${courseList}" var="course">
                    <tr>
                        <td>${course.cno}</td>
                        <td>${course.cname}</td>
                        <td>${course.ccredit}</td>
                        <td>
                            <a href="/selectStuCoursePost/${course.cno}" type="button" class="btn btn-sm btn-success">选修</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>

</div>

<script src="/js/jquery.min.js"></script>

<script src="/js/bootstrap.min.js"></script>

</body>
</html>
