package kz.kdlolymp.gynecology.service;

import kz.kdlolymp.gynecology.entity.Region;
import kz.kdlolymp.gynecology.entity.User;
import kz.kdlolymp.gynecology.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public boolean saveRegion(Region region){
        regionRepository.save(region);
        return true;
    }

    public List<Region> getAll(){
        return regionRepository.findAll();
    }

    public Region findRegionById(int id){
        return regionRepository.findById(id);
    }
    public Region findRegionByName(String regionName){
        return regionRepository.findByRegionName(regionName);
    }
    public boolean addNewRegion(String regionName, String regionKzName){
       Region regionFromDb = findRegionByName(regionName);
       if(regionFromDb!=null){
           return false;
       } else {
           Region region = new Region();
           region.setRegionName(regionName);
           region.setRegionKzName(regionKzName);
           return saveRegion(region);
       }
    }
    public boolean deleteRegion(int id){
        Region region = regionRepository.findById(id);
        regionRepository.delete(region);
        return true;
    }


}
