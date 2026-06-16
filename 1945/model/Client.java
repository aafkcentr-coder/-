package model;

public class Client {
     private int id;
    private String fullName;
    private String driverLicense;
    
    public Client(int id, String fullName, String driverLicense) {
        this.id = id;
        this.fullName = fullName;
        this.driverLicense = driverLicense;    
}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getDriverLicense() { return driverLicense; }
    public void setDriverLicense(String driverLicense) { this.driverLicense = driverLicense; }

@Override
public String toString(){
    return "Client{id=" + id + ", fullName='" + fullName + "', driverLicense='" + driverLicense + "'}";

}
}
