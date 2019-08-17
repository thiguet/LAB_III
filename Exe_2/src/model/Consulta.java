package model;
import java.io.Serializable;
import java.util.Date;

public class Consulta implements Serializable {
	private static final long serialVersionUID = -2564011982434534313L;
	private int codMedico,
				codPaciente,
				codConsulta;
	private Date dataConsulta;
	
	public int getCodMedico() {
		return codMedico;
	}
	public void setCodMedico(int codMedico) {
		this.codMedico = codMedico;
	}
	public int getCodPaciente() {
		return codPaciente;
	}
	public void setCodPaciente(int codPaciente) {
		this.codPaciente = codPaciente;
	}
	public Date getDataConsulta() {
		return dataConsulta;
	}
	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
	public int getCodConsulta() {
		return codConsulta;
	}
	public void setCodConsulta(int codConsulta) {
		this.codConsulta = codConsulta;
	}
}
// Using Date in JAVA 
/* String strData = "20161109103000";  
	Date dt = new SimpleDateFormat("yyyyMMddHHmmss").parse(strData);
	String dataFormatada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dt);
 * */