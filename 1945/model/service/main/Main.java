package model.service.main;

import model.*;
import model.service.RentalService;
import java.util.Scanner;

public class Main {
   private static RentalService service = new RentalService();
    private static Scanner scanner = new Scanner(System.in);
    private static int clientIdCounter = 1;
    
    public static void main(String[] args) {
        // Добавляем тестовые данные
        initTestData();
        
        while (true) {
            printMenu();
            int choice = getIntInput("Выберите пункт: ");
            
            switch (choice) {
                case 1: addClient(); break;
                case 2: addCar(); break;
                case 3: rentCar(); break;
                case 4: returnCar(); break;
                case 5: reportDamage(); break;
                case 6: showClientHistory(); break;
                case 7: showCarHistory(); break;
                case 8: showAvailableCars(); break;
                case 9: calculateRevenue(); break;
                case 0: 
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }
    
    private static void printMenu() {
        System.out.println("\n=== СИСТЕМА АРЕНДЫ АВТОМОБИЛЕЙ ===");
        System.out.println("1. Добавить клиента");
        System.out.println("2. Добавить автомобиль");
        System.out.println("3. Арендовать автомобиль");
        System.out.println("4. Вернуть автомобиль");
        System.out.println("5. Зафиксировать повреждение");
        System.out.println("6. История клиента");
        System.out.println("7. История автомобиля");
        System.out.println("8. Показать доступные авто");
        System.out.println("9. Подсчитать доход");
        System.out.println("0. Выход");
    }
    
    private static void addClient() {
        System.out.print("Введите ФИО: ");
        String name = scanner.nextLine();
        System.out.print("Введите номер водительского удостоверения: ");
        String license = scanner.nextLine();
        
        Client client = new Client(clientIdCounter++, name, license);
        service.addClient(client);
    }
    
    private static void addCar() {
        System.out.print("Введите номерной знак: ");
        String plate = scanner.nextLine();
        System.out.print("Введите модель: ");
        String model = scanner.nextLine();
        double rate = getDoubleInput("Введите стоимость за день: ");
        
        service.addCar(plate, model, rate);
    }
    
    private static void rentCar() {
        System.out.print("Введите номерной знак: ");
        String plate = scanner.nextLine();
        int clientId = getIntInput("Введите ID клиента: ");
        int days = getIntInput("Введите количество дней: ");
        
        service.rentCar(plate, clientId, days);
    }
    
    private static void returnCar() {
        System.out.print("Введите номерной знак: ");
        String plate = scanner.nextLine();
        service.returnCar(plate);
    }
    
    private static void reportDamage() {
        System.out.print("Введите номерной знак: ");
        String plate = scanner.nextLine();
        System.out.print("Введите описание повреждения: ");
        String notes = scanner.nextLine();
        service.reportDamage(plate, notes);
    }
    
    private static void showClientHistory() {
        int clientId = getIntInput("Введите ID клиента: ");
        service.getClientHistory(clientId);
    }
    
    private static void showCarHistory() {
        System.out.print("Введите номерной знак: ");
        String plate = scanner.nextLine();
        service.getCarHistory(plate);
    }
    
    private static void showAvailableCars() {
        service.showAvailableCars();
    }
    
    private static void calculateRevenue() {
        service.calculateRevenue();
    }
    
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число");
            }
        }
    }
    
    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число");
            }
        }
    }
    
    private static void initTestData() {
        service.addClient(new Client(clientIdCounter++, "Иван Петров", "AB123456"));
        service.addClient(new Client(clientIdCounter++, "Мария Сидорова", "CD789012"));
        service.addCar("A001AA", "Toyota Camry", 50.0);
        service.addCar("B002BB", "BMW X5", 80.0);
        service.addCar("C003CC", "Mercedes C200", 70.0);
        System.out.println("Тестовые данные загружены!"); 
}
}