package com.example.project.tool;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 *
 *  字符串工具栏
 *
 * @author mxf
 * @since 2016-8-30
 */
public class StringUtil {

    /** 空字符串。 */
    public static final String EMPTY_STRING = "";

    /**
     * 检查字符串是否为<code>null</code>或空字符串<code>""</code>。
     *
     * </pre>
     *
     * @param str
     *            要检查的字符串
     *
     * @return 如果为空, 则返回<code>true</code>
     */
    public static boolean isEmpty(String str) {
        return ((str == null) || (str.length() == 0));
    }

    /**
     * 取得第一个出现的分隔子串之前的子串。
     *
     * <p>
     * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>或未找到该子串，则返回原字符串。
     *
     *
     * </p>
     *
     * @param str
     *            字符串
     * @param separator
     *            要搜索的分隔子串
     *
     * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
     */
    public static String substringBefore(String str, String separator) {
        if ((str == null) || (separator == null) || (str.length() == 0)) {
            return str;
        }

        if (separator.length() == 0) {
            return EMPTY_STRING;
        }

        int pos = str.indexOf(separator);

        if (pos == -1) {
            return str;
        }

        return str.substring(0, pos);
    }

    /**
     * 比较两个字符串（大小写敏感）。
     *
     * @param str1
     *            要比较的字符串1
     * @param str2
     *            要比较的字符串2
     *
     * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
     */
    public static boolean equals(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }

        return str1.equals(str2);
    }

    /**
     * 编码格式转换
     * @param s
     * @return
     */
    public static final String decode(String s) {
        if (s == null) {
            return null;
        }
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 检查字符串是否是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
     *
     *
     * @param str
     *            要检查的字符串
     *
     * @return 如果为空白, 则返回<code>true</code>
     */
    public static boolean isBlank(String str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }
    /**
     * 获取字母数字随机八位
     * @return
     */
    public static String genRandomKeyNum(){
        int  maxNum = 62;
        int i;
        int count = 0;
        char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z','0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z' };
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while(count < 16){
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count ++;
            }
        }
        return pwd.toString();
    }

    /**
     * 根据传入的字符串，长度获取秘钥
     * @return
     */
    public static String getKeyParam(String s,int b,int length){
        return s.substring(b,b + length);
    }

    /**
     * 根据传入的字符串，长度获取参数
     * @return
     */
    public static String getValueParam(String s,int b,int length){
        StringBuffer sb = new StringBuffer("");
        sb.append(s.substring(0, b));

        sb.append(s.substring(b + length));
        return sb.toString();
    }


    /**
     * 根据传入的字符串，长度将秘钥塞进去
     * @return
     */
    public static String setKeyParam(String s,String key,int b){
        StringBuffer sb = new StringBuffer("");

        sb.append(s.substring(0, b));
        sb.append(key);
        sb.append(s.substring(b));
        return sb.toString();
    }


//    public static String handleJsonArrayToString(String pars,Map<String,Object> valueMap){
//
//        pars=pars.replace("form=", "");
//        JSONArray array=JSONObject.parseArray(pars);
//        String joinParams="";
//
//        List<String> plist=new ArrayList<String>();
//        for (Object object : array) {
//            JSONObject json=JSONObject.parseObject(object+"");
//            plist.add(""+json.get("name"));
//        }
//
//        for (int i=0;i<plist.size()-1;i++) {
//            joinParams+=plist.get(i)+"="+valueMap.get(plist.get(i))+"&";
//        }
//        //拼接后的参数
//        joinParams+=plist.get(plist.size()-1)+"="+valueMap.get(plist.get(plist.size()-1));
//
//        return joinParams;
//
//    }


    public static String genStringReplace(String str){
        String[] st=str.split("=");
        String result=st[1].replace("}", "");
        return result;
    }



    public static String splitWithComma(String xf){
        String result="";
        if(xf.indexOf(",")!=-1){
            String[] st=xf.split(",");
            for (String string : st) {
                result+="\""+string+"\""+",";
            }
            result=result.substring(0,result.length()-1);
            return result;
        }else{
            return "\""+xf+"\"";
        }
    }


    public static List<String> splitStringToListByCommma(String str){
        List<String> list=new ArrayList<String>();
        if(str.indexOf(",")!=-1){
            String[] temp=str.split(",");
            for (String string : temp) {
                list.add(string);
            }
            return list;
        }else{
            list.add(str);
            return list;
        }
    }

    public static Set<String> splitStrToSet(String allPinyin,String shortPinyin){
        Set<String> pinyin = new HashSet<String>();
        List<String> allList=splitStringToListByCommma(allPinyin);
        List<String> shortList=splitStringToListByCommma(shortPinyin);
        for (String string : allList) {
            pinyin.add(string);
        }
        for (String string : shortList) {
            pinyin.add(string);
        }
        return pinyin;
    }

    /**
     * 描述：手机号格式验证.
     *
     * @param str 指定的手机号码字符串
     * @return 是否为手机号码格式:是为true，否则false
     */
    public static boolean isMobileNo(String str) {
        boolean isMobileNo = false;
        try {
            //13开头
            //Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
            Pattern p = Pattern.compile("^(13|14|15|17|18)\\d{9}$");
            Matcher m = p.matcher(str);
            isMobileNo = m.matches();
        } catch (Exception e) {
            //BaseConstants.MY_LOG.warn("校验手机号码异常", e);
            //throw e;

        }
        return isMobileNo;
    }

    public static void main(String[] args) {
        System.out.println(splitWithComma("xx"));
    }

    /**
     * 获取去掉横线的长度为32的UUID串.
     *
     * @author WuShuicheng.
     * @return uuid.
     */
    public static String get32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }






}