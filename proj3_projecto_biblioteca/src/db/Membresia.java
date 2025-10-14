package db;

public class Membresia {
	
	private int id;
	private String tipo_membresia;
	private float descuento;
	
	
	/**
	 * en un futuro el id se asignara de forma aleatoria y se comprobara que no se rpita
	 */
	public Membresia() {
		super();
		this.id = 1;
		this.tipo_membresia = "ninguna";
		this.descuento = 0;
	}
	
	/**
	 * @param id
	 * @param tipo_membresia
	 * @param descuento
	 */
	public Membresia(int id, String tipo_membresia, float descuento) {
		super();
		this.id = id;
		this.tipo_membresia = tipo_membresia;
		this.descuento = descuento;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the tipo_membresia
	 */
	public String getTipo_membresia() {
		return tipo_membresia;
	}
	/**
	 * @param tipo_membresia the tipo_membresia to set
	 */
	public void setTipo_membresia(String tipo_membresia) {
		this.tipo_membresia = tipo_membresia;
	}
	/**
	 * @return the descuento
	 */
	public float getDescuento() {
		return descuento;
	}
	/**
	 * @param descuento the descuento to set
	 */
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	
	
	@Override
	public String toString() {
		return String.format("Membresia [id=%s, tipo_membresia=%s, descuento=%s]", id, tipo_membresia, descuento);
	}
	
	
}
