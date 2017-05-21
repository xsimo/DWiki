package com.xsimo.dwiki.singlePageModel;

import com.xsimo.dwiki.common.DWikiException;

/**
 * A Catalog has the responsibility to create pages and maintain existing pages.
 * Implementation of Catalog may vary, but they must perform at least a
 * persistence concern. More elaborate Catalogs can implement cache or history
 * concerns.
 * 
 * @author deniersi
 */
public interface Catalog {

	/**
	 * Retrieve a page from the catalog.
	 * 
	 * @param pageId
	 * @return the page identified by pageId.
	 * @throws DWikiException
	 *             if page does not exist in catalog.
	 */
	public Page getPage(String pageId) throws DWikiException;

	/**
	 * Create and register a page with identifier pageId and body bodyText.
	 * 
	 * @param pageId
	 * @param bodyText
	 * @return the new Page created.
	 * @throws DWikiException
	 *             if page already exists in the catalog.
	 */
	public Page createPage(String pageId, String bodyText)
			throws DWikiException;

	/**
	 * Create and register a page with identifier pageId and an empty body.
	 * 
	 * @param pageId
	 * @return the new Page created.
	 * @throws DWikiException
	 *             if page already exists in the catalog.
	 * @see ca.diro.dwiki.Catalog#createPage(String, String)
	 */
	public Page createPage(String pageId) throws DWikiException;

	/**
	 * Check if a page is already registered for this id.
	 * 
	 * @param pageId
	 * @return true if it exists, false otherwise.
	 */
	public boolean existPage(String pageId);

	/**
	 * Delete the identified page from the catalog and return it.
	 * 
	 * @param pageId
	 * @return the deleted page or null if no page is registered for this id.
	 */
	public Page deletePage(String pageId);

	/**
	 * Save modifications if page has changed.
	 * 
	 * @param id
	 */
	public void savePage(String id) throws DWikiException;

}
