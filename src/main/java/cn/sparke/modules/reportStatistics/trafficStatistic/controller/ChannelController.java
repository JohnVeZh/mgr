package cn.sparke.modules.reportStatistics.trafficStatistic.controller;

import cn.sparke.core.common.constants.Const;
import cn.sparke.core.common.constants.dictmap.DictMap;
import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.modules.log.LogObjectHolder;
import cn.sparke.core.modules.log.annotation.BussinessLog;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.core.modules.shiro.annotation.Permission;
import cn.sparke.modules.system.dict.entity.DictEntity;
import cn.sparke.modules.system.dict.mapper.DictMapper;
import cn.sparke.modules.system.dict.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 渠道控制器
 *
 * @author spark
 * @Date 2017年4月26日 12:55:31
 */
@Controller
@RequestMapping("/channel")
public class ChannelController extends BaseController {

    private String PREFIX = "/reportStatistics/trafficStatistic/Channel/";

    @Autowired
    private DictMapper dictMapper;


    @Autowired
    private DictService dictService;

    /**
     * 跳转到渠道管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "channel.html";
    }

    /**
     * 跳转到添加渠道
     */
    @RequestMapping("/channel_add")
    public String deptAdd() {
        return PREFIX + "channel_add.html";
    }

    /**
     * 跳转到修改渠道
     */
    @RequestMapping("/channel_edit/{channelId}")
    public String deptUpdate(@PathVariable String channelId, Model model) {
        DictEntity channel = dictMapper.getById(channelId);
        model.addAttribute("channel", channel);
        LogObjectHolder.me().set(channel);
        return PREFIX + "channel_edit.html";
    }

    /**
     * 新增渠道
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@RequestBody  DictEntity dictEntity) {
        this.dictService.addDict(dictEntity);
        return SUCCESS_TIP;
    }

    /**
     * 获取所有渠道列表，分页
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(DictEntity discEntity) {
        discEntity.setType("traffic_statistic_channel");
        return new PageInfo<>(this.dictMapper.findChannelList(discEntity));
    }

    /**
     * 渠道详情
     */
    @RequestMapping(value = "/detail/{channelId}")
    @ResponseBody
    public Object detail(@PathVariable("channelId") String channelId) {
        return dictMapper.getById(channelId);
    }

    /**
     * 修改渠道
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@RequestBody DictEntity dictEntity) {
        dictService.editDict(dictEntity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除渠道记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String channelId) {
        DictEntity dictEntity = dictMapper.getById(channelId);
        dictService.delteDict(channelId);
        return SUCCESS_TIP;
    }
}
