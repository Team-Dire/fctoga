package view.utils;

import javax.swing.*;

public class CPFCNPJInputVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        JTextField textField = (JTextField) input;
        String text = textField.getText();
        return text.matches("\\d{11}|\\d{14}");
    }
}
