package com.example.lcom76.retrofitdemo.Retrofit;

import com.example.lcom76.retrofitdemo.GsonModel.Album;
import com.example.lcom76.retrofitdemo.GsonModel.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by lcom76 on 16/6/16.
 */

public class MyApiEndpointInterface {

    public static interface BooksAPI {

        /*Retrofit get annotation with our URL
           And our method that will return us the list ob Book
        */
        @GET("/posts")
        Call<List<Book>> getBooks();

        @POST("/posts")
        Call<Book> sendPost(@Body Book book);
    }

    public static interface AlbumApis {

        @GET("/json/glide.json")
        Call<List<Album>> getAlbums();

    }
}
