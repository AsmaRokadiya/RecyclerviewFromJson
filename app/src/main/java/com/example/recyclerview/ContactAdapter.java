package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {
    private List<Contacts> contactsList;

    public ContactAdapter(List<Contacts> contactsList) {
        this.contactsList = contactsList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

        Contacts contacts=contactsList.get(position);

        holder.contactID.setText(String.valueOf(contacts.getId()));
        holder.contactName.setText(String.valueOf(contacts.getName()));
        holder.contactEmail.setText(String.valueOf(contacts.getEmail()));
        holder.contactMobile.setText(String.valueOf(contacts.getPhone()));

    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }
}
