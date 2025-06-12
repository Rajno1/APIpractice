package org.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder // it ets you create only the fields you need while ignoring the rest it helps on doing POST and PUT
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)// it will Skip null & empty values from request
@JsonPropertyOrder(alphabetic = false) // it will align the request as per alphabetic order
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    @JsonIgnore // it will ignore sending Address details
    private String role;
    private String email;
    private List<String> jobs ;
    private List<Address> address;
    private List<FavFoods> favFoods ;
}
