package entities;

public class Vehicle {
    private String registrationId;
    private String color;

    public Vehicle(String registrationId, String color) {
        this.registrationId = registrationId;
        this.color = color;
    }

    public String getRegistrationId() {

        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
