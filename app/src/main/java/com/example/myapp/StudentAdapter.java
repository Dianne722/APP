package com.example.myapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends BaseAdapter implements View.OnClickListener {

    private List<Student> studentDatas;

    private Context studentContext;

    public StudentAdapter( Context studentContext, List<Student> studentDatas) {
        this.studentDatas = studentDatas;
        this.studentContext = studentContext;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public int getCount() {
        return studentDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return studentDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View studentView, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (studentView == null) {
            studentView = View.inflate(studentContext, android.R.layout.simple_list_item_2, null);

            viewHolder = new ViewHolder();
            viewHolder.text1 = studentView.findViewById(android.R.id.text1);
            viewHolder.text2 = studentView.findViewById(android.R.id.text2);

            studentView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) studentView.getTag();
        }

        // 设置第一行字体颜色和大小
        TextView text1 = studentView.findViewById(android.R.id.text1);
        text1.setTextColor(Color.BLACK);
        text1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        // 第一行字体加粗
        TextPaint tp = text1.getPaint();
        tp.setFakeBoldText(true);

        // 设置第二行字体颜色和大小
        TextView text2 = studentView.findViewById(android.R.id.text2);
        text2.setTextColor(Color.BLUE);
        text2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);

        TextView tv_name = studentView.findViewById(android.R.id.text1);
        TextView tv_major = studentView.findViewById(android.R.id.text2);

        //将数据源的一项数据和item的子元素绑定
        Student s= (Student) getItem(i);
        tv_name.setText(s.getName());
        tv_major.setText(s.getMajor());

        return studentView;
    }

    static class ViewHolder {
        TextView text1;
        TextView text2;
    }
}
