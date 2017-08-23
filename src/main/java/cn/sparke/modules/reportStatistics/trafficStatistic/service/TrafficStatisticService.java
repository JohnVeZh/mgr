package cn.sparke.modules.reportStatistics.trafficStatistic.service;

import cn.sparke.modules.reportStatistics.trafficStatistic.constants.FromType;
import cn.sparke.modules.reportStatistics.trafficStatistic.entity.ValueEntity;
import cn.sparke.modules.system.dict.entity.DictEntity;
import cn.sparke.modules.system.dict.mapper.DictMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.sparke.modules.reportStatistics.trafficStatistic.mapper.TrafficStatisticMapper;
import cn.sparke.modules.reportStatistics.trafficStatistic.entity.TrafficStatisticEntity;

import java.util.*;

/**
 * 流量统计Dao
 *
 * @author spark
 * @Date 2017-08-01 10:03:56
 */
@Service
public class TrafficStatisticService{
    @Autowired
    private TrafficStatisticMapper trafficStatisticMapper;
    @Autowired
    private DictMapper dictMapper;
    public String getTableHtml(TrafficStatisticEntity trafficStatistic){
        String []color = {"active","","success","","info","","warning","","danger",""};
        List<Map<String, Object>> data = new ArrayList<>();
        DictEntity dictEntity = new DictEntity();
        dictEntity.setType("traffic_statistic_channel");
        List<DictEntity> dictList = dictMapper.findChannelList(dictEntity);
        Map<String,Map<String,Map<String,Long>>> channelValueMap = new HashMap<>();
        Set<String> areaSet = new HashSet<>();
        for(int i = 0 ;i<dictList.size();i++){
            DictEntity dict = dictList.get(i);
            String name = dict.getLabel();
            String code = dict.getValue();
            trafficStatistic.setChannelCode(null);
            trafficStatistic.setFromType(null);
            trafficStatistic.setChannelCode(code);
            List<ValueEntity> osList =  trafficStatisticMapper.getOSPVUV(trafficStatistic);
            List<ValueEntity> addressList =  trafficStatisticMapper.getAddressPVUV(trafficStatistic);
            Map<String,Map<String,Long>> typeMap = new HashMap<>();
            Map<String,Long> osValueMap = new HashMap<>();
            Map<String,Long> addressValueMap = new HashMap<>();

            for(int j = 0;j<osList.size();j++){
                ValueEntity os = osList.get(j);
                if(os.getFromType() == FromType.Android){
                    osValueMap.put("Android_PV",os.getPv());
                    osValueMap.put("Android_UV",os.getUv());
                }else if(os.getFromType()==FromType.IOS){
                    osValueMap.put("IOS_PV",os.getPv());
                    osValueMap.put("IOS_UV",os.getUv());
                }else if(os.getFromType()==FromType.PC){
                    osValueMap.put("PC_PV",os.getPv());
                    osValueMap.put("PC_UV",os.getUv());
                }else if(os.getFromType()== FromType.Other){
                    osValueMap.put("Other_PV",os.getPv());
                    osValueMap.put("Other_UV",os.getUv());
                }
            }
            typeMap.put("OS",osValueMap);

            for(int j = 0;j<addressList.size();j++){
                ValueEntity address = addressList.get(j);
                String province = address.getProvince();
                if(province != null&&!province.equals("null")){
                    areaSet.add(province);
                    addressValueMap.put(province+"_PV",address.getPv());
                    addressValueMap.put(province+"_UV",address.getUv());
                }
            }
            typeMap.put("Address",addressValueMap);
            channelValueMap.put(name+"-"+code,typeMap);
        }
        String html ="<table class='table table-bordered'>";
        String th = "<tr><th rowspan='2' align='center' class='DataTable_Field th' title='序号'>序号</th>" +
                "<th rowspan='2' align='center' class='DataTable_Field th' title='渠道名称'>渠道名称</th>" +
                "<th rowspan='2' align='center' class='DataTable_Field th' title='渠道代码'>渠道代码</th>" +
                "<th colspan='4' align='center' class='DataTable_Field th' title='渠道引流'>渠道引流</th>" +
                "<th colspan='2' align='center' class='DataTable_Field th' title='总计'>总计</th>";
        String th2 = "<tr><th align='center' class='DataTable_Field th'>Android</th>" +
                "<th align='center' class='DataTable_Field th'>IOS</th>" +
                "<th align='center' class='DataTable_Field th'>PC</th>" +
                "<th align='center' class='DataTable_Field th'>其他</th>" +
                "<th align='center' class='DataTable_Field th'>PV</th>" +
                "<th align='center' class='DataTable_Field th'>UV</th>";
                for(String address:areaSet){
                    th+="<th colspan='2' align='center' class='DataTable_Field th' title='"+address+"'>"+address+"</th>";
                    th2+="<th align='center' class='DataTable_Field th'>PV</th>" +
                            "<th align='center' class='DataTable_Field th'>UV</th>";
                }
                th+="</tr>";
                th2+="</tr>";
        html = html+th+th2;
        int row = 0;
        for(int i = 0 ;i<dictList.size();i++){
            DictEntity dict = dictList.get(i);
            String name = dict.getLabel();
            String code = dict.getValue();
            Map<String,Map<String,Long>> typeMap1 = channelValueMap.get(name+"-"+code);
            Map<String,Long> osValueMap1 = typeMap1.get("OS");
            Map<String,Long> addressValueMap1 = typeMap1.get("Address");
            Long Android_PV = osValueMap1.get("Android_PV")==null?0L:osValueMap1.get("Android_PV");
            Long Android_UV = osValueMap1.get("Android_UV")==null?0L:osValueMap1.get("Android_UV");
            Long IOS_PV= osValueMap1.get("IOS_PV")==null?0L:osValueMap1.get("IOS_PV");
            Long IOS_UV = osValueMap1.get("IOS_UV")==null?0L:osValueMap1.get("IOS_UV");
            Long PC_PV = osValueMap1.get("PC_PV")==null?0L:osValueMap1.get("PC_PV");
            Long PC_UV = osValueMap1.get("PC_UV")==null?0L:osValueMap1.get("PC_UV");
            Long Other_PV = osValueMap1.get("Other_PV")==null?0L:osValueMap1.get("Other_PV");
            Long Other_UV = osValueMap1.get("Other_UV")==null?0L:osValueMap1.get("Other_UV");
            Long totalPV = Android_PV+IOS_PV+PC_PV+Other_PV;
            Long totalUV = Android_UV+IOS_UV+PC_UV+Other_UV;
            String str="<tr class='"+color[row%10]+"'>" +
                    "<td align='center'>"+(row+1)+"</td>" +
                    "<td>"+name+"</td>" +
                    "<td>"+code+"</td>" +
                    "<td>"+Android_PV+"</td>" +
                    "<td>"+IOS_PV+"</td>" +
                    "<td>"+PC_PV+"</td>" +
                    "<td>"+Other_PV+"</td>" +
                    "<td>"+totalPV+"</td>" +
                    "<td>"+totalUV+"</td>";
            for(String address:areaSet){
                Long PvValue = addressValueMap1.get(address+"_PV");
                Long UvValue = addressValueMap1.get(address+"_UV");
                if(PvValue==null){
                    PvValue = 0L;
                }
                if(UvValue==null){
                    UvValue = 0L;
                }
                str+= "<td>"+PvValue+"</td><td>"+UvValue+"</td>";
            }
            str+= "</tr>";
            html+=str;
            row++;
        }
        html += "</table>";

        return html;
    }


}
