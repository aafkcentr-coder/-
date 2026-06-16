package model;

public class Visit {
    private int id;
    private String cardNumber;
    private String type;
    private int duration;
    private String notes;
    
    public Visit(int id, String cardNumber, String type, int duration, String notes) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.type = type;
        this.duration = duration;
        this.notes = notes;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    @Override
    public String toString() {
        return "Visit{id=" + id + ", cardNumber='" + cardNumber + "', type='" + type + 
               "', duration=" + duration + ", notes='" + notes + "'}";
    }
}
