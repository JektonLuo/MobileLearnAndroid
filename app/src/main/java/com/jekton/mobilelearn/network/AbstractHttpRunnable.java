package com.jekton.mobilelearn.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jekton.mobilelearn.common.dv.AbstractDocument;
import com.jekton.mobilelearn.common.util.Logger;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Jekton
 *
 * This class is designed to work with the default Document implementation {@link AbstractDocument}
 * of the Document-View framework and hence the parameter type DocumentOps is expected to extend
 * {@link AbstractDocument}.
 */
public abstract class AbstractHttpRunnable implements Runnable {

    private static final String LOG_TAG = AbstractHttpRunnable.class.getSimpleName();

    private volatile Call mCall;
    private final OnResponseCallback mCallback;

    public AbstractHttpRunnable(OnResponseCallback callback) {
        mCallback = callback;
    }

    @Override
    public void run() {
        Request request = makeRequest();
        mCall = HttpClient.getInstance().newCall(request);

        try {
            Response response = mCall.execute();
            if (response.isSuccessful()) {
                mCallback.onResponseSuccess(response);
            } else {
                mCallback.onResponseFail(response);
            }
        } catch (IOException e) {
            Logger.d(LOG_TAG, e);
            mCallback.onNetworkFail();
        }

    }

    public void cancel() {
        mCall.cancel();
    }

    protected abstract @NonNull Request makeRequest();


}
