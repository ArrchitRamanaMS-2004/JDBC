import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) throws Exception{
        batchdemo();
    }
    //simple read from db and display results
    public static void readRecords() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String passWord = "Database@01";
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
        String passWord = "Database@01";
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
        String passWord = "Database@01";

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
        String passWord = "Database@01";

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
        String passWord = "Database@01";

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
        String passWord = "Database@01";

        int id = 1;
        int salary = 150000;
        String query = "update employee set salary = " + salary + " where emp_id = " + id;


        Connection con = DriverManager.getConnection(url,userName,passWord);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query);
        System.out.println("Number of Rows affected : " + rows);

        con.close();
    }
//    Types of statements:
//    1) normal statement
//    2) prepared statement
//    3) callable statement ( mainly used for stored procedure )
    //calling a simple stored procedure
    public static void sp () throws Exception {

        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String username = "root";
        String password = "Database@01";

        Connection con = DriverManager.getConnection(url,"root","Arrchit@2004");
        CallableStatement cst = con.prepareCall("{call GetEmp()}");
        ResultSet rs = cst.executeQuery();

        while (rs.next()) {
            System.out.println("id is : " + rs.getInt(1));
            System.out.println("name is : " + rs.getString(2));
            System.out.println("salary is : " + rs.getInt(3));
        }

        con.close();

    }
    //calling stored procedure with input parameter
    public static void sp2 () throws Exception {

        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String username = "root";
        String password = "Database@01";

        int id = 3;

        Connection con = DriverManager.getConnection(url,username,password);
        CallableStatement cst = con.prepareCall("{call GetEmpById(?)}");
        cst.setInt(1, id);
        ResultSet rs = cst.executeQuery();

        while (rs.next()) {
            System.out.println("id is : " + rs.getInt(1));
            System.out.println("name is : " + rs.getString(2));
            System.out.println("salary is : " + rs.getInt(3));
        }

        con.close();

    }
    //calling store procedure with in and out parameter
    public static void sp3 () throws Exception {

        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String username = "root";
        String password = "Database@01";

        int id = 3;

        Connection con = DriverManager.getConnection(url,"root","Arrchit@2004");
        CallableStatement cst = con.prepareCall("{call GetNameById(?,?)}");
        cst.setInt(1, id);
        cst.registerOutParameter(2, Types.VARCHAR);
        cst.executeUpdate();

        System.out.println(cst.getString(2));

        con.close();

    }
    //commit vs autocommit
    public static void commitdemo() throws Exception {

        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String username = "root";
        String password = "Database@01";

        String query1 = "update employee set salary = 550000 where emp_id = 1";
        String query2 = "update employee set salary = 550000 where emp_id = 2";

        Connection con = DriverManager.getConnection(url,username,password);
        con.setAutoCommit(false);

        Statement st = con.createStatement();
        int rows1 = st.executeUpdate(query1);
        System.out.println("Rows affected for query1 : " + rows1);

        int rows2 = st.executeUpdate(query2);
        System.out.println("Rows affected for query 2: " + rows2);

        if (rows1 > 0 && rows2 > 0) {
            con.commit();
        }
        con.close();

    }
    //batch processing
    public static void batchdemo() throws Exception {

        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String username = "root";
        String password = "Database@01";

        String query1 = "update employee set salary = 300000 where emp_id = 1";
        String query2 = "update employee set salary = 300000 where emp_id = 2";
        String query3 = "update employee set salary = 300000 where emp_id = 3";
        String query4 = "update employee set salary = 300000 where emp_id = 4";

        Connection con = DriverManager.getConnection(url,username,password);
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        st.addBatch(query1);
        st.addBatch(query2);
        st.addBatch(query3);
        st.addBatch(query4);

        int [] res = st.executeBatch();

        for ( int i : res ) {
            if ( i > 0 ) {
                System.out.println("rows affected : " + i);
                continue;
            }
            else {
                con.rollback();
            }
        }

        con.commit();
        con.close();

    }

}
