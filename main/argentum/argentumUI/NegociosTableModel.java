package ui;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.table.AbstractTableModel;

import br.com.caelum.argentum.Negocio;

@SuppressWarnings("serial")
public class NegociosTableModel extends AbstractTableModel {

	private final List<Negocio> negocios;

	public NegociosTableModel(List<Negocio> negocios) {
		this.negocios = negocios;
	}

	@Override
	public int getRowCount() {
		return negocios.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}
	
	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Preço";
		case 1:
			return "Quantidade";
		case 2:
			return "Data";
		}
		return "";				
	}
	
	 

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Negocio n = negocios.get(rowIndex);
		getColumnName(columnIndex);
		switch (columnIndex) {
		case 0:
			Locale brasil = new Locale("pt", "BR");
			NumberFormat formatMoeda = NumberFormat.getCurrencyInstance(brasil);
			return formatMoeda.format(n.getPreco());
		case 1:
			return n.getQuantidade();
		case 2:
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(n.getData().getTime());
		}
		return "";
	}

}
