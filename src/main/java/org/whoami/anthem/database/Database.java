package org.whoami.anthem.database;

import org.whoami.anthem.Anthem;

import java.io.File;
import java.sql.*;
import java.util.logging.Level;

public abstract class Database {
    protected Anthem plugin;
    protected Connection connection;
    public final String dbname;
    public final String table;
    public Database(Anthem plugin,String name,String table){
        this.plugin = plugin;
        this.dbname = name;
        this.table = table;
    }
    public Connection getConnection(){
        File folder = new File(plugin.getDataFolder(), dbname+".db");
        try {
            if(connection!=null&&!connection.isClosed()){
                return connection;
            }
            connection = DriverManager.getConnection("jdbc:sqlite:" + folder);
            plugin.getLogger().log(Level.SEVERE,"SQLite enabled");
            return connection;
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE,"SQLite exception on initialize", ex);
        }
        plugin.getLogger().log(Level.SEVERE,"SQL null");
        return null;
    }
    public abstract void load();
    public abstract void initialize();
    public void close(PreparedStatement ps, ResultSet rs){
        try {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Failed to close MySQL connection: ", ex);
        }
    }
}
