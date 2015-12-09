package ua.kiev.prog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kiev.prog.entity.BuildServices;
import ua.kiev.prog.entity.BuildsEntity;
import ua.kiev.prog.entity.ServicesEntity;

import java.util.List;

/**
 * Created by Пользователь on 04.12.2015.
 */
//@Repository
public interface BuildsServicesRepository extends JpaRepository<BuildServices, Long> {

    List<BuildServices> findAllByServicesEntity(ServicesEntity service);
    List<BuildServices> findAllByBuildsEntity(BuildsEntity build);
}
