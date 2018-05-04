import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

public class MySQLAccess {
    private  Connection connect = null;
    private  Statement statement = null;
    private  PreparedStatement preparedStatement = null;
    private  ResultSet resultSet = null;

    public  void insertInDataBase(Record currentRecord) throws Exception {
        try {

            Class.forName("com.mysql.jdbc.Driver");

            String jdbc = "jdbc:mysql://localhost/trainee?user=root&password=123456KK";
            String jdbcutf8 = "&useUnicode=true&characterEncoding=UTF-8";
            connect = DriverManager.getConnection(jdbc+jdbcutf8);


            statement = connect.createStatement();

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("insert into  trainee.cashFlow values ( ?, ?, ?)");
            // "datum, product, cost from trainee.cashFlow");
            // Parameters start with 1
            preparedStatement.setDate(1, currentRecord.getDate());
            preparedStatement.setString(2, currentRecord.getProduct());
            preparedStatement.setDouble(3, currentRecord.getCost());

            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("SELECT  datum, product, cost from trainee.cashFlow");
            resultSet = preparedStatement.executeQuery();


        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

//    private void writeResultSet(ResultSet resultSet) throws SQLException {
//        // ResultSet is initially before the first data set
//        while (resultSet.next()) {
//            // It is possible to get the columns via name
//            // also possible to get the columns via the column number
//            // which starts at 1
//            // e.g. resultSet.getSTring(2);
//            Date date = resultSet.getDate("datum");
//            String product = resultSet.getString("product");
//            Double cost = resultSet.getDouble("cost");
//            System.out.println("Date: " + date);
//            System.out.println("Product: " + product);
//            System.out.println("Product: " + cost);
//        }
//    }

    private  void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
}
