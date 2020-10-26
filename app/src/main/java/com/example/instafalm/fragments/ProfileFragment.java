package com.example.instafalm.fragments;

import android.util.Log;

import com.example.instafalm.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostsFragment {

    @Override
    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        //This include allows the function to all query for all the users related to each post
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        //Can create a limit on posts seen by doing this:
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e  != null){
                    //Check if the exception is not null, if it is null then everything is working
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                //Now iterate through all the posts and get the data for them
                for(Post post: posts){
                    Log.i(TAG, "Posts: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
