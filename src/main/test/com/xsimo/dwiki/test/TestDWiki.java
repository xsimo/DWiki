
package com.xsimo.dwiki.test;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import com.xsimo.dwiki.DWiki;
import com.xsimo.dwiki.defaultView.MainFrame;
import com.xsimo.dwiki.singleUserControls.Controle;
import com.xsimo.dwiki.singleUserControls.Task2Formatter;

import junit.framework.TestCase;

/**
 * Les m&eacute;thodes sont des tests unitaires pour la classe Controle.java.<br>
 * Pour les tests de style "nested", 
 * mentionnons ce lien : 
 * <a href="http://www.artima.com/suiterunner/private2.html">www.artima.com/suiterunner/private2.html</a><br><br>
 * @author Simon Arame
 *  */
public class TestDWiki extends TestCase {
	protected Controle dw;
	/**
	 * Cette m&eacute;thode est ex&eacute;cut&eacute;e syst&eacute;matiquement
	 * avant chaque test unitaire pour initialiser
	 * l''environnement de test<br>TODO utiliser la m&eacute;thode automatique setUp()
	 */
	public void init() {
		dw = new Controle(new Task2Formatter());
		MainFrame vue = new MainFrame(dw);
		vue.pack();
		vue.setVisible(true);
	}
	
	/**
	 * Cette m&eacute;thode est ex&eacute;cut&eacute;e syst&eacute;matiquement
	 * apr&egrave;s chaque test unitaire pour r&eacute;initialiser
	 * l''environnement de test<br> utiliser la m&eacute;thode automatique tearDown()
	 */
	public void post(){
		dw = null;
	}
	/**
	 * Cette m&eacute;thode v&eacute;rifie que la sauvegarde s'&eacute;ffectue correctement.
	 * cette r&eacute;f&eacute;rence <a href="http://www.artima.com/suiterunner/private2.html">web</a>
	 * explique comment utilis&eacute; les "nested" test methods
	 * 
	 */

	public void testSaveCurrent(){
		init();
		dw.editCurrent();
		String s = "Nested test savecurrent";
		dw.TestPrivate(true,s);
		dw.saveCurrent();
		Page p = null;
		try {
			FileInputStream fis = new FileInputStream("pages/Index.dxt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			p = (Page) ois.readObject();
		} catch (Exception e) {
			assertTrue(false);
		}
		assertTrue(p.getText().contains(s));
		dw.editCurrent();
		dw.TestPrivate(false,s);
		dw.saveCurrent();
		post();
	}
	/**
	 * Cette m&eacute;thode v&eacute;rifie que la navigation est fonctionnelle.
	 * 
	 * 
	 */
	public void testGoTo(){
		init();
		dw.editCurrent();
		String s = "NestedTestGoTo";
		dw.TestPrivate(true,s);
		dw.saveCurrent();
		dw.goTo(s);
		Page p = dw.getCurrent();
		assertTrue(p.getId().equals(s));
		dw.goTo("Index");
		dw.editCurrent();
		assertTrue(dw.getCurrent().getId().equals("Index"));
		dw.TestPrivate(false,s);
		dw.saveCurrent();
		try{
			File f = new File("pages/"+s+".dxt");
			f.delete();
		}catch (Exception e){
			assertTrue(false);
		}
		post();
	}
	/**
	 * Cette m&eacute;thode verifie que l'edition initial m&egrave;ne bien a la page d'index.
	 * 
	 * 
	 */
	
	public void testShowCurrent(){
		init();
		dw.editCurrent();
		assertTrue(dw.getCurrent().getId().equals("Index"));
		post();
	}
	/**
	 * Cette m&eacute;thode verifie que le Jpanel de l'edition (DWiki.Editor) porte bien le bon titre.<br>
	 * 
	 * 
	 */
	public void testEditCurrent(){
		init();
		dw.editCurrent();
		String s = "nested test edit current";
		dw.TestPrivate(true, s);
		assertTrue(dw.getTitle().equals("Index - edition"));
		dw.cancel();
		post();
	}
	/**
	 * Cette m&eacute;thode verifie que les modifications au JtextArea de l'edition ne sont pas 
	 * enregistr&eacute; si l'on appuie sur cancel;
	 * 
	 */
	public void testCancel(){
		init();
		dw.editCurrent();
		String s = "Nested test cancel Button";
		if(dw.getCurrent().getText().contains(s)){
			System.out.println("probleme de persistance avec les test precedants");
			assertTrue(false);
		}
		dw.TestPrivate(true,s);
		dw.cancel();
		assertTrue(!dw.getCurrent().getText().contains(s));
		post();
	}
	
    /**
     * Test method for {@link ca.diro.dwiki.dwiki#bootstrap()}.
     */
    public void testBootstrap(){
       DWiki dw = new DWiki();
       dw.bootstrap();              
       assertEquals(dw.getCurrent().getId(),"Index");
     
      }
   
   
    /**
     * Test method for {@link ca.diro.dwiki.dwiki#changeDisplay()}.
     */
    public void testChangeDisplay(){
       DWiki dw = new DWiki();
       dw.setCurrentPage(new Page("id test","contenu de la page" ));
       dw.changeDisplay();
                 
       assertEquals(dw.getIdLabel().getText(),"id test");
      
       }
   
    
}

