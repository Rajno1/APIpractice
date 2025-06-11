package org.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String role;
    private String email;
    private List<String> jobs ;
    private List<Address> address;
    private List<FavFoods> favFoods ;
}
