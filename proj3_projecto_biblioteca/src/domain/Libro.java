package domain;

public class Libro {
	
	private int id_libro;
	private String titulo;
	private Genero genero;
	private float precio;
	private Autor autor;
	private Editorial editorial;
	private static int duracionPrestamo = 5;
	
	
	
	/**
	 * @param id_libro
	 * @param titulo
	 * @param genero
	 * @param precio
	 * @param autor
	 * @param editotrial
	 */
	public Libro(int id_libro, String titulo, Genero genero, float precio) {
		
		this.id_libro = id_libro;
		this.titulo = titulo;
		this.genero = genero;
		this.precio = precio;
		this.autor = new Autor(0);
		this.editorial = new Editorial(0);
	}
	/**
	 * @param id_libro
	 * @param titulo
	 * @param genero
	 * @param precio
	 * @param autor
	 * @param editotrial
	 */
	public Libro(int id_libro, String titulo, Genero genero, float precio, Autor autor, Editorial editorial) {
		
		this.id_libro = id_libro;
		this.titulo = titulo;
		this.genero = genero;
		this.precio = precio;
		this.autor = autor;
		this.editorial = editorial;
	}

	/**
	 * @return the id_libro
	 */
	public int getId_libro() {
		return id_libro;
	}

	/**
	 * @param id_libro the id_libro to set
	 */
	public void setId_libro(int id_libro) {
		this.id_libro = id_libro;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the genero
	 */
	public Genero getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	/**
	 * @return the precio
	 */
	public float getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}

	/**
	 * @return the autor
	 */
	public Autor getAutor() {
		return autor;
	}

	/**
	 * @param autor the autor to set
	 */
	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	/**
	 * @return the editotrial
	 */
	public Editorial getEditorial() {
		return editorial;
	}

	/**
	 * @param editotrial the editotrial to set
	 */
	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public int getDuracionPrestamo() {
		return duracionPrestamo;
	}
	
	
	@Override
	public String toString() {
		return String.format("Libro [id_libro=%s, titulo=%s, genero=%s, precio=%s, autor=%s, editotrial=%s]", id_libro,
				titulo, genero, precio, autor, editorial);
	}
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	
}
