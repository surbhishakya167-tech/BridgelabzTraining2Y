package linkedlist;

import java.util.Scanner;

public class InventoryManagement {
    static class Node {
        String name; int id, quantity; double price; Node next;
        Node(String n,int id,int q,double p) { name=n; this.id=id; quantity=q; price=p; }
    }
    static class Inventory {
        Node head;
        void addBeginning(String n,int id,int q,double p) { Node x=new Node(n,id,q,p); x.next=head; head=x; }
        void addEnd(String n,int id,int q,double p) {
            Node x=new Node(n,id,q,p); if(head==null){head=x;return;}
            Node t=head; while(t.next!=null)t=t.next; t.next=x;
        }
        void addPosition(String n,int id,int q,double p,int pos) {
            if(pos<1){System.out.println("Invalid position.");return;}
            if(pos==1){addBeginning(n,id,q,p);return;}
            Node t=head; for(int i=1;i<pos-1&&t!=null;i++)t=t.next;
            if(t==null){System.out.println("Position out of range.");return;}
            Node x=new Node(n,id,q,p); x.next=t.next;t.next=x;
        }
        void remove(int id) {
            if(head==null){System.out.println("Inventory empty.");return;}
            if(head.id==id){head=head.next;System.out.println("Removed.");return;}
            Node t=head;while(t.next!=null&&t.next.id!=id)t=t.next;
            if(t.next==null)System.out.println("Item not found.");
            else{t.next=t.next.next;System.out.println("Removed.");}
        }
        void updateQuantity(int id,int q) {
            Node t=findId(id);if(t==null)System.out.println("Item not found.");
            else{t.quantity=q;System.out.println("Quantity updated.");}
        }
        Node findId(int id){for(Node t=head;t!=null;t=t.next)if(t.id==id)return t;return null;}
        void searchId(int id){Node t=findId(id);if(t==null)System.out.println("Not found.");else print(t);}
        void searchName(String name){boolean f=false;for(Node t=head;t!=null;t=t.next)if(t.name.equalsIgnoreCase(name)){print(t);f=true;}if(!f)System.out.println("Not found.");}
        void totalValue(){double sum=0;for(Node t=head;t!=null;t=t.next)sum+=t.price*t.quantity;System.out.printf("Total inventory value: %.2f%n",sum);}
        void display(){if(head==null)System.out.println("Inventory empty.");for(Node t=head;t!=null;t=t.next)print(t);}
        static void print(Node t){System.out.printf("%s | ID: %d | Quantity: %d | Price: %.2f%n",t.name,t.id,t.quantity,t.price);}

        // Merge sort rearranges nodes by name or price.
        Node mergeSort(Node h,boolean byName,boolean ascending) {
            if(h==null||h.next==null)return h;
            Node slow=h,fast=h.next;
            while(fast!=null&&fast.next!=null){slow=slow.next;fast=fast.next.next;}
            Node right=slow.next;slow.next=null;
            return merge(mergeSort(h,byName,ascending),mergeSort(right,byName,ascending),byName,ascending);
        }
        Node merge(Node a,Node b,boolean byName,boolean ascending) {
            Node dummy=new Node("",0,0,0),tail=dummy;
            while(a!=null&&b!=null) {
                int cmp=byName?a.name.compareToIgnoreCase(b.name):Double.compare(a.price,b.price);
                if(!ascending)cmp=-cmp;
                if(cmp<=0){tail.next=a;a=a.next;}else{tail.next=b;b=b.next;}
                tail=tail.next;
            }
            tail.next=(a!=null)?a:b;return dummy.next;
        }
        void sort(boolean byName,boolean ascending){head=mergeSort(head,byName,ascending);System.out.println("Inventory sorted.");}
    }
    static void add(Scanner sc,Inventory l,int mode){
        System.out.print("Item name: ");String n=sc.nextLine();
        System.out.print("Item ID: ");int id=sc.nextInt();
        System.out.print("Quantity: ");int q=sc.nextInt();
        System.out.print("Price: ");double p=sc.nextDouble();
        if(mode==1)l.addBeginning(n,id,q,p);else if(mode==2)l.addEnd(n,id,q,p);
        else{System.out.print("Position: ");l.addPosition(n,id,q,p,sc.nextInt());}
        sc.nextLine();
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);Inventory l=new Inventory();int ch;
        do{
            System.out.println("\n1 Add beginning 2 Add end 3 Add position 4 Remove ID 5 Update quantity");
            System.out.println("6 Search ID 7 Search name 8 Display 9 Total value 10 Sort name 11 Sort price 0 Exit");
            System.out.print("Choice: ");ch=sc.nextInt();sc.nextLine();
            switch(ch){
                case 1,2,3->add(sc,l,ch);
                case 4->{System.out.print("ID: ");l.remove(sc.nextInt());}
                case 5->{System.out.print("ID: ");int id=sc.nextInt();System.out.print("New quantity: ");l.updateQuantity(id,sc.nextInt());}
                case 6->{System.out.print("ID: ");l.searchId(sc.nextInt());}
                case 7->{System.out.print("Name: ");l.searchName(sc.nextLine());}
                case 8->l.display();
                case 9->l.totalValue();
                case 10,11->{System.out.print("1 Ascending, 2 Descending: ");int order=sc.nextInt();l.sort(ch==10,order==1);l.display();}
                case 0->System.out.println("Program ended.");
                default->System.out.println("Invalid choice.");
            }
        }while(ch!=0);
    }
}
