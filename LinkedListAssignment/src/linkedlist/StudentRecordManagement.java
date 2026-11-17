package linkedlist;

import java.util.Scanner;

public class StudentRecordManagement {
    static class Node {
        int rollNo, age;
        String name, grade;
        Node next;
        Node(int rollNo, String name, int age, String grade) {
            this.rollNo = rollNo; this.name = name; this.age = age;
            this.grade = grade; this.next = null;
        }
    }

    static class StudentList {
        Node head;

        void addAtBeginning(int r, String n, int a, String g) {
            Node node = new Node(r, n, a, g);
            node.next = head;
            head = node;
        }

        void addAtEnd(int r, String n, int a, String g) {
            Node node = new Node(r, n, a, g);
            if (head == null) { head = node; return; }
            Node temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = node;
        }

        void addAtPosition(int r, String n, int a, String g, int position) {
            if (position < 1) { System.out.println("Invalid position."); return; }
            if (position == 1) { addAtBeginning(r, n, a, g); return; }
            Node temp = head;
            for (int i = 1; i < position - 1 && temp != null; i++) temp = temp.next;
            if (temp == null) { System.out.println("Position out of range."); return; }
            Node node = new Node(r, n, a, g);
            node.next = temp.next;
            temp.next = node;
        }

        void delete(int roll) {
            if (head == null) { System.out.println("List is empty."); return; }
            if (head.rollNo == roll) { head = head.next; System.out.println("Deleted."); return; }
            Node temp = head;
            while (temp.next != null && temp.next.rollNo != roll) temp = temp.next;
            if (temp.next == null) System.out.println("Student not found.");
            else { temp.next = temp.next.next; System.out.println("Deleted."); }
        }

        Node search(int roll) {
            Node temp = head;
            while (temp != null) {
                if (temp.rollNo == roll) return temp;
                temp = temp.next;
            }
            return null;
        }

        void updateGrade(int roll, String grade) {
            Node student = search(roll);
            if (student == null) System.out.println("Student not found.");
            else { student.grade = grade; System.out.println("Grade updated."); }
        }

        void display() {
            if (head == null) { System.out.println("No student records."); return; }
            Node temp = head;
            while (temp != null) {
                System.out.printf("Roll: %d | Name: %s | Age: %d | Grade: %s%n",
                        temp.rollNo, temp.name, temp.age, temp.grade);
                temp = temp.next;
            }
        }
    }

    static void addStudent(Scanner sc, StudentList list, int mode) {
        System.out.print("Roll number: "); int roll = sc.nextInt(); sc.nextLine();
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Age: "); int age = sc.nextInt(); sc.nextLine();
        System.out.print("Grade: "); String grade = sc.nextLine();
        if (mode == 1) list.addAtBeginning(roll, name, age, grade);
        else if (mode == 2) list.addAtEnd(roll, name, age, grade);
        else {
            System.out.print("Position (starts at 1): "); int pos = sc.nextInt();
            list.addAtPosition(roll, name, age, grade, pos);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentList list = new StudentList();
        int choice;
        do {
            System.out.println("\n1 Add beginning  2 Add end  3 Add position  4 Delete by roll");
            System.out.println("5 Search by roll  6 Display all  7 Update grade  0 Exit");
            System.out.print("Choice: "); choice = sc.nextInt(); sc.nextLine();
            switch (choice) {
                case 1, 2, 3 -> addStudent(sc, list, choice);
                case 4 -> { System.out.print("Roll number: "); list.delete(sc.nextInt()); }
                case 5 -> {
                    System.out.print("Roll number: "); Node s = list.search(sc.nextInt());
                    if (s == null) System.out.println("Student not found.");
                    else System.out.printf("%d | %s | %d | %s%n", s.rollNo, s.name, s.age, s.grade);
                }
                case 6 -> list.display();
                case 7 -> {
                    System.out.print("Roll number: "); int r = sc.nextInt(); sc.nextLine();
                    System.out.print("New grade: "); list.updateGrade(r, sc.nextLine());
                }
                case 0 -> System.out.println("Program ended.");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}
