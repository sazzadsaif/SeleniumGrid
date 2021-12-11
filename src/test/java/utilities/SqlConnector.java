package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.*;

public class SqlConnector {
    private static final String Url = "jdbc:postgresql://localhost:5432/monthly_mortgage";
    private static final String User = ReadConfigFiles.getPropertyValues("DbUser");
    private static final String Password = ReadConfigFiles.getPropertyValues("DbPassword");

    private static final Logger LOGGER = LogManager.getLogger(SqlConnector.class);

    /**
     * Connect to the postgre database
     *
     * @return
     */
    private static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Url, User, Password);
        } catch (SQLException e) {
            LOGGER.error("SQL connection Exception" + e.getMessage());
        }
        return conn;
    }

    public static ResultSet readData(String SQL) throws SQLException {
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = connect();
            LOGGER.debug("Connection object value " + conn);
            Statement stmt =conn.createStatement();
            rs = stmt.executeQuery(SQL);

        } catch (SQLException e) {
            LOGGER.error("SQL resultset exception " + e.getMessage());
        }finally {
            conn.close();
        }
        return rs;
    }
}