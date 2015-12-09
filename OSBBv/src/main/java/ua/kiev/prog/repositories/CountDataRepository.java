package ua.kiev.prog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.prog.entity.CountData;
import ua.kiev.prog.entity.ServicesEntity;
import ua.kiev.prog.entity.UserEntity;

/**
 * Created by mbro8_000 on 08.12.2015.
 */
public interface CountDataRepository extends JpaRepository<CountData,Long>{
    CountData findOneByUserEntityAndServicesEntity(UserEntity userEntity,ServicesEntity servicesEntity);
}
