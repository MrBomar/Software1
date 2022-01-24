package wgu.assignment;

/**
 * This class extends the 'Part.java' class, it creates InHouse Parts and their properties.
 *
 * @author Leslie Calvin Bomar 3rd
 * @version 1.0
 * @since 2020-01-20
 */
public class InHouse extends Part {
    /** Stores the ID number of the machine on which this part is manufactured. */
    private int machineId;

    /**
     * This constructor will create a new InHouse part.
     * @param id The unique part identifier.
     * @param name The name of the part.
     * @param price The price of the part.
     * @param stock The quantity on-hand of the part.
     * @param min The minimum required part inventory.
     * @param max The maximum required part inventory.
     * @param machineId The ID number of the machine used to produce this part.
     * */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * This method updates the machine ID
     * @param machineId The id number of the machine used to produce the part.
     */
    public void setMachineId(int machineId) { this.machineId = machineId; }

    /**
     * This method returns the machine ID
     * @return Returns the ID of the machine this part is produced on.
     */
    public int getMachineId() { return this.machineId; }

}
