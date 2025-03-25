import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Drug implements Serializable {
    private static final List<Drug> extension = new ArrayList<>();
    private String name;
    private Manufacturer manufacturer;
    private List<String> ingredients;
    private String expirationDate;

    private static String warningLabel = "Keep out of reach of children.";

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
        for (String ingredient : ingredients) {
            validateIngredient(ingredient);
        }

        this.name = name;
        this.manufacturer = manufacturer;
        this.ingredients = new ArrayList<>(ingredients);
        setExpirationDate(expirationDate);
        extension.add(this);
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

    public void setExpirationDate(String expirationDate) {
        if (expirationDate != null && !expirationDate.isEmpty()) {
            this.expirationDate = expirationDate;
        } else {
            this.expirationDate = null;
        }
    }

    public void addIngredient(String ingredient) {
        validateIngredient(ingredient);
        ingredients.add(ingredient);
    }

    public void removeIngredient(String ingredient) {
        if (ingredients.size() == 1) {
            throw new IllegalStateException("Cannot remove the last ingredient. At least one ingredient must remain.");
        }
        ingredients.remove(ingredient);
    }

    private void validateIngredient(String ingredient) {
        if (ingredient == null || ingredient.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingredient cannot be null or empty.");
        }
    }

    public static void displayExtension() {
        for (Drug drug : extension) {
            System.out.println(drug.getName());
        }
    }

    @Override
    public String toString() {
        return "Drug{name='" + name + "', manufacturer=" + manufacturer +
                ", ingredients=" + ingredients + ", expirationDate='" + expirationDate +
                "', warningLabel='" + warningLabel + "', isExpired=" + isExpired() + "}";
    }

    public static String getWarningLabel() {
        return warningLabel;
    }

    public static void setWarningLabel(String warningLabel) {
        if (warningLabel == null || warningLabel.trim().isEmpty()) {
            throw new IllegalArgumentException("Warning label cannot be null or empty.");
        }
        Drug.warningLabel = warningLabel;
    }

    // Derived attribute: isExpired
    public boolean isExpired() {
        if (expirationDate == null || expirationDate.isEmpty()) {
            return false; // Assume no expiration if not set
        }

        try {
            LocalDate expiry = LocalDate.parse(expirationDate);
            return expiry.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + expirationDate);
            return false;
        }
    }
}
