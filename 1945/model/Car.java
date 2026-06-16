package model;

public class Car {
    private String plateNumber;
    private int clientId;
    private String model;
    private double dailyRate;
    private boolean available;

    public Car(String plateNumber, int clientId, String model, double dailyRate, boolean available) {
        this.plateNumber = plateNumber;
        this.clientId = clientId;
        this.model = model;
        this.dailyRate = dailyRate;
        this.available = available;
    }

    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }
    public int getClientId() { return clientId; }
    public void setClientId(int clientId) { this.clientId = clientId; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public double getDailyRate() { return dailyRate; }
    public void setDailyRate(double dailyRate) { this.dailyRate = dailyRate; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

@Override
    public String toString() {
        return "Car{plateNumber='" + plateNumber + "', clientId=" + clientId + ", model='" + model + 
               "', dailyRate=" + dailyRate + ", available=" + available + "}";
    }
}
