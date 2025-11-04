package domain;

public class Miembro{
	
	private int id;
	private String nombre;
	private String apellido; 
	private String password;
	private Membresia membresia;
	
	
	
	/**
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @param membresia
	 * @param password
	 */
	public Miembro(int id, String nombre, String apellido,String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.membresia = new Membresia();
	}
	
	/**
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @param password
	 * @param membresia
	 */
	public Miembro(int id, String nombre, String apellido,String password, Membresia membresia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
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
	
	public String getNombreApellido() {
		return nombre+" "+apellido;
	}
	
	
	/**
	 * @return the password
	 */
	public String getpassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setpassword(String password) {
		this.password = password;
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
		return String.format("Miembro [id=%s, nombre=%s, apellido=%s, password=%s, membresia=%s]", id, nombre, apellido,
				password, membresia);
	}
	
	
	
	
	
	
	
}
