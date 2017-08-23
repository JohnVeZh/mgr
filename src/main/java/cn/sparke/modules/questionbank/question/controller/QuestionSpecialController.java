package cn.sparke.modules.questionbank.question.controller;

import cn.sparke.core.common.utils.ToolUtil;
import cn.sparke.modules.questionbank.question.entity.QuestionContentType;
import cn.sparke.modules.questionbank.question.entity.QuestionType;
import cn.sparke.modules.questionbank.question.entity.SectionCodeType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 专项练习
 * Created by douht on 2017/7/20.
 */

@Controller
@RequestMapping("/questionSpecial")
public class QuestionSpecialController {

    private String PREFIX = "/questionbank/question/";

    @RequestMapping("")
    public String index(String type, Model model) {
        model.addAttribute("type", QuestionType.CATALOG_TYPE_SPECIAL);
        String sectionCodes= SectionCodeType.SECTION_CODE_CET4_ENGLISH+","+SectionCodeType.SECTION_CODE_CET6_ENGLISH;
        model.addAttribute("sectionCodes", sectionCodes);
        model.addAttribute("typeName", QuestionType.CATALOG_TYPE_SPECIAL_NAME);
        return PREFIX + "question.html";
    }
}
