package kz.kdlolymp.gynecology.config;

import kz.kdlolymp.gynecology.entity.Region;
import kz.kdlolymp.gynecology.entity.User;
import kz.kdlolymp.gynecology.entity.Visit;
import kz.kdlolymp.gynecology.repositories.VisitRepository;
import kz.kdlolymp.gynecology.service.RegionService;
import kz.kdlolymp.gynecology.service.UserService;
import kz.kdlolymp.gynecology.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SaveLangAuthenticationHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private VisitService visitService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username;
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        String lang = request.getParameter("lang");
        User user = userService.findByUsername(username);
        Visit visit = new Visit();
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        visit.setUser(user);
        visit.setVisitDate(date);
        System.out.println("SaveLangAuthenticationHandler, date: " + date);
        Region region = regionService.findRegionById(user.getRegionId());
        visit.setRegion(region);
        visitService.addVisit(visit);
        String savedLang = user.getLang();
        if(!lang.equals(savedLang)){
            user.setLang(lang);
            userService.saveUser(user);
        }
        if (user.getRole().equals("ADMIN")) {
            response.sendRedirect("admin/work-start");
        } else if (lang.equals("RU")){
            response.sendRedirect("ru/work-page");
        } else {
            response.sendRedirect("kz/work-page");
        }
    }
}
