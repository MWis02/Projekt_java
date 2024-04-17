import javax.swing.*;
import java.awt.*;

public abstract class GUI {
    public abstract void dodaj();
    public abstract void wyswietl(String przycisk);
    public abstract void usun();
    public abstract void zmien();
    public abstract void calosc();

    static Przepisy przepisy = new Przepisy();
    static Lista lista = new Lista();
    public static void wyswietlGUI(){
        JFrame frame = new JFrame("Moja aplikacja");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //przyciski do przepisów
        JButton buttonP1 = new JButton("<html>Wyświetl<br>przepis</html>");
        buttonP1.setBounds(50, 50, 100, 50);
        JButton buttonP2 = new JButton("<html>Dodaj<br>przepis</html>");
        buttonP2.setBounds(50, 125, 100, 50);
        JButton buttonP3 = new JButton("<html>Usuń<br>przepis</html>");
        buttonP3.setBounds(50, 200, 100, 50);
        JButton buttonP4 = new JButton("<html>Edytuj<br>przepis</html>");
        buttonP4.setBounds(50, 275, 100, 50);

        //przyciski do listy
        JButton buttonL1 = new JButton("<html>Wyświetl<br>listę</html>");
        buttonL1.setBounds(350, 50, 100, 50);
        JButton buttonL2 = new JButton("Dodaj listę");
        buttonL2.setBounds(350, 125, 100, 50);
        JButton buttonL3 = new JButton("<html>Usuń<br>z listę</html>");
        buttonL3.setBounds(350, 200, 100, 50);
        JButton buttonL4 = new JButton("<html>Edytuj <br> listę</html>");
        buttonL4.setBounds(350, 275, 100, 50);


        frame.getContentPane().setLayout(null);

        //Przyciski do przepisów wyświetlenie
        frame.getContentPane().add(buttonP1);
        buttonP1.addActionListener(e -> {
            przepisy.wyswietl("wyswietl");
            frame.setVisible(false);
        });
        frame.getContentPane().add(buttonP2);
        buttonP2.addActionListener(e -> {
            przepisy.dodaj();
            frame.setVisible(false);
            });
        frame.getContentPane().add(buttonP3);
        buttonP3.addActionListener(e -> {
            przepisy.wyswietl("usun");
            frame.setVisible(false);
        });
        frame.getContentPane().add(buttonP4);
        buttonP4.addActionListener(e -> {
            przepisy.wyswietl("zmien");
            frame.setVisible(false);
        });

        //Przyciski do przepisów wyświetlenie
        frame.getContentPane().add(buttonL1);
        buttonL1.addActionListener(e -> {
            lista.wyswietl("wyswietl");
            frame.setVisible(false);
        });
        frame.getContentPane().add(buttonL2);
        buttonL2.addActionListener(e -> {
            lista.dodaj();
            frame.setVisible(false);
        });
        frame.getContentPane().add(buttonL3);
        buttonL3.addActionListener(e -> {
            lista.wyswietl("usun");
            frame.setVisible(false);
        });
        frame.getContentPane().add(buttonL4);
        buttonL4.addActionListener(e -> {
            lista.wyswietl("zmien");
            frame.setVisible(false);
        });

        //Ustalenie rozmiarów okna
        frame.getContentPane().setPreferredSize(new Dimension(500, 400));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
