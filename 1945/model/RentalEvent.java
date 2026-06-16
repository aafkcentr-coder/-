package model;

public class RentalEvent {
    private int id;
    private String plateNumber;
    private String type;
    private int days;
    private String notes;

public RentalEvent(int id, String plateNumber, String type, int days, String notes) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.type = type;
        this.days = days;
        this.notes = notes;
    }
    
 public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public int getDays() { return days; }
    public void setDays(int days) { this.days = days; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }    

    @Override
    public String toString() {
        return "RentalEvent{id=" + id + ", plateNumber='" + plateNumber + "', type='" + type + 
               "', days=" + days + ", notes='" + notes + "'}";
    }
}
