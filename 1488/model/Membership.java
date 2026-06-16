package model;

public class Membership {
    private String cardNumber;
    private int memberId;
    private String planType;
    private double monthlyFee;
    private boolean active;
    
    public Membership(String cardNumber, int memberId, String planType, double monthlyFee, boolean active) {
        this.cardNumber = cardNumber;
        this.memberId = memberId;
        this.planType = planType;
        this.monthlyFee = monthlyFee;
        this.active = active;
    }
    
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }
    public String getPlanType() { return planType; }
    public void setPlanType(String planType) { this.planType = planType; }
    public double getMonthlyFee() { return monthlyFee; }
    public void setMonthlyFee(double monthlyFee) { this.monthlyFee = monthlyFee; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    
    @Override
    public String toString() {
        return "Membership{cardNumber='" + cardNumber + "', memberId=" + memberId + 
               ", planType='" + planType + "', monthlyFee=" + monthlyFee + ", active=" + active + "}";
    }
}