package com.xsimo.dwiki;

import javax.swing.JFrame;

import com.xsimo.dwiki.defaultView.MainFrame;
import com.xsimo.dwiki.singleUserControls.Controle;
import com.xsimo.dwiki.singleUserControls.Task2Formatter;

/**
 * Desktop Wiki, a simple wiki to demonstrate object-oriented programming.
 * @author DIRO
 * @author Simon Arame
 * 
 */
public class DWiki{

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Controle controle = new Controle(new Task2Formatter());
		MainFrame vue = new MainFrame(controle);
		controle.setVue(vue);
		vue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vue.pack();
		vue.setVisible(true);
		controle.goTo("Index");
	}
}
