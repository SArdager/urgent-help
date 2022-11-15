package kz.kdlolymp.gynecology.controller;

import kz.kdlolymp.gynecology.entity.Region;
import kz.kdlolymp.gynecology.entity.User;
import kz.kdlolymp.gynecology.service.DefaultEmailService;
import kz.kdlolymp.gynecology.service.RegionService;
import kz.kdlolymp.gynecology.service.TemporaryPasswordGenerator;
import kz.kdlolymp.gynecology.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private DefaultEmailService emailService;

    private String message = "";

    @RequestMapping("/index")
    public String enterView(){
        return "index";
    }

    @RequestMapping("/reg-ru")
    public String regRuuView(Model model){
        List<Region> regions = regionService.getAll();
        model.addAttribute("regions", regions);
        model.addAttribute("userForm", new User());
        return "reg-ru";
    }
    @RequestMapping("/reg-kz")
    public String regKzView(Model model){
        List<Region> regions = regionService.getAll();
        model.addAttribute("regions", regions);
        model.addAttribute("userForm", new User());
        return "reg-kz";
    }

    @GetMapping(value = "/registration", params = {"lang"})
    public String registrationView(HttpServletRequest req){
        String lang = req.getParameter("lang");
        if(lang.equals("RU")) {
            return "redirect: reg-ru";
        } else {
            return "redirect: reg-kz";
        }
    }

    @PostMapping("/registration/checkLogin")
    public void checkLogin (HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        if(userService.isUsernameExist(username)){
            message = "username exist";
        } else {
            message = "none";
        }
        resp.setContentType("text");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(message);
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @PostMapping("/registration/addUser")
    public  String registrationUser(@ModelAttribute("userForm")User userForm, Model model){
        userForm.setRole("USER");
        userForm.setEnabled(true);
        if(userService.addNewUser(userForm)){
            if(userForm.getLang().equals("RU")){
                return "redirect: ../ru/work-page";
            } else {
                return "redirect: ../kz/work-page";
            }
        } else{
            model.addAttribute("errorRegistration", "System error. Repeat later");
            return "../registration";
        }
    }



    @PostMapping("/forget-password")
    public  void forgetPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        User user = userService.findByUsername(username);
        if(user!=null && user.isEnabled()){
            TemporaryPasswordGenerator generator = new TemporaryPasswordGenerator();
            String password = "";
            if(user.getRole().equals("ADMIN")){
                password = generator.generateTemporaryPassword(8);
            } else {
                password = generator.generateTemporaryPassword(6);
            }
            user.setPassword(password);
            String errorMessage = "";
            String trueMessage = "";
            if(user.getLang().equals("RU")){
                trueMessage = "Временный пароль отправлен на ваш адрес электронной почты.";
                errorMessage = "Ошибка сброса пароля. Повторите позднее.";
            } else {
                trueMessage = "Уақытша пароль сіздің электрондық пошта мекенжайыңызға жіберілді.";
                errorMessage = "Құпия сөзді қалпына келтіру қатесі. Кейінірек қайталаңыз.";
            }
//            user.setTemporary(true);
            if(userService.changePassword(user)) {
                String toAddress = user.getEmail();
                if (emailService.sendTemporaryPassword(toAddress, password)) {
                    message = trueMessage;
                } else {
                    message = errorMessage;
                }
            } else {
                message = errorMessage;
            }
        } else {
            message = "";
        }
        resp.setContentType("text");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(message);
        resp.getWriter().flush();
        resp.getWriter().close();
    }

}
