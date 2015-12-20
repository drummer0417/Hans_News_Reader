package nl.androidappfactory.hackernewsreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListNewsItems extends AppCompatActivity {

//
//    news.ycombinator.com\
//    check: https://github.com/HackerNews/API
//            1.	Create main activity: List with articles and refresh button/menuitem
//            2.	Create article activity with article from site in html
//            3.	In Main
//            1)	If db is empty or onRefresh do
//                    1)	Get list with article id from news.ycombinator.com
//                    2)	Get description (and url)  from each article
//                    3)	Store in db
//            2)	Init main with list of articles
//            3)	On select: display article content on art activity (html)
//

    private DbHelper dbHelper;
    ListView lvNewsItems;
    List<NewsItem> newsItems = new ArrayList<NewsItem>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news_items);

        lvNewsItems = (ListView) findViewById(R.id.lvNewsItems);

        dbHelper = DbHelper.getInstance(this);

        generateTestData(); // TODO: remove after reading from news site

        newsItems = dbHelper.getAllNewsItems();

        ArrayAdapter<NewsItem> adapter = new ArrayAdapter<NewsItem>(this, android.R.layout.simple_list_item_1, newsItems);
        lvNewsItems.setAdapter(adapter);
    }

    public void refresh(){

        dbHelper.deleteAllNewsItems();

    }

    private void generateTestData() {
        dbHelper.insertItem("Voetbal nieuws",  "http://news.voetbal.nl");
        dbHelper.insertItem("Voorpagina",  "http://news.voetbal.nl");
        dbHelper.insertItem("Techniek",  "http://news.voetbal.nl");
        dbHelper.insertItem("Binnenlands nieuws",  "http://news.voetbal.nl");
        dbHelper.insertItem("Buitenlands nieuws",  "http://news.voetbal.nl");
        dbHelper.insertItem("Newitem 6 vanallles", "http://news.voetbal.nl");
    }


}
