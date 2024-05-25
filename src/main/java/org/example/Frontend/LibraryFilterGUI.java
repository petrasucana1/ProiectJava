package org.example.Frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Book {
    private int id;
    private String title;
    private String author;
    private LocalDate publicationDate;
    private String language;
    private String publishingHouse;

    public Book(int id, String title, String author, LocalDate publicationDate, String language, String publishingHouse) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.language = language;
        this.publishingHouse = publishingHouse;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public String getLanguage() {
        return language;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }
}

public class LibraryFilterGUI {
    private JFrame frame;
    private JTextField authorField;
    private JTextField titleField;
    private JTextField yearField;
    private JComboBox<String> languageComboBox;
    private JButton searchButton;
    private JTable resultsTable;
    private List<Book> books;

    public LibraryFilterGUI() {
        books = generateDummyBooks();

        frame = new JFrame("Library Book Filter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Titlu "Online Library Viewer"
        JLabel titleLabel = new JLabel("Online Library Viewer", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0x6c584c));
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(0xf0ead2));
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(0xf0ead2));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel filterPanel = new JPanel(new GridBagLayout());
        filterPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        filterPanel.setBackground(new Color(0xf0ead2));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        filterPanel.add(new JLabel("Author:"), gbc);
        authorField = new JTextField();
        authorField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        filterPanel.add(authorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        filterPanel.add(new JLabel("Title:"), gbc);
        titleField = new JTextField();
        titleField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        filterPanel.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        filterPanel.add(new JLabel("Year:"), gbc);
        yearField = new JTextField();
        yearField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        filterPanel.add(yearField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        filterPanel.add(new JLabel("Language:"), gbc);
        String[] languages = {"", "English", "French", "Spanish", "German", "Italian"};
        languageComboBox = new JComboBox<>(languages);
        languageComboBox.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        filterPanel.add(languageComboBox, gbc);

        searchButton = new JButton("Search");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        filterPanel.add(searchButton, gbc);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });

        mainPanel.add(filterPanel, gbc);

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        resultsTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        frame.add(scrollPane, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private List<Book> generateDummyBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Book One", "Author A", LocalDate.of(2020, 1, 1), "English", "Publisher 1"));
        books.add(new Book(2, "Book Two", "Author B", LocalDate.of(2019, 1, 1), "French", "Publisher 2"));
        books.add(new Book(3, "Book Three", "Author A", LocalDate.of(2021, 1, 1), "English", "Publisher 3"));
        books.add(new Book(4, "Book Four", "Author C", LocalDate.of(2018, 1, 1), "Spanish", "Publisher 4"));
        return books;
    }

    private void performSearch() {
        String authorName = authorField.getText();
        String title = titleField.getText();
        String year = yearField.getText();
        String language = (String) languageComboBox.getSelectedItem();

        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : books) {
            boolean matches = true;
            if (!authorName.isEmpty() && !book.getAuthor().toLowerCase().contains(authorName.toLowerCase())) {
                matches = false;
            }
            if (!title.isEmpty() && !book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matches = false;
            }
            if (!year.isEmpty()) {
                try {
                    int yearInt = Integer.parseInt(year);
                    if (book.getPublicationDate().getYear() != yearInt) {
                        matches = false;
                    }
                } catch (NumberFormatException e) {
                    matches = false;
                }
            }
            if (!language.isEmpty() && !book.getLanguage().toLowerCase().equals(language.toLowerCase())) {
                matches = false;
            }
            if (matches) {
                filteredBooks.add(book);
            }
        }

        displayResults(filteredBooks);
    }

    private void displayResults(List<Book> books) {
        String[] columns = {"ID", "Title", "Author", "Year", "Language", "Publishing House"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Book book : books) {
            Object[] row = {
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPublicationDate().getYear(),
                    book.getLanguage(),
                    book.getPublishingHouse()
            };
            model.addRow(row);
        }

        resultsTable.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LibraryFilterGUI();
            }
        });
    }
}
