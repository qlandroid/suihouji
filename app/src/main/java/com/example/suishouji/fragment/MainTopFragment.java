package com.example.suishouji.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.suishouji.R;
import com.example.suishouji.bean.AddAccountBookBean;
import com.example.suishouji.view.ShowHideNumberImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/23.
 */
public class MainTopFragment extends Fragment {
    private final static String NUMBER_HILD = "****";

    @Bind(R.id.main_top_icon)
    ImageView mainTopIcon;
    @Bind(R.id.main_content_message_iv)
    ImageView mainContentMessageIv;
    @Bind(R.id.main_content_take_notes_iv)
    ImageView mainContentTakeNotesIv;
    @Bind(R.id.main_content_message_red_iv)
    ImageView mainContentMessageRedIv;
    @Bind(R.id.board_1)
    TextView board1;
    @Bind(R.id.board_number_1)
    TextView boardNumber1;
    @Bind(R.id.main_content_income_layout)
    LinearLayout mainContentIncomeLayout;
    @Bind(R.id.board_number_0)
    TextView boardNumber0;
    @Bind(R.id.main_content_show_hide_iv)
    ShowHideNumberImageView mainContentShowHideIv;
    @Bind(R.id.main_content_expend_number_layout)
    LinearLayout mainContentExpendNumberLayout;
    @Bind(R.id.main_content_hide_number_iv)
    ImageView mainContentHideNumberIv;
    @Bind(R.id.board_0)
    TextView board0;
    @Bind(R.id.board_2)
    TextView board2;
    @Bind(R.id.board_number_2)
    TextView boardNumber2;
    @Bind(R.id.main_content_layout)
    RelativeLayout mainContentLayout;

    private AddAccountBookBean mBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mainTopView = inflater.inflate(R.layout.main_top_fragment_layout, container, false);
        ButterKnife.bind(this, mainTopView);
        return mainTopView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 设置top显示的图片
     * @param topIcon
     */
    public void setMainTopIcon(int topIcon) {
        Glide.with(getContext()).load(topIcon).override(300, 300).into(mainTopIcon);
    }

    /**
     * 设置要显示的属性；
     * @param bean
     */
    public void setTopTitleText(AddAccountBookBean bean) {
        board0.setText(bean.getFaceBoard_0());
        board1.setText(bean.getFaceBoard_1());
        board2.setText(bean.getFaceBoard_2());
    }

    /**
     * 用于设置显示数字
     * @param bean
     */
    public void setTopNumberText(AddAccountBookBean bean){
        boardNumber0.setText(bean.getBoardNumber0());
        boardNumber1.setText(bean.getBoardNumber1());
        boardNumber2.setText(bean.getBoardNumber2());
    }

    /**
     * 用于隐藏数字
     * @param showContent
     */
    private void setHideNumberText(String showContent){
        boardNumber0.setText(showContent);
        boardNumber1.setText(showContent);
        boardNumber2.setText(showContent);
    }

    /**
     * 控制是否要显示
     * @param isShow
     */
    public void setShowHideNumber(boolean isShow){
        if (isShow){
            setTopNumberText(mBean);
        }else {
            setHideNumberText(NUMBER_HILD);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
