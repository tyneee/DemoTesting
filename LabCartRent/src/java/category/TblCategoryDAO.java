/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package category;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import util.DBHelper;

/**
 *
 * @author USER
 */
public class TblCategoryDAO implements Serializable {

    public List<String> getAllCategory() throws SQLException, NamingException, Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet result = null;
        List<String> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select categoryName\n"
                        + "from tblCategory";

                pstm = con.prepareStatement(sql);
                result = pstm.executeQuery();
                while (result.next()) {
                    String categoryName = result.getString("categoryName");

                    list.add(categoryName);
                }
            }
        } finally {
            if (result != null) {
                result.close();
            }

            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }
}
