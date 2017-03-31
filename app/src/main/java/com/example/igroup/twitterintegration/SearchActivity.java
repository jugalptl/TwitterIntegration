package com.example.igroup.twitterintegration;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.SearchService;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;
import com.twitter.sdk.android.tweetui.UserTimeline;

public class SearchActivity extends ActionBarActivity {
    private boolean fladloading, endofsearchResult;
    private static String search_query = "#Sachin";
     private TweetTimelineListAdapter adapter;
    private static final String search_result_type = "recent";
    private static final Integer search_count = 20;
    private long maxId;
    ListView SearchList;
    private UserTimeline userTimeline;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setProgressBarIndeterminateVisibility(true);


         handleIntent(getIntent());

        SearchList =(ListView)findViewById(R.id.tweet_search) ;
        System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH"+search_query);
        SearchTimeline searchTimeline = new SearchTimeline.Builder().query(search_query).build();
        final TweetTimelineListAdapter timelineAdapter = new TweetTimelineListAdapter(this, searchTimeline);
        //setListAdapter(timelineAdapter);
        SearchList.setAdapter(timelineAdapter);
        SearchList.setEmptyView(findViewById(R.id.loading));





/*
        // TODO: Use a more specific parent
        final ViewGroup parentView = (ViewGroup) getWindow().getDecorView().getRootView();
        // TODO: Base this Tweet ID on some data from elsewhere in your app
        long tweetId = 631879971628183552L;
        TweetUtils.loadTweet(tweetId, new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                TweetView tweetView = new TweetView(SearchActivity.this, result.data);
                parentView.addView(tweetView);
            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Load Tweet failure", exception);
            }
        });
*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);

            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint("Enter tweets");


        return super.onCreateOptionsMenu(menu);
       // return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //

        int id = item.getItemId();
        if (id == R.id.menu_search){
            //onSearchRequested();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //setIntent(intent);
        handleIntent(intent);

    }

   
    private void handleIntent(Intent intent) {


       // search_query = intent.getStringExtra(SearchManager.QUERY);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())){


            search_query = intent.getStringExtra(SearchManager.QUERY);
            Log.d("Searcher",search_query);
            SearchTimeline searchTimeline = new SearchTimeline.Builder().query(search_query).build();
            final TweetTimelineListAdapter timelineAdapter = new TweetTimelineListAdapter(this, searchTimeline);
            //setListAdapter(timelineAdapter);
            SearchList.setAdapter(timelineAdapter);
            SearchList.setEmptyView(findViewById(R.id.loading));
        }

    }
}
