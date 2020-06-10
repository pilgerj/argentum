package br.com.caelum.argentum;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

public class NegocioTest {

	@Test
	public void testMudaData() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 15);
		Negocio n = new Negocio(10, 5, c);

		n.getData().set(Calendar.DAY_OF_MONTH, 20); // tentando alterar a data de um final.

		assertEquals(15, n.getData().get(Calendar.DAY_OF_MONTH));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegocioDataNull() {
		new Negocio(10, 5, null);

	}

	@Test
	public void mesmoMilissegundoEhDoMesmoDia() {
		Calendar agora = Calendar.getInstance();
		Calendar mesmoMomento = (Calendar) agora.clone();

		Negocio n1 = new Negocio(40.0, 100, agora);
		assertTrue(n1.isMesmoDia(mesmoMomento));

	}

	@Test
	public void mesmoDiaHorasDiferentes() {
		// usando GregorianCalendar(ano, mes, dia, hora, minuto)
		Calendar manha = new GregorianCalendar(2017, 10, 20, 8, 30);
		Calendar tarde = new GregorianCalendar(2017, 10, 20, 18, 30);

		Negocio n1 = new Negocio(10, 50, manha);

		assertTrue(n1.isMesmoDia(tarde));

	}

	@Test
	public void mesmoDiaMesDiferente() {
		Calendar manha = new GregorianCalendar(2017, 10, 20, 8, 30);
		Calendar tarde = new GregorianCalendar(2017, 11, 20, 18, 30);

		Negocio n1 = new Negocio(10, 50, manha);

		assertFalse(n1.isMesmoDia(tarde));

	}

	@Test
	public void paraNegociosDeTresDiasDistintosGeraTresCandles() {
		Calendar hoje = Calendar.getInstance();
		Negocio n1 = new Negocio(40.5, 100, hoje);
		Negocio n2 = new Negocio(45.0, 100, hoje);
		Negocio n3 = new Negocio(39.8, 100, hoje);
		Negocio n4 = new Negocio(42.3, 100, hoje);

		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 2);
		Negocio n5 = new Negocio(48.8, 100, amanha);
		Negocio n6 = new Negocio(49.3, 100, amanha);

		Calendar depois = (Calendar) amanha.clone();
		depois.add(Calendar.DAY_OF_MONTH, 3);
		Negocio n7 = new Negocio(51.8, 100, depois);
		Negocio n8 = new Negocio(58.3, 100, depois);

		List<Negocio> negocios = Arrays.asList(n1, n2, n3, n4, n5, n6, n7, n8);
		CandlestickFactory fabrica = new CandlestickFactory();
		List<CandleStick> candles = fabrica.constroiCandles(negocios);

		assertEquals(3, candles.size());
		assertEquals(40.5, candles.get(0).getAbertura(), 0.00001);
		assertEquals(42.3, candles.get(0).getFechamento(), 0.00001);
		assertEquals(48.8, candles.get(1).getAbertura(), 0.00001);
		assertEquals(49.3, candles.get(1).getFechamento(), 0.00001);
		assertEquals(51.8, candles.get(2).getAbertura(), 0.00001);
		assertEquals(58.3, candles.get(2).getFechamento(), 0.00001);

	}
	@Test(expected=IllegalArgumentException.class)
	public void naoPermiteConstruirCandlesComNegociosForaDeOrdem() {

		Calendar hoje = Calendar.getInstance();
		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 2);
		Calendar depois = (Calendar) amanha.clone();
		depois.add(Calendar.DAY_OF_MONTH, 3);
		
		Negocio n1 = new Negocio(40.5, 100, hoje);
		Negocio n2 = new Negocio(45.0, 100, amanha);
		Negocio n3 = new Negocio(39.8, 100, hoje);
		Negocio n4 = new Negocio(42.3, 100, hoje);

		
		
		Negocio n5 = new Negocio(48.8, 100, amanha);
		Negocio n6 = new Negocio(49.3, 100, depois);

		
		Negocio n7 = new Negocio(51.8, 100, depois);
		Negocio n8 = new Negocio(58.3, 100, depois);

		List<Negocio> negocios = Arrays.asList(n1, n2, n3, n4, n5, n6, n7, n8);
		CandlestickFactory fabrica = new CandlestickFactory();
		List<CandleStick> candles = fabrica.constroiCandles(negocios);

		assertEquals(3, candles.size());
		assertEquals(40.5, candles.get(0).getAbertura(), 0.00001);
		assertEquals(42.3, candles.get(0).getFechamento(), 0.00001);
		assertEquals(48.8, candles.get(1).getAbertura(), 0.00001);
		assertEquals(49.3, candles.get(1).getFechamento(), 0.00001);
		assertEquals(51.8, candles.get(2).getAbertura(), 0.00001);
		assertEquals(58.3, candles.get(2).getFechamento(), 0.00001);

	}

}
