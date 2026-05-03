import java.util.Date;

public class Transaction {
    private int id;
    private String title;
    private double amount;
    private String category;
    private String transactionType;
    private Date transactionDate;
    private String description;

    // Constructor without id (for insert)
    public Transaction(String title, double amount, String category, String transactionType, Date transactionDate, String description) {
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.description = description;
    }

    // Constructor with id (for fetch)
    public Transaction(int id, String title, double amount, String category, String transactionType, Date transactionDate, String description) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}