package br.com.caelum.argentum;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CandlestickFactory {

	public CandleStick constroiCandleParaData(Calendar data, List<Negocio> negocios) {
		if (negocios.isEmpty()) {
			throw new IllegalArgumentException("Nao há negocios para o CandleStick definir.");
		}

		double maximo = Double.MIN_VALUE;
		double minimo = Double.MAX_VALUE;
		double volume = 0;

		for (Negocio n : negocios) {
			volume += n.getVolume();

			if (n.getPreco() > maximo) {
				maximo = n.getPreco();
			}
			if (n.getPreco() < minimo) {
				minimo = n.getPreco();
			}
		}

		// double abertura = negocios.isEmpty() ? 0 : negocios.get(0).getPreco();
		// double fechamento = negocios.isEmpty() ? 0 : negocios.get(negocios.size() -
		// 1).getPreco();
		// negocios.isEmpty() ? 0 : ? == se for 0 é false, se não abertura =
		// neg.get.preco.

		double abertura = negocios.get(0).getPreco();
		double fechamento = negocios.get(negocios.size() - 1).getPreco();

		return new CandleStick(abertura, fechamento, minimo, maximo, volume, data);

	}

	public List<CandleStick> constroiCandles(List<Negocio> todosNegocios) {

		List<CandleStick> candles = new ArrayList<CandleStick>();
		List<Negocio> negociosDoDia = new ArrayList<Negocio>();
		Calendar dataAtual = todosNegocios.get(0).getData();

		for (Negocio negocio : todosNegocios) {
			if (negocio.getData().before(dataAtual)) {
				throw new IllegalStateException("Negocios em ordem errada. ");
			}
			// se não for mesmo dia, fecha candle e reinicia variáveis
			if (!negocio.isMesmoDia(dataAtual)) {
				CandleStick candleDoDia = constroiCandleParaData(dataAtual, negociosDoDia);
				candles.add(candleDoDia);
				negociosDoDia = new ArrayList<Negocio>();
				dataAtual = negocio.getData();
			}
			negociosDoDia.add(negocio);
		}
		// adiciona último candle
		CandleStick candleDoDia = constroiCandleParaData(dataAtual, negociosDoDia);
		candles.add(candleDoDia);
		return candles;

	}

}
