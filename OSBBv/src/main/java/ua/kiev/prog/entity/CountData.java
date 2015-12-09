package ua.kiev.prog.entity;

import javax.persistence.*;

/**
 * Created by mbro8_000 on 08.12.2015.
 */
@Entity
@Table(name = "counters_data")
public class CountData {
    @Id
    @GeneratedValue
    private long Id;
    private long value;
    private int rate;
    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name="user_id")
    private UserEntity userEntity;
    @ManyToOne(targetEntity = ServicesEntity.class)
    @JoinColumn(name = "service_id")
    private ServicesEntity servicesEntity;

    public CountData() {
    }

    public CountData(ServicesEntity servicesEntity, long value, int rate, UserEntity userEntity) {
        this.servicesEntity = servicesEntity;
        this.value = value;
        this.rate = rate;
        this.userEntity = userEntity;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ServicesEntity getServicesEntity() {
        return servicesEntity;
    }

    public void setServicesEntity(ServicesEntity servicesEntity) {
        this.servicesEntity = servicesEntity;
    }
}
