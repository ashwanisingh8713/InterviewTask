package com.interview.task.repo;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.interview.task.BuildConfig;
import com.interview.task.model.ItemBean;
import com.interview.task.model.TwoColumnBean;
import com.interview.task.model.ServerResponceBean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ashwanisingh on 25/06/18.
 */
public class NetworkCall {

    public static void fetchSearchQuery(Response response, String query) {

        Observable.just(query)
                .subscribeOn(Schedulers.newThread())
                .map((queryStr)->{
                    final ServerResponceBean responceBean = createBirdRequest(query);

                    final ArrayList<TwoColumnBean> finalBean = new ArrayList<>();
                    final List<ServerResponceBean.HitsBean> getHits = responceBean.getHits();
                    int hitSize = getHits.size();
                    for(int i=0; i<hitSize; i+=2) {
                        ServerResponceBean.HitsBean bean1 = null;
                        ServerResponceBean.HitsBean bean2 = null;
                        ItemBean itemBean1 = null;
                        ItemBean itemBean2 = null;

                        bean1 = getHits.get(i);
                        itemBean1 = new ItemBean(bean1.getPreviewURL(), bean1.getTags());
                        if(hitSize > i+1 ) {
                            bean2 = getHits.get(i + 1);
                            itemBean2 = new ItemBean(bean2.getPreviewURL(), bean2.getTags());
                        }

                        TwoColumnBean twoColumnBean = new TwoColumnBean(itemBean1, itemBean2);
                        finalBean.add(twoColumnBean);
                    }

                    return finalBean;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((reponse)-> {
                    if(reponse != null) {
                        response.onNext(reponse);
                    }
                },
                (t)-> {
                    if(response != null) {
                        response.onError(t);
                    }
                },
                ()-> {
                    if(response != null) {
                        response.onComplete();
                    }
                }

        );

    }

    private static ServerResponceBean createBirdRequest(String query) throws Exception {
        final String urlStr = BuildConfig.BASE_URL+BuildConfig.PIXABAY_KEY+"&q="+query+"&image_type="+BuildConfig.TYPE;
        HttpURLConnection urlConnection = null;
        URL url = null;
        ServerResponceBean responceBean = null;
        InputStream inStream = null;
        try {
            url = new URL(urlStr);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            inStream = urlConnection.getInputStream();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));
            String temp, response = "";
            while ((temp = bReader.readLine()) != null) {
                response += temp;
            }

            JsonParser parser = new JsonParser();
            JsonElement mJson =  parser.parse(response);
            Gson gson = new Gson();
            responceBean = gson.fromJson(mJson, ServerResponceBean.class);

        } finally {
            if (inStream != null) {
                inStream.close();
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return responceBean;
    }

}
