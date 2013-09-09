import cl.builderSoft.aga.certificate.pdf.CertificadoGasPdf;
import junit.framework.TestCase;

public class NumberTest extends TestCase {
	CertificadoGasPdf o = new CertificadoGasPdf();

	public void testNumber1() {
		assertEquals(o.calculateCols(11, 5), 3);
	}

	public void testNumber2() {
		assertEquals(o.calculateCols(12, 6), 2);
	}

	public void testNumber3() {
		assertEquals(o.calculateCols(3, 4), 1);
	}

	public void testNumber4() {
		assertEquals(o.calculateCols(9, 5), 2);
	}

	public void testNumber5() {
		assertEquals(o.calculateCols(1, 6), 1);
	}

	public void testNumber6() {
		assertEquals(o.calculateCols(15, 5), 3);
	}

	public void testNumber7() {
		assertEquals(o.calculateCols(8, 3), 3);
	}

	public void testNumber8() {
		assertEquals(o.calculateCols(19, 5), 4);
	}

}
