package com.checkdoc.check;

/**
 * Class describes rules to check the document on mistakes
 *
 * @author Oleg Dashkevych
 * @version 1.0 4.11.2015
 */
public class Rule {

	/**
	 * Name of font
	 */
	private String font;

	/**
	 * Size of letters
	 */
	private Integer fontSize;

	/**
	 * Size of intend from the left side of page
	 */
	private Long intendSize;

	/**
	 * Default values of parameters
	 */
	public Rule() {
		font = "Times New Roman";
		fontSize = 14;
		intendSize = 2L;
	}

	/**
	 * @param font       Font name
	 * @param fontSize   Size of font
	 * @param intendSize Size of intend
	 */
	public Rule(String font, Integer fontSize, Long intendSize) {
		this.font = font;
		this.fontSize = fontSize;
		this.intendSize = intendSize;
	}

	public String getFont() {
		return font;
	}

	public Integer getFontSize() {
		return fontSize;
	}

	public Long getIntendSize() {
		return intendSize;
	}
}
