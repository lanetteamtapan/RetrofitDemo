package com.example.lcom76.retrofitdemo.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lcom76.retrofitdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewTemplate1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String KEY_NEWS = "param1";
    private static final String KEY_IMAGES = "param2";
    private static final String KEY_HEADING = "param3";
    @Bind(R.id.tvHeading)
    TextView tvHeading;
    @Bind(R.id.tvdetails)
    TextView tvdetails;
    @Bind(R.id.constraintLayout2)
    ConstraintLayout constraintLayout2;
    @Bind(R.id.ivContent)
    ImageView ivContent;

    // TODO: Rename and change types of parameters
    public String news;
    public String image;
    public String heading;


    public NewTemplate1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param news  Parameter 1.
     * @param image Parameter 2.
     * @return A new instance of fragment NewTemplate1.
     */
    // TODO: Rename and change types and number of parameters
    public static NewTemplate1 newInstance(String news, String image, String heading) {
        NewTemplate1 fragment = new NewTemplate1();
        Bundle args = new Bundle();
        args.putString(KEY_NEWS, news);
        args.putString(KEY_IMAGES, image);
        args.putString(KEY_HEADING, heading);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_template1, container, false);
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            news = getArguments().getString(KEY_NEWS);
            image = getArguments().getString(KEY_IMAGES);
            heading = getArguments().getString(KEY_HEADING);


            Uri imageUri = Uri.parse(image);
            Glide.with(getActivity()).load(imageUri).into(ivContent);
            tvHeading.setText(heading);
            tvdetails.setText(news);
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.ivContent)
    public void onClick() {
    }


}
