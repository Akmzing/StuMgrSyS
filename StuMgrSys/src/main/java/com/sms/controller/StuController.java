package com.sms.controller;

import com.sms.model.CourseEntity;
import com.sms.model.SCEntity;
import com.sms.model.StudentEntity;
import com.sms.model.UserEntity;
import com.sms.repository.CourseRepository;
import com.sms.repository.SCRepository;
import com.sms.repository.StudentRepository;
import com.sms.repository.UserRepository;
import com.sms.uils.Uil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class StuController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SCRepository scRepository;
    @Autowired
    private UserRepository userRepository;

    //学生主页面
    @RequestMapping("/stuMain")
    public String stuMain() {
        if(Uil.UserType != 3) return "404Page";
        return "stuMain";
    }

    //可选修课程
    @RequestMapping("/selectStuCourse")
    public String selectStuCourse(ModelMap modelMap){
        if(Uil.UserType != 3) return "404Page";
        List<CourseEntity> courseEntityList = courseRepository.selectStuCourse(Uil.UserAccount);
        modelMap.addAttribute("courseList",courseEntityList);
        return "selectStuCourse";
    }

    //进行选修
    @RequestMapping("/selectStuCoursePost/{cno}")
    public String selectStuCoursePost(@PathVariable("cno")String cno){
        if(Uil.UserType != 3) return "404Page";
        System.out.println(cno);
        if(Uil.UserType == 0) return "404Page";
        CourseEntity courseEntity = courseRepository.findOne(cno);
        SCEntity scEntity = new SCEntity();
        scEntity.setSno(Uil.UserAccount);
        scEntity.setCno(cno);
        scEntity.setCname(courseEntity.getCname());
        scEntity.setCcredit(courseEntity.getCcredit());
        scEntity.setGrade("");
        scRepository.saveAndFlush(scEntity);
        return "redirect:/selectStuCourse";
    }

    //已选课程
    @RequestMapping("/selectedStuCourse")
    public String selectedStuCourse(ModelMap modelMap){
        if(Uil.UserType != 3) return "404Page";
        List<SCEntity> scEntityList = scRepository.selectSCBySno(Uil.UserAccount);
        modelMap.addAttribute("scList",scEntityList);
        int totalCredit = 0;
        for(int i=0;i<scEntityList.size();i++){
            totalCredit+= Integer.parseInt(scEntityList.get(i).getCcredit());
        }
        modelMap.addAttribute("totalCredit",String.valueOf(totalCredit));
        return "selectedStuCourse";
    }

    //学生个人信息
    @RequestMapping("/stuInf")
    public String stuInf(ModelMap modelMap){
        if(Uil.UserType != 3) return "404Page";
        StudentEntity studentEntity = studentRepository.findOne(Uil.UserAccount);
        modelMap.addAttribute("user",studentEntity);
        return "stuInf";
    }

    //显示修改密码界面
    @RequestMapping("/updateStuPWD")
    public String updateStuPWD(){
        if(Uil.UserType != 3) return "404Page";
        return "updateStuPWD";
    }

    //进行修改密码
    @RequestMapping("/updateStuPWDPost")
    public String updateStuPWDPost(String opassword, String password1,String password2,ModelMap modelMap){
        if(Uil.UserType != 3) return "404Page";
        boolean flag = true;
        if(!opassword.matches("^.*[a-zA-Z0-9]{6,15}+.*$")){
            flag = false;
            modelMap.addAttribute("errOpassword","密码必须为6到15个正常字符");
        }
        if(!password1.matches("^.*[a-zA-Z0-9]{6,15}+.*$")){
            flag = false;
            modelMap.addAttribute("errPassword1","密码必须为6到15个正常字符");
        }
        if(!password2.matches("^.*[a-zA-Z0-9]{6,15}+.*$")){
            flag = false;
            modelMap.addAttribute("errPassword2","密码必须为6到15个正常字符");
        }
        if(!flag){
            return "updateStuPWD";
        }
        StudentEntity studentEntity = studentRepository.findOne(Uil.UserAccount);
        if(!studentEntity.getSpassword().equals(opassword)){
            modelMap.addAttribute("updateFeedBack","原密码不正确");
            return "updateStuPWD";
        }
        if(!password1.equals(password2)){
            modelMap.addAttribute("updateFeedBack","重复密码填写不正确");
            return "updateStuPWD";
        }
        userRepository.updateUserPWD(password1,Uil.UserAccount);
        studentRepository.updateStuPWD(Uil.UserAccount,password1);
        modelMap.addAttribute("updateFeedBack","修改成功");
        return "updateStuPWD";
    }
}
