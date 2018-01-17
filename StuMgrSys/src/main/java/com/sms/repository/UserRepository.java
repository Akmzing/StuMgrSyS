package com.sms.repository;

import com.sms.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {
    @Modifying
    @Transactional
    @Query("update UserEntity us set us.password=:password where us.userAccount=:userAccount")
    public void updateUserPWD(@Param("password")String password,
                             @Param("userAccount")String userAccount);
}
