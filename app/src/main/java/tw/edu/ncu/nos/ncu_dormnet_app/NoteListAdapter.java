package tw.edu.ncu.nos.ncu_dormnet_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Blake on 14/12/30.
 */
public class NoteListAdapter extends BaseAdapter
{
    private LayoutInflater inflater;
    private List<NoteListElement> listElement;
    public NoteListAdapter(Context context, List<NoteListElement> listElement)
    {
        inflater = LayoutInflater.from(context);
        this.listElement = listElement;
    }
    @Override
    public int getCount()
    {
        return listElement.size();
    }

    @Override
    public Object getItem(int position)
    {
        return listElement.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view;
        ViewHolder holder;
        if(convertView == null)
        {
            view = inflater.inflate(R.layout.note_list, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView)view.findViewById(R.id.notetitle);
            holder.user = (TextView)view.findViewById(R.id.noteuser);
            holder.date = (TextView)view.findViewById(R.id.notedate);
            view.setTag(holder);
        }
        else
        {
            view = convertView;
            holder = (ViewHolder)view.getTag();
        }

        NoteListElement le = listElement.get(position);
        holder.title.setText(le.get_title());
        holder.user.setText(le.get_user());
        holder.date.setText(le.get_date());

        return view;
    }

    private class ViewHolder
    {
        public TextView title,user,date;
    }
}
