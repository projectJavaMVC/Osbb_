package ua.kiev.prog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.kiev.prog.entity.CountData;
import ua.kiev.prog.entity.ServicesEntity;
import ua.kiev.prog.entity.UserEntity;

import java.util.List;

/**
 * Created by mbro8_000 on 08.12.2015.
 */
public interface CountDataRepository extends JpaRepository<CountData,Long>{
    //@Query("select c from CountData c where userEntity=:user and servicesEntity=:service order by c.id ")
    CountData findTop1ByUserEntityAndServicesEntityOrderByIdDesc(UserEntity userEntity,ServicesEntity servicesEntity);
    List<CountData> findTop2ByUserEntityAndServicesEntityOrderByIdDesc (UserEntity userEntity,ServicesEntity servicesEntity);
}
