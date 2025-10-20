package domain;

public class Autor {
	
	private int id;
	private String nombre;
	private String apellido;
	private String pais;
	
	
	public Autor(int id) {
		super();
		this.id = id;
		this.nombre="Anonimo";
		this.apellido="ninguno";
	}


	/**
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @param pais
	 */
	public Autor(int id, String nombre, String apellido, String pais) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.pais = pais;
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

	public String getNombreApellido()
	{
		return this.nombre + " "+this.apellido;
	}
	
	/**
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}


	/**
	 * @param pais the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}


	@Override
	public String toString() {
		return String.format("Autor [id=%s, nombre=%s, apellido=%s, pais=%s]", id, nombre, apellido, pais);
	}
	
	
	
}