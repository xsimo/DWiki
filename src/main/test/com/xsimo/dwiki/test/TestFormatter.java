package com.xsimo.dwiki.test;
import junit.framework.TestCase;
import com.xsimo.dwiki.singlePageModel.Page;
import com.xsimo.dwiki.singleUserControls.Formatter;
/**
 * Test method for {@link ca.diro.dwiki.Formatter#format(Page)}.
 * @author Rabah Maache
 */
public class TestFormatter extends TestCase {

    public void testFormat()
    {
        Formatter formatter = new Formatter();
        Page page = new Page("","page de test\nTestLink Le test marche\nTestLink2: QualiteCorrect ou QualiteFiable, QualiteRobuste.\n");
        assertEquals("<html>page de test<br><a href=\"TestLink\">TestLink</a> Le test marche<br><a href=\"TestLink2\">TestLink2</a>: <a href=\"QualiteCorrect\">QualiteCorrect</a> ou <a href=\"QualiteFiable\">QualiteFiable</a>, <a href=\"QualiteRobuste\">QualiteRobuste</a>.<br><br><br><a href=\"Index\">Index</a></html>",formatter.format(page,0));
    }
}

