import java.time.LocalDateTime;

public class Due {
    String Name;
    String details;
    Integer amount;
    LocalDateTime dateTime;

    public Due(String name, String details, Integer amount, LocalDateTime dateTime) {
        Name = name;
        this.details = details;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    
}
