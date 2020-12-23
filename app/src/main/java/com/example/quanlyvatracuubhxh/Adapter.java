package com.example.quanlyvatracuubhxh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.quanlyvatracuubhxh.databinding.LvitemBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Adapter extends BaseAdapter {
    List<QuanLyBHXH> items;
    List<QuanLyBHXH> itemlist;
    LayoutInflater layout;

    public Adapter(List<QuanLyBHXH> items) {
        this.items = items;
        this.itemlist = new ArrayList<>();
        itemlist.addAll(items);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View result = convertView;
        LvitemBinding binding;
        if (result == null) {
            if (layout == null) {
                layout = (LayoutInflater) parent.getContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            binding = LvitemBinding.inflate(
                    layout, parent, false);
            result = binding.getRoot();
            result.setTag(binding);
        } else {
            binding = (LvitemBinding) result.getTag();
        }

        QuanLyBHXH item = items.get(position);
        binding.itemMaBHXH.setText(item.getMaBH());
        binding.itemTenKH.setText(String.valueOf(item.getTenKH()));
        binding.itemNgaysinh.setText(item.getNgaysinh());
        binding.itemSex.setText(item.getSex());
        binding.itemDiachi.setText(String.valueOf(item.getDiachi()));
        binding.itemNoicap.setText(item.getNoicap());

        return result;
    }

    public void search(String a, String gt) {
        a = a.toLowerCase(Locale.getDefault());
        items.clear();
        if (a.length() == 0 && gt == QuanLyBHXH.TATCA) {
            items.addAll(itemlist);
        } else if (a.length() == 0 && gt != QuanLyBHXH.TATCA) {
            for (QuanLyBHXH ql : itemlist) {
                if (ql.getSex().toLowerCase(Locale.getDefault()).contains(gt.toLowerCase())) {
                    items.add(ql);
                }
            }
        } else if (a.length() != 0 && gt == QuanLyBHXH.TATCA) {
            for (QuanLyBHXH ql : itemlist) {
                if (ql.getMaBH().toLowerCase(Locale.getDefault()).contains(a) || ql.getTenKH().toLowerCase(Locale.getDefault()).contains(a)) {
                    items.add(ql);
                }
            }
        } else {
            for (QuanLyBHXH ql : itemlist) {
                if (ql.getMaBH().toLowerCase(Locale.getDefault()).contains(a) || ql.getTenKH().toLowerCase(Locale.getDefault()).contains(a) && ql.getSex().toLowerCase(Locale.getDefault()).contains(gt.toLowerCase())) {
                    items.add(ql);
                }
            }
        }


        notifyDataSetChanged();
    }
//    public void searchgt(String a,List<QuanLyBHXH> b){
//    a=a.toLowerCase(Locale.getDefault());
//    items.clear();
//    if (a=="nam"){
//        for (QuanLyBHXH ql:b)
//            items.add(ql);
//    }
//    notifyDataSetChanged();
//    }
}
