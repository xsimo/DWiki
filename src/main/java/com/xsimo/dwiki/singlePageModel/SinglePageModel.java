package com.xsimo.dwiki.singlePageModel;

import com.xsimo.dwiki.common.DWikiException;

/**
 * Following the MVC Architecture, this class is the model.
 * @author Simon Arame
 *
 */

public class SinglePageModel 
{
	/*
	 * Fields
	 */
	/**
	 * Le catalogue des pages
	 */
	public DCatalog catalog; 
	/**
	 * la page courante
	 */
	public Page currentPage;
	
	/**
	 * Constructor
	 */
	
	public SinglePageModel(){
		this.catalog = new DCatalog();
	}
	
	/*
	 * Methods
	 */
	/**
	 * Cette m&eacute;thode provoque l'enregistrement de la version de page donnée en parametre.
	 * @return void
	 */
	public void Revert(int index, Page page) {
		page.revert(index);
		try {
			this.catalog.savePage(page.getId());
		} catch (DWikiException e) {
			e.printStackTrace();
		}
	}
}
