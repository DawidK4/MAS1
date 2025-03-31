import java.io.*;
import java.util.*;

public class Drug extends ObjectPlus implements Serializable {
    private static final List<Drug> extension = new ArrayList<>();
    private String name;
    private Manufacturer manufacturer;
    private List<String> ingredients; // Repeatable attribute
    private String expirationDate; // Derived attribute
    private String description; // Optional attribute

    private static String warningLabel = "Keep out of reach of children."; // Class attribute

    // 1st constructor
    public Drug(String name, Manufacturer manufacturer, List<String> ingredients, String expirationDate, String description) {
        // Verification of required attributes
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (manufacturer == null) {
            throw new IllegalArgumentException("Manufacturer cannot be null.");
        }
        if (ingredients == null || ingredients.isEmpty()) {
            throw new IllegalArgumentException("At least one ingredient is required.");
        }

        if (expirationDate == null || expirationDate.isEmpty()){
            throw new IllegalArgumentException("Expiration date cannot be empty or null!");
        }

        this.name = name;
        this.manufacturer = manufacturer;
        this.ingredients = new ArrayList<>(ingredients);
        this.expirationDate = expirationDate;
        this.description = description;
        extension.add(this);
    }

    // 2nd constructor
    public Drug(String name, Manufacturer manufacturer, List<String> ingredients, String expirationDate) {
        this(name, manufacturer, ingredients, expirationDate, null);
    }

    public String getName() {
        return name;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public List<String> getIngredients() {
        return Collections.unmodifiableList(ingredients);
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void addIngredient(String ingredient) {
        if (ingredient == null || ingredient.isEmpty()) {
            throw new IllegalArgumentException("Ingredient cannot be null or empty.");
        }
        ingredients.add(ingredient);
    }

    public void removeIngredient(String ingredient) {
        if (ingredients.size() == 1) {
            throw new IllegalStateException("Cannot remove the last ingredient.");
        }
        ingredients.remove(ingredient);
    }

    public static String getWarningLabel() {
        return warningLabel;
    }

    public static void setWarningLabel(String warningLabel) {
        if (warningLabel == null || warningLabel.isEmpty()) {
            throw new IllegalArgumentException("Warning label cannot be empty.");
        }
        Drug.warningLabel = warningLabel;
    }

    public static List<Drug> getExtension() {
        return Collections.unmodifiableList(new ArrayList<>(extension));
    }

    public static void removeFromExtension(Drug drug) {
        extension.remove(drug);
    }

    public static void displayExtension() {
        for (Drug drug : extension) {
            System.out.println(drug);
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Overriding
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Drug drug = (Drug) obj;
        return name.equals(drug.name) && manufacturer.equals(drug.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, manufacturer);
    }

    @Override
    public String toString() {
        return "Drug{name='" + name + "', manufacturer=" + manufacturer +
                ", ingredients=" + ingredients + ", expirationDate='" + expirationDate + "'}";
    }

    // Class method
    public static void clearExtension() {
        extension.clear();
    }
}
