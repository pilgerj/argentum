package br.com.caelum.argentum;

import java.util.Calendar;

public final class CandleStick {

	private final double abertura;
	private final double fechamento;
	private final double minimo;
	private final double maximo;
	private final double volume;
	private final Calendar data;
	
	

	public CandleStick(double abertura, double fechamento, double minimo, 
		double maximo, double volume, Calendar data) {
		if (maximo<minimo) {
			throw new IllegalArgumentException("ERRO: Valor Máximo está menor que o Mínimo.");
		}
		if (data==null) {
			throw new IllegalArgumentException("ERRO: Insira uma data válida.");
		}
		this.abertura = abertura;
		this.fechamento = fechamento;
		this.minimo = minimo;
		this.maximo = maximo;
		this.volume = volume;
		this.data = data;
	}
	
	public boolean isAlta() {
		return this.abertura < this.fechamento;
	}
	
	public boolean isBaixa() {
		return this.abertura > this.fechamento;
	}

	public double getAbertura() {
		return abertura;
	}

	public double getFechamento() {
		return fechamento;
	}

	public double getMinimo() {
		return minimo;
	}

	public double getMaximo() {
		return maximo;
	}

	public double getVolume() {
		return volume;
	}

	public Calendar getData() {
		return data;
	}
}
