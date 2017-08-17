package julius.barde.com.pickme;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

    }

    Uri myUri;
    private static final int SELECT_PHOTO = 25;
    ImageView imageView;
    Button shareBtn;

    private void findViews() {
        imageView = (ImageView) findViewById(R.id.choosenPhoto_img);
        shareBtn = (Button) findViewById(R.id.shareBtn);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null && data.getData() != null) {

            myUri = data.getData();


            imageView.setImageURI(myUri);


        }
    }

    public void SharePhoto(View view) {
        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("image/*");
        myIntent.setData(myUri);
        myIntent.putExtra(Intent.EXTRA_STREAM, myUri);

        startActivity(Intent.createChooser(myIntent, getString(R.string.app_name)));
    }

    public void ChoosePhoto(View view) {
        Intent myIntent = new Intent(Intent.ACTION_PICK);
        myIntent.setType("image/*");
        startActivityForResult(myIntent, SELECT_PHOTO);
        shareBtn.setVisibility(View.VISIBLE);

    }
}


