package team.mcqueen.smartdisplay.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@EnableFeignClients(basePackages = ["team.mcqueen.smartdisplay.ujin.generated.api"])
@Configuration
class UjinApiConfig