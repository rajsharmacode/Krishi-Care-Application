package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class JivaduDeseasePage extends AppCompatActivity {

//
//    RecyclerView recview;
//    LivAdapters adpt1;
//
//    ArrayList<LivModel> list66;
//    DatabaseReference reference;
//    SearchView searchView;
//    ImageView caltback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jivadu_desease_page);

//        recview=findViewById(R.id.caltrecview);
//        searchView=findViewById(R.id.searchdata);
//        caltback=findViewById(R.id.caltback);
//        searchView.clearFocus();
//
//
//        list66=new ArrayList<>();
//        reference= FirebaseDatabase.getInstance().getReference();
//        recview.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
////        recview.setNestedScrollingEnabled(false);
//        adpt1=new LivAdapters(list66,getApplicationContext());
//        recview.setAdapter(adpt1);
//        reference.child("LeaveDesease").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                list66.clear();
//                for(DataSnapshot snapshot: dataSnapshot.getChildren())
//                {
//                    LivModel data=snapshot.getValue(LivModel.class);
//                    list66.add(data);
//                }
//                adpt1.notifyDataSetChanged();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//
//        });
//
//
//
//        caltback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                reference.child("LeaveDesease").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                        list66.clear();
//                        for(DataSnapshot snapshot: dataSnapshot.getChildren())
//                        {
//
//                            LivModel data=snapshot.getValue(LivModel.class);
//
////                    ;+String.valueOf(data)
////                            Toast.makeText(SerachItemPage.this, data.getItemname(), Toast.LENGTH_SHORT).show();
//                            if(data.getLivname().toLowerCase().contains(s))
//                            {
//                                list66.add(data);
//                            }
//
//                        }
//                        adpt1.notifyDataSetChanged();
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                        Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//
//                    }
//
//                });
//                return true;
//            }
//        });

    }
}