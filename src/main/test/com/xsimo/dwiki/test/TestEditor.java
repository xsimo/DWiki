package com.xsimo.dwiki.test;

import java.util.Date; 

import com.xsimo.dwiki.defaultView.Editor;
import com.xsimo.dwiki.singlePageModel.Page;

import junit.framework.TestCase;
/**
 * Les m&eacute;thodes sont des tests unitaires pour la classe Editor.java.<br><br>
 * Cette classe est imputable � Selim Barama avant la s&eacute;paration de l'&eacute;quipe qui &eacute;tait 
 * aussi compos&eacute;e de Maxime Benoit-gagn&eacute; et Rabah Maache.
 * @author Selim Barama
 *
 */
public class TestEditor extends TestCase {
	
	/**
     * Test method for {@link ca.diro.dwiki.Editor#Editor()}.
     */
    public void testEditor() {
        Editor editor = new Editor();
        assertFalse(editor.getFormText() == null);
    }

        /**
         * Test method for {@link ca.diro.dwiki.Editor#setFormText(String)}.
         */
        public void testSetFormText() {
            Editor editor = new Editor();
            editor.setFormText("Test form Text");
            assertEquals("Test form Text", editor.getFormText());
        }
      
        /**
         * Test method for {@link ca.diro.dwiki.Editor#edit(Page)}.
         */
        public void testEdit(){
            Editor editor = new Editor();
            Page page = new Page("test", "Test Editor.edit");
          
            editor.edit(page);
            assertEquals("Test Editor.edit", editor.getFormText());
        }
        /**
         * Test method for {@link ca.diro.dwiki.Editor#save()}.
         */
        /*public void testSave()
        {
            Editor editor = new Editor();
            Page page = new Page("", "");
            editor.edit(page);
           
            editor.setFormText("Test save method");
            try{

                editor.save();
            }
            catch(DWikiException e)
            {
                e.printStackTrace();
            }
           
            assertEquals("Test save method",page.getText());
           
            // Devons nous tester la date aussi? une milliseconde c'est enorme en temps
            // d'ordinateur, mais ca peut quand meme changer entre quelques operations java.
            assertEquals(page.getEditDate(), new Date(System.currentTimeMillis()));
        }*/
        
        ///M�THODE SAVE D�PLAC� DANS CATALOG 
        
        
        /**
         * Test method for {@link ca.diro.dwiki.Editor#setPreferredSize(Dimension)}.
         */
        public void testSetPreferredSize() {
            Editor editor = new Editor();
            java.awt.Dimension d = new java.awt.Dimension();
            editor.setPreferredSize(d);
           assertTrue(editor.getPreferredSize().equals(d));
         
        }
}