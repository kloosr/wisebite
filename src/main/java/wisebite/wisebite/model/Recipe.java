package wisebite.wisebite.model;

public class Recipe {
    private String name;
    private int calorieAmount;
    private String instructions;

    public Recipe(String name, int calorieAmount, String instructions){
        this.name = name;
        this.calorieAmount = calorieAmount;
        this.instructions = instructions;
    }

    public Recipe(String name, int calorieAmount){
        this.name = name;
        this.calorieAmount = calorieAmount;
    }
}
