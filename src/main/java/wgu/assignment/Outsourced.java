package wgu.assignment;

/**
 * This class extends the 'Part.java' class and creates Outsourced parts.
 *
 * @author Leslie Calvin Bomar 3rd
 * @version 1.0
 * @since 2020-01-20
 */
public class Outsourced extends Part{
    /** Stores the name of the company which this part is procured from.*/
    private String companyName;

    /**
     * Creates a new outsourced part.
     * @param id The unique ID for this part.
     * @param name The name/description of this part.
     * @param price The price/cost of this part.
     * @param stock The quantity on hand of this part.
     * @param min The minimum required inventory of this part.
     * @param max The maximum inventory permitted for this part.
     * @param companyName The name of the company from which this part is procured.
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * This method changes the company name associated with this part.
     * @param companyName The name of the company from which this part will be procured.
     */
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    /**
     * This method returns the name of the company which provides this part.
     * @return The name of the company which provides this part.
     */
    public String getCompanyName() { return this.companyName; }
}
