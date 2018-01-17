package com.sms.controller;

import com.sms.model.*;
import com.sms.repository.*;
import com.sms.uils.Uil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@Controller
public class MgrController {

    // 自动装配
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SCRepository scRepository;

    @Autowired
    private ThreadPoolTaskExecutor executor;


    //管理员主页面
    @RequestMapping("/mgrMain")
    public String mainPage(){
        if(Uil.UserType != 1) return "404Page";
        Future future = executor.submit(new Callable() {
            public Object call() throws Exception {
                System.out.println("callable manager");
                return "manager";
            }
        });
        try {
            System.out.println("future.get()"+future.get());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return "mgrMain";
    }

    //学生信息页面
    @RequestMapping("/stuMgr")
    public String sayHello(ModelMap modelMap){
        if(Uil.UserType != 1) return "404Page";
        List<StudentEntity> studentEntityList = studentRepository.findAll();
        modelMap.addAttribute("userList", studentEntityList);
        return "stuMgr";
    }

    //添加学生处理
    @RequestMapping("/addStuPost")
    public String addStuPost(@ModelAttribute("userP") StudentEntity studentEntity,ModelMap modelMap){
        if(Uil.UserType != 1) return "404Page";
        if(!studentEntity.isValidate()){
            modelMap.addAttribute("errSno",studentEntity.getErrorMsg("errSno"));
            modelMap.addAttribute("errSname",studentEntity.getErrorMsg("errSname"));
            modelMap.addAttribute("errSsex",studentEntity.getErrorMsg("errSsex"));
            modelMap.addAttribute("errSage",studentEntity.getErrorMsg("errSage"));
            modelMap.addAttribute("errSpassword",studentEntity.getErrorMsg("errSpassword"));
            modelMap.addAttribute("user", studentEntity);
            return "addStu";
        }
        //从数据库获取该要添加的学生
        UserEntity userEntity;
        //该用户已经存在
        if(userRepository.exists(studentEntity.getSno())) {
            modelMap.addAttribute("addFeedBack","学生已存在");
            return "addStu";
        }
        //设置UserEntity的信息
        userEntity = new UserEntity();
        userEntity.setUserAccount(studentEntity.getSno());
        userEntity.setPassword(studentEntity.getSpassword());
        userEntity.setUserType(3);
        //同时再User表和Student表添加学生
        userRepository.saveAndFlush(userEntity);
        studentRepository.saveAndFlush(studentEntity);
        modelMap.addAttribute("addFeedBack","添加成功");
        return "addStu";
    }

    //登录检查
    @RequestMapping("/loginCheck")
    public String addLoginCheck(@ModelAttribute("login") UserEntity userEntity,ModelMap modelMap){

        if(!userEntity.isValidate()){
            modelMap.addAttribute("errUserAccount",userEntity.getErrorMsg("errUserAccount"));
            modelMap.addAttribute("errPWD",userEntity.getErrorMsg("errPWD"));
            return "login";
        }

        //从数据库获取改用户信息
        UserEntity userEntity1 = userRepository.findOne(userEntity.getUserAccount());

        if (userEntity1==null) {
            modelMap.addAttribute("loginFeedback","登录失败,用户名或密码不正确");
            return "login";
        }

        //密码正确
        if(userEntity1.getPassword().equals(userEntity.getPassword()))   {
            Uil.UserType = userEntity1.getUserType();
            Uil.UserAccount = userEntity.getUserAccount();
            //根据用户类型跳转到不同管理页面
            return getMainPage();
        }
        //密码错误，提示错误信息
        modelMap.addAttribute("loginFeedback","登录失败,用户名或密码不正确");
        return "login";
    }

    //登录界面
    @RequestMapping("/")
    public String login(){
        return "login";
    }

    //添加学生界面
    @RequestMapping("/addStu")
    public String addStu(){
        if(Uil.UserType != 1) return "404Page";
        return "addStu";
    }

    //学生详情界面
    @RequestMapping("/showStu/{sno}")
    public String showStu(@PathVariable("sno") String sno, ModelMap modelMap){
        if(Uil.UserType != 1) return "404Page";
        StudentEntity studentEntity = studentRepository.findOne(sno);
        modelMap.addAttribute("user",studentEntity);
        return "stuDetail";
    }

    //显示需要修改学生的信息
    @RequestMapping("/updateStu/{sno}")
    public String updateStu(@PathVariable("sno") String sno, ModelMap modelMap){
        if(Uil.UserType != 1) return "404Page";
        StudentEntity studentEntity = studentRepository.findOne(sno);
        modelMap.addAttribute("user",studentEntity);
        return "updateStu";
    }

    //修改学生
    @RequestMapping("/updateStuPost")
    public String updateStuPost(@ModelAttribute("userP") StudentEntity studentEntity,ModelMap modelMap){
        if(Uil.UserType != 1) return "404Page";
        if(!studentEntity.isValidate()){
            modelMap.addAttribute("errSname",studentEntity.getErrorMsg("errSname"));
            modelMap.addAttribute("errSsex",studentEntity.getErrorMsg("errSsex"));
            modelMap.addAttribute("errSage",studentEntity.getErrorMsg("errSage"));
            modelMap.addAttribute("errSpassword",studentEntity.getErrorMsg("errSpassword"));
            modelMap.addAttribute("user",studentEntity);
            return "updateStu";
        }
        studentRepository.updateStu(
                studentEntity.getSno(),
                studentEntity.getSname(),
                studentEntity.getSsex(),
                studentEntity.getSage(),
                studentEntity.getSpassword());
        return "redirect:/stuMgr";
    }

    //删除学生
    @RequestMapping("/delStu/{sno}")
    public String delStu(@PathVariable("sno") String sno){
        if(Uil.UserType != 1) return "404Page";
        studentRepository.delete(sno);
        userRepository.delete(sno);
        scRepository.deleteBySno(sno);
        scRepository.flush();
        studentRepository.flush();
        userRepository.flush();
        return "redirect:/stuMgr";
    }

    //教师信息界面
    @RequestMapping("/teacherMgr")
    public String teacherMgr(ModelMap modelMap){
        if(Uil.UserType != 1) return "404Page";
        List<TeacherEntity> teacherEntityList = teacherRepository.findAll();
        modelMap.addAttribute("userList", teacherEntityList);
        return "teacherMgr";
    }

    //添加教师界面
    @RequestMapping("/addTeacher")
    public String addTeacher(){
        if(Uil.UserType != 1) return "404Page";
        return "addTeacher";
    }

    //添加教师处理
    @RequestMapping("addTeacherPost")
    public String addTeacherPost(@ModelAttribute("userP") TeacherEntity teacherEntity,ModelMap modelMap){
        if(Uil.UserType != 1) return "404Page";
        if(!teacherEntity.isValidate()){
            modelMap.addAttribute("errTno",teacherEntity.getErrorMsg("errTno"));
            modelMap.addAttribute("errTname",teacherEntity.getErrorMsg("errTname"));
            modelMap.addAttribute("errTdept",teacherEntity.getErrorMsg("errTdept"));
            modelMap.addAttribute("errTsalary",teacherEntity.getErrorMsg("errTsalary"));
            modelMap.addAttribute("errTpassword",teacherEntity.getErrorMsg("errTpassword"));
            modelMap.addAttribute("user", teacherEntity);
            return "addTeacher";
        }

        //该用户已经存在
        if(userRepository.exists(teacherEntity.getTno())) {
            modelMap.addAttribute("addFeedBack","教师已存在");
            return "addTeacher";
        }
        //设置UserEntity的信息
        UserEntity userEntity = new UserEntity();
        userEntity.setUserAccount(teacherEntity.getTno());
        userEntity.setPassword(teacherEntity.getTpassword());
        userEntity.setUserType(2);
        //同时再User表和Teacher表添加教师
        userRepository.saveAndFlush(userEntity);
        teacherRepository.saveAndFlush(teacherEntity);
        modelMap.addAttribute("addFeedBack","添加成功");
        return "addTeacher";
    }

    //显示教师详细信息
    @RequestMapping("/showTeacher/{tno}")
    public String showTeacher(@PathVariable("tno") String tno, ModelMap modelMap){
        if(Uil.UserType != 1) return "404Page";
        TeacherEntity teacherEntity = teacherRepository.findOne(tno);
        modelMap.addAttribute("user",teacherEntity);
        return "teacherDetail";
    }

    //显示需要修改的教师信息页面
    @RequestMapping("/updateTeacher/{tno}")
    public String updateTeacher(@PathVariable("tno") String tno, ModelMap modelMap){
        if(Uil.UserType != 1) return "404Page";
        TeacherEntity teacherEntity = teacherRepository.findOne(tno);
        modelMap.addAttribute("user",teacherEntity);
        return "updateTeacher";
    }

    //处理修改教师
    @RequestMapping("/updateTeacherPost")
    public String updateTeacherPost(@ModelAttribute("userP") TeacherEntity teacherEntity,ModelMap modelMap){
        if(Uil.UserType != 1) return "404Page";
        if(!teacherEntity.isValidate()){
            modelMap.addAttribute("errTname",teacherEntity.getErrorMsg("errTname"));
            modelMap.addAttribute("errTdept",teacherEntity.getErrorMsg("errTdept"));
            modelMap.addAttribute("errTsalary",teacherEntity.getErrorMsg("errTsalary"));
            modelMap.addAttribute("errTpassword",teacherEntity.getErrorMsg("errTpassword"));
            modelMap.addAttribute("user",teacherEntity);
            return "updateTeacher";
        }
        teacherRepository.updateTeacher(
                teacherEntity.getTno(),
                teacherEntity.getTname(),
                teacherEntity.getTdept(),
                teacherEntity.getTsalary(),
                teacherEntity.getTpassword());
        return "redirect:/teacherMgr";
    }

    //删除教师
    @RequestMapping("/delTeacher/{tno}")
    public String delTeacher(@PathVariable("tno") String tno){
        if(Uil.UserType != 1) return "404Page";
        teacherRepository.delete(tno);
        userRepository.delete(tno);
        teacherRepository.flush();
        userRepository.flush();
        return "redirect:/teacherMgr";
    }

    //课程信息界面
    @RequestMapping("/courseMgr")
    public String courseMgr(ModelMap modelMap){
        if(Uil.UserType != 1) return "404Page";
        List<CourseEntity> courseEntityList = courseRepository.findAll();
        modelMap.addAttribute("courseList", courseEntityList);
        return "courseMgr";
    }

    //添加课程界面
    @RequestMapping("/addCourse")
    public String addCourse(){
        if(Uil.UserType != 1) return "404Page";
        return "addCourse";
    }

    //添加课程处理
    @RequestMapping("addCoursePost")
    public String addTeacherPost(@ModelAttribute("courseP") CourseEntity courseEntity,ModelMap modelMap){
        if(Uil.UserType != 1) return "404Page";
        if(!courseEntity.isValidate()){
            modelMap.addAttribute("errCno",courseEntity.getErrorMsg("errCno"));
            modelMap.addAttribute("errCname",courseEntity.getErrorMsg("errCname"));
            modelMap.addAttribute("errCcredit",courseEntity.getErrorMsg("errCcredit"));
            modelMap.addAttribute("course", courseEntity);
            return "addCourse";
        }

        //该用户已经存在
        if(courseRepository.exists(courseEntity.getCno())) {
            modelMap.addAttribute("addFeedBack","课程已存在");
            return "addCourse";
        }
        courseRepository.saveAndFlush(courseEntity);
        modelMap.addAttribute("addFeedBack","添加成功");
        return "addCourse";
    }

    //显示课程详细信息
    @RequestMapping("/showCourse/{cno}")
    public String showCourse(@PathVariable("cno") String cno, ModelMap modelMap){
        if(Uil.UserType != 1) return "404Page";
        CourseEntity courseEntity = courseRepository.findOne(cno);
        modelMap.addAttribute("course",courseEntity);
        return "courseDetail";
    }

    //显示需要修改的课程信息页面
    @RequestMapping("/updateCourse/{cno}")
    public String updateCourse(@PathVariable("cno") String cno, ModelMap modelMap){
        if(Uil.UserType != 1) return "404Page";
        CourseEntity courseEntity = courseRepository.findOne(cno);
        modelMap.addAttribute("course",courseEntity);
        return "updateCourse";
    }

    //处理修改课程
    @RequestMapping("/updateCoursePost")
    public String updateCoursePost(@ModelAttribute("courseP") CourseEntity courseEntity,ModelMap modelMap){
        if(Uil.UserType != 1) return "404Page";
        if(!courseEntity.isValidate()){
            modelMap.addAttribute("errCname",courseEntity.getErrorMsg("errCname"));
            modelMap.addAttribute("errCcredit",courseEntity.getErrorMsg("errCcredit"));
            modelMap.addAttribute("course",courseEntity);
            return "updateCourse";
        }
        courseRepository.updateCourse(
                courseEntity.getCno(),
                courseEntity.getCname(),
                courseEntity.getCcredit());
        return "redirect:/courseMgr";
    }

    //删除课程
    @RequestMapping("/delCourse/{cno}")
    public String delCourse(@PathVariable("cno") String cno){
        if(Uil.UserType != 1) return "404Page";
        courseRepository.delete(cno);
        scRepository.deleteByCno(cno);
        courseRepository.flush();
        scRepository.flush();
        return "redirect:/courseMgr";
    }

    //退出
    @RequestMapping("loginOut")
    public String loginOut(){
        Uil.UserAccount="";
        Uil.UserType = 0;
        return "login";
    }

    //显示需要录入成绩的学生
    @RequestMapping("/addGrade")
    public String addGrade(ModelMap modelMap){
        List<SCEntity> scEntityList = scRepository.findAll();
        modelMap.addAttribute("scList",scEntityList);
        return "addGrade";
    }

    //显示录入成绩的学生详情
    @RequestMapping("/addGradePost/{sno}/{cno}")
    public String addGradePost(@PathVariable("sno")String sno,@PathVariable("cno")String cno,ModelMap modelMap){
        SCEntity scEntity = scRepository.selectSCBySnoAndCno(sno,cno);
        modelMap.addAttribute("sc",scEntity);
        return "updateGrade";
    }
    //进行成绩录入
    @RequestMapping("updateGradePost")
    public String updateGradePost(@ModelAttribute("scP")SCEntity scEntity, ModelMap modelMap){
        if(!scEntity.getGrade().matches("^[1-9][0-9]{0,1}$")){
            if(!scEntity.getGrade().equals("")&&!scEntity.getGrade().equals("100")&&!scEntity.getGrade().equals("0")){
                modelMap.addAttribute("sc",scEntity);
                modelMap.addAttribute("errGrade","成绩只能为0-100");
                return "updateGrade";
            }
        }
        scRepository.updateGrade(scEntity.getGrade(),scEntity.getSno(),scEntity.getCno());
        return "redirect:/addGrade";
    }

    //统计不同部门教师的数量
    @RequestMapping("/figureTeacherNum")
    public String figureTeacherNum(ModelMap modelMap){
        List<Object> objects = teacherRepository.selectDept();
        List<TdeptAndCount> tdeptAndCountList = new ArrayList<TdeptAndCount>();
        for(int i=0;i<objects.size();i++){
            Object[] objects1 = (Object[])objects.get(i);
            TdeptAndCount tdeptAndCount = new TdeptAndCount();
            tdeptAndCount.setTdept((String)objects1[0]);
            Long count = (Long)objects1[1];
            tdeptAndCount.setCount(String.valueOf(count));
            tdeptAndCountList.add(tdeptAndCount);
        }
        modelMap.addAttribute("userList",tdeptAndCountList);
        return "figureTeacherNum";
    }

    //统计不同部门教师的平均工资
    @RequestMapping("/figureTsalary")
    public String figureTsalary(ModelMap modelMap){
        List<Object> objects = teacherRepository.selectTsalary();
        List<TdeptAndCount> tdeptAndCountList = new ArrayList<TdeptAndCount>();
        for(int i=0;i<objects.size();i++){
            Object[] objects1 = (Object[])objects.get(i);
            TdeptAndCount tdeptAndCount = new TdeptAndCount();
            tdeptAndCount.setTdept((String)objects1[0]);
            Double count = (Double) objects1[1];
            tdeptAndCount.setCount(String.valueOf(count));
            tdeptAndCountList.add(tdeptAndCount);
        }
        modelMap.addAttribute("userList",tdeptAndCountList);
        return "figureTsalary";
    }

    //统计课程的成绩
    @RequestMapping("/figureCourseGrade")
    public String figureCourseGrade(ModelMap modelMap){
        List<Object> objects = scRepository.selectAvgGrade();
        List<AvgGrade> avgGradeList = new ArrayList<AvgGrade>();
        for(int i=0;i<objects.size();i++){
            Object[] objects1 = (Object[])objects.get(i);
            AvgGrade avgGrade = new AvgGrade();
            avgGrade.setCno((String)objects1[0]);
            avgGrade.setCname((String)objects1[1]);
            avgGrade.setAvgGrade(String.valueOf(objects1[2]));
            avgGrade.setMaxGrade(String.valueOf(objects1[3]));
            avgGrade.setMinGrade(String.valueOf(objects1[4]));
            avgGradeList.add(avgGrade);
        }
        modelMap.addAttribute("scList",avgGradeList);
        return "figureCourseGrade";
    }

    //统计学生的成绩
    @RequestMapping("/figureStuCredit")
    public String figureStuCredit(ModelMap modelMap){
        List<Object> objects = scRepository.selectStuCredit();
        List<StuCredit> stuCreditList = new ArrayList<StuCredit>();
        for(int i=0;i<objects.size();i++){
            Object[] objects1 = (Object[])objects.get(i);
            StuCredit stuCredit = new StuCredit();
            stuCredit.setSno((String)objects1[0]);
            stuCredit.setSname((String)objects1[1]);
            stuCredit.setCredit(String.valueOf(objects1[2]));
            stuCreditList.add(stuCredit);
        }
        modelMap.addAttribute("userList",stuCreditList);
        return "figureStuCredit";
    }

    //获取主页面
    public String getMainPage(){
        switch (Uil.UserType){
            case 1:
                return "redirect:/mgrMain";
            case 2:
                return "";
            case 3:
                return "redirect:/stuMain";
            default:
                return "login";
        }
    }

}
