package ua.kiev.prog.controllers;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kiev.prog.entity.*;
import ua.kiev.prog.services.Services;
import ua.kiev.prog.utils.PDFCreate;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mbro8_000 on 05.12.2015.
 */
@Controller
@RequestMapping("/")
public class MainController {
    static final int USER_TYPE = 0;
    static final int ADMIN_TYPE = 1;

    @Autowired
    private Services services;

    @RequestMapping("/sec/signIN")
    public String signin(  Model model) {
        UserEntity user = getCurrUser();
        new PDFCreate().createPDF(user);
        List<UserEntity> listUsers = services.findAllUsersByBuild(user.getBuildsEntity());
        List<User> listUser = new ArrayList<User>();
        List<BuildServices> buildServicesList = user.getBuildsEntity().getServices();
        Map<String, String> countersData = new HashMap<String, String>();
        for (UserEntity u : listUsers) {
            if (u.getId()!=user.getId() && u.getUserInfo() != null) {
                User us = new User(u);
                for (BuildServices buildServ : buildServicesList) {
                    String name = buildServ.getServicesEntity().getName();
                    Long value = 0L;
                    if(services.findLastValue(u, buildServ.getServicesEntity()) != null)
                        value =  services.findLastValue(u, buildServ.getServicesEntity()).getValue();
                    countersData.put(name, value.toString());
                }
                us.setCurrentCounter(countersData);
                listUser.add(us);
            }
        }
        //
        model.addAttribute("payments",getPayments(user));
        model.addAttribute("servicesList", getCurrentUserServiceList(user));
        model.addAttribute("user", user);
        model.addAttribute("users", listUser);
        //инициализация А

        return user.getType() == USER_TYPE ? "user/main/mainuser" : "admin/main/mainadmin";

    }
    @RequestMapping("/user/add/currentvalue/{id}")
    public String listGroup(@PathVariable("id") long servId, @RequestParam int rate,@RequestParam long currentvalue ,Model model) {
        UserEntity user = getCurrUser();
        CountData data = new CountData();
        data.setServicesEntity(services.getServiceById(servId));
        data.setRate(rate);
        data.setValue(currentvalue);
        data.setUserEntity(user);
        services.addCountData(data);
        model.addAttribute("payments",getPayments(user));
        model.addAttribute("servicesList",getCurrentUserServiceList(user));
        model.addAttribute("user", user);
        model.addAttribute("users", getCurrentUserList(user));
        ////Вынести в метод инициализацию а и прокрутить тут.
        return   "user/main/mainuser";
    }
     @RequestMapping("/admin/add/payments/{serviceId}")
    public String addPayments(@PathVariable("serviceId") long serviceId,Model model) {
        UserEntity user = getCurrUser();
        ServicesEntity servicesEntity = services.getServiceById(serviceId);
        BuildServices buildServicese = services.getBuildServiceByBuildAndService(user.getBuildsEntity() , servicesEntity);
        List<UserEntity> userEntities = services.findAllUsersByBuild(user.getBuildsEntity());
        for(UserEntity userEntity : userEntities)
        {
            if (userEntity.getType()!=1)
            {
                CurrentPayments currentPayments = new CurrentPayments();
                currentPayments.setServicesEntity(servicesEntity);
                currentPayments.setUserEntity(userEntity);
                currentPayments.setRate(buildServicese.getRate());
                currentPayments.setStatus((short) 0);
                Map<Long,Long> countDatas = services.getCurrValuesByUser(userEntity);

                if (countDatas!=null) {
                    currentPayments.setCurrValue(countDatas.get(servicesEntity.getId()));
                } else {
                    currentPayments.setCurrValue(50);}
                Map<Long,Long> countDatas2 = services.getPrevValuesByUser(userEntity);
                for(Map.Entry<Long,Long>entry: countDatas2.entrySet()){
                    System.out.println(entry.getKey()+" - "+entry.getValue());
                    System.out.println(servicesEntity.getId());
                }

                if (countDatas2!=null) {
                    currentPayments.setPrevValue(countDatas2.get(servicesEntity.getId()));
                } else {
                    currentPayments.setPrevValue(0);}
                services.addCurrentPayments(currentPayments);
            }
        }
         model.addAttribute("payments",getPayments(user));
        model.addAttribute("servicesList",getCurrentUserServiceList(user));
        model.addAttribute("user", user);
        model.addAttribute("users", getCurrentUserList(user));
        ////Вынести в метод инициализацию а и прокрутить тут.
        return   "admin/main/mainadmin";
    }






//--//--//--//-- //--//--//--//--  //--//--//--//--  //--//--//--//--  //--//--//--//--  //--//--//--//--  //--//--//--//--
        public  List<User> getCurrentUserList(UserEntity user) {
        List<UserEntity> listUsers = services.findAllUsersByBuild(user.getBuildsEntity());
        List<User> listUser = new ArrayList<User>();
        for (UserEntity u : listUsers) {
            if (u.getUserInfo() != null && u.getId() != user.getId()) {
                User us = new User(u);
                listUser.add(us);
            }
        }
        return listUser;
    }

    public List<ServiceUser> getCurrentUserServiceList(UserEntity user) {
        List<ServiceUser> serviceUserList = new ArrayList<ServiceUser>();
        List<BuildServices> buildServicesList = user.getBuildsEntity().getServices();
        for (BuildServices buildServ : buildServicesList) {
            ServiceUser serviceUser = new ServiceUser();
            serviceUser.setName(buildServ.getServicesEntity().getName());
            serviceUser.setServiceId(buildServ.getServicesEntity().getId());
            serviceUser.setRate(buildServ.getRate());
            serviceUser.setLastValue(services.findLastValue(user, buildServ.getServicesEntity()) == null ? 0 : services.findLastValue(user, buildServ.getServicesEntity()).getValue());
            serviceUserList.add(serviceUser);
        }
        return serviceUserList;
    }

    private UserEntity getCurrUser(){
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return services.findOneUserByLogin(login);
    }
    private List<Payments> getPayments(UserEntity userEntity) {
        if (userEntity.getType() == 0) {
            List<CurrentPayments> currentPaymentses = services.getCurrentPayments(userEntity);
            List<Payments> payments = new ArrayList<Payments>();
            if (currentPaymentses!=null) {
                for (CurrentPayments currentPayment : currentPaymentses) {
                    Payments payment = new Payments();
                    payment.setCurrValue(currentPayment.getCurrValue());
                    payment.setPrevValue(currentPayment.getPrevValue());
                    payment.setRate(currentPayment.getRate());
                    payment.setServiceName(currentPayment.getServicesEntity().getName());
                    payment.calc();
                    payments.add(payment);
                }
            }
            return payments;
        }else
            return null;
    }



}
