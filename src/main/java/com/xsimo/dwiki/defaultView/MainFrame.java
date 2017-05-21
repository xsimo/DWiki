package com.xsimo.dwiki.defaultView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.DateFormat;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import com.xsimo.dwiki.common.DWikiIPage;
import com.xsimo.dwiki.common.Utilities;
import com.xsimo.dwiki.singleUserControls.Controle;

/**
 * Following the MVC architecture design pattern, this class is the view.
 * @author Simon Arame
 *
 */
public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	/*
	 * Fields
	 */
	public Editor editor;
	public Viewer viewer;
	public History history;
	
	/*
	 * Widgets for GUI.
	 */
	private JPanel mainPanel;
	public JPanel editorPanel;
	public JPanel viewerPanel;
	public JPanel historyPanel;
	public JLabel idLabel;
	public JLabel lastEditLabel;
	
		
	/**
	 * Constructor
	 */
	public MainFrame(Controle controle){
		super();
		initialize(controle);
	}
	
	/*
	 * Methods
	 */
	private void initialize(Controle controle){
		this.setSize(800,600);
		this.editor = new Editor();
		this.viewer = new Viewer(controle);
		this.history = new History(controle);
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.mainPanel.setPreferredSize(new Dimension(800, 600));

		// topPanel
		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(700, 25));
		this.idLabel = new JLabel();
		this.lastEditLabel = new JLabel();
		topPanel.add(this.idLabel);
		topPanel.add(Box.createHorizontalGlue());
		topPanel.add(new JLabel("Last edition:"));
		topPanel.add(this.lastEditLabel);
		this.mainPanel.add(topPanel, BorderLayout.NORTH);

		// centerPanel
		JPanel centerPanel = new JPanel();
		this.editorPanel = createEditorPanel(controle);
		this.viewerPanel = createViewerPanel(controle);
		this.historyPanel = createHistoryPanel(controle);
		this.historyPanel.setVisible(false);
		this.editorPanel.setVisible(false);
		centerPanel.add(this.viewerPanel);
		centerPanel.add(this.editorPanel);
		centerPanel.add(this.historyPanel);
		centerPanel.setPreferredSize(new Dimension(675, 450));
		this.mainPanel.add(centerPanel, BorderLayout.CENTER);

		// bottomPane
		String instructionsString = Utilities.lireFichier("./pages/instructions.dxt");
		JTextPane bottomPane = new JTextPane();
		bottomPane.setPreferredSize(new Dimension(675, 100));
		bottomPane.setContentType("text/html");
		bottomPane.setEditable(false);
		this.mainPanel.add(bottomPane, BorderLayout.SOUTH);
		bottomPane.setText(instructionsString);
		
		//conclude
		this.setContentPane(this.mainPanel);
	}

	protected JPanel createCentralPanel(JPanel textArea, JPanel buttons) {
		textArea.setPreferredSize(new Dimension(675, 400));
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(675, 450));
		panel.setLayout(new BorderLayout());
		buttons.setPreferredSize(new Dimension(675, 50));
		buttons.setLayout(new FlowLayout());
		panel.add(textArea, BorderLayout.CENTER);
		panel.add(buttons, BorderLayout.SOUTH);
		return panel;
	}
	protected JPanel createViewerPanel(Controle controle) {
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(controle.new sourisEdit());
		JButton historyButton = new JButton("History");
		historyButton.addActionListener(controle.new sourisHistory());
		JPanel buttons = new JPanel();
		buttons.add(editButton);
		buttons.add(historyButton);
		return createCentralPanel(this.viewer, buttons);
	}
	/**
	 * This method creates the main history panel (historyPanel) that encapsulates a smaller one (history)
	 */
	protected JPanel createHistoryPanel(Controle controle) {
		JButton revertButton = new JButton("Revert");
		revertButton.addActionListener(controle.new sourisRevert()); 
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(controle.new sourisCancel());
		JPanel buttons = new JPanel();
		buttons.add(revertButton);
		buttons.add(cancelButton);
		return createCentralPanel(this.history, buttons);
	}
	
	protected JPanel createEditorPanel(Controle controle) {
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(controle.new sourisSave());
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(controle.new sourisCancel());
		JPanel buttons = new JPanel();
		buttons.add(saveButton);
		buttons.add(cancelButton);
		return createCentralPanel(this.editor, buttons);
	}
	
	public void changeDisplay(DWikiIPage page) {
		this.idLabel.setText(page.getId());
		this.lastEditLabel.setText(DateFormat.getDateTimeInstance().format(
				page.getEditDate()));
	}
	public void viewerMode(String formattedText,DWikiIPage page){
		editorPanel.setVisible(false);
		historyPanel.setVisible(false);
		setTitle(page.getId()); 
		viewer.show(formattedText);
		changeDisplay(page);
		viewerPanel.setVisible(true);
	}
	public void editMode(DWikiIPage page) {
		viewerPanel.setVisible(false);
		setTitle(page.getId() + " - edition");
		editor.edit(page);
		changeDisplay(page);
		editorPanel.setVisible(true);
	}

	/**
	 * Cette m&eacute;thode provoque l'affichage de la version la plus recente de la page
	 * 
	 */
	public void historyMode(DWikiIPage page) {
		this.viewerPanel.setVisible(false);
		this.setTitle(page.getId() + " - historique");
		this.historyPanel.setVisible(true);
	}

}