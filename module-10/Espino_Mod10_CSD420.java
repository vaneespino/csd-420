
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Espino_Mod10_CSD420 {
    private static final String URL = "jdbc:mysql://localhost:3306/databasedb";
    private static final String USER = "student1";
    private static final String PASSWORD = "pass";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {
            
            System.out.println("Connected to database successfully!");
            viewFans(conn);

            System.out.print("\nEnter ID of the fan you want to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.print("Enter new first name: ");
            String firstName = scanner.nextLine();

            System.out.print("Enter new last name: ");
            String lastName = scanner.nextLine();

            System.out.print("Enter new favorite team: ");
            String favoriteTeam = scanner.nextLine();

            updateFan(conn, id, firstName, lastName, favoriteTeam);
            
            System.out.println("\nUpdated Fan List:");
            viewFans(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewFans(Connection conn) throws SQLException {
        String query = "SELECT id, firstname, lastname, favoriteteam FROM fans";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            System.out.println("\nID | First Name | Last Name | Favorite Team");
            System.out.println("-------------------------------------------");
            while (rs.next()) {
                System.out.printf("%d | %s | %s | %s%n",
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("favoriteteam"));
            }
        }
    }

    public static void updateFan(Connection conn, int id, String firstName, String lastName, String favoriteTeam) throws SQLException {
        String query = "UPDATE fans SET firstname = ?, lastname = ?, favoriteteam = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, favoriteTeam);
            pstmt.setInt(4, id);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Fan updated successfully!");
            } else {
                System.out.println("No fan found with ID " + id);
            }
        }
    }
}