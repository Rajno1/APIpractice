package org.utils;

import com.github.javafaker.Faker;
import org.pojo.Address;
import org.pojo.Employee;
import org.pojo.FavFoods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestDataFactory {

    public static Employee employeeData(){
        Faker faker = new Faker();

        List<Address> addressList = new ArrayList<>();
        addressList.add(new Address("Palamaneru", "Chittoor", "Andhra Pradesh"));
        addressList.add(new Address("Hyderabad", "RangaReddy", "Telangana"));


        List<FavFoods> favFoods = new ArrayList<>();
        favFoods.add(new FavFoods("Idle","Biryani", Arrays.asList("chapthi","Milk")));

        return new Employee(faker.number().numberBetween(4,10),
                faker.name().firstName(),
                faker.name().lastName(),
                "Analyst",
                faker.internet().emailAddress(),
                Arrays.asList("Developer","Designer"),addressList,favFoods);
    }
}
