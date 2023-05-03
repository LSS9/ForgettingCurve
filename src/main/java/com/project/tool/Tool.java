package com.project.tool;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tool {

    /** 生成唯一标识，防止相同时间戳生成的uuid重复问题 **/
    private static final AtomicInteger SEQ = new AtomicInteger(1000);
    private static final DateTimeFormatter DF_FMT_PREFIX = DateTimeFormatter.ofPattern("yyMMddHHmmssSS");
    private static ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");


    public static String generateOrderNo(){
        LocalDateTime dataTime = LocalDateTime.now(ZONE_ID);
        if(SEQ.intValue()>9990){
            SEQ.getAndSet(1000);
        }
        return  dataTime.format(DF_FMT_PREFIX) + SEQ.getAndIncrement();
    }

    public static String getUUIDByString(String str){
        long id = 0;
        if (str == null || "".equals(str)){
            id = System.currentTimeMillis();
        }else{
            id = Long.valueOf(str);
        }
        return new UUID(id,0).toString();
    }

    /**
     * 常规生成32位uuid
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }


    /**
     * 生成uuid，在高并发场景中
     * @return
     */
    public static String getUUIDAtHighConcurrency(){
        return getUUIDByString(generateOrderNo());
    }


    public static void main(String[] args) {
        List<String> orderNos = Collections.synchronizedList(new ArrayList<String>());
        IntStream.range(0,8000).parallel().forEach(i->{
            orderNos.add(getUUIDAtHighConcurrency());
        });
        List<String> filterOrderNos = orderNos.stream().distinct().collect(Collectors.toList());
        System.out.println("生成订单数："+orderNos.size());
        System.out.println("过滤重复后订单数："+filterOrderNos.size());
        System.out.println("重复订单数："+(orderNos.size()-filterOrderNos.size()));
    }


}
