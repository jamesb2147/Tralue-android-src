package com.embaucha.tralue;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NewListAdapter extends BaseAdapter {
	List list;
	Context context;
	
	public NewListAdapter(Context context, List<CardContainer[]> list) {
		super();
		this.list = list;
		this.context = context;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}
	
	public CardContainer[] getItemAtPosition(int position) {
		return (CardContainer[]) list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		 // inflate the layout for each item of listView
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.new_list_view_item, null);
        
        //grab card array
        CardContainer card_array[] = (CardContainer[]) list.get(position);
        
        //grab textview fields
        TextView tv1 = (TextView)view.findViewById(R.id.card_text);
    	TextView tv2 = (TextView)view.findViewById(R.id.card_text_2);
    	TextView tv3 = (TextView)view.findViewById(R.id.card_text_3);
    	TextView tvAlt = (TextView)view.findViewById(R.id.card_2_text);
    	TextView tvPlusSign = (TextView)view.findViewById(R.id.plus_sign);
    	TextView tvEqualsSign = (TextView)view.findViewById(R.id.equals_sign);
        
        //populate card_1 data
        if (card_array.length > 0) {
        	tv1.setText(card_array[0].getName());
        	tv2.setText(Integer.toString((int)card_array[0].getPercentage()));
        	tv3.setText(card_array[0].getAward_program());
        }
        //if card_2 != null, populate card_2 data
        if (card_array.length > 1) {
        	tvAlt.setText(card_array[1].getName());
        	tv2.setText(Integer.toString((int)(card_array[0].getPercentage() + card_array[1].getPercentage())));
        	
        	//set view elements to visible
        	tvPlusSign.setVisibility(View.VISIBLE);
        	tvEqualsSign.setVisibility(View.VISIBLE);
        	tvAlt.setVisibility(View.VISIBLE);
        }
        

        // move the cursor to required position 
//        cursor.moveToPosition(position);
        
        // fetch the sender number and sms body from cursor
//        String senderNumber=cursor.getString(cursor.getColumnIndex("address"));
//        String smsBody=cursor.getString(cursor.getColumnIndex("body"));
       
        // get the reference of textViews
//        TextView textViewConatctNumber=(TextView)view.findViewById(R.id.textViewSMSSender);
//        TextView textViewSMSBody=(TextView)view.findViewById(R.id.textViewMessageBody);
        
        // Set the Sender number and smsBody to respective TextViews 
//        textViewConatctNumber.setText(senderNumber);
//        textViewSMSBody.setText(smsBody);
        

        return view;
	}
}