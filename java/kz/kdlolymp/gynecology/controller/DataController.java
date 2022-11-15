package kz.kdlolymp.gynecology.controller;

import com.google.gson.Gson;
import kz.kdlolymp.gynecology.entity.Article;
import kz.kdlolymp.gynecology.entity.Region;
import kz.kdlolymp.gynecology.entity.User;
import kz.kdlolymp.gynecology.service.ArticleService;
import kz.kdlolymp.gynecology.service.RegionService;
import kz.kdlolymp.gynecology.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class DataController {

    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private RegionService regionService;
    private Gson gson = new Gson();
    private String message;

    @RequestMapping("ru/work-page")
    public String ruWorkPageView(Model model){
        User user = getUserFromAuthentication();
        if (user!=null) {
            if (user.getLang().equals("KZ")) {
                user.setLang("RU");
                userService.saveUser(user);
            }
            model.addAttribute("user", user);
            return "ru/work-page";
        } else {
            return "redirect: ../login";
        }
    }
    @RequestMapping("kz/work-page")
    public String kzWorkPageView(Model model){
        User user = getUserFromAuthentication();
        if (user!=null) {
            if(user.getLang().equals("RU")){
                user.setLang("KZ");
                userService.saveUser(user);
            }
            String confirmChange = (String) model.getAttribute("confirmChange");
            model.addAttribute("confirmChange", confirmChange);
            model.addAttribute("user", user);
            return "kz/work-page";
        } else {
            return "redirect: ../login";
        }
    }
    @RequestMapping("/ru/change-password")
    public String ruChangePasswordView(Model model){
        User user = getUserFromAuthentication();
        if (user!=null) {
            if(user.getLang().equals("KZ")){
                user.setLang("RU");
                userService.saveUser(user);
            }
            model.addAttribute("user", user);
            return "ru/change-password";
        } else {
            return "redirect: ../login";
        }
    }
    @RequestMapping("/kz/change-password")
    public String kzChangePasswordView(Model model){
        User user = getUserFromAuthentication();
        if (user!=null) {
            if(user.getLang().equals("RU")){
                user.setLang("KZ");
                userService.saveUser(user);
            }
            model.addAttribute("user", user);
            return "kz/change-password";
        } else {
            return "redirect: ../login";
        }
    }
    @RequestMapping("/ru/analyses")
    public String ruAnalysesView(Model model){
        User user = getUserFromAuthentication();
        if (user!=null) {
            if(user.getLang().equals("KZ")){
                user.setLang("RU");
                userService.saveUser(user);
            }
            model.addAttribute("user", user);
            return "ru/analyses";
        } else {
            return "redirect: ../login";
        }
    }
    @RequestMapping("/kz/analyses")
    public String kzAnalysesView(Model model){
        User user = getUserFromAuthentication();
        if (user!=null) {
            if(user.getLang().equals("RU")){
                user.setLang("KZ");
                userService.saveUser(user);
            }
            model.addAttribute("user", user);
            return "kz/analyses";
        } else {
            return "redirect: ../login";
        }
    }
    @RequestMapping("/ru/continue-care")
    public String ruContinueView(Model model){
        User user = getUserFromAuthentication();
        if (user!=null) {
            if(user.getLang().equals("KZ")){
                user.setLang("RU");
                userService.saveUser(user);
            }
            model.addAttribute("user", user);
            return "ru/continue-care";
        } else {
            return "redirect: ../login";
        }
    }
    @RequestMapping("/kz/continue-care")
    public String kzContinueView(Model model){
        User user = getUserFromAuthentication();
        if (user!=null) {
            if(user.getLang().equals("RU")){
                user.setLang("KZ");
                userService.saveUser(user);
            }
            model.addAttribute("user", user);
            return "kz/continue-care";
        } else {
            return "redirect: ../login";
        }
    }
    @RequestMapping("/ru/dbc-page")
    public String ruDbcView(Model model){
        User user = getUserFromAuthentication();
        if (user!=null) {
            if(user.getLang().equals("KZ")){
                user.setLang("RU");
                userService.saveUser(user);
            }
            model.addAttribute("user", user);
            return "ru/dbc-page";
        } else {
            return "redirect: ../login";
        }
    }
    @RequestMapping("/kz/dbc-page")
    public String kzDbcView(Model model){
        User user = getUserFromAuthentication();
        if (user!=null) {
            if(user.getLang().equals("RU")){
                user.setLang("KZ");
                userService.saveUser(user);
            }
            model.addAttribute("user", user);
            return "kz/dbc-page";
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

    @PostMapping("/article/get-texts")
    public void getArticles(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String lang = req.getParameter("lang");
        List<Article> articles = articleService.getArticlesByLang(lang);
        resp.setContentType("json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(this.gson.toJson(articles));
        resp.getWriter().flush();
        resp.getWriter().close();
    }
    @PostMapping("/article/change-text")
    public void changeArticle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String lang = req.getParameter("lang");
        String text = req.getParameter("text");
        int placeNumber = Integer.parseInt(req.getParameter("placeNumber"));
        String placeName = "text" + placeNumber;
        Article article = articleService.getArticleByPlaceNameAndLang(placeName, lang);
        if(article==null){
            article = new Article();
            article.setLang(lang);
            article.setPlaceName(placeName);
        }
        article.setText(text);
        if(articleService.saveArticle(article)){
            message = "Текст параграфа изменен";
        } else {
            message = "Ошибка обращения в базу данных. Повторите.";
        }
        resp.setContentType("text");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(message);
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @PostMapping("/change-password/change")
    public  void changePassword(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String password = req.getParameter("password");
        User user = getUserFromAuthentication();
        user.setPassword(password);
        user.setTemporary(false);
        if(user.getLang().equals("RU")){
            if(userService.changePassword(user)){
                message = "Пароль пользователя изменен";
            } else {
                message = "Ошибка смены пароля пользователя";
            }
        } else{
            if(userService.changePassword(user)){
                message = "Пайдаланушының паролі өзгертілді";
                } else {
                message = "Пайдаланушының паролін өзгерту қатесі";
            }
        }
        resp.setContentType("text");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(message);
        resp.getWriter().flush();
        resp.getWriter().close();
    }


}
