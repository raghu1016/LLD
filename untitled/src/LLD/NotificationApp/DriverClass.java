package LLD.NotificationApp;

import java.util.*;

public class DriverClass {
    public static void main(String[] args) {
        User u = new User("Shiva","shiva16");
        Message m = new Message("Hello shivu");
        SystemUpdate su = new SystemUpdate("M1","New feature update","1");
        Event e = new Event();
        e.addEvent(m);
        e.addEvent(su);
        NotificationSystem ns = new NotificationSystem(u,e);
        ns.sendNotification(Types.email);
        u.displayNotification();

    }
}


class Event {

Map<Message,Boolean> msgs  = new HashMap<>();
Map<SystemUpdate , Boolean > stup = new HashMap<>();

    public void addEvent(Object obj) {
        if (obj instanceof Message) {
            msgs.put((Message) obj, false);  // Add Message to the map
        } else if (obj instanceof SystemUpdate) {
            stup.put((SystemUpdate) obj, false);  // Add SystemUpdate to the map
        } else {
            throw new IllegalArgumentException("Invalid type, only Message or SystemUpdate are allowed.");
        }
    }

    public Map<Message, Boolean> getMsgs() {return msgs;}
    public Map<SystemUpdate, Boolean> getStup() {return stup;}
}

class User{

    String name;
    String userId;
    List<Notification> notify;

    User(String n,String i){
        name = n;
        userId = i;
        notify = new ArrayList<Notification>();
    }

    public void displayNotification(){
        for(Notification n : notify){
            System.out.println(n.id+" "+n.type+" "+n.content);
        }
    }
}

class Notification{
    int id;
    String content;
    String type;
    Notification(String content,String type){
        id = UUID.randomUUID().hashCode();
        this.content = content;
        this.type = type ;
        System.out.println("user has notification "+type+" has "+content);
    }
}

class NotificationSystem {

    Event event;
    User user;
    Types notifyType;

    NotificationSystem(User user,Event event){
        this.event = event;  // Correct assignment
        this.user = user;
    }

    public void sendNotification(Types notifyType) {

        for (Message msg : event.getMsgs().keySet()) {
            Boolean flag = event.msgs.get(msg);
            if (flag == false) {
                user.notify.add(new Notification(msg.message, "message"));
                sendVia(notifyType);
                event.msgs.put(msg, true);
            }
        }
        for (SystemUpdate stm : event.getStup().keySet()) {
            Boolean flag = event.stup.get(stm);
            if (flag == false) {
                user.notify.add(new Notification(stm.info, "systemupdate"));
                sendVia(notifyType);
                event.stup.put(stm, true);
            }
        }
    }

    public void sendVia(Types notifyType) {
        if (notifyType== Types.email) {
            System.out.println("sent via mail");

        }
        if (notifyType== Types.sms) {
            System.out.println("sent via sms");

        }
        if (notifyType== Types.in_app) {
            System.out.println("sent via in-app");

        }
    }

}

    class Message {

        int id;
        String message;

        Message(String m){
            id = UUID.randomUUID().hashCode();
            message = m;

        }

    }

    class SystemUpdate {

        String model;
        String info;
        int version;

        SystemUpdate(String model,String info,String version){
            this.model = model;
            this.info = info;
            this.version = Integer.parseInt(version);
        }

    }


