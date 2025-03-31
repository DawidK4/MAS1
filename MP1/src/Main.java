import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // Create Manufacturer and Drug objects
        Manufacturer manufacturer = new Manufacturer("Pharma Inc.", "123 Pharma St.", "123-456-7890", "https://pharma.com");
        Drug drug = new Drug("Aspirin", manufacturer, Arrays.asList("Salicylic Acid", "Starch"), "2025-03-31", "Pain reliever");

        // Serialize the objects
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("drug_serialization_test.dat"))) {
            out.writeObject(manufacturer);
            out.writeObject(drug);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize the objects and verify they maintain their state
        Manufacturer deserializedManufacturer = null;
        Drug deserializedDrug = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("drug_serialization_test.dat"))) {
            deserializedManufacturer = (Manufacturer) in.readObject();
            deserializedDrug = (Drug) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Verify the deserialized objects
        if (deserializedManufacturer != null && deserializedDrug != null) {
            assert deserializedManufacturer.getName().equals("Pharma Inc.");
            assert deserializedManufacturer.getAddress().equals("123 Pharma St.");
            assert deserializedManufacturer.getContactNumber().equals("123-456-7890");
            assert deserializedManufacturer.getWebsite().equals("https://pharma.com");

            assert deserializedDrug.getName().equals("Aspirin");
            assert deserializedDrug.getManufacturer().equals(deserializedManufacturer);
            assert deserializedDrug.getIngredients().equals(Arrays.asList("Salicylic Acid", "Starch"));
            assert deserializedDrug.getExpirationDate().equals("2025-03-31");
            assert deserializedDrug.getDescription().equals("Pain reliever");

            System.out.println("Serialization and deserialization verified successfully.");
        } else {
            System.out.println("Serialization or deserialization failed.");
        }
    }
}
