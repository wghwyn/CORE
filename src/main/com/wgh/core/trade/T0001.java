package com.wgh.core.trade;

import com.wgh.core.Global.PageUtil;
import com.wgh.core.db.model.Salaries;
import com.wgh.core.service.SalariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 */
@Controller("T0001")
public class T0001 extends BaseTrade{
    @Autowired
    private SalariesService salariesService;

    protected void proc() throws Exception{
        StringBuilder returnStr = new StringBuilder();
        System.out.println(reqMsg);
        long start = System.currentTimeMillis();

        //TODO 添加查询条件，可以改到客户端添加，通过报文传送到服务器
        List<Integer> empNoList = new ArrayList<Integer>();
        empNoList.add(44293);
        empNoList.add(44321);
        empNoList.add(44351);
        empNoList.add(44151);
        empNoList.add(44157);
        empNoList.add(44157);
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        Map param = new HashMap();
        //param.put("emp_no", empNoList);
        param.put("from_date", new Date(format.parse("19990101").getTime()));
        param.put("to_date", new Date(format.parse("20101212").getTime()));

        //根据条件查询数据
        List<Salaries> list = salariesService.find(param, new PageUtil(10, 1));

        //拼接返回字符串
        int len = list.size();
        returnStr.append("size:").append(len).append("\n");
        for(int i = 0; i < len; i++) {
            returnStr.append(list.get(i).toString());
        }
        long end = System.currentTimeMillis();
        System.out.println("time:"+(end-start));
        this.rspMsg = returnStr.toString();
    }
}
