package tw.edu.ncu.nos.ncu_dormnet_app;

/**
 * Created by Blake on 14/12/30.
 */
public class NoteListElement
{
    private String _title;
    private String _date;
    private String _user;
    private String _href;
    public void set_title(String input)
    {
        _title=input;
    }
    public void set_date(String input)
    {
        _date=input;
    }
    public void set_user(String input)
    {
        _user=input;
    }
    public void set_href(String input)
    {
        _href=input;
    }
    public String get_title()
    {
        return _title;
    }
    public String get_date()
    {
        return _date;
    }
    public String get_user()
    {
        return _user;
    }
    public String get_href()
    {
        return _href;
    }
}
