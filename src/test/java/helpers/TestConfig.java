package helpers;

import org.aeonbits.owner.Config;

import java.net.URL;

@Config.Sources({
        "classpath:common.properties"
})
public interface TestConfig extends Config {
    @DefaultValue("3000")
    int timeout();

    @Key("url")
    String url();
}
