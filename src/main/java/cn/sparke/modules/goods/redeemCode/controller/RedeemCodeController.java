package cn.sparke.modules.goods.redeemCode.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.core.modules.shiro.ShiroKit;
import cn.sparke.modules.goods.networkCourse.entity.NetworkCourseEntity;
import cn.sparke.modules.goods.networkCourse.service.NetworkCourseService;
import cn.sparke.modules.goods.redeemCode.entity.ExcelRedeemCodeEntity;
import cn.sparke.modules.goods.redeemCode.entity.QueryVo;
import cn.sparke.modules.goods.redeemCode.entity.RedeemCodeEntity;
import cn.sparke.modules.goods.redeemCode.service.RedeemCodeService;
import cn.sparke.modules.orders.utils.BizUtil;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 网课兑换码控制器
 *
 * @author spark
 * @Date 2017-07-21 17:17:55
 */
@Controller
@RequestMapping("/goods/redeemCode")
public class RedeemCodeController extends BaseController {

    private String PREFIX = "/goods/redeemCode/";

     @Autowired
     private RedeemCodeService redeemCodeService;
     @Autowired
     private NetworkCourseService networkCourseService;

    /**
     * 跳转到网课兑换码首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "redeemCode.html";
    }

    /**
     * 根据id修改禁用和可用的状态
     * @param redeemCodeId
     */
    @RequestMapping("/enableOrNot/{redeemCodeId}")
    @ResponseBody
    public Object enableOrNot(@PathVariable String redeemCodeId){
        redeemCodeService.enableOrNot(redeemCodeId);
        return super.SUCCESS_TIP;
    }

    /**
     * 跳转到添加网课兑换码
     */
    @RequestMapping("/redeemCode_add")
    public String redeemCodeAdd(Model model) {
//        Page<NetworkCourseEntity> list = networkCourseService.queryAllList(null);
//        model.addAttribute("ncList",list);
        return PREFIX + "redeemCode_add.html";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/select")
    @ResponseBody
    public Object select(){
        Page<NetworkCourseEntity> list = networkCourseService.queryAllList(null);
        return list;
    }


    /**
     * 跳转到修改网课兑换码
     */
    @RequestMapping("/redeemCode_update/{redeemCodeId}")
    public String redeemCodeUpdate(@PathVariable String redeemCodeId, Model model) {
       RedeemCodeEntity bean = redeemCodeService.getById(redeemCodeId);
        model.addAttribute("redeemCode",bean);
        return PREFIX + "redeemCode_edit.html";
    }

    /**
     * 获取网课兑换码列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(QueryVo vo) {
        Page<QueryVo> redeemCodeEntities = redeemCodeService.queryCodeList(vo);
        return new PageInfo<>(redeemCodeEntities);
    }

    /**
     * 新增网课兑换码
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

        //根据传入的实体类获取后台的参数
        Map<String, Object> paramMap = BizUtil.getParameterMap(request);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ncId = (String) paramMap.get("ncId");
        Date startDate = sdf.parse(paramMap.get("startDate").toString());
        Date endDate = sdf.parse(paramMap.get("endDate").toString());
        Integer number = Integer.parseInt(paramMap.get("number").toString());
        Integer export = Integer.parseInt(paramMap.get("export").toString());

        NetworkCourseEntity networkCourseEntity = networkCourseService.getById(ncId);
        String ncName = networkCourseEntity.getName();
        //处理其它所需的公共参数
        String createBy = ShiroKit.getUser().getName();
        Date createDate = new Date();

        List<RedeemCodeEntity> codeList = new ArrayList<RedeemCodeEntity>();
        List<ExcelRedeemCodeEntity> excelList = new ArrayList<ExcelRedeemCodeEntity>();
        for (int i = 0; i < number; i++){
            //创建实体类
            RedeemCodeEntity redeemCodeEntity = new RedeemCodeEntity();
            ExcelRedeemCodeEntity excelRedeemCodeEntity = new ExcelRedeemCodeEntity();
            //设置id和code
            UUID uuid = UUID.randomUUID();
            long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
            String code = Long.toString(l, Character.MAX_RADIX);
//            String id = uuid.toString().replace("-","");
            //为实体类附属性值
            redeemCodeEntity.setId(code);
            redeemCodeEntity.setNetworkCourseId(ncId);
            redeemCodeEntity.setCode(code);
            redeemCodeEntity.setStartTime(startDate);
            redeemCodeEntity.setEndTime(endDate);
            redeemCodeEntity.setIsExport(export);
            redeemCodeEntity.setCreateDate(createDate);
            redeemCodeEntity.setCreateBy(createBy);
            redeemCodeEntity.setUpdateDate(createDate);

            excelRedeemCodeEntity.setCode(code);
            excelRedeemCodeEntity.setStartTime(sdf.format(startDate));
            excelRedeemCodeEntity.setEndTime(sdf.format(endDate));
            if (export == 1){
                excelRedeemCodeEntity.setExprotOrNot("已导出");
            }else{
                excelRedeemCodeEntity.setExprotOrNot("未导出");
            }
            excelRedeemCodeEntity.setEnableOrNot("禁用");
            excelRedeemCodeEntity.setState("未使用");
            excelRedeemCodeEntity.setNcName(ncName);

            excelList.add(excelRedeemCodeEntity);
            codeList.add(redeemCodeEntity);
        }
        if (export == 1){
            redeemCodeService.exportExcel(BizUtil.getParameterMap(request), response,excelList);
        }
        redeemCodeService.insertAll(codeList);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除网课兑换码
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String redeemCodeId) {
        redeemCodeService.deleteById(redeemCodeId);
        return SUCCESS_TIP;
    }


    /**
     * 修改网课兑换码
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated RedeemCodeEntity entity) {
        redeemCodeService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 网课兑换码详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return redeemCodeService.getById(id);
    }
}
