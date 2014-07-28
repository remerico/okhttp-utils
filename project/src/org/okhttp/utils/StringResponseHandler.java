package org.okhttp.utils;

public abstract class StringResponseHandler {
	
	public abstract void onSuccess(String data);
	public abstract void onFailure(Throwable error, int code);

}
