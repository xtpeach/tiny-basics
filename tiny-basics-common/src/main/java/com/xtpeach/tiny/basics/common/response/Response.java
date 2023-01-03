package com.xtpeach.tiny.basics.common.response;

import com.xtpeach.tiny.basics.common.enums.response.ResponseCodeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;

@ToString
public class Response<T> {

    @Schema(description = "状态码", example = "200")
    private String status;

    @Schema(description = "status等于200时，可能会返回的数据集")
    private T data;

    @Schema(description = "异常时的提示", example = "参数错误")
    private String message;

    /**
     * 操作成功状态码
     */
    public static final String SUCCESS = ResponseCodeEnum.SUCCESS.getCode();

    /**
     * 操作失败状态码
     */
    public static final String FAILURE = ResponseCodeEnum.FAILURE.getCode();

    public Response() {
    }

    public Response(String status) {
        this.data = null;
        this.status = status;
        this.message = "";
    }

    public Response(String status, T data) {
        this.status = status;
        this.data = data;
        this.message = "";
    }

    public Response(String status, String message) {
        this.status = status;
        this.data = null;
        this.message = message;
    }

    public Response(String status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }


    public String getStatus() {
        return status;
    }

    public Response<T> setStatus(String status) {
        this.status = status;
        return this;
    }

    public T getData() {
        return data;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public static <T> boolean isValid(Response<T> res) {
        return res != null && SUCCESS.equals(res.getStatus()) && res.getData() != null;
    }

    public static <T> Response<T> success() {
        return new Response<>(SUCCESS);
    }

    /**
     * 操作成功返回响应
     *
     * @param data 返回数据
     * @return 响应结果
     */
    public static <T> Response<T> success(T data) {
        return new Response<>(SUCCESS, data);
    }

    /**
     * 操作失败返回响应
     *
     * @param message 错误信息
     * @return 响应结果
     */
    public static <T> Response<T> fail(String message) {
        return new Response<>(FAILURE, message);
    }

    /**
     * 操作失败返回响应，可自定义状态码
     *
     * @param status  状态码
     * @param message 错误信息
     * @return 响应结果
     */
    public static <T> Response<T> fail(String status, String message) {
        return new Response<>(status, message);
    }

}
