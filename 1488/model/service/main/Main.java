package model.service.main;

import model.*;
import model.service.FitnessService;
import java.util.Scanner;

public class Main {
    private static FitnessService service = new FitnessService();
    private static Scanner scanner = new Scanner(System.in);
    private static int memberIdCounter = 1;
    
    public static void main(String[] args) {
        initTestData();
        
        while (true) {
            printMenu();
            int choice = getIntInput("Выберите пункт: ");
            
            switch (choice) {
                case 1: addMember(); break;
                case 2: createMembership(); break;
                case 3: recordEntry(); break;
                case 4: recordExit(); break;
                case 5: freezeMembership(); break;
                case 6: getMembershipInfo(); break;
                case 7: getVisitHistory(); break;
                case 8: getTotalRevenue(); break;
                case 9: getMembersWithVisits(); break;
                case 10: getTopVisitor(); break;
                case 0: 
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }
    
    private static void printMenu() {
        System.out.println("\n=== СИСТЕМА ФИТНЕС-КЛУБА ===");
        System.out.println("1. Добавить участника");
        System.out.println("2. Оформить абонемент");
        System.out.println("3. Зафиксировать вход");
        System.out.println("4. Зафиксировать выход");
        System.out.println("5. Заморозить абонемент");
        System.out.println("6. Информация об абонементе");
        System.out.println("7. История посещений");
        System.out.println("8. Суммарный доход");
        System.out.println("9. Участники с N посещениями");
        System.out.println("10. Топ участник");
        System.out.println("0. Выход");
    }
    
    private static void addMember() {
        System.out.print("Введите ФИО: ");
        String name = scanner.nextLine();
        System.out.print("Введите телефон: ");
        String phone = scanner.nextLine();
        
        Member member = new Member(memberIdCounter++, name, phone);
        service.addMember(member);
    }
    
    private static void createMembership() {
        int memberId = getIntInput("Введите ID участника: ");
        System.out.print("Введите тип (BASIC/PREMIUM/VIP): ");
        String planType = scanner.nextLine();
        double fee = getDoubleInput("Введите стоимость: ");
        
        service.createMembership(memberId, planType, fee);
    }
    
    private static void recordEntry() {
        System.out.print("Введите номер карты: ");
        String card = scanner.nextLine();
        service.recordEntry(card);
    }
    
    private static void recordExit() {
        System.out.print("Введите номер карты: ");
        String card = scanner.nextLine();
        service.recordExit(card);
    }
    
    private static void freezeMembership() {
        System.out.print("Введите номер карты: ");
        String card = scanner.nextLine();
        System.out.print("Введите причину: ");
        String reason = scanner.nextLine();
        service.freezeMembership(card, reason);
    }
    
    private static void getMembershipInfo() {
        System.out.print("Введите номер карты: ");
        String card = scanner.nextLine();
        service.getMembershipInfo(card);
    }
    
    private static void getVisitHistory() {
        System.out.print("Введите номер карты: ");
        String card = scanner.nextLine();
        service.getVisitHistory(card);
    }
    
    private static void getTotalRevenue() {
        service.getTotalRevenue();
    }
    
    private static void getMembersWithVisits() {
        int month = getIntInput("Введите месяц (1-12): ");
        int minVisits = getIntInput("Минимальное количество посещений: ");
        service.getMembersWithVisits(month, minVisits);
    }
    
    private static void getTopVisitor() {
        service.getTopVisitor();
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
        service.addMember(new Member(memberIdCounter++, "Алексей Смирнов", "+7-900-111-22-33"));
        service.addMember(new Member(memberIdCounter++, "Елена Козлова", "+7-900-444-55-66"));
        service.createMembership(1, "PREMIUM", 100.0);
        service.createMembership(2, "BASIC", 50.0);
        service.recordEntry("FIT-1");
        service.recordEntry("FIT-2");
        service.recordEntry("FIT-1");
        System.out.println("Тестовые данные загружены!");
    }
}
