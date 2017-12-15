package org.nusabit.demoapp.presentation.search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.nusabit.demoapp.R;

/**
 * Created by sid-tech on 12/15/17.
 */

class UserViewHolder extends RecyclerView.ViewHolder {
    final TextView textViewBio;
    final TextView textViewName;
    final ImageView imageViewAvatar;

    UserViewHolder(View v ) {
        super(v);
        imageViewAvatar = (ImageView) v.findViewById(R.id.imageview_userprofilepic);
        textViewName = (TextView) v.findViewById(R.id.textview_username);
        textViewBio = (TextView) v.findViewById(R.id.textview_user_profile_info);
    }
}
