package linkedlist;

import java.util.Scanner;

public class MovieManagementSystem {
    static class Node {
        String title, director;
        int year;
        double rating;
        Node prev, next;
        Node(String t, String d, int y, double r) {
            title=t; director=d; year=y; rating=r;
        }
    }
    static class MovieList {
        Node head, tail;
        int size;

        void addBeginning(String t, String d, int y, double r) {
            Node n = new Node(t,d,y,r);
            if (head == null) head=tail=n;
            else { n.next=head; head.prev=n; head=n; }
            size++;
        }
        void addEnd(String t, String d, int y, double r) {
            Node n = new Node(t,d,y,r);
            if (tail == null) head=tail=n;
            else { tail.next=n; n.prev=tail; tail=n; }
            size++;
        }
        void addPosition(String t, String d, int y, double r, int pos) {
            if (pos < 1 || pos > size+1) { System.out.println("Invalid position."); return; }
            if (pos==1) { addBeginning(t,d,y,r); return; }
            if (pos==size+1) { addEnd(t,d,y,r); return; }
            Node cur=head;
            for(int i=1;i<pos;i++) cur=cur.next;
            Node n=new Node(t,d,y,r);
            n.prev=cur.prev; n.next=cur;
            cur.prev.next=n; cur.prev=n; size++;
        }
        void remove(String title) {
            Node cur=head;
            while(cur!=null && !cur.title.equalsIgnoreCase(title)) cur=cur.next;
            if(cur==null) { System.out.println("Movie not found."); return; }
            if(cur.prev!=null) cur.prev.next=cur.next; else head=cur.next;
            if(cur.next!=null) cur.next.prev=cur.prev; else tail=cur.prev;
            size--; System.out.println("Movie removed.");
        }
        void searchDirector(String director) {
            boolean found=false;
            for(Node c=head;c!=null;c=c.next) if(c.director.equalsIgnoreCase(director)) { print(c); found=true; }
            if(!found) System.out.println("No movie found for that director.");
        }
        void searchRating(double rating) {
            boolean found=false;
            for(Node c=head;c!=null;c=c.next) if(Double.compare(c.rating,rating)==0) { print(c); found=true; }
            if(!found) System.out.println("No movie found with that rating.");
        }
        void updateRating(String title,double rating) {
            for(Node c=head;c!=null;c=c.next) if(c.title.equalsIgnoreCase(title)) { c.rating=rating; System.out.println("Rating updated."); return; }
            System.out.println("Movie not found.");
        }
        void forward() { if(head==null) System.out.println("No movies."); for(Node c=head;c!=null;c=c.next) print(c); }
        void reverse() { if(tail==null) System.out.println("No movies."); for(Node c=tail;c!=null;c=c.prev) print(c); }
        static void print(Node n) { System.out.printf("%s | %s | %d | Rating: %.1f%n",n.title,n.director,n.year,n.rating); }
    }
    static void add(Scanner sc, MovieList l, int mode) {
        System.out.print("Movie title: "); String t=sc.nextLine();
        System.out.print("Director: "); String d=sc.nextLine();
        System.out.print("Release year: "); int y=sc.nextInt();
        System.out.print("Rating: "); double r=sc.nextDouble(); sc.nextLine();
        if(mode==1) l.addBeginning(t,d,y,r);
        else if(mode==2) l.addEnd(t,d,y,r);
        else { System.out.print("Position: "); l.addPosition(t,d,y,r,sc.nextInt()); sc.nextLine(); }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in); MovieList l=new MovieList(); int ch;
        do {
            System.out.println("\n1 Add beginning 2 Add end 3 Add position 4 Remove title");
            System.out.println("5 Search director 6 Search rating 7 Forward 8 Reverse 9 Update rating 0 Exit");
            System.out.print("Choice: "); ch=sc.nextInt(); sc.nextLine();
            switch(ch) {
                case 1,2,3 -> add(sc,l,ch);
                case 4 -> { System.out.print("Title: "); l.remove(sc.nextLine()); }
                case 5 -> { System.out.print("Director: "); l.searchDirector(sc.nextLine()); }
                case 6 -> { System.out.print("Rating: "); l.searchRating(sc.nextDouble()); sc.nextLine(); }
                case 7 -> l.forward();
                case 8 -> l.reverse();
                case 9 -> { System.out.print("Title: "); String t=sc.nextLine(); System.out.print("New rating: "); l.updateRating(t,sc.nextDouble()); sc.nextLine(); }
                case 0 -> System.out.println("Program ended.");
                default -> System.out.println("Invalid choice.");
            }
        } while(ch!=0);
    }
}
