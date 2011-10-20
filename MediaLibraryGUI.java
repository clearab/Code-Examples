import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class implements a full GUI for the Media Library.  
 * It contains the basic user interface code and uses an 
 * instance of the MediaLibrary class as its data source.
 * 
 *This GUI was provided as a shell, and was NOT entirely
 *coded by Andrew Clear
 */
public class MediaLibraryGUI extends JPanel {

	// Buttons for generating lists
	private JButton topTenButton;
	private JButton fullListButton;
	private JButton fullListByTitleButton;
	private JButton fullListByPriceJB;
	
	// The load data components
	private JTextField fileNameTF;
	private JButton loadButton;
	
	// Items for the CD music query
	private JTextField cdMusicRatingTF;
	private JButton cdMusicRatingJB;
	
	// Items for the DVD directing query
	private JTextField dvdDirectingRatingTF;
	private JButton dvdDirectingRatingJB;
	
	// Items for the list by "budget" query
	private JTextField budgetHighTF, budgetLowTF;
	private JButton budgetJB;
	
	// The text area for program output
	private JTextArea outputArea;
	
	// The media library
	private MediaLibraryModel library;
	
	/**
	 * Sets up the GUI for this panel, and instantiates a MediaLibrary
	 * as the data source.
	 */
	public MediaLibraryGUI(MediaLibraryModel lib) {
		
		// Store a reference to the MediaLibrary
		this.library = lib;
		
		// Set up the GUI
		this.setUpGUI();
		
		// Create the JFrame to hold this GUI
		JFrame frame = new JFrame("Media Library");
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * Builds the user interface for this panel.
	 */
	private void setUpGUI() {
		this.setPreferredSize(new Dimension(800,600));
		this.setLayout(new BorderLayout());
		
		Box inputPanel = Box.createVerticalBox();
		
		QueryListener listener = new QueryListener();
		
		// Create the panel that contains the load data options
		Box loadPanel = Box.createVerticalBox();
		loadPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(10, 10, 10, 10), 
				BorderFactory.createTitledBorder("Load Media Data") ) );
		
		// Set up a box containing the file loading options
		Box loadBox = Box.createHorizontalBox();
		loadBox.add(Box.createHorizontalStrut(15));
		loadBox.add(new JLabel("Media data file: "));
		this.fileNameTF = new JTextField(10);
		this.fileNameTF.setMaximumSize(this.fileNameTF.getPreferredSize());
		this.fileNameTF.setText("media.txt");
		loadBox.add(this.fileNameTF);
		loadBox.add(loadButton = new JButton("Load Data") );
		loadButton.addActionListener(new LoadDataListener());
		loadBox.add(Box.createHorizontalGlue());
		
		loadPanel.add(loadBox);
		
		// Create the panel that contains the query options, and set up
		// its border.
		Box queryPanel = Box.createVerticalBox();
		queryPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(10, 10, 10, 10), 
				BorderFactory.createTitledBorder("Query Media Library") ) );
		
		// Set up a box containing buttons for generating lists
		Box query1Box = Box.createHorizontalBox();
		fullListButton = new JButton("All");
		fullListButton.addActionListener(listener);
		topTenButton = new JButton("Top Ten Overall");
		topTenButton.addActionListener(listener);
		this.fullListByTitleButton = new JButton("All Items By Title");
		this.fullListByTitleButton.addActionListener(listener);
		query1Box.add(Box.createHorizontalStrut(15));
		query1Box.add(new JLabel("List: "));
		query1Box.add(fullListButton);
		query1Box.add(topTenButton);
		query1Box.add(this.fullListByPriceJB = new JButton("All Items By Price"));
		query1Box.add(this.fullListByTitleButton);
		this.fullListByPriceJB.addActionListener(listener);
		query1Box.add(Box.createHorizontalGlue());
		
		// Set up a box containing the query for CDs by music rating
		Box query2Box = Box.createHorizontalBox();
		query2Box.add(Box.createHorizontalStrut(15));
		query2Box.add(new JLabel("List CDs with a music rating of at least: "));
		this.cdMusicRatingTF = new JTextField(5);
		this.cdMusicRatingTF.setMaximumSize(this.cdMusicRatingTF.getPreferredSize());
		this.cdMusicRatingTF.setText("3");
		query2Box.add(this.cdMusicRatingTF);
		query2Box.add(cdMusicRatingJB = new JButton("List") );
		cdMusicRatingJB.addActionListener(listener);
		query2Box.add(Box.createHorizontalGlue());
		
		// Set up a box containing the query for DVDs by directing rating
		Box query3Box = Box.createHorizontalBox();
		query3Box.add(Box.createHorizontalStrut(15));
		query3Box.add(new JLabel("List DVDs with a directing rating of at least: "));
		this.dvdDirectingRatingTF = new JTextField(5);
		this.dvdDirectingRatingTF.setMaximumSize(this.dvdDirectingRatingTF.getPreferredSize());
		this.dvdDirectingRatingTF.setText("3");
		query3Box.add(this.dvdDirectingRatingTF);
		query3Box.add(this.dvdDirectingRatingJB = new JButton("List") );
		dvdDirectingRatingJB.addActionListener(listener);
		query3Box.add(Box.createHorizontalGlue());
		
		// Set up a box containing the query for all items within a price range
		Box query4Box = Box.createHorizontalBox();
		query4Box.add(Box.createHorizontalStrut(15));
		query4Box.add(new JLabel("List media items priced between "));
		this.budgetLowTF = new JTextField(5);
		this.budgetLowTF.setMaximumSize(this.budgetLowTF.getPreferredSize());
		this.budgetLowTF.setText("1.00");
		query4Box.add(this.budgetLowTF);
		query4Box.add(new JLabel(" and "));
		this.budgetHighTF = new JTextField(5);
		this.budgetHighTF.setMaximumSize(this.budgetHighTF.getPreferredSize());
		this.budgetHighTF.setText("10.00");
		query4Box.add(this.budgetHighTF);
		query4Box.add(this.budgetJB = new JButton("List"));
		budgetJB.addActionListener(listener);
		query4Box.add(Box.createHorizontalGlue());
		
		// Add each of the query boxes to the query panel with
		// some space in between each.
		queryPanel.add(Box.createVerticalStrut(10));
		queryPanel.add(query1Box);
		queryPanel.add(Box.createVerticalStrut(10));
		queryPanel.add(query2Box);
		queryPanel.add(Box.createVerticalStrut(10));
		queryPanel.add(query3Box);
		queryPanel.add(Box.createVerticalStrut(10));
		queryPanel.add(query4Box);
		queryPanel.add(Box.createVerticalStrut(10));
		
		inputPanel.add(loadPanel);
		inputPanel.add(queryPanel);
		
		// Add the input panel to this panel
		this.add(inputPanel,BorderLayout.NORTH);
		
		// Create the output text area, place in a scroll pane and 
		// add it to this panel.
		this.outputArea = new JTextArea();
		this.outputArea.setFont(new Font("monospaced", Font.PLAIN, 12));
		this.add(new JScrollPane(this.outputArea),BorderLayout.CENTER);		
	}
	
	private class LoadDataListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String fileName = fileNameTF.getText();
			outputArea.setText("Loading data from: " + fileName + "\n");
			library.loadLibrary(fileNameTF.getText());
		}
		
	}

	/**
	 * This class is responsible for responding to buttons in the GUI.
	 */
	private class QueryListener implements ActionListener {
		
		/**
		 * Responds to one of the buttons in the main GUI, by querying
		 * the MediaLibrary, and displaying the results into the 
		 * output text area.
		 * 
		 * @param e the ActionEvent generated with this event.
		 */
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			
			if( source == fullListButton ) {
				// Clear the text area and display the full list
				outputArea.setText("");
				outputArea.append(library.getFullList());
			} else if(source == topTenButton) {
				// Clear the text area and display the top ten list
				outputArea.setText("");
				outputArea.append(library.getTopTenList());
			} else if( source == cdMusicRatingJB ) {
				// Clear the text area, get the rating, and display the list
				// of CDs that have the given music rating or better.
				outputArea.setText("");
				int rating = Integer.parseInt(cdMusicRatingTF.getText());
				outputArea.append(library.getCDsByMusicRating(rating));
			} else if( source == dvdDirectingRatingJB ) {
				// Clear the text area, get the rating, and display the list
				// of DVDs that have the given directing rating or better.
				outputArea.setText("");
				int rating = Integer.parseInt(dvdDirectingRatingTF.getText());
				outputArea.append(library.getDVDsByDirectingRating(rating));
			} else if( source == budgetJB ) {
				// Clear the text area, get the low and high values, and display the list
				// of items that a price between low and high.
				outputArea.setText("");
				double low = Double.parseDouble(budgetLowTF.getText());
				double high = Double.parseDouble(budgetHighTF.getText());
				outputArea.append(library.getItemsWithinBudget(low,high));
			} else if( source == fullListByPriceJB ) {
				outputArea.setText("");
				outputArea.append(library.fullListByPrice());
			} else if( source == fullListByTitleButton ) {
				outputArea.setText("");
				outputArea.append(library.fullListByTitle());
			}
		}
	}
}
