package constants;

public enum StatusCode {

    CODE_401(401,"Invalid access token"),
    CODE_400(400,""),
    CODE_500(500,""),
    CODE_404(404,"Not Found"),
    CODE_201(201, "CREATED"),
    CODE_204(204,"NO_CONTENT"),
    CODE_200(200, "SUCCESS")
    ;
    private final  int code;
    private final String msg;

    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;

    }
    public int getCode (){
        return code;
    }
    public String getMsg(){
        return msg;
    }
}