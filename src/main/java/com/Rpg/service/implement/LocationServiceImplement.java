package com.Rpg.service.implement;

import com.Rpg.config.exception.location.LocationNotFoundException;
import com.Rpg.dto.LocationDTO;
import com.Rpg.entity.Location;
import com.Rpg.repository.LocationRepository;
import com.Rpg.service.ImageService;
import com.Rpg.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@Service
public class LocationServiceImplement implements LocationService {

    @Value("${locations}")
    private String locationsPath;

    private LocationRepository locationRepository;

    private ImageService imageService;

    @Autowired
    public LocationServiceImplement(LocationRepository locationRepository, ImageService imageService) {
        this.locationRepository = locationRepository;
        this.imageService = imageService;
    }

    private Location map(LocationDTO locationDTO) {
        Location location = new Location();
        location.setName(locationDTO.getName());
        location.setMonsters(locationDTO.getMonsters());
        location.setImage(locationDTO.getImage());
        return location;
    }

    private LocationDTO map(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setName(location.getName());
        locationDTO.setMonsters(location.getMonsters());
        locationDTO.setImage(location.getImage());
        return locationDTO;
    }

    private List<LocationDTO> map(List<Location> locations) {
        List<LocationDTO> locationDTOS = new ArrayList<>();
        for (Location location : locations) {
            locationDTOS.add(map(location));
        }
        return locationDTOS;
    }

    @Override
    public void create(LocationDTO locationDTO, MultipartFile multipartFile) throws IOException {
        Location location = map(locationDTO);
        String resultFileName = imageService.saveFile(locationsPath, multipartFile);
        location.setImage(resultFileName);
        save(location);
    }

    private void save(Location location) {
        locationRepository.save(location);
    }

    @Override
    public LocationDTO getByName(String name) {
        return map(findOne(name));
    }

    private Location findOne(String name) {
        return locationRepository.findByName(name);
    }

    @Override
    public List<LocationDTO> getAll() {
        return map(findAll());
    }

    private List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        if (imageService.deleteFile(locationsPath, findOne(name).getImage()))
            locationRepository.deleteByName(name);
    }

    @Override
    public Location get(String name) {
        return findOne(name);
    }

    @Override
    public void updateName(String name, String updateName) {
        Location location = findOne(name);
        location.setName(updateName);
        save(location);
    }

    @Override
    public void updateImage(String name, MultipartFile multipartFile) throws IOException {
        Location location = findOne(name);
        if (location.getImage() != null)
            imageService.deleteFile(locationsPath, location.getImage());
        location.setImage(imageService.saveFile(locationsPath, multipartFile));
        save(location);
    }

    @Override
    public LocationDTO getOne(String name){
        Optional<Location> optionalLocation = locationRepository.findLocationByName(name);
        if(optionalLocation.isPresent()){
            return map(optionalLocation.get());
        }
        throw new LocationNotFoundException("location: "+ name +" not found");
    }



//    @Override
//    public void addLocation(String name) {
//        location location = new location(name);
//        if(locationRepository.countByName(name) > 0) throw new LocationExistException("location with this name:" + name+ "already exist");
//        save(location);
//    }
}

