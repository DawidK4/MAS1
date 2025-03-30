import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Creating a Manufacturer
        Manufacturer manufacturer = new Manufacturer("Pfizer", "New York, USA", "123-456-7890", "https://www.pfizer.com");
        System.out.println("Created Manufacturer: " + manufacturer);

        // Creating a Drug
        List<String> ingredients = Arrays.asList("Paracetamol", "Caffeine");
        Drug drug = new Drug("PainRelief", manufacturer, ingredients, "2026-12-31", "For headache relief");
        System.out.println("Created Drug: " + drug);

        // Testing Getters
        System.out.println("Drug Name: " + drug.getName());
        System.out.println("Drug Manufacturer: " + drug.getManufacturer().getName());
        System.out.println("Drug Ingredients: " + drug.getIngredients());
        System.out.println("Drug Expiration Date: " + drug.getExpirationDate());
        System.out.println("Drug Description: " + drug.getDescription());

        // Testing Adding and Removing Ingredients
        drug.addIngredient("Vitamin C");
        System.out.println("Added Ingredient, New List: " + drug.getIngredients());
        drug.removeIngredient("Caffeine");
        System.out.println("Removed Ingredient, New List: " + drug.getIngredients());

        // Testing Exception Handling for Removing Last Ingredient
        try {
            drug.removeIngredient("Paracetamol");
        } catch (IllegalStateException e) {
            System.out.println("Exception Caught: " + e.getMessage());
        }

        // Testing Static Warning Label
        System.out.println("Default Warning Label: " + Drug.getWarningLabel());
        Drug.setWarningLabel("Do not exceed recommended dosage.");
        System.out.println("Updated Warning Label: " + Drug.getWarningLabel());

        // Testing Save and Load Extension
        Drug.saveExtensionToFile("drugs.dat");
        Drug.clearExtension();
        System.out.println("Extension after clearing: " + Drug.getExtension());
        Drug.loadExtensionFromFile("drugs.dat");
        System.out.println("Extension after loading: " + Drug.getExtension());
        

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Manufacturer");
            System.out.println("2. Add Drug");
            System.out.println("3. Add Ingredient to a Drug");
            System.out.println("4. Display Manufacturers");
            System.out.println("5. Display Drugs");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addManufacturer(scanner);
                    break;
                case 2:
                    addDrug(scanner);
                    break;
                case 3:
                    addIngredient(scanner);
                    break;
                case 4:
                    displayManufacturers();
                    break;
                case 5:
                    displayDrugs();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addManufacturer(Scanner scanner) {
        System.out.print("Enter Manufacturer Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();
        System.out.print("Enter Website: ");
        String website = scanner.nextLine();
        new Manufacturer(name, address, contact, website);
        System.out.println("Manufacturer added successfully!");
    }

    private static void addDrug(Scanner scanner) {
        System.out.print("Enter Drug Name: ");
        String name = scanner.nextLine();
        System.out.println("Choose Manufacturer:");
        List<Manufacturer> manufacturers = Manufacturer.getExtension();
        for (int i = 0; i < manufacturers.size(); i++) {
            System.out.println((i + 1) + ". " + manufacturers.get(i).getName());
        }
        System.out.print("Enter Manufacturer Number: ");
        int manufacturerIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline
        if (manufacturerIndex < 0 || manufacturerIndex >= manufacturers.size()) {
            System.out.println("Invalid manufacturer selection.");
            return;
        }
        Manufacturer manufacturer = manufacturers.get(manufacturerIndex);
        System.out.print("Enter Ingredients (comma-separated): ");
        List<String> ingredients = Arrays.asList(scanner.nextLine().split(","));
        System.out.print("Enter Expiration Date (YYYY-MM-DD): ");
        String expirationDate = scanner.nextLine();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        new Drug(name, manufacturer, ingredients, expirationDate, description);
        System.out.println("Drug added successfully!");
    }

    private static void addIngredient(Scanner scanner) {
        System.out.println("Choose a Drug:");
        List<Drug> drugs = Drug.getExtension();
        for (int i = 0; i < drugs.size(); i++) {
            System.out.println((i + 1) + ". " + drugs.get(i).getName());
        }
        System.out.print("Enter Drug Number: ");
        int drugIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline
        if (drugIndex < 0 || drugIndex >= drugs.size()) {
            System.out.println("Invalid drug selection.");
            return;
        }
        Drug drug = drugs.get(drugIndex);
        System.out.print("Enter New Ingredient: ");
        String ingredient = scanner.nextLine();
        drug.addIngredient(ingredient);
        System.out.println("Ingredient added successfully!");
    }

    private static void displayManufacturers() {
        List<Manufacturer> manufacturers = Manufacturer.getExtension();
        if (manufacturers.isEmpty()) {
            System.out.println("No manufacturers available.");
        } else {
            for (Manufacturer manufacturer : manufacturers) {
                System.out.println(manufacturer);
            }
        }
    }

    private static void displayDrugs() {
        List<Drug> drugs = Drug.getExtension();
        if (drugs.isEmpty()) {
            System.out.println("No drugs available.");
        } else {
            for (Drug drug : drugs) {
                System.out.println(drug);
            }
        }
    }
}
