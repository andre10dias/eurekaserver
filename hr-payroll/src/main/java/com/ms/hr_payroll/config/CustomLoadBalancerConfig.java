package com.ms.hr_payroll.config;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Flux;

@Configuration
public class CustomLoadBalancerConfig {

    @Bean
    public ServiceInstanceListSupplier serviceInstanceListSupplier() {
        return new ServiceInstanceListSupplier() {
            @Override
            public String getServiceId() {
                return "hr-worker";
            }

            @Override
            public Flux<List<ServiceInstance>> get() {
                return Flux.just(Arrays.asList(
                        new DefaultServiceInstance("hr-worker-1", "hr-worker", "localhost", 8001, false),
                        new DefaultServiceInstance("hr-worker-2", "hr-worker", "localhost", 8002, false)
                ));
            }
        };
    }

    static class DefaultServiceInstance implements ServiceInstance {
        private final String instanceId;
        private final String serviceId;
        private final String host;
        private final int port;
        private final boolean secure;

        public DefaultServiceInstance(String instanceId, String serviceId, String host, int port, boolean secure) {
            this.instanceId = instanceId;
            this.serviceId = serviceId;
            this.host = host;
            this.port = port;
            this.secure = secure;
        }

        @Override
        public String getInstanceId() {
            return instanceId;
        }

        @Override
        public String getServiceId() {
            return serviceId;
        }

        @Override
        public String getHost() {
            return host;
        }

        @Override
        public int getPort() {
            return port;
        }

        @Override
        public boolean isSecure() {
            return secure;
        }

        @Override
        public URI getUri() {
            return URI.create((isSecure() ? "https" : "http") + "://" + getHost() + ":" + getPort());
        }

        @Override
        public Map<String, String> getMetadata() {
            return Collections.emptyMap();
        }
    }
}
