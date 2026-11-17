package linkedlist;

import java.util.Scanner;

public class TaskScheduler {
    static class Node {
        int id, priority; String name, dueDate; Node next;
        Node(int id,String name,int priority,String dueDate) {
            this.id=id; this.name=name; this.priority=priority; this.dueDate=dueDate;
        }
    }
    static class TaskList {
        Node head, current; int size;
        void addBeginning(int id,String name,int p,String date) {
            Node n=new Node(id,name,p,date);
            if(head==null) { head=n; n.next=n; current=n; }
            else { Node tail=tail(); n.next=head; tail.next=n; head=n; }
            size++;
        }
        void addEnd(int id,String name,int p,String date) {
            Node n=new Node(id,name,p,date);
            if(head==null) { head=n; n.next=n; current=n; }
            else { Node tail=tail(); tail.next=n; n.next=head; }
            size++;
        }
        Node tail() { Node t=head; if(t==null)return null; while(t.next!=head)t=t.next; return t; }
        void addPosition(int id,String name,int p,String date,int pos) {
            if(pos<1||pos>size+1) { System.out.println("Invalid position."); return; }
            if(pos==1) { addBeginning(id,name,p,date); return; }
            if(pos==size+1) { addEnd(id,name,p,date); return; }
            Node prev=head; for(int i=1;i<pos-1;i++)prev=prev.next;
            Node n=new Node(id,name,p,date); n.next=prev.next; prev.next=n; size++;
        }
        void remove(int id) {
            if(head==null) { System.out.println("No tasks."); return; }
            Node prev=tail(), cur=head;
            do {
                if(cur.id==id) {
                    if(cur==head && cur.next==head) { head=current=null; }
                    else {
                        prev.next=cur.next;
                        if(cur==head) head=cur.next;
                        if(cur==current) current=cur.next;
                    }
                    size--; System.out.println("Task removed."); return;
                }
                prev=cur; cur=cur.next;
            } while(cur!=head);
            System.out.println("Task not found.");
        }
        void showCurrentAndMoveNext() {
            if(current==null) { System.out.println("No tasks."); return; }
            print(current); current=current.next;
        }
        void display() {
            if(head==null) { System.out.println("No tasks."); return; }
            Node c=head; do { print(c); c=c.next; } while(c!=head);
        }
        void searchPriority(int p) {
            if(head==null) { System.out.println("No tasks."); return; }
            boolean found=false; Node c=head;
            do { if(c.priority==p) { print(c); found=true; } c=c.next; } while(c!=head);
            if(!found) System.out.println("No task with that priority.");
        }
        void print(Node n) { System.out.printf("ID: %d | %s | Priority: %d | Due: %s%n",n.id,n.name,n.priority,n.dueDate); }
    }
    static void add(Scanner sc,TaskList l,int mode) {
        System.out.print("Task ID: "); int id=sc.nextInt(); sc.nextLine();
        System.out.print("Task name: "); String name=sc.nextLine();
        System.out.print("Priority: "); int p=sc.nextInt(); sc.nextLine();
        System.out.print("Due date: "); String date=sc.nextLine();
        if(mode==1)l.addBeginning(id,name,p,date);
        else if(mode==2)l.addEnd(id,name,p,date);
        else { System.out.print("Position: "); l.addPosition(id,name,p,date,sc.nextInt()); }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in); TaskList l=new TaskList(); int ch;
        do {
            System.out.println("\n1 Add beginning 2 Add end 3 Add position 4 Remove ID");
            System.out.println("5 View current and next 6 Display tasks 7 Search priority 0 Exit");
            System.out.print("Choice: "); ch=sc.nextInt(); sc.nextLine();
            switch(ch) {
                case 1,2,3 -> add(sc,l,ch);
                case 4 -> { System.out.print("Task ID: "); l.remove(sc.nextInt()); }
                case 5 -> l.showCurrentAndMoveNext();
                case 6 -> l.display();
                case 7 -> { System.out.print("Priority: "); l.searchPriority(sc.nextInt()); }
                case 0 -> System.out.println("Program ended.");
                default -> System.out.println("Invalid choice.");
            }
        } while(ch!=0);
    }
}
