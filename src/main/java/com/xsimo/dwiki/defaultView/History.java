package com.xsimo.dwiki.defaultView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import com.xsimo.dwiki.singleUserControls.Controle;
import com.xsimo.dwiki.singleUserControls.Controle.comboAction;
/**
 * Cette classe h&eacute;rite de JPanel pour impl&eacute;menter la t&acirc;che 3.<br>
 * i.e. la sauvegarde, l'acc&egrave;s, et la r&eacute;cup&eacute;ration des versions ant&eacute;rieures des pages
 * @author Simon Arame
 */
public class History extends JPanel {
	
	private static final long serialVersionUID = 1L;

	/**
	 * The choice of using a JTextPane is inspired from the same choice in the viewer
	 */
	private JTextPane bodyText;
	public JComboBox cb;
	public History(Controle controle) {
		this.setPreferredSize(new Dimension(675,400));
		this.setLayout(new BorderLayout());
		this.bodyText = new JTextPane();
		this.bodyText.setContentType("text/html");
		this.bodyText.setEditable(false);
		this.bodyText.setPreferredSize(new Dimension(675,350));
		this.add(this.bodyText,BorderLayout.CENTER);
		this.bodyText.addHyperlinkListener(controle.new SourisHyperlinkListener());
		cb = new JComboBox();
		this.add(cb,BorderLayout.SOUTH);
	}
	public void populate(String[] choices,ActionListener a){
		this.cb = new JComboBox(choices);
		this.cb.setPreferredSize(new Dimension(675,25));
		//this.cb.setSelectedIndex(choices.length);
		this.cb.addActionListener(a);
		this.add(this.cb,BorderLayout.SOUTH);
	}
	public void setText(String text){
		this.bodyText.setText(text);
	}
}