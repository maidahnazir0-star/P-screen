package com.example.pscreen;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private boolean isFollowing = false;
    private int followerCount = 1245;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFollow = findViewById(R.id.btnFollow);
        Button btnFollowers = findViewById(R.id.btnFollowers);

        LinearLayout panelGrid = findViewById(R.id.panelGrid);
        LinearLayout panelReels = findViewById(R.id.panelReels);
        LinearLayout panelTagged = findViewById(R.id.panelTagged);

        Button tabGrid = findViewById(R.id.tabGrid);
        Button tabReels = findViewById(R.id.tabReels);
        Button tabTagged = findViewById(R.id.tabTagged);

        setupPosts();
        setupSimpleActionToasts();
        updateFollowersButton(btnFollowers);

        btnFollow.setOnClickListener(v -> {
            isFollowing = !isFollowing;
            followerCount += isFollowing ? 1 : -1;
            updateFollowersButton(btnFollowers);

            if (isFollowing) {
                btnFollow.setText("Following");
                btnFollow.setBackgroundColor(Color.parseColor("#262626"));
                Toast.makeText(this, "You are now following this profile.", Toast.LENGTH_SHORT).show();
            } else {
                btnFollow.setText("Follow");
                btnFollow.setBackgroundColor(Color.parseColor("#0095F6"));
                Toast.makeText(this, "You unfollowed this profile.", Toast.LENGTH_SHORT).show();
            }
        });

        tabGrid.setOnClickListener(v -> switchTab(tabGrid, tabReels, tabTagged, panelGrid, panelReels, panelTagged));
        tabReels.setOnClickListener(v -> switchTab(tabReels, tabGrid, tabTagged, panelReels, panelGrid, panelTagged));
        tabTagged.setOnClickListener(v -> switchTab(tabTagged, tabGrid, tabReels, panelTagged, panelGrid, panelReels));
    }

    private void updateFollowersButton(Button btnFollowers) {
        String formatted = String.format(Locale.US, "%,d", followerCount);
        btnFollowers.setText(formatted + "\nfollowers");
    }

    private void switchTab(Button selected, Button other1, Button other2, View selectedPanel, View panel1, View panel2) {
        selected.setTextColor(Color.WHITE);
        other1.setTextColor(Color.parseColor("#8E8E8E"));
        other2.setTextColor(Color.parseColor("#8E8E8E"));

        selectedPanel.setVisibility(View.VISIBLE);
        panel1.setVisibility(View.GONE);
        panel2.setVisibility(View.GONE);

        Toast.makeText(this, "Switched tab", Toast.LENGTH_SHORT).show();
    }

    private void setupPosts() {
        GridLayout postGrid = findViewById(R.id.postGrid);
        for (int i = 1; i <= 12; i++) {
            Button post = new Button(this);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = 220;
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.setMargins(2, 2, 2, 2);
            post.setLayoutParams(params);
            post.setBackgroundColor(Color.parseColor(i % 2 == 0 ? "#454545" : "#5A5A5A"));
            int finalI = i;
            post.setOnClickListener(v -> Toast.makeText(this, "Opened post #" + finalI, Toast.LENGTH_SHORT).show());
            postGrid.addView(post);
        }
    }

    private void setupSimpleActionToasts() {
        int[] clickableIds = {
                R.id.btnTopSwitch, R.id.btnTopNotify, R.id.btnTopMenu,
                R.id.btnPosts, R.id.btnFollowers, R.id.btnFollowing,
                R.id.btnMessage, R.id.btnShare, R.id.btnAddPerson,
                R.id.btnHighlightNew, R.id.btnHighlightTravel, R.id.btnHighlightFits, R.id.btnHighlightFood,
                R.id.txtProfileLink
        };

        for (int id : clickableIds) {
            View view = findViewById(id);
            view.setOnClickListener(v -> Toast.makeText(this,
                    getResources().getResourceEntryName(v.getId()) + " clicked", Toast.LENGTH_SHORT).show());
        }
    }
}
