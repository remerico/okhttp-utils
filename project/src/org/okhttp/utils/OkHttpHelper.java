package org.okhttp.utils;

import java.io.IOException;

import android.os.Handler;
import android.os.Looper;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class OkHttpHelper {
	
	protected OkHttpClient client = new OkHttpClient();
	
	protected Handler mainHandler = new Handler(Looper.getMainLooper());
	
	
	public void get(String url, RequestParams params, final StringResponseHandler handler) {
		
		if (handler == null) return;
		
		String paramString = ((params != null) ? ("?" + params.toString()) : "");
		
		Request request = new Request.Builder()
			.url(url + paramString)
			.build();
		
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onFailure(Request request, final IOException error) {
				
				mainHandler.post(new Runnable() {
					@Override
					public void run() {
						handler.onFailure(error, -1);
					}
				});
				
			}

			@Override
			public void onResponse(final Response response) throws IOException {
		
				final String body = response.body().string();
				
				mainHandler.post(new Runnable() {
					@Override
					public void run() {
						if (response.isSuccessful()) {
							handler.onSuccess(body);
						}
						else {
							int code = response.code();
							handler.onFailure(new HttpResponseException(code, body), code);
						}
					}
				});

				
			}
		});
		
	}

}
