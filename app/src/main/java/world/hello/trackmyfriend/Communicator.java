package world.hello.trackmyfriend;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by nprasad on 16/06/16.
 */
public class Communicator extends AsyncTask<Void, Void, Void> {


    private String data;
    HttpURLConnection urlConnection = null;

    protected void onPreExecute() {
        //display progress dialog.

    }

    protected Void doInBackground(Void... params) {
        URL url = null;
        try {
            url = new URL("http://www.trackmyfriend.comlu.com/receive.php?");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        System.setProperty("http.proxyHost", "trackmyfriend.comlu.com");
        System.setProperty("http.proxyPort", "8080");
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            postDataToServer(data);
            String responseFromServer = receiveDataFromServer(urlConnection);
            Log.d("LocationActivity", responseFromServer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void postDataToServer(String dataToSend) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(urlConnection.getOutputStream());
        outputStreamWriter.write(dataToSend);
        outputStreamWriter.flush();
    }

    private String receiveDataFromServer(URLConnection urlConnection) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        return sb.toString();
    }

    protected void onPostExecute(Void result) {
        // dismiss progress dialog and update ui
        Log.d("LocationActivity", "Data sent successfully");

    }

    public void setData(String data) {
        this.data = data;
    }
}
