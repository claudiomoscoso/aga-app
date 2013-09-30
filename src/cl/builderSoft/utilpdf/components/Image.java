package cl.builderSoft.utilpdf.components;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cl.builderSoft.utilpdf.PdfComponent;
import cl.builderSoft.utilpdf.Resources;

import com.lowagie.text.Chunk;
import com.lowagie.text.Element;

public class Image extends PdfComponent {

	String name;
	String path = Resources.DEFAULT_RESOURCE_PATH;
	int x,y;
	int w,h;
	
	public Image(String name) {
		this.name = name;
	}
	
	public Element render() {
		try {
			com.lowagie.text.Image image = com.lowagie.text.Image.getInstance(getResource());
//  		com.lowagie.text.Image image = com.lowagie.text.Image.getInstance("c://aga_iso.GIF");

			if (h*w != 0) image.scaleToFit(h,w);
			return new com.lowagie.text.Paragraph(new Chunk(image, 0, 0));		
		} catch (Exception e) {
			return new Paragraph("Image Not found:" + name + "/" + path).render();
		}
	}

	private byte[] getResource() throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(path + "/" + name);
//		InputStream is = this.getClass().getClassLoader().getResourceAsStream(name);
		byte buffer[] = new byte[4096];
		int len = 0;
		while ((len = is.read(buffer))>0) {
			os.write(buffer,0,len);
		}
		is.close();
		os.close();
		return os.toByteArray();
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setSize(int w, int h) {
		this.w = w;
		this.h = h;
	}

	
}
