package com.example.bucket.utils;

import com.example.bucket.model.Status;

public class StatusHelper {
	
	public static Status getStatus(int code) {
		Status status = new Status();
		if(code >= 200 && code <= 399) {
			switch (code) {
			case 200:
				status.setCode(Constants.SUCCESS_GET_CODE);
				status.setDetail(Constants.SUCCESS);
				break;
			case 201:
				status.setCode(Constants.SUCCESS_CREATE_CODE);
				status.setDetail(Constants.SUCCESS);
				break;
			case 202:
				status.setCode(Constants.EMPTY_CODE);
				status.setDetail(Constants.EMPTY);
				break;
			}
		}else if(code >= 400 && code <=499) {
			switch (code) {
			case 404:
				status.setCode(Constants.ERROR_CODE);
				status.setDetail(Constants.ERROR);
				break;
			}
		}else if(code >= 500) {
			switch (code) {
			case 500:
				status.setCode(Constants.FAILURE_CODE);
				status.setDetail(Constants.FAULURE);
				break;
			}
		}
		return status;
	}

}
