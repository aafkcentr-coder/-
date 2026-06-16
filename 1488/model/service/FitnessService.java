package model.service;

import model.*;
import java.util.*;

public class FitnessService {
    private List<Member> members = new ArrayList<>();
    private List<Membership> memberships = new ArrayList<>();
    private List<Visit> visits = new ArrayList<>();
    private Map<String, Membership> membershipMap = new HashMap<>();
    private int memberIdCounter = 1;
    private int visitIdCounter = 1;
    
    public void addMember(Member member) {
        if (member == null) {
            System.out.println("Ошибка: участник не может быть null");
            return;
        }
        members.add(member);
        System.out.println("Участник добавлен: " + member);
    }
    
    public void createMembership(int memberId, String planType, double fee) {
        if (fee <= 0) {
            System.out.println("Ошибка: стоимость должна быть больше 0");
            return;
        }
        
        Member member = findMemberById(memberId);
        if (member == null) {
            System.out.println("Ошибка: участник не найден");
            return;
        }
        
        String cardNumber = "FIT-" + memberId;
        Membership membership = new Membership(cardNumber, memberId, planType, fee, true);
        memberships.add(membership);
        membershipMap.put(cardNumber, membership);
        System.out.println("Абонемент оформлен: " + membership);
    }
    
    public void recordEntry(String cardNumber) {
        Membership membership = membershipMap.get(cardNumber);
        if (membership == null) {
            System.out.println("Ошибка: абонемент не найден");
            return;
        }
        if (!membership.isActive()) {
            System.out.println("Ошибка: абонемент неактивен");
            return;
        }
        
        Visit visit = new Visit(visitIdCounter++, cardNumber, "ENTRY", 0, "Вход");
        visits.add(visit);
        System.out.println("Вход зафиксирован для карты " + cardNumber);
    }
    
    public void recordExit(String cardNumber) {
        Membership membership = membershipMap.get(cardNumber);
        if (membership == null) {
            System.out.println("Ошибка: абонемент не найден");
            return;
        }
        
        Visit visit = new Visit(visitIdCounter++, cardNumber, "EXIT", 0, "Выход");
        visits.add(visit);
        System.out.println("Выход зафиксирован для карты " + cardNumber);
    }
    
    public void freezeMembership(String cardNumber, String reason) {
        Membership membership = membershipMap.get(cardNumber);
        if (membership == null) {
            System.out.println("Ошибка: абонемент не найден");
            return;
        }
        if (!membership.isActive()) {
            System.out.println("Ошибка: абонемент уже неактивен");
            return;
        }
        
        membership.setActive(false);
        Visit visit = new Visit(visitIdCounter++, cardNumber, "FREEZE", 0, reason);
        visits.add(visit);
        System.out.println("Абонемент заморожен: " + cardNumber + " (" + reason + ")");
    }
    
    public void getMembershipInfo(String cardNumber) {
        Membership membership = membershipMap.get(cardNumber);
        if (membership == null) {
            System.out.println("Ошибка: абонемент не найден");
            return;
        }
        System.out.println(membership);
    }
    
    public void getVisitHistory(String cardNumber) {
        System.out.println("История посещений для карты " + cardNumber + ":");
        boolean found = false;
        for (Visit visit : visits) {
            if (visit.getCardNumber().equals(cardNumber)) {
                System.out.println("  " + visit);
                found = true;
            }
        }
        if (!found) {
            System.out.println("  Нет посещений");
        }
    }
    
    public void getTotalRevenue() {
        double total = 0;
        for (Membership m : memberships) {
            if (m.isActive()) {
                total += m.getMonthlyFee();
            }
        }
        System.out.println("Суммарный доход от активных абонементов: " + total);
    }
    
    public void getMembersWithVisits(int month, int minVisits) {
        System.out.println("Участники с более чем " + minVisits + " посещениями в месяце " + month + ":");
        // Упрощенная версия - считаем все посещения
        Map<Integer, Integer> visitCount = new HashMap<>();
        for (Visit visit : visits) {
            if ("ENTRY".equals(visit.getType())) {
                Membership m = membershipMap.get(visit.getCardNumber());
                if (m != null) {
                    visitCount.put(m.getMemberId(), visitCount.getOrDefault(m.getMemberId(), 0) + 1);
                }
            }
        }
        
        boolean found = false;
        for (Map.Entry<Integer, Integer> entry : visitCount.entrySet()) {
            if (entry.getValue() > minVisits) {
                Member member = findMemberById(entry.getKey());
                if (member != null) {
                    System.out.println("  " + member.getFullName() + " - " + entry.getValue() + " посещений");
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("  Нет участников");
        }
    }
    
    public void getTopVisitor() {
        Map<Integer, Integer> visitCount = new HashMap<>();
        for (Visit visit : visits) {
            if ("ENTRY".equals(visit.getType())) {
                Membership m = membershipMap.get(visit.getCardNumber());
                if (m != null) {
                    visitCount.put(m.getMemberId(), visitCount.getOrDefault(m.getMemberId(), 0) + 1);
                }
            }
        }
        
        int maxVisits = 0;
        int topMemberId = -1;
        for (Map.Entry<Integer, Integer> entry : visitCount.entrySet()) {
            if (entry.getValue() > maxVisits) {
                maxVisits = entry.getValue();
                topMemberId = entry.getKey();
            }
        }
        
        if (topMemberId != -1) {
            Member member = findMemberById(topMemberId);
            System.out.println("Участник с наибольшим количеством посещений: " + 
                              member.getFullName() + " (" + maxVisits + " посещений)");
        } else {
            System.out.println("Нет посещений");
        }
    }
    
    private Member findMemberById(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }
}
