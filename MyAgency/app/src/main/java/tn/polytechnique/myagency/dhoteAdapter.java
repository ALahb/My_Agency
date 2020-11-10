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


public class dhoteAdapter extends RecyclerView.Adapter<dhoteAdapter.MyViewHolder>{
    private LayoutInflater inflater;
    private Context context;
    private List<getdhote> getdhote;

     RequestOptions option;
    public dhoteAdapter(Context context, List<getdhote> listedhote)
    {

        this.getdhote = listedhote;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
         option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = inflater.inflate(R.layout.row_dhote,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final getdhote currentdhote = getdhote.get(position);
        String price = String.valueOf(currentdhote.getPrix());
        holder.tv_price.setText(price+" Dt");
        holder.tv_nom.setText(currentdhote.getNom());
        holder.tv_emplacement.setText(currentdhote.getEmplacement());
        Glide.with(context).load(currentdhote.getImage()).apply(option).into(holder.tv_photo);

        // holder.tv_plat_name.setText(currentAnim.getnom_dhote());
        // holder.tv_unit.setText("DT");
//        Glide.with(context).load(currentAnim.getphoto_plat()).apply(option).into(holder.tv_photo_plat);
        holder.linearLayoutdhote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DhoteDetails.class);
                i.putExtra("nom",currentdhote.getNom());
                i.putExtra("prix",currentdhote.getPrix());
                i.putExtra("emp",currentdhote.getEmplacement());
                i.putExtra("lati",currentdhote.getLati());
                i.putExtra("longi",currentdhote.getLongi());
                i.putExtra("image",currentdhote.getImage());
                i.putExtra("id",currentdhote.getIddhote()+"");


                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);


            }
        });

    }

    @Override
    public int getItemCount() {

        return getdhote.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_price,tv_nom,tv_emplacement,tv_kilometrage;
        ImageView tv_photo;
        LinearLayout linearLayoutdhote;

        public MyViewHolder(View itemView) {
            super(itemView);

            linearLayoutdhote = itemView.findViewById(R.id.container_dhote);
            tv_price = itemView.findViewById(R.id.prix);
            tv_emplacement = itemView.findViewById(R.id.emplacement);
            tv_nom = itemView.findViewById(R.id.nom);
            tv_photo = itemView.findViewById(R.id.photo_dhote);
        }
    }
}

