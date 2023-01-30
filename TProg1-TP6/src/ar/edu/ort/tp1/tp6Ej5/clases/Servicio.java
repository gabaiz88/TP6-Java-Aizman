package ar.edu.ort.tp1.tp6Ej5.clases;

public class Servicio {
	private int codigo; // de 1 a 5
	private String desc;
	private float horas;// de 0.5 a 4

	public Servicio(int codigo, String desc, float horas) {
		this.codigo = codigo;
		this.desc = desc;
		this.horas = horas;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public float getHoras() {
		return horas;
	}

	public void setHoras(float horas) {
		this.horas = horas;
	}

	@Override
	public String toString() {
		return "Servicio [codigo=" + codigo + ", desc=" + desc + ", horas=" + horas + "]";
	}
}
