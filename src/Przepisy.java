import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Przepisy extends GUI{
    private static String wybranaNazwaPrzepisu;
    public void dodaj(){
        JFrame frame = new JFrame("Dodaj przepis");//Tworzymy nowe okno JFrame
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Ustawiamy domyślną operację zamknięcia na DISPOSE_ON_CLOSE

        //Tworzymy JTextFields, aby użytkownik mógł wprowadzić nazwę przepisu, składniki i treść przepisu
        JTextField nazwaTF = new JTextField(35);
        JTextArea skladnikiTA = new JTextArea(15, 15);
        skladnikiTA.setWrapStyleWord(true);
        skladnikiTA.setLineWrap(true);
        skladnikiTA.setOpaque(true);
        skladnikiTA.setEditable(true);
        JScrollPane scrollSTA = new JScrollPane(skladnikiTA);
        scrollSTA.setPreferredSize(new Dimension(750, 150));
        scrollSTA.setMinimumSize(scrollSTA.getPreferredSize());
        scrollSTA.setMaximumSize(scrollSTA.getPreferredSize());

        JTextArea przepisTA = new JTextArea(15, 15);
        przepisTA.setWrapStyleWord(true);
        przepisTA.setLineWrap(true);
        przepisTA.setOpaque(true);
        przepisTA.setEditable(true);
        JScrollPane scrollPTA = new JScrollPane(przepisTA);
        scrollPTA.setPreferredSize(new Dimension(750, 250));
        scrollPTA.setMinimumSize(scrollPTA.getPreferredSize());
        scrollPTA.setMaximumSize(scrollPTA.getPreferredSize());

        //Tworzymy przyciski "Cofnij" i "Dodaj"
        JButton cofnij = new JButton("<html>Cofnij</html>");
        cofnij.addActionListener( e -> {
            GUI.wyswietlGUI();
            frame.setVisible(false);
        });

        JButton dodaj = new JButton("<html>Dodaj</html>");
        dodaj.addActionListener( e -> {
            String nazwaP = nazwaTF.getText();
            String skladnikiP = skladnikiTA.getText();
            String trescP = przepisTA.getText();
            if(nazwaP.isEmpty() || skladnikiP.isEmpty() || trescP.isEmpty()){
                JOptionPane.showMessageDialog(null, "Wszystkie pola muszą zawierać treść ", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                frame.setVisible(false);
                dodaj();
            }else {
                wpiszPrzepis(nazwaP, skladnikiP, trescP);
                frame.setVisible(false);
            }
        });

        //Tworzymy JPanel, aby zawierać JTextFields i przyciski
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints GBC_1 = new GridBagConstraints();
        GBC_1.gridx = 0;
        GBC_1.gridy = 0;
        GBC_1.gridwidth = 2;
        GBC_1.gridheight = 1;
        GBC_1.fill = GridBagConstraints.HORIZONTAL;
        GBC_1.insets = new Insets(5, 10, 5, 10);
        GBC_1.anchor = GridBagConstraints.WEST;
        GBC_1.weightx = 1;
        GBC_1.weighty = 0;

        //Dodajemy etykiety i JTextFields do panelu
        panel.add(new JLabel("Dodaj nazwa przepisu: "), GBC_1);
        GBC_1.gridy++;
        panel.add(nazwaTF, GBC_1);
        GBC_1.gridy++;
        panel.add(new JLabel("Dodaj składniki przepisu: "), GBC_1);
        GBC_1.gridy++;
        panel.add(scrollSTA,GBC_1);
        GBC_1.gridy++;
        panel.add(new JLabel("Dodaj treść przepisu: "), GBC_1);
        GBC_1.gridy++;
        panel.add(scrollPTA,GBC_1);
        GBC_1.gridy++;
        GBC_1.gridx = 0;
        GBC_1.gridwidth = 1;
        GBC_1.fill = GridBagConstraints.NONE;
        GBC_1.weightx = 0;
        GBC_1.weighty = 0;
        panel.add(cofnij, GBC_1);

        GBC_1.gridx++;
        panel.add(dodaj, GBC_1);

        //Dodajemy panel do okna JFrame
        frame.add(panel);
        frame.getContentPane().setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private static void wpiszPrzepis(String Pnazwa, String Pskladniki, String Ptresc) {
        //Wpisujemy przepis do bazy danych
        MySql.insertP(Pnazwa, Pskladniki, Ptresc);
        GUI.wyswietlGUI();
    }
    public void wyswietl(String przycisk) {
        ArrayList<String> nazwyPrzepisow = MySql.selectN();

        switch (przycisk) {
            //W zależności od przycisku, wyświetlamy przepisy, usuwamy przepisy lub zmieniamy przepisy
            case "wyswietl" -> {
                //Tworzymy nowe okno JFrame
                JFrame frameP = new JFrame("Lista przepisów");
                frameP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                panel.add(Box.createVerticalStrut(20));// margines górny

                //Tworzymy przyciski dla każdego przepisu
                for (String nazwaPrzepisu : nazwyPrzepisow) {

                    JButton buttonPrzepis = new JButton(nazwaPrzepisu);
                    buttonPrzepis.setPreferredSize(new Dimension(150, 75));
                    buttonPrzepis.setMaximumSize(buttonPrzepis.getPreferredSize());
                    buttonPrzepis.setMinimumSize(buttonPrzepis.getPreferredSize());
                    buttonPrzepis.setAlignmentX(Component.CENTER_ALIGNMENT);

                    panel.add(buttonPrzepis);
                    panel.add(Box.createVerticalStrut(15)); //odstępy między przyciskami

                    buttonPrzepis.addActionListener(e -> {
                        wybranaNazwaPrzepisu = nazwaPrzepisu;
                        calosc();
                        frameP.setVisible(false);
                    });
                }

                //Tworzymy przycisk "Cofnij"
                JButton cofnij_w = new JButton("<html>Cofnij</html>");
                cofnij_w.setPreferredSize(new Dimension(150, 75));
                cofnij_w.setMaximumSize(cofnij_w.getPreferredSize());
                cofnij_w.setMinimumSize(cofnij_w.getPreferredSize());
                cofnij_w.setAlignmentX(Component.CENTER_ALIGNMENT);
                cofnij_w.addActionListener(e -> {
                    GUI.wyswietlGUI();
                    frameP.setVisible(false);
                });
                panel.add(cofnij_w);

                //Dodajemy panel do JScrollPane, a JScrollPane do okna JFrame
                JScrollPane scrollPane = new JScrollPane(panel);
                frameP.getContentPane().add(scrollPane);
                frameP.getContentPane().setPreferredSize(new Dimension(500, 400));
                frameP.pack();
                frameP.setLocationRelativeTo(null);
                frameP.setVisible(true);
            }
            case "usun" -> {
                //Tworzymy nowe okno JFrame
                JFrame frameP = new JFrame("Lista przepisów");
                frameP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                JPanel panel = new JPanel();
                //Ustawiamy layout panelu na BoxLayout
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.add(Box.createVerticalStrut(20));// margines górny

                //Tworzymy przyciski dla każdego przepisu
                for (String nazwaPrzepisu : nazwyPrzepisow) {
                    JButton buttonPrzepis = new JButton(nazwaPrzepisu);
                    buttonPrzepis.setPreferredSize(new Dimension(150, 75));
                    buttonPrzepis.setMaximumSize(buttonPrzepis.getPreferredSize());
                    buttonPrzepis.setMinimumSize(buttonPrzepis.getPreferredSize());
                    buttonPrzepis.setAlignmentX(Component.CENTER_ALIGNMENT);

                    panel.add(buttonPrzepis);
                    panel.add(Box.createVerticalStrut(15)); //odstępy między przyciskami

                    buttonPrzepis.addActionListener(e -> {
                        wybranaNazwaPrzepisu = nazwaPrzepisu;
                        usun();
                        frameP.setVisible(false);
                    });
                }

                //Tworzymy przycisk "Cofnij"
                JButton cofnij_u = new JButton("<html>Cofnij</html>");
                cofnij_u.setPreferredSize(new Dimension(150, 75));
                cofnij_u.setMaximumSize(cofnij_u.getPreferredSize());
                cofnij_u.setMinimumSize(cofnij_u.getPreferredSize());
                cofnij_u.setAlignmentX(Component.CENTER_ALIGNMENT);
                cofnij_u.addActionListener(e -> {
                    GUI.wyswietlGUI();
                    frameP.setVisible(false);
                });
                panel.add(cofnij_u);

                //Dodajemy panel do JScrollPane, a JScrollPane do okna JFrame
                JScrollPane scrollPane = new JScrollPane(panel);
                frameP.getContentPane().add(scrollPane);
                frameP.getContentPane().setPreferredSize(new Dimension(500, 400));
                frameP.pack();
                frameP.setLocationRelativeTo(null);
                frameP.setVisible(true);
            }
            case "zmien" -> {
                JFrame frameP = new JFrame("Lista przepisów");//Tworzymy nowe okno JFrame
                frameP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Ustawiamy domyślną operację zamknięcia na DISPOSE_ON_CLOSE
                JPanel panel = new JPanel();//Tworzymy nowy panel JPanel
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));//Ustawiamy layout panelu na BoxLayout

                panel.add(Box.createVerticalStrut(20));// margines górny

                //Tworzymy przyciski dla każdego przepisu
                for (String nazwaPrzepisu : nazwyPrzepisow) {
                    JButton buttonPrzepis = new JButton(nazwaPrzepisu);
                    buttonPrzepis.setPreferredSize(new Dimension(150, 75));
                    buttonPrzepis.setMaximumSize(buttonPrzepis.getPreferredSize());
                    buttonPrzepis.setMinimumSize(buttonPrzepis.getPreferredSize());
                    buttonPrzepis.setAlignmentX(Component.CENTER_ALIGNMENT);

                    panel.add(buttonPrzepis);
                    panel.add(Box.createVerticalStrut(15)); //odstępy między przyciskami

                    buttonPrzepis.addActionListener(e -> {
                        wybranaNazwaPrzepisu = nazwaPrzepisu;
                        zmien();
                        frameP.setVisible(false);
                    });
                }

                //Tworzymy przycisk "Cofnij"
                JButton cofnij_u = new JButton("<html>Cofnij</html>");
                cofnij_u.setPreferredSize(new Dimension(150, 75));
                cofnij_u.setMaximumSize(cofnij_u.getPreferredSize());
                cofnij_u.setMinimumSize(cofnij_u.getPreferredSize());
                cofnij_u.setAlignmentX(Component.CENTER_ALIGNMENT);
                cofnij_u.addActionListener(e -> {
                    GUI.wyswietlGUI();
                    frameP.setVisible(false);
                });
                panel.add(cofnij_u);

                //Dodajemy panel do JScrollPane, a JScrollPane do okna JFrame
                JScrollPane scrollPane = new JScrollPane(panel);
                frameP.getContentPane().add(scrollPane);
                frameP.getContentPane().setPreferredSize(new Dimension(500, 400));
                frameP.pack();
                frameP.setLocationRelativeTo(null);
                frameP.setVisible(true);
            }
        }
    }
    public void calosc(){
        //Tworzymy nowe okno JFrame
        JFrame frameC = new JFrame("Szczegóły przepisu " + wybranaNazwaPrzepisu);
        frameC.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Pobieramy składniki i przepis z bazy danych
        String skladniki = MySql.SelectS(wybranaNazwaPrzepisu);
        String przepis = MySql.SelectP(wybranaNazwaPrzepisu);

        //Tworzymy przycisk "Cofnij"
        JButton cofnij = new JButton("<html>Cofnij</html>>");
        cofnij.addActionListener( e -> {
            wyswietl("wyswietl");
            frameC.setVisible(false);
        });

        // Tworzymy JTextArea, aby wyświetlić składniki i przepis w wielu liniach
        JTextArea skladnikiTextArea = new JTextArea(skladniki);
        skladnikiTextArea.setWrapStyleWord(true);
        skladnikiTextArea.setLineWrap(true);
        skladnikiTextArea.setOpaque(false);
        skladnikiTextArea.setEditable(false);
        JScrollPane scrollSTA = new JScrollPane(skladnikiTextArea);
        scrollSTA.setPreferredSize(new Dimension(750, 150));
        scrollSTA.setMinimumSize(scrollSTA.getPreferredSize());
        scrollSTA.setMaximumSize(scrollSTA.getPreferredSize());

        JTextArea przepisTextArea = new JTextArea(przepis);
        przepisTextArea.setWrapStyleWord(true);
        przepisTextArea.setLineWrap(true);
        przepisTextArea.setOpaque(false);
        przepisTextArea.setEditable(false);
        JScrollPane scrollPTA = new JScrollPane(przepisTextArea);
        scrollPTA.setPreferredSize(new Dimension(750, 250));
        scrollPTA.setMinimumSize(scrollPTA.getPreferredSize());
        scrollPTA.setMaximumSize(scrollPTA.getPreferredSize());

        //Tworzymy panel JPanel, aby zawierać JTextAreas i przycisk "Cofnij"
        JPanel skladnikiPanel = new JPanel(new GridBagLayout());
        //Ustawiamy layout panelu na GridBagLayout
        GridBagConstraints GBC = new GridBagConstraints();
        GBC.gridx = 0;
        GBC.gridy = 0;
        GBC.gridwidth = 2;
        GBC.gridheight = 1;
        GBC.fill = GridBagConstraints.HORIZONTAL;
        GBC.insets = new Insets(5, 10, 5, 10);
        GBC.anchor = GridBagConstraints.WEST;
        GBC.weightx = 1;
        GBC.weighty = 0;

        //Dodajemy etykiety i JTextAreas do panelu
        skladnikiPanel.add(new JLabel("Nazwa przepisu: " + wybranaNazwaPrzepisu), GBC);
        GBC.gridy++;
        skladnikiPanel.add(new JLabel("Składniki:"), GBC);
        GBC.gridy++;
        skladnikiPanel.add(scrollSTA, GBC);
        GBC.gridy++;
        skladnikiPanel.add(new JLabel("Przygotowanie:"), GBC);
        GBC.gridy++;
        skladnikiPanel.add(scrollPTA, GBC);
        GBC.gridy++;
        GBC.fill = GridBagConstraints.NONE;
        GBC.weightx = 0;
        GBC.weighty = 0;
        skladnikiPanel.add(cofnij, GBC);

        //JScrollPane scrollPane = new JScrollPane(skladnikiPanel);
        //frameC.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frameC.add(skladnikiPanel);
        frameC.getContentPane().setPreferredSize(new Dimension(800, 550));
        frameC.pack();
        frameC.setLocationRelativeTo(null);
        frameC.setVisible(true);
    }
    public void usun() {
        //Tworzymy nowe okno JFrame
        JFrame frameU = new JFrame();
        frameU.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Tworzymy etykietę JLabel, aby zapytać użytkownika, czy na pewno chce usunąć przepis
        JLabel labelU = new JLabel("Czy na pewno chcesz usunąć ten przepis: ");
        JButton buttonP1 = new JButton("<html>Tak</html>");
        buttonP1.setBounds(40, 50, 85, 65);
        JButton buttonP2 = new JButton("<html>Nie</html>");
        buttonP2.setBounds(165, 50, 85, 65);

        //Dodajemy etykietę i przyciski do okna JFrame
        frameU.getContentPane().setLayout(null);
        frameU.add(labelU);
        labelU.setBounds(30, 10, 260, 30);

        //Dodajemy akcję do przycisków
        frameU.getContentPane().add(buttonP1);
        buttonP1.addActionListener(e -> {
            //Usuwanie przepisu z bazy danych
            MySql.DeleteP(wybranaNazwaPrzepisu);
            frameU.setVisible(false);
        });
        frameU.getContentPane().add(buttonP2);
        buttonP2.addActionListener(e -> {
            wyswietl("usun");
            frameU.setVisible(false);
        });

        frameU.getContentPane().setPreferredSize(new Dimension(300, 150));
        frameU.pack();
        frameU.setLocationRelativeTo(null);
        frameU.setVisible(true);

    }
    public void zmien() {
        //Tworzymy nowe okno JFrame
        JFrame frame = new JFrame("Dodaj przepis");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Pobieramy składniki i przepis z bazy danych
        String skladniki = MySql.SelectS(wybranaNazwaPrzepisu);
        String przepis = MySql.SelectP(wybranaNazwaPrzepisu);

        //Tworzymy JTextFields, aby użytkownik mógł zmienić nazwę przepisu
        JTextField nazwaTF = new JTextField(35);
        nazwaTF.setText(wybranaNazwaPrzepisu);

        //Tworzymy JTextAreas, aby użytkownik mógł zmienić składniki i treść przepisu
        JTextArea skladnikiTA = new JTextArea(15, 15);
        skladnikiTA.setWrapStyleWord(true);
        skladnikiTA.setLineWrap(true);
        skladnikiTA.setOpaque(true);
        skladnikiTA.setEditable(true);
        skladnikiTA.setText(skladniki);
        JScrollPane scrollSTA = new JScrollPane(skladnikiTA);
        scrollSTA.setPreferredSize(new Dimension(750, 150));
        scrollSTA.setMinimumSize(scrollSTA.getPreferredSize());
        scrollSTA.setMaximumSize(scrollSTA.getPreferredSize());

        JTextArea przepisTA = new JTextArea(15, 15);
        przepisTA.setWrapStyleWord(true);
        przepisTA.setLineWrap(true);
        przepisTA.setOpaque(true);
        przepisTA.setEditable(true);
        przepisTA.setText(przepis);
        JScrollPane scrollPTA = new JScrollPane(przepisTA);
        scrollPTA.setPreferredSize(new Dimension(750, 250));
        scrollPTA.setMinimumSize(scrollPTA.getPreferredSize());
        scrollPTA.setMaximumSize(scrollPTA.getPreferredSize());

        JButton cofnij = new JButton("<html>Cofnij</html>");
        cofnij.addActionListener( e -> {
            wyswietl("zmien");
            frame.setVisible(false);
        });

        //Tworzymy przycisk "Edytuj"
        JButton edytuj = new JButton("<html>Edytuj</html>");
        edytuj.addActionListener( e -> {
            String nazwaP = nazwaTF.getText();
            String skladnikiP = skladnikiTA.getText();
            String trescP = przepisTA.getText();
            if(nazwaP.isEmpty() || skladnikiP.isEmpty() || trescP.isEmpty()){
                JOptionPane.showMessageDialog(null, "Wszystkie pola muszą zawierać treść ", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                frame.setVisible(false);
            }else {
                UpdatePrzepis(wybranaNazwaPrzepisu, nazwaP, skladnikiP, trescP);
                frame.setVisible(false);
            }
        });

        //Tworzymy panel JPanel, aby zawierać JTextFields i przyciski
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints GBC_1 = new GridBagConstraints();
        GBC_1.gridx = 0;
        GBC_1.gridy = 0;
        GBC_1.gridwidth = 2;
        GBC_1.gridheight = 1;
        GBC_1.fill = GridBagConstraints.HORIZONTAL;
        GBC_1.insets = new Insets(5, 10, 5, 10);
        GBC_1.anchor = GridBagConstraints.WEST;
        GBC_1.weightx = 1;
        GBC_1.weighty = 0;

        //Dodajemy etykiety i JTextFields do panelu
        panel.add(new JLabel("Dodaj nazwa przepisu: "), GBC_1);
        GBC_1.gridy++;
        panel.add(nazwaTF, GBC_1);
        GBC_1.gridy++;
        panel.add(new JLabel("Dodaj lub zmień składniki przepisu: "), GBC_1);
        GBC_1.gridy++;
        panel.add(scrollSTA,GBC_1);
        GBC_1.gridy++;
        panel.add(new JLabel("Dodaj lub zmień treść przepisu: "), GBC_1);
        GBC_1.gridy++;
        panel.add(scrollPTA,GBC_1);

        GBC_1.gridy++;
        GBC_1.gridx = 0;
        GBC_1.gridwidth = 1;
        GBC_1.fill = GridBagConstraints.NONE;
        GBC_1.weightx = 0;
        GBC_1.weighty = 0;
        panel.add(cofnij, GBC_1);

        GBC_1.gridx++;
        panel.add(edytuj, GBC_1);

        frame.add(panel);

        frame.getContentPane().setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private static void UpdatePrzepis(String wybranaNazwaPrzepisu,String Pnazwa, String Pskladniki, String Ptresc) {
        //Aktualizujemy przepis w bazie danych
       MySql.updateP(wybranaNazwaPrzepisu, Pnazwa, Pskladniki, Ptresc);
       GUI.wyswietlGUI();
    }
}