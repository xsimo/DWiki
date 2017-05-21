package com.xsimo.dwiki.singleUserControls;

import com.xsimo.dwiki.singlePageModel.*;
/**
 * The Formatter transforms some standard text in Hyper Text Markup Language
 * @author DIRO
 */
public class Formatter {
	/**
	 * this format() method recognize CamelCase and adds hyperlinks to it.<br> 
	 * it also adds html basic tags
	 */
	public String format(Page page, int index) { 
		// NOTE: see java.util.regex.Parser/Matcher for more powerful regex if necessary
		String source = page.getText(index);
		String result;
		result = source.replaceAll("\n", "<br>");
		result = result.replaceAll("([A-Z]\\w+([A-Z0-9]\\w*)+)", "<a href=\"$1\">$1</a>");
		result = result.concat("<br><br><a href=\"Index\">Index</a>");
		return "<html>" + result + "</html>";
	}

}
