package com.sms.repository;

import com.sms.model.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity,String> {
    @Modifying // 说明该方法是修改操作
    @Transactional // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update CourseEntity us set us.cname=:cname, us.ccredit=:ccredit where us.cno=:cno")
    public void updateCourse(@Param("cno") String cno,
                              @Param("cname") String cnamae,
                              @Param("ccredit") String ccredit);

    @Query("select us from CourseEntity us where us.cno not in (select sc.cno from SCEntity sc where sc.sno=:sno)")
    public List<CourseEntity> selectStuCourse(@Param("sno") String sno);

}
