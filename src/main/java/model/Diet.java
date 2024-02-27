package model;

import java.util.ArrayList;
import java.util.List;

public class Diet extends Recipe {
    List<Recipe> recipes= new ArrayList<>();

    public  Diet(String name, int calorieAmount, String instructions){
        super(name, calorieAmount, instructions);
    }
    public Diet (String name, int calrieAmount){
        super(name, calrieAmount);
    }
}
