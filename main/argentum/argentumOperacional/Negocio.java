package br.com.caelum.argentum;

import java.util.Calendar;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("negocio")
public final class Negocio{ 

	private final double preco;
	private final int quantidade;
	private final Calendar data;

	public Negocio(double preco, int quantidade, Calendar data) {
		if (preco == 0.0 || quantidade == 0 || data == null) {
			throw new IllegalArgumentException("Insira um valor valido.");
		} else {
			this.preco = preco;
			this.quantidade = quantidade;
			this.data = data;
		}
	}

	public double getVolume() {
		return preco * quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Calendar getData() {
		return (Calendar) this.data.clone();
	}

	public boolean isMesmoDia(Calendar dataComparada) {
		return 
				data.get(Calendar.DATE) == dataComparada.get(Calendar.DATE) //Calendar.DATE pega apenas o dia
				&& data.get(Calendar.MONTH) == dataComparada.get(Calendar.MONTH)
				&& data.get(Calendar.YEAR) == dataComparada.get(Calendar.YEAR);
		
	}



}
