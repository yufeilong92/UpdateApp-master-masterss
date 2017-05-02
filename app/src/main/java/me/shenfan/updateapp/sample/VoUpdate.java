package me.shenfan.updateapp.sample;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: UpdateApp-master-master
 * @Package me.shenfan.updateapp.sample
 * @Description: $todo$
 * @author: YFL
 * @date: 2017/5/2 23:02
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */


public class VoUpdate {
    String msg;
    String uptype;
    DateBean date;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUptype() {
        return uptype;
    }

    public void setUptype(String uptype) {
        this.uptype = uptype;
    }

    public DateBean getDate() {
        return date;
    }

    public void setDate(DateBean date) {
        this.date = date;
    }

    public static class DateBean{
        String UpVersion;
        String upcode;
        String UpdateSize;
        String upinfo;

        public String getUpVersion() {
            return UpVersion;
        }

        public void setUpVersion(String upVersion) {
            this.UpVersion = upVersion;
        }

        public String getUpcode() {
            return upcode;
        }

        public void setUpcode(String upcode) {
            this.upcode = upcode;
        }

        public String getUpdateSize() {
            return UpdateSize;
        }

        public void setUpdateSize(String updateSize) {
            this.UpdateSize = updateSize;
        }

        public String getUpinfo() {
            return upinfo;
        }

        public void setUpinfo(String upinfo) {
            this.upinfo = upinfo;
        }
    }
}
