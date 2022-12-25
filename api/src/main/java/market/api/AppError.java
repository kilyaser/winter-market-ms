package market.api;


public class AppError {
    private int statusCode;
    private String message;
    public AppError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

}
