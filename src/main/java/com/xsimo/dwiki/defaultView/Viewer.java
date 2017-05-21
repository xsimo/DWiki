package com.xsimo.dwiki.defaultView;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import com.xsimo.dwiki.singleUserControls.Controle;
/**
 * The Viewer extends JPanel and uses a JTextPane that is set to be uneditable.
 * Minor modifications have been done concerning Components dimensions and layout. 
 * @author DIRO
 * @author Simon Arame
 */
public class Viewer extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextPane bodyText;
	
	public Viewer(final Controle controle) {
		this.setLayout(new BorderLayout());
		this.bodyText = new JTextPane();
		this.bodyText.setContentType("text/html");
		this.bodyText.setEditable(false);
		this.setPreferredSize(new Dimension(675,400));
		this.add(this.bodyText,BorderLayout.NORTH);
		this.bodyText.addHyperlinkListener(controle.new SourisHyperlinkListener()); 
	}

	public void show(String formattedText) {
		this.bodyText.setText(formattedText);
	}
	
	@Override
	public void setPreferredSize(Dimension d) {
		super.setPreferredSize(d);
		this.bodyText.setPreferredSize(d);
	}
}
