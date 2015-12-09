package ua.kiev.prog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kiev.prog.entity.UserInfoEntity;

/**
 * Created by Пользователь on 30.11.2015.
 */
//@Repository
public interface UserInfoEntityRepository extends JpaRepository<UserInfoEntity, Long> {

}
