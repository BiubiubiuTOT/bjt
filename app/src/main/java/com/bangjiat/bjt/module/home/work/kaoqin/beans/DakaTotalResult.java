package com.bangjiat.bjt.module.home.work.kaoqin.beans;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/16 0016
 */

public class DakaTotalResult {
    /**
     * companyClockRule : {"ruleId":29,"inTime":"18:00","outTime":"18:00","workDay":"1,2,3,4,5,6","companyId":31,"longitude":"106.65127292275429","latitude":"26.651439371410557","address":"贵阳互联网金融特区大厦","wifiName":"D-Link_DIR-605L","ctime":1525923218075}
     * companyClockList : [{"clockId":86,"userId":"6c1503d6f55942648a1184bb4f4f5612","inTime":1526433783083,"inType":2,"inAddress":"贵州省贵阳市观山湖区金朱东路7号靠近贵阳互联网金融特区大厦","inLongitude":"106.651171","inLatitude":"26.651509","userRealname":"李官辉","inWay":1,"ctime":1526433783083},{"clockId":88,"userId":"6c1503d6f55942648a1184bb4f4f5612","outTime":1526464044417,"outType":2,"outAddress":"贵州省贵阳市观山湖区金朱东路135号靠近贵阳互联网金融特区大厦","outLongitude":"106.651191","outLatitude":"26.651056","userRealname":"李官辉","outWay":1,"ctime":1526464044454},{"clockId":89,"userId":"6c1503d6f55942648a1184bb4f4f5612","outTime":1526464044527,"outType":2,"outAddress":"贵州省贵阳市观山湖区金朱东路135号靠近贵阳互联网金融特区大厦","outLongitude":"106.651191","outLatitude":"26.651056","userRealname":"李官辉","outWay":1,"ctime":1526464044547}]
     */

    private RuleInput companyClockRule;
    private List<DakaHistoryResult> companyClockList;

    public RuleInput getCompanyClockRule() {
        return companyClockRule;
    }

    public void setCompanyClockRule(RuleInput companyClockRule) {
        this.companyClockRule = companyClockRule;
    }

    public List<DakaHistoryResult> getCompanyClockList() {
        return companyClockList;
    }

    public void setCompanyClockList(List<DakaHistoryResult> companyClockList) {
        this.companyClockList = companyClockList;
    }

}
