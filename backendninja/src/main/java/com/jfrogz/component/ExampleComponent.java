package com.jfrogz.component;

import com.jfrogz.controller.Example3Controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component("exampleComponent")
public class ExampleComponent {
    public static final Log logger= LogFactory.getLog(ExampleComponent.class);

    public void sayHello(){
        logger.info("HELLO FROM EXAMPLECOMPONENT");
    }
}
