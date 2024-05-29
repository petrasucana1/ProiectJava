package org.example.frontend;

import org.example.entities.Book;
import org.example.repository.BookRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends BackgroundPanel {
    private JButton searchButton;
    private SearchResultsListener listener;

    private List<String> answers;

    private List<Book>books;

    public SearchResultsPage(List<String> selectedAnswers) {
        this.answers = selectedAnswers;
        books = getBooks();
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);

        searchButton = new JButton("Search in our library");
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(new Color(0, 0, 0, 100));
        searchButton.setFont(new Font("Serif", Font.BOLD, 24));
        searchButton.setPreferredSize(new Dimension(300, 50));

        buttonPanel.add(searchButton);
        add(buttonPanel, BorderLayout.NORTH);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LibraryTablePage tablePage = new LibraryTablePage(); // Inițializează clasa LibraryTablePage

                // Deschidem LibraryTablePage în aceeași fereastră
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(SearchResultsPage.this);
                frame.getContentPane().removeAll();
                frame.getContentPane().add(tablePage);
                frame.repaint();
                frame.revalidate();
            }
        });

        // Adaugă un JPanel pentru a afișa cărțile
        List<Book> selectedBooks= new ArrayList<>();

        for (String answ: answers) {
            switch (answ) {
                case "Before 1900": {
                    for (Book book : books) {
                        if (book.getPublicationDate().getYear() < 1900) {
                            selectedBooks.add(book);
                        }
                    }
                    break;
                }

                case "1900-1950": {
                    for (Book book : books) {
                        if (book.getPublicationDate().getYear() >= 1900 && book.getPublicationDate().getYear() <= 1950) {
                            selectedBooks.add(book);
                        }
                    }
                    break;
                }

                case "1950-2000": {
                    for (Book book : books) {
                        if (book.getPublicationDate().getYear() > 1950 && book.getPublicationDate().getYear() <= 2000) {
                            selectedBooks.add(book);
                        }
                    }
                    break;
                }

                case "After 2000": {
                    for (Book book : books) {
                        if (book.getPublicationDate().getYear() >= 1900 && book.getPublicationDate().getYear() > 2000) {
                            selectedBooks.add(book);
                        }
                    }
                    break;
                }
            }

            switch (answ) {
                case "Contemporary": {
                    for (Book book : selectedBooks) {
                        if (book.getPublicationDate().getYear() <= 1950)
                            selectedBooks.remove(book);
                    }

                    break;
                }

                case "Classics": {
                    for (Book book : selectedBooks) {
                        if (book.getPublicationDate().getYear() > 1950)
                            selectedBooks.remove(book);
                    }
                    break;
                }

                case "No preference": {

                    break;
                }
            }
        }



    }


    private List<Book> getBooks() {
        BookRepository bookRepository=new BookRepository();
        List<org.example.entities.Book> allBooks = bookRepository.selectAllBooks();
        return allBooks;
    }
    public void setSearchResultsListener(SearchResultsListener listener) {
        this.listener = listener;
    }

    private JLabel createTitleLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Serif", Font.BOLD, 24));
        return label;
    }

    public interface SearchResultsListener {
        void onSearchButtonClicked();
    }
}