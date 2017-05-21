package com.xsimo.dwiki.common;

import java.io.IOException;

public class Utilities {
	/**
	 * Cette m&eacute;thode retourne le contenu d'un fichier
	 * Dans DWiki, les instructions d'utilisation du modele 
	 * une simple chaine de caractere stocké dans "./pages/instructions.dxt"
	 * @return la chaine contenue dans le fichier
	 */
	public static String lireFichier(String nomFichier) {
		String s = "default instructions";
		try {
			String t = "";
			s = "";
			java.io.RandomAccessFile rd = new java.io.RandomAccessFile(
					nomFichier, "r");
			while (t != null) {
				t = rd.readLine();
				if (t != null) {
					s += t;
				}
			}
		} catch (IOException e) {
			s = "le fichier \'instructions.dxt\' doit exister et être placer dans le dossier \'pages\'\n\n";
			return s;
		}
		return s;
	}

}
