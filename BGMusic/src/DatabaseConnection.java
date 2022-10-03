
import java.sql.*;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class DatabaseConnection
{

    private static final String URL = "jdbc:mysql://localhost:3306/bgmusic";
    private static final String USER = "root";
    private static final String PASSWORD = "oracle";
    private static Connection connection = null;

    public static Connection getConnection()
    {
        if (connection == null)
        {
            try
            {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return connection;
    }

    public boolean loginCheck(String loginUsername, String loginPassword)
    {
        boolean check = false;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD))
        {
            String sql = "SELECT username,password,functie FROM angajat where username=? and password=? and functie=\"manager\"";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, loginUsername);
            statement.setString(2, loginPassword);
            ResultSet result = statement.executeQuery();
            if (result.next())
            {
                check = true;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return check;
    }

    public boolean loginCheck1(String loginUsername, String loginPassword)
    {
        boolean check = false;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD))
        {
            String sql = "SELECT username,password,functie FROM angajat where username=? and password=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, loginUsername);
            statement.setString(2, loginPassword);
            ResultSet result = statement.executeQuery();
            if (result.next())
            {
                check = true;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return check;
    }

    public TableModel afisareAngajati()
    {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD))
        {
            String sql = "SELECT * FROM angajat";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            return DbUtils.resultSetToTableModel(result);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public TableModel produseCresc()
    {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD))
        {
            String sql = "SELECT * FROM produs ORDER BY pret ASC";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            return DbUtils.resultSetToTableModel(result);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public TableModel produseDesc()
    {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD))
        {
            String sql = "SELECT * FROM produs ORDER BY pret DESC";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            return DbUtils.resultSetToTableModel(result);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public TableModel afisareProduse()
    {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD))
        {
            String sql = "SELECT * FROM produs";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            //System.out.println("MERGE");
            return DbUtils.resultSetToTableModel(result);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public TableModel cautaProdus(String denumireString)
    {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD))
        {
            String sql = "SELECT * FROM produs WHERE denumire=\"" + denumireString + "\"";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            System.out.println("PRODUS GASIT");
            return DbUtils.resultSetToTableModel(result);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public TableModel cautaAngajat(String functieString)
    {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD))
        {
            String sql = "SELECT * FROM angajat WHERE functie=\"" + functieString + "\"";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            System.out.println("ANGAJAT GASIT");
            return DbUtils.resultSetToTableModel(result);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public void adaugaAngajat(int id_ang, String nume, String prenume, String functie, String cnp, String telefon, String username, String password)
    {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD))
        {
            String sql = "INSERT INTO angajat () VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id_ang);
            statement.setString(2, nume);
            statement.setString(3, prenume);
            statement.setString(4, functie);
            statement.setString(5, cnp);
            statement.setString(6, telefon);
            statement.setString(7, username);
            statement.setString(8, password);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0)
            {
                System.out.println("Un nou angajat a fost adaugat!");
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void adaugaProdus(int idprodus, String categorie, String denumire, int greutate, String garantie, int pret)
    {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD))
        {
            String sql = "INSERT INTO produs () VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idprodus);
            statement.setString(2, categorie);
            statement.setString(3, denumire);
            statement.setInt(4, greutate);
            statement.setString(5, garantie);
            statement.setInt(6, pret);
            //statement.setInt(7, idbon);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0)
            {
                System.out.println("Un nou produs a fost adaugat!");
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void eliminaAngajat(int idang)
    {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD))
        {
            String sql = "DELETE FROM angajat WHERE id_angajat=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idang);
            statement.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void eliminaProdus(int idprod)
    {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD))
        {
            String sql = "DELETE FROM produs WHERE id_produs=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idprod);
            statement.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void modificaAngajat(int id_angajat, String nume, String prenume, String functie, String cnp, String telefon, String username, String password)
    {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD))
        {
            int counter = 1;
            boolean first = true;
            String sql = "UPDATE angajat SET ";
            if (nume.equals("nu") == false)
            {
                if (first)
                {
                    sql = sql + "nume=? ";
                    first = false;
                }
                else
                {
                    sql = sql + ",nume=? ";
                }
            }
            if (prenume.equals("nu") == false)
            {
                if (first)
                {
                    sql = sql + "prenume=? ";
                    first = false;
                }
                else
                {
                    sql = sql + ",prenume=? ";
                }
            }
            if (cnp.equals("nu") == false)
            {
                if (first)
                {
                    sql = sql + "cnp=? ";
                    first = false;
                }
                else
                {
                    sql = sql + ",cnp=? ";
                }
            }
            if (telefon.equals("nu") == false)
            {
                if (first)
                {
                    sql = sql + "telefon=? ";
                    first = false;
                }
                else
                {
                    sql = sql + ",telefon=? ";
                }
            }
            if (functie.equals("nu") == false)
            {
                if (first)
                {
                    sql = sql + "functie=? ";
                    first = false;
                }
                else
                {
                    sql = sql + ",functie=? ";
                }
            }
            if (username.equals("nu") == false)
            {
                if (first)
                {
                    sql = sql + "username=? ";
                    first = false;
                }
                else
                {
                    sql = sql + ",username=? ";
                }
            }
            if (password.equals("nu") == false)
            {
                if (first)
                {
                    sql = sql + "password=? ";
                    first = false;
                }
                else
                {
                    sql = sql + ",password=? ";
                }
            }
            sql = sql + "where id_angajat=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            if (nume.equals("nu") == false)
            {
                statement.setString(counter, nume);
                counter++;
            }
            if (prenume.equals("nu") == false)
            {
                statement.setString(counter, prenume);
                counter++;
            }
            if (cnp.equals("nu") == false)
            {
                statement.setString(counter, cnp);
                counter++;
            }
            if (telefon.equals("nu") == false)
            {
                statement.setString(counter, telefon);
                counter++;
            }
            if (functie.equals("nu") == false)
            {
                statement.setString(counter, functie);
                counter++;
            }
            if (username.equals("nu") == false)
            {
                statement.setString(counter, username);
                counter++;
            }
            if (password.equals("nu") == false)
            {
                statement.setString(counter, password);
                counter++;
            }
            statement.setInt(counter, id_angajat);
            statement.executeUpdate();
            System.out.println(statement);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void modificaProdus(int id_produs, String categorie, String denumire, int greutate, String garantie, int pret)
    {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD))
        {
            int counter = 1;
            boolean first = true;
            String sql = "UPDATE produs SET ";
            if (categorie.equals("nu") == false)
            {
                if (first)
                {
                    sql = sql + "categorie=? ";
                    first = false;
                }
                else
                {
                    sql = sql + ",categorie=? ";
                }
            }
            if (denumire.equals("nu") == false)
            {
                if (first)
                {
                    sql = sql + "denumire=? ";
                    first = false;
                }
                else
                {
                    sql = sql + ",denumire=? ";
                }
            }
            if (greutate != 0)
            {
                if (first)
                {
                    sql = sql + "greutate=? ";
                    first = false;
                }
                else
                {
                    sql = sql + ",greutate=? ";
                }
            }
            if (garantie.equals("nu") == false)
            {
                if (first)
                {
                    sql = sql + "garantie=? ";
                    first = false;
                }
                else
                {
                    sql = sql + ",garantie=? ";
                }
            }
            if (pret != 0)
            {
                if (first)
                {
                    sql = sql + "pret=? ";
                    first = false;
                }
                else
                {
                    sql = sql + ",pret=? ";
                }
            }
            sql = sql + "where id_produs=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            if (categorie.equals("nu") == false)
            {
                statement.setString(counter, categorie);
                counter++;
            }
            if (denumire.equals("nu") == false)
            {
                statement.setString(counter, denumire);
                counter++;
            }
            if (greutate != 0)
            {
                statement.setInt(counter, greutate);
                counter++;
            }
            if (garantie.equals("nu") == false)
            {
                statement.setString(counter, garantie);
                counter++;
            }
            if (pret != 0)
            {
                statement.setInt(counter, pret);
                counter++;
            }
            statement.setInt(counter, id_produs);
            statement.executeUpdate();
            System.out.println(statement);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public TableModel filtrarecmmd(int pret)
    {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD))
        {
            String sql = "SELECT * FROM produs WHERE pret >= \"" + pret + "\"";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            //System.out.println("PRODUS GASIT");
            return DbUtils.resultSetToTableModel(result);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public TableModel filtrarecv(int pret, int pret1)
    {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD))
        {
            String sql = "SELECT * FROM produs WHERE pret >= " + pret + "" + " AND pret <= " + pret1;
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            //System.out.println("PRODUS GASIT");
            return DbUtils.resultSetToTableModel(result);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public TableModel filtrarecpmm(int pret)
    {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD))
        {
            String sql = "SELECT * FROM produs WHERE pret <= \"" + pret + "\"";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            //System.out.println("PRODUS GASIT");
            return DbUtils.resultSetToTableModel(result);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}
