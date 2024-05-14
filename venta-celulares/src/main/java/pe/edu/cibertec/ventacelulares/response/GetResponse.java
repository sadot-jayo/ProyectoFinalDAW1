package pe.edu.cibertec.ventacelulares.response;

public record GetResponse<T>(String code, String error, T data) {
}
