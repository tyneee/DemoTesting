/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tblcar;

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
public class TblCarDAO implements Serializable {

    public List<TblCarDTO> getAllCar(int index) throws SQLException, NamingException, Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet result = null;
        List<TblCarDTO> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select carID,carName,color,yearOfCreate,categoryID,price,quantityOfRemain,image,description,status \n"
                        + "from tblCar\n"
                        + "where status = 'active' and quantityOfRemain > 0\n"
                        + "order by yearOfCreate desc\n"
                        + "OFFSET ? ROWS FETCH NEXT 2\n"
                        + "row only";

                pstm = con.prepareStatement(sql);
                pstm.setInt(1, (index - 1) * 2);
                result = pstm.executeQuery();
                while (result.next()) {
                    TblCarDTO dto = new TblCarDTO(result.getInt("carID"), result.getString("carName"), result.getString("color"), result.getInt("yearOfCreate"), result.getInt("categoryID"), result.getFloat("price"), result.getInt("quantityOfRemain"), result.getString("image"), result.getString("description"), result.getString("status"));

                    list.add(dto);
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

    public int countPageNumber() throws NamingException, SQLException, Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet result = null;
        int countPage = 0;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select count(carID)\n"
                        + "from tblCar\n"
                        + "where quantityOfRemain > 0 and status = 'active'";

                pstm = con.prepareStatement(sql);
                result = pstm.executeQuery();
                while (result.next()) {
                    int totalCar = result.getInt(1);

                    countPage = totalCar / 2;
                    if (totalCar % 2 != 0) {
                        countPage++;
                    }

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
        return countPage;

    }

    public List<TblCarDTO> getCarWithNameCondition(int index, String name, String pickUpDate, String returnDate, int amount) throws SQLException, NamingException, Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet result = null;
        List<TblCarDTO> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select c.carID ,c.carName as carName,color, yearOfCreate,c.categoryID,  c.price,c.quantityOfRemain - isnull(tmp2.quantity, 0) as quantityOfRemain ,image,description,status ,tmp.avg as rate\n"
                        + "from tblCar c left join \n"
                        + "          (select carName, AVG(rate) as avg\n"
                        + "            from tblFeedBack JOIN tblCar ON tblCar.carID = tblFeedBack.carID\n"
                        + "            GROUP BY carName) tmp on c.carName = tmp.carName left JOIN (SELECT d.carID as carID, sum(d.quantity) as quantity\n"
                        + "			FROM tblOrderDetail d JOIN dbo.tblOder o ON d.billID = o.billID\n"
                        + "			WHERE o.status = 'active' AND NOT(d.pickUpDate > ? OR d.returnDate < ?) group by d.carID)tmp2 on tmp2.carID = c.carID\n"
                        + "where c.quantityOfRemain - isnull(tmp2.quantity, 0) >= ? AND c.carName LIKE ?\n"
                        + "ORDER BY c.yearOfCreate, carName,quantity\n"
                        + "OFFSET ? ROWS\n"
                        + "FETCH FIRST 2 ROWS ONLY";

                pstm = con.prepareStatement(sql);

                pstm.setString(1, returnDate);
                pstm.setString(2, pickUpDate);
                pstm.setInt(3, amount);
                pstm.setString(4, "%" + name + "%");
                pstm.setInt(5, (index - 1) * 2);
                result = pstm.executeQuery();

                while (result.next()) {

                    int carId = result.getInt(1);

                    String carName = result.getString(2);

                    String color = result.getString(3);

                    int year = result.getInt(4);

                    int categoryID = result.getInt(5);
                    float price = result.getFloat(6);
                    int quantityRemain = result.getInt(7);
                    String image = result.getString(8);
                    String description = result.getString(9);
                    String status = result.getString(10);
                    int rate = result.getInt(11);
                    TblCarDTO dto = new TblCarDTO(carId, carName, color, year, categoryID, price, quantityRemain, image, description, status, rate);
                    list.add(dto);
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

    public List<TblCarDTO> getCarWithCategoryCondition(int index, int name, String pickUpDate, String returnDate, int amount) throws SQLException, NamingException, Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet result = null;
        List<TblCarDTO> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select c.carID ,c.carName as carName,color, yearOfCreate,c.categoryID,  c.price,c.quantityOfRemain - isnull(tmp2.quantity, 0) as quantityOfRemain ,image,description,status ,tmp.avg as rate\n"
                        + "from tblCar c left join \n"
                        + "          (select carName, AVG(rate) as avg\n"
                        + "            from tblFeedBack JOIN tblCar ON tblCar.carID = tblFeedBack.carID\n"
                        + "            GROUP BY carName) tmp on c.carName = tmp.carName left JOIN (SELECT d.carID as carID, sum(d.quantity) as quantity\n"
                        + "			FROM tblOrderDetail d JOIN dbo.tblOder o ON d.billID = o.billID\n"
                        + "			WHERE o.status = 'active' AND NOT(d.pickUpDate > ? OR d.returnDate < ?) group by d.carID)tmp2 on tmp2.carID = c.carID\n"
                        + "where c.quantityOfRemain - isnull(tmp2.quantity, 0) >= ? AND c.categoryID LIKE ?\n"
                        + "ORDER BY c.yearOfCreate, carName,quantity\n"
                        + "OFFSET ? ROWS\n"
                        + "FETCH FIRST 2 ROWS ONLY";

                pstm = con.prepareStatement(sql);

                pstm.setString(1, returnDate);
                pstm.setString(2, pickUpDate);
                pstm.setInt(3, amount);
                pstm.setInt(4, name);
                pstm.setInt(5, (index - 1) * 2);
                result = pstm.executeQuery();

                while (result.next()) {

                    int carId = result.getInt(1);

                    String carName = result.getString(2);

                    String color = result.getString(3);

                    int year = result.getInt(4);

                    int categoryID = result.getInt(5);
                    float price = result.getFloat(6);
                    int quantityRemain = result.getInt(7);
                    String image = result.getString(8);
                    String description = result.getString(9);
                    String status = result.getString(10);
                    int rate = result.getInt(11);
                    TblCarDTO dto = new TblCarDTO(carId, carName, color, year, categoryID, price, quantityRemain, image, description, status, rate);
                    list.add(dto);
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

    public int countPageNumberWithNameCondition(String name, String pickUpDate, String returnDate, int amount) throws NamingException, SQLException, Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet result = null;
        int countPage = 0;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select count(c.carID) as count\n"
                        + "from tblCar c left JOIN (select d.carID AS carID, sum(d.quantity) as quantity\n"
                        + "from tblOrderDetail d JOIN tblOder o on d.billID = o.billID\n"
                        + "where o.status = 'active' and not(pickUpDate > ? or returnDate < ?) group by d.carID )tmp2 on tmp2.carID = c.carID\n"
                        + "WHERE c.carName like ? and c.quantityOfRemain - isnull(tmp2.quantity, 0) >= ?";

                pstm = con.prepareStatement(sql);
                pstm.setString(1, returnDate);
                pstm.setString(2, pickUpDate);
                pstm.setString(3, "%" + name + "%");
                pstm.setInt(4, amount);

                result = pstm.executeQuery();
                while (result.next()) {
                    int totalCar = result.getInt(1);

                    countPage = totalCar / 2;
                    if (totalCar % 2 != 0) {
                        countPage++;
                    }

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
        return countPage;

    }

    public int countPageNumberWithCategoryCondition(int name, String pickUpDate, String returnDate, int amount) throws NamingException, SQLException, Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet result = null;
        int countPage = 0;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select count(c.carID) as count\n"
                        + "from tblCar c left JOIN (select d.carID AS carID, sum(d.quantity) as quantity\n"
                        + "from tblOrderDetail d JOIN tblOder o on d.billID = o.billID\n"
                        + "where o.status = 'active' and not(pickUpDate > ? or returnDate < ?) group by d.carID )tmp2 on tmp2.carID = c.carID\n"
                        + "WHERE c.categoryID like ? and c.quantityOfRemain - isnull(tmp2.quantity, 0) >= ?";

                pstm = con.prepareStatement(sql);
                pstm.setString(1, returnDate);
                pstm.setString(2, pickUpDate);
                pstm.setInt(3, name);
                pstm.setInt(4, amount);

                result = pstm.executeQuery();
                while (result.next()) {
                    int totalCar = result.getInt(1);

                    countPage = totalCar / 2;
                    if (totalCar % 2 != 0) {
                        countPage++;
                    }

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
        return countPage;

    }

    public int getcategoryID(String name) throws NamingException, SQLException, Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet result = null;
        int cateID = 0;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select categoryID\n"
                        + "from tblCategory\n"
                        + "where categoryName like ?";

                pstm = con.prepareStatement(sql);
                pstm.setString(1, name);

                result = pstm.executeQuery();
                if (result.next()) {
                    cateID = result.getInt(1);

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
        return cateID;

    }

    public int getQuantityRemain(int ID, String pickUpDate, String returnDate, int amount) throws SQLException, NamingException, Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet result = null;
        int quantity = 0;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select c.quantityOfRemain - isnull(tmp2.quantity, 0) as quantityOfRemain \n"
                        + "from tblCar c left join \n"
                        + "          (select carName, AVG(rate) as avg\n"
                        + "            from tblFeedBack JOIN tblCar ON tblCar.carID = tblFeedBack.carID\n"
                        + "            GROUP BY carName) tmp on c.carName = tmp.carName left JOIN (SELECT d.carID as carID, sum(d.quantity) as quantity\n"
                        + "			FROM tblOrderDetail d JOIN dbo.tblOder o ON d.billID = o.billID\n"
                        + "			WHERE o.status = 'active' AND NOT(d.pickUpDate > ? OR d.returnDate < ?) group by d.carID)tmp2 on tmp2.carID = c.carID\n"
                        + "where c.quantityOfRemain - isnull(tmp2.quantity, 0) >= ? AND c.carID = ?";

                pstm = con.prepareStatement(sql);

                pstm.setString(1, returnDate);
                pstm.setString(2, pickUpDate);
                pstm.setInt(3, amount);
                pstm.setInt(4, ID);

                result = pstm.executeQuery();

                if (result.next()) {
                    quantity = result.getInt(1);
                   
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
        return quantity;
    }

}
