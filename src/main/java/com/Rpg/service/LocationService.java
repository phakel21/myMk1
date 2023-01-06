package com.Rpg.service;

import com.Rpg.entity.Location;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface LocationService {

    void create(Location location, MultipartFile multipartFile) throws IOException;

    Location getLocationByName(String name);

    List<Location> getAll();

    void updateName(String name, String updateName);

    void updateImage(String name, MultipartFile multipartFile) throws IOException;

    void deleteByName(String name);

    void chooseLocation(String name);

    Location currentLocation();
}
