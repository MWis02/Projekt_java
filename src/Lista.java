import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Lista extends GUI{
    private static String wybranaNazwaListy;
    private static String wybranaNazwaPrzepisu;
    private String wybranyPrzycisk;
    private String wybor;
    private List<String> importowaneSkladniki;
    private JTextArea trescL;
    public Lista(){
        this.importowaneSkladniki = new ArrayList<>();
    }
    public void wyswietl(String przycisk){
        ArrayList<String> nazwyList = MySql.selectL();
        ArrayList<String> nazwyPrzepisow = MySql.selectN();

        switch (przycisk) {
            case "wyswietl" -> {
                JFrame frameL = new JFrame("Listy");
                frameL.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                panel.add(Box.createVerticalStrut(20));// margines górny

                for (String nazwaListy : nazwyList) {

                    JButton buttonList = new JButton(nazwaListy);
                    buttonList.setPreferredSize(new Dimension(150, 75));
                    buttonList.setMaximumSize(buttonList.getPreferredSize());
                    buttonList.setMinimumSize(buttonList.getPreferredSize());
                    buttonList.setAlignmentX(Component.CENTER_ALIGNMENT);

                    panel.add(buttonList);
                    panel.add(Box.createVerticalStrut(15)); //odstępy między przyciskami

                    buttonList.addActionListener(e -> {
                        wybranaNazwaListy = nazwaListy;
                        calosc();
                        frameL.setVisible(false);
                    });
                }

                JButton cofnij_w = new JButton("<html>Cofnij</html>");
                cofnij_w.setPreferredSize(new Dimension(150, 75));
                cofnij_w.setMaximumSize(cofnij_w.getPreferredSize());
                cofnij_w.setMinimumSize(cofnij_w.getPreferredSize());
                cofnij_w.setAlignmentX(Component.CENTER_ALIGNMENT);
                cofnij_w.addActionListener(e -> {
                    GUI.wyswietlGUI();
                    frameL.setVisible(false);
                });

                panel.add(cofnij_w);

                JScrollPane scrollPane = new JScrollPane(panel);

                frameL.getContentPane().add(scrollPane);
                frameL.getContentPane().setPreferredSize(new Dimension(500, 400));
                frameL.pack();
                frameL.setLocationRelativeTo(null);
                frameL.setVisible(true);
            }
            case "usun" -> {
                JFrame frameL = new JFrame("Lista przepisów");
                frameL.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                panel.add(Box.createVerticalStrut(20));// margines górny


                for (String nazwaListy : nazwyList) {
                    JButton buttonLista = new JButton(nazwaListy);
                    buttonLista.setPreferredSize(new Dimension(150, 75));
                    buttonLista.setMaximumSize(buttonLista.getPreferredSize());
                    buttonLista.setMinimumSize(buttonLista.getPreferredSize());
                    buttonLista.setAlignmentX(Component.CENTER_ALIGNMENT);

                    panel.add(buttonLista);
                    panel.add(Box.createVerticalStrut(15)); //odstępy między przyciskami
                    buttonLista.addActionListener(e -> {
                        wybranaNazwaListy = nazwaListy;
                        usun();
                        frameL.setVisible(false);
                    });
                }

                JButton cofnij_u = new JButton("<html>Cofnij</html>");
                cofnij_u.setPreferredSize(new Dimension(150, 75));
                cofnij_u.setMaximumSize(cofnij_u.getPreferredSize());
                cofnij_u.setMinimumSize(cofnij_u.getPreferredSize());
                cofnij_u.setAlignmentX(Component.CENTER_ALIGNMENT);
                cofnij_u.addActionListener(e -> {
                    GUI.wyswietlGUI();
                    frameL.setVisible(false);
                });

                panel.add(cofnij_u);

                JScrollPane scrollPane = new JScrollPane(panel);

                frameL.getContentPane().add(scrollPane);
                frameL.getContentPane().setPreferredSize(new Dimension(500, 400));
                frameL.pack();
                frameL.setLocationRelativeTo(null);
                frameL.setVisible(true);
            }
            case "zmien" -> {
                JFrame frameL = new JFrame("Lista przepisów");
                frameL.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                panel.add(Box.createVerticalStrut(20));// margines górny


                for (String nazwaListy : nazwyList) {
                    JButton buttonLista = new JButton(nazwaListy);
                    buttonLista.setPreferredSize(new Dimension(150, 75));
                    buttonLista.setMaximumSize(buttonLista.getPreferredSize());
                    buttonLista.setMinimumSize(buttonLista.getPreferredSize());
                    buttonLista.setAlignmentX(Component.CENTER_ALIGNMENT);

                    panel.add(buttonLista);
                    panel.add(Box.createVerticalStrut(15)); //odstępy między przyciskami

                    buttonLista.addActionListener(e -> {
                        wybranaNazwaListy = nazwaListy;
                        zmien();
                        frameL.setVisible(false);
                    });
                }

                JButton cofnij_u = new JButton("<html>Cofnij</html>");
                cofnij_u.setPreferredSize(new Dimension(150, 75));
                cofnij_u.setMaximumSize(cofnij_u.getPreferredSize());
                cofnij_u.setMinimumSize(cofnij_u.getPreferredSize());
                cofnij_u.setAlignmentX(Component.CENTER_ALIGNMENT);
                cofnij_u.addActionListener(e -> {
                    GUI.wyswietlGUI();
                    frameL.setVisible(false);
                });

                panel.add(cofnij_u);

                JScrollPane scrollPane = new JScrollPane(panel);

                frameL.getContentPane().add(scrollPane);
                frameL.getContentPane().setPreferredSize(new Dimension(500, 400));
                frameL.pack();
                frameL.setLocationRelativeTo(null);
                frameL.setVisible(true);
            }
            case "import" -> {
                switch (wybor) {
                    case "przepis" -> {
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
                                importuj(wybranyPrzycisk);
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
                    case "lista" -> {
                        JFrame frameL = new JFrame("Lista przepisów");
                        frameL.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                        JPanel panel = new JPanel();
                        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                        panel.add(Box.createVerticalStrut(20));// margines górny


                        for (String nazwaListy : nazwyList) {
                            JButton buttonLista = new JButton(nazwaListy);
                            buttonLista.setPreferredSize(new Dimension(150, 75));
                            buttonLista.setMaximumSize(buttonLista.getPreferredSize());
                            buttonLista.setMinimumSize(buttonLista.getPreferredSize());
                            buttonLista.setAlignmentX(Component.CENTER_ALIGNMENT);

                            panel.add(buttonLista);
                            panel.add(Box.createVerticalStrut(15)); //odstępy między przyciskami

                            buttonLista.addActionListener(e -> {
                                wybranaNazwaListy = nazwaListy;
                                importuj(wybranyPrzycisk);
                                frameL.setVisible(false);
                            });
                        }

                        JButton cofnij_u = new JButton("<html>Cofnij</html>");
                        cofnij_u.setPreferredSize(new Dimension(150, 75));
                        cofnij_u.setMaximumSize(cofnij_u.getPreferredSize());
                        cofnij_u.setMinimumSize(cofnij_u.getPreferredSize());
                        cofnij_u.setAlignmentX(Component.CENTER_ALIGNMENT);
                        cofnij_u.addActionListener(e -> {
                            GUI.wyswietlGUI();
                            frameL.setVisible(false);
                        });

                        panel.add(cofnij_u);

                        JScrollPane scrollPane = new JScrollPane(panel);

                        frameL.getContentPane().add(scrollPane);
                        frameL.getContentPane().setPreferredSize(new Dimension(500, 400));
                        frameL.pack();
                        frameL.setLocationRelativeTo(null);
                        frameL.setVisible(true);
                    }
                }
            }
        }
    }
    public void importuj(String przycisk) {
        switch (przycisk) {
            case "dodaj"-> {
                switch (wybor) {
                    case "przepis" -> {
                        JFrame frame = new JFrame("Importuj składniki przepisu");
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                        String skladniki = MySql.SelectS(wybranaNazwaPrzepisu);

                        String[] sklad = skladniki.split("\n");
                        DefaultListModel<String> model = new DefaultListModel<>();

                        for (String skladnik : sklad) {
                            model.addElement(skladnik);
                        }

                        JList<String> checkList = new JList<>(model);
                        checkList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

                        JScrollPane scrollPane = new JScrollPane(checkList);
                        scrollPane.setPreferredSize(new Dimension(750, 400));
                        scrollPane.setMinimumSize(scrollPane.getPreferredSize());
                        scrollPane.setMaximumSize(scrollPane.getPreferredSize());

                        JPanel panel = new JPanel();
                        panel.setLayout(new GridBagLayout());

                        GridBagConstraints GBC = new GridBagConstraints();
                        GBC.gridx = 0; //położenie w siatce
                        GBC.gridy = 0; //położenie w siatce
                        GBC.gridwidth = 1; //okreslenie zajmowanych komórek
                        GBC.gridheight = 1; //określenie zajmowanych komórek
                        GBC.fill = GridBagConstraints.NONE; //wypełnienie przestrzenii
                        GBC.insets = new Insets(5, 10, 5, 10); //marginesy - dostosuj do własnych preferencji
                        GBC.anchor = GridBagConstraints.NORTHWEST; //przypiecię elementu do siatki
                        GBC.weightx = 1; //reakcja na zmiany rozmiaru kontenera
                        GBC.weighty = 0;

                        panel.add(new JLabel("Wybrany przepis: " + wybranaNazwaPrzepisu), GBC);
                        GBC.gridy++;
                        panel.add(scrollPane, GBC);


                        JButton cofnij = new JButton("<html>Cofnij</html>");
                        cofnij.addActionListener(e -> {
                            dodaj();
                            frame.setVisible(false);
                        });


                        JButton importuj = new JButton("<html>Importuj</html>");
                        importuj.addActionListener(e -> {
                            int[] wybraneSkladniki = checkList.getSelectedIndices();

                            for (int id : wybraneSkladniki) {
                                importowaneSkladniki.add(model.getElementAt(id));
                            }

                            dodaj();
                            frame.setVisible(false);
                        });

                        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        frame.add(buttonPanel, BorderLayout.SOUTH);
                        buttonPanel.add(cofnij);
                        buttonPanel.add(importuj);

                        frame.add(panel);
                        frame.getContentPane().setPreferredSize(new Dimension(800, 550));
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                    case "lista" ->{
                        JFrame frame = new JFrame("Importuj z listy");
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                        String informacje = MySql.SelectT(wybranaNazwaListy);

                        String[] info = informacje.split("\n");
                        DefaultListModel<String> model = new DefaultListModel<>();

                        for (String skladnik : info) {
                            model.addElement(skladnik);
                        }

                        JList<String> checkList = new JList<>(model);
                        checkList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

                        JScrollPane scrollPane = new JScrollPane(checkList);
                        scrollPane.setPreferredSize(new Dimension(750, 400));
                        scrollPane.setMinimumSize(scrollPane.getPreferredSize());
                        scrollPane.setMaximumSize(scrollPane.getPreferredSize());

                        JPanel panel = new JPanel();
                        panel.setLayout(new GridBagLayout());

                        GridBagConstraints GBC = new GridBagConstraints();
                        GBC.gridx = 0; //położenie w siatce
                        GBC.gridy = 0; //położenie w siatce
                        GBC.gridwidth = 1; //okreslenie zajmowanych komórek
                        GBC.gridheight = 1; //określenie zajmowanych komórek
                        GBC.fill = GridBagConstraints.NONE; //wypełnienie przestrzenii
                        GBC.insets = new Insets(5, 10, 5, 10); //marginesy - dostosuj do własnych preferencji
                        GBC.anchor = GridBagConstraints.NORTHWEST; //przypiecię elementu do siatki
                        GBC.weightx = 1; //reakcja na zmiany rozmiaru kontenera
                        GBC.weighty = 0;

                        panel.add(new JLabel("Wybrany przepis: " + wybranaNazwaPrzepisu), GBC);
                        GBC.gridy++;
                        panel.add(scrollPane, GBC);


                        JButton cofnij = new JButton("<html>Cofnij</html>");
                        cofnij.addActionListener(e -> {
                            dodaj();
                            frame.setVisible(false);
                        });


                        JButton importuj = new JButton("<html>Importuj</html>");
                        importuj.addActionListener(e -> {
                            int[] wybraneElementy = checkList.getSelectedIndices();

                            for (int id : wybraneElementy) {
                                importowaneSkladniki.add(model.getElementAt(id));
                            }

                            dodaj();
                            frame.setVisible(false);
                        });

                        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        frame.add(buttonPanel, BorderLayout.SOUTH);
                        buttonPanel.add(cofnij);
                        buttonPanel.add(importuj);

                        frame.add(panel);
                        frame.getContentPane().setPreferredSize(new Dimension(800, 550));
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                }
            }
            case "zmien"->{
                switch (wybor) {
                    case "przepis" -> {
                        JFrame frame = new JFrame("Importuj składniki przepisu");
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                        String skladniki = MySql.SelectS(wybranaNazwaPrzepisu);
                        String[] sklad = skladniki.split("\n");
                        DefaultListModel<String> model = new DefaultListModel<>();

                        for (String skladnik : sklad) {
                            model.addElement(skladnik);
                        }

                        JList<String> checkList = new JList<>(model);
                        checkList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

                        JScrollPane scrollPane = new JScrollPane(checkList);
                        scrollPane.setPreferredSize(new Dimension(750, 400));
                        scrollPane.setMinimumSize(scrollPane.getPreferredSize());
                        scrollPane.setMaximumSize(scrollPane.getPreferredSize());

                        JPanel panel = new JPanel();
                        panel.setLayout(new GridBagLayout());

                        GridBagConstraints GBC = new GridBagConstraints();
                        GBC.gridx = 0; //położenie w siatce
                        GBC.gridy = 0; //położenie w siatce
                        GBC.gridwidth = 1; //okreslenie zajmowanych komórek
                        GBC.gridheight = 1; //określenie zajmowanych komórek
                        GBC.fill = GridBagConstraints.NONE; //wypełnienie przestrzenii
                        GBC.insets = new Insets(5, 10, 5, 10); //marginesy - dostosuj do własnych preferencji
                        GBC.anchor = GridBagConstraints.NORTHWEST; //przypiecię elementu do siatki
                        GBC.weightx = 1; //reakcja na zmiany rozmiaru kontenera
                        GBC.weighty = 0;

                        panel.add(new JLabel("Wybrany przepis: " + wybranaNazwaPrzepisu), GBC);
                        GBC.gridy++;
                        panel.add(scrollPane, GBC);


                        JButton cofnij = new JButton("<html>Cofnij</html>");
                        cofnij.addActionListener(e -> {
                            zmien();
                            frame.setVisible(false);
                        });


                        JButton importuj = new JButton("<html>Importuj</html>");
                        importuj.addActionListener(e -> {
                            int[] wybraneSkladniki = checkList.getSelectedIndices();

                            for (int id : wybraneSkladniki) {
                                    importowaneSkladniki.add(model.getElementAt(id));
                            }

                            zmien();
                            frame.setVisible(false);
                        });

                        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        frame.add(buttonPanel, BorderLayout.SOUTH);
                        buttonPanel.add(cofnij);
                        buttonPanel.add(importuj);

                        frame.add(panel);
                        frame.getContentPane().setPreferredSize(new Dimension(800, 550));
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                    case "lista" ->{
                        JFrame frame = new JFrame("Importuj z listy");
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                        String informacje = MySql.SelectT(wybranaNazwaListy);
                        String[] info = informacje.split("\n");
                        DefaultListModel<String> model = new DefaultListModel<>();

                        for (String skladnik : info) {
                            model.addElement(skladnik);
                        }

                        JList<String> checkList = new JList<>(model);
                        checkList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

                        JScrollPane scrollPane = new JScrollPane(checkList);
                        scrollPane.setPreferredSize(new Dimension(750, 400));
                        scrollPane.setMinimumSize(scrollPane.getPreferredSize());
                        scrollPane.setMaximumSize(scrollPane.getPreferredSize());

                        JPanel panel = new JPanel();
                        panel.setLayout(new GridBagLayout());

                        GridBagConstraints GBC = new GridBagConstraints();
                        GBC.gridx = 0; //położenie w siatce
                        GBC.gridy = 0; //położenie w siatce
                        GBC.gridwidth = 1; //okreslenie zajmowanych komórek
                        GBC.gridheight = 1; //określenie zajmowanych komórek
                        GBC.fill = GridBagConstraints.NONE; //wypełnienie przestrzenii
                        GBC.insets = new Insets(5, 10, 5, 10); //marginesy - dostosuj do własnych preferencji
                        GBC.anchor = GridBagConstraints.NORTHWEST; //przypiecię elementu do siatki
                        GBC.weightx = 1; //reakcja na zmiany rozmiaru kontenera
                        GBC.weighty = 0;

                        panel.add(new JLabel("Wybrany przepis: " + wybranaNazwaListy), GBC);
                        GBC.gridy++;
                        panel.add(scrollPane, GBC);


                        JButton cofnij = new JButton("<html>Cofnij</html>");
                        cofnij.addActionListener(e -> {
                            zmien();
                            frame.setVisible(false);
                        });


                        JButton importuj = new JButton("<html>Importuj</html>");
                        importuj.addActionListener(e -> {
                            int[] wybraneElementy = checkList.getSelectedIndices();

                            for (int id : wybraneElementy) {
                                    importowaneSkladniki.add(model.getElementAt(id));
                            }
                            zmien();
                            frame.setVisible(false);
                        });

                        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        frame.add(buttonPanel, BorderLayout.SOUTH);
                        buttonPanel.add(cofnij);
                        buttonPanel.add(importuj);

                        frame.add(panel);
                        frame.getContentPane().setPreferredSize(new Dimension(800, 550));
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                }
            }
        }
    }
    public void calosc() {
        JFrame frameC = new JFrame("Szczegóły przepisu " + wybranaNazwaListy);
        frameC.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String tresc = MySql.SelectT(wybranaNazwaListy);

        JButton cofnij = new JButton("<html>Cofnij</html>>");
        cofnij.addActionListener( e -> {
            wyswietl("wyswietl");
            frameC.setVisible(false);
        });


        JTextArea trescTextArea = new JTextArea(tresc);
        trescTextArea.setWrapStyleWord(true);
        trescTextArea.setLineWrap(true);
        trescTextArea.setOpaque(false);
        trescTextArea.setEditable(false);
        JScrollPane scrollTTA = new JScrollPane(trescTextArea);
        scrollTTA.setPreferredSize(new Dimension(750, 450));
        scrollTTA.setMinimumSize(scrollTTA.getPreferredSize());
        scrollTTA.setMaximumSize(scrollTTA.getPreferredSize());

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

        skladnikiPanel.add(new JLabel("Nazwa listy: " + wybranaNazwaListy), GBC);
        GBC.gridy++;
        skladnikiPanel.add(new JLabel("Treść:"), GBC);
        GBC.gridy++;
        skladnikiPanel.add(scrollTTA, GBC);
        GBC.gridy++;
        GBC.fill = GridBagConstraints.NONE;
        GBC.weightx = 0;
        GBC.weighty = 0;
        skladnikiPanel.add(cofnij, GBC);

        JScrollPane scrollPane = new JScrollPane(skladnikiPanel);
        frameC.getContentPane().add(scrollPane, BorderLayout.CENTER);

        frameC.getContentPane().setPreferredSize(new Dimension(800, 600));
        frameC.pack();
        frameC.setLocationRelativeTo(null);
        frameC.setVisible(true);
    }
    public void usun() {
        JFrame frameU = new JFrame();
        frameU.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel labelU = new JLabel("Czy na pewno chcesz usunąć tą listę: ");
        JButton buttonP1 = new JButton("<html>Tak</html>");
        buttonP1.setBounds(40, 50, 85, 65);
        JButton buttonP2 = new JButton("<html>Nie</html>");
        buttonP2.setBounds(165, 50, 85, 65);

        frameU.getContentPane().setLayout(null);
        frameU.add(labelU);
        labelU.setBounds(30, 10, 260, 30);

        frameU.getContentPane().add(buttonP1);
        buttonP1.addActionListener(e -> {
            MySql.DeleteL(wybranaNazwaListy);
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
        JFrame frame = new JFrame("Edytuj Listę");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String tresc = MySql.SelectT(wybranaNazwaListy);

        JTextField nazwaTF = new JTextField(35);
        nazwaTF.setText(wybranaNazwaListy);

        trescL = new JTextArea(55, 150);
        trescL.append(tresc);
        trescL.setWrapStyleWord(true);
        trescL.setLineWrap(true);
        trescL.setOpaque(true);
        trescL.setEditable(true);


        for (Object skladnik : importowaneSkladniki) {
            trescL.append(skladnik + "\n");
        }

        JScrollPane scrollTTA = new JScrollPane(trescL);
        scrollTTA.setPreferredSize(new Dimension(750, 400));
        scrollTTA.setMinimumSize(scrollTTA.getPreferredSize());
        scrollTTA.setMaximumSize(scrollTTA.getPreferredSize());

        JButton cofnij = new JButton("<html>Cofnij</html>");
        cofnij.addActionListener( e -> {
            importowaneSkladniki.clear();
            GUI.wyswietlGUI();
            frame.setVisible(false);
        });



        JButton edytuj = new JButton("<html>Edytuj</html>");
        edytuj.addActionListener( e -> {
            String nazwaL = nazwaTF.getText();
            String tresclisty = trescL.getText();
            if(nazwaL.isEmpty() || tresclisty.isEmpty()){
                JOptionPane.showMessageDialog(null, "Wszystkie pola muszą zawierać treść ", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                frame.setVisible(false);
            }else {
                UpdateLista(wybranaNazwaListy, nazwaL, tresclisty);
                importowaneSkladniki.clear();
                frame.setVisible(false);
            }
        });

        JButton importujSklad = new JButton("<html>Importuj składniki<br> z przepisu</html>");
        importujSklad.addActionListener(e -> {
            wybierzListedoImportu("zmien");
            frame.setVisible(false);
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

        panel.add(new JLabel("Zmień nazwę listy: "), GBC_1);
        GBC_1.gridy++;
        panel.add(nazwaTF, GBC_1);
        GBC_1.gridy++;
        panel.add(new JLabel("Dodaj lub zmień treść listy: "), GBC_1);
        GBC_1.gridy++;
        panel.add(scrollTTA,GBC_1);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        frame.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(cofnij);
        buttonPanel.add(edytuj);
        buttonPanel.add(importujSklad);

        frame.add(panel);
        frame.getContentPane().setPreferredSize(new Dimension(800, 550));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private void UpdateLista(String wybranaNazwaListy, String nazwaL, String trescL) {
        MySql.updateL(wybranaNazwaListy, nazwaL, trescL);
        GUI.wyswietlGUI();
    }
    public void dodaj() {
        JFrame frameL = new JFrame("Dodaj listę");
        frameL.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameL.getContentPane().setPreferredSize(new Dimension(800, 550));

        JTextField nazwaL =  new JTextField(15);

        trescL = new JTextArea(55, 150);
        trescL.setWrapStyleWord(true);
        trescL.setLineWrap(true);
        trescL.setOpaque(true);
        trescL.setEditable(true);

        for (Object skladnik : importowaneSkladniki) {
            trescL.append(skladnik + "\n");
        }

        JButton cofnij = new JButton("<html>Cofnij</html>");
        cofnij.addActionListener(e -> {
            GUI.wyswietlGUI();
            frameL.setVisible(false);
            importowaneSkladniki.clear();
        });

        JButton dodaj = new JButton("<html>Dodaj</html>");
        dodaj.addActionListener(e -> {
            String nazwa = nazwaL.getText();
            String tresc = trescL.getText();
            if (nazwa.isEmpty() || tresc.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Wszystkie pola muszą zawierać treść", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                dodaj();
                frameL.setVisible(false);
            } else {
                wpiszListę(nazwa, tresc);
                importowaneSkladniki.clear();
                frameL.setVisible(false);
            }
        });

        JButton importujSklad = new JButton("<html>Importuj składniki<br> z przepisu</html>");
        importujSklad.addActionListener(e -> {
            wybierzListedoImportu("dodaj");
            frameL.setVisible(false);
        });

        JPanel panel = new JPanel(new GridBagLayout());
        frameL.add(panel);

        JScrollPane scrollPane = new JScrollPane(trescL);

        GridBagConstraints GBC = new GridBagConstraints();
        GBC.gridx = 0; // położenie w siatce
        GBC.gridy = 0; // położenie w siatce
        GBC.gridwidth = 1; // określenie zajmowanych komórek
        GBC.gridheight = 1; // określenie zajmowanych komórek
        GBC.fill = GridBagConstraints.HORIZONTAL; // wypełnienie przestrzeni
        GBC.insets = new Insets(5, 10, 5, 10); // marginesy - dostosuj do własnych preferencji
        GBC.anchor = GridBagConstraints.WEST; // przypięcie elementu do siatki
        GBC.weightx = 1; // reakcja na zmiany rozmiaru kontenera
        GBC.weighty = 0;

        panel.add(new JLabel("Dodaj nazwę listy"), GBC);
        GBC.gridy++;
        panel.add(nazwaL, GBC);
        GBC.gridy++;
        panel.add(new JLabel("Dodaj treść listy"), GBC);
        GBC.gridy++;
        GBC.fill = GridBagConstraints.BOTH;
        GBC.weightx = 1;
        GBC.weighty = 1;
        panel.add(scrollPane, GBC);
        GBC.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        frameL.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(cofnij);
        buttonPanel.add(dodaj);
        buttonPanel.add(importujSklad);

        frameL.pack();
        frameL.setLocationRelativeTo(null);
        frameL.setVisible(true);

    }
    public void wybierzListedoImportu(String przycisk){
        switch (przycisk) {
            case "dodaj" -> {
                JFrame frameU = new JFrame();
                frameU.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JLabel labelU = new JLabel("Wybierz listę do importu ");
                JButton buttonP1 = new JButton("<html>Z przepisów</html>");
                buttonP1.setBounds(40, 50, 85, 65);
                JButton buttonP2 = new JButton("<html>Z list</html>");
                buttonP2.setBounds(165, 50, 85, 65);

                frameU.getContentPane().setLayout(null);
                frameU.add(labelU);
                labelU.setBounds(30, 10, 260, 30);

                frameU.getContentPane().add(buttonP1);
                buttonP1.addActionListener(e -> {
                    wybor = "przepis";
                    wybranyPrzycisk = "dodaj";
                    wyswietl("import");
                    frameU.setVisible(false);
                });
                frameU.getContentPane().add(buttonP2);
                buttonP2.addActionListener(e -> {
                    wybor = "lista";
                    wybranyPrzycisk = "dodaj";
                    wyswietl("import");
                    frameU.setVisible(false);

                });

                frameU.getContentPane().setPreferredSize(new Dimension(300, 150));
                frameU.pack();
                frameU.setLocationRelativeTo(null);
                frameU.setVisible(true);
            }
            case "zmien" -> {
                JFrame frameU = new JFrame();
                frameU.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JLabel labelU = new JLabel("Wybierz listę do importu ");
                JButton buttonP1 = new JButton("<html>Z przepisów</html>");
                buttonP1.setBounds(40, 50, 85, 65);
                JButton buttonP2 = new JButton("<html>Z list</html>");
                buttonP2.setBounds(165, 50, 85, 65);

                frameU.getContentPane().setLayout(null);
                frameU.add(labelU);
                labelU.setBounds(30, 10, 260, 30);

                frameU.getContentPane().add(buttonP1);
                buttonP1.addActionListener(e -> {
                    wybor = "przepis";
                    wybranyPrzycisk = "zmien";
                    wyswietl("import");
                    frameU.setVisible(false);
                });
                frameU.getContentPane().add(buttonP2);
                buttonP2.addActionListener(e -> {
                    wybor = "lista";
                    wybranyPrzycisk = "zmien";
                    wyswietl("import");
                    frameU.setVisible(false);
                });

                frameU.getContentPane().setPreferredSize(new Dimension(300, 150));
                frameU.pack();
                frameU.setLocationRelativeTo(null);
                frameU.setVisible(true);
            }
        }
    }
    static public void wpiszListę(String Wnazwa, String Wtresc){
        MySql.insertL(Wnazwa, Wtresc);
        GUI.wyswietlGUI();
    }
}
