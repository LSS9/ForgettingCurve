package com.project.controller;

import com.github.pagehelper.PageHelper;
import com.project.task.ForgettingCurveTask;
import com.project.pojo.File;
import com.project.pojo.ForgettingCurve;
import com.project.service.FileService;
import com.project.service.ForgettingCurveService;
import com.project.tool.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 抗遗忘曲线
 */
@RestController
@RequestMapping("/system/forgettingcurve")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ForgettingCurveController {

    @Autowired
    private ForgettingCurveService forgettingCurveService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ForgettingCurveTask forgettingCurveTask;

    @GetMapping("/test")
    @ResponseBody
    public Result<Object> test() {
        List<ForgettingCurve> list = new ArrayList<>();
        try{
            list = forgettingCurveService.findListAllAdd1();
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(ResultEnum.EXCEPTION);
        }
        return Result.success(list);
    }

    @GetMapping("/test2")
    @ResponseBody
    public Result<Object> test2() {
        List<Map> list = new ArrayList<>();
        try{
            //PageHelper.startPage(1, 10);
            list = forgettingCurveService.test2();
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(ResultEnum.EXCEPTION);
        }
        return Result.success(list);
    }

    @GetMapping("/test3")
    @ResponseBody
    public Result<Object> test3(LocalDateTime time) {
        List<Map> list = new ArrayList<>();
        try{
            ForgettingCurve forgettingCurve = new ForgettingCurve();
            forgettingCurve.setCreation_time(time);
            System.out.println(forgettingCurve.toString());
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(ResultEnum.EXCEPTION);
        }
        return Result.success(list);
    }

    @GetMapping("/list")
    @ResponseBody
    public Result<Object> list() {
        List<ForgettingCurve> list = new ArrayList<>();
        try{
            list = forgettingCurveService.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.success(list);
    }

    @PostMapping("/save")
    @ResponseBody
    public Result save(ForgettingCurve forgettingCurve){
        try{
            forgettingCurveService.save(forgettingCurve);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(ResultEnum.EXCEPTION);
        }
        return Result.success();
    }

//    @PostMapping("/save")
//    @ResponseBody
//    public Result save(ForgettingCurve forgettingCurve){
//        try{
//            forgettingCurveService.save(forgettingCurve);
//        }catch (Exception e){
//            e.printStackTrace();
//            return Result.error(ResultEnum.EXCEPTION);
//        }
//        return Result.success();
//    }

    @GetMapping("/delete")
    @ResponseBody
    public Result delete(Integer forgetting_curve_id){
        try{
            if (forgetting_curve_id == null) return Result.error(ResultEnum.PARAMETER_EXCEPTION);
            forgettingCurveService.delete(forgetting_curve_id);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(ResultEnum.EXCEPTION);
        }
        return Result.success();
    }

    /**
     * 插入新的记录，并保存文件内容到file
     * @param forgetting_curve_name
     * @param context
     * @return
     */
    @PostMapping("/saveNowRecord")
    @ResponseBody
    public Result saveNowRecord(String forgetting_curve_name,String context){
        try{
            if (forgetting_curve_name == null || "".equals(forgetting_curve_name)) return Result.error(ResultEnum.PARAMETER_EXCEPTION);
            if (context == null) return Result.error(ResultEnum.PARAMETER_EXCEPTION);
            String file_id = fileService.saveOfcC(context);
            //存入关联文件id、创建时间、循环开启时间、次数
            LocalDateTime localDateTime = LocalDateTime.now();
            ForgettingCurve forgettingCurve = new ForgettingCurve(forgetting_curve_name, file_id, localDateTime, localDateTime, 1, 1);
            forgettingCurveService.save(forgettingCurve);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(ResultEnum.EXCEPTION);
        }
        return Result.success();
    }

    /**
     * 列表今天需要提醒的任务,显示任务名称 内容
     * @return
     */
    @GetMapping("/getTodayList")
    @ResponseBody
    public Result getTodayList(){
        Map<String, Object> map = new HashMap<>();
        try{
            //如果当前时间小于20点，则分别查询目前提醒的（时间小于当前时间的）、晚上提醒（时间 大于当前时间、小于当天23.59.59）
            if(LocalDateTime.now().getHour() < 20){
                //目前提醒的（时间小于当前时间的）
                List<Map<String, Object>> mapList1 = forgettingCurveService.getLTTodayList(LocalDateUtils.getLocalDateTimeStr());
                //晚上提醒（时间 大于当前时间、小于当天23.59.59）
                List<Map<String, Object>> mapList2 = forgettingCurveService.getBetweenTodayList(LocalDateUtils.getLocalDateTimeStr(),LocalDateUtils.getEndTimeOfDayStr());
                map.put("key1",mapList1);
                map.put("key2",mapList2);
            }else{//如果当前时间大于20点，则查询目前提醒的（时间小于当天23.59.59）
                //目前提醒的（时间小于当天23.59.59）
                List<Map<String, Object>> mapList1 = forgettingCurveService.getLTTodayList(LocalDateUtils.getEndTimeOfDayStr());
                map.put("key1",mapList1);
                map.put("key2","");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(ResultEnum.EXCEPTION);
        }
        return Result.success(map);
    }

    /**
     * 让指定id进入下一循环
     * @param forgetting_curve_id
     * @return
     */
    @GetMapping("/toNextRemind")
    @ResponseBody
    public Result toNextRemind(Integer forgetting_curve_id){
        try{
            if (forgetting_curve_id == null) return Result.error(ResultEnum.PARAMETER_EXCEPTION);
            forgettingCurveService.toNextRemind(forgetting_curve_id);
            //更新下次提醒时间
            forgettingCurveTask.updateRemindeTask();
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(ResultEnum.EXCEPTION);
        }
        return Result.success();
    }




//    @GetMapping("/export")
//    public void export(HttpServletResponse response) {
//        //数据源
//        //List<Data> dataList = DataApi.listAll();
//        List<ForgettingCurve> list = forgettingCurveService.findAll();
//
//        //非空校验
////        if (CollectionUtils.isEmpty(dataList)) {
////            return new R(HttpStatus.OK, "无可用数据");
////        }
//
//        // 构造导出数据结构
//        String titles = "组号一,组名2,时间3,组号4,组名5,时间6";  // 设置表头
//        String keys = "Forgetting_curve_id,Forgetting_curve_name,Relevant_file,Creation_time,Cycle_opening_time,Frequency";  // 设置每列字段
//
//        // 构造导出数据
//        List<Map<String, Object>> datas = new ArrayList<>();
//        Map<String, Object> map = null;
//        for(ForgettingCurve forgettingCurve :list){
//            map = new HashMap<>();
//            //map.put("Forgetting_curve_id",forgettingCurve.getForgetting_curve_id());
//            map.put("Forgetting_curve_name",forgettingCurve.getForgetting_curve_name());
//            map.put("Relevant_file",forgettingCurve.getRelevant_file());
//            map.put("Creation_time",forgettingCurve.getCreation_time());
//            map.put("Cycle_opening_time",forgettingCurve.getCycle_opening_time());
//            map.put("Frequency",forgettingCurve.getFrequency());
//            datas.add(map);
//        }
//
//
//        // 设置导出文件前缀
//        String fName = "data_";
//
//        // 文件导出
//        try {
//            OutputStream os = response.getOutputStream();
//            CsvExportUtil.responseSetProperties(fName, response);
//            CsvExportUtil.doExport(datas, titles, keys, os);
//            CsvExportUtil.doExport(datas, titles, keys, os);
//            os.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ;
//    }



}