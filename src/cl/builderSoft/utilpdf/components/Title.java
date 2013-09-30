package cl.builderSoft.utilpdf.components;


import cl.builderSoft.utilpdf.Resources;

import com.lowagie.text.Element;

public class Title extends Paragraph {
	
	public Title(String title) {
		this(title, Resources.TITLE_LEVEL1);
	}
	
	public Title(String title, int level) {
		super(title);
		setAlignment(Element.ALIGN_CENTER);
		font = Resources.getFontTitle(level);
	}

}
