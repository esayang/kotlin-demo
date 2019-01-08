package com.easyang.base.net.exception;

import com.google.gson.JsonParseException;
import org.json.JSONException;
import retrofit2.HttpException;

import java.net.*;
import java.text.ParseException;

/**
 * Created by SC16004984 on 2018/2/8.
 */

public class ExceptionEngine {

    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ApiException handleException(Throwable e) {
        ApiException ex = null;
        while (e != null) {
            if (e instanceof HttpException){             //HTTP错误
                HttpException httpException = (HttpException) e;
                ex = new ApiException(e, ErrorType.HTTP_ERROR);
                switch(httpException.code()){
                    case FORBIDDEN:
                        ex.msg = "服务器拒绝该请求";
                        break;
                    case NOT_FOUND:
                        ex.msg = "请求资源不存在";
                        break;
                    case REQUEST_TIMEOUT:
                        ex.msg = "请求超时";
                        break;
                    case GATEWAY_TIMEOUT:
                        ex.msg = "网关连接超时";
                    case INTERNAL_SERVER_ERROR:
                        ex.msg = "对不起，服务器处理请求出现异常";
                        break;
                    case BAD_GATEWAY:
                    case SERVICE_UNAVAILABLE:
                        ex.msg = "服务器当前无法处理请求";
                        break;
                    default:
                        ex.msg = HttpConstant.INSTANCE.getHTTP_ERROR_MESSAGE();  //均视为网络错误
                        break;
                }
            } else if (e instanceof ServerException){    //服务器返回的错误
                ServerException resultException = (ServerException) e;
                ex = new ApiException(resultException, ErrorType.SERVER_ERROR);
                ex.msg = resultException.getMessage();
            } else if (e instanceof JsonParseException
                    || e instanceof JSONException
                    || e instanceof ParseException){
                ex = new ApiException(e, ErrorType.PARSE_ERROR);
                ex.msg = HttpConstant.INSTANCE.getDATA_PARSE_ERROR();
                break;//均视为解析错误
            }else if (e instanceof SocketTimeoutException){
                ex = new ApiException(e,ErrorType.HTTP_ERROR);
                ex.msg = HttpConstant.INSTANCE.getHTTP_TIMEOUT_MESSAGE();
                break;
            }
            else if(e instanceof ConnectException ||e instanceof SocketException){
                ex = new ApiException(e, ErrorType.CONNECT_ERROR);
                ex.msg = HttpConstant.INSTANCE.getHTTP_CONNECT_MESSAGE();  //均视为网络错误
                break;
            }else if (e instanceof TokenInvalidException){
                ex = new ApiException(e,ErrorType.TOKEN_UNVAILD_ERROR);
                ex.msg = HttpConstant.INSTANCE.getTOKEN_UNVALID_MESSAGE();
                break;
            }if (e instanceof UnknownHostException || e instanceof UnknownServiceException
                    ||e instanceof URISyntaxException || e instanceof ProtocolException){
                ex = new ApiException(e,ErrorType.HTTP_ERROR);
                ex.msg = HttpConstant.INSTANCE.getHTTP_CONNECT_MESSAGE();
                break;
            }
            e = e.getCause();
        }
        if (ex == null){
            ex = new ApiException(e, ErrorType.UNKNOWN);
            ex.msg = HttpConstant.INSTANCE.getHTTP_ERROR_MESSAGE();          //未知错误
        }
        return ex;
    }
}
