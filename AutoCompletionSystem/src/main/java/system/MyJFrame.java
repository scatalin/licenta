package system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class MyJFrame extends JFrame {

    AutoCompletionSystem system;
    String prefix = "";

    String text;
    // Variables declaration - do not modify
    private List<JLabel> label = new ArrayList<>();
    private JTextArea textArea;

    public MyJFrame() {
        system = new AutoCompletionSystemImpl();
        system.start();
        text = "Type your text here \n";
        initComponents();
    }

    public static void main(String[] args) {
        new MyJFrame().setVisible(true);

    }

    private void initComponents() {

        JScrollPane jScrollPane1 = new JScrollPane();
        textArea = new JTextArea();
        for (int i = 1; i <= 10; i++) {
            label.add(new JLabel());
        }

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setPreferredSize(new Dimension(600, 400));

        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                jTextArea1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(textArea);
        textArea.setText(text);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 11, 430, 290);

        for (int i = 0; i < 10; i++) {
            label.get(i).setText(i + ":" + i);
            getContentPane().add(label.get(i));
            label.get(i).setBounds(460, 20 + (i * 30), 110, 14);
        }

        pack();
    }

    private void jTextArea1KeyPressed(KeyEvent evt) {
        Character c = evt.getKeyChar();
        system.inputCharacter(c);
        text+=c;
        System.out.println(c);
        if ((c.compareTo('0') >= 0) && ((c.compareTo('9') <= 0))) {
            String s = "";
            switch (c) {
                case '0':
                    s = getJLableText(label.get(9));
                    break;
                case '1':
                    s = getJLableText(label.get(0));
                    break;
                case '2':
                    s = getJLableText(label.get(1));
                    break;
                case '3':
                    s = getJLableText(label.get(2));
                    break;
                case '4':
                    s = getJLableText(label.get(3));
                    break;
                case '5':
                    s = getJLableText(label.get(4));
                    break;
                case '6':
                    s = getJLableText(label.get(5));
                    break;
                case '7':
                    s = getJLableText(label.get(6));
                    break;
                case '8':
                    s = getJLableText(label.get(7));
                    break;
                case '9':
                    s = getJLableText(label.get(8));
                    break;
                default:
                    break;
            }
            system.selectCompletionWord(s);
            System.out.println("sugestia : " + s);
            if (!s.isEmpty()) {
                text = text.substring(0,text.length()-1);
                if(text.endsWith(prefix)){
                    int index = text.lastIndexOf(prefix);
                    text = text.substring(0,index);
                }
                text += s;
                textArea.setText(text);
            }
            List<String> suggestions = system.getFrazeCompletion(s);
            fillJLabels(suggestions);
        } else if (((c.compareTo('a') >= 0) && ((c.compareTo('z') <= 0))) || ((c.compareTo('A') >= 0) && (c.compareTo('Z') <= 0))) {
            prefix += c;
            List<String> suggestions = system.getWordCompletion(prefix);
            fillJLabels(suggestions);
        } else {
            prefix = "";
        }
    }

    private void fillJLabels(List<String> suggestions) {
        for (int i = 0; i < suggestions.size(); i++) {
            label.get(i).setText(i + ":" + suggestions.get(i));
        }
        for (int i = suggestions.size(); i < 10; i++) {
            label.get(i).setText(i + ":");
        }
    }

    private String getJLableText(JLabel jLabel) {
        String[] tokens = jLabel.getText().split(":");
        if (tokens.length > 1) {
            return tokens[1];
        }
        return "";
    }
    // End of variables declaration                   
}