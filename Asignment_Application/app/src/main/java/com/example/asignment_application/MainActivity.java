package com.example.asignment_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asignment_application.adapter.Sach_Adapter;
import com.example.asignment_application.adapter.SelecListener;
import com.example.asignment_application.apiService.Sach_ApiService;
import com.example.asignment_application.apiService.Sach_PostService;
import com.example.asignment_application.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    ArrayList<Sach> list = new ArrayList<>();
    Sach_Adapter adapter;
    FloatingActionButton fab;
    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rcv);
        fab = findViewById(R.id.fabSach);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


//        Sach sach = new Sach("1", "2", "3");
//        list.add(sach);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = -1;
                openDialog();

            }
        });


    }

    @Override
    protected void onResume() {
        getData();
        super.onResume();
    }

    public void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.8:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Sach_ApiService apiService = retrofit.create(Sach_ApiService.class);
        Call<ArrayList<Sach>> call = apiService.getBooks();
        call.enqueue(new Callback<ArrayList<Sach>>() {
            @Override
            public void onResponse(Call<ArrayList<Sach>> call, Response<ArrayList<Sach>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    Log.i("Sync", "Success");
                    Log.i("Sync", String.valueOf(list.size()));
                    adapter = new Sach_Adapter(MainActivity.this, list, new SelecListener() {
                        @Override
                        public void onItemClick(int position) {
                            a = position;
                            openDialog();

                        }
                    });
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.i("Sync", "Fail");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Sach>> call, Throwable t) {

            }
        });
    }

    private void openDialog() {
        Dialog dialog = new Dialog(this);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.setContentView(LayoutInflater.from(this).inflate(R.layout.dialog_addsach, null));

        EditText txtname = dialog.findViewById(R.id.dialog_sach_txtName);
        EditText txtprice = dialog.findViewById(R.id.dialog_sach_txtPrice);
        Button btnAdd = dialog.findViewById(R.id.dialog_sach_btnAdd);
        Button btnCancel = dialog.findViewById(R.id.dialog_sach_btnCancel);
        TextView title = dialog.findViewById(R.id.dialog_sach_tvTitle);

        if (a < 0) {
            title.setText("Thêm thông tin sách");
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String strName = txtname.getText().toString();
                    String strPrice = txtprice.getText().toString();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.1.8:3000/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    Sach_PostService postService = retrofit.create(Sach_PostService.class);
                    Call<ArrayList<Sach>> call = postService.postData(strName, strPrice);
                    call.enqueue(new Callback<ArrayList<Sach>>() {
                        @Override
                        public void onResponse(Call<ArrayList<Sach>> call, Response<ArrayList<Sach>> response) {

                        }

                        @Override
                        public void onFailure(Call<ArrayList<Sach>> call, Throwable t) {

                        }
                    });

                    dialog.dismiss();
                    getData();
                }
            });

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dialog.dismiss();
                }
            });
        } else {
            // SỬA
            title.setText("Thay đổi thông tin sách");
            btnAdd.setText("Sửa");
            btnCancel.setText("Xóa");
            Sach sach = list.get(a);
            txtname.setText(sach.getName());
            txtprice.setText(sach.getPrice());

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String strName = txtname.getText().toString();
                    String strPrice = txtprice.getText().toString();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.1.8:3000/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    Sach_ApiService apiService = retrofit.create(Sach_ApiService.class);
                    Call<ArrayList<Sach>> call = apiService.updateBooks(sach.getId(), strName, strPrice);
                    call.enqueue(new Callback<ArrayList<Sach>>() {
                        @Override
                        public void onResponse(Call<ArrayList<Sach>> call, Response<ArrayList<Sach>> response) {

                        }

                        @Override
                        public void onFailure(Call<ArrayList<Sach>> call, Throwable t) {

                        }
                    });
                    dialog.dismiss();
                    getData();
                }
            });

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.1.8:3000/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    Sach_ApiService apiService = retrofit.create(Sach_ApiService.class);
                    Call<ArrayList<Sach>> call = apiService.deleteBooks(sach.getId());
                    call.enqueue(new Callback<ArrayList<Sach>>() {
                        @Override
                        public void onResponse(Call<ArrayList<Sach>> call, Response<ArrayList<Sach>> response) {
                            Log.i("Delete", "xóa ok");
                        }

                        @Override
                        public void onFailure(Call<ArrayList<Sach>> call, Throwable t) {

                        }
                    });
                    dialog.dismiss();
                    getData();
                }
            });
        }
        dialog.show();
    }


}