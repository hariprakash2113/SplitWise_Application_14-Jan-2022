import java.time.LocalDate;

public class Due {
    String Name;
    String details;
    Integer amount;
    LocalDate date;
    
    public Due(String name, String details, Integer amount, LocalDate date) {
        Name = name;
        this.details = details;
        this.amount = amount;
        this.date = date;
    }

    
}
