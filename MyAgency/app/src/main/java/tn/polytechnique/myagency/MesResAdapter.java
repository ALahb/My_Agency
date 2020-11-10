package tn.polytechnique.myagency;



import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;


public class MesResAdapter extends RecyclerView.Adapter<MesResAdapter.MyViewHolder>{
    private LayoutInflater inflater;
    private Context context;
    private List<MesRes> mesResList;

    public MesResAdapter(Context context, List<MesRes> mesResList)
    {

        this.mesResList = mesResList;
        this.inflater = LayoutInflater.from(context);
        this.context = context;



    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = inflater.inflate(R.layout.mesres_row,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MesRes res = mesResList.get(position);

        holder.date_fin.setText("Date debut : "+res.getDate_fin());
        holder.date_deb.setText("Date fin : " + res.getDate_debut());
        holder.emplacement.setText("Emplacement : " + res.getEmplacement());


    }

    @Override
    public int getItemCount() {

        return mesResList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView date_deb,date_fin,emplacement;


        public MyViewHolder(View itemView) {
            super(itemView);


            date_deb = itemView.findViewById(R.id.date_debutme);
            date_fin = itemView.findViewById(R.id.date_finme);
            emplacement = itemView.findViewById(R.id.nom_me);

        }
    }
}

