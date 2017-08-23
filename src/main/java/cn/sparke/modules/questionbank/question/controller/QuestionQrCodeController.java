package cn.sparke.modules.questionbank.question.controller;

import cn.sparke.modules.questionbank.question.entity.QuestionContentType;
import cn.sparke.modules.questionbank.question.entity.QuestionType;
import cn.sparke.modules.questionbank.question.entity.SectionCodeType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by douht on 2017/7/22.
 */
@Controller
@RequestMapping("/questionQrCode")
public class QuestionQrCodeController {

    private String PREFIX = "/questionbank/question/";

    @RequestMapping("")
    public String index(String type, Model model) {
        model.addAttribute("type", QuestionType.CATALOG_TYPE_QR_CODE);
        String sectionCodes=SectionCodeType.SECTION_CODE_CET4_ENGLISH+","+SectionCodeType.SECTION_CODE_CET6_ENGLISH;
        model.addAttribute("sectionCodes", sectionCodes);
        model.addAttribute("typeName", QuestionType.CATALOG_TYPE_QR_CODE_NAME);
        return PREFIX + "question.html";
    }
}
