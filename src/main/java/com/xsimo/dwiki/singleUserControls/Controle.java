package com.xsimo.dwiki.singleUserControls;

import com.xsimo.dwiki.singlePageModel.*;
import com.xsimo.dwiki.common.DWikiException;
import com.xsimo.dwiki.defaultView.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.event.HyperlinkEvent;

/**
 * Following the MVC architecture design pattern, this class is the Controler.
 * @author Simon Arame
 *
 */
public class Controle{
	/*
	 * Fields 
	 */
	private SinglePageModel modele;
	private MainFrame vue;
	/**
	 * Le formatter pour l'affichage des pages en intérprétant la syntaxe
	 */
	public Formatter formatter;
	
	/**
	 * Constructor
	 */
	/**
	 * La syntaxe du formatter donné en paramètre sera celle appliqué par ce controleur.
	 */
	public Controle(Formatter formatter){
		this.modele = new SinglePageModel();
		this.formatter = formatter;
	}
	
	/*
	 * Methods
	 */
	/**
	 * set the view for this controler
	 */
	public void setVue(MainFrame vue) {
		this.vue = vue;
	}
	/**
	 * A call to this function will cause all suscribed views to navigate toward the given page parameter
	 * @param pageId
	 */
	public void goTo(String pageId) {
		try {
			modele.currentPage = modele.catalog.getPage(pageId);
			if (modele.currentPage.isNew()){
				vue.editMode(modele.currentPage);
				return;
			}
		
		} catch (DWikiException e) {
			e.printStackTrace();
			showCurrent();
		}
		showCurrent();
	}
	protected void showCurrent() {
		String formattedText = formatter.format(modele.currentPage,modele.currentPage.versionsCount); 
		vue.viewerMode(formattedText,modele.currentPage);
	}
	protected void saveCurrent() {
		try {
			modele.currentPage.setText(vue.editor.getFormText());
			modele.currentPage.setEditDate(new Date(System.currentTimeMillis()));
			modele.catalog.savePage(modele.currentPage.getId());
		} catch (DWikiException e) {
			//TODO "no page to save" msg
			e.printStackTrace();
		}
		showCurrent();
	}
	/**
	 * Cette m&eacute;thode affiche la version dont le num&eacute;ro est donn&eacute;e en param&egrave;tre
	 */
	public void showHistory(int r){
		vue.history.setText(formatter.format(modele.currentPage,r));
	}

/**
 * Le JComboBox doit &ecirc;tre &agrave; l'&eacute;coute des clics de l'utilisateur.
 */
	public final class comboAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int t = vue.history.cb.getSelectedIndex();
			showHistory(t);
		}
	}
	public final class sourisRevert implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println(vue.history.cb.getSelectedIndex());
			modele.Revert(vue.history.cb.getSelectedIndex(),modele.currentPage);
			goTo(modele.currentPage.getId());
		}
	}
	public final class sourisCancel implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			showCurrent();
		}
	}
	public final class sourisEdit implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			vue.editMode(modele.currentPage);
		}
	}
	public final class sourisHistory implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int r = modele.currentPage.versionsCount;
			//BLOC DU COMBO BOX
			{
				String[] choices = new String[modele.currentPage.versionsCount+1];
				int i;
				for(i=0;i<=modele.currentPage.versionsCount;i++){
					String d = modele.currentPage.getVersions().get(i).toString();
					System.out.println(d);
					choices[i] = d;
				}
				vue.history.populate(choices,new comboAction());
			}
			modele.currentPage.stackHistoricalVersion(r);
			vue.history.setText(formatter.format(modele.currentPage,modele.currentPage.versionsCount));
			modele.currentPage.unStackHistoricalVersion();
			vue.historyMode(modele.currentPage);
		}
	}
	public final class sourisSave implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			saveCurrent();
		}
	}
	public class SourisHyperlinkListener implements javax.swing.event.HyperlinkListener{
		public void hyperlinkUpdate(HyperlinkEvent e) {
			if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
				goTo(e.getDescription());
			}
		}
	}
}