package cn.sparke.modules.goods.redeemCode.service;

import cn.sparke.modules.goods.redeemCode.entity.ExcelRedeemCodeEntity;
import cn.sparke.modules.goods.redeemCode.entity.QueryVo;
import cn.sparke.modules.goods.redeemCode.entity.RedeemCodeEntity;
import cn.sparke.modules.goods.redeemCode.mapper.RedeemCodeMapper;
import cn.sparke.modules.orders.utils.DateUtils;
import cn.sparke.modules.orders.utils.ExcelUtils;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 网课兑换码Dao
 *
 * @author spark
 * @Date 2017-07-21 17:17:55
 */
@Service
public class RedeemCodeService{
    @Autowired
    private RedeemCodeMapper redeemCodeMapper;

    public void save(RedeemCodeEntity redeemCode){
        redeemCode.preInsert();
        redeemCodeMapper.insert(redeemCode);
    }

    public void update(RedeemCodeEntity redeemCode){
        redeemCode.preUpdate();
        redeemCodeMapper.update(redeemCode);
    }

    public RedeemCodeEntity get(RedeemCodeEntity redeemCode){
        return redeemCodeMapper.get(redeemCode);
    }

    public RedeemCodeEntity getById(String id){
        return redeemCodeMapper.getById(id);
    }

    public Page<RedeemCodeEntity> findList(RedeemCodeEntity redeemCode){
        Page<RedeemCodeEntity> list = redeemCodeMapper.findList(redeemCode);
        return list;
    }

    //查询返回列表
    public Page<QueryVo> queryCodeList(QueryVo vo){
        Page<QueryVo> list = redeemCodeMapper.queryCodeList(vo);
        return list;
    }

    public void deleteById(String id){
      redeemCodeMapper.deleteById(id);
    }


    /**
     * 根据id修改禁用和可用的状态
     * @param redeemCodeId
     */
    public void enableOrNot(String redeemCodeId) {

        if (null != redeemCodeId && !"".equals(redeemCodeId)){
            RedeemCodeEntity entity = redeemCodeMapper.getById(redeemCodeId);
            if (null != entity){
                if (entity.getIsEnable() == 0){
                    entity.setIsEnable(1);
                }else{
                    entity.setIsEnable(0);
                }
                redeemCodeMapper.update(entity);
            }
        }
    }

    /**
     * 批量插入
     * @param codeList
     */
    public void insertAll(List<RedeemCodeEntity> codeList) {
        redeemCodeMapper.insertAll(codeList);
    }

    public void exportExcel(Map<String, Object> parameterMap, HttpServletResponse response, List<ExcelRedeemCodeEntity> excelList) throws IOException {

        String fileName = new String((  "兑换码-" + DateUtils.getCurrentTimestampStr("yyyyMMddHHmm")).getBytes("gb2312"), "iso8859-1")+ ".xlsx";
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.setCharacterEncoding("utf-8");
        ServletOutputStream outputStream = response.getOutputStream();
        try {
            System.out.println("paramMap: " + parameterMap);
            System.out.println("orderList: " + excelList.toString());
            ExcelUtils.getInstance().exportObjects2Excel(excelList, ExcelRedeemCodeEntity.class, true,  "兑换码", true, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
