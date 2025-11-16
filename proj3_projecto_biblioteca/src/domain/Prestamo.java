package domain;

import java.time.LocalDate;
import java.util.Objects;

public class Prestamo {
	
	private int id;
	private Libro libro;
	private Miembro miembro;
	private LocalDate fecha_inicial_prestamo;
	private float tiempo_prestamo;
	/**
	 * @param id
	 * @param libro
	 * @param miembro
	 * @param localDate
	 * @param tiempo_prestamo
	 */
	public Prestamo(int id, Libro libro, Miembro miembro, LocalDate localDate, float tiempo_prestamo) {
		super();
		this.id = id;
		this.libro = libro;
		this.miembro = miembro;
		this.fecha_inicial_prestamo = localDate;
		this.tiempo_prestamo = tiempo_prestamo;
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
	 * @return the libro
	 */
	public Libro getLibro() {
		return libro;
	}
	/**
	 * @param libro the libro to set
	 */
	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	/**
	 * @return the miembro
	 */
	public Miembro getMiembro() {
		return miembro;
	}
	/**
	 * @param miembro the miembro to set
	 */
	public void setMiembro(Miembro miembro) {
		this.miembro = miembro;
	}
	/**
	 * @return the fecha_inicial_prestamo
	 */
	public LocalDate getFecha_inicial_prestamo() {
		return fecha_inicial_prestamo;
	}
	/**
	 * @param fecha_inicial_prestamo the fecha_inicial_prestamo to set
	 */
	public void setFecha_inicial_prestamo(LocalDate fecha_inicial_prestamo) {
		this.fecha_inicial_prestamo = fecha_inicial_prestamo;
	}
	/**
	 * @return the tiempo_prestamo
	 */
	public float getTiempo_prestamo() {
		return tiempo_prestamo;
	}
	/**
	 * @param tiempo_prestamo the tiempo_prestamo to set
	 */
	public void setTiempo_prestamo(float tiempo_prestamo) {
		this.tiempo_prestamo = tiempo_prestamo;
	}
	@Override
	public String toString() {
		return String.format("Prestamo [id=%s, libro=%s, miembro=%s, fecha_inicial_prestamo=%s, tiempo_prestamo=%s]",
				id, libro, miembro, fecha_inicial_prestamo, tiempo_prestamo);
	}
	@Override
	public int hashCode() {
		return Objects.hash(fecha_inicial_prestamo, id, libro, miembro);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prestamo other = (Prestamo) obj;
		return Objects.equals(fecha_inicial_prestamo, other.fecha_inicial_prestamo) && id == other.id
				&& Objects.equals(libro, other.libro) && Objects.equals(miembro, other.miembro);
	}
	
	
	
	
	
}
