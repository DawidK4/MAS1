public class Address extends ObjectPlus{
    private final String street;
    private final String city;
    private final String zipCode;
    private final String country;

    public Address(String street, String city, String zipCode, String country) {
        Manufacturer.validateNotEmpty(street, "Street");
        Manufacturer.validateNotEmpty(city, "City");
        Manufacturer.validateNotEmpty(zipCode, "ZipCode");
        Manufacturer.validateNotEmpty(country, "Country");

        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    @Override
    public String toString() {
        return String.format("Address{street='%s', city='%s', zipCode='%s', country='%s'}",
                street, city, zipCode, country);
    }
}