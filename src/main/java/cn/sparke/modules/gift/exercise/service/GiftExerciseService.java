package cn.sparke.modules.gift.exercise.service;

import cn.sparke.modules.gift.exercise.entity.GiftExerciseEntity;
import cn.sparke.modules.gift.exercise.mapper.GiftExerciseMapper;
import cn.sparke.modules.paper.paperStructure.entity.PaperStructureEntity;
import cn.sparke.modules.paper.paperStructure.mapper.PaperStructureMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;

/**
 * 课后作业Dao
 *
 * @author spark
 * @Date 2017-08-21 15:23:45
 */
@Service
public class GiftExerciseService{
    @Autowired
    private GiftExerciseMapper giftExerciseMapper;
    @Autowired
    private PaperStructureMapper paperStructureMapper;

    public void save(GiftExerciseEntity giftExercise){
        PaperStructureEntity paperStructureEntity = paperStructureMapper.getById(giftExercise.getStructureId());
        giftExercise.setQuestionType(paperStructureEntity.getContentType());
        giftExercise.setSort(0);
        giftExercise.preInsert();
        giftExerciseMapper.insert(giftExercise);
    }

    public void update(GiftExerciseEntity giftExercise){
        giftExercise.preUpdate();
        giftExerciseMapper.update(giftExercise);
    }

    public GiftExerciseEntity get(GiftExerciseEntity giftExercise){
        return giftExerciseMapper.get(giftExercise);
    }

    public GiftExerciseEntity getById(String id){
        return giftExerciseMapper.getById(id);
    }
    public Page<GiftExerciseEntity> findList(GiftExerciseEntity giftExercise){
       return giftExerciseMapper.findList(giftExercise);
    }

    public void deleteById(String id){
      giftExerciseMapper.deleteById(id);
    }

    public void delete(GiftExerciseEntity entity){
        entity = giftExerciseMapper.get(entity);
        giftExerciseMapper.deleteById(entity.getId());
    }

}
