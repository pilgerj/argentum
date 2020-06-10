package br.com.caelum.argentum;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import br.com.caelum.argetum.reader.LeitorXML;

public class LeitorXMLTest {

	@Test
	public void testNormal() {

		String xmlDeTeste = "<list>" + 
							"  <negocio>" + 
							"    <preco>43.5</preco>" + 
							"    <quantidade>1000</quantidade>"+ 
							"    <data>" + 
							"      <time>1322233344455</time>" + 
							"    </data>" + 
							"  </negocio>" + 
							"</list>";

		LeitorXML leitor = new LeitorXML();

		List<Negocio> negocios = leitor.carrega(new StringReader(xmlDeTeste));
		assertEquals(43.5, negocios.get(0).getPreco(), 0d);
		assertEquals(1000, negocios.get(0).getQuantidade());
		assertEquals(1, negocios.size());
		assertFalse(negocios.isEmpty());
	}

	@Test(expected = NullPointerException.class)
	public void testSemXML() {

		String newXML = null;

		LeitorXML leitor = new LeitorXML();
		List<Negocio> n = leitor.carrega(new StringReader(newXML));
		assertEquals(0, n.size());

	}
	@Test//(expected = IndexOutOfBoundsException.class)
	public void testNegocioZeroPrecoEQtd() {

		String newXML = "<list>" + 
						"  <negocio>" + 
						"    <preco>0</preco>" + 
						"    <quantidade>0</quantidade>"+ 
						"    <data><time>1322233344455</time></data>" + 
						"  </negocio>" + 
						"</list>";

		LeitorXML leitor = new LeitorXML();
		List<Negocio> n = leitor.carrega(new StringReader(newXML));
		assertEquals(1, n.size());
		assertEquals(0.0, n.get(0).getPreco(), 0.1);

	}
	
	@Test
	public void testVariosXML() {
	
		String xml1 = "<list>" + 
						"  <negocio>" + 
						"    <preco>43.5</preco>" + 
						"    <quantidade>1000</quantidade>"+ 
						"    <data>" + 
						"      <time>1322233344455</time>" + 
						"    </data>" + 
						"  </negocio>" + 
						"</list>";
		
		String xml2 = "<list>" + 
						"  <negocio>" + 
						"    <preco>23.7</preco>" + 
						"    <quantidade>500</quantidade>"+ 
						"    <data>" + 
						"      <time>1322233344455</time>" + 
						"    </data>" + 
						"  </negocio>" + 
						"</list>";
		
		String xml3 = "<list>" + 
						"  <negocio>" + 
						"    <preco>32.1</preco>" + 
						"    <quantidade>200</quantidade>"+ 
						"    <data>" + 
						"      <time>1322233344455</time>" + 
						"    </data>" + 
						"  </negocio>" + 
						"</list>";

	
			LeitorXML leitor = new LeitorXML();
			List<Negocio> list = leitor.carrega(new StringReader(xml1));
			list.addAll(leitor.carrega(new StringReader(xml2)));
			list.addAll(leitor.carrega(new StringReader(xml3)));
			
			assertEquals(3, list.size());
			assertEquals(43.5, list.get(0).getPreco(), 0d);
			assertEquals(1000, list.get(0).getQuantidade());
			
			assertEquals(23.7, list.get(1).getPreco(), 0d);
			assertEquals(500, list.get(1).getQuantidade());
			
			assertEquals(32.1, list.get(2).getPreco(), 0);
			assertEquals(200, list.get(2).getQuantidade());
 
			
	}
	
}
