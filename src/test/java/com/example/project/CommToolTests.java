package com.example.project;


import com.example.project.comm.PushTypeEnum;
import com.example.project.model.AAAInfo;
import com.example.project.model.DTO_User;
import com.example.project.model.SmallUser;
import com.example.project.mysql.DataDaoService;
import com.example.project.talking.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

/**
 *公共函数和基本操作测试
 * 1.时间函数操作
 * 2.集合操作
 *
 *
 */

@RunWith(value=SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ChinaCoreApplication.class)
public class CommToolTests {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DataDaoService datadaoservice;

    @Test
    public void Test()  throws Exception{





    }





    //region todo: 测试函数

    // todo: 测试Java对象功能
    public void testObject() {
        // todo: 测试对象功能
        DTO_User user = null;
        if (user == null) {
            System.out.println("null");
        } else {
            System.out.println("not null");
        }


        user = DTO_User.builder().build();
        if (user == null) {
            System.out.println("null");
        } else {
            System.out.println("not null");
        }

        // todo: 测试数据小数点
        DecimalFormat df = new DecimalFormat("#.0000");
        System.out.println(df.format(1.234567));
        System.out.println(df.format(0.6234567));
        System.out.println(df.format(0));

    }


    // todo: 日期操作功能
    public void testDate() {
        // todo: 获取今天的日期
        LocalDate today = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dt11=today.format(format);//获取当天日期
        LocalDate yesterday= today.plus(-1,ChronoUnit.DAYS);
        String dt12=yesterday.format(format);//获取前一天日期

        LocalDate oneDate=LocalDate.parse("2018-11-11");
        //***********************************************************************************************************
        // 今天是几号
        int dayofMonth = today.getDayOfMonth();
        // 今天是周几（返回的是个枚举类型，需要再getValue()）
        int dayofWeek = today.getDayOfWeek().getValue();
        // 今年是哪一年
        int dayofYear = today.getDayOfYear();
        // 根据字符串取： 严格按照yyyy-MM-dd验证，02写成2都不行，当然也有一个重载方法允许自己定义格式
        LocalDate endOfFeb = LocalDate.parse("2018-02-28");
        // 取本月第1天：
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        // 取本月第2天：
        LocalDate secondDayOfThisMonth = today.withDayOfMonth(2);
        // 取本月最后一天，再也不用计算是28，29，30还是31：
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        // 取下一天：
        LocalDate firstDayOfNextMonth = lastDayOfThisMonth.plusDays(1);
        // 取2017年1月第一个周一：
        LocalDate firstMondayOf2017 = LocalDate.parse("2018-11-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));

        DateTimeFormatter formatZH= DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        String text = today.format(formatZH);


        //***********************************************************************************************************
        LocalDate currentDate = LocalDate.now();
        LocalDate preDate = currentDate.plus(-10, ChronoUnit.DAYS);
        LocalDate checkSentDate = currentDate.plus(10, ChronoUnit.DAYS);
        String startdt = preDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String enddt = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String checkDate = checkSentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }


    // TODO:Java-8 版的Lanmq操作
    public static void testCollectionStream() {
        List<AAAInfo> listxx = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            String guid = UUID.randomUUID().toString();
            String name = "longlong" + i;
            Calendar ca = Calendar.getInstance();
            ca.add(Calendar.HOUR, i);
            Date dt = ca.getTime();
            AAAInfo model = AAAInfo.builder().name(name).createtime(dt).content("com").remark("hello").nameId(String.valueOf(i)).build();
            listxx.add(model);
        }

        AAAInfo bb = listxx.stream().filter(a -> a.getName().equals("")).findFirst().orElse(null);
        //---------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------

        List<DTO_User> lists = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            lists.add(new DTO_User(i, "timer" + i, 20 + i, i + "@163.com",new Date()));
        }

        //TODO: 对象集合获取对应字符串列表 List<String>
        List<String> contentIds = lists.stream().map(p -> p.getName()).collect(Collectors.toList());
        DTO_User model = lists.stream().filter(p -> ("timer100".equals(p.getName()))).findFirst().orElse(new DTO_User());
        model.setAge(10);

        //TODO: 对象集合获取新组建对象列表 List<Object>
        List<SmallUser> listSmallUser = lists.stream().map(a -> {
            SmallUser smalluser = new SmallUser();
            smalluser.setId(a.getId());
            smalluser.setNamestr(a.getName());
            return smalluser;
        }).filter(p -> (p.getId() > 3)).collect(Collectors.toList());

        //TODO: 根据集合中的字段进行升降排序
        List<DTO_User> cc1 = lists.stream().sorted(Comparator.comparing(DTO_User::getAge)).collect(Collectors.toList());//根据年龄自然顺序（升序=ASC）
        List<DTO_User> cc2 = lists.stream().sorted(Comparator.comparing(DTO_User::getAge).reversed()).collect(Collectors.toList());//根据年龄逆序（降序=DESC）

        //TODO: 根据条件过滤对象集合并排序
        List<DTO_User> one1 = lists.stream().filter(p -> (p.getId() > 3)).sorted(Comparator.comparing(DTO_User::getAge).reversed()).collect(Collectors.toList());
        one1.stream().forEach(p -> System.out.println(p.toString()));

        //TODO: 根据条件过滤对象集合
        List<DTO_User> one = lists.stream().filter(p -> (p.getId() > 3)).collect(Collectors.toList());
        one.stream().forEach(p -> System.out.println(p.toString()));

        //TODO: 根据条件过滤对象 skip--limit
        List<DTO_User> ones = lists.stream().filter(p -> (p.getId() > 3)).collect(Collectors.toList());
        List<DTO_User> bbb = ones;
        List<DTO_User> two = lists.stream().filter(p -> (p.getId() > 3)).skip(2).limit(3).collect(Collectors.toList());
        List<DTO_User> cc = two;
        System.out.println("--------------------------------------------");
        one.stream().forEach(p -> System.out.println(p.toString()));
        System.out.println("--------------------------------------------");
        two.stream().forEach(p -> System.out.println(p.toString()));
        System.out.println("--------------------------------------------");

        //TODO: 根据条件过滤对象 forEach,max
        int[] a = {1, 20, 63, 58, 185, 60, 59, 20};
        Arrays.stream(a).filter(x -> (x >= 50)).sorted().forEach(System.out::println);
        List<String> list = new ArrayList<>();
        list.add("1.0.1");
        list.add("3.0");
        list.add("4.8");
        list.add("4.1.1");
        list.add("4.2.1.8");
        list.add("4.2.2");
        list.add("4.0.2");
        Optional<String> max = list.stream().max((u1, u2) -> u1.compareTo(u2));
    }


    // TODO:Menu操作
    public static void testEnum() {
        String t1 = PushTypeEnum.PUSH_TYP_2.getType();
        String t2 = PushTypeEnum.PUSH_TYP_2.getName();
        String t3 = PushTypeEnum.PUSH_TYP_2.getTip();
        System.out.println(t1 + t2 + t3);
        for (PushTypeEnum value : PushTypeEnum.values()) {
            System.out.println(value.getType());
            System.out.println(value.getName());
            System.out.println(value.getTip());
        }
    }

    //endregion
}



