package com.hex.base.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hex.base.domain.Attendance;
import com.hex.base.domain.UserInfo;
import com.hex.base.service.AttendanceService;
import com.hex.base.service.UserInfoService;
import com.hex.base.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * User: hexuan
 * Date: 2018/11/8
 * Time: 11:30 AM
 */
@RestController
public class ExcelController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("exportUserInfoExcel")
    public Object exportUserInfoExcel(HttpServletResponse response) throws IOException {
        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
        List<UserInfo> userInfoList = userInfoService.findAllUserInfoList(sort);
        int num = 1;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String title = "用户信息列表";
        Map<String, String> headMap = new LinkedHashMap<>();
        headMap.put("num", "序号");
        headMap.put("name", "姓名");
        headMap.put("phone", "联系电话");
        headMap.put("placeName", "所在地");
        headMap.put("hospital", "所在医院");
        headMap.put("attendanceTimes", "签到次数");
        headMap.put("createTime", "创建时间");
        JSONArray ja = new JSONArray();
        JSONObject jsonObject;
        for (UserInfo userInfo : userInfoList) {
            jsonObject = new JSONObject();
            jsonObject.put("num", num++);
            jsonObject.put("name", userInfo.getName());
            jsonObject.put("phone", userInfo.getPhone());
            jsonObject.put("placeName", userInfo.getPlaceName());
            jsonObject.put("hospital", userInfo.getHospital());
            jsonObject.put("attendanceTimes", userInfo.getAttendanceTimes());
            jsonObject.put("createTime", simpleDateFormat.format(userInfo.getCreateTime()));

            ja.add(jsonObject);
        }
        ExcelUtil.downloadExcelFile(title, headMap, ja, response);
        return "success";
    }

    @GetMapping("exportAttendanceExcelByAll")
    public Object exportAttendanceExcelByAll(HttpServletResponse response) throws IOException {
        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
        List<Attendance> attendanceList = attendanceService.findAllAttendanceList(sort);
        this.exportAttendanceExcel(attendanceList, response);
        return "success";
    }

    @GetMapping("exportAttendanceExcelByMeeting")
    public Object exportAttendanceExcelByMeeting(HttpServletResponse response, Integer meetingId) throws IOException {
        List<Attendance> attendanceList = attendanceService.findAttendanceListByMeetingId(meetingId);
        this.exportAttendanceExcel(attendanceList, response);
        return "success";
    }

    private void exportAttendanceExcel(List<Attendance> attendanceList, HttpServletResponse response) {
        int num = 1;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String title = "签到列表";
        Map<String, String> headMap = new LinkedHashMap<>();
        headMap.put("num", "序号");
        headMap.put("meetingName", "会议名称");
        headMap.put("name", "姓名");
        headMap.put("phone", "联系电话");
        headMap.put("productName", "购买意向");
        headMap.put("placeName", "所在地");
        headMap.put("hospital", "所在医院");
        headMap.put("winningRecord", "是否中奖");
        headMap.put("createTime", "创建时间");
        JSONArray ja = new JSONArray();
        JSONObject jsonObject;
        String winningRecordStr;
        for (Attendance attendance : attendanceList) {
            jsonObject = new JSONObject();
            jsonObject.put("num", num++);
            jsonObject.put("meetingName", attendance.getMeetingName());
            jsonObject.put("name", attendance.getName());
            jsonObject.put("phone", attendance.getPhone());
            jsonObject.put("productName", attendance.getProductName());
            jsonObject.put("placeName", attendance.getPlaceName());
            jsonObject.put("hospital", attendance.getHospital());
            jsonObject.put("createTime", simpleDateFormat.format(attendance.getCreateTime()));
            winningRecordStr = "";
            if (null != attendance.getWinningRecord()) {
                if (attendance.getWinningRecord()) {
                    winningRecordStr = "中奖";
                } else {
                    winningRecordStr = "未中奖";
                }
            }
            jsonObject.put("winningRecord", winningRecordStr);

            ja.add(jsonObject);
        }
        ExcelUtil.downloadExcelFile(title, headMap, ja, response);
    }
}
