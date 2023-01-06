package com.Rpg.service.implement;

import com.Rpg.config.exception.location.LocationExistException;
import com.Rpg.config.exception.location.LocationNotFoundException;
import com.Rpg.entity.Location;
import com.Rpg.repository.LocationRepository;
import com.Rpg.service.ImageService;
import com.Rpg.service.LocationService;
import com.Rpg.validator.location.create.LocationCreateValidator;
import com.Rpg.validator.location.update.LocationUpdateValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LocationServiceImplement implements LocationService {

    private Location currentLocation;

    @Value("${locations}")
    private String locationsPath;

    private final LocationRepository locationRepository;
    private final ImageService imageService;
    private final List<LocationCreateValidator> locationCreateValidators;
    private final List<LocationUpdateValidator> locationUpdateValidators;


//    //mappers
//
//    private Location map(LocationDTO locationDTO) {
//        Location location = new Location();
//        location.setName(locationDTO.getName());
//        location.setImage(locationDTO.getImage());
//        return location;
//    }
//
//    private LocationDTO map(Location location) {
//        LocationDTO locationDTO = new LocationDTO();
//        locationDTO.setName(location.getName());
//        locationDTO.setImage(location.getImage());
//        return locationDTO;
//    }
//
//    private List<LocationDTO> map(List<Location> locations) {
//        List<LocationDTO> locationDTOS = new ArrayList<>();
//        for (Location location : locations) {
//            locationDTOS.add(map(location));
//        }
//        return locationDTOS;
//    }

    //methods for controller

    @Override
    public void create(Location location, MultipartFile multipartFile) throws IOException {

        checkLocationExist(location.getName());

        for (LocationCreateValidator locationCreateValidator : locationCreateValidators) {
            locationCreateValidator.validate(location);
        }

        String resultFileName = imageService.saveFile(locationsPath, multipartFile);
        location.setImage(resultFileName);

        locationRepository.save(location);
    }

    @Override
    public Location getLocationByName(String name) {
        Optional<Location> optionalLocation = locationRepository.findLocationByName(name);
        if (!optionalLocation.isPresent()) {
            throw new LocationNotFoundException("location: " + name + " not found");
        }
        return optionalLocation.get();
    }

    @Override
    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    private void update(Location location) {
        for (LocationUpdateValidator locationUpdateValidator : locationUpdateValidators) {
            locationUpdateValidator.validate(location);
        }
        locationRepository.save(location);
    }

    @Override
    public void updateName(String name, String updateName) {
        checkLocationExist(updateName);
        Location location = locationRepository.findByName(name);
        location.setName(updateName);
        update(location);
    }

    @Override
    public void updateImage(String name, MultipartFile multipartFile) throws IOException {
        Location location = locationRepository.findByName(name);
        imageService.deleteFile(locationsPath, location.getImage());
        location.setImage(imageService.saveFile(locationsPath, multipartFile));
        update(location);
    }

    @Override
    public void deleteByName(String name) {

        Optional<Location> optionalLocation = locationRepository.findLocationByName(name);

        if (!optionalLocation.isPresent()) {
            throw new LocationNotFoundException("Location not found");
        }

        Location location = optionalLocation.get();

        String image = location.getImage();

        imageService.deleteFile(locationsPath, image);

        locationRepository.delete(location);
    }

    private void checkLocationExist(String name) {
        if (locationRepository.existsHeroByName(name)) {
            throw new LocationExistException("Hero " + name + " is already exist");
        }
    }

    @Override
    public void chooseLocation(String name) {
        Optional<Location> locationByName = locationRepository.findLocationByName(name);

        if (!locationByName.isPresent()){
            throw new LocationNotFoundException("Not found");
        }

        currentLocation = locationByName.get();

    }

    @Override
    public Location currentLocation() {
        return currentLocation;
    }
}

