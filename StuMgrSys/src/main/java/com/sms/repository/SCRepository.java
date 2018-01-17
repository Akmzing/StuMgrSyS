package com.sms.repository;

import com.sms.model.CourseEntity;
import com.sms.model.SCEntity;
import com.sms.model.SCEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SCRepository extends JpaRepository<SCEntity,SCEntityPK> {

    @Transactional
    @Query("select us from SCEntity us where sno=:sno")
    public List<SCEntity> selectSCBySno(@Param("sno")String sno);

    @Transactional
    @Query("select us from SCEntity us where sno=:sno and cno=:cno")
    public SCEntity selectSCBySnoAndCno(@Param("sno")String sno,@Param("cno")String cno);

    @Modifying
    @Transactional
    @Query("update SCEntity sc set sc.grade=:grade where sno=:sno and cno=:cno")
    public void updateGrade(@Param("grade")String grade,@Param("sno")String sno,@Param("cno")String cno);

    @Transactional
    @Query("select us.cno,us.cname,avg(us.grade),max(us.grade),min(us.grade) from SCEntity us group by us.cno,us.cname")
    public List<Object> selectAvgGrade();

    @Transactional
    @Query("select us.sno,st.sname,sum(us.ccredit) from SCEntity us,StudentEntity st where us.sno = st.sno group by us.sno,st.sname ")
    public List<Object> selectStuCredit();

    @Modifying
    @Transactional
    @Query("delete from SCEntity sc where sc.sno=:sno")
    public void deleteBySno(@Param("sno")String sno);

    @Modifying
    @Transactional
    @Query("delete from SCEntity sc where sc.cno=:cno")
    public void deleteByCno(@Param("cno")String cno);
}
