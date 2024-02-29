package wisebite.wisebite.model;

import java.util.HashSet;
import java.util.Set;

public class Meal {
    private int recipeAmount;
    private Set<Recipe> recipes = new HashSet<>();
    public Meal(int recipeAmount, Set<Recipe> recipes) {
        this.recipeAmount = recipeAmount;
        this.recipes = recipes;
    }

}
