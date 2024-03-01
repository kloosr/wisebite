package wisebite.wisebite.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Diet {
    // Variables
    private int id;
    private String type;
    private int calorieAmount;
    private List<Recipe> recipes = new ArrayList<>();
    //all args constructor
    public Diet(int id, String type, int calorieAmount, List<Recipe> recipes) {
        this.id = id;
        this.setType(type);
        this.setCalorieAmount(calorieAmount);
        this.setRecipes(recipes);
    }
    //constructor that sets the recipe list to null by default
    public Diet(int id, String type, int calorieAmount) {
        this.id = id;
        this.setType(type);
        this.setCalorieAmount(calorieAmount);
        this.setRecipes(null);
    }
    //default constructor
    public Diet () {
        this(0, "default", 0, null);
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCalorieAmount() {
        return calorieAmount;
    }

    public void setCalorieAmount(int calorieAmount) {
        this.calorieAmount = calorieAmount;
    }
}
