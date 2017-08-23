package cn.sparke.modules.simple.qrCaptionListening.service;

import cn.sparke.core.common.exception.BizExceptionEnum;
import cn.sparke.core.common.exception.BussinessException;
import cn.sparke.core.common.utils.ExcelToObject;
import cn.sparke.core.common.utils.ValidatedUtil;
import cn.sparke.modules.paper.paper.entity.PaperEntity;
import cn.sparke.modules.paper.paperCatalog.entity.PaperCatalogEntity;
import cn.sparke.modules.qrcode.QrCaptionListening.entity.QrCaptionListeningEntity;
import cn.sparke.modules.qrcode.QrCaptionListening.mapper.QrCaptionListeningMapper;
import cn.sparke.modules.qrcode.QrCaptionListening.service.QrCaptionListeningService;
import cn.sparke.modules.qrcode.qrCode.entity.QrCodeEntity;
import cn.sparke.modules.qrcode.qrCode.service.QrCodeService;
import cn.sparke.modules.questionbank.CaptionListening.entity.CaptionListeningEntity;
import cn.sparke.modules.questionbank.CaptionListening.mapper.CaptionListeningMapper;
import cn.sparke.modules.simple.qrCaptionListening.entity.SimpleCaptionListeningEntity;
import cn.sparke.modules.simple.qrCaptionListening.mapper.SimpleCaptionListeningMapper;
import com.github.pagehelper.Page;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class SimpleCaptionListeningService {
    @Autowired
    private SimpleCaptionListeningMapper mapperForFindList;

    @Autowired
    private QrCaptionListeningMapper qrCaptionListeningMapper;
    @Autowired
    private QrCaptionListeningService qrCaptionListeningService;
    @Autowired
    private CaptionListeningMapper captionListeningMapper;
    @Autowired
    private QrCodeService qrCodeService;


    public Page<SimpleCaptionListeningEntity> findList(SimpleCaptionListeningEntity entity) {
        return mapperForFindList.findList(entity);
    }

    public void deleteByQrCode(String qrCode) {
        qrCaptionListeningMapper.deleteByQrcode(qrCode);
    }

    public QrCaptionListeningEntity getQrListeningByQrcode(String qrcode){
        QrCaptionListeningEntity queryCondition = new QrCaptionListeningEntity();
        queryCondition.setQrCode(qrcode);
        return qrCaptionListeningMapper.get(queryCondition);
    }

    @Transactional
    public void saveOrUpdate(QrCaptionListeningEntity entity) {
        QrCaptionListeningEntity oldEntity = qrCaptionListeningService.get(entity.getQrCode());
        entity.setPaperId("");
        if(Objects.isNull(oldEntity)){
            //不带qr的
            CaptionListeningEntity cle = new CaptionListeningEntity();
            BeanUtils.copyProperties(entity,cle );
            cle.preInsert();
            captionListeningMapper.insert(cle);
            //qr的
            entity.preInsert();
            entity.setSort(0);
            entity.setCaptionListeningId(cle.getId());
            entity.setQrCode(entity.getQrCode());
            entity.preInsert();
            qrCaptionListeningMapper.insert(entity);

        }else{
            //不带qr的
            CaptionListeningEntity cle = new CaptionListeningEntity();
            BeanUtils.copyProperties(entity,cle );
            cle.setId(oldEntity.getCaptionListeningId());
            cle.preUpdate();
            captionListeningMapper.update(cle);
            //update
            entity.setId(oldEntity.getId());
            entity.preUpdate();
            qrCaptionListeningMapper.update(entity);
        }
    }

    public void upload(MultipartFile file) throws Exception {
        //文件校验
        if (file.isEmpty())
            throw new BussinessException(BizExceptionEnum.FILE_NOT_FOUND);
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if (!"xls".equals(suffix) && !"XLS".equals(suffix))
            throw new BussinessException(BizExceptionEnum.PAPER_EXCEL_VERSION_ERROR);
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        List<QrCaptionListeningEntity> list = ExcelToObject.convert(workbook, "simple_caption_listening_template_data.xls", new QrCaptionListeningEntity());
        validExcel(list);

        for (int i=0; i<list.size(); i++){
            QrCaptionListeningEntity qrEntity = list.get(i);
            QrCodeEntity qrCode = qrCodeService.get(qrEntity.getQrCode(),6);
            if(qrCode==null){
                throw new Exception("第"+(i+1)+"行数据有误:二维码不存在");
            }
            saveOrUpdate(qrEntity);
        }

    }

    private void validExcel(List<QrCaptionListeningEntity> list) throws Exception {
        if(list==null || list.size()==0){
            throw new BussinessException(BizExceptionEnum.PAPER_EXCEL_EMPTY);
        }
        for (int i=0; i<list.size(); i++){
            ValidatedUtil.ValidateResult validateResult = ValidatedUtil.validator(list.get(i));
            if (!validateResult.isValid()){
                throw new Exception("第"+(i+1)+"行数据有误:"+validateResult.getErrMsg());
            }
        }
    }
}
