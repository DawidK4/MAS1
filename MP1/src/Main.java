import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Create manufacturers
        Manufacturer manufacturer1 = new Manufacturer("Pfizer", "New York, USA", "123456789", "https://www.pfizer.com");
        Manufacturer manufacturer2 = new Manufacturer("Moderna", "Cambridge, USA", "987654321", "https://www.modernatx.com");

        // Create drugs
        Drug drug1 = new Drug("Paracetamol", manufacturer1, Arrays.asList("Paracetamol", "Starch", "Magnesium Stearate"), "2026-12-01",100.00f, 10, "Pain reliever");
        Drug drug2 = new Drug("Ibuprofen", manufacturer2, Arrays.asList("Ibuprofen", "Silicon Dioxide", "Cellulose"),"2025-08-15", 123.12f, 10);

        // Display drugs
        System.out.println("Drugs in the system:");
        Drug.displayExtension();

        // Add an ingredient to a drug
        drug2.addIngredient("Titanium Dioxide");

        // Remove an ingredient from a drug
        drug1.removeIngredient("Starch");

        // Display updated drug info
        System.out.println("\nUpdated drug list:");
        Drug.displayExtension();

        // Serialize objects
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("drugs.dat"))) {
            ObjectPlus.writeExtents(oos);
            System.out.println("\nSerialization successful.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Clear current extension to test deserialization
        Drug.clearExtension();
        System.out.println("\nAfter clearing extension:");
        Drug.displayExtension();

        // Deserialize objects
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("drugs.dat"))) {
            ObjectPlus.readExtents(ois);
            System.out.println("\nDeserialization successful.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Reading data from the extension
        try {
            System.out.println("\nContent of the loaded extension: ");
            ObjectPlus.showExtent(Drug.class);
            ObjectPlus.showExtent(Manufacturer.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
