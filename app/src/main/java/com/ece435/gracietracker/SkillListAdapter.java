package com.ece435.gracietracker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jdenn on 4/29/2017.
 */

public class SkillListAdapter extends ArrayAdapter<Skill> implements View.OnClickListener {

    public SkillListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public SkillListAdapter(Context context, int resource, List<Skill> items) {
        super(context, resource, items);
    }

    private Skill skill;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.layout_skill_list_item, null);

        }

        skill = getItem(position);

        if (skill != null) {
            TextView techniqueTitle = (TextView) v.findViewById(R.id.techniqueView);
            ImageView trainingVideoThumbnail = (ImageView) v.findViewById(R.id.trainingVideoThumbnail);
            ImageButton trainingVideoButton = (ImageButton) v.findViewById(R.id.trainingVideoButton);

            if (techniqueTitle != null) {
                techniqueTitle.setText(skill.getName());
            }
            if (trainingVideoThumbnail != null) {
//                trainingVideoThumbnail.setBackgroundDrawable
//                        (ContextCompat.getDrawable(null, R.drawable.gracie_video_thumbnail_1));
            }
            if (trainingVideoButton != null) {
                trainingVideoButton.setOnClickListener(this);
            }
        }

        return v;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.trainingVideoButton:
                if(skill != null){
                    String link = (String) skill.getLink();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(link));
                    getContext().startActivity(i);
                }
                break;
        }
    }
}
