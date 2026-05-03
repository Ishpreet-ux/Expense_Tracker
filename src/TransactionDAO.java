import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    public void addTransaction(Transaction transaction) {
        String query = "INSERT INTO transactions (title, amount, category, transaction_type, transaction_date, description) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, transaction.getTitle());
            stmt.setDouble(2, transaction.getAmount());
            stmt.setString(3, transaction.getCategory());
            stmt.setString(4, transaction.getTransactionType());
            stmt.setDate(5, new java.sql.Date(transaction.getTransactionDate().getTime()));
            stmt.setString(6, transaction.getDescription());

            stmt.executeUpdate();
            System.out.println("Transaction added successfully!");

        } catch (SQLException e) {
            System.err.println("Error adding transaction: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Transaction transaction = new Transaction(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getDouble("amount"),
                    rs.getString("category"),
                    rs.getString("transaction_type"),
                    rs.getDate("transaction_date"),
                    rs.getString("description")
                );

                transactions.add(transaction);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving transactions: " + e.getMessage());
            e.printStackTrace();
        }

        return transactions;
    }

    public void updateTransaction(Transaction transaction) {
        String query = "UPDATE transactions SET title=?, amount=?, category=?, transaction_type=?, transaction_date=?, description=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, transaction.getTitle());
            stmt.setDouble(2, transaction.getAmount());
            stmt.setString(3, transaction.getCategory());
            stmt.setString(4, transaction.getTransactionType());
            stmt.setDate(5, new java.sql.Date(transaction.getTransactionDate().getTime()));
            stmt.setString(6, transaction.getDescription());
            stmt.setInt(7, transaction.getId());

            stmt.executeUpdate();
            System.out.println("Transaction updated successfully!");

        } catch (SQLException e) {
            System.err.println("Error updating transaction: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteTransaction(int id) {
        String query = "DELETE FROM transactions WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Transaction deleted successfully!");

        } catch (SQLException e) {
            System.err.println("Error deleting transaction: " + e.getMessage());
            e.printStackTrace();
        }
    }
}