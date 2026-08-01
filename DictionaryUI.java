package dictionary;

import javax.swing.*;
import java.awt.*;

public class DictionaryUI extends JFrame {
    private DictionaryManager dictionaryManager;

    private JTextField txtSearch;
    private JButton btnSearch;
    private JLabel lblResult;

    private JTextField txtNewTerm;
    private JTextField txtNewDefinition;
    private JButton btnAdd;

    public DictionaryUI() {
        dictionaryManager = new DictionaryManager();
        initUI();
    }

    private void initUI() {
        setTitle("Desktop Dictionary Application");
        setSize(480, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel searchPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search Word"));

        JPanel inputPanel = new JPanel(new FlowLayout());
        txtSearch = new JTextField(15);
        btnSearch = new JButton("Search");
        inputPanel.add(new JLabel("Term:"));
        inputPanel.add(txtSearch);
        inputPanel.add(btnSearch);

        lblResult = new JLabel("Definition will appear here...", SwingConstants.CENTER);
        lblResult.setFont(new Font("Arial", Font.BOLD, 14));
        lblResult.setForeground(Color.BLUE);

        searchPanel.add(inputPanel);
        searchPanel.add(lblResult);

        JPanel addPanel = new JPanel(new FlowLayout());
        addPanel.setBorder(BorderFactory.createTitledBorder("Add New Word"));

        txtNewTerm = new JTextField(8);
        txtNewDefinition = new JTextField(8);
        btnAdd = new JButton("Add");

        addPanel.add(new JLabel("Word:"));
        addPanel.add(txtNewTerm);
        addPanel.add(new JLabel("Definition:"));
        addPanel.add(txtNewDefinition);
        addPanel.add(btnAdd);

        add(searchPanel, BorderLayout.CENTER);
        add(addPanel, BorderLayout.SOUTH);

        btnSearch.addActionListener(e -> {
            String result = dictionaryManager.searchWord(txtSearch.getText());
            lblResult.setText(result);
        });

        btnAdd.addActionListener(e -> {
            boolean isSuccess = dictionaryManager.addWord(txtNewTerm.getText(), txtNewDefinition.getText());
            if (isSuccess) {
                JOptionPane.showMessageDialog(this, "Word added successfully!");
                txtNewTerm.setText("");
                txtNewDefinition.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add word! (Fields might be empty or word already exists)", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DictionaryUI ui = new DictionaryUI();
            ui.setVisible(true);
        });
    }
}