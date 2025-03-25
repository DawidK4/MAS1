import java.io.Serializable;

public class Manufacturer implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String address;
    private String contactNumber;

    // Optional attribute
    private String website;

    public Manufacturer(String name, String address, String contactNumber, String website) {
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        if (website != null && !website.isEmpty()) {
            if (website.startsWith("http://") || website.startsWith("https://")) {
                this.website = website;
            } else {
                throw new IllegalArgumentException("Website must start with 'http://' or 'https://'");
            }
        } else {
            this.website = null;
        }
    }

    @Override
    public String toString() {
        return "Manufacturer{name='" + name + "', address='" + address + "', contactNumber='" + contactNumber + "', website='" + website + "'}";
    }
}
