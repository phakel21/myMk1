package com.Rpg.validator.location.create.implement;

import com.Rpg.config.exception.location.LocationDontHaveNameException;
import com.Rpg.entity.Location;
import com.Rpg.validator.location.create.LocationCreateValidator;
import org.springframework.stereotype.Component;

@Component
public class LocationCreateNameValidator implements LocationCreateValidator {

    @Override
    public void validate(Location location) {
        if(location.getName() == null || location.getName().trim().isEmpty()){
            throw new LocationDontHaveNameException("Location dont have name");
        }

    }
}
