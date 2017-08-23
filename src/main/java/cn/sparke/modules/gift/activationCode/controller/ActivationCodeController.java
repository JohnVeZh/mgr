package cn.sparke.modules.gift.activationCode.controller;

import cn.sparke.core.common.constants.tips.FailureTip;
import cn.sparke.core.common.constants.tips.SuccessTip;
import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.gift.activationCode.entity.ActivationCodeEntity;
import cn.sparke.modules.gift.activationCode.service.ActivationCodeService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 激活码管理控制器
 *
 * @author spark
 * @Date 2017-08-22 09:48:21
 */
@Controller
@RequestMapping("/gift/activationCode")
public class ActivationCodeController extends BaseController {

    private String PREFIX = "/gift/activationCode/";

     @Autowired
     private ActivationCodeService activationCodeService;

    /**
     * 跳转到激活码管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "activationCode.html";
    }


    /**
     * 获取激活码管理列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(ActivationCodeEntity entity) {
        Page codeList = activationCodeService.findList(entity);
        return new PageInfo<>(codeList);

    }


    /**
     * 激活码导入
     */
    @RequestMapping("/import")
    public String activationCodeImport() {
        return PREFIX + "activationCode_import.html";
    }

    /**
     * 激活码导入提交
     */
    @RequestMapping("/importSubmit")
    @ResponseBody
    public Object activationCodeImportSubmit(@RequestParam(value = "qSectionCode") int qSectionCode, @RequestParam(value = "txtFile") MultipartFile txtFile) throws Exception {
        if (qSectionCode != 1 && qSectionCode != 2) {
            return new FailureTip("请选择学段！");
        }
        if(txtFile.isEmpty()){
            return new FailureTip("请选择需要导入文件！");
        }
        else{
            InputStream inputstream = txtFile.getInputStream();
            if(inputstream.available() == 0){ // 激活码条数
                return new FailureTip("未能导入激活码！");
            }else{
                InputStreamReader read = new InputStreamReader(inputstream, "GBK");//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String codeStr;
                List<String> codeList = new ArrayList<>();
                StringBuilder sql=new StringBuilder("INSERT INTO dlb_activation_code (id,code,section,create_date,import_user_id) VALUES ");
                while((codeStr = bufferedReader.readLine()) != null){
                    codeList.add(codeStr);
                }
                read.close();
                int resultCount = activationCodeService.batchInsert(qSectionCode, codeList);
                return resultCount == 0?(new FailureTip("未能导入激活码！")):(new SuccessTip("成功导入：" + resultCount + " 条激活码！"));
            }
        }
    }


}
