// Name Shayan ADil Khan
// id :  CA/S1/9549

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class HotelReservationSystem {
    private static final Map<String, Room> rooms = new HashMap<>();
    private static final Map<String, Booking> bookings = new HashMap<>();

    public static void main(String[] args) {
        // Sample room data
        rooms.put("101", new Room("101", "Single", 100.0, true));
        rooms.put("102", new Room("102", "Double", 150.0, true));
        rooms.put("103", new Room("103", "Suite", 250.0, false));
        rooms.put("104", new Room("104", "Single", 100.0, true));
        rooms.put("105", new Room("105", "Double", 150.0, true));
        rooms.put("106", new Room("106", "Suite", 250.0, true));
        rooms.put("107", new Room("107", "Single", 100.0, true));
        rooms.put("108", new Room("108", "Double", 150.0, true));
        rooms.put("109", new Room("109", "Suite", 250.0, true));
        rooms.put("110", new Room("110", "Single", 100.0, true));

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Hotel Reservation System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 700); // Increased size of the frame
            frame.add(new MainPanel());
            frame.setVisible(true);
        });
    }

    static class MainPanel extends JPanel {
        private CardLayout cardLayout;
        private JPanel cardPanel;
        private JTextArea detailsArea;
        private JTextField searchRoomField;
        private JTextField nameField;
        private JTextField roomField;
        private JTextField paymentField;

        public MainPanel() {
            setLayout(new BorderLayout());
            setBackground(new Color(144, 238, 144)); // Light green background color

            // Adding the label with your name and ID
            JLabel headerLabel = new JLabel("Shayan Adil Khan  ID: CA/S1/9549", SwingConstants.CENTER);
            headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
            headerLabel.setForeground(Color.BLACK);
            add(headerLabel, BorderLayout.NORTH);

            cardLayout = new CardLayout();
            cardPanel = new JPanel(cardLayout);

            cardPanel.add(createSearchPanel(), "Search");
            cardPanel.add(createReservationPanel(), "Reserve");
            cardPanel.add(createBookingDetailsPanel(), "Details");

            add(cardPanel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(new Color(144, 238, 144)); // Light green for button panel
            
            JButton searchButton = createStyledButton("Search Rooms");
            JButton reserveButton = createStyledButton("Make Reservation");
            JButton detailsButton = createStyledButton("Booking Details");

            searchButton.addActionListener(e -> cardLayout.show(cardPanel, "Search"));
            reserveButton.addActionListener(e -> cardLayout.show(cardPanel, "Reserve"));
            detailsButton.addActionListener(e -> cardLayout.show(cardPanel, "Details"));

            buttonPanel.add(searchButton);
            buttonPanel.add(reserveButton);
            buttonPanel.add(detailsButton);

            add(buttonPanel, BorderLayout.SOUTH); // Changed to SOUTH to avoid overlapping with the header label
        }

        private JButton createStyledButton(String text) {
            JButton button = new JButton(text);
            button.setBackground(Color.DARK_GRAY);
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            button.setPreferredSize(new Dimension(150, 40));
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            // Add a hover effect
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(Color.GRAY);
                }
                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(Color.DARK_GRAY);
                }
            });

            return button;
        }

        private JPanel createSearchPanel() {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            searchRoomField = new JTextField(20);  // Increased length of the search field
            searchRoomField.setFont(new Font("Arial", Font.BOLD, 16)); // Bold and increased font size
            JLabel searchLabel = new JLabel("Enter Room Number:");
            searchLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Bold and increased font size

            JButton searchButton = createStyledButton("Search");
            searchButton.addActionListener(new SearchButtonListener());

            JPanel inputPanel = new JPanel();
            inputPanel.add(searchLabel); // Label for input
            inputPanel.add(searchRoomField); // Adding text field to input panel
            inputPanel.add(searchButton); // Adding button to input panel

            detailsArea = new JTextArea(15, 50);
            detailsArea.setEditable(false);
            detailsArea.setFont(new Font("Arial", Font.PLAIN, 16)); // Increased font size for details

            panel.add(inputPanel, BorderLayout.NORTH); // Add input panel to the top of the search panel
            panel.add(new JScrollPane(detailsArea), BorderLayout.CENTER); // Add details area to the center

            return panel;
        }

        private JPanel createReservationPanel() {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(5, 2));

            JLabel nameLabel = new JLabel("Name:");
            nameLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Bold and increased font size
            panel.add(nameLabel);
            nameField = new JTextField();
            nameField.setFont(new Font("Arial", Font.BOLD, 16)); // Bold and increased font size
            panel.add(nameField);

            JLabel roomLabel = new JLabel("Room Number:");
            roomLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Bold and increased font size
            panel.add(roomLabel);
            roomField = new JTextField();
            roomField.setFont(new Font("Arial", Font.BOLD, 16)); // Bold and increased font size
            panel.add(roomField);

            JLabel paymentLabel = new JLabel("Payment Amount:");
            paymentLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Bold and increased font size
            panel.add(paymentLabel);
            paymentField = new JTextField();
            paymentField.setFont(new Font("Arial", Font.BOLD, 16)); // Bold and increased font size
            panel.add(paymentField);

            JButton reserveButton = createStyledButton("Reserve");
            reserveButton.addActionListener(new ReserveButtonListener());
            panel.add(reserveButton);

            return panel;
        }

        private JPanel createBookingDetailsPanel() {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            JTextArea bookingDetailsArea = new JTextArea(15, 50);
            bookingDetailsArea.setEditable(false);
            bookingDetailsArea.setFont(new Font("Arial", Font.PLAIN, 16)); // Increased font size for booking details
            panel.add(new JScrollPane(bookingDetailsArea), BorderLayout.CENTER);

            JButton refreshButton = createStyledButton("Refresh");
            refreshButton.addActionListener(e -> {
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, Booking> entry : bookings.entrySet()) {
                    sb.append("Booking ID: ").append(entry.getKey()).append("\n")
                      .append("Name: ").append(entry.getValue().getName()).append("\n")
                      .append("Room Number: ").append(entry.getValue().getRoomNumber()).append("\n")
                      .append("Amount Paid: $").append(entry.getValue().getAmountPaid()).append("\n")
                      .append("--------\n");
                }
                bookingDetailsArea.setText(sb.toString());
            });
            panel.add(refreshButton, BorderLayout.SOUTH);

            return panel;
        }

        private class SearchButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomNumber = searchRoomField.getText();
                Room room = rooms.get(roomNumber);
                if (room != null) {
                    detailsArea.setText("Room Number: " + room.getNumber() + "\n" +
                                         "Category: " + room.getCategory() + "\n" +
                                         "Price: $" + room.getPrice() + "\n" +
                                         "Available: " + (room.isAvailable() ? "Yes" : "No"));
                } else {
                    detailsArea.setText("Room not found.");
                }
            }
        }

        private class ReserveButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String roomNumber = roomField.getText();
                String paymentAmount = paymentField.getText();

                Room room = rooms.get(roomNumber);
                if (room != null) {
                    if (room.isAvailable()) {
                        double amount;
                        try {
                            amount = Double.parseDouble(paymentAmount);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Invalid payment amount.");
                            return;
                        }

                        bookings.put(roomNumber, new Booking(name, roomNumber, amount));
                        room.setAvailable(false);

                        JOptionPane.showMessageDialog(null, "Reservation successful.");
                        nameField.setText("");
                        roomField.setText("");
                        paymentField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Room is not available.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Room not found.");
                }
            }
        }
    }

    static class Room {
        private String number;
        private String category;
        private double price;
        private boolean available;

        public Room(String number, String category, double price, boolean available) {
            this.number = number;
            this.category = category;
            this.price = price;
            this.available = available;
        }

        public String getNumber() {
            return number;
        }

        public String getCategory() {
            return category;
        }

        public double getPrice() {
            return price;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }
    }

    static class Booking {
        private String name;
        private String roomNumber;
        private double amountPaid;

        public Booking(String name, String roomNumber, double amountPaid) {
            this.name = name;
            this.roomNumber = roomNumber;
            this.amountPaid = amountPaid;
        }

        public String getName() {
            return name;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public double getAmountPaid() {
            return amountPaid;
        }
    }
}