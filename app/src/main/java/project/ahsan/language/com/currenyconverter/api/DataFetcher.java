package project.ahsan.language.com.currenyconverter.api;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataFetcher {
    // https://jsonvat.com/
    private static DataFetcher dataFetcher = new DataFetcher();

    private FetchingCallback fetchingCallback;

    OkHttpClient client;
    MediaType json;

    private DataFetcher(){

    }

    public static DataFetcher sharedInstance(){
        return dataFetcher;
    }


    public void fecthData(FetchingCallback fetchingCallback){
        this.fetchingCallback = fetchingCallback;
        client = new OkHttpClient();
        json = MediaType.parse("application/json; charset=utf-8");
        GetJsonFile getJsonFile = new GetJsonFile();
        getJsonFile.execute();
    }


    private void dataFetched(String json){
        Log.d("TAG", "dataFetched: " + json);
        fetchingCallback.dataFetchingSuccess(json);
    }

    private void dataFetchingCanceled(){
        fetchingCallback.dataFetchingCanceled();
    }


    class GetJsonFile extends AsyncTask<String,String,String> {


        @Override
        protected String doInBackground(String... strings) {
            String response = null;
            try {
                response = get("https://jsonvat.com/");
                return  response;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s == null){
                dataFetchingCanceled();
                return;
            }
            dataFetched(s);
        }

        protected String get(String url)throws IOException {
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }
    }

    public interface FetchingCallback{
        void dataFetchingSuccess(String json);
        void dataFetchingCanceled();
    }

}
