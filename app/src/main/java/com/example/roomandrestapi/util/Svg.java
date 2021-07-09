package com.example.roomandrestapi.util;

import android.content.Context;
import android.widget.ImageView;

import com.pixplicity.sharp.Sharp;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Svg {
    private static OkHttpClient httpClient;

    public static void fetchSvg(Context context, String url, final ImageView target) {
        if (httpClient == null) {
            httpClient = new OkHttpClient.Builder()
                    .cache(new Cache(context.getCacheDir(), 5 * 1024 * 1014))
                    .build();
        }

        Request request = new Request.Builder().url(url).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                InputStream stream = Objects.requireNonNull(response.body()).byteStream();
                Sharp.loadInputStream(stream).into(target);
                stream.close();
            }
        });
    }
}
