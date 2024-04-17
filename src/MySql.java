import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class MySql {
    static int id=0;
    static String jdbcUrl = "jdbc:mysql://localhost:3306/baza_projekt";
    static String user = "root";

    //My sql obsługujący przepisy
    public static void insertP(String nazwa, String skladniki, String tresc) {
        try{
            Connection connection = DriverManager.getConnection(jdbcUrl, user, "");

            String dodN = "INSERT INTO `dane` (`nazwa`) VALUES (?);";
            PreparedStatement prepnazwa = connection.prepareStatement(dodN);
            prepnazwa.setString(1, nazwa);
            prepnazwa.executeUpdate();
            System.out.println(nazwa);
            System.out.println(skladniki);
            System.out.println(tresc);

            String idN = "SELECT id from dane WHERE nazwa = ?";
            PreparedStatement prepidN = connection.prepareStatement(idN);
            prepidN.setString(1, nazwa);
            ResultSet idW = prepidN.executeQuery();
            while(idW.next()){
                id = idW.getInt("id");
            }

            String dodS = "INSERT INTO `skladniki`(`id`, `skladniki`) VALUES (?,?);";
            PreparedStatement prepskladniki = connection.prepareStatement(dodS);
            prepskladniki.setInt(1, id);
            prepskladniki.setString(2, skladniki);
            prepskladniki.executeUpdate();

            String dodT = "INSERT INTO `przygotowanie`(`id`, `przygotowanie`) VALUES (?,?);";
            PreparedStatement preptresc = connection.prepareStatement(dodT);
            preptresc.setInt(1, id);
            preptresc.setString(2, tresc);
            preptresc.executeUpdate();

           JOptionPane.showMessageDialog(null, "Poprawnie zapisane dane", "Informacja", JOptionPane.INFORMATION_MESSAGE);

        }catch (SQLException e){
            String blad = e.getMessage();
            String porownanie = "Duplicate entry '"+nazwa+"' for key 'unikalne_nazwy'";
            if(blad.equals(porownanie)){
                JOptionPane.showMessageDialog(null, "Podany przepis istnieje w bazie danych", "Informacja", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Błąd zapisu w bazie danych", "Informacja", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    public static ArrayList<String> selectN() {
        ArrayList<String> nazwyPrzepisow = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, user, "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nazwa from dane");
            while (resultSet.next()){
                String nazwa = resultSet.getString("nazwa");
                nazwyPrzepisow.add(nazwa);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return nazwyPrzepisow;
    }
    public static String SelectS(String wybranaNazwa){
        StringBuilder skladnikiBuilder = new StringBuilder();

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, user, "");

            String idN = "SELECT id from dane WHERE nazwa = ?";
            PreparedStatement prepidN = connection.prepareStatement(idN);
            prepidN.setString(1, wybranaNazwa);
            ResultSet idW = prepidN.executeQuery();
            while(idW.next()){
                id = idW.getInt("id");
            }

            String skladnikiS = "SELECT skladniki from skladniki WHERE id = ?";
            PreparedStatement prepSkladniki = connection.prepareStatement(skladnikiS);
            prepSkladniki.setInt(1, id);
            ResultSet skladnikiW = prepSkladniki.executeQuery();
            while(skladnikiW.next()){
                String skladnikiT = skladnikiW.getString("skladniki");
                String[] SkladnikiP = skladnikiT.split("\n");

                for (String skladnik : SkladnikiP) {
                    //System.out.println(skladnik);
                    skladnikiBuilder.append(skladnik).append("\n"); // Dodaj składnik z nową linią
                }

            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return skladnikiBuilder.toString();
    }
    public static String SelectP(String wybranaNazwa) {
        StringBuilder przygotowanieBuilder = new StringBuilder();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, user, "");

            String idN = "SELECT id from dane WHERE nazwa = ?";
            PreparedStatement prepidN = connection.prepareStatement(idN);
            prepidN.setString(1, wybranaNazwa);
            ResultSet idW = prepidN.executeQuery();
            while(idW.next()){
                id = idW.getInt("id");
            }

            String przygotowanieS = "SELECT przygotowanie from przygotowanie WHERE id=?";
            PreparedStatement prepPrzygotowanie = connection.prepareStatement(przygotowanieS);
            prepPrzygotowanie.setInt(1, id);
            ResultSet przygotowanieW = prepPrzygotowanie.executeQuery();
            while(przygotowanieW.next()){
                String przygotowanieT = przygotowanieW.getString("przygotowanie");
                String[] przygotowanieP = przygotowanieT.split("\n");

                for (String przygotowanie : przygotowanieP) {
                    //System.out.println(przygotowanie);
                    przygotowanieBuilder.append(przygotowanie).append("\n"); // Dodaj składnik z nową linią
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return przygotowanieBuilder.toString();
    }
    public static void DeleteP(String wybranaNazwaPrzepisu) {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, user, "");

            String idN = "SELECT id from dane WHERE nazwa = ?";
            PreparedStatement prepidN = connection.prepareStatement(idN);
            prepidN.setString(1, wybranaNazwaPrzepisu);
            ResultSet idW = prepidN.executeQuery();
            while(idW.next()){
                id = idW.getInt("id");
            }

            String delete = "DELETE from dane WHERE id = ? AND nazwa =?";
            PreparedStatement prepDel = connection.prepareStatement(delete);
            prepDel.setInt(1, id);
            prepDel.setString(2, wybranaNazwaPrzepisu);

            int delRows = prepDel.executeUpdate();

            if(delRows > 0){
                JOptionPane.showMessageDialog(null, "Poprawnie usunięte dane", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                GUI.wyswietlGUI();
            }else{
                JOptionPane.showMessageDialog(null, "Błąd przy usunięciu danych", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                GUI.wyswietlGUI();
            }
            prepDel.close();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateP(String wybranaNazwaPrzepisu, String pnazwa, String pskladniki, String ptresc) {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, user, "");

            String idN = "SELECT id from dane WHERE nazwa = ?";
            PreparedStatement prepidN = connection.prepareStatement(idN);
            prepidN.setString(1, wybranaNazwaPrzepisu);
            ResultSet idW = prepidN.executeQuery();
            while(idW.next()){
                id = idW.getInt("id");
            }

            String UpN = "UPDATE dane " +
                    "JOIN skladniki on dane.id = skladniki.id " +
                    "JOIN przygotowanie on dane.id = przygotowanie.id " +
                    "set dane.nazwa = ?, " +
                    "skladniki.skladniki = ?, " +
                    "przygotowanie.przygotowanie = ? " +
                    "WHERE dane.id = ? ";

            PreparedStatement prepUpN = connection.prepareStatement(UpN);
            prepUpN.setString(1, pnazwa);
            prepUpN.setString(2, pskladniki);
            prepUpN.setString(3, ptresc);
            prepUpN.setInt(4, id);
            prepUpN.executeUpdate();

            JOptionPane.showMessageDialog(null, "Dane zostały poprawnie zmienione", "Informacja", JOptionPane.INFORMATION_MESSAGE);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Mysql obsługujący listy
    public static void insertL(String Wnazwa, String Wtresc) {
        try{
            Connection connection = DriverManager.getConnection(jdbcUrl, user, "");

            String dodN = "INSERT INTO `lista` (`nazwaL`) VALUES (?);";
            PreparedStatement prepnazwa = connection.prepareStatement(dodN);
            prepnazwa.setString(1, Wnazwa);
            prepnazwa.executeUpdate();

            String idN = "SELECT id from lista WHERE nazwaL = ?";
            PreparedStatement prepidN = connection.prepareStatement(idN);
            prepidN.setString(1, Wnazwa);
            ResultSet idW = prepidN.executeQuery();
            while(idW.next()){
                id = idW.getInt("id");
            }

            String dodS = "INSERT INTO `dane_l`(`id`, informacje) VALUES (?,?);";
            PreparedStatement prepskladniki = connection.prepareStatement(dodS);
            prepskladniki.setInt(1, id);
            prepskladniki.setString(2, Wtresc);
            prepskladniki.executeUpdate();

            JOptionPane.showMessageDialog(null, "Poprawnie zapisane dane", "Informacja", JOptionPane.INFORMATION_MESSAGE);

        }catch (SQLException e){
            String blad = e.getMessage();
            String porownanie = "Duplicate entry '"+Wnazwa+"' for key 'nazwaL'";
            if(blad.equals(porownanie)){
                JOptionPane.showMessageDialog(null, "Podana lista istnieje w bazie danych", "Informacja", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Błąd zapisu w bazie danych", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                GUI.wyswietlGUI();
            }
        }

    }
    public static ArrayList<String> selectL() {
        ArrayList<String> nazwyList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, user, "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nazwaL from lista");
            while (resultSet.next()){
                String nazwa = resultSet.getString("nazwaL");
                nazwyList.add(nazwa);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return nazwyList;
    }
    public static String SelectT(String wybranaNazwa) {
        StringBuilder ListyBuilder = new StringBuilder();

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, user, "");

            String idN = "SELECT id from lista WHERE nazwaL = ?";
            PreparedStatement prepidN = connection.prepareStatement(idN);
            prepidN.setString(1, wybranaNazwa);
            ResultSet idW = prepidN.executeQuery();
            while (idW.next()) {
                id = idW.getInt("id");
            }

            String informacjeS = "SELECT informacje from dane_l WHERE id = ?";
            PreparedStatement prepInformacje = connection.prepareStatement(informacjeS);
            prepInformacje.setInt(1, id);
            ResultSet informacjeW = prepInformacje.executeQuery();
            while (informacjeW.next()) {
                String informacjeT = informacjeW.getString("informacje");
                String[] informacjeP = informacjeT.split("\n");

                for (String info : informacjeP) {
                    ListyBuilder.append(info).append("\n"); // Dodaj składnik z nową linią
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListyBuilder.toString();
    }
    public static void DeleteL(String wybranaNazwa) {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, user, "");

            String idN = "SELECT id from lista WHERE nazwaL = ?";
            PreparedStatement prepidN = connection.prepareStatement(idN);
            prepidN.setString(1, wybranaNazwa);
            ResultSet idW = prepidN.executeQuery();
            while (idW.next()) {
                id = idW.getInt("id");
            }

            String delete = "DELETE from lista WHERE id = ? AND nazwaL =?";
            PreparedStatement prepDel = connection.prepareStatement(delete);
            prepDel.setInt(1, id);
            prepDel.setString(2, wybranaNazwa);

            int delRows = prepDel.executeUpdate();

            if (delRows > 0) {
                JOptionPane.showMessageDialog(null, "Poprawnie usunięte dane", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                GUI.wyswietlGUI();
            } else {
                JOptionPane.showMessageDialog(null, "Błąd przy usunięciu danych", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                GUI.wyswietlGUI();
            }
            prepDel.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateL(String wybranaNazwaListy, String nazwaL, String trescL) {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, user, "");

            String idN = "SELECT id from lista WHERE nazwaL = ?";
            PreparedStatement prepidN = connection.prepareStatement(idN);
            prepidN.setString(1, wybranaNazwaListy);
            ResultSet idW = prepidN.executeQuery();
            while(idW.next()){
                id = idW.getInt("id");
            }

            String UpN = "UPDATE lista " +
                    "JOIN dane_l on lista.id = dane_l.id " +
                    "set lista.nazwaL = ?, dane_l.informacje = ?" +
                    "WHERE lista.id = ? ";

            PreparedStatement prepUpL = connection.prepareStatement(UpN);
            prepUpL.setString(1, nazwaL);
            prepUpL.setString(2, trescL);
            prepUpL.setInt(3, id);
            prepUpL.executeUpdate();

            JOptionPane.showMessageDialog(null, "Dane zostały poprawnie zmienione", "Informacja", JOptionPane.INFORMATION_MESSAGE);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
