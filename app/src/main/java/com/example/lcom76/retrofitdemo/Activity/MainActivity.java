package com.example.lcom76.retrofitdemo.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.lcom76.retrofitdemo.Adapers.Listadapter;
import com.example.lcom76.retrofitdemo.GsonModel.Book;
import com.example.lcom76.retrofitdemo.R;
import com.example.lcom76.retrofitdemo.Retrofit.MyApiEndpointInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String ROOT_URL = "http://jsonplaceholder.typicode.com";

    //Strings to bind with intent will be used to send data to other activity
    public static final String KEY_BOOK_ID = "key_book_id";
    public static final String KEY_BOOK_NAME = "key_book_name";
    public static final String KEY_BOOK_PRICE = "key_book_price";
    public static final String KEY_BOOK_STOCK = "key_book_stock";
    private static final String TAG = "MainActivity";
    @Bind(R.id.listViewBooks)
    ListView listViewBooks;
    @Bind(R.id.Addbtn)
    Button Addbtn;
    @Bind(R.id.floatingActionButton2)
    FloatingActionButton floatingActionButton2;
    @Bind(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    private MyApiEndpointInterface.BooksAPI api;

    @OnItemSelected(R.id.listViewBooks)
    void onItemSelected(int position) {
        Log.d(TAG, "onItemSelected: " + position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getBooks();

    }

    private void getBooks() {
        //While the app fetched data we are displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);

        //Creating a rest adapter

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //Creating an object of our api interface
        api = retrofit.create(MyApiEndpointInterface.BooksAPI.class);

        //Defining the method
        Call<List<Book>> getbooks = api.getBooks();
        getbooks.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                loading.dismiss();
                listViewBooks.setAdapter(new Listadapter(MainActivity.this, new ArrayList<>(response.body())));
                Log.d(TAG, "onResponse: " + response.message());
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {

            }
        });


    }



    @OnClick({R.id.Addbtn, R.id.floatingActionButton2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Addbtn:
                final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);

                Book book = new Book();
                book.setTitle("this is book added by retrofit");
                book.setBody("quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto");
                Call<Book> sendpost = api.sendPost(book);
                sendpost.enqueue(new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {
                        loading.dismiss();
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this, R.style.AppTheme);
                        alertDialog.setMessage(response.body().getTitle() + " is inserted ").show();
                    }

                    @Override
                    public void onFailure(Call<Book> call, Throwable t) {
                        loading.dismiss();
                    }
                });
                break;
            case R.id.floatingActionButton2:
                startActivity(new Intent(this,NewsActivity.class));
                break;
        }
    }
}