<%--
  Created by IntelliJ IDEA.
  User: jingcg
  Date: 17/12/8
  Time: 下午10:29
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
                <li class="style"><a href="/mgrMain">主页</a></li>
                <li class="style">
                    <div class="dropdown">
                        <p style="color: black;">学生管理</p>
                        <div class="dropdown-content">
                            <a href="/stuMgr">学生信息</a>
                            <br>
                            <a href="/addStu">添加学生</a>
                            <br>
                            <a href="/addGrade">录入成绩</a>
                        </div>
                    </div>
                </li>
                <li class="style">
                    <div class="dropdown">
                        <p style="color: black;">教师管理</p>
                        <div class="dropdown-content">
                            <a href="/teacherMgr">教师信息</a>
                            <br>
                            <a href="/addTeacher">添加教师</a>
                        </div>
                    </div>
                </li>
                <li class="style">
                    <div class="dropdown">
                        <p style="color: black;">课程管理</p>
                        <div class="dropdown-content">
                            <a href="/courseMgr">课程信息</a>
                            <br>
                            <a href="/addCourse">添加课程</a>
                        </div>
                    </div>
                </li>
                <li class="style">
                    <div class="dropdown">
                        <p style="color: black;">统计信息</p>
                        <div class="dropdown-content">
                            <a href="/figureTeacherNum">统计教师数量</a>
                            <br>
                            <a href="/figureTsalary">统计平均工资</a>
                            <br>
                            <a href="/figureCourseGrade">统计课程成绩</a>
                            <br>
                            <a href="/figureStuCredit">统计学生学分</a>
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

    <h1>更新课程信息</h1>
    <hr/>

    <form:form action="/updateCoursePost" method="post" commandName="courseP" role="form">
        <div class="form-group">
            <label>课程号:</label>
            <label>${course.cno}</label>
        </div>
        <div class="form-group">
            <label for="cname">课程名:</label>
            <input type="text" class="form-control" id="cname" name="cname" placeholder="输入课程名:"
                   value="${course.cname}"/>
            <p style="color:red">${errCname}</p>
        </div>
        <div class="form-group">
            <label for="ccredit">学分:</label>
            <input type="text" class="form-control" id="ccredit" name="ccredit" placeholder="输入学分:"
                   value="${course.ccredit}"/>
            <p style="color:red">${errCcredit}</p>
        </div>
        <input type="hidden" id="cno" name="cno" value="${course.cno}"/>

        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success">修改</button>
        </div>
    </form:form>
</div>


<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="/js/bootstrap.min.js"></script>
</body>
</html>