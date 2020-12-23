package com.example.quanlyvatracuubhxh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.quanlyvatracuubhxh.databinding.ActivityQuanlyBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class QuanlyActivity extends AppCompatActivity {
    ActivityQuanlyBinding binding;
    Adapter adapter;
    Calendar cal;
    Date dateFinish;
    QuanLyBHXH item;
    List<QuanLyBHXH> items = new ArrayList<>();
    SQL sqLite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_quanly);
        sqLite= new SQL(getBaseContext());
        initSpinner();
        initData();
        initListview();
        binding.btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

    }

    private void initListview() {
        items = new ArrayList<>();
        items.addAll(sqLite.getAll());
        adapter = new Adapter(items);
        binding.lv1.setAdapter(adapter);
        binding.lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = new QuanLyBHXH(items.get(i));
                binding.edmabh.setText(item.getMaBH());
                binding.edname.setText(item.getTenKH());
                if (item.getSex().equalsIgnoreCase(QuanLyBHXH.NAM)) {
                    binding.spinner.setSelection(0);
                } else {
                    binding.spinner.setSelection(1);
               }
                binding.tvngaysinh.setText(item.getNgaysinh());
                binding.edaddress.setText(item.getDiachi());
                binding.ednoicap.setText(item.getNoicap());
            }
        });
    }

    public void initData() {
        item = new QuanLyBHXH("BH01","Đỗ Ngọc Sơn","13/10/1998","Nam","Tuyên Quang","Yên Phú- Hàm Yên- Tuyên Quang");
        if (!sqLite.ifExistsItem(item.getMaBH())) {
            sqLite.insert(item);
        }
//        items.add(item);
//        for (QuanLyBHXH i : items) {
//            if (!sqLite.ifExistsItem(i.getMaBH())) {
//                sqLite.insert(i);
//            }
//        }
        items.clear();
    }
    public void initSpinner() {
        final List<String> arr = new ArrayList<>();

        arr.add(QuanLyBHXH.NAM);
        arr.add(QuanLyBHXH.NU);


        ArrayAdapter arrayAdapter = new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, arr);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        binding.spinner.setAdapter(arrayAdapter);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String clas = arr.get(i);
//                items.clear();
//                if (clas.equalsIgnoreCase(Item.cl0)) {
//                    items.addAll(sqLite.getAll());
//                    adapter.notifyDataSetChanged();
//                } else if (clas.equalsIgnoreCase(Item.cl1)) {
//                    select(Item.cl1);
//                } else if (clas.equalsIgnoreCase(Item.cl2)) {
//                    select(Item.cl2);
//                } else if (clas.equalsIgnoreCase(Item.cl3)) {
//                    select(Item.cl3);
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * Sự kiện khi chọn 1 lớp trong spinner
     *
//     * @param lop
     */
//    public void select(String lop) {
//        for (Item i : sqLite.getAll()) {
//            if (i.getLop().equalsIgnoreCase(lop)) {
//                items.add(i);
//            }
//        }
//        adapter.notifyDataSetChanged();
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itThem:
                Them();
                break;
            case R.id.itSua:
                Sua();
                break;
            case R.id.itXoa:
                Xoa();
                break;
            case  R.id.itQuaylai:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Xoa() {
        getItem();
        sqLite.delete(item.getMaBH());
        items.clear();
        items.addAll(sqLite.getAll());
        adapter.notifyDataSetChanged();
    }

    private void Sua() {
        getItem();
        sqLite.update(item);
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getMaBH().equalsIgnoreCase(item.getMaBH())) {
                items.set(i, item);
                break;
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void Them() {
        getItem();
        items.add(item);
        sqLite.insert(item);
        adapter.notifyDataSetChanged();
    }

    private void getItem() {
        if (binding.edmabh.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(getBaseContext(), "Bạn chưa nhập mã", Toast.LENGTH_SHORT).show();
            binding.edmabh.requestFocus();
        } else if (binding.edname.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(getBaseContext(), "Bạn chưa nhập tên", Toast.LENGTH_SHORT).show();
            binding.edname.requestFocus();
        } else if (binding.tvngaysinh.getText().toString().equalsIgnoreCase("")) {
                Toast.makeText(getBaseContext(), "Bạn chưa nhập ngày sinh", Toast.LENGTH_SHORT).show();
                binding.tvngaysinh.requestFocus();
        }
        else {
            String ma = binding.edmabh.getText().toString();
            String ten = binding.edname.getText().toString();
            String ngaysinh = (binding.tvngaysinh.getText().toString());
            String gt = binding.spinner.getSelectedItem().toString();
            String diachi=binding.edaddress.getText().toString();
            String noicap=binding.ednoicap.getText().toString();
            item = new QuanLyBHXH(ma, ten, ngaysinh, gt, diachi, noicap);
        }
    }

    public void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener callback =
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker,
                                          int i, int i1, int i2) {
                        binding.tvngaysinh.setText(i2 + "/" + (i1 + 1) + "/" + i);
                        cal = Calendar.getInstance();
                        cal.set(i, (i1 + 1), i2);
                        dateFinish = cal.getTime();
                    }
                };
        DatePickerDialog pic = new DatePickerDialog(
                QuanlyActivity.this, callback,
                2020, 11, 10);
        pic.setTitle("my datetime picker");
        pic.show();
    }
}