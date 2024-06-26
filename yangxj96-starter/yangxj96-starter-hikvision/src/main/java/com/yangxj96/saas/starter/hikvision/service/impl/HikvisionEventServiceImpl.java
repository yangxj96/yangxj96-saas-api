package com.yangxj96.saas.starter.hikvision.service.impl;

import com.yangxj96.saas.starter.hikvision.helper.HikvisionHelper;
import com.yangxj96.saas.starter.hikvision.props.HikvisionProperties;
import com.yangxj96.saas.starter.hikvision.response.dto.EventSubscriptionDto;
import com.yangxj96.saas.starter.hikvision.response.dto.EventUnSubscriptionDto;
import com.yangxj96.saas.starter.hikvision.response.entity.EventDetails;
import com.yangxj96.saas.starter.hikvision.service.HikvisionEventService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 时间服务接口层实现
 */
@Service
public class HikvisionEventServiceImpl implements HikvisionEventService {

    @Resource
    private HikvisionProperties properties;

    @Override
    public void subscribe(EventSubscriptionDto params) throws Exception {
        HikvisionHelper.postRequest("/api/eventService/v1/eventSubscriptionByEventTypes", params);
    }

    @Override
    public void subscribeDefault() throws Exception {
        var params = new EventSubscriptionDto();
        params.setEventTypes(properties.getEvents().getTypes());
        params.setEventDest(properties.getEvents().getDestination());
        HikvisionHelper.postRequest("/api/eventService/v1/eventSubscriptionByEventTypes", params);
    }

    @Override
    public EventDetails querySubscribeDetails() throws Exception {
        return HikvisionHelper.postRequest("/api/eventService/v1/eventSubscriptionView", "{}", EventDetails.class);
    }

    @Override
    public void unsubscribe(EventUnSubscriptionDto params) throws Exception {
        HikvisionHelper.postRequest("/api/eventService/v1/eventUnSubscriptionByEventTypes", params);
    }

    @Override
    public void unsubscribeDefault() throws Exception {
        var params = new EventSubscriptionDto();
        params.setEventTypes(properties.getEvents().getTypes());
        HikvisionHelper.postRequest("/api/eventService/v1/eventUnSubscriptionByEventTypes", params);
    }
}
