package com.example.kidsgamenew;

import static android.view.DragEvent.ACTION_DROP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.DragAndDropPermissions;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;


//timer was from chatGPT

//https://stackoverflow.com/questions/58376131/drag-and-drop-imageview-into-a-container-for-verification

public class GameActivity extends AppCompatActivity {

    private TextView timerTextView;
    private Button startButton;
    private Button stopButton;
    private long startTime = 0L;
    private Handler handler = new Handler();
    ImageButton imgBtn;
    private Runnable timerRunnable;
    private int listSize;

    private GestureDetectorCompat mGestureDetector;
    ImageView imgViewClifford, imgViewSonic, imgViewSully, imgViewMinion, imgViewKermit, imgViewHulk,imgViewElmo, imgViewCookieMon, imgViewGengar, imgViewGreenLantern, imgViewWaluigi, imgViewDeadpool;
    float xDown = 0, xDown2 = 0,xDown3 = 0, xDown4 = 0,xDown5 = 0, xDown6 = 0, xDown7 = 0, xDown8 = 0,xDown9 = 0, xDown10 = 0, xDown11 = 0, xDown12 = 0;
    float yDown = 0,yDown2 = 0,yDown3 = 0,yDown4 = 0, yDown5 = 0, yDown6 = 0, yDown7 = 0, yDown8 = 0, yDown9 = 0, yDown10 = 0, yDown11 = 0, yDown12 = 0;

    ImageView targetImageView;

    TextView receiver_msg;

    private ArrayList<ImageView> imageViewList;
    private ArrayList<Integer> listRandNums;

    private String savedTime;

    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mGestureDetector = new GestureDetectorCompat(this,new GestureListener());

        receiver_msg = (TextView) findViewById(R.id.received_value_id);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");
        receiver_msg.setText(str);

        imgViewClifford = (ImageView) findViewById(R.id.imgViewClifford);
        imgViewClifford.setImageResource(R.drawable.clifford);

        imgViewSonic = (ImageView) findViewById(R.id.imgViewSonic);
        imgViewSonic.setImageResource(R.drawable.sonic);

        imgViewSully =(ImageView) findViewById(R.id.imgViewSully);
        imgViewSully.setImageResource(R.drawable.sully);

        imgViewMinion =(ImageView) findViewById(R.id.imgViewMinion);
        imgViewMinion.setImageResource(R.drawable.minion);

        imgViewKermit= (ImageView) findViewById(R.id.imgViewKermit);
        imgViewKermit.setImageResource(R.drawable.kermit);

        imgViewHulk=(ImageView) findViewById(R.id.imgViewHulk);
        imgViewHulk.setImageResource(R.drawable.hulk);

        imgViewElmo=(ImageView) findViewById(R.id.imgViewElmo);
        imgViewElmo.setImageResource(R.drawable.elmo);

        imgViewCookieMon=(ImageView) findViewById(R.id.imgViewCookieMon);
        imgViewCookieMon.setImageResource(R.drawable.cookiemonster);

        imgViewGengar=(ImageView) findViewById(R.id.imgViewGengar);
        imgViewGengar.setImageResource(R.drawable.gengar);

        imgViewGreenLantern=(ImageView) findViewById(R.id.imgViewGreenLantern);
        imgViewGreenLantern.setImageResource(R.drawable.greenlantern);

        imgViewWaluigi=(ImageView) findViewById(R.id.imgViewWaluigi);
        imgViewWaluigi.setImageResource(R.drawable.waluigi);

        imgViewDeadpool=(ImageView) findViewById(R.id.imgViewDeadpool);
        imgViewDeadpool.setImageResource(R.drawable.deadpool);



//
//        listRandNums = new ArrayList<>();
//        listSize = 12;
//
//        for (int i = 0; i < listSize; i++) {
//            listRandNums.add(i);
//        }

        imageViewList = new ArrayList<>();

        imageViewList.add(findViewById(R.id.imgViewClifford)); // Replace with your actual ImageView IDs
        imageViewList.add(findViewById(R.id.imgViewSonic));
        imageViewList.add(findViewById(R.id.imgViewSully));
        imageViewList.add(findViewById(R.id.imgViewMinion));
        imageViewList.add(findViewById(R.id.imgViewKermit));
        imageViewList.add(findViewById(R.id.imgViewHulk));
        imageViewList.add(findViewById(R.id.imgViewElmo));
        imageViewList.add(findViewById(R.id.imgViewCookieMon));
        imageViewList.add(findViewById(R.id.imgViewGengar));
        imageViewList.add(findViewById(R.id.imgViewGreenLantern));
        imageViewList.add(findViewById(R.id.imgViewWaluigi));
        imageViewList.add(findViewById(R.id.imgViewDeadpool));


        imgBtn =  (ImageButton) findViewById(R.id.imageButton);
        imgBtn.setOnClickListener(view -> {
            Intent i = new Intent(this, TimeActivity.class);

            // Store the number in a Bundle
            Bundle bundle = new Bundle();
            bundle.putString("NUMBER_KEY", timeArray.get(timeArray.size() - 1));
            bundle.putString("TIME", savedTime);
            i.putExtras(bundle);
            startActivity(i);
        });


        Intent i2 = getIntent();
        if (i2 != null && i2.getExtras() != null) {
            Bundle bundle = i2.getExtras();
            String receivedNumber = bundle.getString("TIME_KEY");

            savedTime = receivedNumber;
        }

        imgViewClifford.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                onTouchEvent(event);
                System.out.println(event);
                switch (event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        xDown = event.getX();
                        yDown = event.getY();


                        break;
                    case MotionEvent.ACTION_MOVE:
                        float movedX,movedY;
                        movedX = event.getX();
                        movedY = event.getY();


                        float distanceX = movedX - xDown;
                        float distanceY = movedY - yDown;

                        imgViewClifford.setX(imgViewClifford.getX()+distanceX);
                        imgViewClifford.setY(imgViewClifford.getY()+distanceY);

                        break;

                }



                return true;
            }
        });

        imgViewSonic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                onTouchEvent(event);
                System.out.println(event);
                switch (event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        xDown2 = event.getX();
                        yDown2 = event.getY();


                        break;
                    case MotionEvent.ACTION_MOVE:
                        float movedX,movedY;
                        movedX = event.getX();
                        movedY = event.getY();


                        float distanceX = movedX - xDown2;
                        float distanceY = movedY - yDown2;

                        imgViewSonic.setX(imgViewSonic.getX()+distanceX);
                        imgViewSonic.setY(imgViewSonic.getY()+distanceY);

                        break;

                }



                return true;
            }
        });

        imgViewSully.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                onTouchEvent(event);
                System.out.println(event);
                switch (event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        xDown3 = event.getX();
                        yDown3 = event.getY();


                        break;
                    case MotionEvent.ACTION_MOVE:
                        float movedX,movedY;
                        movedX = event.getX();
                        movedY = event.getY();


                        float distanceX = movedX - xDown3;
                        float distanceY = movedY - yDown3;

                        imgViewSully.setX(imgViewSully.getX()+distanceX);
                        imgViewSully.setY(imgViewSully.getY()+distanceY);

                        break;

                }



                return true;
            }
        });

        imgViewMinion.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                onTouchEvent(event);
                System.out.println(event);
                switch (event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        xDown4 = event.getX();
                        yDown4 = event.getY();


                        break;
                    case MotionEvent.ACTION_MOVE:
                        float movedX,movedY;
                        movedX = event.getX();
                        movedY = event.getY();


                        float distanceX = movedX - xDown4;
                        float distanceY = movedY - yDown4;

                        imgViewMinion.setX(imgViewMinion.getX()+distanceX);
                        imgViewMinion.setY(imgViewMinion.getY()+distanceY);

                        break;

                }

                return true;
            }
        });

        imgViewKermit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                onTouchEvent(event);
                System.out.println(event);
                switch (event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        xDown5 = event.getX();
                        yDown5 = event.getY();


                        break;
                    case MotionEvent.ACTION_MOVE:
                        float movedX,movedY;
                        movedX = event.getX();
                        movedY = event.getY();


                        float distanceX = movedX - xDown5;
                        float distanceY = movedY - yDown5;

                        imgViewKermit.setX(imgViewKermit.getX()+distanceX);
                        imgViewKermit.setY(imgViewKermit.getY()+distanceY);

                        break;

                }



                return true;
            }
        });


        imgViewHulk.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                onTouchEvent(event);
                System.out.println(event);
                switch (event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        xDown6 = event.getX();
                        yDown6 = event.getY();


                        break;
                    case MotionEvent.ACTION_MOVE:
                        float movedX,movedY;
                        movedX = event.getX();
                        movedY = event.getY();


                        float distanceX = movedX - xDown6;
                        float distanceY = movedY - yDown6;

                        imgViewHulk.setX(imgViewHulk.getX()+distanceX);
                        imgViewHulk.setY(imgViewHulk.getY()+distanceY);

                        break;

                }



                return true;
            }
        });

        imgViewElmo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                onTouchEvent(event);
                System.out.println(event);
                switch (event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        xDown7 = event.getX();
                        yDown7 = event.getY();


                        break;
                    case MotionEvent.ACTION_MOVE:
                        float movedX,movedY;
                        movedX = event.getX();
                        movedY = event.getY();


                        float distanceX = movedX - xDown7;
                        float distanceY = movedY - yDown7;

                        imgViewElmo.setX(imgViewElmo.getX()+distanceX);
                        imgViewElmo.setY(imgViewElmo.getY()+distanceY);

                        break;

                }



                return true;
            }
        });

        imgViewCookieMon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                onTouchEvent(event);
                System.out.println(event);
                switch (event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        xDown8 = event.getX();
                        yDown8 = event.getY();


                        break;
                    case MotionEvent.ACTION_MOVE:
                        float movedX,movedY;
                        movedX = event.getX();
                        movedY = event.getY();


                        float distanceX = movedX - xDown8;
                        float distanceY = movedY - yDown8;

                        imgViewCookieMon.setX(imgViewCookieMon.getX()+distanceX);
                        imgViewCookieMon.setY(imgViewCookieMon.getY()+distanceY);

                        break;

                }



                return true;
            }
        });

        imgViewGengar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                onTouchEvent(event);
                System.out.println(event);
                switch (event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        xDown9 = event.getX();
                        yDown9 = event.getY();


                        break;
                    case MotionEvent.ACTION_MOVE:
                        float movedX,movedY;
                        movedX = event.getX();
                        movedY = event.getY();


                        float distanceX = movedX - xDown9;
                        float distanceY = movedY - yDown9;

                        imgViewGengar.setX(imgViewGengar.getX()+distanceX);
                        imgViewGengar.setY(imgViewGengar.getY()+distanceY);

                        break;

                }



                return true;
            }
        });

        imgViewGreenLantern.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                onTouchEvent(event);
                System.out.println(event);
                switch (event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        xDown10 = event.getX();
                        yDown10 = event.getY();


                        break;
                    case MotionEvent.ACTION_MOVE:
                        float movedX,movedY;
                        movedX = event.getX();
                        movedY = event.getY();


                        float distanceX = movedX - xDown10;
                        float distanceY = movedY - yDown10;

                        imgViewGreenLantern.setX(imgViewGreenLantern.getX()+distanceX);
                        imgViewGreenLantern.setY(imgViewGreenLantern.getY()+distanceY);

                        break;

                }



                return true;
            }
        });

        imgViewWaluigi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                onTouchEvent(event);
                System.out.println(event);
                switch (event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        xDown11 = event.getX();
                        yDown11 = event.getY();


                        break;
                    case MotionEvent.ACTION_MOVE:
                        float movedX,movedY;
                        movedX = event.getX();
                        movedY = event.getY();


                        float distanceX = movedX - xDown11;
                        float distanceY = movedY - yDown11;

                        imgViewWaluigi.setX(imgViewWaluigi.getX()+distanceX);
                        imgViewWaluigi.setY(imgViewWaluigi.getY()+distanceY);

                        break;

                }



                return true;
            }
        });

        imgViewDeadpool.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                onTouchEvent(event);
                System.out.println(event);
                switch (event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        xDown12 = event.getX();
                        yDown12 = event.getY();


                        break;
                    case MotionEvent.ACTION_MOVE:
                        float movedX,movedY;
                        movedX = event.getX();
                        movedY = event.getY();


                        float distanceX = movedX - xDown12;
                        float distanceY = movedY - yDown12;

                        imgViewDeadpool.setX(imgViewDeadpool.getX()+distanceX);
                        imgViewDeadpool.setY(imgViewDeadpool.getY()+distanceY);

                        break;

                }



                return true;
            }
        });

        targetImageView = findViewById(R.id.imageView);
        targetImageView.setOnDragListener( (view, event) -> {

            switch (event.getAction()) {

                case ACTION_DROP:
                    ClipData.Item imageItem = event.getClipData().getItemAt(0);
                    Uri uri = imageItem.getUri();

                    // Request permission to access the image data being dragged into
                    // the target activity's ImageView element.
                    DragAndDropPermissions dropPermissions =
                            requestDragAndDropPermissions(event);

                    ((ImageView)view).setImageURI(uri);

                    // Release the permission immediately afterward because it's no
                    // longer needed.
                    dropPermissions.release();

                    return true;

                // Implement logic for other DragEvent cases here.

                // An unknown action type was received.
                default:
                    Log.e("DragDrop Example","Unknown action type received by View.OnDragListener.");
                    break;
            }

            return false;
        });

        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
                //shuffleImages();
                centerImageViews();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
                addTimeToArray();
            }
        });

    }


    private void startTimer() {
        startTime = System.currentTimeMillis();
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime;
                updateTimerLabel(elapsedTime);
                handler.postDelayed(this, 1);
            }
        };
        handler.postDelayed(timerRunnable, 1);
    }

    private void stopTimer() {
        handler.removeCallbacks(timerRunnable);
    }

    private void updateTimerLabel(long elapsedTime) {
        long seconds = elapsedTime / 1000;
        long milliseconds = elapsedTime % 1000;
        timerTextView.setText(String.format("%02d:%02d:%03d", seconds / 60, seconds % 60, milliseconds));
    }



    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public void onLongPress(@NonNull MotionEvent e) {
            Toast.makeText(GameActivity.this,"Long Press", Toast.LENGTH_SHORT).show();
            //dragImg.setImageResource(R.drawable.search);
            super.onLongPress(e);
        }

        @Override
        public boolean onDoubleTapEvent(@NonNull MotionEvent e) {
            Toast.makeText(GameActivity.this,"Double Tap", Toast.LENGTH_SHORT).show();
            //dragImg.setImageResource(R.drawable.search);
            return super.onDoubleTapEvent(e);
        }

        @Override
        public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
            Toast.makeText(GameActivity.this,"Single Tap Confirm", Toast.LENGTH_SHORT).show();
            //dragImg.setImageResource(R.drawable.search);
            return super.onSingleTapConfirmed(e);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

//    public void shuffleImages() {
//        imageViewList = new ArrayList<>();
//        Collections.shuffle(listRandNums);
//        imageViewList.clear();
//        System.out.println(listRandNums);
//
//
//        for (int i = 0; i < listSize; i++) {
//            if (listRandNums.get(i) == 0) {
//                imageViewList.add(imgViewClifford);
//            } else if (listRandNums.get(i) == 1) {
//                imageViewList.add(imgViewSonic);
//            } else if (listRandNums.get(i) == 2) {
//                imageViewList.add(imgViewSully);
//            } else if (listRandNums.get(i) == 3) {
//                imageViewList.add(imgViewMinion);
//            } else if (listRandNums.get(i) == 4) {
//                imageViewList.add(imgViewKermit);
//            } else if (listRandNums.get(i) == 5) {
//                imageViewList.add(imgViewHulk);
//            } else if (listRandNums.get(i) == 6) {
//                imageViewList.add(imgViewElmo);
//            } else if (listRandNums.get(i) == 7) {
//                imageViewList.add(imgViewCookieMon);
//            } else if (listRandNums.get(i) == 8) {
//                imageViewList.add(imgViewGengar);
//            } else if (listRandNums.get(i) == 9) {
//                imageViewList.add(imgViewGreenLantern);
//            } else if (listRandNums.get(i) == 10) {
//                imageViewList.add(imgViewWaluigi);
//            } else if (listRandNums.get(i) == 11) {
//                imageViewList.add(imgViewDeadpool);
//            }
//        }
//        System.out.println(imageViewList);
//    }

    private void centerImageViews() {
        for (int i = 0; i < imageViewList.size(); i++) {
            centerImageView(imageViewList.get(i));
        }
    }

    private void centerImageView(ImageView imageView) {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getResources().getDisplayMetrics().heightPixels;

        int imageViewWidth = imageView.getWidth();
        int imageViewHeight = imageView.getHeight();

        int newX = (screenWidth - imageViewWidth) / 2;
        double newY = (screenHeight - imageViewHeight) / 1.75;

        imageView.setX(newX);
        imageView.setY((float) newY);
    }

    ArrayList<String> timeArray = new ArrayList<>();
    public void addTimeToArray() {
        timeArray.add(timerTextView.getText().toString());
    }


}