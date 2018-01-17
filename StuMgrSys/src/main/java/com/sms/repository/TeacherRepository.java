package com.sms.repository;

import com.sms.model.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity,String> {
    @Modifying // 说明该方法是修改操作
    @Transactional // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update TeacherEntity us set us.tname=:tnamae, us.tdept=:tdept, us.tsalary=:tsalary, us.tpassword=:tpassword where us.tno=:tno")
    public void updateTeacher(@Param("tno") String tno,
                          @Param("tnamae") String tnamae,
                          @Param("tdept") String tdept,
                          @Param("tsalary") String tsalary,
                          @Param("tpassword") String tpassword);

    @Transactional
    @Query("select us.tdept,count(us.tdept) from TeacherEntity us group by us.tdept")
    public List<Object> selectDept();

    @Transactional
    @Query("select us.tdept,avg(us.tsalary)from TeacherEntity us group by us.tdept")
    public List<Object> selectTsalary();

}
