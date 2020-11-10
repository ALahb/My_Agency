package tn.polytechnique.myagency;



import android.content.Context;
import android.content.Intent;
//import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.RequestOptions;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;


public class campingAdapter extends RecyclerView.Adapter<campingAdapter.MyViewHolder>{
    private LayoutInflater inflater;
    private Context context;
    private List<getcamping> getcamping;

   RequestOptions option;
    public campingAdapter(Context context, List<getcamping> listecamping)
    {

        this.getcamping = listecamping;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = inflater.inflate(R.layout.row_camping,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final getcamping currentCamping = getcamping.get(position);
        String price = String.valueOf(currentCamping.getPrix());
        holder.tv_price.setText(price + "dt");
        holder.tv_date.setText(currentCamping.getDate());
        holder.tv_destination.setText(currentCamping.getDestination());

        Glide.with(context).load(currentCamping.getImage()).apply(option).into(holder.tv_photo);
        holder.linearLayoutplat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, CampDetails.class);
                i.putExtra("description",currentCamping.getDescription());
                i.putExtra("prix",currentCamping.getPrix()+"");
                i.putExtra("destination",currentCamping.getDestination());
                i.putExtra("date",currentCamping.getDate());
                i.putExtra("image",currentCamping.getImage());
                i.putExtra("id",currentCamping.getIdComping()+"");


                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);


            }
        });

    }

    @Override
    public int getItemCount() {

        return getcamping.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_price,tv_date,tv_destination;
        ImageView tv_photo;
        LinearLayout linearLayoutplat;

        public MyViewHolder(View itemView) {
            super(itemView);

            linearLayoutplat = itemView.findViewById(R.id.container_camping);
            tv_price = itemView.findViewById(R.id.prix);
            tv_date = itemView.findViewById(R.id.date);
            tv_destination = itemView.findViewById(R.id.destination);
            tv_photo = itemView.findViewById(R.id.photo_camping);
        }
    }
}

