package org.example.cinemabackend.cinema.testdata;

import org.example.cinemabackend.cinema.core.domain.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressTestDataProvider {

    public static List<Address> generateSampleAddresses() {
        List<Address> addresses = new ArrayList<>();

        String[] streets = {"Street 1", "Street 2", "Street 3"};
        String[] cities = {"City 1", "City 2", "City 3"};
        String[] postalCodes = {"Postal Code 1", "Postal Code 2", "Postal Code 3"};
        String[] countries = {"Country 1", "Country 2", "Country 3"};

        for (int i = 0; i < streets.length; i++) {
            addresses.add(new Address(streets[i], cities[i], postalCodes[i], countries[i]));
        }

        return addresses;
    }
}
