package Graphic;

public class Viajeback {
	private String desde;
	private String hasta;
	private String origen;
	private String llegada;
	
	public Viajeback(String desde, String hasta, String origen, String llegada) {
		this.desde = desde;
		this.hasta = hasta;
		this.origen = origen;
		this.llegada = llegada;
	}

	public String getDesde() {
		return desde;
	}

	public void setDesde(String desde) {
		this.desde = desde;
	}

	public String getHasta() {
		return hasta;
	}

	public void setHasta(String hasta) {
		this.hasta = hasta;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getLlegada() {
		return llegada;
	}

	public void setLlegada(String llegada) {
		this.llegada = llegada;
	}
	
	
}
