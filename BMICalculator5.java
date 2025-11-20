import javax.swing.*;
import java.awt.*;

public class BMICalculator5 {

    public static void main(String[] args) {

        JFrame frame = new JFrame("BMI Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setLayout(new GridLayout(6, 2, 10, 10));

        // ---- Height Label (your requested format) ----
        JLabel heightLabel = new JLabel("Height (m/cm/ft):");
        JTextField heightField = new JTextField();

        // ---- Dropdown for selecting height unit ----
        String[] units = {"m", "cm", "ft"};
        JComboBox<String> unitBox = new JComboBox<>(units);

        // ---- Weight ----
        JLabel weightLabel = new JLabel("Weight (kg):");
        JTextField weightField = new JTextField();

        // ---- Buttons ----
        JButton calcButton = new JButton("Calculate BMI");
        JButton clearButton = new JButton("Clear");

        // ---- Result Label ----
        JLabel resultLabel = new JLabel("Your BMI will appear here...");

        // ---- BMI Calculation ----
        calcButton.addActionListener(e -> {
            try {
                double height = Double.parseDouble(heightField.getText());
                double weight = Double.parseDouble(weightField.getText());
                String unit = (String) unitBox.getSelectedItem();

                if (unit.equals("cm")) {
                    height = height / 100;
                } else if (unit.equals("ft")) {
                    height = height * 0.3048;
                }

                double bmi = weight / (height * height);
                resultLabel.setText("Your BMI: " + String.format("%.2f", bmi));

            } catch (Exception ex) {
                resultLabel.setText("Invalid Input!");
            }
        });

        // ---- Clear Function ----
        clearButton.addActionListener(e -> {
            heightField.setText("");
            weightField.setText("");
            resultLabel.setText("Your BMI will appear here...");
        });

        // ---- Add to GUI ----
        frame.add(heightLabel);
        frame.add(heightField);
        frame.add(new JLabel("Select unit:"));
        frame.add(unitBox);

        frame.add(weightLabel);
        frame.add(weightField);

        frame.add(calcButton);
        frame.add(clearButton);

        frame.add(new JLabel("")); // empty
        frame.add(resultLabel);

        frame.setVisible(true);
    }
}