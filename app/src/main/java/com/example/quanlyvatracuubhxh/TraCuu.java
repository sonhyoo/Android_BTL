package com.example.quanlyvatracuubhxh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.quanlyvatracuubhxh.databinding.ActivityTraCuuBinding;

import java.util.ArrayList;
import java.util.List;

public class TraCuu extends AppCompatActivity {
    ActivityTraCuuBinding binding;
    Adapter adapter;
    SQL sql;
    List<QuanLyBHXH> searchs = new ArrayList<>();

    List<QuanLyBHXH> listql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tra_cuu);
        sql = new SQL(getBaseContext());
        listql = new ArrayList<>();
        listql.addAll(sql.getAll());
        adapter = new Adapter(listql);
        binding.lv1.setAdapter(adapter);

//                binding.search.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                        searchs= adapter.search(s.toString(),s.toString());
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable s) {
//
//                    }
//                });

        binding.btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gt = QuanLyBHXH.TATCA;
//                listql.clear();
//                listql.addAll(sql.getAll());
                if (R.id.rdoNu == binding.rdogr.getCheckedRadioButtonId()) {
                    gt = QuanLyBHXH.NU;
                } else if (R.id.rdoNam == binding.rdogr.getCheckedRadioButtonId()) {
                    gt = QuanLyBHXH.NAM;
                } else
                    gt = QuanLyBHXH.TATCA;

                adapter.search(binding.search.getText().toString(), gt);
            }
        });

    }
}