package br.com.caelum.argentum;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

public class CandlestickFactoryTest {

	@Test
	public void testCincoNegocios() {

		Calendar hoje = Calendar.getInstance();
		Negocio n1 = new Negocio(40.5, 100, hoje);
		Negocio n2 = new Negocio(45.0, 100, hoje);
		Negocio n3 = new Negocio(39.8, 100, hoje);
		Negocio n4 = new Negocio(42.3, 100, hoje);
		Negocio n5 = new Negocio(62.3, 100, hoje);

		List<Negocio> negocios = Arrays.asList(n1, n2, n3, n4, n5);
		CandlestickFactory factory = new CandlestickFactory();
		CandleStick candle = factory.constroiCandleParaData(hoje, negocios);
		assertEquals(40.5, candle.getAbertura(), 0.00001);
		assertEquals(62.3, candle.getFechamento(), 0.00001);
		assertEquals(39.8, candle.getMinimo(), 0.00001);
		assertEquals(62.3, candle.getMaximo(), 0.00001);
		assertEquals(22990.0, candle.getVolume(), 0.00001);

	}

	@Test(expected=IllegalArgumentException.class)
	public void testSemNegocios() {
		Calendar hoje = Calendar.getInstance();

		List<Negocio> negocios = Arrays.asList();
		CandlestickFactory fabrica = new CandlestickFactory();
		CandleStick candle = fabrica.constroiCandleParaData(hoje, negocios);
		
		assertEquals(0.0, candle.getVolume(), 0.00001);
		assertEquals(0.0, candle.getAbertura(), 0.00001);
		assertEquals(0.0, candle.getFechamento(), 0.00001);
		assertEquals(0.0, candle.getMinimo(), 0.00001);
		assertEquals(0.0, candle.getMaximo(), 0.00001);
	}

	@Test
	public void apenasUmNegocio() {
		Calendar hoje = Calendar.getInstance();
		Negocio n1 = new Negocio(40.5, 100, hoje);
		List<Negocio> negocios = Arrays.asList(n1);

		CandlestickFactory fabrica = new CandlestickFactory();
		CandleStick candle = fabrica.constroiCandleParaData(hoje, negocios);

		assertEquals(40.5, candle.getAbertura(), 0.00001);

		assertEquals(40.5, candle.getFechamento(), 0.00001);

//		System.out.println(candle.getMinimo());
//		System.out.println(Double.MAX_VALUE);
	
		assertEquals(40.5, candle.getMinimo(), 0.00001);

		assertEquals(40.5, candle.getMaximo(), 0.00001);

		assertEquals(4050.0, candle.getVolume(), 0.00001);
	}
	
//	@Test
//	public void testaSeLa() {
//		Calendar hoje = Calendar.getInstance();
//		Negocio n1 = new Negocio(40.5, 100, hoje);
//		Negocio n2 = new Negocio(45.0, 100, hoje);
//		Negocio n3 = new Negocio(39.8, 100, hoje);
//		Negocio n4 = new Negocio(42.3, 100, hoje);
//		Negocio n5 = new Negocio(62.3, 100, hoje);
//		
//		
//	}

}
