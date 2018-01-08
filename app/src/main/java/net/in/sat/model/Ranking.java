package net.in.sat.model;

import java.util.List;

public class Ranking {
    /**
     * ranking : Most Viewed Product
     * products : [{"id":1,"view_count":56700},{"id":2,"view_count":60000},{"id":3,"view_count":74000},{"id":4,"view_count":12000},{"id":5,"view_count":66000},{"id":6,"view_count":23456},{"id":7,"view_count":65783},{"id":8,"view_count":23456},{"id":9,"view_count":78965},{"id":10,"view_count":23456},{"id":11,"view_count":65784},{"id":12,"view_count":34756},{"id":13,"view_count":67543},{"id":14,"view_count":20000},{"id":15,"view_count":35000},{"id":16,"view_count":22000},{"id":17,"view_count":21000},{"id":18,"view_count":28000},{"id":19,"view_count":87694},{"id":20,"view_count":78645},{"id":21,"view_count":54673},{"id":22,"view_count":76894},{"id":23,"view_count":34567},{"id":24,"view_count":23456},{"id":25,"view_count":54678}]
     */

    private String ranking;
    private List<ProductInfo> products;

    public String getRanking() {
        return ranking;
    }

    public List<ProductInfo> getProductInfo() {
        return products;
    }


    public static class ProductInfo {
        /**
         * id : 1
         * view_count : 56700
         */

        private int id;
        private int view_count;
        private int order_count;
        private int shares;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getView_count() {
            return view_count;
        }

        public void setView_count(int view_count) {
            this.view_count = view_count;
        }

        public int getOrder_count() {
            return order_count;
        }

        public void setOrder_count(int order_count) {
            this.order_count = order_count;
        }

        public int getShares() {
            return shares;
        }

        public void setShares(int shares) {
            this.shares = shares;
        }

        @Override
        public String toString() {
            return "" + id;
        }
    }

    @Override
    public String toString() {
        return ranking + " : " + products.toString();
    }
}