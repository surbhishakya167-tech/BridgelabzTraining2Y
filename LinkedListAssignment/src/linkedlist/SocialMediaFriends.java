package linkedlist;

import java.util.*;

public class SocialMediaFriends {
    static class User {
        int id,age;String name;Set<Integer> friends=new LinkedHashSet<>();User next;
        User(int id,String name,int age){this.id=id;this.name=name;this.age=age;}
    }
    static class UserList {
        User head;
        User findId(int id){for(User u=head;u!=null;u=u.next)if(u.id==id)return u;return null;}
        User findName(String name){for(User u=head;u!=null;u=u.next)if(u.name.equalsIgnoreCase(name))return u;return null;}
        void addUser(int id,String name,int age){
            if(findId(id)!=null){System.out.println("User ID already exists.");return;}
            User u=new User(id,name,age);if(head==null)head=u;else{User t=head;while(t.next!=null)t=t.next;t.next=u;}
            System.out.println("User added.");
        }
        void addFriend(int a,int b){
            User x=findId(a),y=findId(b);
            if(x==null||y==null){System.out.println("Both users must exist.");return;}
            if(a==b){System.out.println("A user cannot add themselves.");return;}
            x.friends.add(b);y.friends.add(a);System.out.println("Friend connection added.");
        }
        void removeFriend(int a,int b){
            User x=findId(a),y=findId(b);
            if(x==null||y==null){System.out.println("User not found.");return;}
            x.friends.remove(b);y.friends.remove(a);System.out.println("Friend connection removed if it existed.");
        }
        void mutual(int a,int b){
            User x=findId(a),y=findId(b);
            if(x==null||y==null){System.out.println("User not found.");return;}
            Set<Integer> common=new LinkedHashSet<>(x.friends);common.retainAll(y.friends);
            if(common.isEmpty())System.out.println("No mutual friends.");
            else for(int id:common){User f=findId(id);if(f!=null)System.out.println(f.id+" - "+f.name);}
        }
        void displayFriends(int id){
            User u=findId(id);if(u==null){System.out.println("User not found.");return;}
            System.out.println("Friends of "+u.name+":");
            if(u.friends.isEmpty())System.out.println("No friends.");
            for(int fid:u.friends){User f=findId(fid);if(f!=null)System.out.println(f.id+" - "+f.name);}
        }
        void search(String key){
            User u;try{u=findId(Integer.parseInt(key));}catch(NumberFormatException e){u=findName(key);}
            if(u==null)System.out.println("User not found.");
            else System.out.printf("ID: %d | Name: %s | Age: %d | Friends: %d%n",u.id,u.name,u.age,u.friends.size());
        }
        void countAll(){for(User u=head;u!=null;u=u.next)System.out.println(u.name+" ("+u.id+"): "+u.friends.size()+" friends");}
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);UserList users=new UserList();int ch;
        do{
            System.out.println("\n1 Add user 2 Add friend connection 3 Remove friend connection");
            System.out.println("4 Mutual friends 5 Display user's friends 6 Search user 7 Count friends 0 Exit");
            System.out.print("Choice: ");ch=sc.nextInt();sc.nextLine();
            switch(ch){
                case 1->{System.out.print("User ID: ");int id=sc.nextInt();sc.nextLine();System.out.print("Name: ");String n=sc.nextLine();System.out.print("Age: ");users.addUser(id,n,sc.nextInt());}
                case 2,3->{System.out.print("First user ID: ");int a=sc.nextInt();System.out.print("Second user ID: ");int b=sc.nextInt();if(ch==2)users.addFriend(a,b);else users.removeFriend(a,b);}
                case 4->{System.out.print("First user ID: ");int a=sc.nextInt();System.out.print("Second user ID: ");users.mutual(a,sc.nextInt());}
                case 5->{System.out.print("User ID: ");users.displayFriends(sc.nextInt());}
                case 6->{System.out.print("Enter name or ID: ");users.search(sc.nextLine());}
                case 7->users.countAll();
                case 0->System.out.println("Program ended.");default->System.out.println("Invalid choice.");
            }
        }while(ch!=0);
    }
}
