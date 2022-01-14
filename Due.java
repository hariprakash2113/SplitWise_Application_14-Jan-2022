import java.time.LocalDateTime;

public class Due {
    String Name;
    String details;
    Integer amount;
    User duedBy;
    LocalDateTime dateTime;

    public Due(String name, String details, Integer amount, LocalDateTime dateTime,User duedBy) {
        Name = name;
        this.details = details;
        this.amount = amount;
        this.dateTime = dateTime;
        this.duedBy=duedBy;
    }

    
}
