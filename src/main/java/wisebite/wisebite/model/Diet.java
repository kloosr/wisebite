package wisebite.wisebite.model;

import java.util.HashSet;
import java.util.Set;

public class Diet {
    private Set<Recipe> recipes = new HashSet<>();
    public Diet(Set<Recipe> recipes) {
        this.setRecipes(recipes);
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
}
