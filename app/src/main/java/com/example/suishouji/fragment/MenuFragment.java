package com.example.suishouji.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.suishouji.R;
import com.example.suishouji.view.ItemMoveListView;
import com.example.suishouji.view.MenuButton;
import com.example.suishouji.view.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/24.
 */
public class MenuFragment extends Fragment implements View.OnClickListener ,Animation.AnimationListener{
    @Bind(R.id.menu_user_head_iv)
    RoundImageView menuUserHeadIv;
    @Bind(R.id.menu_user_name_tv)
    TextView menuUserNameTv;
    @Bind(R.id.menu_user_account_tv)
    TextView menuUserAccountTv;
    @Bind(R.id.menu_user_message_layout)
    RelativeLayout menuUserMessageLayout;
    @Bind(R.id.menu_user_upload_iv)
    ImageView menuUserUploadIv;
    @Bind(R.id.menu_sync_title_tv)
    TextView menuSyncTitleTv;
    @Bind(R.id.menu_notes_list_view)
    ListView menuNotesListView;
    @Bind(R.id.menu_bottom_finish_btn)
    MenuButton menuBottomFinishBtn;
    @Bind(R.id.menu_bottom_edit_btn)
    MenuButton menuBottomEditBtn;
    @Bind(R.id.menu_bottom_add_btn)
    MenuButton menuBottomAddBtn;
    @Bind(R.id.menu_bottom_layout)
    LinearLayout menuBottomLayout;
    @Bind(R.id.menu_bottom)
    FrameLayout menuBottom;

    private final boolean FINISH_BUTTON_CLOSE =false;
    private final boolean FINISH_BUTTON_OPEN = true;
    private static final String TAG = "MenuFragment";
    private int type_btn;
    private static final int TYPE_BUTTON_OPEN = 0;
    private static final int TYPE_BUTTON_CLOSE= 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View menuView = LayoutInflater.from(getContext()).inflate(R.layout.main_menu_layout, null, false);
        ButterKnife.bind(this, menuView);
        return menuView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        menuBottomFinishBtn.setOnClickListener(this);
        menuBottomEditBtn.setOnClickListener(this);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("item ---->"+i);

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_expandable_list_item_1,list);
        menuNotesListView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu_bottom_edit_btn://编辑
                type_btn = TYPE_BUTTON_OPEN;
                openFinshButton();

                break;
            case R.id.menu_bottom_finish_btn://添加完成
                type_btn = TYPE_BUTTON_CLOSE;
                closeFinshButton();
                break;
        }
    }

    private void closeFinshButton() {
        Animation closeAnimation =AnimationUtils.loadAnimation(getContext(),R.anim.menu_bottom_finish_close);
        closeAnimation.setAnimationListener(this);
        menuBottomFinishBtn.startAnimation(closeAnimation);
        menuBottomLayout.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.menu_bottom_layout_open));
    }

    private void openFinshButton() {
        Animation openAnimation =AnimationUtils.loadAnimation(getContext(),R.anim.menu_bottom_layout_close);
        openAnimation.setAnimationListener(this);
        menuBottomLayout.startAnimation(openAnimation);
        menuBottomFinishBtn.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.menu_bottom_finish_open));

    }



    private void finishBtnOpenClose(boolean isOpen){
        menuBottomFinishBtn.setVisibility(isOpen?View.VISIBLE:View.GONE);
        menuBottomLayout.setVisibility(isOpen? View.GONE:View.VISIBLE);
    }



    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        animation.setAnimationListener(null);
        switch (type_btn){
            case TYPE_BUTTON_OPEN:
                finishBtnOpenClose(FINISH_BUTTON_OPEN);
                break;
            case TYPE_BUTTON_CLOSE:
                finishBtnOpenClose(FINISH_BUTTON_CLOSE);
                break;
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
