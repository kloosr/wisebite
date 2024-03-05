package wisebite.wisebite.repository;

import org.springframework.stereotype.Repository;
import wisebite.wisebite.database.DietDAO;
import wisebite.wisebite.database.RecipeDAO;
import wisebite.wisebite.model.Diet;
import wisebite.wisebite.model.Recipe;

import java.util.List;
import java.util.Set;

@Repository
public class DietRepository {
    private final DietDAO dietDAO;
    private final RecipeDAO recipeDAO;
    public DietRepository(DietDAO dietDAO, RecipeDAO recipeDAO) {
        this.dietDAO = dietDAO;
        this.recipeDAO = recipeDAO;
    }
    // creates a Diet by getting the diet information from the database and filling a list with recipes
    public Diet createDietById(int id) {
        if (dietDAO.getById(id) != null) {
            List<Recipe> recipeList = recipeDAO.getAllByDiet(id);
            Diet diet = dietDAO.getById(id);
            diet.setRecipes(recipeList);
            return diet;
        } else {
            return null;
        }
    }
}
