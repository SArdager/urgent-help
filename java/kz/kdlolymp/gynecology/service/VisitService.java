package kz.kdlolymp.gynecology.service;

import kz.kdlolymp.gynecology.entity.Visit;
import kz.kdlolymp.gynecology.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class VisitService {

    @Autowired
    private VisitRepository visitRepository;
    @PersistenceContext
    private EntityManager manager;

    public List<Visit> getAllByUserId(Long userId){
        return visitRepository.findAllByUserId(userId);
    }

    public List<Visit> getAllByRegionId(int regionId){
        return visitRepository.findAllByRegionId(regionId);
    }
    public boolean addVisit(Visit visit){
        visitRepository.save(visit);
        return true;
    }
    public List<Visit> getAllBetweenDates(LocalDateTime fromDate, LocalDateTime untilDate, int regionId){
        Query query;
        List<Visit> visits = new ArrayList<>();
        try {
            if(regionId>0){
                query = manager.createQuery("SELECT v FROM Visit v LEFT JOIN User u ON v.user.id = u.id LEFT JOIN Region r ON v.region.id = r.id " +
                        "WHERE v.visitDate BETWEEN :paramFrom AND :paramUntil AND v.region.id = :paramRegion ORDER BY v.id DESC",
                        Visit.class).setParameter("paramFrom", fromDate).setParameter("paramUntil", untilDate).setParameter("paramRegion", regionId);

            } else {
                query = manager.createQuery("SELECT v FROM Visit v LEFT JOIN User u ON v.user.id = u.id LEFT JOIN Region r ON v.region.id = r.id " +
                        "WHERE v.visitDate BETWEEN :paramFrom AND :paramUntil ORDER BY v.id DESC",
                        Visit.class).setParameter("paramFrom", fromDate).setParameter("paramUntil", untilDate);
            }
            visits = query.setMaxResults(500).getResultList();
        } catch (NoResultException ex){}
        return visits;
    }

    public List<Visit> getPageBetweenDates(LocalDateTime fromDate, LocalDateTime untilDate, int regionId,  Pageable pageable){
        Query query;
        List<Visit> visits = new ArrayList<>();
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        try {
            if(regionId>0){
                query = manager.createQuery("SELECT v FROM Visit v LEFT JOIN User u ON v.user.id = u.id LEFT JOIN Region r ON v.region.id = r.id " +
                        "WHERE v.visitDate BETWEEN :paramFrom AND :paramUntil AND v.region.id = :paramRegion ORDER BY v.id DESC",
                        Visit.class).setParameter("paramFrom", fromDate).setParameter("paramUntil", untilDate).setParameter("paramRegion", regionId);

            } else {
                query = manager.createQuery("SELECT v FROM Visit v LEFT JOIN User u ON v.user.id = u.id LEFT JOIN Region r ON v.region.id = r.id " +
                        "WHERE v.visitDate BETWEEN :paramFrom AND :paramUntil ORDER BY v.id DESC",
                        Visit.class).setParameter("paramFrom", fromDate).setParameter("paramUntil", untilDate);
            }
            query.setFirstResult(pageNumber * pageSize);
            query.setMaxResults(pageSize);
            visits = query.getResultList();
        } catch (NoResultException ex) {}
        return visits;
    }

    public List<Visit> getAllByUserBetweenDates(LocalDateTime fromDate, LocalDateTime untilDate, Long userId){
        Query query;
        List<Visit> visits = new ArrayList<>();
        try {
            query = manager.createQuery("SELECT v FROM Visit v WHERE v.visitDate BETWEEN :paramFrom AND :paramUntil AND v.user.id = :paramUser ORDER BY v.id DESC",
                    Visit.class).setParameter("paramFrom", fromDate).setParameter("paramUntil", untilDate).setParameter("paramUser", userId);
            visits = query.setMaxResults(200).getResultList();
        } catch (NoResultException ex) {}
        return visits;
    }
    public List<Visit> getUserVisitsBetweenDates(LocalDateTime fromDate, LocalDateTime untilDate, Long userId,  Pageable pageable){
        Query query;
        List<Visit> visits = new ArrayList<>();
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        try {
            query = manager.createQuery("SELECT v FROM Visit v WHERE v.visitDate BETWEEN :paramFrom AND :paramUntil AND v.user.id = :paramUser ORDER BY v.id DESC",
                    Visit.class).setParameter("paramFrom", fromDate).setParameter("paramUntil", untilDate).setParameter("paramUser", userId);
            query.setFirstResult(pageNumber * pageSize);
            query.setMaxResults(pageSize);
            visits = query.getResultList();
        } catch (NoResultException ex) {}
        return visits;
    }

}
