package com.example.project.xml;

import java.net.URL;
import java.util.*;

import com.example.project.model.HealthExamPackDetail;
import com.example.project.model.HealthExamPackItem;
import org.dom4j.*;
import org.dom4j.io.SAXReader;


public class xmlhelper {
    public static List<HealthExamPackItem> GetList() throws Exception {
        List<HealthExamPackItem> itemList =new ArrayList<>();
        SAXReader reader = new SAXReader();
        URL url=new URL("http://zhangshangtijian.b0.upaiyun.com/common/healthexampack.xml");
        Document document =  reader.read(url);
        Element root = document.getRootElement();
        List list = root.elements("item");
        for(Object obj:list){
            Element row = (Element)obj;
            Element idinfo = row.element("id");
            String id=idinfo.getText();
            System.out.println(idinfo.getName()+": "+idinfo.getText());

            Element titleinfo = row.element("title");
            String title=titleinfo.getText();
            System.out.println(titleinfo.getName()+": "+titleinfo.getText());

            Element imageinfo = row.element("image");
            String image=imageinfo.getText();
            System.out.println(imageinfo.getName()+": "+imageinfo.getText());

            HealthExamPackItem healthExamPackItem= HealthExamPackItem.builder().id(id).image(image).title(title).build();
            itemList.add(healthExamPackItem);
        }
        return itemList;
    }


    public static HealthExamPackDetail GetSingle(String queryId) throws Exception {
        HealthExamPackDetail healthExamPackDetail =new HealthExamPackDetail();
        SAXReader reader = new SAXReader();
        URL url=new URL("http://zhangshangtijian.b0.upaiyun.com/common/healthexampack.xml");
        Document document =  reader.read(url);
        Element root = document.getRootElement();
        List list = root.elements("item");
        for(Object obj:list){
            Element row = (Element)obj;
            Element idinfo = row.element("id");
            String id=idinfo.getText();
            if(queryId.equals(id)){
            System.out.println(idinfo.getName()+": "+idinfo.getText());
            Element titleinfo = row.element("title");
            String title=titleinfo.getText();
            System.out.println(titleinfo.getName()+": "+titleinfo.getText());

            Element imageinfo = row.element("image");
            String image=imageinfo.getText();
            System.out.println(imageinfo.getName()+": "+imageinfo.getText());

            Element contentinfo = row.element("content");
            String content=contentinfo.getText();
            System.out.println(contentinfo.getName()+": "+contentinfo.getText());
            healthExamPackDetail= healthExamPackDetail.builder().id(id).image(image).title(title).content(content).build();
            break;
            }
        }
        return healthExamPackDetail;
    }

    /**
     * 解析XML到对象List
     */
    public Object DeserializeVersionXML(String url) {
        url = "https://zhangshangtijian.b0.upaiyun.com/common/VersionConfig.xml";
        Collection<Object> modelList=new ArrayList<>();
        SAXReader reader = new SAXReader();
        try {
            URL urlStr = new URL(url);
            Document document = reader.read(urlStr);
            Element root = document.getRootElement();
            List items = root.elements("items");
            Element one = (Element) items.get(0);
            String mesage = one.attributeValue("message");
            String iOSDownlink = one.attributeValue("iOSDownlink");
            String androidDownlink = one.attributeValue("androidDownlink");
            String apkDownLink = one.attributeValue("apkDownLink");
            List<Element> list = root.elements();
            for (Element e : list) {
                List<Element> books = e.elements();
                for (Element book : books) {
                    String index = book.element("index").getText();
                    String versioncode = book.element("versioncode").getText();
                    String versionname = book.element("versionname").getText();
                    String ostype = book.element("ostype").getText();
                    String status = book.element("status").getText();
                    String apkdownlink="";
                    String downlink="";
                    if ("1".equals(ostype)) {
                        downlink = iOSDownlink;
                        apkdownlink = "";
                    }
                    if ("2".equals(ostype)) {
                        downlink = androidDownlink;
                        apkdownlink = apkDownLink;
                    }
//                    VersionItemVO versionItemVO = VersionItemVO.builder()
//                            .index(Integer.parseInt(index))
//                            .versionCode(versioncode)
//                            .versionName(versionname)
//                            .osType(Integer.parseInt(ostype))
//                            .status(Integer.parseInt(status))
//                            .apkDownLink(apkdownlink)
//                            .downLink(downlink)
//                            .message(mesage)
//                            .build();
                    ((ArrayList<Object>) modelList).add(null);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return modelList;
    }


}
