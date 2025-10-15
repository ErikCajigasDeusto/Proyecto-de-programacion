package domain;

public class Miembro {
	
	private int id;
	private String nombre;
	private String apellido;
	private String telefono;
	private Membresia membresia;
	
	
	
	/**
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @param membresia
	 * @param telefono
	 */
	public Miembro(int id, String nombre, String apellido,String telefono) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.membresia = new Membresia();
	}
	
	/**
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @param telefono
	 * @param membresia
	 */
	public Miembro(int id, String nombre, String apellido,String telefono, Membresia membresia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.membresia = membresia;
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
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
	
	
	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the membresia
	 */
	public Membresia getMembresia() {
		return membresia;
	}

	/**
	 * @param membresia the membresia to set
	 */
	public void setMembresia(Membresia membresia) {
		this.membresia = membresia;
	}

	@Override
	public String toString() {
		return String.format("Miembro [id=%s, nombre=%s, apellido=%s, telefono=%s, membresia=%s]", id, nombre, apellido,
				telefono, membresia);
	}
	
	
	
	
	
	
}
