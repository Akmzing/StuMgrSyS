<%--
  Created by IntelliJ IDEA.
  User: jingcg
  Date: 17/12/10
  Time: 下午2:41
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
    <div>
        <h3>学生列表</h3>

        <!-- 如果用户列表为空 -->
        <c:if test="${empty scList}">
            <table class="table table-bordered table-striped">
                <tr>
                    <th>学号</th>
                    <th>课程号</th>
                    <th>课程名</th>
                    <th>学分</th>
                    <th>成绩</th>
                </tr>
            </table>
        </c:if>

        <!-- 如果用户列表非空 -->
        <c:if test="${!empty scList}">
            <table class="table table-bordered table-striped">
                <tr>
                    <td>学号</td>
                    <th>课程号</th>
                    <th>课程名</th>
                    <th>学分</th>
                    <th>成绩</th>
                </tr>

                <c:forEach items="${scList}" var="sc">
                    <tr>
                        <td>${sc.sno}</td>
                        <td>${sc.cno}</td>
                        <td>${sc.cname}</td>
                        <td>${sc.ccredit}</td>
                        <td>${sc.grade}</td>
                        <td>
                            <a href="/addGradePost/${sc.sno}/${sc.cno}" type="button" class="btn btn-sm btn-success">录入成绩</a>
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
