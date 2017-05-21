package com.xsimo.dwiki.test;

import java.util.Date; 
import com.xsimo.dwiki.singlePageModel.Page;
import junit.framework.TestCase;

/**
 * test method for {@link ca.diro.dwiki.Page}<br>
 * Minor modifications consists of the date parsing removal
 * 
 * @author DIRO
 */
public class TestPage extends TestCase {

	/**
	 * Test method for
	 * {@link ca.diro.dwiki.Page#Page(java.lang.String, java.lang.String)}.
	 */
	public void testPageStringString() {
		Page page = new Page("", "");
		assertTrue(page.isNew());
	}

	/**
	 * Test method for
	 * {@link ca.diro.dwiki.Page#Page(java.lang.String, java.util.Date, java.lang.String)}.
	 */
	public void testPageStringDateString() {
		//DateFormat format = DateFormat.getDateInstance();
		Page page;
		try {
			Date date = new Date();
			page = new Page("test", date, "Test Page");
			assertTrue(page.isNew());
			assertEquals("test", page.getId());
			assertEquals(date.toString(), page.getEditDate().toString());
			assertEquals("Test Page", page.getText());
		} catch (Exception e) {
			e.printStackTrace();
			//fail("Date parsing " + e.toString());
		}
	}

	/**
	 * Test method for {@link ca.diro.dwiki.Page#setText(String)}.
	 */
	public void testSetText() {
		Page page = new Page("", "");
		page.setText("lorem ipsum");
		assertTrue(page.hasChanged());
		assertEquals("lorem ipsum", page.getText());
		page.commit();
		assertFalse(page.hasChanged() || page.isNew());
	}

	/**
	 * Test method for {@link ca.diro.dwiki.Page#commit()}.
	 */
	public void testCommit() {
		Page page = new Page("", "");
		assertTrue(page.isNew());
		page.commit();
		assertFalse(page.hasChanged() || page.isNew());
	}

}
