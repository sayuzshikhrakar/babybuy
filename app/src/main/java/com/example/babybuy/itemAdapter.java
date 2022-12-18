//package com.example.babybuy;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.babybuy.Model.ItemInfo;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//
//public class itemAdapter extends FirebaseRecyclerAdapter<ItemInfo, itemAdapter.itemViewHolder>{
//    public itemAdapter(
//            @NonNull FirebaseRecyclerOptions<ItemInfo> options)
//    {
//        super(options);
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull itemAdapter.itemViewHolder holder, int position, @NonNull ItemInfo model) {
//        // Add firstname from model class (here
//        // "person.class")to appropriate view in Card
//        // view (here "person.xml")
//        holder.itemName.setText(model.getItemName());
//
//        // Add lastname from model class (here
//        // "person.class")to appropriate view in Card
//        // view (here "person.xml")
//        holder.Description.setText(model.getDescription());
//
//    }
//
//    @NonNull
//    @Override
//    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
//        return new itemAdapter.itemViewHolder(view);
//    };
//
//
//    public class itemViewHolder extends RecyclerView.ViewHolder {
//        TextView itemName, Description;
//
//        public itemViewHolder(@NonNull View itemView) {
//            super(itemView);
////
//            itemName = itemView.findViewById(R.id.itemName);
//            Description = itemView.findViewById(R.id.description);
//        }
//    }
//}