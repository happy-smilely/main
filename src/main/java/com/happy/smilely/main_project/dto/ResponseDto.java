package com.happy.smilely.main_project.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseDto {

	private boolean success;
	private int code;
	private String msg;
	private Object data;
	
	@Getter
	public enum Response {
        SUCCESS(200, "success");
		
        int code;
        String msg;

        Response(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

    }
	
    public static ResponseDto setFail(int code, String msg) {
    	return ResponseDto.builder()
    			.success(false)
				.code(code)
				.msg(msg)
				.build();
    }
    
	public static ResponseDto setSuccess() {
		return ResponseDto.builder()
				.success(true)
				.code(Response.SUCCESS.getCode())
				.msg(Response.SUCCESS.getMsg())
				.build();
	}
	
	public static ResponseDto setSuccess(String msg) {
		return ResponseDto.builder()
				.success(true)
				.code(Response.SUCCESS.getCode())
				.msg(msg)
				.build();
	}
	
	public static ResponseDto setSuccess(Object data) {
		return ResponseDto.builder()
				.success(true)
				.code(Response.SUCCESS.getCode())
				.msg(Response.SUCCESS.getMsg())
				.data(data)
				.build();
	}
	
}
