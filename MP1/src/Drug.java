import java.io.*;
import java.util.*;

public class Drug extends ObjectPlus{
    private static final List<Drug> extension = new ArrayList<>();
    private String name;
    private Manufacturer manufacturer;
    private List<String> ingredients; // Repeatable attribute
    private String expirationDate;
    private float price;
    private int quantity;
    private float entireProductPrice; // Derived attribute
    private String description; // Optional attribute

    private static String warningLabel = "Keep out of reach of children."; // Class attribute

    // 1st constructor
    public Drug(String name, Manufacturer manufacturer, List<String> ingredients, String expirationDate, float price
                , int quantity, String description) {
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

        if (price <= 0){
            throw new IllegalArgumentException("Price cannot be less/equal to 0!");
        }

        if (quantity < 0){
            throw new IllegalArgumentException("The quantity cannot be negative!");
        }

        this.name = name;
        this.manufacturer = manufacturer;
        this.ingredients = new ArrayList<>(ingredients);
        this.expirationDate = expirationDate;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.entireProductPrice = price * quantity;
        extension.add(this);
    }

    // 2nd constructor
    public Drug(String name, Manufacturer manufacturer, List<String> ingredients, String expirationDate,  float price
            , int quantity) {
        this(name, manufacturer, ingredients, expirationDate, price, quantity, null);
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
            throw new IllegalArgumentException("Warning label cannot be null or empty.");
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
        if (description.isEmpty()){
            throw new IllegalArgumentException("Description cannot be empty!");
        }

        this.description = description;
    }

    public void setExpirationDate(String expirationDate) {
        if (expirationDate == null || expirationDate.isEmpty()){
            throw new IllegalArgumentException("Expiration date cannot be null or empty!");
        }

        this.expirationDate = expirationDate;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be null or empty!");
        }

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
