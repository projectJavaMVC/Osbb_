package ua.kiev.prog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kiev.prog.entity.BuildServices;
import ua.kiev.prog.entity.ServicesEntity;

import java.util.List;

/**
 * Created by Пользователь on 30.11.2015.
 */
//@Repository
public interface ServicesEntityRepository extends JpaRepository<ServicesEntity, Long> {

}
