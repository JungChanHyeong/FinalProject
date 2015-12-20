package chanh.finalproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

// Custom ListView Adapter
public class MyAdapter extends BaseAdapter {

    Activity activity;

    private Context context;
    private ArrayList<MyData> arrData;
    private LayoutInflater inflater;

    public MyAdapter(Context c, ArrayList<MyData> arr) {
        this.context = c;
        this.arrData = arr;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return arrData.size();
    }

    public Object getItem(int position) {
        return arrData.get(position).getSmallCategory();
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        TextView listItemCategory = (TextView) convertView.findViewById(R.id.listItemCategory);
        listItemCategory.setText(arrData.get(position).getSmallCategory());

        TextView listItemStory = (TextView) convertView.findViewById(R.id.listItemStory);
        listItemStory.setText(arrData.get(position).getStory());

        TextView listItemDate = (TextView) convertView.findViewById(R.id.listItemDate);
        listItemDate.setText(arrData.get(position).getDate());

        TextView listItemTime = (TextView) convertView.findViewById(R.id.listItemTime);
        listItemTime.setText(arrData.get(position).getTime());

        TextView listItemPosition = (TextView) convertView.findViewById(R.id.listItemPosition);
        listItemPosition.setText(arrData.get(position).getPosition());

        Button listItemBtn = (Button) convertView.findViewById(R.id.listItemBtn);
        listItemBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String str = arrData.get(position).getBigCategory();

//                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();

                if (str.equalsIgnoreCase("일상"))
                    ((LifeList) context).mapView(arrData.get(position).getPointX(), arrData.get(position).getPointY());
                else
                    ((IssueList) context).mapView(arrData.get(position).getPointX(), arrData.get(position).getPointY());
            }
        });

        return convertView;
    }
}