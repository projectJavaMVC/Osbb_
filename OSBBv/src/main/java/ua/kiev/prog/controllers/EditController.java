package ua.kiev.prog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kiev.prog.entity.*;
import ua.kiev.prog.services.Services;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Пользователь on 09.12.2015.
 */

@Controller
@RequestMapping("/")
public class EditController {

    @Autowired
    private Services services;

    @RequestMapping("/user/edit")
    public String edit(Model model) {
        UserEntity us = getCurrUser();
        User user = new User(us);
        model.addAttribute("user", user);
        return "/user/main/edit";

    }

    @RequestMapping("/admin/edit")
    public String editAdmin(Model model) {
        UserEntity user = getCurrUser();
        model.addAttribute("services", getAllUserServiceList(user));
        return "/admin/main/edit2";
    }

    @RequestMapping("/edit/ServiceRates")
    public void editServiceRates(@RequestParam Map<String,String > allRequestParam,HttpServletResponse response, Model model) {
        try {
            UserEntity user = getCurrUser();
            List<BuildServices> buildServicesList = user.getBuildsEntity().getServices();
            for (Map.Entry<String, String> entry : allRequestParam.entrySet()) {
                for (BuildServices buildService : buildServicesList) {
                    if (buildService.getId() == Integer.parseInt(entry.getKey()))
                        buildService.setRate(Integer.parseInt(entry.getValue()));
                    services.mergeBuildService(buildService);
                }
            }
            services.mergeBuild(user.getBuildsEntity());
            response.sendRedirect("/sec/signIN");
        } catch (Exception e){
            e.printStackTrace();
        }

       /* model.addAttribute("services", getCurrentUserServiceList(user));
        model.addAttribute("user", user);

        return "/admin/main/mainadmin";*/
    }

    @RequestMapping("/edit/userInfo")
    public void editUserData(@RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam String email,
                               @RequestParam String phone,
                               @RequestParam BigDecimal area,
                               @RequestParam int peopleCNT,
                               HttpServletResponse response,
                               Model model) {
        try {
            UserEntity us = getCurrUser();
            us.getUserInfo().setFirstName(name);
            us.getUserInfo().setLastName(surname);
            us.getUserInfo().setPhone(phone);
            us.setEmail(email);
            us.getUserInfo().getFlatsEntity().setArea(area);
            us.getUserInfo().getFlatsEntity().setPeopleCnt(peopleCNT);
            response.sendRedirect("/sec/signIN");
        } catch (Exception e){
            e.printStackTrace();
        }

        /*User user = new User(us);


        model.addAttribute("servicesList", getCurrentUserServiceList(us));
        model.addAttribute("user", user);
        model.addAttribute("users", getCurrentUserList(us));
        return "user/main/mainuser";*/
    }


    private UserEntity getCurrUser(){
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return services.findOneUserByLogin(login);
    }

   /* public List<User> getCurrentUserList(UserEntity user) {
        List<UserEntity> listUsers = services.findAllUsersByBuild(user.getBuildsEntity());
        List<User> listUser = new ArrayList<User>();
        for (UserEntity u : listUsers) {
            if (u.getUserInfo() != null && u.getId() != user.getId()) {
                User us = new User(u);
                listUser.add(us);
            }
        }
        return listUser;
    }*/

   /* public List<ServiceUser> getCurrentUserServiceList(UserEntity user) {
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
    }*/
     public List<ServiceUser> getAllUserServiceList(UserEntity user) {
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
        List<ServicesEntity> servicesEntities = services.getAllServicesEntity();
        outer: for(ServicesEntity service : servicesEntities){
            for(ServiceUser serviceUser: serviceUserList){
                if(serviceUser.getServiceId()==service.getId())
                { continue outer;}
            }
            ServiceUser serviceUser2 = new ServiceUser();
            serviceUser2.setName(service.getName());
            serviceUser2.setServiceId(service.getId());
            serviceUserList.add(serviceUser2);
        }
        return serviceUserList;
    }

}
