package com.example.lcom76.retrofitdemo.Adapers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lcom76.retrofitdemo.GsonModel.Book;
import com.example.lcom76.retrofitdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lcom76 on 16/6/16.
 */

public class Listadapter extends BaseAdapter {
    private List<Book> objects = new ArrayList<Book>();

    private Context context;
    private LayoutInflater layoutInflater;

    public Listadapter(Context context,ArrayList<Book> books) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = books;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Book getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_listview, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Book) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Book object, ViewHolder holder) {
        //TODO implement
        holder.text2.setText(object.getTitle());

    }

    static class ViewHolder {
        @Bind(R.id.text2)
        TextView text2;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
