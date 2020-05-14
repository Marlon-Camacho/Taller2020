
package Fecha;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Marlon_Camacho
 */
public class ConexionDB1 {
    static final String JDBC_DRIVER = "", DATABASE_URL = "";
    private Connection connection;
    private Statement statement;
    
    public ConexionDB1(){
        try{
            Class.forName(JDBC_DRIVER);
            connection = (Connection) DriverManager.getConnection(DATABASE_URL, "root", "root");
            statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery("");
            StringBuffer results = new StringBuffer();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            for (int i = 1; i <= numberOfColumns; i++) {
                results.append(metaData.getColumnName(i)+"\t");
            }
            results.append("\n");
            while (resultSet.next()){
                for (int i = 1; i <= numberOfColumns; i++) {
                    results.append(resultSet.getObject(i)+"\t");
                }
                results.append("\n");
            }
            System.out.println(results.toString());
        }
        catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null, sqlException.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        catch(ClassNotFoundException classNotFound){
            JOptionPane.showMessageDialog(null, classNotFound.getMessage(), "Driver Not Found", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            try{
                statement.close();
                connection.close();
            }
            catch (SQLException sqlException){
                JOptionPane.showMessageDialog(null, sqlException.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }
    }
    public static void main(String[] args) {
        ConexionDB1 solucion = new ConexionDB1();
    }
}
