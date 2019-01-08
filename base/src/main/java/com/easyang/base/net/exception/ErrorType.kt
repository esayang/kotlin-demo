package com.easyang.base.net.exception

/**
 * @author SC16004984
 * @date 2019/1/8 0008.
 */
enum class ErrorType {
    LOCAL_ERROR, // 本地错误
    HTTP_ERROR, // 网络错误
    PARSE_ERROR, // 解析错误
    SERVER_ERROR, // 服务端返回错误
    CONNECT_ERROR, //网络连接错误
    TOKEN_UNVAILD_ERROR, // token 验证错误
    UNKNOWN  // 未知错误
}