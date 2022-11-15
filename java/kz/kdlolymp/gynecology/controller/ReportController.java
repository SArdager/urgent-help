package kz.kdlolymp.gynecology.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kz.kdlolymp.gynecology.controller.serializers.RegionVisitsSerializer;
import kz.kdlolymp.gynecology.controller.serializers.UserVisitsSerializer;
import kz.kdlolymp.gynecology.controller.serializers.VisitSerializer;
import kz.kdlolymp.gynecology.entity.Region;
import kz.kdlolymp.gynecology.entity.RegionVisits;
import kz.kdlolymp.gynecology.entity.UserVisits;
import kz.kdlolymp.gynecology.entity.Visit;
import kz.kdlolymp.gynecology.excelExport.RegionsExcelExporter;
import kz.kdlolymp.gynecology.excelExport.UserInfoExcelExporter;
import kz.kdlolymp.gynecology.excelExport.UsersVisitsExcelExporter;
import kz.kdlolymp.gynecology.excelExport.VisitsExcelExporter;
import kz.kdlolymp.gynecology.service.RegionService;
import kz.kdlolymp.gynecology.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private VisitService visitService;
    @Autowired
    private RegionService regionService;
    private Gson gson = new Gson();

    @PostMapping("/admin/load-data/report-totalVisits")
    public void loadTotalVisits(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        int regionId = Integer.parseInt(req.getParameter("regionId"));
        LocalDateTime[] localDateTimes = getLocalDateTime(req.getParameter("startDate"), req.getParameter("endDate"));
        LocalDateTime startDateTime = localDateTimes[0];
        LocalDateTime endDateTime = localDateTimes[1];

        long count = visitService.getAllBetweenDates(startDateTime, endDateTime, regionId).size();
        resp.setContentType("json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(this.gson.toJson(count));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @PostMapping("/admin/load-data/regions-report")
    public void loadRegionsVisits(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        LocalDateTime[] localDateTimes = getLocalDateTime(req.getParameter("startDate"), req.getParameter("endDate"));
        LocalDateTime startDateTime = localDateTimes[0];
        LocalDateTime endDateTime = localDateTimes[1];

        List<Visit> visits = visitService.getAllBetweenDates(startDateTime, endDateTime, 0);
        List<RegionVisits> rvList = getRegionVisits(visits);

        resp.setContentType("json");
        resp.setCharacterEncoding("UTF-8");
        gson = new GsonBuilder().registerTypeAdapter(RegionVisits.class, new RegionVisitsSerializer()).create();
        resp.getWriter().print(gson.toJson(rvList));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @PostMapping("/admin/load-data/visits-report")
    public void loadVisits(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        int regionId = Integer.parseInt(req.getParameter("regionId"));
        int pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        LocalDateTime[] localDateTimes = getLocalDateTime(req.getParameter("startDate"), req.getParameter("endDate"));
        LocalDateTime startDateTime = localDateTimes[0];
        LocalDateTime endDateTime = localDateTimes[1];
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Visit> visits = visitService.getPageBetweenDates(startDateTime, endDateTime, regionId, pageable);
        resp.setContentType("json");
        resp.setCharacterEncoding("UTF-8");
        gson = new GsonBuilder().registerTypeAdapter(Visit.class, new VisitSerializer()).create();
        resp.getWriter().print(gson.toJson(visits));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @PostMapping("/admin/load-data/report-userAllVisits")
    public void loadUserAllVisits(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        Long userId = Long.parseLong(req.getParameter("userId"));
        LocalDateTime[] localDateTimes = getLocalDateTime(req.getParameter("startDate"), req.getParameter("endDate"));
        LocalDateTime startDateTime = localDateTimes[0];
        LocalDateTime endDateTime = localDateTimes[1];

        long count = visitService.getAllByUserBetweenDates(startDateTime, endDateTime, userId).size();
        resp.setContentType("json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(this.gson.toJson(count));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @PostMapping("/admin/load-data/user-visits")
    public void loadUserVisits(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        Long userId = Long.parseLong(req.getParameter("userId"));
        int pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        LocalDateTime[] localDateTimes = getLocalDateTime(req.getParameter("startDate"), req.getParameter("endDate"));
        LocalDateTime startDateTime = localDateTimes[0];
        LocalDateTime endDateTime = localDateTimes[1];
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Visit> visits = visitService.getUserVisitsBetweenDates(startDateTime, endDateTime, userId, pageable);
        resp.setContentType("json");
        resp.setCharacterEncoding("UTF-8");
        gson = new GsonBuilder().registerTypeAdapter(Visit.class, new VisitSerializer()).create();
        resp.getWriter().print(gson.toJson(visits));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @PostMapping("/admin/load-data/user-visits-count")
    public void loadUserVisitsCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        int regionId = Integer.parseInt(req.getParameter("regionId"));
        LocalDateTime[] localDateTimes = getLocalDateTime(req.getParameter("startDate"), req.getParameter("endDate"));
        LocalDateTime startDateTime = localDateTimes[0];
        LocalDateTime endDateTime = localDateTimes[1];

        List<Visit> allVisits = visitService.getAllBetweenDates(startDateTime, endDateTime, regionId);
        List<UserVisits> uvList = new ArrayList<>();

        if(regionId>0){
            uvList = getUserVisitsForRegion(regionId, allVisits, uvList);
        }
        else {
            List<Region> regions = regionService.getAll();
            List<Visit> regionVisits = new ArrayList<>();
            for(Region region: regions){
                regionId = region.getId();
                for(Visit visit: allVisits){
                    if(visit.getRegion().getId() == regionId){
                        regionVisits.add(visit);
                    }
                }
                uvList = getUserVisitsForRegion(regionId, regionVisits, uvList);
            }
        }
        resp.setContentType("json");
        resp.setCharacterEncoding("UTF-8");
        gson = new GsonBuilder().registerTypeAdapter(UserVisits.class, new UserVisitsSerializer()).create();
        resp.getWriter().print(gson.toJson(uvList));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    private List<UserVisits> getUserVisitsForRegion(int regionId, List<Visit> visitsList, List<UserVisits> uvList) {
        int k = visitsList.size();
        for(int i=0; i<k; i++){
            Long userId = visitsList.get(i).getUser().getId();
            int visitsCount = 1;
            for(int j=1; j<k; j++){
                Long nextVisitorId = visitsList.get(j).getUser().getId();
                if(userId == nextVisitorId){
                    visitsCount++;
                    visitsList.remove(j);
                    j--;
                    k--;
                }
            }
            UserVisits userVisits = new UserVisits();
            userVisits.setRegionId(regionId);
            userVisits.setUser(visitsList.get(i).getUser());
            userVisits.setTotal(visitsCount);
            uvList.add(userVisits);
            visitsList.remove(i);
            k--;
            i--;
        }
        return uvList;
    }

    @PostMapping("/admin/load-data/visits-exportExcel")
    public void visitsExportExcel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        int regionId = Integer.parseInt(req.getParameter("regionId"));
        LocalDateTime[] localDateTimes = getLocalDateTime(req.getParameter("startDate"), req.getParameter("endDate"));
        LocalDateTime startDateTime = localDateTimes[0];
        LocalDateTime endDateTime = localDateTimes[1];

        DateTimeFormatter rightFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String startDateString = startDateTime.format(rightFormatter);
        String endDateString = endDateTime.format(rightFormatter);

        List<Visit> visits = visitService.getAllBetweenDates(startDateTime, endDateTime, regionId);
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDateString = currentDate.format(formatter);
        String headerKey = "Content-disposition";
        String headerValue = "attachment; filename=visits_" + currentDateString + ".xlsx";
        resp.setContentType("application/octet-stream");
        resp.setHeader(headerKey, headerValue);
        VisitsExcelExporter excelExporter = new VisitsExcelExporter(visits, startDateString, endDateString);
        excelExporter.export(resp);
    }

    @PostMapping("/admin/load-data/regions-exportExcel")
    public void regionsExportExcel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        LocalDateTime[] localDateTimes = getLocalDateTime(req.getParameter("startDate"), req.getParameter("endDate"));
        LocalDateTime startDateTime = localDateTimes[0];
        LocalDateTime endDateTime = localDateTimes[1];

        DateTimeFormatter rightFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String startDateString = startDateTime.format(rightFormatter);
        String endDateString = endDateTime.format(rightFormatter);

        List<Visit> visits = visitService.getAllBetweenDates(startDateTime, endDateTime, 0);
        List<RegionVisits> rvList = getRegionVisits(visits);

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDateString = currentDate.format(formatter);
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=regions_" + currentDateString + ".xlsx";
        resp.setContentType("application/octet-stream");
        resp.setHeader(headerKey, headerValue);
        RegionsExcelExporter excelExporter = new RegionsExcelExporter(rvList, startDateString, endDateString);
        excelExporter.export(resp);
    }

    @PostMapping("/admin/load-data/users-visits-exportExcel")
    public void usersVisitsExportExcel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        int regionId = Integer.parseInt(req.getParameter("regionId"));
        LocalDateTime[] localDateTimes = getLocalDateTime(req.getParameter("startDate"), req.getParameter("endDate"));
        LocalDateTime startDateTime = localDateTimes[0];
        LocalDateTime endDateTime = localDateTimes[1];

        DateTimeFormatter rightFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String startDateString = startDateTime.format(rightFormatter);
        String endDateString = endDateTime.format(rightFormatter);

        List<Visit> allVisits = visitService.getAllBetweenDates(startDateTime, endDateTime, regionId);
        List<UserVisits> uvList = new ArrayList<>();

        if(regionId>0){
            uvList = getUserVisitsForRegion(regionId, allVisits, uvList);
        }
        else {
            List<Region> regions = regionService.getAll();
            List<Visit> regionVisits = new ArrayList<>();
            for(Region region: regions){
                regionId = region.getId();
                for(Visit visit: allVisits){
                    if(visit.getRegion().getId() == regionId){
                        regionVisits.add(visit);
                    }
                }
                uvList = getUserVisitsForRegion(regionId, regionVisits, uvList);
            }
        }

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDateString = currentDate.format(formatter);
        String headerKey = "Content-disposition";
        String headerValue = "attachment; filename=users_visits_" + currentDateString + ".xlsx";
        resp.setContentType("application/octet-stream");
        resp.setHeader(headerKey, headerValue);
        UsersVisitsExcelExporter excelExporter = new UsersVisitsExcelExporter(uvList, startDateString, endDateString);
        excelExporter.export(resp);
    }

    @PostMapping("/admin/load-data/user-info-exportExcel")
    public void userInfoExportExcel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        Long userId = Long.parseLong(req.getParameter("userId"));
        LocalDateTime[] localDateTimes = getLocalDateTime(req.getParameter("startDate"), req.getParameter("endDate"));
        LocalDateTime startDateTime = localDateTimes[0];
        LocalDateTime endDateTime = localDateTimes[1];

        DateTimeFormatter rightFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String startDateString = startDateTime.format(rightFormatter);
        String endDateString = endDateTime.format(rightFormatter);
        List<Visit> visits = visitService.getAllByUserBetweenDates(startDateTime, endDateTime, userId);
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDateString = currentDate.format(formatter);
        String headerKey = "Content-disposition";
        String headerValue = "attachment; filename=user_info_" + currentDateString + ".xlsx";
        resp.setContentType("application/octet-stream");
        resp.setHeader(headerKey, headerValue);
        UserInfoExcelExporter excelExporter = new UserInfoExcelExporter(visits, startDateString, endDateString);
        excelExporter.export(resp);
    }

    private LocalDateTime[] getLocalDateTime(String fromDate, String untilDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime endDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);;
        LocalDateTime startDateTime;
        if(untilDate.length()>0){
            LocalDate endDate = LocalDate.parse(untilDate, formatter);
            LocalTime endTime = LocalTime.of(23, 59);
            endDateTime = LocalDateTime.of(endDate,endTime);
        }
        if(fromDate.length()>0){
            LocalDate startDate = LocalDate.parse(fromDate, formatter);
            LocalTime startTime = LocalTime.of(0, 0);
            startDateTime = LocalDateTime.of(startDate, startTime);
        } else {
            startDateTime = endDateTime.minusMonths(1);
        }
        LocalDateTime[] localDateTimes = new LocalDateTime[2];
        localDateTimes[0] = startDateTime;
        localDateTimes[1] = endDateTime;
        return localDateTimes;
    }
    private List<RegionVisits> getRegionVisits(List<Visit> visits){
        List<Region> regions = regionService.getAll();
        List<RegionVisits> rvList = new ArrayList<>();
        for(int i=0; i<regions.size(); i++) {
            int regionId = regions.get(i).getId();
            int count = 0;
            for (int j = 0; j < visits.size(); j++) {
                if (visits.get(j).getRegion().getId() == regionId) {
                    count++;
                }
            }
            RegionVisits rv = new RegionVisits();
            rv.setRegionName(regions.get(i).getRegionName());
            rv.setVisitsCount(count);
            rvList.add(rv);
        }
        RegionVisits rv = new RegionVisits();
        rv.setRegionName("Всего по регионам");
        rv.setVisitsCount(visits.size());
        rvList.add(rv);
        return rvList;
    }

}
