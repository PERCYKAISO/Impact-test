import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class NumberRangeSummarizerGUI {

    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton summarizeButton;

    public NumberRangeSummarizerGUI() {
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Number Range Summarizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 400));

        // Set the look and feel of the GUI to the system's default look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add a label for the input field
        label = new JLabel("Enter a comma delimited list of numbers:");
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(label, gbc);

        // Add the input field
        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(400, 25));
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(inputField, gbc);

        // Add the summarize button
        summarizeButton = new JButton("Summarize");
        summarizeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc = new GridBagConstraints();
        gbc.gridx =2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(summarizeButton, gbc);

         // Add the output area
    outputArea = new JTextArea();
    outputArea.setEditable(false);
    outputArea.setFont(new Font("Arial", Font.PLAIN, 14));
    JScrollPane scrollPane = new JScrollPane(outputArea);
    scrollPane.setPreferredSize(new Dimension(400, 200));
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(0, 0, 0, 0);
    panel.add(scrollPane, gbc);

    // Add action listener to the summarize button
    summarizeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = inputField.getText();
            NumberRangeSummarizer summarizer = new NumberRangeSummarizerImpl();
            Collection<Integer> numbers = summarizer.collect(input);
            String summary = summarizer.summarizeCollection(numbers);
            outputArea.setText(summary);
        }
    });

    // Add the panel to the frame
    frame.getContentPane().add(panel, BorderLayout.CENTER);

    // Display the frame
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}

public static void main(String[] args) {
    new NumberRangeSummarizerGUI();
}
}  