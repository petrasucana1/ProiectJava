package org.example.frontend;

import org.example.entities.Book;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class QuizPage extends BackgroundPanel {
    private List<Book> books;
    private JButton searchButton;

    // Radio button groups
    private ButtonGroup group1, group2, group4;
    private JRadioButton option1a, option1b, option1c, option1d;
    private JRadioButton option2a, option2b, option2c;
    private JRadioButton option4Yes, option4No;

    // Checkboxes
    private JCheckBox option3a, option3b, option3c, option3d, option3e, option3f, option3g;

    public QuizPage(List<Book> books) {
        this.books = books;

        setLayout(new BorderLayout());


        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        JLabel titleLabel = createTitleLabel("Answer the following questions:");
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);


        JPanel quizPanel = new JPanel(new GridBagLayout());
        quizPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Margini pentru spațiu
        quizPanel.setOpaque(false); // Transparent

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        quizPanel.add(createLabel("What is your preferred publication period for books?"), gbc);

        group1 = new ButtonGroup();
        option1a = createRadioButton("Before 1900");
        option1b = createRadioButton("1900-1950");
        option1c = createRadioButton("1950-2000");
        option1d = createRadioButton("After 2000");
        group1.add(option1a);
        group1.add(option1b);
        group1.add(option1c);
        group1.add(option1d);

        gbc.gridy = 1;
        quizPanel.add(option1a, gbc);
        gbc.gridy = 2;
        quizPanel.add(option1b, gbc);
        gbc.gridy = 3;
        quizPanel.add(option1c, gbc);
        gbc.gridy = 4;
        quizPanel.add(option1d, gbc);


        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        quizPanel.add(createLabel("Do you prefer books written by contemporary authors or classics?"), gbc);

        group2 = new ButtonGroup();
        option2a = createRadioButton("Contemporary");
        option2b = createRadioButton("Classics");
        option2c = createRadioButton("No preference");
        group2.add(option2a);
        group2.add(option2b);
        group2.add(option2c);

        gbc.gridy = 1;
        quizPanel.add(option2a, gbc);
        gbc.gridy = 2;
        quizPanel.add(option2b, gbc);
        gbc.gridy = 3;
        quizPanel.add(option2c, gbc);


        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        quizPanel.add(createLabel("Which language do you prefer for reading books?"), gbc);

        option3a = createCheckBox("English");
        option3b = createCheckBox("Swedish");
        option3c = createCheckBox("Russian");
        option3d = createCheckBox("Spanish");
        option3e = createCheckBox("French");
        option3f = createCheckBox("Portuguese");
        option3g = createCheckBox("Italian");

        gbc.gridy = 6;
        quizPanel.add(option3a, gbc);
        gbc.gridy = 7;
        quizPanel.add(option3b, gbc);
        gbc.gridy = 8;
        quizPanel.add(option3c, gbc);
        gbc.gridy = 9;
        quizPanel.add(option3d, gbc);
        gbc.gridy = 10;
        quizPanel.add(option3e, gbc);
        gbc.gridy = 11;
        quizPanel.add(option3f, gbc);
        gbc.gridy = 12;
        quizPanel.add(option3g, gbc);


        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        quizPanel.add(createLabel("Do you prefer books that are part of a series?"), gbc);

        group4 = new ButtonGroup();
        option4Yes = createRadioButton("Yes");
        option4No = createRadioButton("No");
        group4.add(option4Yes);
        group4.add(option4No);

        gbc.gridy = 6;
        quizPanel.add(option4Yes, gbc);
        gbc.gridy = 7;
        quizPanel.add(option4No, gbc);


        JScrollPane scrollPane = new JScrollPane(quizPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane, BorderLayout.CENTER);


        searchButton = new JButton("Search Book");
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(new Color(0, 0, 0, 100));
        searchButton.setFont(new Font("Serif", Font.BOLD, 24));
        searchButton.setPreferredSize(new Dimension(200, 50));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false); // Transparent
        buttonPanel.add(searchButton);
        add(buttonPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(QuizPage.this);
                frame.getContentPane().removeAll();
                List<String> selectedAnswers= collectSelectedOptions();
                frame.getContentPane().add(new SearchResultsPage(selectedAnswers));
                frame.repaint();
                frame.revalidate();
            }
        });
    }

    private JLabel createTitleLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Serif", Font.BOLD, 24));
        return label;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Serif", Font.BOLD, 18));
        return label;
    }

    private JRadioButton createRadioButton(String text) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setForeground(Color.WHITE);
        radioButton.setOpaque(false);
        radioButton.setFont(new Font("Serif", Font.BOLD, 18));
        return radioButton;
    }

    private JCheckBox createCheckBox(String text) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setForeground(Color.WHITE);
        checkBox.setOpaque(false); // Transparent
        checkBox.setFont(new Font("Serif", Font.BOLD, 18));
        return checkBox;
    }

    private List<String> collectSelectedOptions() {
        List<String> selectedOptions = new ArrayList<>();

        // Prima întrebare
        if (option1a.isSelected()) selectedOptions.add(option1a.getText());
        if (option1b.isSelected()) selectedOptions.add(option1b.getText());
        if (option1c.isSelected()) selectedOptions.add(option1c.getText());
        if (option1d.isSelected()) selectedOptions.add(option1d.getText());

        // A doua întrebare
        if (option2a.isSelected()) selectedOptions.add(option2a.getText());
        if (option2b.isSelected()) selectedOptions.add(option2b.getText());
        if (option2c.isSelected()) selectedOptions.add(option2c.getText());

        // A treia întrebare
        if (option3a.isSelected()) selectedOptions.add(option3a.getText());
        if (option3b.isSelected()) selectedOptions.add(option3b.getText());
        if (option3c.isSelected()) selectedOptions.add(option3c.getText());
        if (option3d.isSelected()) selectedOptions.add(option3d.getText());
        if (option3e.isSelected()) selectedOptions.add(option3e.getText());
        if (option3f.isSelected()) selectedOptions.add(option3f.getText());
        if (option3g.isSelected()) selectedOptions.add(option3g.getText());

        // A patra întrebare
        if (option4Yes.isSelected()) selectedOptions.add(option4Yes.getText());
        if (option4No.isSelected()) selectedOptions.add(option4No.getText());

        return selectedOptions;
    }
}
