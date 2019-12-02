package com.wxmetro.ic.modules.icmgmt.web;

import com.wxmetro.ic.common.config.Constants;
import com.wxmetro.ic.common.utils.StringUtils;
import com.wxmetro.ic.modules.icmgmt.entity.*;
import com.wxmetro.ic.modules.icmgmt.service.IntelligentContainerBoxOpeningService;
import com.wxmetro.ic.modules.icmgmt.service.IntelligentContainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ApiController {

    protected Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    IntelligentContainerService icService;

    @Autowired
    private IntelligentContainerBoxOpeningService openingService;

    @RequestMapping("/register1")
    public ApiResultEntity register1(@RequestBody RegisterEntity bean) {
        ApiResultEntity result = new ApiResultEntity();

        if(StringUtils.isNotEmpty(bean.getSerialNo())){
            IntelligentContainer existed = icService.get(bean.getSerialNo());
            if(existed == null){
                logger.info("device register : " + bean.toString());
                result.setSuccess(Boolean.FALSE.toString());
                result.setMessage("货柜内部识别码不存在！");
            }else {
                existed.setDeviceId(bean.getDeviceId());
                existed.setDeviceName(bean.getDeviceName());
                existed.setStatus(Constants.IC_STATUS_ONLINE);
                icService.save(existed);

                result.setMessage("注册成功。");

                Map map = new HashMap<String , String>();
                map.put("token", existed.getToken());
                result.setData(map);
            }
        }
        return result;
    }

    @RequestMapping(value="/register", method = {RequestMethod.GET, RequestMethod.POST})
    public ApiResultEntity register(RegisterEntity bean) {
        ApiResultEntity result = new ApiResultEntity();

        if(StringUtils.isNotEmpty(bean.getSerialNo())){
            IntelligentContainer existed = icService.get(bean.getSerialNo());
            if(existed == null){
                logger.info("device register : " + bean.toString());
                result.setSuccess(Boolean.FALSE.toString());
                result.setMessage("货柜内部识别码不存在！");
            }else {
                existed.setDeviceId(bean.getDeviceId());
                existed.setDeviceName(bean.getDeviceName());
                existed.setStatus(Constants.IC_STATUS_ONLINE);
                icService.save(existed);

                result.setMessage("注册成功。");

                Map map = new HashMap<String , String>();
                map.put("token", existed.getToken());
                result.setData(map);
            }
        }
        return result;
    }



    @RequestMapping("/keepAlive1")
    public ApiResultEntity keepAlive1(@RequestBody KeepAliveEntity bean) {
        ApiResultEntity result = new ApiResultEntity();

        logger.info("device keepAlive : " + bean.toString());

        if(StringUtils.isNotEmpty(bean.getSerialNo()) && StringUtils.isNotEmpty(bean.getToken())){
            IntelligentContainer existed = icService.getBySerialNoAndToken(bean.getSerialNo(), bean.getToken());
            if(existed == null){
                result.setSuccess(Boolean.FALSE.toString());
                result.setMessage("货柜内部识别码不存在或TOKEN无效");
            }else {

                Map<String,String> boxes = bean.getBoxes();
                Set<String> keys =  boxes.keySet();

                for(String key : keys){
                    IntelligentContainerBox box = new IntelligentContainerBox();
                    box.setSerialNo(existed);
                    box.setNo(key);
                    box.setStatus(boxes.get(key));

                    existed.getIntelligentContainerBoxList().add(box);
                }
                icService.saveBoxes(existed);


                result.setMessage("状态更新成功。");
            }
        }
        return result;
    }


    @RequestMapping(value="/keepAlive" ,method = {RequestMethod.GET, RequestMethod.POST})
    public ApiResultEntity keepAlive(KeepAliveEntity bean) {
        ApiResultEntity result = new ApiResultEntity();

        logger.info("device keepAlive : " + bean.toString());

        if(StringUtils.isNotEmpty(bean.getSerialNo()) && StringUtils.isNotEmpty(bean.getToken())){
            IntelligentContainer existed = icService.getBySerialNoAndToken(bean.getSerialNo(), bean.getToken());
            if(existed == null){
                result.setSuccess(Boolean.FALSE.toString());
                result.setMessage("货柜内部识别码不存在或TOKEN无效");
            }else {

//                Map<String,String> boxes = bean.getBoxes();
//                Set<String> keys =  boxes.keySet();
//
//                for(String key : keys){
//                    IntelligentContainerBox box = new IntelligentContainerBox();
//                    box.setSerialNo(existed);
//                    box.setNo(key);
//                    box.setStatus(boxes.get(key));
//
//                    existed.getIntelligentContainerBoxList().add(box);
//                }
//                icService.saveBoxes(existed);

                Map<String,String> boxes = new HashMap<String, String>();
//                boxes.put("openbox", "1");
//                result.setData(boxes);
                IntelligentContainerBoxOpening openBoxCondition = new IntelligentContainerBoxOpening();
                openBoxCondition.setIcid(bean.getSerialNo());

                List<IntelligentContainerBoxOpening> openBoxList = openingService.findList(openBoxCondition);
                if(openBoxList != null && !openBoxList.isEmpty()){
                    IntelligentContainerBoxOpening openBox = openBoxList.get(0);

                    boxes.put("openbox", openBox.getNo());
                    result.setData(boxes);

                    //remove
                    openingService.delete(openBox);
                }

                result.setMessage("状态保持成功。");
            }
        }
        return result;
    }


}
