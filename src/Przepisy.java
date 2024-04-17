import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Przepisy extends GUI{
    private static String wybranaNazwaPrzepisu;
    public void dodaj(){
        JFrame frame = new JFrame("Dodaj przepis");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints GBC_1 = new GridBagConstraints();
        GBC_1.gridx = 0; //położenie w siatce
        GBC_1.gridy = 0; //położenie w siatce
        GBC_1.gridwidth = 2; //okreslenie zajmowanych komórek
        GBC_1.gridheight = 1; //określenie zajmowanych komórek
        GBC_1.fill = GridBagConstraints.HORIZONTAL; //wypełnienie przestrzenii
        GBC_1.insets = new Insets(5, 10, 5, 10); //marginesy - dostosuj do własnych preferencji
        GBC_1.anchor = GridBagConstraints.WEST; //przypiecię elementu do siatki
        GBC_1.weightx = 1; //reakcja na zmiany rozmiaru kontenera
        GBC_1.weighty = 0;

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

        frame.add(panel);
        frame.getContentPane().setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private static void wpiszPrzepis(String Pnazwa, String Pskladniki, String Ptresc) {
        MySql.insertP(Pnazwa, Pskladniki, Ptresc);
        GUI.wyswietlGUI();
    }
    public void wyswietl(String przycisk) {

        ArrayList<String> nazwyPrzepisow = MySql.selectN();

        switch (przycisk) {
            case "wyswietl" -> {
                JFrame frameP = new JFrame("Lista przepisów");
                frameP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                panel.add(Box.createVerticalStrut(20));// margines górny


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

                JScrollPane scrollPane = new JScrollPane(panel);

                frameP.getContentPane().add(scrollPane);
                frameP.getContentPane().setPreferredSize(new Dimension(500, 400));
                frameP.pack();
                frameP.setLocationRelativeTo(null);
                frameP.setVisible(true);
            }
            case "usun" -> {
                JFrame frameP = new JFrame("Lista przepisów");
                frameP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                panel.add(Box.createVerticalStrut(20));// margines górny


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

                JScrollPane scrollPane = new JScrollPane(panel);

                frameP.getContentPane().add(scrollPane);
                frameP.getContentPane().setPreferredSize(new Dimension(500, 400));
                frameP.pack();
                frameP.setLocationRelativeTo(null);
                frameP.setVisible(true);
            }
            case "zmien" -> {
                JFrame frameP = new JFrame("Lista przepisów");
                frameP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                panel.add(Box.createVerticalStrut(20));// margines górny


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
        JFrame frameC = new JFrame("Szczegóły przepisu " + wybranaNazwaPrzepisu);
        frameC.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String skladniki = MySql.SelectS(wybranaNazwaPrzepisu);
        String przepis = MySql.SelectP(wybranaNazwaPrzepisu);

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

        // Tworzymy JPanel, aby zawierać JTextArea z nazwą przepisu i składnikami
        JPanel skladnikiPanel = new JPanel(new GridBagLayout());
        GridBagConstraints GBC = new GridBagConstraints();

        GBC.gridx = 0; //położenie w siatce
        GBC.gridy = 0; //położenie w siatce
        GBC.gridwidth = 2; //okreslenie zajmowanych komórek
        GBC.gridheight = 1; //określenie zajmowanych komórek
        GBC.fill = GridBagConstraints.HORIZONTAL; //wypełnienie przestrzenii
        GBC.insets = new Insets(5, 10, 5, 10); //marginesy - dostosuj do własnych preferencji
        GBC.anchor = GridBagConstraints.WEST; //przypiecię elementu do siatki
        GBC.weightx = 1; //reakcja na zmiany rozmiaru kontenera
        GBC.weighty = 0; //zmieniamy na 0, aby tekst nie rozciągał wertykalnie

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
        JFrame frameU = new JFrame();
        frameU.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel labelU = new JLabel("Czy na pewno chcesz usunąć ten przepis: ");
        JButton buttonP1 = new JButton("<html>Tak</html>");
        buttonP1.setBounds(40, 50, 85, 65);
        JButton buttonP2 = new JButton("<html>Nie</html>");
        buttonP2.setBounds(165, 50, 85, 65);

        frameU.getContentPane().setLayout(null);
        frameU.add(labelU);
        labelU.setBounds(30, 10, 260, 30);

        frameU.getContentPane().add(buttonP1);
        buttonP1.addActionListener(e -> {
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
        JFrame frame = new JFrame("Dodaj przepis");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String skladniki = MySql.SelectS(wybranaNazwaPrzepisu);
        String przepis = MySql.SelectP(wybranaNazwaPrzepisu);

        JTextField nazwaTF = new JTextField(35);
        nazwaTF.setText(wybranaNazwaPrzepisu);

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

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints GBC_1 = new GridBagConstraints();
        GBC_1.gridx = 0; //położenie w siatce
        GBC_1.gridy = 0; //położenie w siatce
        GBC_1.gridwidth = 2; //okreslenie zajmowanych komórek
        GBC_1.gridheight = 1; //określenie zajmowanych komórek
        GBC_1.fill = GridBagConstraints.HORIZONTAL; //wypełnienie przestrzenii
        GBC_1.insets = new Insets(5, 10, 5, 10); //marginesy - dostosuj do własnych preferencji
        GBC_1.anchor = GridBagConstraints.WEST; //przypiecię elementu do siatki
        GBC_1.weightx = 1; //reakcja na zmiany rozmiaru kontenera
        GBC_1.weighty = 0;

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
       MySql.updateP(wybranaNazwaPrzepisu, Pnazwa, Pskladniki, Ptresc);
       GUI.wyswietlGUI();
    }
}