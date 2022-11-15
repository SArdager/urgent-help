package kz.kdlolymp.gynecology.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kz.kdlolymp.gynecology.controller.serializers.UserSerializer;
import kz.kdlolymp.gynecology.entity.Region;
import kz.kdlolymp.gynecology.entity.User;
import kz.kdlolymp.gynecology.service.DefaultEmailService;
import kz.kdlolymp.gynecology.service.RegionService;
import kz.kdlolymp.gynecology.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private DefaultEmailService emailService;
    private Gson gson = new Gson();
    private String message = "";

    @RequestMapping("/admin/work-start")
    public String adminStartView(Model model){
        User user = getUserFromAuthentication();
        if (user!=null) {
            model.addAttribute("user", user);
            return "admin/work-start";
        } else {
            return "redirect: ../login";
        }
    }
    private User getUserFromAuthentication() {
        String username = "";
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userService.findByUsername(username);
    }
    @RequestMapping("/admin/work-page")
    public String adminPageView(Model model){
        User user = getUserFromAuthentication();
        if (user!=null) {
        model.addAttribute("user", user);
        return "admin/work-page";
        } else {
            return "redirect: ../login";
        }
    }

    @RequestMapping("/admin/edit-regions")
    public String editRegionsView(Model model){
        User user = getUserFromAuthentication();
        if (user!=null) {
            List<Region> regions = regionService.getAll();
            model.addAttribute("regions", regions);
            model.addAttribute("user", user);
            return "admin/edit-regions";
        } else {
            return "redirect: ../login";
        }
    }
    @RequestMapping("/admin/edit-rights")
    public String editRightsView(Model model){
        User user = getUserFromAuthentication();
        if (user!=null) {
            model.addAttribute("user", user);
            return "admin/edit-rights";
        } else {
            return "redirect: ../login";
        }
    }
    @RequestMapping("/admin/report")
    public String reportView(Model model){
        User user = getUserFromAuthentication();
        if (user!=null) {
            List<Region> regions = regionService.getAll();
            model.addAttribute("regions", regions);
            model.addAttribute("user", user);
            return "admin/report";
        } else {
            return "redirect: ../login";
        }
    }
    @PostMapping("/admin/edit-regions/get-names")
    public void getRegionNames(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("Request arrived");
        req.setCharacterEncoding("UTF-8");
        int regionId = Integer.parseInt(req.getParameter("regionId"));
        Region region = regionService.findRegionById(regionId);

        resp.setContentType("json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(this.gson.toJson(region));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @PostMapping("/admin/edit-regions/add-region")
    public void addRegion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String regionName = req.getParameter("regionName");
        String regionKzName = req.getParameter("regionKzName");
        if(id > 0) {
            Region region = regionService.findRegionById(id);
            region.setRegionName(regionName);
            region.setRegionKzName(regionKzName);
            if (regionService.saveRegion(region)) {
                message = "Название региона изменено";
            } else {
                message = "Ошибка записи в базу данных. Повторите.";
            }
        } else {
            if (regionService.addNewRegion(regionName, regionKzName)) {
                message = "Новый регион добавлен";
            } else {
                message = "Ошибка записи в базу данных. Возможно название региона уже имеется в базе данных.";
            }
        }
        resp.setContentType("text");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(message);
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @PostMapping("/admin/edit-regions/del-region")
    public void delRegion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        if(regionService.deleteRegion(id)){
            message = "Регион удален";
        } else {
            message = "Ошибка обращения в базу данных.";
        }
        resp.setContentType("text");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(message);
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @PostMapping("/admin/edit-rights/search-user")
    public void searchUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String text = req.getParameter("text").trim() + "%";
        List<User> usersList = userService.getUsersByPartUsername(text);
        List<User> users = new ArrayList<>();
        if(usersList.size()>0){
            for(User userFromList: usersList){
                User user = new User();
                user.setId(userFromList.getId());
                user.setUsername(userFromList.getUsername());
                user.setUserFirstname(userFromList.getUserFirstname());
                user.setUserSurname(userFromList.getUserSurname());
                user.setRole(userFromList.getRole());
                user.setRegionName(userFromList.getRegionName());
                users.add(user);
            }
        }
        resp.setContentType("json");
        resp.setCharacterEncoding("UTF-8");
        gson = new GsonBuilder().registerTypeAdapter(User.class, new UserSerializer()).create();
        resp.getWriter().print(gson.toJson(users));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @PostMapping("/admin/edit-rights/change-rights")
    public void changeRights(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String role = req.getParameter("role");
        User user = userService.findUserById(id);
        user.setRole(role);

        if(userService.saveUser(user)){
            message = "Права пользователя изменены.";
        } else {
            message = "Ошибка обращения в базу данных.";
        }
        resp.setContentType("text");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(message);
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @PostMapping("/admin/get-user")
    public void getUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(req.getParameter("userId"));
        User user = userService.getUserById(id);
        resp.setContentType("json");
        resp.setCharacterEncoding("UTF-8");
        gson = new GsonBuilder().registerTypeAdapter(User.class, new UserSerializer()).create();
        resp.getWriter().print(gson.toJson(user));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

}
