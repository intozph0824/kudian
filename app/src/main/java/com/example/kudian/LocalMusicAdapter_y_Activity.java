package com.example.kudian;


import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.List;

class LocalMusicAdapterActivity extends RecyclerView.Adapter<LocalMusicAdapterActivity.LocalMuxicViewHolder> {
    Context context;
    List<LocalMusicBean_y_Activity> mDatas;

    OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener{
        public void OnItemClick(View view,int position);
    }

    public LocalMusicAdapterActivity(Context context, List<LocalMusicBean_y_Activity> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public LocalMuxicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item_local_music,parent,false);
        LocalMuxicViewHolder holder = new LocalMuxicViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LocalMuxicViewHolder holder, final int position) {
        LocalMusicBean_y_Activity musicBean_y=mDatas.get(position);
        holder.idTv.setText(musicBean_y.getId());
        holder.songTv.setText(musicBean_y.getSong());
        holder.singerTv.setText(musicBean_y.getSinger());
        holder.albumTv.setText(musicBean_y.getAlbum());


        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onItemClickListener.OnItemClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {

        return mDatas.size();
    }

    class LocalMuxicViewHolder extends RecyclerView.ViewHolder{
        TextView idTv,songTv,singerTv,albumTv,timeTv;
        public LocalMuxicViewHolder(@NonNull View itemView) {
            super(itemView);
            idTv = itemView.findViewById(R.id.item_local_music_num);
            songTv = itemView.findViewById(R.id.item_local_music_song);
            singerTv = itemView.findViewById(R.id.item_local_music_singer);
            albumTv = itemView.findViewById(R.id.item_local_music_album);
            albumTv = itemView.findViewById(R.id.item_local_music_ddurtion);

        }
    }
}

