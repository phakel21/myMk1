package com.Rpg.service.implement;

import com.Rpg.config.exception.location.LocationExistException;
import com.Rpg.config.exception.location.LocationNotFoundException;
import com.Rpg.dto.LocationDTO;
import com.Rpg.entity.Location;
import com.Rpg.repository.LocationRepository;
import com.Rpg.service.ImageService;
import com.Rpg.service.LocationService;
import com.Rpg.validator.location.create.LocationCreateValidator;
import com.Rpg.validator.location.update.LocationUpdateValidator;
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

    private List<LocationCreateValidator> locationCreateValidators;

    private List<LocationUpdateValidator> locationUpdateValidators;

    @Autowired
    public LocationServiceImplement(LocationRepository locationRepository,
                                    ImageService imageService,
                                    List<LocationCreateValidator> locationCreateValidators,
                                    List<LocationUpdateValidator> locationUpdateValidators) {
        this.locationRepository = locationRepository;
        this.imageService = imageService;
        this.locationCreateValidators = locationCreateValidators;
        this.locationUpdateValidators = locationUpdateValidators;
    }

    //CRUD methods work with repository

    private void save(Location location) {
        locationRepository.save(location);
    }

    private Location findOne(String name) {
        return locationRepository.findByName(name);
    }

    private List<Location> findAll() {
        return locationRepository.findAll();
    }

    private void delete(String name) {
        locationRepository.deleteByName(name);
    }

    //mappers

    private Location map(LocationDTO locationDTO) {
        Location location = new Location();
        location.setName(locationDTO.getName());
        location.setImage(locationDTO.getImage());
        return location;
    }

    private LocationDTO map(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setName(location.getName());
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

    //methods for controller

    @Override
    public void create(LocationDTO locationDTO, MultipartFile multipartFile) throws IOException {

        Location location = map(locationDTO);

        checkLocationExist(location.getName());

        for (LocationCreateValidator locationCreateValidator : locationCreateValidators) {
            locationCreateValidator.validate(location);
        }

        String resultFileName = imageService.saveFile(locationsPath, multipartFile);
        location.setImage(resultFileName);

        save(location);
    }

    @Override
    public Location getLocationByName(String name) {
        Optional<Location> optionalLocation = locationRepository.findLocationByName(name);
        if (optionalLocation.isPresent()) {
            return optionalLocation.get();
        }
        throw new LocationNotFoundException("location: " + name + " not found");
    }

    @Override
    public List<LocationDTO> getAll() {
        return map(findAll());
    }

    private void update(Location location) {
        for (LocationUpdateValidator locationUpdateValidator : locationUpdateValidators) {
            locationUpdateValidator.validate(location);
        }
        save(location);
    }

    @Override
    public void updateName(String name, String updateName) {
        checkLocationExist(updateName);
        Location location = findOne(name);
        location.setName(updateName);
        update(location);
    }

    @Override
    public void updateImage(String name, MultipartFile multipartFile) throws IOException {
        Location location = findOne(name);
        imageService.deleteFile(locationsPath, location.getImage());
        location.setImage(imageService.saveFile(locationsPath, multipartFile));
        update(location);
    }

    @Override
    @Transactional
    public void deleteByName(String name) {

        Optional<Location> optionalLocation = locationRepository.findLocationByName(name);

        if (!optionalLocation.isPresent()) {
            throw new LocationNotFoundException("Location not found");
        }

        imageService.deleteFile(locationsPath, findOne(name).getImage());
        delete(name);
    }

    @Override
    public LocationDTO getLocationDTOByName(String name) {
        return map(getLocationByName(name));
    }

    private void checkLocationExist(String name) {
        if (locationRepository.existsHeroByName(name)) {
            throw new LocationExistException("Hero " + name + " is already exist");
        }
    }


//    @Override
//    public void addLocation(String name) {
//        location location = new location(name);
//        if(locationRepository.countByName(name) > 0) throw new LocationExistException("location with this name:" + name+ "already exist");
//        save(location);
//    }
}

