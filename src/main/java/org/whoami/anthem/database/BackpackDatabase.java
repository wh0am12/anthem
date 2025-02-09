package org.whoami.anthem.database;

import com.saicone.rtag.item.ItemTagStream;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.whoami.anthem.Anthem;

import java.nio.file.Files;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class BackpackDatabase extends Database{
    public String SQLiteCreateTokensTable = "CREATE TABLE IF NOT EXISTS backpack (" + // make sure to put your table name in here too.
            "`uuid` varchar(36) NOT NULL," +
            "`slot` int(36) NOT NULL," +
            "`inventory` varchar(1024) NOT NULL," +
            "PRIMARY KEY (`uuid`)" +
            ");";
    public BackpackDatabase(Anthem plugin){
        super(plugin,"AnthemBackpack","backpack");
    }
    @Override
    public void load() {
        connection = getConnection();
        try {
            Statement s = connection.createStatement();
            s.executeUpdate(SQLiteCreateTokensTable);
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initialize();
    }

    @Override
    public void initialize() {
        connection = getConnection();
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE inventory = ?");
            ResultSet rs = ps.executeQuery();
            close(ps,rs);

        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
    }
    public void putInventoryData(String UUID, int slotNumber,String base64){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement("REPLACE INTO " + table + " (uuid,slot,inventory) VALUES(?,?,?)");
            ps.setString(1, UUID);                                             // YOU MUST put these into this line!! And depending on how many
            ps.setInt(2, slotNumber); // This sets the value in the database. The colums go in order. Player is ID 1, kills is ID 2, Total would be 3 and so on. you can use
            ps.setObject(3,base64);
            ps.executeUpdate();
            return;
        } catch (SQLException ex) {
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
            }
        }
        return;
    }
    public String getInventoryData(String UUID){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement("SELECT * FROM " + table + " WHERE uuid = '"+UUID+"';");
            rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getString("uuid").equalsIgnoreCase(UUID.toLowerCase())){
                    return rs.getString("inventory");
                }
            }
            return null;
        } catch (SQLException ex) {
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
            }
        }
        return null;
    }
}
