package org.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class FavFoods {
    private String brakfast;
    private String lunch;
    private List<String> dinner;
}
