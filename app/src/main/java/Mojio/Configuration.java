package Mojio;

/**
 * Created by robchartier on 15-01-16.
 */
public class Configuration {
    private String application = "39f443b0-dcd0-4b60-b471-27d3e41e31c6";
    private String secret = "4d73a7b3-944c-4dc9-a75b-93ad2e8b4264";
    private String host = "https://api.moj.io";
    public String getApplication() {
        return this.application;
    }
    public void setApplication(String application) {
        this.application = application;
    }
    public String getSecret(){
        return this.secret;
    }
    public void setSecret(String secret){
        this.secret = secret;
    }

    public String getHost(){
        return this.host;
    }
    public void setHost(String host){
        this.host = host;
    }

    private static Configuration _configuration = new Configuration();
    public static Configuration getDefault() {
        return _configuration;
    }

}
