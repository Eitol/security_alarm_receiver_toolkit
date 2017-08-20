package instant.alarmreceptortoolkitapp.global.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import instant.alarmreceptortoolkitapp.R;
import us.feras.mdv.MarkdownView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        String resourcePath = getIntent().getStringExtra(InfoActivity.PROTOCOL_OR_RECEIVER_NAME);
        resourcePath = converToResource(resourcePath);
        MarkdownView markdownView = (MarkdownView) findViewById(R.id.markdownView);
//        markdownView.loadMarkdownFile("file://android_asset/"+resourcePath);
        markdownView.loadMarkdownFile("file:///android_asset/info/"+resourcePath);
    }

    private String converToResource(String protocolOrReceiverName){
        return protocolOrReceiverName.replace(' ','-')+".md";
    }

    /****** CONSTANTS ********/
    public static final String PROTOCOL_OR_RECEIVER_NAME = "PROTOCOL_OR_RECEIVER_NAME";
}
