import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manufacturer extends ObjectPlus {
    private static final List<Manufacturer> extension = new ArrayList<>();
    private String name;
    private Address address;  // Changed to use Address class
    private String contactNumber;
    private String website;

    public Manufacturer(String name, Address address, String contactNumber, String website) {
        validateNotEmpty(name, "Name");
        validateNotEmpty(contactNumber, "Phone number");
        validatePhoneNumber(contactNumber);
        validateNotEmpty(website, "Website");
        validateWebsite(website);

        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.website = website;
        extension.add(this);
    }

    // Static validation method
    public static void validateNotEmpty(String value, String fieldName) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty!");
        }
    }

    // Static validation method
    private static void validatePhoneNumber(String phoneNumber) {
        String regex = "^\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Phone number has to have 9 digits without spaces!");
        }
    }

    // Static validation method
    private static void validateWebsite(String website) {
        if (!(website.startsWith("https://") || website.startsWith("http://"))) {
            throw new IllegalArgumentException("Website has to start with https:// or http://");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateNotEmpty(name, "Name");
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null!");
        }
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        validateNotEmpty(contactNumber, "Phone number");
        validatePhoneNumber(contactNumber);
        this.contactNumber = contactNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        validateNotEmpty(website, "Website");
        validateWebsite(website);
        this.website = website;
    }

    public static List<Manufacturer> getExtension() {
        return Collections.unmodifiableList(new ArrayList<>(extension));
    }

    @Override
    public String toString() {
        return String.format("Manufacturer{name='%s', address='%s', contactNumber='%s', website='%s'}",
                name, address, contactNumber, website);
    }
}
