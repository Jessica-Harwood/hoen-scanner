package com.skyscanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

import java.util.ArrayList;
import java.util.List;

public class HoenScannerApplication extends Application<HoenScannerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HoenScannerApplication().run(args);
    }

    @Override
    public String getName() {
        return "hoen-scanner";
    }

    @Override
    public void initialize(final Bootstrap<HoenScannerConfiguration> bootstrap) {

    }

    @Override
    public void run(final HoenScannerConfiguration configuration, final Environment environment) throws Exception {
        ObjectMapper objectMapper = environment.getObjectMapper();

        List<SearchResult> hotels = objectMapper.readValue(
                getClass().getResourceAsStream("/hotels.json"),
                new TypeReference<List<SearchResult>>() {}
        );
        hotels.forEach(hotel -> hotel.setKind("hotel"));

        List<SearchResult> rentalCars = objectMapper.readValue(
                getClass().getResourceAsStream("/rental_cars.json"),
                new TypeReference<List<SearchResult>>() {}
        );
        rentalCars.forEach(car -> car.setKind("rental_car"));

        List<SearchResult> searchResults = new ArrayList<>();
        searchResults.addAll(hotels);
        searchResults.addAll(rentalCars);

        environment.jersey().register(new SearchResource(searchResults));
    }
}