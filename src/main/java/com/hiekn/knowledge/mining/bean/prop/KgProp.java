package com.hiekn.knowledge.mining.bean.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "kgprop")
public class KgProp {
//   private NlpProp nlp;
//    private CounterProp counter;
//    private PatternProp pattern;
//    private RelatedProp related;

private Map nlp = new HashMap();

}
