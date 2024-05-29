package org.example.Frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchResultsPage extends BackgroundPanel {
    private JButton searchButton;
    private SearchResultsListener listener;

    public SearchResultsPage() {
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);

        searchButton = new JButton("Search in our library");
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(new Color(0, 0, 0, 100));
        searchButton.setFont(new Font("Serif", Font.BOLD, 24));
        searchButton.setPreferredSize(new Dimension(300, 50));

        buttonPanel.add(searchButton);
        add(buttonPanel, BorderLayout.CENTER);

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
