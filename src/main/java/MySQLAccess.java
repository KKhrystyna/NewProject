import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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

            preparedStatement = connect.prepareStatement("insert into  trainee.cashFlow values ( ?, ?, ?)");

            preparedStatement.setDate(1, currentRecord.getDate());
            preparedStatement.setString(2, currentRecord.getProduct());
            preparedStatement.setDouble(3, currentRecord.getCost());

            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("SELECT  datum, product, cost from trainee.cashFlow");
            resultSet = preparedStatement.executeQuery();

        } finally {
            close();
        }
    }

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
