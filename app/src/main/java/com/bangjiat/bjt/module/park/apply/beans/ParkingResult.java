package com.bangjiat.bjt.module.park.apply.beans;

import java.io.Serializable;
import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/14 0014
 */

public class ParkingResult {

    /**
     * pageData : {"total":3,"size":10,"pages":1,"current":1,"records":[{"spaceId":2,"name":"fdsa","address":"fdsa","longitude":"fdsa","latitude":"fdsa","number":1,"open":1,"monthFee":1,"yearFee":1,"hourFee":1,"temporaryNumber":1,"fixedNumber":1,"contact":"fdsa","contactWay":"fdsa","temporary":50,"fixed":50},{"spaceId":3,"name":"对方回复电话","address":"法规和法国","longitude":"12","latitude":"32","number":5,"open":1,"monthFee":1,"yearFee":1,"hourFee":1,"temporaryNumber":1,"fixedNumber":1,"contact":"规范化规范化","contactWay":"3423423424"},{"spaceId":1,"name":"中天停车场","address":"金融城","longitude":"12.3456","latitude":"13.222","number":200,"open":1,"monthFee":0.01,"yearFee":2400,"hourFee":10,"temporaryNumber":100,"fixedNumber":100,"temporary":50,"fixed":50}]}
     */

    private PageDataBean pageData;

    public PageDataBean getPageData() {
        return pageData;
    }

    public void setPageData(PageDataBean pageData) {
        this.pageData = pageData;
    }

    public static class PageDataBean {
        /**
         * total : 3
         * size : 10
         * pages : 1
         * current : 1
         * records : [{"spaceId":2,"name":"fdsa","address":"fdsa","longitude":"fdsa","latitude":"fdsa","number":1,"open":1,"monthFee":1,"yearFee":1,"hourFee":1,"temporaryNumber":1,"fixedNumber":1,"contact":"fdsa","contactWay":"fdsa"},{"spaceId":3,"name":"对方回复电话","address":"法规和法国","longitude":"12","latitude":"32","number":5,"open":1,"monthFee":1,"yearFee":1,"hourFee":1,"temporaryNumber":1,"fixedNumber":1,"contact":"规范化规范化","contactWay":"3423423424"},{"spaceId":1,"name":"中天停车场","address":"金融城","longitude":"12.3456","latitude":"13.222","number":200,"open":1,"monthFee":0.01,"yearFee":2400,"hourFee":10,"temporaryNumber":100,"fixedNumber":100,"temporary":50,"fixed":50}]
         */

        private int total;
        private int size;
        private int pages;
        private int current;
        private List<RecordsBean> records;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean implements Serializable {
            /**
             * spaceId : 2
             * name : fdsa
             * address : fdsa
             * longitude : fdsa
             * latitude : fdsa
             * number : 1
             * open : 1
             * monthFee : 1.0
             * yearFee : 1.0
             * hourFee : 1.0
             * temporaryNumber : 1
             * fixedNumber : 1
             * contact : fdsa
             * contactWay : fdsa
             * temporary : 50
             * fixed : 50
             */

            private int spaceId;
            private String name;
            private String address;
            private String longitude;
            private String latitude;
            private int number;
            private int open;
            private double monthFee;
            private double yearFee;
            private double hourFee;
            private int temporaryNumber;
            private int fixedNumber;
            private String contact;
            private String contactWay;
            private int temporary;
            private int fixed;

            public int getSpaceId() {
                return spaceId;
            }

            public void setSpaceId(int spaceId) {
                this.spaceId = spaceId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public int getOpen() {
                return open;
            }

            public void setOpen(int open) {
                this.open = open;
            }

            public double getMonthFee() {
                return monthFee;
            }

            public void setMonthFee(double monthFee) {
                this.monthFee = monthFee;
            }

            public double getYearFee() {
                return yearFee;
            }

            public void setYearFee(double yearFee) {
                this.yearFee = yearFee;
            }

            public double getHourFee() {
                return hourFee;
            }

            public void setHourFee(double hourFee) {
                this.hourFee = hourFee;
            }

            public int getTemporaryNumber() {
                return temporaryNumber;
            }

            public void setTemporaryNumber(int temporaryNumber) {
                this.temporaryNumber = temporaryNumber;
            }

            public int getFixedNumber() {
                return fixedNumber;
            }

            public void setFixedNumber(int fixedNumber) {
                this.fixedNumber = fixedNumber;
            }

            public String getContact() {
                return contact;
            }

            public void setContact(String contact) {
                this.contact = contact;
            }

            public String getContactWay() {
                return contactWay;
            }

            public void setContactWay(String contactWay) {
                this.contactWay = contactWay;
            }

            public int getTemporary() {
                return temporary;
            }

            public void setTemporary(int temporary) {
                this.temporary = temporary;
            }

            public int getFixed() {
                return fixed;
            }

            public void setFixed(int fixed) {
                this.fixed = fixed;
            }

            @Override
            public String toString() {
                return "RecordsBean{" +
                        "spaceId=" + spaceId +
                        ", name='" + name + '\'' +
                        ", address='" + address + '\'' +
                        ", longitude='" + longitude + '\'' +
                        ", latitude='" + latitude + '\'' +
                        ", number=" + number +
                        ", open=" + open +
                        ", monthFee=" + monthFee +
                        ", yearFee=" + yearFee +
                        ", hourFee=" + hourFee +
                        ", temporaryNumber=" + temporaryNumber +
                        ", fixedNumber=" + fixedNumber +
                        ", contact='" + contact + '\'' +
                        ", contactWay='" + contactWay + '\'' +
                        ", temporary=" + temporary +
                        ", fixed=" + fixed +
                        '}';
            }
        }
    }
}
