package com.Rpg.validator.location.update.implement;

import com.Rpg.config.exception.location.LocationDontHaveNameException;
import com.Rpg.entity.Location;
import com.Rpg.validator.location.update.LocationUpdateValidator;
import org.springframework.stereotype.Component;

@Component
public class LocationUpdateNameValidator implements LocationUpdateValidator {

    @Override
    public void validate(Location location) {
        if (location.getName() == null || location.getName().trim().isEmpty()) {
            throw new LocationDontHaveNameException("Location dont have name");
        }
    }
}
