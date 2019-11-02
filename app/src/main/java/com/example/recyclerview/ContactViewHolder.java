package com.example.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactViewHolder extends RecyclerView.ViewHolder {
    TextView contactID,contactName,contactEmail,contactMobile;

    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);

        contactID=itemView.findViewById(R.id.contactID);
        contactName=itemView.findViewById(R.id.contactName);
        contactEmail=itemView.findViewById(R.id.contactEmail);
        contactMobile=itemView.findViewById(R.id.contactMobile);
    }
}
