package com.xsimo.dwiki.common;
/**
 * Une simple extension de java.lang.Exception
 * @author DIRO
 */
public class DWikiException extends Exception {
	
	private static final long serialVersionUID = 1L;
/**
 * consiste en un appel au constructeur de la super classe java.lang.Exception
 * @param string
 */
	public DWikiException(String string) {
		super(string); 
	}
	public void printStackTrace(){
		System.out.println("DWIKI EXCEPTION CAUGHT");
		super.printStackTrace();
	}
}
