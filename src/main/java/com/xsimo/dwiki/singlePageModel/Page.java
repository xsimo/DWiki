package com.xsimo.dwiki.singlePageModel;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;

import com.xsimo.dwiki.common.DWikiIPage;
/**
 * A Page is the basic data object manipulated by controls in DWiki. It stores <b>all</b>
 * versions of a page identified by its Id in WikiWord format. <br>
 * Original code from DIRO was storing only the latest version.<br>
 * The actual code stores them all. Modifications also includes the revert() method to permanently make a previous version to the revert date 
 * and the stackHistoricalVersion() and the unStackHistoricalVersion() that adds a previous version but in non-permanent way.  
 * @author DIRO
 * @author Simon Arame
 * 
 */
public class Page implements Serializable,DWikiIPage {

	private static final long serialVersionUID = 1;

	private enum State {
		NEW, CHANGED, COMMIT
	}

	/**
	 * Current state of page in wiki flow.
	 */
	private State state;

	/**
	 * An id in WikiWord format.
	 */
	private String id;

	/*
	 * This Array stores a time stamp each a page is saved.
	 */
	private ArrayList<Date> versions;
	
	/*
	 * This integer indicates the number of revision for a file
	 */
	public int versionsCount;
	
	/**
	 * Last edit date.
	 */
	private Date editDate;

	/**
	 * The page versions sources in wiki syntax.
	 */
	private ArrayList<String> text;

	/**
	 * Create page at current date.
	 * 
	 * @param id
	 * @param text
	 */
	public Page(String id, String text) {
		this(id, new Date(System.currentTimeMillis()), text);
	}

	/**
	 * Create a page with arbitrary parameters.
	 * 
	 * @param id
	 * @param editDate
	 * @param text
	 */
	public Page(String id, Date editDate, String text) {
		this.state = State.NEW;
		this.id = id;
		this.editDate = editDate;
		this.text = new ArrayList<String>();
		this.text.add(0,text);
		this.versions = new ArrayList<Date>();
		this.versions.add(0,editDate);
		this.versionsCount = 0;
	}

	public Date getEditDate() {
		return this.editDate;
	}

	public void setEditDate(Date editDate) {
		this.versions.add(this.versionsCount,editDate);
		this.editDate = editDate;
	}

	public String getText() {
		return this.text.get(versionsCount);
	}
	
	public String getText(int index){
		return this.text.get(index);
	}
	
/**
 * this method adds a previous versions. it is used in a non-permanent way
 * @param version
 */
	public void stackHistoricalVersion(int version){
		versionsCount++;
		this.text.add(versionsCount,this.text.get(version));
		this.versions.add(versionsCount,new Date());
	}
	/**
	 * This method removes a previous versions that was put on top temporarily.
	 * 
	 */

	public void unStackHistoricalVersion() {
		this.text.remove(versionsCount);
		this.versions.remove(versionsCount);
		versionsCount--;
	}
	
	public void setText(String newText) {
		if (!this.text.get(versionsCount).equals(newText)) {
			this.versionsCount++;
			this.text.add(this.versionsCount,newText);
			changed();
		}
	}

	public String getId() {
		return this.id;
	}

	/**
	 * No write accessor for Id. We dont want to change the id as it can break
	 * link from other pages. Renaming a page could be an extension
	 */

	public boolean isNew() {
		return this.state == State.NEW;
	}

	public boolean hasChanged() {
		return this.state == State.CHANGED;
	}

	public void commit() {
		this.state = State.COMMIT;
	}

	public void changed() {
		this.state = State.CHANGED;
	}
/**
 * puts a copy of the previous version number "a" at the end of ArrayList with the current date. 
 * @param a the number of the version it is reverting to.
 */
	public void revert(int a){
		versionsCount++;
		this.text.add(versionsCount,this.text.get(a));
		this.versions.add(versionsCount,new Date(System.currentTimeMillis()));
		this.editDate = this.versions.get(versionsCount);
		changed();
	}

	/**
	 * @return the versions
	 */
	public ArrayList<Date> getVersions() {
		return versions;
	}
}