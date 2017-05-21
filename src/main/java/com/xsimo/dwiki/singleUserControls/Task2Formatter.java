package com.xsimo.dwiki.singleUserControls;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xsimo.dwiki.singlePageModel.Page;
/**
 * 
 * This class extends {@link ca.diro.dwiki.Formatter}
 * @author Simon Arame
 * */
public class Task2Formatter extends Formatter{
	public String format(Page page,int index){
		String text = super.format(page,index);
		return miniTask(text);
	}
	/**
	 * Cette m&eacute;thode impl&eacutemente les t&acirc;ches 2a) et 2b)<br><br>
	 * 2a)<i>l'italique</i>, <b>le grassage</b> et le <u>soulignement</u>.<br>
	 * 2b)les liens vers une Page qui n'existe pas comme suit : PageInexistante<a href="/.">?</a><br><br> 
	 * le code est inspir&eacute; de la source suivante : <a href="http://www.java2s.com/Code/Java/Regular-Expressions/MatcherappendReplacement.htm">java2s.com</a><br>
	 */
	public String miniTask(String text){
		//ITALIQUE
		Pattern p = Pattern.compile("\\*\\*(.+?)\\*\\*");
		StringBuffer sb3 = new StringBuffer();
		String replacement = "<i>$1</i>";
		Matcher matcher = p.matcher(text);
		while(matcher.find()){
			matcher.appendReplacement(sb3, replacement);
		}
		matcher.appendTail(sb3);
		text = sb3.toString();
		matcher.reset();
		
		//GRASSAGE
		p = Pattern.compile("\\*(.+?)\\*");
		StringBuffer sb = new StringBuffer();
		replacement = "<b>$1</b>";
		matcher = p.matcher(text);
		while(matcher.find()){
			matcher.appendReplacement(sb, replacement);
		}
		matcher.appendTail(sb);
		text = sb.toString();
		matcher.reset();
		
		
		//SOULIGNEMENT
		p = Pattern.compile("_(.+?)_");
		StringBuffer sb2 = new StringBuffer();
		replacement = "<u>$1</u>";
		matcher = p.matcher(text);
		while(matcher.find()){
			matcher.appendReplacement(sb2, replacement);
		}
		matcher.appendTail(sb2);
		text = sb2.toString();
		matcher.reset();
		
		
		//FORMAT DES LIENS POUR PAGE MANQUANTE
		p = Pattern.compile("<a href=\"([A-Z]\\w+([A-Z0-9]\\w*)+)\">([A-Z]\\w+([A-Z0-9]\\w*)+)</a>");
		replacement = "$1<a href=\"$1\">?</a>";
		StringBuffer sb4 = new StringBuffer();
		matcher = p.matcher(text);
		String s = "";
		while(matcher.find()){
			int g = 0;
			g++;
			s = matcher.group();
			int z = 12; //le cas du CamelCase de trois char.
			while(s.charAt(z)!='"'){
				z++;
			}
			String lien = s.substring(9,z);
			boolean pe = new File("pages/"+lien+".dxt").exists();
			if(!pe){
				matcher.appendReplacement(sb4,replacement);
			}
		}
		matcher.appendTail(sb4);
		text = sb4.toString();
		
		return text;
  }
}

