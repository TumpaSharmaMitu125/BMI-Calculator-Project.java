import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BMICalculator2 extends JFrame implements ActionListener {

    private JTextField heightField, weightField;
    private JLabel resultLabel;
    private JButton calculateButton, clearButton;

    public BMICalculator2() {
        setTitle("BMI Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        inputPanel.add(new JLabel("Height (m):"));
        heightField = new JTextField();
        inputPanel.add(heightField);

        inputPanel.add(new JLabel("Weight (kg):"));
        weightField = new JTextField();
        inputPanel.add(weightField);

        // Buttons
        calculateButton = new JButton("Calculate BMI");
        calculateButton.addActionListener(this);
        clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            heightField.setText("");
            weightField.setText("");
            resultLabel.setText("Your BMI will appear here...");
        });

        inputPanel.add(calculateButton);
        inputPanel.add(clearButton);

        // Result label
        resultLabel = new JLabel("Your BMI will appear here...", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Add panels
        add(inputPanel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double height = Double.parseDouble(heightField.getText());
            double weight = Double.parseDouble(weightField.getText());
            double bmi = weight / (height * height);

            String category;
            if (bmi < 18.5) category = "Underweight";
            else if (bmi < 24.9) category = "Normal weight";
            else if (bmi < 29.9) category = "Overweight";
            else category = "Obese";

            resultLabel.setText(String.format("BMI: %.2f — %s", bmi, category));

            JOptionPane.showMessageDialog(this,
                    "Your BMI: " + String.format("%.2f", bmi) + "\nCategory: " + category,
                    "Result",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numbers!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new BMICalculator2();
    }
}
