package BusResv;

import com.mysql.cj.jdbc.result.ResultSetImpl;


import java.sql.*;


public class BusDAO {

    public void displayBusInfo() throws SQLException {
        Connection con = DbConnection.getConnection();
        Statement st = con.createStatement();

        String query = "SELECT * FROM bus";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            System.out.print("Bus No : " + rs.getInt("busNo") + "\t");
            if ( rs.getBoolean("ac") == false) {
                System.out.print("Ac : NO" + "\t\t");
            }
            else {
                System.out.print("Ac : YES" + "\t");
            }

            System.out.println("Total Capacity : " + rs.getInt("capacity"));
        }
        System.out.println("-------------------------------------------------------------");
        con.close();
    }

    public int getCapacity (int busNo) throws SQLException {
        String query = "SELECT capacity FROM bus WHERE busNo = ? " ;

        Connection con = DbConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1,busNo);
        ResultSet rs = pst.executeQuery();

        rs.next();
        return rs.getInt("capacity");

    }
}
