import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Create Manufacturer
        Manufacturer pfizer = new Manufacturer("Pfizer", "New York, USA", "123-456-789", "https://www.pfizer.com");
        Manufacturer moderna = new Manufacturer("Moderna", "Cambridge, USA", "987-654-321", "https://www.moderna.com");

        // Create Drugs
        Drug aspirin = new Drug("Aspirin", pfizer, Arrays.asList("Acetylsalicylic Acid"), "2025-12-31");
        Drug ibuprofen = new Drug("Ibuprofen", moderna, Arrays.asList("Ibuprofen", "Starch"));

        // Display drugs
        System.out.println("Created Drugs:");
        Drug.displayExtension();

        // Add ingredient
        ibuprofen.addIngredient("Gelatin");
        System.out.println("\nAfter adding an ingredient to Ibuprofen:");
        System.out.println(ibuprofen);

        // Remove ingredient
        ibuprofen.removeIngredient("Starch");
        System.out.println("\nAfter removing an ingredient from Ibuprofen:");
        System.out.println(ibuprofen);

        // Save drugs to file
        String filename = "drugs.ser";
        Drug.saveExtensionToFile(filename);
        System.out.println("\nDrugs saved to file.");

        // Clear and reload from file
        Drug.loadExtensionFromFile(filename);
        Drug.clearExtension();
        System.out.println("\nDrugs loaded from file:");
        Drug.displayExtension();

        // Change warning label
        Drug.setWarningLabel("Do not use without prescription.");
        System.out.println("\nUpdated Warning Label: " + Drug.getWarningLabel());
    }
}
