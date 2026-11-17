package linkedlist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TicketReservation {
    static class Node {
        int ticketId,seatNumber;String customer,movie,bookingTime;Node next;
        Node(int id,String c,String m,int seat,String time){ticketId=id;customer=c;movie=m;seatNumber=seat;bookingTime=time;}
    }
    static class TicketList {
        Node tail;int size;
        void add(int id,String customer,String movie,int seat){
            if(findId(id)!=null){System.out.println("Ticket ID already exists.");return;}
            String time=LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            Node n=new Node(id,customer,movie,seat,time);
            if(tail==null){tail=n;n.next=n;}else{n.next=tail.next;tail.next=n;tail=n;}
            size++;System.out.println("Ticket booked.");
        }
        Node findId(int id){
            if(tail==null)return null;Node c=tail.next;
            do{if(c.ticketId==id)return c;c=c.next;}while(c!=tail.next);return null;
        }
        void remove(int id){
            if(tail==null){System.out.println("No bookings.");return;}
            Node prev=tail,c=tail.next;
            do{
                if(c.ticketId==id){
                    if(c==c.next)tail=null;
                    else{prev.next=c.next;if(c==tail)tail=prev;}
                    size--;System.out.println("Ticket cancelled.");return;
                }
                prev=c;c=c.next;
            }while(c!=tail.next);
            System.out.println("Ticket not found.");
        }
        void searchCustomer(String name){search(name,true);}
        void searchMovie(String movie){search(movie,false);}
        void search(String value,boolean byCustomer){
            if(tail==null){System.out.println("No bookings.");return;}
            boolean found=false;Node c=tail.next;
            do{if((byCustomer?c.customer:c.movie).equalsIgnoreCase(value)){print(c);found=true;}c=c.next;}while(c!=tail.next);
            if(!found)System.out.println("No matching ticket.");
        }
        void display(){
            if(tail==null){System.out.println("No booked tickets.");return;}
            Node c=tail.next;do{print(c);c=c.next;}while(c!=tail.next);
        }
        void count(){System.out.println("Total booked tickets: "+size);}
        static void print(Node n){System.out.printf("Ticket: %d | Customer: %s | Movie: %s | Seat: %d | Time: %s%n",n.ticketId,n.customer,n.movie,n.seatNumber,n.bookingTime);}
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);TicketList tickets=new TicketList();int ch;
        do{
            System.out.println("\n1 Book ticket 2 Cancel ticket by ID 3 Display tickets");
            System.out.println("4 Search customer 5 Search movie 6 Count tickets 0 Exit");
            System.out.print("Choice: ");ch=sc.nextInt();sc.nextLine();
            switch(ch){
                case 1->{System.out.print("Ticket ID: ");int id=sc.nextInt();sc.nextLine();System.out.print("Customer name: ");String c=sc.nextLine();System.out.print("Movie name: ");String m=sc.nextLine();System.out.print("Seat number: ");tickets.add(id,c,m,sc.nextInt());}
                case 2->{System.out.print("Ticket ID: ");tickets.remove(sc.nextInt());}
                case 3->tickets.display();
                case 4->{System.out.print("Customer name: ");tickets.searchCustomer(sc.nextLine());}
                case 5->{System.out.print("Movie name: ");tickets.searchMovie(sc.nextLine());}
                case 6->tickets.count();
                case 0->System.out.println("Program ended.");default->System.out.println("Invalid choice.");
            }
        }while(ch!=0);
    }
}
