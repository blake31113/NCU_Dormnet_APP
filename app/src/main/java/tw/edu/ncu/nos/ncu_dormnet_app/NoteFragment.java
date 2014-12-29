package tw.edu.ncu.nos.ncu_dormnet_app;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewAnimator;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import tw.edu.ncu.nos.ncu_dormnet_app.R;

/**
 * Created by Blake on 14/12/25.
 */
public class NoteFragment extends Fragment
{

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static ListView listView;
    private static final String dormnetNoteURL="https://uncia.cc.ncu.edu.tw/dormnet/index.php?section=note";
    private static final String unciaURL="https://uncia.cc.ncu.edu.tw";
    private static Context fragment;
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static NoteFragment newInstance()
    {
        NoteFragment fragment = new NoteFragment();
        return fragment;
    }

    public NoteFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.note_fragment, container, false);

        Task task=new Task();
        task.execute(dormnetNoteURL);

        listView=(ListView)rootView.findViewById(R.id.notelistview);
        fragment=getActivity();
        return rootView;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        ((Main) activity).onSectionAttached(0);
    }

    private static class Task extends AsyncTask<String,Void,Document>
    {
        @Override
        protected Document doInBackground(String... params)
        {
            //
            org.jsoup.Connection connection= Jsoup.connect(dormnetNoteURL);
            try
            {
                return connection.get();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Document document) {
            Element element=document.body();
            element=element.select("td[rowspan=\"2\"]").first();
            Elements elements=element.select("tr[bgcolor=\"#ffffaa\"]");
            ArrayList<NoteListElement> noteListElements=new ArrayList<NoteListElement>();

//            ArrayList<String> time_list=new ArrayList<String>();
//            ArrayList<String> user_list=new ArrayList<String>();
//            ArrayList<String> context_list=new ArrayList<String>();
//            ArrayList<String> href_list=new ArrayList<String>();
            for(Element e:elements)
            {
                NoteListElement nle=new NoteListElement();
                Elements trs=e.select("td[nowrap]");
                nle.set_date(trs.get(0).text());
                nle.set_user(trs.get(1).text());
                nle.set_title(trs.get(2).text());
                Element href=trs.get(2).select("a[href]").first();
                nle.set_href(href.attr("href"));
//                time_list.add(trs.get(0).text());
//                user_list.add(trs.get(1).text());
//                context_list.add(trs.get(2).text());

//                href_list.add();
                noteListElements.add(nle);
            }
//            textView.setText(href_list.toString());
            NoteListAdapter adapter=new NoteListAdapter(fragment,noteListElements);
            listView.setAdapter(adapter);

        }
    }
}