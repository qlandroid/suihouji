package com.example.suishouji.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */
public class AddAccountBookBean {

    /**
     * name : 标准理财
     * content : 标准账本，分类较全
     * icon : suite_bg_for_standard_0.png
     * type : 0
     */

    private List<BeanBean> bean;

    public List<BeanBean> getBean() {
        return bean;
    }

    public void setBean(List<BeanBean> bean) {
        this.bean = bean;
    }

    public static class BeanBean {
        private String name;
        private String content;
        private int icon;
        private int type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
