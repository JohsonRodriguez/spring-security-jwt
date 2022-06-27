package com.example.appcocherabyron.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcocherabyron.R;
import com.example.appcocherabyron.services.UsersResponse;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    private List<UsersResponse> userResponseList;
    private Context context;
    /*private ClickedItem clickedItem;*/

    public UsersAdapter() {
    }

    public void setData(  List<UsersResponse> userResponseList){
        this.userResponseList=userResponseList;
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new UsersAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_cars,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UsersResponse usersResponse = userResponseList.get(position);
        String Registrationplate = usersResponse.getRegistrationplate();
        String Carmodel = usersResponse.getCarmodel();
        String Carcolor = usersResponse.getCarcolor();
        String Carbrand = usersResponse.getCarbrand();

        holder._registrationplate.setText(Registrationplate);
        holder._carmodel.setText(Carmodel);
        holder._carcolor.setText(Carcolor);
        holder._carbrand.setText(Carbrand);

        holder.Imagemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*clickedItem.ClickedUser(personaResponse);*/
            }
        });

    }

    @Override
    public int getItemCount() {

        return userResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView _registrationplate, _carbrand, _carcolor, _carmodel;
        ImageView Imagemore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            _registrationplate=itemView.findViewById(R.id.tvregistrationplate);
            _carbrand=itemView.findViewById(R.id.tvmarca);
            _carcolor=itemView.findViewById(R.id.tvcolor);
            _carmodel=itemView.findViewById(R.id.tvmodelo);
            Imagemore=itemView.findViewById(R.id.imageMore);

        }
    }
}
