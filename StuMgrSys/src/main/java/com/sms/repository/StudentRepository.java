package com.sms.repository;

import com.sms.model.StudentEntity;
import org.hibernate.SessionFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,String>{

    @Modifying // 说明该方法是修改操作
    @Transactional // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update StudentEntity us set us.sname=:sname, us.ssex=:ssex, us.sage=:sage, us.spassword=:spassword where us.sno=:sno")
    public void updateStu(@Param("sno") String sno,
                           @Param("sname") String sname,
                           @Param("ssex") String ssex,
                           @Param("sage") String sage,
                           @Param("spassword") String spassword);

    @Modifying
    @Transactional
    @Query("update StudentEntity us set us.spassword=:spassword where us.sno=:sno")
    public void updateStuPWD(@Param("sno")String sno,
                             @Param("spassword")String spassword);
}
