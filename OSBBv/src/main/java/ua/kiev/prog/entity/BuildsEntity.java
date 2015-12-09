package ua.kiev.prog.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m.bratyuk on 23.11.2015.
 */
@Entity
@Table(name = "buildings")
public class BuildsEntity {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String code;
    private Short flatCnt;
    private String city;
    private String street;
    private String buildNum;

    public List<BuildServices> getServices() {
        return buildservices;
    }

    public void setServices(List<BuildServices> services) {
        this.buildservices = services;
    }

    @OneToMany(mappedBy = "buildsEntity", targetEntity = BuildServices.class)
    private List<BuildServices> buildservices = new ArrayList<BuildServices>();

    @OneToMany(mappedBy = "buildsEntity", targetEntity = FlatsEntity.class)
    private List<FlatsEntity> flats = new ArrayList<FlatsEntity>();


    @OneToMany(mappedBy = "buildsEntity", cascade = CascadeType.ALL, targetEntity = UserEntity.class)
    private List<UserEntity> users = new ArrayList<UserEntity>();

    public BuildsEntity(String code, Short flatCnt, String city, String street, String buildNum) {

        String symbols = "qwertyuiopasdfghjklzxcvbnm1234657890ZXCVBNASDFGHQWERTY";
        StringBuilder randString = new StringBuilder();
        int count = 10;
        for (int i = 0; i < count; i++)
            randString.append(symbols.charAt((int) (Math.random() * symbols.length())));
        this.code = randString.toString();
        this.flatCnt = flatCnt;
        this.city = city;
        this.street = street;
        this.buildNum = buildNum;
    }

    public BuildsEntity(String code) {
        String symbols = "qwertyuiopasdfghjklzxcvbnm1234657890ZXCVBNASDFGHQWERTY";
        StringBuilder randString = new StringBuilder();
        int count = 10;
        for (int i = 0; i < count; i++)
            randString.append(symbols.charAt((int) (Math.random() * symbols.length())));
        this.code = randString.toString();
    }

    public BuildsEntity() {
    }

    public List<FlatsEntity> getFlats() {
        return flats;
    }

    public void setFlats(List<FlatsEntity> flats) {
        this.flats = flats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Short getFlatCnt() {
        return flatCnt;
    }

    public void setFlatCnt(Short flatCnt) {
        this.flatCnt = flatCnt;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildNum() {
        return buildNum;
    }

    public void setBuildNum(String buildNum) {
        this.buildNum = buildNum;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
