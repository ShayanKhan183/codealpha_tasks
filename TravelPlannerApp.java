// Name Shayan ADil Khan
// id :  CA/S1/9549

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class TravelPlannerApp extends JFrame {
    public TravelPlannerApp() {
        setTitle("Travel Itinerary Planner");
        setSize(800, 800);
        setLayout(null);
        getContentPane().setBackground(Color.lightGray);

        // Instructional Label
        JLabel instructionLabel = new JLabel("Note:");
        instructionLabel.setBounds(400, 540, 600, 30);
        instructionLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        instructionLabel.setForeground(Color.DARK_GRAY);

        JLabel instructionLabel1 = new JLabel("Please fill in the details to generate your travel itinerary.");
        instructionLabel1.setBounds(420, 570, 600, 30);
        instructionLabel1.setFont(new Font("Calibri", Font.PLAIN, 20));

        JTextField destinationField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField budgetField = new JTextField();
        JComboBox<String> preferenceBox = new JComboBox<>(new String[]{"Select one...", "Sightseeing", "Adventure", "Relaxation"});
        CurvedButton generateButton = new CurvedButton("Generate Itinerary");

        JLabel l11 = new JLabel("Welcome TO Shayan's TravelPlannerApp");
        l11.setBounds(350, 100, 700, 100);
        l11.setFont(new Font("Calibri", Font.BOLD, 30));

        JLabel l1 = new JLabel("Destination:");
        l1.setBounds(370, 220, 300, 30);
        l1.setFont(new Font("Calibri", Font.BOLD, 19));
        destinationField.setBounds(490, 220, 300, 30);

        JLabel l2 = new JLabel("Date (YYYY-MM-DD):");
        l2.setBounds(310, 280, 300, 30);
        l2.setFont(new Font("Calibri", Font.BOLD, 18));
        dateField.setBounds(490, 280, 300, 30);

        JLabel l3 = new JLabel("Budget ($):");
        l3.setBounds(370, 340, 300, 30);
        l3.setFont(new Font("Calibri", Font.BOLD, 18));
        budgetField.setBounds(490, 340, 300, 30);

        JLabel l4 = new JLabel("Preferences:");
        l4.setBounds(370, 400, 300, 30);
        l4.setFont(new Font("Calibri", Font.BOLD, 18));
        preferenceBox.setBounds(490, 400, 300, 30);

        generateButton.setBounds(550, 460, 200, 30);

        // Add ActionListener for the button
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String destination = destinationField.getText().trim();
                String date = dateField.getText().trim();
                String budget = budgetField.getText().trim();
                String preference = (String) preferenceBox.getSelectedItem();

                // Validate inputs
                if (destination.isEmpty() || date.isEmpty() || budget.isEmpty() || "Select one...".equals(preference)) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Input Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String weatherInfo = getWeatherInfo(destination);
                double budgetAmount = Double.parseDouble(budget);
                double dailyBudget = budgetAmount / 7; // Assume a week-long trip

                String itinerary = "Itinerary:\n" +
                        "Destination: " + destination + "\n" +
                        "Date: " + date + "\n" +
                        "Budget: $" + budget + " (Daily: $" + dailyBudget + ")\n" +
                        "Preference: " + preference + "\n" +
                        "Weather: " + weatherInfo + "\n";

                new ItineraryDisplay(itinerary);

                try {
                    Desktop.getDesktop().browse(new URL("https://www.google.com/maps/search/?api=1&query=" + destination).toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Add components to the frame
        add(instructionLabel);
        add(l11);
        add(l1);
        add(destinationField);
        add(l2);
        add(dateField);
        add(l3);
        add(budgetField);
        add(l4);
        add(preferenceBox);
        add(generateButton);
        add(instructionLabel1);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private String getWeatherInfo(String destination) {
        // Dummy implementation; replace with real API call
        return "Sunny, 25°C"; // Placeholder for demonstration
    }

    public static void main(String[] args) {
        new TravelPlannerApp();
    }

    class CurvedButton extends JButton {
        public CurvedButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setFont(new Font("Arial", Font.BOLD, 16));
            setBackground(new Color(0x2dce98));
            setForeground(Color.white);
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Rounded corners
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            // Prevent default border painting
        }
    }

    class ItineraryDisplay extends JFrame {
        public ItineraryDisplay(String itinerary) {
            setTitle("Your Itinerary");
            setSize(600, 400);
            setLayout(new BorderLayout());

            JTextArea textArea = new JTextArea(itinerary);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            add(scrollPane, BorderLayout.CENTER);

            JButton closeButton = new JButton("Close");
            closeButton.setBackground(new Color(0x2dce98));
            closeButton.setForeground(Color.white);
            closeButton.addActionListener(e -> dispose());
            add(closeButton, BorderLayout.SOUTH);

            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
        }
    }
}
