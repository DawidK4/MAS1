import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Create manufacturers
        Manufacturer manufacturer1 = new Manufacturer("Pfizer", "New York, USA", "123-456-789", "https://www.pfizer.com");
        Manufacturer manufacturer2 = new Manufacturer("Moderna", "Cambridge, USA", "987-654-321", "https://www.modernatx.com");

        // Create drugs
        Drug drug1 = new Drug("Paracetamol", manufacturer1, Arrays.asList("Paracetamol", "Starch", "Magnesium Stearate"), "2026-12-01", "Pain reliever");
        Drug drug2 = new Drug("Ibuprofen", manufacturer2, Arrays.asList("Ibuprofen", "Silicon Dioxide", "Cellulose"), "2025-08-15");

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
            Iterable<Drug> drugExtent = ObjectPlus.getExtent(Drug.class);
            Iterable<Manufacturer> manufacturerExtent = ObjectPlus.getExtent(Manufacturer.class);

            System.out.println("\nReading data from the loaded extension: ");
            for (var drug : drugExtent) System.out.println(drug.toString());
            for (var manufacturer : manufacturerExtent) System.out.println(manufacturer.toString());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
