package com.aqara.common;

import com.aqara.common.aes.WXBizMsgCrypt;


public class text {
    public static void main(String[] args) throws Exception {
        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt("9IoF",
                "cg5DROA8wox9A5F8cFrM4GTVOuTaH45K5IXT4v31D7v",
                "ww2fd0def5ad11e2cf");
        String msg_signature = "30865067e034ddac83fbe08fe0346c355d288909";
        String timestamp = "1703580143";
        String nonce = "1703184633";
        String postData = "<xml>" +
                "<ToUserName><![CDATA[ww2fd0def5ad11e2cf]]></ToUserName>" +
                "<Encrypt><![CDATA[T0fKCxyCyLJhPCD9PdSqyHTZ8uUN2Btm1MX4FFmsKkM7cjiyEgsearzKq2nNB5xEuXkb4uMBqKDV5kDiYSmSfnKVNrQIPcYK+gqN9cBoHNZSyQ/1wQE3Fg38kcz/tvvzEJmLkQlzPNyBG7w9VvGpeaW9dA6HDeMKEqRyWebPmE0DNhDCDCJnwQt8MtQ62ztlzUhJJmWGCcdGId4jVvDAqcLHDAtqSBHqZx2FCjU5gp+pIkEDfEHwQequPc/NghIMspwkgSxahj6G9Erf8NrfDuKaCvCh+MFkOiZXgYqzd5d2NVIhPqhSkD8jrWwL7JLq13icnI7V4fGzCuPiijJHeCMMEsbDVriUZY7ed++4mEn4k1msKTgrqZbd82NEac0b]]></Encrypt>" +
                "<AgentID><![CDATA[]]></AgentID></xml>";
        String xml = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, postData);
        System.out.printf("xml:" + xml);
    }
}
