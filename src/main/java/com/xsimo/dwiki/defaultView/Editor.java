package com.xsimo.dwiki.defaultView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.xsimo.dwiki.common.DWikiIPage;

/**
 * The Editor extends JPanel and uses a JTextArea which is in fact editable.
 * Minor modifications have been done including Panel Dimension. 
 * @author DIRO
 * @author Simon Arame
 */
public class Editor extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextArea textForm;

	public Editor() {
		this.setLayout(new BorderLayout());
		this.textForm = new JTextArea();
		this.textForm.setLineWrap(true);
		this.textForm.setWrapStyleWord(true);
		this.textForm.setEditable(true);
		this.setPreferredSize(new Dimension(675,400));
		this.add(this.textForm,BorderLayout.CENTER);
	}

	public void edit(DWikiIPage page) {
		setFormText(page.getText());
	}
/**
 * When a page is edited, this method calls {@link ca.diro.dwiki.Page#setText(String)} and {@link ca.diro.dwiki.Page#setEditDate(Date)}
 * @throws DWikiException if the current page has no modifications.
 */
	public String getFormText() {
		return this.textForm.getText();
	}

	public void setFormText(String text) {
		this.textForm.setText(text);
	}

	@Override
	public void setPreferredSize(Dimension d) {
		super.setPreferredSize(d);
		this.textForm.setPreferredSize(d);
	}

}
