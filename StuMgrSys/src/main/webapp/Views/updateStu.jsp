<%--
  Created by IntelliJ IDEA.
  User: jingcg
  Date: 17/12/5
  Time: 下午11:11
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

    <h1>更新学生信息</h1>
    <hr/>

    <form:form action="/updateStuPost" method="post" commandName="userP" role="form">
        <div class="form-group">
            <label>学号:</label>
            <label>${user.sno}</label>
        </div>
        <div class="form-group">
            <label for="sname">姓名:</label>
            <input type="text" class="form-control" id="sname" name="sname" placeholder="输入姓名:"
                   value="${user.sname}"/>
            <p style="color:red">${errSname}</p>
        </div>
        <div class="form-group">
            <label for="ssex">性别:</label>
            <input type="text" class="form-control" id="ssex" name="ssex" placeholder="输入性别:"
                   value="${user.ssex}"/>
            <p style="color:red">${errSsex}</p>
        </div>
        <div class="form-group">
            <label for="sage">年龄:</label>
            <input type="text" class="form-control" id="sage" name="sage" placeholder="输入年龄:"
                   value="${user.sage}"/>
            <p style="color:red">${errSage}</p>
        </div>
        <div class="form-group">
            <label for="spassword">密码:</label>
            <input type="text" class="form-control" id="spassword" name="spassword" placeholder="输入密码:"
                   value="${user.spassword}"/>
            <p style="color:red">${errSpassword}</p>
        </div>
        <input type="hidden" id="sno" name="sno" value="${user.sno}"/>

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
