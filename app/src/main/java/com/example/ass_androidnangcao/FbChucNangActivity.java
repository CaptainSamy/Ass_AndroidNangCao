package com.example.ass_androidnangcao;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class FbChucNangActivity extends AppCompatActivity {
    EditText edtTitle, edtScription, edtUrl;
    Button btnSharelink, btnShareanh, btnSharevideo, btnChonvideo;
    ImageView imageView;
    VideoView videoView;
    ShareDialog shareDialog;
    ShareLinkContent shareLinkContent;
    public static int Select_Image = 1;
    public static int Pick_Video = 2;
    Bitmap bitmap;
    Uri selectVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb_chuc_nang);
        Anhxa();
        shareDialog = new ShareDialog(FbChucNangActivity.this);

        //share link
        btnSharelink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (ShareDialog.canShow(ShareLinkContent.class)){
                     shareLinkContent = new ShareLinkContent.Builder()
                             .setContentTitle(edtTitle.getText().toString())
                             .setContentDescription(edtScription.getText().toString())
                             .setContentUrl(Uri.parse(edtUrl.getText().toString()))
                             .build();
                 }
                 shareDialog.show(shareLinkContent);
            }
        });

        //chon anh
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, Select_Image);
            }
        });

        //share anh
        btnShareanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(bitmap)
                        .build();
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();
                shareDialog.show(content);
            }
        });

        //chon video
        btnChonvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("video/*");
                startActivityForResult(intent, Pick_Video);
            }
        });

        //share video
        btnSharevideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareVideo shareVideo = null;
                shareVideo = new ShareVideo.Builder()
                        .setLocalUrl(selectVideo)
                        .build();
                ShareVideoContent content = new ShareVideoContent.Builder()
                        .setVideo(shareVideo)
                        .build();
                shareDialog.show(content);
                videoView.stopPlayback();
            }
        });

    }

    private void Anhxa() {
        edtTitle = findViewById(R.id.edittexttitle);
        edtScription = findViewById(R.id.edittextscription);
        edtUrl = findViewById(R.id.edittexturl);
        btnSharelink = findViewById(R.id.buttonsharelink);
        btnShareanh = findViewById(R.id.buttonshareanh);
        btnSharevideo = findViewById(R.id.buttonShareVideo);
        btnChonvideo = findViewById(R.id.buttonChonVideo);
        imageView = findViewById(R.id.imghinh);
        videoView = findViewById(R.id.video);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Select_Image && resultCode == RESULT_OK){
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == Pick_Video && resultCode == RESULT_OK){
            selectVideo = data.getData();
            videoView.setVideoURI(selectVideo);
            videoView.start();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
