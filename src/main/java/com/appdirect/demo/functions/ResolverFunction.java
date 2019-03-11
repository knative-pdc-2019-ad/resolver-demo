package com.appdirect.demo.functions;

import static org.slf4j.LoggerFactory.getLogger;

import com.appdirect.demo.functions.domain.bo.FieldResolverConfig;
import com.appdirect.demo.functions.domain.bo.RawEvent;
import com.appdirect.demo.functions.domain.bo.ResolvedEvent;
import com.appdirect.demo.functions.resolver.ResolverManager;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.function.Function;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

@SpringBootApplication
public class ResolverFunction implements Function<RawEvent, ResolvedEvent> {

  private static final Logger LOGGER = getLogger(MethodHandles.lookup().lookupClass());

  private ResolverManager resolverManager;
  private final FieldResolverConfig resolverConfig;

  @Autowired
  public ResolverFunction(ResolverManager resolverManager) {
    this.resolverManager = resolverManager;
    this.resolverConfig = resolverConfig();
  }

  @Override
  public ResolvedEvent apply(RawEvent rawEvent) {

    LOGGER.info("Received: {}", rawEvent);

    return ResolvedEvent.builder()
        .eventId(
            resolverManager.apply(rawEvent, resolverConfig.getFields().get("f_event_id"))
        )
        .eventDateTime(
            resolverManager.apply(rawEvent, resolverConfig.getFields().get("f_event_date_time"))
        )
        .userId(
            resolverManager.apply(rawEvent, resolverConfig.getFields().get("f_user_id"))
        )
        .productId(
            resolverManager.apply(rawEvent, resolverConfig.getFields().get("f_product_id"))
        )
        .quantity(
            resolverManager.apply(rawEvent, resolverConfig.getFields().get("f_quantity"))
        )
        .totalPrice(
            resolverManager.apply(rawEvent, resolverConfig.getFields().get("f_total_price"))
        )
        .build();
  }

  //......##### internal #####......//

  private FieldResolverConfig resolverConfig() {
    InputStream is = getClass().getClassLoader().getResourceAsStream("config/resolver.yaml");
    Yaml yaml = new Yaml(new Constructor(FieldResolverConfig.class));
    return yaml.load(is);
  }

  public static void main(String[] args) {
    SpringApplication.run(ResolverFunction.class, args);
  }
}
