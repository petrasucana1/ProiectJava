package org.example.frontend;

import org.example.repository.AuthorRepository;
import org.example.repository.BookRepository;
import org.example.entities.Book;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibraryTablePage extends JPanel implements SearchResultsPage.SearchResultsListener {
    private List<Book> books;
    private JTextField authorField;
    private JTextField titleField;
    private JTextField yearField;
    private JComboBox<String> languageComboBox;
    private JButton searchButton;
    private JTable resultsTable;

    public LibraryTablePage() {
        books = getBooks();
        setLayout(new BorderLayout());


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

        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        resultsTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private List<Book> getBooks() {
        BookRepository bookRepository=new BookRepository();
        List<org.example.entities.Book> allBooks = bookRepository.selectAllBooks();
        return allBooks;
    }

    private void performSearch() {
        String authorName = authorField.getText();
        String title = titleField.getText();
        String year = yearField.getText();
        String language = (String) languageComboBox.getSelectedItem();

        AuthorRepository authorRepository =new AuthorRepository();

        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : books) {
            boolean matches = true;
            if (!authorName.isEmpty() && !authorRepository.findById(book.getAuthorId()).getName().toLowerCase().contains(authorName.toLowerCase())) {
                matches = false;
            }
            if (!title.isEmpty() && !book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matches = false;
            }
            if (!year.isEmpty()) {
                try {
                    int yearInt = Integer.parseInt(year);
                    int bookYear = book.getPublicationDate().getYear();
                    if (bookYear != yearInt) {
                        matches = false;
                    }
                } catch (NumberFormatException ex) {
                    matches = false;
                }
            }
            if (!language.isEmpty() && !book.getLanguage().equalsIgnoreCase(language)) {
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
        AuthorRepository authorRepository =new AuthorRepository();

        for (Book book : books) {
            Object[] row = {
                    book.getId(),
                    book.getTitle(),
                    authorRepository.findById(book.getAuthorId()).getName(),
                    book.getPublicationDate().getYear(),
                    book.getLanguage(),
                    book.getPublishingHouse()
            };
            model.addRow(row);
        }

        resultsTable.setModel(model);
    }

    @Override
    public void onSearchButtonClicked() {

    }
}
