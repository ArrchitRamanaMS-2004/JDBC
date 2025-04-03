import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) throws Exception{
        update();
    }
    //simple read from db and display results
    public static void readRecords() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String passWord = "Arrchit@2004";
        String query = "select * from employee";


        Connection con = DriverManager.getConnection(url,userName,passWord);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while(rs.next()) {
            System.out.println("Id is " + rs.getInt(1));
            System.out.println("Name is " + rs.getString(2));
            System.out.println("Salary is " + rs.getInt(3));
        }

        con.close();
    }
    //inserting command example
    public static void insertRecord() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String passWord = "Arrchit@2004";
        String query = "insert into employee values (4,'priya',250000)";


        Connection con = DriverManager.getConnection(url,userName,passWord);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query);
        System.out.println("Number of Rows affected : " + rows);

        con.close();
    }
    //insert using variable
    public static void insertVar() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String passWord = "Arrchit@2004";

        int id = 5;
        String name = "varun";
        int salary = 300000;

        String query = "insert into employee values (" + id + ",'" + name + "'," + salary + ");" ;

        Connection con = DriverManager.getConnection(url,userName,passWord);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query);
        System.out.println("Number of Rows affected : " + rows);

        con.close();
    }
    //insert using prepared statement
    public static void insertUsingPst() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String passWord = "Arrchit@2004";

        int id = 6;
        String name = "Nila";
        int salary = 300000;

        String query = "insert into employee values (?,?,?);" ;

        Connection con = DriverManager.getConnection(url,userName,passWord);

        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1,id);
        pst.setString(2,name);
        pst.setInt(3,salary);
        int rows = pst.executeUpdate();

        System.out.println("Number of records inserted : " + rows);

        con.close();
    }

    //delete
    public static void delete() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String passWord = "Arrchit@2004";

        int id = 5;

        String query = "delete from employee where emp_id = " + id  ;

        Connection con = DriverManager.getConnection(url,userName,passWord);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query);

        System.out.println("Number of records deleted : " + rows);
        con.close();
    }
    //update
    public static void update() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String passWord = "Arrchit@2004";

        int id = 1;
        int salary = 150000;
        String query = "update employee set salary = " + salary + " where emp_id = " + id;


        Connection con = DriverManager.getConnection(url,userName,passWord);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query);
        System.out.println("Number of Rows affected : " + rows);

        con.close();
    }
    
}
