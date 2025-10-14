package db;

public class Editorial {
	
	private int id;
	private String nombre;
	private String direccion;
	
	
	
	/**
	 * @param id
	 * @param nombre
	 * @param direccion
	 */
	public Editorial(int id) {
		super();
		this.id = id;
		this.nombre = "indie";
		this.direccion = "ninguna";
	}
	
	/**
	 * @param id
	 * @param nombre
	 * @param direccion
	 */
	public Editorial(int id, String nombre, String direccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
	
	@Override
	public String toString() {
		return String.format("Editorial [id=%s, nombre=%s, direccion=%s]", id, nombre, direccion);
	}
	
	
}
