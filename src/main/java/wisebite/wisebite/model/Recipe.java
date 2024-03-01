package wisebite.wisebite.model;

public class Recipe {
    private String name;
    private String description;
    private int calorieAmount;
    private String instructions;

    public Recipe(String name, String description, int calorieAmount, String instructions){
        this.name = name;
        this.description = description;
        this.calorieAmount = calorieAmount;
        this.instructions = instructions;
    }

    public Recipe(String name, int calorieAmount){
        this.name = name;
        this.calorieAmount = calorieAmount;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCalorieAmount() {
        return calorieAmount;
    }

    public String getInstructions() {
        return instructions;
    }
}
