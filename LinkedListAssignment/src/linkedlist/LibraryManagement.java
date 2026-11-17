package linkedlist;

import java.util.Scanner;

public class LibraryManagement {
    static class Node {
        String title,author,genre; int bookId; boolean available; Node prev,next;
        Node(String t,String a,String g,int id,boolean av){title=t;author=a;genre=g;bookId=id;available=av;}
    }
    static class BookList {
        Node head,tail;int size;
        void addBeginning(String t,String a,String g,int id,boolean av){
            Node n=new Node(t,a,g,id,av);if(head==null)head=tail=n;
            else{n.next=head;head.prev=n;head=n;}size++;
        }
        void addEnd(String t,String a,String g,int id,boolean av){
            Node n=new Node(t,a,g,id,av);if(tail==null)head=tail=n;
            else{tail.next=n;n.prev=tail;tail=n;}size++;
        }
        void addPosition(String t,String a,String g,int id,boolean av,int p){
            if(p<1||p>size+1){System.out.println("Invalid position.");return;}
            if(p==1){addBeginning(t,a,g,id,av);return;}if(p==size+1){addEnd(t,a,g,id,av);return;}
            Node c=head;for(int i=1;i<p;i++)c=c.next;
            Node n=new Node(t,a,g,id,av);n.prev=c.prev;n.next=c;c.prev.next=n;c.prev=n;size++;
        }
        void remove(int id){
            Node c=head;while(c!=null&&c.bookId!=id)c=c.next;
            if(c==null){System.out.println("Book not found.");return;}
            if(c.prev!=null)c.prev.next=c.next;else head=c.next;
            if(c.next!=null)c.next.prev=c.prev;else tail=c.prev;
            size--;System.out.println("Book removed.");
        }
        void searchTitle(String s){boolean f=false;for(Node c=head;c!=null;c=c.next)if(c.title.equalsIgnoreCase(s)){print(c);f=true;}if(!f)System.out.println("No matching book.");}
        void searchAuthor(String s){boolean f=false;for(Node c=head;c!=null;c=c.next)if(c.author.equalsIgnoreCase(s)){print(c);f=true;}if(!f)System.out.println("No matching book.");}
        void updateAvailability(int id,boolean av){for(Node c=head;c!=null;c=c.next)if(c.bookId==id){c.available=av;System.out.println("Availability updated.");return;}System.out.println("Book not found.");}
        void forward(){if(head==null)System.out.println("No books.");for(Node c=head;c!=null;c=c.next)print(c);}
        void reverse(){if(tail==null)System.out.println("No books.");for(Node c=tail;c!=null;c=c.prev)print(c);}
        void count(){System.out.println("Total books: "+size);}
        static void print(Node n){System.out.printf("ID: %d | %s | %s | %s | %s%n",n.bookId,n.title,n.author,n.genre,n.available?"Available":"Issued");}
    }
    static void add(Scanner sc,BookList l,int mode){
        System.out.print("Title: ");String t=sc.nextLine();System.out.print("Author: ");String a=sc.nextLine();
        System.out.print("Genre: ");String g=sc.nextLine();System.out.print("Book ID: ");int id=sc.nextInt();
        System.out.print("Available? (true/false): ");boolean av=sc.nextBoolean();
        if(mode==1)l.addBeginning(t,a,g,id,av);else if(mode==2)l.addEnd(t,a,g,id,av);
        else{System.out.print("Position: ");l.addPosition(t,a,g,id,av,sc.nextInt());}sc.nextLine();
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);BookList l=new BookList();int ch;
        do{
            System.out.println("\n1 Add beginning 2 Add end 3 Add position 4 Remove ID 5 Search title");
            System.out.println("6 Search author 7 Update availability 8 Forward 9 Reverse 10 Count 0 Exit");
            System.out.print("Choice: ");ch=sc.nextInt();sc.nextLine();
            switch(ch){
                case 1,2,3->add(sc,l,ch);
                case 4->{System.out.print("Book ID: ");l.remove(sc.nextInt());}
                case 5->{System.out.print("Title: ");l.searchTitle(sc.nextLine());}
                case 6->{System.out.print("Author: ");l.searchAuthor(sc.nextLine());}
                case 7->{System.out.print("Book ID: ");int id=sc.nextInt();System.out.print("Available? (true/false): ");l.updateAvailability(id,sc.nextBoolean());}
                case 8->l.forward();case 9->l.reverse();case 10->l.count();
                case 0->System.out.println("Program ended.");default->System.out.println("Invalid choice.");
            }
        }while(ch!=0);
    }
}
