package org.example.frontend;

import org.example.entities.Book;
import org.example.repository.BookRepository;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibraryFilterGUI {
    private JFrame frame;
    private JPanel contentPanel;
    private JButton takeQuizButton;
    private List<Book> books;

    public LibraryFilterGUI() {
        frame = new JFrame("Library Book Filter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        contentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage image = ImageIO.read(new File("library.jpg"));
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        contentPanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome! Take our quiz to find out which book suits you the best!");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 30));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(welcomeLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);

        takeQuizButton = new JButton("Take Quiz");
        takeQuizButton.setFont(new Font("Serif", Font.BOLD, 24));
        takeQuizButton.setBackground(new Color(139, 69, 19));
        takeQuizButton.setForeground(Color.WHITE);
        takeQuizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openQuizPage();
            }
        });

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        buttonPanel.add(takeQuizButton);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(contentPanel);
        frame.setVisible(true);

        books = getBooks();
    }

    private List<Book> getBooks() {
        BookRepository bookRepository=new BookRepository();
        List<org.example.entities.Book> allBooks = bookRepository.selectAllBooks();
        return allBooks;
    }

    private void openQuizPage() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new QuizPage(books));
        frame.revalidate();
        frame.repaint();
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
