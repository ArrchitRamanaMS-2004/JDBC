package BusResv;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class BookingDAO {

    public int getBookedCount (int busNo , Date date) throws SQLException {
        String query = "SELECT COUNT(passenger_name) FROM booking WHERE busNo = ? AND travel_date = ?;" ;

        Connection con = DbConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1,busNo);
        pst.setDate(2, new java.sql.Date (date.getTime()));
        ResultSet rs = pst.executeQuery();

        rs.next();
        return rs.getInt(1);
    }

    public void addBooking(Booking booking) throws SQLException {
        String query = "insert into booking values (?,?,?);";

        Connection con = DbConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, booking.getPassengerName());
        pst.setInt(2,booking.getBusNo());
        pst.setDate(3, new java.sql.Date (booking.getDate().getTime()));

        pst.executeUpdate();
        con.close();
    }
}
