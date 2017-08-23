package cn.sparke.modules.paper.paperCorrect.entity;

import cn.sparke.core.common.entity.BaseEntity;

/**
 * Created by wanghaiguang on 2017/8/22.
 */
public class RuleBean extends BaseEntity{

    private String ruleId; //规则id

    private String ruleDetailId; //股则详情id

    private String ruleName; //规则名称

    private Integer level; //规则级别

    private String content; //规则内容

    private Integer score; //分数

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getRuleDetailId() {
        return ruleDetailId;
    }

    public void setRuleDetailId(String ruleDetailId) {
        this.ruleDetailId = ruleDetailId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
