package com.example.jobinterviewpractical.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jobinterviewpractical.R;
import com.example.jobinterviewpractical.activity.adaptor.RecyclerviewAdaptor;
import com.example.jobinterviewpractical.activity.model.DetailBean;
import com.example.jobinterviewpractical.activity.retrofit.APIService;
import com.example.jobinterviewpractical.activity.util.Utility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerlistview;
    private Context mcontext;
    private ArrayList<DetailBean.search> movielist = new ArrayList<>();
    private Toolbar toolbarnew;
    private RecyclerviewAdaptor recyclerviewAdaptor;
    private LinearLayoutManager linearLayoutManager;
    private EditText editsearch;
    private String serachtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mcontext = this;
        initUI();
        callAPI();
        setClickListner();
    }


    private void callAPI() {
        if (Utility.getInstance().isNetworkAvailable(mcontext)) {
            APIService.getInstance().getBaseUrl()
                    .moviedata("batman", "8e93f9f1")
                    .enqueue(new Callback<DetailBean>() {
                        @Override
                        public void onResponse(Call<DetailBean> call, Response<DetailBean> response) {

                            if (response.isSuccessful()) {
                                if (response.body().getSearch() != null) {
                                    movielist.addAll(response.body().getSearch());
                                    setData(movielist);
                                    Log.d("Movie list is", "list is+" + movielist.size());
                                }

                            } else {
                                Toast.makeText(mcontext, "There is some problem in server", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<DetailBean> call, Throwable t) {
                            Log.d("message", "API msg" + t.getMessage());
                            Toast.makeText(mcontext, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


        } else {
            Toast.makeText(mcontext, "Please check the net connection!", Toast.LENGTH_SHORT).show();
        }

    }

    private void setClickListner() {
        editsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                serachtext = editsearch.getText().toString();

                recyclerviewAdaptor.getFilter().filter(editsearch.getText().toString());
//                else if (!switch_available.isChecked())
//                    mapFragment.filterResult(et_searchview.getText().toString());

//                if (!Utility.getInstance().isBlankString(serachtext)) {
//                    img_clear.setVisibility(View.VISIBLE);
//                } else {
//                    img_clear.setVisibility(View.GONE);
//                    searcherror_msg.setVisibility(View.GONE);
//                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }


    private void setData(ArrayList<DetailBean.search> movielist) {
        recyclerviewAdaptor = new RecyclerviewAdaptor(mcontext, movielist);
        linearLayoutManager = new LinearLayoutManager(mcontext);
        recyclerlistview.setAdapter(recyclerviewAdaptor);
        recyclerlistview.setLayoutManager(linearLayoutManager);

    }

    private void initUI() {
        recyclerlistview = findViewById(R.id.recyclerlistview);
        toolbarnew = findViewById(R.id.toolbarnew);
        editsearch = findViewById(R.id.editsearch);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
//        setSupportActionBar(toolbarnew);
        toolbarnew.setTitle("New Release Moview");
    }
}