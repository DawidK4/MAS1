import java.io.*;
import java.util.*;

public class Drug implements Serializable {
    private static final List<Drug> extension = new ArrayList<>();
    private String name;
    private Manufacturer manufacturer;
    private List<String> ingredients;
    private String expirationDate;

    private static String warningLabel = "Keep out of reach of children.";

    // 1st constructor
    public Drug(String name, Manufacturer manufacturer, List<String> ingredients, String expirationDate) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (manufacturer == null) {
            throw new IllegalArgumentException("Manufacturer cannot be null.");
        }
        if (ingredients == null || ingredients.isEmpty()) {
            throw new IllegalArgumentException("At least one ingredient is required.");
        }

        this.name = name;
        this.manufacturer = manufacturer;
        this.ingredients = new ArrayList<>(ingredients);
        this.expirationDate = expirationDate;
        extension.add(this);
    }

    // 2nd constructor
    public Drug(String name, Manufacturer manufacturer, List<String> ingredients) {
        this(name, manufacturer, ingredients, null);
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

    public static void saveExtensionToFile(String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(extension);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void loadExtensionFromFile(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            List<Drug> loadedList = (List<Drug>) inputStream.readObject();
            extension.clear();
            extension.addAll(loadedList);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void displayExtension() {
        for (Drug drug : extension) {
            System.out.println(drug);
        }
    }

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

    public static void clearExtension() {
        extension.clear();
    }
}
