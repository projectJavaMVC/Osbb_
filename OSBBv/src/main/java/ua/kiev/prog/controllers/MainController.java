package ua.kiev.prog.controllers;

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
import java.util.List;

/**
 * Created by mbro8_000 on 05.12.2015.
 */
@Controller
@RequestMapping("/")
public class MainController {
    static final int USER_TYPE = 0;
    static final int ADMIN_TYPE = 1;

    private UserEntity getCurrUser(){
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return services.findOneUserByLogin(login);
    }

    @Autowired
    private Services services;

    @RequestMapping("/sec/signIN")
    public String signin(  Model model) {
        UserEntity user = getCurrUser();
        new PDFCreate().createPDF(user);
        List<UserEntity> listUsers = services.findAllUsersByBuild(user.getBuildsEntity());
        List<User> listUser = new ArrayList<User>();
        for (UserEntity u : listUsers) {
            if (u.getId()!=user.getId() && u.getUserInfo() != null) {
                User us = new User(u);
                listUser.add(us);
            }
        }
        //
        List<ServiceUser> serviceUserList = new ArrayList<ServiceUser>();
        List<BuildServices> buildServicesList = user.getBuildsEntity().getServices();
        for( BuildServices buildServ : buildServicesList){
           // servicesEntityList.add(buildServ.getServicesEntity());
            ServiceUser serviceUser = new ServiceUser();
            serviceUser.setName(buildServ.getServicesEntity().getName());
            serviceUser.setServiceId(buildServ.getServicesEntity().getId());
            serviceUser.setRate(buildServ.getRate());
            serviceUser.setLastValue(services.findLastValue(user, buildServ.getServicesEntity()) == null ? 0 : services.findLastValue(user, buildServ.getServicesEntity()).getValue());
            serviceUserList.add(serviceUser);
        }
        //
        model.addAttribute("servicesList",serviceUserList);
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

        ////Вынести в метод инициализацию а и прокрутить тут.
        return   "user/main/mainuser";
    }
}
