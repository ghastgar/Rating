package com.curs.pau.jsoup;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ListView listView;
    private RedditAdapter redditAdapter;
    private List<RedditThing> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        RedditTask redditTask = new RedditTask();
        redditTask.execute();

        redditAdapter = new RedditAdapter(getApplicationContext(), data);
        listView.setAdapter(redditAdapter);
    }

    private class RedditTask extends AsyncTask<Void, Void, List<RedditThing>> {

        @Override
        protected List<RedditThing> doInBackground(Void... params) {
            List<RedditThing> result = new ArrayList<>();
            try {
                Document doc = Jsoup.connect("http://www.reddit.com/").get();
                Elements redditThings = doc.select("div.thing");
                Log.i("RedditElements size", "SIZE: " + redditThings.size());

                for (Element thing : redditThings) {
                    RedditThing redditThing = new RedditThing();
                    Elements title = thing.select("a.title");
                    if (title.size() == 1) {
                        // title
                        redditThing.title = title.first().text();
                        Log.i("RedditThing title", redditThing.title);


                        // img

                        Elements img = thing.select("img");
                        if (img.size() == 1) {
                            URL url = new URL("http:" + img.first().attr("src"));
                            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                            redditThing.img = bitmap;
                            Log.i("RedditThing img", "http:" + img.first().attr("src"));
                        }
                        result.add(redditThing);
                    }

                }
            } catch (IOException e) {
                Log.wtf("RedditTask", "Something went wrong");
                cancel(true);
            }
            return result;
        }
        @Override
        protected void onPostExecute(List<RedditThing> redditThings) {
            super.onPostExecute(redditThings);
            redditAdapter.addAll(redditThings);
        }
    }
}
