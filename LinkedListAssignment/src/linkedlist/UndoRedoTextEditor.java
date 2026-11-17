package linkedlist;

import java.util.Scanner;

public class UndoRedoTextEditor {
    static class Node { String text;Node prev,next;Node(String text){this.text=text;} }
    static class History {
        Node head,tail,current;int size;final int LIMIT=10;
        History(){addState("");}
        void addState(String text){
            if(current!=tail){
                Node remove=current.next;current.next=null;tail=current;
                while(remove!=null){Node next=remove.next;remove.prev=remove.next=null;remove=next;size--;}
            }
            Node n=new Node(text);
            if(tail==null)head=tail=current=n;
            else{tail.next=n;n.prev=tail;tail=current=n;}
            size++;
            if(size>LIMIT){head=head.next;head.prev=null;size--;}
        }
        void undo(){if(current!=null&&current.prev!=null){current=current.prev;System.out.println("Undo successful.");}else System.out.println("Nothing to undo.");}
        void redo(){if(current!=null&&current.next!=null){current=current.next;System.out.println("Redo successful.");}else System.out.println("Nothing to redo.");}
        void show(){System.out.println("Current text: "+(current==null?"":current.text));}
        void history(){for(Node n=head;n!=null;n=n.next)System.out.println((n==current?"-> ":"   ")+n.text);}
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);History h=new History();int ch;
        do{
            System.out.println("\n1 Enter/replace text 2 Undo 3 Redo 4 Show current text 5 Show history 0 Exit");
            System.out.print("Choice: ");ch=sc.nextInt();sc.nextLine();
            switch(ch){
                case 1->{System.out.print("Enter complete text: ");h.addState(sc.nextLine());}
                case 2->h.undo();case 3->h.redo();case 4->h.show();case 5->h.history();
                case 0->System.out.println("Program ended.");default->System.out.println("Invalid choice.");
            }
        }while(ch!=0);
    }
}
