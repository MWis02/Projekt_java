import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try{
            for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
                if("Nimbus".equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            // Dostosowanie wyglądu
            UIManager.put("nimbusBase", Color.decode("#EADFB4"));
            UIManager.put("nimbusBlueGrey", Color.decode("#EADFB4"));
            UIManager.put("control", Color.decode("#EADFB4"));

            // Dostosowanie czcionki
            UIManager.put("Label.font", new Font("Calibri", Font.BOLD, 18));
            UIManager.put("Button.font", new Font("Calibri Light", Font.PLAIN, 14));
            UIManager.put("TextArea.font", new Font("Calibri Light", Font.PLAIN, 14));
            UIManager.put("TextField.font", new Font("Calibri Light", Font.PLAIN, 14));
            UIManager.put("JFrame.font", new Font("Calibri", Font.BOLD, 18));

            // Dostosowanie wyglądu JButton
            UIManager.put("Button.background", Color.decode("#9BB0C1"));
            UIManager.put("Button.foreground", Color.black);

            // Dostosowanie wyglądu JTextArea
            UIManager.put("TextArea.background", Color.decode("#F1F1F2"));
            UIManager.put("TextArea.foreground", Color.black);

            // Dostosowanie wyglądu JTextField
            UIManager.put("TextField.background", Color.decode("#F1F1F2"));
            UIManager.put("TextField.foreground", Color.black);

        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        GUI.wyswietlGUI();
    }
}