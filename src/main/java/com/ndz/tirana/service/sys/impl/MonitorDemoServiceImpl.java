package com.ndz.tirana.service.sys.impl;

import com.ndz.tirana.service.sys.MonitorDemoService;
import io.micrometer.core.instrument.Counter;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitorDemoServiceImpl implements MonitorDemoService {
    private final Counter counter;

    @Autowired
    public MonitorDemoServiceImpl(MeterRegistry meterRegistry) {
        // 创建一个名为"myService.methodCalls"的计数器Meter
        this.counter = meterRegistry.counter("MonitorDemoService.methodCalls");
    }

    public void myMethod() {
        // 方法调用时，计数器加1
        counter.increment();
        // ... 方法的其他逻辑
    }
}
