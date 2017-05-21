package com.xsimo.dwiki.common;

import java.util.Date;
/**
 * Une interface qu'importeront les vues pour faciliter la transmission de parametres aux fonctions d'affichage.
 * @author Simon Arame
 *
 */
public interface DWikiIPage {
	public Date getEditDate();
	public String getId();
	public String getText();
}
