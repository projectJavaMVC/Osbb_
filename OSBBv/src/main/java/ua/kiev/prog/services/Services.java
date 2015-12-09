package ua.kiev.prog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kiev.prog.entity.*;
import ua.kiev.prog.repositories.*;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class Services {


    @Autowired
    private BuildsEntityRepository buildsEntityRepository;
    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private UserInfoEntityRepository userIERepository;
    @Autowired
    private FlatsEntityRepository flatsRepository;
    @Autowired
    private ServicesEntityRepository SERepository;
    @Autowired
    private BuildsServicesRepository buildServicesRepository;
    @Autowired
    private CountDataRepository countDataRepository;


    @Transactional
    public void addCountData(CountData  countData){
        countDataRepository.save( countData);
    }

    @Transactional
    public CountData findLastValue (UserEntity userEntity,ServicesEntity servicesEntity )
    {
        return countDataRepository.findOneByUserEntityAndServicesEntity(userEntity, servicesEntity);
    }
    @Transactional
    public void addUser(UserEntity userEntity) {
        userEntityRepository.saveAndFlush(userEntity);
    }

    @Transactional
    public UserEntity mergeUser(UserEntity user) {
        return userEntityRepository.save(user);
    }

    @Transactional
    public List<UserEntity> findAllUsersByBuild(BuildsEntity buildsEntity) {
        return (List<UserEntity>) userEntityRepository.findAllByBuildsEntity(buildsEntity);
    }

    @Transactional
    public UserEntity findOneUserByBuild(BuildsEntity buildsEntity) {
        return userEntityRepository.findOneByBuildsEntity(buildsEntity);
    }

    @Transactional
    public UserEntity findOneUserByLogin(String login) {
        return userEntityRepository.findOneByLogin(login);
    }

    @Transactional
    public BuildsEntity findBuildById(Long id) {
        return buildsEntityRepository.findOne(id);
    }

    @Transactional
    public BuildsEntity findBuildByKey(String key) {
        return buildsEntityRepository.findOneByKey(key);
    }

    @Transactional
    public BuildsEntity mergeBuild(BuildsEntity build) {
        return buildsEntityRepository.save(build);
    }

    @Transactional
    public void addBuild(BuildsEntity build) {
        buildsEntityRepository.saveAndFlush(build);
    }

    @Transactional
    public List<FlatsEntity> findAllByBuildsEntity(BuildsEntity build) {
        return flatsRepository.findAllByBuildsEntity(build);
    }

    @Transactional
    public void addFlat(FlatsEntity flat) {
        flatsRepository.saveAndFlush(flat);
    }

    @Transactional
    public FlatsEntity mergeFlat(FlatsEntity flat) {
        return flatsRepository.save(flat);
    }

    @Transactional
    public FlatsEntity getFlatById(long id) {
        return flatsRepository.findOne(id);
    }

    @Transactional
    public void addUserInfo(UserInfoEntity userIE) {
        userIERepository.saveAndFlush(userIE);
    }

    @Transactional
    public List<ServicesEntity> listServices() {
        return SERepository.findAll();
    }

    @Transactional
    public ServicesEntity getServiceById(long id) {
        return SERepository.findOne(id);
    }

    @Transactional
    public void addBuildService(BuildServices buildServices) {
        buildServicesRepository.saveAndFlush(buildServices);
    }
}

