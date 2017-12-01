
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kamenskaya on 15.05.2016.
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userName;
    private String message;
    private Date date;

    public Message(String userName, String message, Date date2){
        this.message = message;
        this.userName = userName;
        this.date = date2;
    }

    public String getName(){
        return userName;
    }

    public String getMessage(){
        return message;
    }

    public Date getDate(){
        return date;
    }

}
