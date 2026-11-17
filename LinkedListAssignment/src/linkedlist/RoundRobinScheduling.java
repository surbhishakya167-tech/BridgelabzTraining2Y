package linkedlist;

import java.util.*;

public class RoundRobinScheduling {
    static class Process {
        int id, burst, remaining, priority, completion;
        Process next;
        Process(int id,int burst,int priority){this.id=id;this.burst=burst;this.remaining=burst;this.priority=priority;}
    }
    static class ProcessQueue {
        Process tail; int size;
        void add(int id,int burst,int priority){
            if(burst<=0){System.out.println("Burst time must be positive.");return;}
            Process p=new Process(id,burst,priority);
            if(tail==null){tail=p;p.next=p;}else{p.next=tail.next;tail.next=p;tail=p;}size++;
        }
        void display(){
            if(tail==null){System.out.println("No active processes.");return;}
            Process c=tail.next;do{System.out.printf("P%d (remaining=%d, priority=%d) -> ",c.id,c.remaining,c.priority);c=c.next;}while(c!=tail.next);
            System.out.println("(back to first)");
        }
        void simulate(int quantum){
            if(quantum<=0){System.out.println("Time quantum must be positive.");return;}
            if(tail==null){System.out.println("No processes.");return;}
            List<Process> all=new ArrayList<>();Process c=tail.next;
            do{all.add(c);c=c.next;}while(c!=tail.next);
            int time=0,completed=0;Process prev=tail,current=tail.next;
            System.out.println("\nScheduling order:");
            while(tail!=null){
                int used=Math.min(quantum,current.remaining);
                System.out.printf("P%d runs for %d units%n",current.id,used);
                current.remaining-=used;time+=used;
                if(current.remaining==0){
                    current.completion=time;completed++;
                    System.out.printf("P%d completed at time %d%n",current.id,time);
                    if(current==current.next){tail=null;break;}
                    prev.next=current.next;
                    if(current==tail)tail=prev;
                    current=prev.next;size--;
                }else{
                    prev=current;current=current.next;
                }
                System.out.print("Active queue: ");display();
            }
            double totalWait=0,totalTurn=0;
            for(Process p:all){int tat=p.completion;int wt=tat-p.burst;totalTurn+=tat;totalWait+=wt;
                System.out.printf("P%d: Waiting=%d, Turnaround=%d%n",p.id,wt,tat);}
            System.out.printf("Average waiting time: %.2f%n",totalWait/all.size());
            System.out.printf("Average turnaround time: %.2f%n",totalTurn/all.size());
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);ProcessQueue q=new ProcessQueue();int ch;
        do{
            System.out.println("\n1 Add process 2 Display queue 3 Run Round Robin 0 Exit");
            System.out.print("Choice: ");ch=sc.nextInt();
            switch(ch){
                case 1->{System.out.print("Process ID: ");int id=sc.nextInt();System.out.print("Burst time: ");int b=sc.nextInt();System.out.print("Priority: ");q.add(id,b,sc.nextInt());}
                case 2->q.display();
                case 3->{System.out.print("Time quantum: ");q.simulate(sc.nextInt());}
                case 0->System.out.println("Program ended.");default->System.out.println("Invalid choice.");
            }
        }while(ch!=0);
    }
}
