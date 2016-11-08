import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

class Message implements Serializable{
    private String name;
    private int id;
    private String message;
    private String[] users;
    private Date time;

    //Конструктор, которым будет пользоваться клиент
    Message(String name, String message, int id){
        this.id = id;
        this.name = name;
        this.message = message;
        this.time = java.util.Calendar.getInstance().getTime();
    }

    //Конструктор, которым будет пользоваться сервер
    Message(String name, String message, int id, String[] users){
        this.id = id;
        this.name = name;
        this.message = message;
        this.time = java.util.Calendar.getInstance().getTime();
        this.users = users;
    }

    public void setOnlineUsers(String[] users) {
        this.users = users;
    }

    String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    String getMessage() {
        return this.message;
    }

    public String[] getUsers() {
        return this.users;
    }

    public String getDate(){
        Time tm = new Time(this.time.getTime());
        return tm.toString();
    }

}
