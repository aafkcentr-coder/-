package model.service;

import model.*;
import java.util.*;

public class RentalService {
    private List<Client> clients = new ArrayList<>();
    private List<Car> cars = new ArrayList<>();
    private List<RentalEvent> events = new ArrayList<>();
    private Map<String, Car> carMap = new HashMap<>();
    private int clientIdCounter = 1;
    private int eventIdCounter = 1;
    
    public void addClient(Client client) {
        if (client == null) {
            System.out.println("Ошибка: клиент не может быть null");
            return;
        }
        clients.add(client);
        System.out.println("Клиент добавлен: " + client);
    }
    
    public void addCar(String plateNumber, String model, double dailyRate) {
        if (plateNumber == null || plateNumber.trim().isEmpty()) {
            System.out.println("Ошибка: номер не может быть пустым");
            return;
        }
        if (dailyRate <= 0) {
            System.out.println("Ошибка: стоимость должна быть больше 0");
            return;
        }
        Car car = new Car(plateNumber, -1, model, dailyRate, true);
        cars.add(car);
        carMap.put(plateNumber, car);
        System.out.println("Автомобиль добавлен: " + car);
    }
    
    public void rentCar(String plateNumber, int clientId, int days) {
        Car car = carMap.get(plateNumber);
        if (car == null) {
            System.out.println("Ошибка: автомобиль не найден");
            return;
        }
        if (!car.isAvailable()) {
            System.out.println("Ошибка: автомобиль уже арендован");
            return;
        }
        Client client = findClientById(clientId);
        if (client == null) {
            System.out.println("Ошибка: клиент не найден");
            return;
        }
        if (days <= 0) {
            System.out.println("Ошибка: количество дней должно быть положительным");
            return;
        }
        
        car.setAvailable(false);
        car.setClientId(clientId);
        
        RentalEvent event = new RentalEvent(eventIdCounter++, plateNumber, "RENT", days, 
                                          "Аренда клиентом " + client.getFullName());
        events.add(event);
        
        System.out.println("Автомобиль " + plateNumber + " арендован клиентом " + 
                          client.getFullName() + " на " + days + " дней");
        System.out.println("Стоимость: " + (days * car.getDailyRate()));
    }
    
    public void returnCar(String plateNumber) {
        Car car = carMap.get(plateNumber);
        if (car == null) {
            System.out.println("Ошибка: автомобиль не найден");
            return;
        }
        if (car.isAvailable()) {
            System.out.println("Ошибка: автомобиль не был арендован");
            return;
        }
        
        car.setAvailable(true);
        car.setClientId(-1);
        
        RentalEvent event = new RentalEvent(eventIdCounter++, plateNumber, "RETURN", 0, 
                                          "Возврат автомобиля");
        events.add(event);
        
        System.out.println("Автомобиль " + plateNumber + " возвращен");
    }
    
    public void reportDamage(String plateNumber, String notes) {
        Car car = carMap.get(plateNumber);
        if (car == null) {
            System.out.println("Ошибка: автомобиль не найден");
            return;
        }
        
        RentalEvent event = new RentalEvent(eventIdCounter++, plateNumber, "DAMAGE", 0, notes);
        events.add(event);
        
        System.out.println("Повреждение зафиксировано для автомобиля " + plateNumber);
    }
    
    public void getClientHistory(int clientId) {
        Client client = findClientById(clientId);
        if (client == null) {
            System.out.println("Ошибка: клиент не найден");
            return;
        }
        
        System.out.println("История клиента " + client.getFullName() + ":");
        boolean found = false;
        for (RentalEvent event : events) {
            Car car = carMap.get(event.getPlateNumber());
            if (car != null && car.getClientId() == clientId) {
                System.out.println("  " + event);
                found = true;
            }
        }
        if (!found) {
            System.out.println("  Нет событий для этого клиента");
        }
    }
    
    public void getCarHistory(String plateNumber) {
        Car car = carMap.get(plateNumber);
        if (car == null) {
            System.out.println("Ошибка: автомобиль не найден");
            return;
        }
        
        System.out.println("История автомобиля " + plateNumber + ":");
        boolean found = false;
        for (RentalEvent event : events) {
            if (event.getPlateNumber().equals(plateNumber)) {
                System.out.println("  " + event);
                found = true;
            }
        }
        if (!found) {
            System.out.println("  Нет событий для этого автомобиля");
        }
    }
    
    public void showAvailableCars() {
        System.out.println("Доступные автомобили:");
        boolean found = false;
        for (Car car : cars) {
            if (car.isAvailable()) {
                System.out.println("  " + car);
                found = true;
            }
        }
        if (!found) {
            System.out.println("  Нет доступных автомобилей");
        }
    }
    
    public void calculateRevenue() {
        double total = 0;
        for (RentalEvent event : events) {
            if ("RENT".equals(event.getType())) {
                Car car = carMap.get(event.getPlateNumber());
                if (car != null) {
                    total += event.getDays() * car.getDailyRate();
                }
            }
        }
        System.out.println("Общий доход: " + total);
    }
    
    private Client findClientById(int id) {
        for (Client client : clients) {
            if (client.getId() == id) {
                return client;
            }
        }
        return null;
    }
}