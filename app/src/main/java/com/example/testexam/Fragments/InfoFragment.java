package com.example.testexam.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testexam.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoFragment extends Fragment {
    @BindView(R.id.iv_activityDescrF)
    ImageView imageViewF;

    @BindView(R.id.name_activityDescrF)
    TextView nameF;

    @BindView(R.id.address_activityDescrF)
    TextView addressF;

    @BindView(R.id.author_activityDescrF)
    TextView authorF;

    @BindView(R.id.description_activityDescrF)
    TextView descriptionF;

    public static InfoFragment newInstance(Bundle args) {
        InfoFragment infoFragment = new InfoFragment();
        infoFragment.setArguments(args);
        return infoFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, view);

        String nam = getArguments().getString("name");
        String imag = getArguments().getString("image");
        String auth = getArguments().getString("author");
        String descr = getArguments().getString("description");
        String addr = getArguments().getString("address");

        nameF.setText(nam);
        authorF.setText(auth);
        descriptionF.setText(descr);
        addressF.setText(addr);
        if (imag != null && !imag.isEmpty()) {
            Picasso.get().load(imag).into(imageViewF);
        } else {
            imageViewF.setImageResource(R.drawable.ic_launcher_background);
        }
        return view;
    }
}
