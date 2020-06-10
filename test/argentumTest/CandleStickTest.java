package br.com.caelum.argentum;

import java.util.Calendar;

import org.junit.Test;

public class CandleStickTest {
	
	@Test(expected=IllegalArgumentException.class)
		public void maxMenorQueMin() {
			
		
		Calendar c = Calendar.getInstance();
		@SuppressWarnings("unused")
		CandleStick c1 = new CandleStick(12.5, 15.5, 20, 15.5, 100, c);
		
		
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void candleSemData() {
		@SuppressWarnings("unused")
		CandleStick c1 = new CandleStick(12.5, 15.5, 20, 25, 100, null);
	}

}
