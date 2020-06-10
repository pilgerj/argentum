package br.com.caelum.argentum;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Main {

	public static void main(String[] args) throws IOException {

		Calendar hoje = Calendar.getInstance();

		// ------------------------------------------//
		// -----------------CASO-01------------------//
		// --------------varios negocios-------------//

		// Negocio n1 = new Negocio(40.5, 100, hoje);
		// Negocio n2 = new Negocio(45.0, 100, hoje);
		// Negocio n3 = new Negocio(39.8, 100, hoje);
		// Negocio n4 = new Negocio(42.3, 100, hoje);
		// Negocio n5 = new Negocio(62.3, 100, hoje);
		//
		// List<Negocio> negocios = Arrays.asList(n1, n2, n3, n4, n5);
		//
		// CandlestickFactory fabrica = new CandlestickFactory();
		//
		// CandleStick candle = fabrica.constroiCandleParaData(hoje, negocios);
		//
		// System.out.println(candle.getAbertura());
		//
		// System.out.println(candle.getFechamento());
		//
		// System.out.println(candle.getMinimo());
		//
		// System.out.println(candle.getMaximo());
		//
		// System.out.println(candle.getVolume());

		// ------------------------------------------//
		// -----------------CASO-02------------------//
		// -----------Apenas um negocio--------------//

		// Negocio n1 = new Negocio(40.5, 100, hoje);
		// List<Negocio> negocios = Arrays.asList(n1);
		// CandlestickFactory fabrica = new CandlestickFactory();
		//
		// CandleStick candle = fabrica.constroiCandleParaData(hoje, negocios);
		//
		// System.out.println(candle.getAbertura());
		//
		// System.out.println(candle.getFechamento());
		//
		// System.out.println(candle.getMinimo()); // ERRO AQUI
		//
		// System.out.println(candle.getMaximo());
		//
		// System.out.println(candle.getVolume());

		// ------------------------------------------//
		// -----------------CASO-03------------------//
		// --------Negocios em ordem crescente-------//

		Negocio n1 = new Negocio(40.5, 100, hoje);
		Negocio n2 = new Negocio(45.0, 100, hoje);
		Negocio n3 = new Negocio(49.8, 100, hoje);
		Negocio n4 = new Negocio(32.3, 100, hoje);
		Negocio n5 = new Negocio(62.5, 100, hoje);
		Negocio n6 = new Negocio(54.8, 100, hoje);
		Negocio n7 = new Negocio(37.5, 100, hoje);
		Negocio n8 = new Negocio(24.2, 100, hoje);
		Negocio n9 = new Negocio(74.9, 100, hoje);
		Negocio n10 = new Negocio(54.8, 100, hoje);

		List<Negocio> negocios = Arrays.asList(n1,n2,n3,n4,n5,n6,n7,n8,n9,n10);

		CandlestickFactory fabrica = new CandlestickFactory();

		CandleStick candle = fabrica.constroiCandleParaData(hoje, negocios);

		System.out.println(candle.getAbertura());

		System.out.println(candle.getFechamento());

		System.out.println(candle.getMinimo());

		System.out.println(candle.getMaximo());

		System.out.println(candle.getVolume() + "\n");

		

		XStream stream = new XStream(new DomDriver());
		stream.alias("negocio", Negocio.class);
		//System.out.println(stream.toXML(negocios));

//		PrintStream out = new PrintStream(new File("novosNegocios.xml"));
//		out.println(stream.toXML(negocios));
		

	}

}
