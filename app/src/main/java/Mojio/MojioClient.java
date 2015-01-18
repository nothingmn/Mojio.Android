package Mojio;

import android.content.Context;
import android.graphics.Bitmap;

import com.loopj.android.http.*;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by robchartier on 15-01-16.
 */
public class MojioClient {

    // A SyncHttpClient is an AsyncHttpClient
    public AsyncHttpClient syncClient = new SyncHttpClient();
    public AsyncHttpClient asyncClient = new AsyncHttpClient();

    private Configuration _config = null;
    public MojioClient(Configuration configuration){
        if(configuration==null) configuration = Configuration.getDefault();
        _config=configuration;

        getClient().addHeader("Content-Type", "application/json");
    }
    private AsyncHttpClient getClient()
    {
        // Return the synchronous HTTP client when the thread is not prepared
        if (android.os.Looper.myLooper() == null) {
            return syncClient;
        } else {
            return asyncClient;
        }
    }
    private void setHeader(String id) {
        MojioAPIToken = id;
        getClient().addHeader("MojioAPIToken", id);
        System.out.println("token header set" + id);
    }
    String MojioAPIToken;
    public String getMojioAPIToken() {
        return MojioAPIToken;
    }
    public void setMojioAPIToken(String value) {
        MojioAPIToken = value;
        setHeader(value);
    }

    public void setCookieStore(PersistentCookieStore cookieStore) {
        getClient().setCookieStore(cookieStore);
    }

    private JsonHttpResponseHandler loginHandler = null;
    public void Login(String userName, String passWord, JsonHttpResponseHandler handler) {
        loginHandler = handler;
        System.out.println("login called");
        String loginUrl = _config.getHost() + "/v1/Login/"+_config.getApplication()+"?secretKey="+_config.getSecret()+"&minutes=43829&userOrEmail=" + EncodingUtil.encodeURIComponent(userName) + "&password=" + EncodingUtil.encodeURIComponent(passWord);
        System.out.println("login url=" + loginUrl);


        getClient().post(loginUrl, new JsonHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, org.apache.http.Header[] headers, String errorResponse, Throwable e) {
                loginHandler.onFailure(statusCode, headers, errorResponse, e);
            }

            @Override
            public void onSuccess(int statusCode, org.apache.http.Header[] headers, JSONObject response) {
                try {
                    String id = response.getString("_id");
                    setHeader(id);

                } catch (Exception e){
                    System.out.println("success errr"+e);
                }
                loginHandler.onSuccess(statusCode, headers, response);
                System.out.println("success obj");
            }

        });
    }
    public void Vehicles(JsonHttpResponseHandler handler){
        System.out.println("Vehicles called");
        String url = _config.getHost()+ "/v1/Vehicles";
        System.out.println("Vehicles url=" + url);
        getClient().get( url, handler);
    }

}
