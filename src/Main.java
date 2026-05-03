import java.util.List;

public class Main {
    public static void main(String[] args) {
        TransactionDAO dao = new TransactionDAO();

        List<Transaction> transactions = dao.getAllTransactions();

        for (Transaction t : transactions) {
            System.out.println(
                t.getId() + " | " +
                t.getTitle() + " | " +
                t.getAmount() + " | " +
                t.getCategory() + " | " +
                t.getTransactionType() + " | " +
                t.getTransactionDate() + " | " +
                t.getDescription()
            );
        }
    }
}