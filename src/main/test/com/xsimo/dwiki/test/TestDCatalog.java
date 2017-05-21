package com.xsimo.dwiki.test;

import com.xsimo.dwiki.common.DWikiException;
import com.xsimo.dwiki.singlePageModel.DCatalog;
import com.xsimo.dwiki.singlePageModel.Page;

import junit.framework.TestCase;

/**
 * Initially intended to be a bonus, this class tests the methods of the DCatalog class.
 * @author Rabah Maache
 * @author Simon Arame
 */

public class TestDCatalog extends TestCase {

    /**
     * General test method for
     * {@link ca.diro.dwiki.DCatalog}.
     */
	public void testCatalog(){
		DCatalog dc = new DCatalog();
		try{
		@SuppressWarnings("unused")
		Page page = dc.createPage("PageDeTest","corps de texte avec un LienWikiAutomatique");
		}catch(DWikiException e){
		e.printStackTrace();
		}
		assertTrue(dc.existInCache("PageDeTest"));
		dc.deletePage("PageDeTest");
		assertTrue(!dc.existInCache("PageDeTest"));
		assertTrue(!dc.existInCache("LienWikiAutomatique"));
		}
    
    /**
     * Test method for
     * {@link ca.diro.dwiki.DCatalog#createPage(java.lang.String)}.
     */
    public void testCreatePageString() {
        
        DCatalog dc = new DCatalog();        
                
     try{
         Page page = dc.createPage("id page testCreatePageString");
         assertEquals(page.getId(),"id page testCreatePageString");
         assertEquals(page.getText(),"");
       }catch(DWikiException e){
        e.printStackTrace();
        }   
                
    }
    
    /**
     * Test method for
     * {@link ca.diro.dwiki.DCatalog#createPage(java.lang.String,java.lang.String)}.
     */
    public void testCreatePageStringString() {
        DCatalog dc = new DCatalog();        
                
     try{
         Page page = dc.createPage("id page1","Le corps de la premiere page");
         assertEquals(page.getId(),"id page1");
         assertEquals(page.getText(),"Le corps de la premiere page");
       }catch(DWikiException e){
        e.printStackTrace();
        }
       
       //On essai de cr�er une page avec un identificateur qui existe d�ja
       try{
             @SuppressWarnings("unused")
			Page page = dc.createPage("id page1","Le corps de la deuxieme page");    
             
             //Si on se rend ici, c'est que la tentative d'inserer une nouvelle page avec un id qui
             //existe d�ja a r�ussi, puisque l'exception DWikiException n'a pas �t� soulev�e..
             //On met alors une assertion toujours fausse pour signifier que le test a �chou�
             assertTrue(1 == 2);
             
           }catch(DWikiException e){
              e.printStackTrace();
            }
    
                
    }


    /**
     * Test method for
     * {@link ca.diro.dwiki.DCatalog#existPage(java.lang.String)}.
     */
    public void testExistPage() {
        DCatalog dc = new DCatalog();    
        
        try{
         @SuppressWarnings("unused")
		Page page = dc.createPage("id page testExistPage","");
         assertTrue(dc.existPage("id page testExistPage"));
        assertFalse(dc.existPage("id page qui n'existe pas"));

       }catch(DWikiException e){
        e.printStackTrace();
        }
            
    }
    
    /**
     * Test method for
     * {@link ca.diro.dwiki.DCatalog#existInCache(java.lang.String)}.
     */
    public void testExistInCache() {
        
        DCatalog dc = new DCatalog();    
        
        try{
         @SuppressWarnings("unused")
		Page page = dc.createPage("id page testExistInCache","");
         assertTrue(dc.existInCache("id page testExistInCache"));
         assertFalse(dc.existInCache("id page qui n'existe pas"));

       }catch(DWikiException e){
        e.printStackTrace();
        }
            
    }
    
    /**
     * Test method for
     * {@link ca.diro.dwiki.DCatalog#retrievePage(java.lang.String)}.
     */
    public void testRetrievePage() {
        DCatalog dc = new DCatalog();    
        
        try{
         Page createdPage   = dc.createPage("id page testRetrievePage","");
         Page retrievedPage = dc.retrievePage("id page testRetrievePage");
         assertEquals(createdPage,retrievedPage);
         
         Page pageInexistante = dc.getPage("id page inexistante");
         
         assertTrue(createdPage != pageInexistante);
         assertTrue(dc.existPage("id page inexistante"));
         assertEquals(pageInexistante.getText(),"");
         
       }catch(DWikiException e){
        e.printStackTrace();
        }
            
    }
    
    
    /**
     * Test method for
     * {@link ca.diro.dwiki.DCatalog#getPage(java.lang.String)}.
     */
    public void testGetPage() {
        DCatalog dc = new DCatalog();    
        
        try{
         Page createdPage    = dc.createPage("id page testGetPage");
         Page retrievedPage = dc.getPage("id page testGetPage");
         assertEquals(createdPage,retrievedPage);
         
         Page pageInexistante = dc.getPage("id page inexistante");
         
         assertTrue(createdPage != pageInexistante);
         assertTrue(dc.existPage("id page inexistante"));
         assertEquals(pageInexistante.getText(),"");
         
       }catch(DWikiException e){
        e.printStackTrace();
        }
            
    }
    
    
    /**
     * Test method for
     * {@link ca.diro.dwiki.DCatalog#deletePage(java.lang.String)}.
     */
    public void testDeletePage() {
        
        DCatalog dc = new DCatalog();    
        
        try{
/*tester si on veut supprimer une page qui n<existe pas**/
         Page createdPage = dc.createPage("id page testDeletePage","");
         Page deletedPage = dc.deletePage("id page testDeletePage");
         assertEquals(createdPage,deletedPage);
         assertFalse(dc.existPage("id page testDeletePage"));
       }catch(DWikiException e){
        e.printStackTrace();
        }
            
    }
    
    /**
     * Test method for
     * {@link ca.diro.dwiki.DCatalog#savePage(java.lang.String)}.
     */
    public void testSavePage() {
        DCatalog dc = new DCatalog();    
        
        try{
            
         Page page = dc.createPage("id page testSavePage","");
         
         page.changed();
         dc.savePage("id page testSavePage");        
         assertFalse(page.hasChanged());
         
       }catch(DWikiException e){
         e.printStackTrace();
        }
            
    }
    
    /**
     * Test method for
     * {@link ca.diro.dwiki.DCatalog#filename(java.lang.String)}.
     */
    public void testFilename() {
        
        DCatalog dc = new DCatalog();    
        
        try{
            
         @SuppressWarnings("unused")
		Page page = dc.createPage("id page testFilename","");
         assertEquals(dc.filename("id page testFilename"),"pages/id page testFilename.dxt");        
         
       }catch(DWikiException e){
         e.printStackTrace();
        }
            
    }



}