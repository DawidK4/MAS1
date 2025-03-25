import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

        this.name = name;
        this.manufacturer = manufacturer;
        this.ingredients = new ArrayList<>(ingredients);
        setExpirationDate(expirationDate);
        extension.add(this);
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

    public static List<Drug> getExtension() {
        return Collections.unmodifiableList(new ArrayList<>(extension));
    }
}
