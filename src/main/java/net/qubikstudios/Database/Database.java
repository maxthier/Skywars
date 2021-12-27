package net.qubikstudios.Database;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import net.qubikstudios.Skywars;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class Database {

    private MysqlDataSource dataSource;

    public Database(String host, int port, String database, String user, String password) throws SQLException{
        dataSource = new MysqlConnectionPoolDataSource();
        dataSource.setServerName(host);
        dataSource.setPortNumber(port);
        dataSource.setDatabaseName(database);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        testDataSource(1000);
        System.out.println("Database Connected!");
        try {
            initDB(dataSource);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public void testDataSource(int timeout) throws SQLException {
        try (Connection conn = dataSource.getConnection()){
            if(!conn.isValid(timeout)){
                throw new SQLException("Could not establish database connection!");
            }
        }
    }

    private void initDB(DataSource dataSource) throws SQLException, IOException {
        String setup;
        try (InputStream in = Skywars.getPlugin().ClassLoader().getResourceAsStream("dbsetup.sql")){
            setup = new BufferedReader(new InputStreamReader(in)).lines().collect(Collectors.joining("\n"));
        }catch (IOException e){
            Skywars.getPlugin().getLogger().log(Level.SEVERE, "Could not read db setup file.", e);
            throw e;
        }
        String[] queries = setup.split(";");
        for (String query : queries){
            if(query.isEmpty()) continue;
            try (Connection conn = dataSource.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.execute();
            }
        }
        Skywars.getPlugin().getLogger().info("§2Database setup Complete.");
    }

    public MysqlDataSource getDataSource() {
        return dataSource;
    }
}