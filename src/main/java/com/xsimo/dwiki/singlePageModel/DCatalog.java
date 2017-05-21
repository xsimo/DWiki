package com.xsimo.dwiki.singlePageModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import com.xsimo.dwiki.common.DWikiException;
/**
 * Dcatalog (Desktop Catalog) is composed of an HashMap that binds pages to their id.<br>
 * This implementation of {@link com.xsimo.dwiki.singlePageModel.Catalog} goes further by storing  and loading the pages directly on the hard drive.<br>
 * When a page is already in the cache, the user has a slightly fastest access.
 * The 
 * @author DIRO
 */
public class DCatalog implements Catalog {

	private HashMap<String, Page> cache;

	public DCatalog() {
		this.cache = new HashMap<String, Page>();
	}

	public boolean existPage(String pageId) {
		return existInCache(pageId) || new File(filename(pageId)).exists();
	}

	public boolean existInCache(String pageId) {
		return this.cache.containsKey(pageId);
	}

	public Page getPage(String pageId) throws DWikiException {
		if (existPage(pageId)) {
			return retrievePage(pageId);
		} else
			return createPage(pageId);
	}

	public Page retrievePage(String pageId) {
		if (!existInCache(pageId))
			loadPage(pageId);
		return this.cache.get(pageId);
	}

	public Page createPage(String pageId) throws DWikiException {
		return createPage(pageId, "");
	}

	public Page createPage(String pageId, String bodyText)
			throws DWikiException {
		if (!existPage(pageId)) {
			Page newPage = new Page(pageId, bodyText);
			registerPage(pageId, newPage);
			return newPage;
		} else
			throw new DWikiException("Trying to create an existing page");
	}

	protected void registerPage(String pageId, Page page) {
		this.cache.put(pageId, page);
	}

	public Page deletePage(String pageId) {
		new File(filename(pageId)).delete();
		return this.cache.remove(pageId); 
	}

	public void savePage(String pageId) throws DWikiException {
		Page p = getPage(pageId);
		if (p.hasChanged()) {
			p.commit();
			storePage(pageId);
		}
	}

	/*
	 * Persistence concern
	 */

	public String filename(String id) {
		return "pages/" + id + ".dxt";
	}

	protected void loadPage(String id) {
		try {
			FileInputStream fis = new FileInputStream(filename(id));
			ObjectInputStream ois = new ObjectInputStream(fis);
			Page p = (Page) ois.readObject();
			registerPage(id, p);
		} catch (FileNotFoundException e) {
			System.err.println("Cant find persistent page");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error retrieving page in file");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void storePage(String id) {
		try {
			Page p = getPage(id);
			FileOutputStream fos = new FileOutputStream(filename(id));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(p);
			oos.close();
		} catch (DWikiException e) {
			System.err.println("Unknown page " + id);
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.err.println("Cant open file to save page " + id);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error saving page " + id);
			e.printStackTrace();
		}
	}

}
